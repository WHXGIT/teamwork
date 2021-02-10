package com.kingdee.inte.teamwork.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: WebSocketManager
 *
 * @author Administrator
 * @date 2021/2/10 15:39
 */
public class SocketManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(SocketManager.class);
	private static Map<String, WebSocketSession> manager = new ConcurrentHashMap<>();

	public static void add(String key, WebSocketSession webSocketSession) {
		LOGGER.info("新添加webSocket连接 {} ", key);
		manager.put(key, webSocketSession);
	}

	public static void remove(String key) {
		LOGGER.info("移除webSocket连接 {} ", key);
		manager.remove(key);
	}

	public static WebSocketSession get(String key) {
		LOGGER.info("获取webSocket连接 {}", key);
		return manager.get(key);
	}
}
