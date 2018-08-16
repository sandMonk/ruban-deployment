package com.ruban.deployment.api.shell.context;

import java.util.HashMap;
import java.util.Map;

import com.jcraft.jsch.Session;

/**
 * shell服务 session本地缓存池
 * 
 * @author hins
 * @date 2018年8月16日
 */
public class ShellSessionHolder {

	private Map<String, Session> sessionMap = new HashMap<String, Session>();

	private static ShellSessionHolder shellSessionHolder = new ShellSessionHolder();

	private ShellSessionHolder() {
	}

	public static ShellSessionHolder getInstance() {
		return shellSessionHolder;
	}

	public Session get(String key) {
		return sessionMap.get(key);
	}

	public void add(String key, Session session) {
		sessionMap.put(key, session);
	}

	public void remove(String key) {
		sessionMap.remove(key);
	}
}
