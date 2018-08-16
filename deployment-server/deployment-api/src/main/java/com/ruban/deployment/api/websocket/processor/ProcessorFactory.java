package com.ruban.deployment.api.websocket.processor;

import com.ruban.deployment.api.websocket.request.MessageType;

/**
 * 消息处理工厂类
 * 
 * @author hins
 * @date 2018年8月16日
 */
public class ProcessorFactory {

	public static MessageProcessor create(MessageType type) {
		switch (type) {
		case START:
			return new StartMessageProcessor();
		case DEPLOY_LOG:
			return new LogMessageProcessor();
		default:
			break;
		}
		return null;
	}
}
