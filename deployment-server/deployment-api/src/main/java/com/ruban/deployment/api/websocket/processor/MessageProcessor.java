package com.ruban.deployment.api.websocket.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

/**
 * 消息处理
 * 
 * @author hins
 * @date 2018年8月16日
 */
public abstract class MessageProcessor {

    protected static final Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

    /**
     * 处理消息
     * @param webSocketSession
     * @param message
     */
    public abstract void process(WebSocketSession webSocketSession, String message);
}
