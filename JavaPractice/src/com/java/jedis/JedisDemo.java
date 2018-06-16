package com.java.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {
	
	/**
	 * 单实例测试
	 */
	@Test
	public void test1(){
		//1.设置IP和端口
		Jedis jedis=new Jedis("192.168.2.119", 6379);
		//2.保存数据
		jedis.set("name", "Jasper");
		//3.获取数据
		String value=jedis.get("name");
		System.out.println(value);
		System.out.println(jedis.get("foo"));
		//4.释放资源
		jedis.close();
	}
	
	/**
	 * 连接池方式连接
	 */
	@SuppressWarnings("resource")
	@Test
	public void test2(){
		//创建连接池配置对象
		JedisPoolConfig config=new JedisPoolConfig();
		//设置最大连接数
		config.setMaxTotal(30);
		//设置最大空闲连接数
		config.setMaxIdle(10);
		//获得连接池
		JedisPool jedisPool=new JedisPool(config, "192.168.2.119", 6379);
		//获得核心对象
		Jedis jedis=null;
		try {
			//通过连接池获取连接
			jedis=jedisPool.getResource();
			//保存数据
			jedis.set("age", "100");
			//获取数据
			System.out.println(jedis.get("age"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//关闭资源
			jedis.close();
		}
	}

}
