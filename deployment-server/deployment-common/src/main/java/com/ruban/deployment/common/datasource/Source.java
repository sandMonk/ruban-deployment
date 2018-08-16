/**
 * 
 */
package com.ruban.deployment.common.datasource;

/**
 * 数据源描述 <br>
 * name必须在datasource配置中存在
 * 
 */
public enum Source {

	MASTER("master") // 主数据库
	, SLAVE1("slave1") // 从库1
	// , SLAVE2("slave2") // 从库2
	;

	private String name;

	private Source(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
