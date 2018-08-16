package com.ruban.deployment.api.websocket;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.ruban.deployment.api.websocket.processor.MessageProcessor;
import com.ruban.deployment.api.websocket.processor.ProcessorFactory;
import com.ruban.deployment.api.websocket.request.MessageType;
import com.ruban.deployment.api.websocket.request.RequestMessage;

/**
 * 一个websocket的服务端实现demo
 * 
 * @author qiu.jun
 * @date 2018年8月16日
 */
@Component
public class DemoWebSocketHandler implements WebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(DemoWebSocketHandler.class);

	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1) throws Exception {

	}

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("afterConnectionEstablished ...");
	}

	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage)
			throws Exception {
		TextMessage message = (TextMessage) webSocketMessage;

		String content = message.getPayload();
		if (StringUtils.isEmpty(content)) {
			logger.error("消息内容为 empty");
		} else {
			try {
				RequestMessage request = JSONObject.parseObject(content, RequestMessage.class);
				if (request == null) {
					logger.error("消息内容格式错误: " + content);
				} else {
					MessageType messagetType = MessageType.from(request.getType());
					MessageProcessor messageProcessor = ProcessorFactory.create(messagetType);
                    messageProcessor.process(webSocketSession, content);
				}
			} catch (Exception e) {
				logger.error("消息处理出错: " + content, e);
			}
		}
	}

	public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
		logger.error("服务器异常, message: {}", throwable.getMessage(), throwable);
	}

	public boolean supportsPartialMessages() {
		return false;
	}

}
