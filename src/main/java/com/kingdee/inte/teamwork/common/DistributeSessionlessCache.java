package com.kingdee.inte.teamwork.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description:
 *
 * @author Andy
 * @version 1.0
 * @date 06/28/2020 22:45
 */
public class DistributeSessionlessCache {
	private Map<String, String> map = new ConcurrentHashMap<>();

	public DistributeSessionlessCache() {

	}

	public void put(String key, String val) {
		map.put(key, val);
	}

	public String get(String key) {
		return map.get(key);
	}


}
