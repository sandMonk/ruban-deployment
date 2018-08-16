package com.ruban.deployment.api.websocket.context;

import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.springframework.web.socket.WebSocketSession;

/**
 * websocket session本地缓存池
 * 
 * @author hins
 * @date 2018年8月16日
 */
public class LogIdToSessionHolder {

    /**
     * 保存 serverDeployId -> session 的关系
     */
    private Map<String, WebSocketSession> idToSessionMap = new ConcurrentHashMap<String, WebSocketSession>();

    private static LogIdToSessionHolder logIdToSessionHolder = new LogIdToSessionHolder();

    private LogIdToSessionHolder() {}

    public static LogIdToSessionHolder getInstance() {
        return logIdToSessionHolder;
    }

    public WebSocketSession get(String key) {
        return idToSessionMap.get(key);
    }

	public void add(String key, WebSocketSession session) {
		idToSessionMap.put(key, session);
	}

    public void remove(int serverDeployId) {
        idToSessionMap.remove(serverDeployId);
    }
}
