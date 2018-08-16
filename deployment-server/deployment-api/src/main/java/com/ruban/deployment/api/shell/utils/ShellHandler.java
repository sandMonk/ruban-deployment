package com.ruban.deployment.api.shell.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.ruban.deployment.api.shell.context.ShellSessionHolder;

/**
 * shell命令相关工具
 * 
 * @author hins
 * @date 2018年8月16日
 */
public class ShellHandler {
	
	public static final int DEFAULT_SSH_PORT = 22; 
	
	private ArrayList<String> stdout = new ArrayList<String>();
	
	/**
	 * 连接服务器
	 * 
	 * @author hins 
	 * @date 2018年8月16日  
	 * @param token - 请求者的token
	 * @param ip - 目标服务器ip
	 * @param username - 目标服务器登陆用户名 
	 * @param password - 目标服务器登陆密码
	 */
	public void connection(final String token, final String ip, final String username, final String password){
		connection(token, ip, username, password, DEFAULT_SSH_PORT);
	}
	
	
	public void connectionByRsa(final String token, final String ip, final String username, final String file){
		connectionByRsa(token, ip, username, file, DEFAULT_SSH_PORT);
	}
	
	public void connectionByRsa(final String token, final String ip, final String username, final String file, final int port){
		JSch jsch = new JSch();
        Session session;
		try {
			jsch.addIdentity(file);
			
			//创建session并且打开连接，因为创建session之后要主动打开连接
			session = jsch.getSession(username, ip, port);
	        session.connect();
	        ShellSessionHolder.getInstance().add(token, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 连接服务器
	 * 
	 * @author hins 
	 * @date 2018年8月16日  
	 * @param token - 请求者的token
	 * @param ip - 目标服务器ip
	 * @param username - 目标服务器登陆用户名 
	 * @param password - 目标服务器登陆密码
	 * @param port - 目标服务器ssh2登陆端口
	 */
	public void connection(final String token, final String ip, final String username, final String password, final int port){
		JSch jsch = new JSch();
		
        Session session;
		try {
			//创建session并且打开连接，因为创建session之后要主动打开连接
			session = jsch.getSession(username, ip, port);
			session.setPassword(password);
	        session.connect();
	        ShellSessionHolder.getInstance().add(token, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
        	
	}
	
	/**
	 * 执行shell命令
	 * 
	 * @author hins 
	 * @date 2018年8月16日  
	 * @param token - 请求者的token
	 * @param command - 命令字符串
	 */
	public void execute(final String token, final String command){
		
		if(StringUtils.isBlank(command)){
			return;
		}
		
        try {
        	//打开通道，设置通道类型，和执行的命令
        	Session session = ShellSessionHolder.getInstance().get(token);
            Channel channel = session.openChannel("exec");
    		ChannelExec channelExec = (ChannelExec)channel;
            channelExec.setCommand(command);
            channelExec.setInputStream(null);
        	BufferedReader input = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));
			channelExec.connect();
			
			 //接收远程服务器执行命令的结果
            String line;
            while ((line = input.readLine()) != null) {  
                stdout.add(line);  
            }  
            
            input.close();  
            
            // 得到returnCode
            if (channelExec.isClosed()) {  
                channelExec.getExitStatus();  
            }  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取shell命令执行后的结果
	 * 
	 * @author hins 
	 * @date 2018年8月16日  
	 * @return
	 */
	public ArrayList<String> getStdOut() {
		return stdout;
	}

	public static void main(String[] args) {
		ShellHandler handler = new ShellHandler();
		
	}
	
}
