package com.kingdee.inte.teamwork.common;

import java.util.ArrayList;
import java.util.List;

/**
 * description 地址服务提供商枚举
 *
 * @author Administrator
 * @date 2020/7/28 10:51
 */
public enum AddrProviderEnum {
	/**
	 * 百度
	 */
	BAIDU("Baidu",
			"百度",
			new Const("http://api.map.baidu.com/geocoding/v3/",
					"http://api.map.baidu.com/reverse_geocoding/v3/",
					"http://api.map.baidu.com/timezone/v1",
					"http://api.map.baidu.com/location/ip"),
			"json",
			"ak"),
	/**
	 * 谷歌
	 */
	GOOGLE("Google",
			"谷歌",
			new Const("https://maps.googleapis.com/maps/api/geocode/json",
					null, null, null),
			"json",
			"key"),
	/**
	 * 高德
	 */
	AUTONAVI("AutoNavi",
			"高德",
			new Const("https://restapi.amap.com/v3/geocode/geo",
					null, null, null),
			"JSON",
			"key");

	private final String nameEn;
	private final String nameCn;
	private final Const aConst;
	private final String output;
	private final String keyLabel;

	AddrProviderEnum(String nameEn, String nameCn, Const aConst, String output, String keyLabel) {
		this.nameEn = nameEn;
		this.nameCn = nameCn;
		this.aConst = aConst;
		this.output = output;
		this.keyLabel = keyLabel;
	}

	public String getNameEn() {
		return nameEn;
	}

	public String getNameCn() {
		return nameCn;
	}

	public Const getaConst() {
		return aConst;
	}

	public String getOutput() {
		return output;
	}

	public String getKeyLabel() {
		return keyLabel;
	}

	public static AddrProviderEnum getEnum(String provider) {
		AddrProviderEnum providerEnum = null;
		for (AddrProviderEnum addrProviderEnum : AddrProviderEnum.values()) {
			if (addrProviderEnum.nameEn.equals(provider)) {
				providerEnum = addrProviderEnum;
			}
		}
		return providerEnum;
	}

	public static class Const {
		public Const(String geoCoderUrl, String reverseGeoCoderUrl, String timezoneUrl, String positionUrl) {
			this.geoCoderUrl = geoCoderUrl;
			this.reverseGeoCoderUrl = reverseGeoCoderUrl;
			this.timezoneUrl = timezoneUrl;
			this.positionUrl = positionUrl;
		}

		private final String geoCoderUrl;
		private final String reverseGeoCoderUrl;
		private final String timezoneUrl;
		private final String positionUrl;

		public String getPositionUrl() {
			return positionUrl;
		}

		public String getGeoCoderUrl() {
			return geoCoderUrl;
		}

		public String getReverseGeoCoderUrl() {
			return reverseGeoCoderUrl;
		}

		public String getTimezoneUrl() {
			return timezoneUrl;
		}
	}
}
