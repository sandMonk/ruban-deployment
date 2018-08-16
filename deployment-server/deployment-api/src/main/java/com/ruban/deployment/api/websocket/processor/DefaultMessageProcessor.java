package com.ruban.deployment.api.websocket.processor;

import org.springframework.web.socket.WebSocketSession;

/**
 * 默认的消息处理
 * 
 * @author hins
 * @date 2018年8月16日
 */
public class DefaultMessageProcessor extends MessageProcessor {

    @Override
    public void process(WebSocketSession webSocketSession, String message) {
        logger.warn("默认消息处理，丢弃消息 ... message: " + message);
    }
}
