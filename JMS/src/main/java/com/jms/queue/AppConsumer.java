package com.jms.queue;

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
/**
 * 消费者
 * @author qiuzhiwen
 *
 */
public class AppConsumer {
	
	private static final String URL="tcp://127.0.0.1:61616";
	private static final String QUEUE_NAME="queue_test";
	
	
	public static void main(String[] args) throws JMSException{
		ConnectionFactory connectionFactory=null;
		Connection conn=null;
		try {
			//1.创建ActiveMQConnectionFactory对象
			connectionFactory=new ActiveMQConnectionFactory(URL);
			//2.创建Connection对象
			conn=connectionFactory.createConnection();
			//3.启动连接
			conn.start();
			//4.创建Session对象
			Session session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5.创建Destination
			Destination destination=session.createQueue(QUEUE_NAME);
			//6.创建MessageConsumer对象
			MessageConsumer consumer=session.createConsumer(destination);
			//7.创建MessageListener
			consumer.setMessageListener(new MessageListener() {
				//8.接收消息
				public void onMessage(Message message) {
					TextMessage textMessage=(TextMessage) message;
					try {
						System.out.println("接收消息："+textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//9.关闭连接
			//注意：接收消息是一个异步过程
			if(conn!=null){
				//conn.close();
			}
		}
	}

}
