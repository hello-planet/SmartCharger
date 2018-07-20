package com.func.util;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;

import com.func.domain.ServerConfig;
import com.func.service.CommonManager;

/**
 * For initializing, recording, and recycling data.<br>
 * 
 * Singleton mode.
 * 
 * @author helloplanet
 * @create 2018.4.15
 */
public class ServerManager extends CommonManager{

	public ServerManager() {

	}

	public static ServerManager getServerManager() {
		return ServerMangerHolder.sm;
	}

	public void initialServerConfig() {
		ServerConfig sc = ServerConfig.getServerConfig();
		Document doc = FileManager.xmlParser("/conf/server-config.xml");
		
		//redis server
		NamedNodeMap redis_config = doc.getElementsByTagName("redis").item(0).getAttributes();
		sc.setREDIS_ADDR(redis_config.getNamedItem("redis_addr").getNodeValue());
		sc.setREDIS_PORT(redis_config.getNamedItem("port").getNodeValue());
		sc.setREDIS_PW(redis_config.getNamedItem("password").getNodeValue());
		sc.setTASK_LIST_NAME(redis_config.getNamedItem("task_list").getNodeValue());
		sc.setNODE_ADDR_LIST_NAME(redis_config.getNamedItem("node_addr").getNodeValue());
		
		//mysql server
		NamedNodeMap mysql_config = doc.getElementsByTagName("mysql").item(0).getAttributes();
		sc.setMYSQL_ADDR(mysql_config.getNamedItem("mysql_addr").getNodeValue());
		sc.setMYSQL_PORT(mysql_config.getNamedItem("port").getNodeValue());
		sc.setMYSQL_USR(mysql_config.getNamedItem("user").getNodeValue());
		sc.setMYSQL_PW(mysql_config.getNamedItem("password").getNodeValue());
		
		//extra initialization
		//TODO log file path initializtion
				

	}
	
	public void initialNodeAddrList(){
		MysqlConnector mc = new MysqlConnector();
		List<String> nodeAddrList = mc.getNodeAddr();
		mc.release();
		RedisConnector rc = new RedisConnector();
		rc.addList(ServerConfig.NODE_ADDR_LIST_NAME, nodeAddrList);
		rc.release();
	}

	public void service() {
		
	}

	private static class ServerMangerHolder {
		private static ServerManager sm = new ServerManager();
	}

}
