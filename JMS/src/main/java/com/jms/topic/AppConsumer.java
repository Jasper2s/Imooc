package com.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppConsumer {
	
	private static final String URL="tcp://127.0.0.1:61616";
	private static final String TOPIC_NAME="topic_test";
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory=null;
		Connection connection=null;
		try {
			//1.创建ConnectionFactory
			connectionFactory=new ActiveMQConnectionFactory(URL);
			//2.创建Connection
			connection=connectionFactory.createConnection();
			//3.启动Connection
			connection.start();
			//4.创建Session
			Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5.创建目标Destination
			Destination destination=session.createTopic(TOPIC_NAME);
			//6.创建消费者MessageConsumer
			MessageConsumer messageConsumer=session.createConsumer(destination);
			//7.创建消息监听器MessageListener
			messageConsumer.setMessageListener(new MessageListener() {
				
				public void onMessage(Message message) {
					//8.接收消息
					TextMessage textMessage=(TextMessage) message;
					try {
						System.out.println("接收消息"+textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(connection!=null){
				try {
					//9.关闭连接
					//connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
