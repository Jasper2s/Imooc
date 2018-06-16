package com.jms.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.jms.service.ProducerService;

public class ProducerServiceImpl implements ProducerService{

	@Autowired
	private JmsTemplate jmsTemplate;
	
	//@Resource(name="queueDetination")//指定队列模式目的地
	@Resource(name="topicDetination")//指定主题模式目的地
	private Destination destination;
	
	public void sendMessage(final String message) {
		//使用JmsTemplate发送消息
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage=session.createTextMessage(message);
				return textMessage;
			}
		});
		System.out.println("发送消息："+message);
	}

}
