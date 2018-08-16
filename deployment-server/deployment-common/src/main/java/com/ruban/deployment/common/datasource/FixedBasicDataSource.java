package com.ruban.deployment.common.datasource;
//package com.bunga.commons.datasource;
//
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import org.apache.commons.dbcp.BasicDataSource;
//
//public class FixedBasicDataSource extends BasicDataSource {
//
//	public int getNumActive() {
//		return super.connectionPool.getNumActive();
//	}
//
//	public int getNumIdel() {
//		return super.connectionPool.getNumIdle();
//	}
//
//	@Override
//	public synchronized void close() throws SQLException {
//		DriverManager.deregisterDriver(DriverManager.getDriver(url));
//		super.close();
//	}
//
//}
