package com.kingdee.inte.teamwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * description: 缓存服务
 *
 * @author Administrator
 * @date 2020/10/22 12:27
 */

@Service("caffeineCacheService")
public class CaffeineCacheServiceImpl {
	@Autowired
	CacheManager caffeineCacheManager;

	private final static String DEFAULT_CACHE = "default";

	public <T> T getValue(Object key) {
		if (key == null) {
			return null;
		}

		Cache cache = caffeineCacheManager.getCache(DEFAULT_CACHE);
		if (cache != null) {
			Cache.ValueWrapper wrapper = cache.get(key);
			if (wrapper != null) {
				return (T) wrapper.get();
			}
		}

		return null;
	}

	public <T> T getValue(String cacheName, Object key) {
		if (cacheName == null || key == null) {
			return null;
		}

		Cache cache = caffeineCacheManager.getCache(cacheName);
		if (cache != null) {
			Cache.ValueWrapper wrapper = cache.get(key);
			if (wrapper != null) {
				return (T) wrapper.get();
			}
		}

		return null;
	}

	public void putValue(Object key, Object value) {
		if (key == null || value == null) {
			return;
		}

		Cache cache = caffeineCacheManager.getCache(DEFAULT_CACHE);
		if (cache != null) {
			cache.put(key, value);
		}
	}

	public void putValue(String cacheName, Object key, Object value) {
		if (cacheName == null || key == null || value == null) {
			return;
		}
		Cache cache = caffeineCacheManager.getCache(cacheName);
		if (cache != null) {
			cache.put(key, value);
		}
	}
}
