package com.ruban.deployment.api.websocket.request;

/**
 * websocket客户端首次请求的发送消息
 * 
 * @author hins
 * @date 2018年8月16日
 */
public class StartMessage extends RequestMessage {
	
	private static final long serialVersionUID = 1L;
	
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
