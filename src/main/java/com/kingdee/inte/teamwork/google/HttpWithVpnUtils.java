package com.kingdee.inte.teamwork.google;

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
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

/**
 * description:
 *
 * @author Administrator
 * @date 2020/6/29 14:02
 */
public class HttpWithVpnUtils {

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
	                          String host, int port, String protocol) throws IOException {
		if (headerParam == null) {
			headerParam = Collections.emptyMap();
		}
		if (bodyParam == null) {
			bodyParam = Collections.emptyMap();
		}
		HttpHost proxy = new HttpHost(host, port, protocol);
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		CloseableHttpClient httpClient = HttpClients.custom()
				.setRoutePlanner(routePlanner)
				.build();
		HttpPost postRequest = new HttpPost(uri);
		HttpEntity httpEntity = new StringEntity(JSON.toJSONString(bodyParam), StandardCharsets.UTF_8);
		postRequest.setEntity(httpEntity);
		headerParam.forEach(postRequest::addHeader);

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
	 * @param host        主机名
	 * @param port        端口号
	 * @param protocol    协议
	 * @return java.lang.String json
	 * @throws IOException io 异常
	 * @description 〈get 请求 with vpn〉
	 * @date 2020/6/18 12:54
	 * @author RD_haoxin_wang
	 */
	public static String get(URI uri, Map<String, String> headerParam, String host, int port, String protocol) throws IOException {
		if (headerParam == null) {
			headerParam = Collections.emptyMap();
		}
		HttpHost proxy = new HttpHost(host, port, protocol);
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		CloseableHttpClient httpClient = HttpClients.custom()
				.setRoutePlanner(routePlanner)
				.build();
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

}
