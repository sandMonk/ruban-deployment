package com.ruban.deployment.api.websocket.request;

import java.io.Serializable;

/**
 * websocket客户端发送消息的基础对象
 * 
 * @author hins
 * @date 2018年8月16日
 */
public class RequestMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String type;

    public RequestMessage(String type) {
        this.type = type;
    }

    public RequestMessage() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
