package com.kingdee.inte.teamwork.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * description: 缓存配置
 *
 * @author Administrator
 * @date 2020/10/22 11:33
 */

@Configuration
@EnableCaching
public class CacheConfig {
	@Bean
	public Cache<String, Object> caffeineCache() {
		return Caffeine.newBuilder()
				// 设置最后一次写入或访问后经过固定时间过期
				.expireAfterAccess(2, TimeUnit.HOURS)
				// 初始的缓存空间大小
				.initialCapacity(100)
				// 缓存的最大条数
				.maximumSize(1000)
				//缓存写入/删除 监控
				.writer(new CacheWriter<Object, Object>() {
					@Override
					public void write(Object key, Object value) { //此方法是同步阻塞的
						System.out.println("--缓存写入--:key=" + key + ", value=" + value);
					}

					@Override
					public void delete(Object key, Object value, RemovalCause cause) {
						System.out.println("--缓存删除--:key=" + key);
					}
				})
				.build();
	}

}
