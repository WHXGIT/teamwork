package com.kingdee.inte.teamwork.service.impl;

import com.kingdee.inte.teamwork.common.AddrProviderEnum;
import com.kingdee.inte.teamwork.google.TCConst;
import com.kingdee.inte.teamwork.service.AddrService;
import com.kingdee.inte.teamwork.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 地址服务
 *
 * @author Administrator
 * @date 2020/7/28 10:40
 */
@Service
public class AddrServiceImpl implements AddrService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddrServiceImpl.class);
	private static final String ADDR = "address";
	private static final String LOCA = "location";

	private static final String OUTPUT = "output";
	private static final String BAIDU_AK = "ZgFLu2sUXZjEZHQkViRN8Exu7vjzqI03";

	private static final String GOOGLE_AK = "AIzaSyDMG25XEEtUPjYWNGtF4CIwctk62_Z-6pE";

	private static final String AUTO_NAVI_SERVER_AK = "1d775467b6ef6e0d1ced8beda8b84798";
	private static final String AUTO_NAVI_WEB_AK = "1fca6ac01460ba30da59296034c691a4";

	private static final String TIME_STP = "timestamp";
	private static final String IP = "ip";

	@Override
	public String getGeoCoder(String address, String provider) throws IOException {
		LOGGER.info("地址服务， 地址：{}， 服务提供者：{}", address, provider);
		AddrProviderEnum addrProviderEnum = AddrProviderEnum.getEnum(provider);
		Map<String, String> params = new HashMap<>(TCConst.DEFAULT_CAPACITY);
		params.put(ADDR, address);
		params.put(addrProviderEnum.getKeyLabel(), BAIDU_AK);
		params.put(OUTPUT, addrProviderEnum.getOutput());
		String result = HttpUtils.get(URI.create(addrProviderEnum.getaConst().getGeoCoderUrl()), null, params);
		return result;
	}

	@Override
	public String getReverseGeoCoder(String location, String provider) throws IOException {
		LOGGER.info("经纬度， 坐标：{}， 服务提供者：{}", location, provider);
		AddrProviderEnum addrProviderEnum = AddrProviderEnum.getEnum(provider);
		Map<String, String> params = new HashMap<>(TCConst.DEFAULT_CAPACITY);
		params.put(LOCA, location);
		params.put(addrProviderEnum.getKeyLabel(), BAIDU_AK);
		params.put(OUTPUT, addrProviderEnum.getOutput());
		String result = HttpUtils.get(URI.create(addrProviderEnum.getaConst().getReverseGeoCoderUrl()), null, params);
		return result;
	}

	@Override
	public String getTimezone(String addrOrLocation, String provider) throws IOException {
		LOGGER.info("地址， 地址或坐标：{}， 服务提供者：{}", addrOrLocation, provider);
/*		String REX = "^[d]+.[d]+,[d]+.[d]+$";
		Pattern pattern = Pattern.compile(REX);
		Matcher matcher = pattern.matcher(addrOrLocation);
		if (matcher.matches()) {

		} else {

		}*/
		AddrProviderEnum addrProviderEnum = AddrProviderEnum.getEnum(provider);
		Map<String, String> params = new HashMap<>(TCConst.DEFAULT_CAPACITY);
		params.put(LOCA, addrOrLocation);
		params.put(addrProviderEnum.getKeyLabel(), BAIDU_AK);
		params.put(OUTPUT, addrProviderEnum.getOutput());
		long epochSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));

		params.put(TIME_STP, String.valueOf(epochSecond));
		String result = HttpUtils.get(URI.create(addrProviderEnum.getaConst().getTimezoneUrl()), null, params);
		return result;
	}

	@Override
	public String getPosition(String ip, String provider) throws IOException {
		if (StringUtils.isBlank(ip)) {
			InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
			String hostAddress = address.getHostAddress();
			System.out.println(hostAddress);
		}
		AddrProviderEnum addrProviderEnum = AddrProviderEnum.getEnum(provider);
		Map<String, String> params = new HashMap<>(TCConst.DEFAULT_CAPACITY);
		params.put(IP, ip);
		params.put(addrProviderEnum.getKeyLabel(), BAIDU_AK);
		String result = HttpUtils.get(URI.create(addrProviderEnum.getaConst().getPositionUrl()), null, params);
		return result;
	}

	@Override
	public String getNavigation(String address, String provider) throws IOException {
		AddrProviderEnum addrProviderEnum = AddrProviderEnum.getEnum(provider);
		Map<String, String> params = new HashMap<>(TCConst.DEFAULT_CAPACITY);
		params.put(LOCA, address);
		params.put(addrProviderEnum.getKeyLabel(), BAIDU_AK);
		params.put(OUTPUT, addrProviderEnum.getOutput());
		long epochSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));

		params.put(TIME_STP, String.valueOf(epochSecond));
		String result = HttpUtils.get(URI.create(addrProviderEnum.getaConst().getTimezoneUrl()), null, params);
		return result;
	}

	@Override
	public String getGoogleJS() throws IOException {
		String jsUrl = "https://maps.googleapis.com/maps/api/js?key=" + GOOGLE_AK;
		String result = HttpUtils.get(URI.create(jsUrl), null, null, "127.0.0.1", 1080, "http");
		return result;
	}
}
