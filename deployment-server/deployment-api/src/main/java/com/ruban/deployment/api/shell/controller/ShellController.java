package com.ruban.deployment.api.shell.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruban.deployment.api.shell.common.RestResult;
import com.ruban.deployment.api.shell.utils.ShellHandler;

/**
 * shell命令相关的接口
 * 
 * @author hins
 * @date 2018年8月16日
 */
@Controller
@RequestMapping("/admin/shell/")
public class ShellController {

	
	/**
	 * 创建linux服务器的ssh通道连接<br>
	 * 方法不会校验ip、用户信息是否正确，如果因为输入参数不合法，执行会抛出exception
	 * 
	 * @author hins 
	 * @date 2018年8月16日  
	 * @param ip - 目标服务器ip
	 * @param username - 目标服务器登陆用户名 
	 * @param password - 目标服务器登陆密码
	 * @param port - 目标服务器ssh2登陆端口(可空)
	 * @return 连接会话的id
	 */
	@RequestMapping(value = "connection.do", method = RequestMethod.POST)
	@ResponseBody
	public RestResult<String> connection(String ip, String username, String fileUrl, Integer port) {

		String sessionId = UUID.randomUUID().toString().replaceAll("-", "");
		ShellHandler handler = new ShellHandler();
		if (null == port) {
			handler.connectionByRsa(sessionId, ip, username, fileUrl);
		} else {
			handler.connectionByRsa(sessionId, ip, username, fileUrl, port);
		}
		return new RestResult<String>(sessionId);
	}
	
	/**
	 * 去目标服务器执行shell命令<br>
	 * 方法不会校验命令是否合法，sessionId是否存在，如果因为输入参数不合法，执行会抛出exception
	 * 
	 * @author hins 
	 * @date 2018年8月16日  
	 * @param sessionId - 创建的连接会话id
	 * @param command - 命令
	 * @return
	 */
	@RequestMapping(value = "execute.do", method = RequestMethod.POST)
	@ResponseBody
	public RestResult<List<String>> execute(String sessionId, String command) {
		ShellHandler handler = new ShellHandler();
		handler.execute(sessionId, command);
		
		List<String> stdout = handler.getStdOut();
		return new RestResult<List<String>>(stdout);
		
	}

}
