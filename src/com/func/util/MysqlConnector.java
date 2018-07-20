package com.func.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.func.domain.ServerConfig;
import com.func.domain.UserEntity;
import com.func.service.DataConnector;

/**
 * For interaction with MYSQL server. <br>
 * For trans/application/admin.<br>
 * 
 * @author helloplanet
 * @create 2018.4.8
 *
 */
public class MysqlConnector extends DataConnector {

	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;

	public MysqlConnector() {
		this.connect();
	}

	@Override
	public void connect() {
		String url = "jdbc:mysql://" + ServerConfig.MYSQL_ADDR + ":" + ServerConfig.MYSQL_PORT + "/charger";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, ServerConfig.MYSQL_USR, ServerConfig.MYSQL_PW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void service() {

	}

	@Override
	public void release() {
		if (this.resultSet != null) {
			try {
				this.resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (this.pstmt != null) {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * QUERY
	 * 
	 * @return list [ map1( field_1:value_1, fields_2:value_2,... ),
	 *         map2(),...]<br>
	 */
	public List<Map<String, Object>> queryData(String sql, List<?> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		this.pstmt = this.connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		this.resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = this.resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (this.resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = this.resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * UPDATE
	 * 
	 * @return flag(boolean) updated successfully or not
	 */
	public boolean updateData(String sql, List<?> params) throws SQLException {
		boolean flag = false;
		this.pstmt = this.connection.prepareStatement(sql);
		int result = -1;
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				this.pstmt.setObject(index++, params.get(i));
			}
		}
		result = this.pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;
	}

	/**
	 * read addressed of nodes in the pool
	 * 
	 * @return list &lt;String &gt; of addresses
	 */
	// TODO packaged method or primitive method in ServerManager ???
	public List<String> getNodeAddr() {
		List<String> nodeAddr = new ArrayList<String>();
		String sql = "SELECT * FROM pool";
		try {
			List<Map<String, Object>> rawNodeAddr = queryData(sql, null);
			for (Map<String, Object> oneNodeAddr : rawNodeAddr) {
				nodeAddr.add(oneNodeAddr.get("node_addr").toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nodeAddr;
	}

	// TODO packaged method or primitive method in UserManager ???
	public boolean writeUser(UserEntity usr) {
		String sql = "INSERT INTO usr (usr_id,usr_pw,owner_flg) VALUES (?,?,?)";
		boolean flag = false;
		try {
			flag = updateData(sql, usr.toListForm());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
