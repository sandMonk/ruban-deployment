package com.ruban.deployment.api.shell.context;

import java.util.HashMap;
import java.util.Map;

import com.jcraft.jsch.ChannelExec;

/**
 * shell服务 channel本地缓存池
 * 
 * @author hins
 * @date 2018年8月16日
 */
public class ShellChannelHolder {

	private Map<String, ChannelExec> channelMap = new HashMap<String, ChannelExec>();

	private static ShellChannelHolder shellChannelHolder = new ShellChannelHolder();

	private ShellChannelHolder() {
	}

	public static ShellChannelHolder getInstance() {
		return shellChannelHolder;
	}

	public ChannelExec get(String key) {
		return channelMap.get(key);
	}

	public void add(String key, ChannelExec Channel) {
		channelMap.put(key, Channel);
	}

	public void remove(String key) {
		channelMap.remove(key);
	}
	
}
