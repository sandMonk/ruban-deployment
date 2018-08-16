package com.ruban.deployment.api.websocket.processor;

import java.io.IOException;
import java.util.Date;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.ruban.deployment.common.utils.DateUtils;

/**
 * 处理发布日志内容更新
 * 
 * @author hins
 * @date 2018年8月16日
 */
public class LogMessageProcessor  extends MessageProcessor {

	@Override
	public void process(WebSocketSession webSocketSession, String message) {
		
		try {
            webSocketSession.sendMessage(new TextMessage("兄弟，部署日志更新了" + DateUtils.formatDate(new Date(), DateUtils.YYYY_MM_DD_HH_mm_ss)));
        } catch (IOException e) {
            logger.error("发送消息失败", e);
        }
		
	}

}
