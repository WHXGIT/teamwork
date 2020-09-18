package com.kingdee.inte.teamwork.util;

import com.alibaba.fastjson.JSON;
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
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

/**
 * description:
 *
 * @author Administrator
 * @date 2020/6/29 14:02
 */
public class HttpUtils {

	private static final PoolingHttpClientConnectionManager POOL_MANAGER = new PoolingHttpClientConnectionManager();

	static {
		//设置最大连接数
		POOL_MANAGER.setMaxTotal(100);
		//设置每个主机的最大连接
		POOL_MANAGER.setDefaultMaxPerRoute(10);
	}


	/**
	 * @param uri         请求地址
	 * @param headerParam 请求头
	 * @param bodyParam   请求体
	 * @return java.lang.String json
	 * @throws IOException io 异常
	 * @description 〈post 请求 without vpn〉
	 * @date 2020/6/18 12:54
	 * @author RD_haoxin_wang
	 */
	public static String post(URI uri, Map<String, String> headerParam, Map<String, Object> bodyParam) throws IOException {
		return post(uri, headerParam, bodyParam, null, null, null);
	}

	/**
	 * @param uri         请求地址
	 * @param headerParam 请求头
	 * @param bodyParam   请求体
	 * @param host        主机名
	 * @param port        端口号
	 * @param protocol    协议
	 * @return java.lang.String json
	 * @throws IOException io 异常
	 * @description 〈post 请求 with vpn〉
	 * @date 2020/6/18 12:54
	 * @author RD_haoxin_wang
	 */
	public static String post(URI uri, Map<String, String> headerParam, Map<String, Object> bodyParam,
	                          String host, Integer port, String protocol) throws IOException {
		if (headerParam == null) {
			headerParam = Collections.emptyMap();
		}
		if (bodyParam == null) {
			bodyParam = Collections.emptyMap();
		}

		HttpPost postRequest = new HttpPost(uri);
		HttpEntity httpEntity = new StringEntity(JSON.toJSONString(bodyParam), StandardCharsets.UTF_8);
		postRequest.setEntity(httpEntity);
		headerParam.forEach(postRequest::addHeader);
		CloseableHttpClient httpClient = setVpnProxy(host, port, protocol);
		CloseableHttpResponse response = httpClient.execute(postRequest);
		String result;
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
		} else {
			throw new IOException(String.format("Http post 请求失败！-> url: %s, header: %s, body: %s",
					uri.getPath(), JSON.toJSONString(headerParam), JSON.toJSONString(bodyParam)));
		}
		return result;
	}

	/**
	 * @param uri         请求地址
	 * @param headerParam 请求头
	 * @param params      请求参数
	 * @return java.lang.String json
	 * @throws IOException io 异常
	 * @description 〈get 请求 without vpn〉
	 * @date 2020/6/18 12:54
	 * @author RD_haoxin_wang
	 */
	public static String get(URI uri, Map<String, String> headerParam, Map<String, String> params) throws IOException {
		return get(uri, headerParam, params, null, null, null);
	}

	/**
	 * @param uri         请求地址
	 * @param headerParam 请求头
	 * @param params      请求参数
	 * @param host        主机名
	 * @param port        端口号
	 * @param protocol    协议
	 * @return java.lang.String json
	 * @throws IOException io 异常
	 * @description 〈get 请求 with vpn〉
	 * @date 2020/6/18 12:54
	 * @author RD_haoxin_wang
	 */
	public static String get(URI uri, Map<String, String> headerParam, Map<String, String> params, String host, Integer port, String protocol) throws IOException {
		if (headerParam == null) {
			headerParam = Collections.emptyMap();
		}
		if (params != null && params.size() > 0) {
			StringBuilder url = new StringBuilder(uri.toString());
			url.append('?');
			params.forEach((key, val) -> {
				url.append(key).append('=').append(val).append('&');
			});
			url.deleteCharAt(url.length() - 1);
			uri = URI.create(url.toString());
		}
		CloseableHttpClient httpClient = setVpnProxy(host, port, protocol);
		HttpGet getRequest = new HttpGet(uri);
		headerParam.forEach(getRequest::addHeader);
		CloseableHttpResponse response = httpClient.execute(getRequest);
		String result;
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
		} else {
			throw new IOException(String.format("Http post 请求失败！-> url: %s, header: %s",
					uri.getPath(), JSON.toJSONString(headerParam)));
		}
		return result;
	}

	/**
	 * Method Description: Created by whx
	 * 〈 设置代理 〉
	 *
	 * @param host     vpn主机名
	 * @param port     vpn端口号
	 * @param protocol vpn协议
	 * @return org.apache.http.impl.client.CloseableHttpClient*
	 * @date 2020/7/28 13:57
	 */
	private static CloseableHttpClient setVpnProxy(String host, Integer port, String protocol) {
		CloseableHttpClient httpClient;
		// 设置vpn代理
		if (host != null && port != null && protocol != null) {
			HttpHost proxy = new HttpHost(host, port, protocol);
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
			httpClient = HttpClients.custom()
					.setConnectionManager(POOL_MANAGER)
					.setConnectionManagerShared(true)
					.setRoutePlanner(routePlanner)
					.build();
		} else {
			httpClient = HttpClients.custom()
					.setConnectionManager(POOL_MANAGER)
					.setConnectionManagerShared(true)
					.build();
		}
		return httpClient;
	}

}
