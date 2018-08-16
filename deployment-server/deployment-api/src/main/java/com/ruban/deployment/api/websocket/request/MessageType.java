package com.ruban.deployment.api.websocket.request;

/**
 * websocket 请求的类型
 * 
 * @author hins
 * @date 2018年8月16日
 */
public enum MessageType {
	
	START("START", "首次请求信息"),
	
    DEPLOY_LOG("DEPLOY_LOG", "部署步骤日志");
	
	 /**
     * 名称
     */
    private String name;

    /**
     * 信息备注
     */
    private String msg;

    private MessageType(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static MessageType from(String name){
        for(MessageType type : MessageType.values()){
            if(type.getName().equals(name)){
                return type;
            }
        }
        return null;
    }
	
}
