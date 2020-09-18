package com.kingdee.inte.teamwork.google;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kingdee.inte.teamwork.common.DistributeSessionlessCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.net.URI;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * description: google 翻译生成token 定时器
 *
 * @author: Administrator
 * @date: 2020/6/29 13:07
 */
public class GoogleTokenTimerSingleton {
	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleTokenTimerSingleton.class);

	private static final String RAS = "RSA";

	/**
	 * 重试获取Token次数
	 */
	private static final int TOKEN_RETRY_TIMES = 50;

	/**
	 * 重试等待时间 10ms
	 */
	private static final int TOKEN_WAIT_TIME = 10;


	/**
	 * token 超时时间 1小时
	 */
	private static final int TOKEN_TIME_OUT = 3600 * 1000;

	/**
	 * token 10分钟
	 */
	private static final int TOKEN_TIME_DELAY = 10 * 1000;

	/**
	 * 初始化定时任务， 延迟执行时间 10 分钟
	 */
	private static final int INITIAL_DELAY = 10 * 60 * 1000;

	private static final String GRANT_TYPE = "grant_type";
	private static final String GRANT_TYPE_VAL = "urn:ietf:params:oauth:grant-type:jwt-bearer";
	private static final String ASSERTION = "assertion";
	private static final String SCOPE = "scope";

	private static final String GOOGLE_TRANS_TOKEN = "google_trans_token";

	private static GoogleTokenTimerSingleton instance;

	private GooglePrivateConf conf;

	private boolean isRunning = false;

	private ScheduledExecutorService service;

	private DistributeSessionlessCache cache = new DistributeSessionlessCache();

	public static GoogleTokenTimerSingleton getInstance(GooglePrivateConf conf) {
		if (instance == null) {
			synchronized (GoogleTokenTimerSingleton.class) {
				if (instance == null) {
					instance = new GoogleTokenTimerSingleton();
				}
			}
		}
		instance.conf = conf;
		return instance;
	}

	private GoogleTokenTimerSingleton() {
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void shutdownGenerateTokenTimer() {
		if (isRunning) {
			service.shutdown();
		}
		isRunning = false;
	}

	public synchronized void startGenerateTokenTimer() {
		Runnable runnable = () -> {
			isRunning = true;
			boolean isSuccess = retryGetToken();
			if (isSuccess) {
				LOGGER.info("通过重试机制，获取Token 成功！");
			} else {
				LOGGER.error("通过重试机制，获取Token 失败！");
			}
		};

		service = Executors.newScheduledThreadPool(5);
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(runnable, INITIAL_DELAY, TOKEN_TIME_DELAY, TimeUnit.MILLISECONDS);
	}


	public boolean retryGetToken() {
		int i = 0;
		boolean result = false;
		try {
			while (i < TOKEN_RETRY_TIMES) {
				i++;
				result = generateToken();
				if (result) {
					break;
				} else {
					wait(TOKEN_WAIT_TIME);
				}
			}
		} catch (InterruptedException e) {
			LOGGER.error("重试获取Token 失败！" + e.getMessage(), e);
		}
		return result;
	}

	/**
	 * @return java.lang.String token
	 * @throws IOException io异常
	 * @description 〈生成google access_token〉
	 * @date 2020/6/17 18:08
	 * @author RD_haoxin_wang
	 */
	public boolean generateToken() {
		boolean result = false;
		try {
			String signedJWT = this.getSignedJWT();
			String token = this.generateToken(signedJWT);
			putTokenIntoCache(token);
			result = true;
		} catch (IOException e) {
			LOGGER.error("生成token 失败！" + e.getMessage(), e);
		}
		return result;
	}

	public void putTokenIntoCache(String token) {
		cache.put(GOOGLE_TRANS_TOKEN, token);
	}


	public String getTokenFromCache() {
		String token = cache.get(GOOGLE_TRANS_TOKEN);
		if (token == null) {
			retryGetToken();
		}
		return cache.get(GOOGLE_TRANS_TOKEN);
	}

	/**
	 * @return java.lang.String signedJwt
	 * @throws IOException io异常
	 * @description 〈 根据google账号中生成的私钥文件生成 signedJwt 〉
	 * @date 2020/6/17 18:13
	 * @author RD_haoxin_wang
	 */
	private String getSignedJWT() throws IOException {
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) getPrivateKey(conf.getPrivateKey());
		Algorithm algorithm = Algorithm.RSA256(null, rsaPrivateKey);
		long now = System.currentTimeMillis();
		String signedJWT = JWT.create()
				.withKeyId(conf.getPrivateKeyId())
				.withIssuer(conf.getClientEmail())
				.withSubject(conf.getClientEmail())
				.withAudience(conf.getTokenUri())
				.withClaim(SCOPE, conf.getScope())
				.withIssuedAt(new Date(now))
				.withExpiresAt(new Date(now + TOKEN_TIME_OUT))
				.sign(algorithm);
		return signedJWT;
	}

	/**
	 * @param signedJwt 生成的signedJWT
	 * @return java.lang.String token
	 * @throws IOException io异常
	 * @description 〈生成google access_token〉
	 * @date 2020/6/17 18:09
	 * @author RD_haoxin_wang
	 */
	private String generateToken(String signedJwt) throws IOException {
		Map<String, Object> params = new HashMap<>(TCConst.DEFAULT_CAPACITY);
		params.put(GRANT_TYPE, GRANT_TYPE_VAL);
		params.put(ASSERTION, signedJwt);
		String result = HttpWithVpnUtils.post(URI.create(conf.getTokenUri()), null, params,
				conf.getVpnHost(), conf.getVpnPort(), conf.getProtocol());
		JSONObject jsonObject = JSON.parseObject(result);
		String token = jsonObject.getString(TCConst.ACCESS_TOKEN);
		return token;
	}

	/**
	 * @param priKey 密钥字符串
	 * @return java.security.PrivateKey 密钥对象
	 * @description 〈密钥字符串 生成 auth0 需要的密钥对象〉
	 * @date 2020/6/17 18:11
	 * @author RD_haoxin_wang
	 */
	private PrivateKey getPrivateKey(String priKey) {
		PrivateKey privateKey = null;
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec;
		try {
			pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(priKey));
			KeyFactory keyFactory = KeyFactory.getInstance(RAS);
			privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		} catch (Exception e) {
			LOGGER.error("获取私钥失败" + e.getMessage(), e);
		}
		return privateKey;
	}

}
