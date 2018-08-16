package com.ruban.deployment.api.websocket.processor;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.ruban.deployment.api.websocket.SendLogThread;
import com.ruban.deployment.api.websocket.context.LogIdToSessionHolder;
import com.ruban.deployment.api.websocket.request.StartMessage;

/**
 * 处理socket客户端首次请求
 * 
 * @author hins
 * @date 2018年8月16日
 */
public class StartMessageProcessor extends MessageProcessor {

	@Override
	public void process(WebSocketSession webSocketSession, String message) {

		StartMessage body = JSONObject.parseObject(message, StartMessage.class);
		try {
			webSocketSession.sendMessage(new TextMessage(body.getUuid() + "接收完毕，等待处理"));
			
			// 将该uuid对应的session，添加到本地缓存
			LogIdToSessionHolder.getInstance().add(body.getUuid(), webSocketSession);
			
			// 新开一个线程，每2s发送一条日志给客户端
			SendLogThread send = new SendLogThread(body.getUuid());
			Thread thread = new Thread(send);
			thread.start();
		} catch (IOException e) {
			logger.error("发送消息失败", e);
		}

	}

}
