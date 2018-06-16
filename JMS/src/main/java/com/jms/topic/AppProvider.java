package com.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppProvider {
	
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
			//3.启动连接
			connection.start();
			//4.创建Session
			Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5.创建Destination
			Destination destination=session.createTopic(TOPIC_NAME);
			//6.创建生产者MessageProducer
			MessageProducer messageProducer=session.createProducer(destination);
			
			for(int i=0;i<10;i++){
				//7.创建消息TextMessage
				TextMessage textMessage=session.createTextMessage("topic-test"+i);
				//8.发送消息
				messageProducer.send(textMessage);
				System.out.println("发送消息："+textMessage.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(connection!=null){
				try {
					//9.关闭连接
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
