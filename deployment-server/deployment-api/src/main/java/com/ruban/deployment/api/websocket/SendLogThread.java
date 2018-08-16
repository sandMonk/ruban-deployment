package com.ruban.deployment.api.websocket;

import java.io.IOException;
import java.util.Date;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.ruban.deployment.api.websocket.context.LogIdToSessionHolder;
import com.ruban.deployment.common.utils.DateUtils;

/**
 * 推送步骤日志到客户端
 * 
 * @author hins
 * @date 2018年8月16日
 */
public class SendLogThread implements Runnable {

	private String uuid;

	private int MAX_LOG_LENGTH = 100; // 最大消息数

	public SendLogThread(String uuid) {
		this.uuid = uuid;
	}

	public void run() {
		System.out.println(uuid + "run...");
		WebSocketSession session = LogIdToSessionHolder.getInstance().get(uuid);
		try {
			int index = 0;
			while (index < MAX_LOG_LENGTH) {
				index++;
				session.sendMessage(new TextMessage("兄弟，部署日志更新了" + DateUtils.formatDate(new Date(), DateUtils.YYYY_MM_DD_HH_mm_ss)));
				Thread.sleep(2000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
