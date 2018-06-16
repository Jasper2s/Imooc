package com.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 生产者
 * @author qiuzhiwen
 *
 */
public class AppProvider {
	
	private static final String URL="tcp://127.0.0.1:61616";
	private static final String QUEUE_NAME="queue_test";

	public static void main(String[] args){
		ConnectionFactory connectionFactory=null;
		Connection conn=null;
		try {
			//1.创建ConnectionFactory
			connectionFactory=new ActiveMQConnectionFactory(URL);
			//2.创建Connection
			conn=connectionFactory.createConnection();
			//3.启动连接
			conn.start();
			//4.创建Session
			Session session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5.创建目标Destination
			Destination destination=session.createQueue(QUEUE_NAME);
			//6.创建生产者MessageProducer
			MessageProducer producer=session.createProducer(destination);
			
			for(int i=0;i<9;i++){
				//7.创建消息
				TextMessage message=session.createTextMessage("test11"+i);
				//8.发送消息
				producer.send(message);
				System.out.println("发送消息："+message.getText());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} finally{
			if (conn!=null) {
				try {
					//9.关闭连接
					conn.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
