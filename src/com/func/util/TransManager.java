package com.func.util;

import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.func.domain.ServerConfig;
import com.func.domain.TransEntity;
import com.func.service.CommonManager;

/**
 * Superclass of different trans modulers.
 * 
 * @author helloplanet
 * @create 2018.3.15
 */
public class TransManager extends CommonManager {

	// TODO transentity[] ????
	private TransEntity[] trans_list;
	private TransEntity trans;
	private RedisConnector rc;
	private MysqlConnector mc;

	public TransManager() {

	}

	public TransManager(HashMap<String, String> trans) {
		int transListLength = this.trans_list.length;
		this.trans_list[transListLength] = (new TransEntity(trans));
	}

	public TransManager(ArrayList<String> trans) {
		this.trans = new TransEntity(trans);
	}

	public void setTrans(HashMap<String, String> trans) {
		this.trans = new TransEntity(trans);
	}

	public void setTrans(ArrayList<String> trans) {
		this.trans = new TransEntity(trans);
	}

	/**
	 * invoked only when tasks involving redis data.
	 */
	public void initialRedisConnector() {
		this.rc = new RedisConnector();
	}

	public void initialMysqlConnector() {
		this.mc = new MysqlConnector();
	}

	/**
	 * return the jedis and mysql resource.<br>
	 * pairly-invoked with initial***Connector().
	 */
	public void realseDataConnector() {
		if (this.rc != null) {
			this.rc.release();
		}
		if (this.mc != null) {
			this.mc.release();
		}
	}

	/**
	 * Invoked during Act_I<br>
	 * To write trans from the http into REDIS.
	 */
	public boolean writeTransToRedis() {
		if (this.rc == null) {
			return false;
		}
		// write into trans
		HashMap<String, String> items = this.trans.toMapForm();
		items.remove("trans_id");
		this.rc.addHash(this.trans.getTransID(), items);
		// write into task_list
		this.rc.getJedis().hsetnx(ServerConfig.TASK_LIST_NAME, this.trans.getChargerID(), this.trans.getTransID());
		return true;
	}

	/**
	 * Invoked during Act_II<br>
	 * To write trans from REDIS into MYSQL server
	 */
	public boolean writeTransToMySQL() {
		boolean flag = false;
		if (this.mc == null) {
			return flag;
		}
		String sql = "INSERT INTO trans (trans_id,power_vol,cash_vol,charger_id,usr_id,date_time) VALUES (?,?,?,?,?,?)";
		try {
			flag = this.mc.updateData(sql, this.trans.toListForm());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Invoked during Act_I<br>
	 * To cast the trans to multi-nodes in the pool
	 */
	public void broadCast() throws Exception {
		// sample port
		int nodePort = 10000;
		// TODO socket multiplied-used or multiple objects ???
		for (int i = 0; i < this.rc.getJedis().llen(ServerConfig.NODE_ADDR_LIST_NAME); i++) {
			String host = this.rc.getJedis().lindex(ServerConfig.NODE_ADDR_LIST_NAME, i);
			Socket socket = new Socket(host, nodePort);
			OutputStream outputStream = socket.getOutputStream();
			String message = this.trans.toStringForm();
			socket.getOutputStream().write(message.getBytes("UTF-8"));
			outputStream.close();
			socket.close();
		}
	}

	/**
	 * Invoked during Act_IV<br>
	 * To delete the successfully-completed trans from REDIS
	 */
	public boolean deleteTrans(String trans_id, String charger_id) {
		boolean flag;
		if (this.rc == null) {
			flag = false;
		} else {
			// delete trans
			this.rc.getJedis().del(trans_id);
			// delete task
			this.rc.getJedis().hdel(ServerConfig.NODE_ADDR_LIST_NAME, charger_id);
			flag = true;
		}
		return flag;
	}
}