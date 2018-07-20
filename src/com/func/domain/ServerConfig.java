package com.func.domain;

/**
 * Server information<br>
 * Initialized when the application started<br>
 * Singleton mode<br>
 * 
 * @author helloplanet
 * @create 2018.4.8
 *
 */
public class ServerConfig {

	// Database
	public static String REDIS_ADDR = "localhost";
	public static String MYSQL_ADDR = "localhost";
	public static String REDIS_PORT = "6379";
	public static String MYSQL_PORT = "3306";
	public static String REDIS_PW;
	public static String MYSQL_PW;
	public static String MYSQL_USR;
	public static String TASK_LIST_NAME = "task_list";
	public static String NODE_ADDR_LIST_NAME = "node_addr_list";

	// User

	// Server
	public static String LogFolderPath = "/log/";

	private ServerConfig() {
	}

	public static ServerConfig getServerConfig() {
		return ServerConfigHolder.sc;
	}

	public void setTASK_LIST_NAME(String tASK_LIST_NAME) {
		TASK_LIST_NAME = tASK_LIST_NAME;
	}

	public void setNODE_ADDR_LIST_NAME(String nODE_ADDR_LIST_NAME) {
		NODE_ADDR_LIST_NAME = nODE_ADDR_LIST_NAME;
	}

	public void setREDIS_PW(String rEDIS_PW) {
		REDIS_PW = rEDIS_PW;
	}

	public void setMYSQL_USR(String mYSQL_USR) {
		MYSQL_USR = mYSQL_USR;
	}

	public void setMYSQL_PW(String mYSQL_PW) {
		MYSQL_PW = mYSQL_PW;
	}

	public void setREDIS_ADDR(String rEDIS_ADDR) {
		REDIS_ADDR = rEDIS_ADDR;
	}

	public void setMYSQL_ADDR(String mYSQL_ADDR) {
		MYSQL_ADDR = mYSQL_ADDR;
	}

	public void setREDIS_PORT(String rEDIS_PORT) {
		REDIS_PORT = rEDIS_PORT;
	}

	public void setMYSQL_PORT(String mYSQL_PORT) {
		MYSQL_PORT = mYSQL_PORT;
	}

	public static void setLogFolderPath(String logFolderPath) {
		LogFolderPath = logFolderPath;
	}

	private static class ServerConfigHolder {
		private static ServerConfig sc = new ServerConfig();
	}

}
