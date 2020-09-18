package com.kingdee.inte.teamwork.google;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.inte.teamwork.common.DistributeSessionlessCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * description:
 *
 * @author Andy
 * @version 1.0
 * @date 06/23/2020 23:03
 */
public class GoogleCredential {
	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleCredential.class);

	private static final String TYPE = "type";
	private static final String PROJECT_ID = "project_id";
	private static final String PRIVATE_KEY_ID = "private_key_id";
	private static final String PRIVATE_KEY = "private_key";
	private static final String CLIENT_EMAIL = "client_email";
	private static final String CLIENT_ID = "client_id";
	private static final String AUTH_URI = "auth_uri";
	private static final String TOKEN_URI = "token_uri";
	private static final String AUTH_PROVIDER_X509_CERT_URL = "auth_provider_x509_cert_url";
	private static final String CLIENT_X509_CERT_URL = "client_x509_cert_url";

	private static final String BEGIN_PRIVATE_KEY = "BEGIN PRIVATE KEY";
	private static final String END_PRIVATE_KEY = "END PRIVATE KEY";

	private static final String JSON_CONTENT_LABEL = "fjson_content";
	private static final String SCOPE_URL_LABEL = "fscope_url";
	private static final String VPN_HOST_LABEL = "fvpn_host";
	private static final String VPN_PORT_LABEL = "fvpn_port";
	private static final String VPN_PROTOCOL_LABEL = "fprotocol";
	private static final String GLOSSARY_FILENAME_LABEL = "fglsy_fn";
	private static final String PROJECT_NUM_LABEL = "fproj_num";
	private static final String GLOSSARY_BUCKET_LABEL = "fglsy_bucket";

	private static final String TRANS_API_ROOT_V3_URL = "https://translation.googleapis.com/v3/";
	private static final String TRANS_API_ROOT_URL = "https://translation.googleapis.com/v3/projects/";

	private static final String SUCCEEDED = "SUCCEEDED";


	/**
	 * 重试次数
	 */
	private static final int RETRY_TIMES = 50;

	/**
	 * 等待时间
	 */
	private static final long WAIT_TIME = 2 * 60 * 1000;

	/**
	 * 11个-
	 */
	private static final String SPLIT_11 = "-----------";


	private GooglePrivateConf conf;


	private String glossaryId;

	private GoogleTokenTimerSingleton singleton;

	public GoogleCredential(String glossaryId) {
		this.glossaryId = glossaryId;
		if (conf == null) {
			ResultSet set = getResultSet();
			conf = buildGooglePrivateConf(set);
			singleton = GoogleTokenTimerSingleton.getInstance(conf);
			if (!singleton.isRunning()) {
				singleton.startGenerateTokenTimer();
			}
		}
	}

	public GoogleTokenTimerSingleton getSingleton() {
		return singleton;
	}

	private static ResultSet getResultSet() {
		// todo
		return null;
	}

	/**
	 * @param set 读取数据库得到的内容
	 * @return kd.bos.inte.entity.GooglePrivateKeyJson
	 * @throws SQLException
	 * @description 〈根据 json 字符串构建GooglePrivateKeyJson 〉
	 * @date 2020/6/17 18:16
	 * @author RD_haoxin_wang
	 */
	private GooglePrivateConf buildGooglePrivateConf(ResultSet set) {
		/*
		String json = set.getString(JSON_CONTENT_LABEL);
		String scope = set.getString(SCOPE_URL_LABEL);
		String vpnHost = set.getString(VPN_HOST_LABEL);
		int vpnPort = Integer.valueOf(set.getString(VPN_PORT_LABEL));
		String vpnProtocol = set.getString(VPN_PROTOCOL_LABEL);
		String glossaryFilename = set.getString(GLOSSARY_FILENAME_LABEL);
		String glossaryBucket = set.getString(GLOSSARY_BUCKET_LABEL);
		String projectNum = set.getString(PROJECT_NUM_LABEL);*/
		String json = "{\n" +
				"  \"type\": \"service_account\",\n" +
				"  \"project_id\": \"translationadvanced01\",\n" +
				"  \"private_key_id\": \"2ac27d9bc2a95ef5a1efa1803ee9a8b32bb06a0e\",\n" +
				"  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC7Wmwv9Wz/BHxZ\\nY56XwNZYcH/v9jl/Lu7v4lcWXspiClLn7SJ8stAsZX4DuuVlUxZA3WCJwBh9Vf69\\nVkJQ8CG7Dxechf7gnoUwkZ0Mgft1AF/U3T+JBlFfIigow1lBK5uqfRU3yjdFW25L\\n9HOILWRoQ0EjycBRs1GPBBmQ2JRKZ+UMLIT1dTo3ABV8c3fDSqgiv3lJhFHvNk9b\\n0h76eJPIJXv43kMaVtoreGWfFxRV6vpBrg/oM7ZTZ4Crdc3pbP2lp1WjfO7woLSq\\ng3cFHxB+OCIhkQf+5ULf06gNGXljbnbt6ybQ6lPZgKqxHvg13SwQ48x2MlEm2KqK\\nM24pT7q1AgMBAAECggEAEZTMrjtzvQKowKKukUMRWj9EYjpS7vJyjaRJ09mVWFnr\\nqcQBBn6pNmFXz/XL7RQ89F1R39tc8PYO76mWk43VPH6QEqooXt/BU+t2N2RDOpmM\\nZKdWfP9Qx4KnDhw21fzHoNoYEaIySZno4cvQw6iu0XHuPPODzqbrMmaKF6ObVtf7\\nzWgbR0B8js3HwvPsq6ZhVinmZ6mSa8qboXD7h6+AyHNlLQvJcwrsIb0bJshxfaWm\\nPf532NkYnFe9Hh8z0EOpP0Z3JaPWtIJVa4NYRnyjbBl1v/y6eOCUev5QnA9bqyi7\\noefI54hbTtebtr1NF5Nc8DYYcjmXuQbW6dMNr+YEwQKBgQDvIWjf+SMydmyyaS/T\\nmZQHDGSPUbtUsXrKwAeUqJA+bkq26pPuio9gzDipOYVgukdHv+fBAUIUmrzgPcLM\\nGHYmBvgcb7/M6bpb44Y37UaRJCPsJwXyCcv9vJU+vkncrnG1zOUCrW2FAIWKe5VH\\nkv7/yKeWRNlm2oL2s3len5Kf3QKBgQDIkfAn4nBXiYg1LEC+PRE/6NkZsIW31d/X\\nBqlIRfWiXG2PAcMxD84rK79oURsjqVQc6vnDu7Jh+UFrJyvkSLPYPzTTTBcyfxKO\\nezKz/GrxCx6KVgRyt3LK8QJ8hgIoTJKTOwXbkj5Vf9z3JsKO4fQ2n4YHZEvDehmY\\n1mUp+PnEuQKBgQChQwKaqESYL0y2NEFQjW1KI41dKV0PjHCmckGqetJrh61TFXaV\\nk1OFj7NWS7gqtvSC6mO7wMFza7ab8op/YK+sjV6crq/F3IkUlqdY5+aC0sB27QoR\\nY5Hxl09+dI0TCYEdfQOjfpkw7F0iOp09G2Bj1klOCTgjKJDRFc9vhGU48QKBgQDA\\nUbd+9xCTz2uM2O8Hu1Sj/GjXyjyd5vVooJmHlJWQ5qGjA6SfkbE2Eg/KPiMQ0de2\\nltWBiqPkb/X7gicDEXdpfknv5cFEiTZI6vbWQM5mbSwOp6ZCPYdT8z9YGcNN0wal\\nKG5YeWVN5HoDfuYFzcG28sW4psJDIPwUYlfEpFkS+QKBgFPN4pg64fH+nAo5I50M\\ni8yLiPBpYV7X1DShnM9gGkpIFdspdbauPbFOF1NKUip3iNN+E/F7kds+O5GXyqGb\\nz5cpBFCd+5YiMt3vFsgW1mLQpg2fewYDz2vxELHOR5ExycFd1pOCm2b1Yf5gLBaq\\nlvFrJn1ldz61qyXXpRVVIc9K\\n-----END PRIVATE KEY-----\\n\",\n" +
				"  \"client_email\": \"google-trans@translationadvanced01.iam.gserviceaccount.com\",\n" +
				"  \"client_id\": \"116101300796684973266\",\n" +
				"  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
				"  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
				"  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
				"  \"client_x509_cert_url\":\"https://www.googleapis.com/robot/v1/metadata/x509/google-trans%40translationadvanced01.iam.gserviceaccount.com\"\n" +
				"}";
		String scope = "https://www.googleapis.com/auth/cloud-platform";
		String vpnHost = "127.0.0.1";
		int vpnPort = 1080;
		String vpnProtocol = "HTTP";
		String glossaryFilename = "whx_test.csv";
		String glossaryBucket = "glossary_bucket01";
		String projectNum = "264423809822";
		JSONObject jsonObject = JSON.parseObject(json);
		String privateKeyBefore = jsonObject.getString(PRIVATE_KEY);
		privateKeyBefore = privateKeyBefore.replace(BEGIN_PRIVATE_KEY, "-").replace(END_PRIVATE_KEY, "-");
		String privateKey = privateKeyBefore.split(SPLIT_11)[1];
		GooglePrivateConf googlePrivateConf = Builder.of(GooglePrivateConf::new)
				.with(GooglePrivateConf::setType, jsonObject.getString(TYPE))
				.with(GooglePrivateConf::setProjectId, jsonObject.getString(PROJECT_ID))
				.with(GooglePrivateConf::setPrivateKeyId, jsonObject.getString(PRIVATE_KEY_ID))
				.with(GooglePrivateConf::setPrivateKey, privateKey)
				.with(GooglePrivateConf::setClientEmail, jsonObject.getString(CLIENT_EMAIL))
				.with(GooglePrivateConf::setClientId, jsonObject.getString(CLIENT_ID))
				.with(GooglePrivateConf::setAuthUri, jsonObject.getString(AUTH_URI))
				.with(GooglePrivateConf::setTokenUri, jsonObject.getString(TOKEN_URI))
				.with(GooglePrivateConf::setAuthProviderX509CertUrl, jsonObject.getString(AUTH_PROVIDER_X509_CERT_URL))
				.with(GooglePrivateConf::setClientX509CertUrl, jsonObject.getString(CLIENT_X509_CERT_URL))
				.with(GooglePrivateConf::setScope, scope)
				.with(GooglePrivateConf::setVpnHost, vpnHost)
				.with(GooglePrivateConf::setVpnPort, vpnPort)
				.with(GooglePrivateConf::setProtocol, vpnProtocol)
				.with(GooglePrivateConf::setGlossaryId, glossaryId)
				.with(GooglePrivateConf::setGlossaryFilename, glossaryFilename)
				.with(GooglePrivateConf::setGlossaryBucket, glossaryBucket)
				.with(GooglePrivateConf::setProjectNum, projectNum)
				.build();
		return googlePrivateConf;
	}

	/**
	 * @param sourceLanguageCode 源语言编码
	 * @param targetLanguageCode 目标语言编码
	 * @param contents           内容数组
	 * @return java.util.List<java.lang.String> 结果数组
	 * @throws IOException io 异常
	 * @description 〈调用翻译api〉
	 * @date 2020/6/18 12:57
	 * @author RD_haoxin_wang
	 */
	public List<String> callingTranslateApi(String sourceLanguageCode,
	                                        String targetLanguageCode,
	                                        String[] contents) throws IOException {
		String accessToken = singleton.getTokenFromCache();
		// 构造请求头
		Map<String, String> headerParam = new HashMap<>(TCConst.DEFAULT_CAPACITY);
		headerParam.put("Authorization", "Bearer " + accessToken);

		// 构造请求体
		Map<String, Object> bodyParam = new HashMap<>(TCConst.DEFAULT_CAPACITY);
		bodyParam.put("sourceLanguageCode", sourceLanguageCode);
		bodyParam.put("targetLanguageCode", targetLanguageCode);
		Map<String, String> glossaryMap = new HashMap<>(1);
		glossaryMap.put("glossary", "projects/" + conf.getProjectId() + "/locations/us-central1/glossaries/" + conf.getGlossaryId());
		bodyParam.put("glossaryConfig", glossaryMap);
		bodyParam.put("contents", contents);

		// 构建 访问地址
		String url = TRANS_API_ROOT_URL + conf.getProjectNum() + "/locations/us-central1:translateText";
		URI uri = URI.create(url);
		String result = HttpWithVpnUtils.post(uri, headerParam, bodyParam, conf.getVpnHost(), conf.getVpnPort(), conf.getProtocol());

		JSONArray glossaryTranslations = JSONObject.parseObject(result.replace("@", "")).getJSONArray("glossaryTranslations");
		List<String> list = new ArrayList<>(TCConst.DEFAULT_CAPACITY);
		glossaryTranslations.forEach(item -> {
			String translatedText = JSON.parseObject(item.toString()).getString("translatedText");
			list.add(translatedText);
		});
		return list;
	}

	public String callingTranslateApi(String sourceLanguageCode,
	                                  String targetLanguageCode,
	                                  String content) throws IOException {
		String[] contents = new String[]{content};
		List<String> list = callingTranslateApi(sourceLanguageCode, targetLanguageCode, contents);
		return list.get(0);
	}

}
