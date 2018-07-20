package com.func.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.func.domain.ServerConfig;
import com.func.service.DataConnector;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * For interaction with REDIS server.<br>
 * For trans/node_addr.<br>
 * 
 * RedisConnector()-->getJedis()/methods-->release()
 * 
 * @author helloplanet
 * @create 2018.4.11
 *
 */
public class RedisConnector extends DataConnector {
	private static JedisPool jedispool = null;
	private Jedis jedis;

	/**
	 * Default initialized config parameters<br>
	 * 
	 * testWhileIdle = true<br>
	 * minEvictableIdleTimeMillis = 60000<br>
	 * setTimeBetweenEvictionRunsMillis = 30000<br>
	 * setNumTestsPerEvictionRun = -1<br>
	 */
	// TODO jedispoolconfig need to be more detailed
	public RedisConnector() {
		if (jedispool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			// config.setMaxTotal(500);
			// config.setMaxIdle(100);
			// config.setMaxWaitMillis(10000);
			// config.setTestOnBorrow(true);
			// config.setTestOnReturn(true);
			jedispool = new JedisPool(config, ServerConfig.REDIS_ADDR, new Integer(ServerConfig.REDIS_PORT));
		}
		this.connect();
	}

	@Override
	public void connect() {
		this.jedis = jedispool.getResource();
		this.jedis.auth(ServerConfig.REDIS_PW);
	}

	@Override
	public void service() {

	}

	@Override
	public void release() {
		this.jedis.close();
	}

	/**
	 * Common use in adding trans, task<br>
	 */
	public boolean addHash(String key, HashMap<String, String> items) {
		Set<String> fields = items.keySet();
		for (String field : fields) {
			this.jedis.hsetnx(key, field, items.get(field).toString());
		}
		return true;
	}

	/**
	 * Common use in adding node_list<br>
	 */
	public boolean addList(String key, List<String> values) {
		for (String value : values) {
			this.jedis.lpush(key, value);
		}
		return true;
	}

	public List<String> getList(String key) {
		List<String> list = new ArrayList<String>();
		long length = this.jedis.llen(key);
		for (int i = 0; i < length; i++) {
			list.add(this.jedis.lindex(key, i));
		}
		return list;
	}

	/**
	 * When other methods of jedis needed
	 */
	public Jedis getJedis() {
		return this.jedis;
	}
}