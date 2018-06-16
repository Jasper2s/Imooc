package com.jms.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jms.service.ProducerService;

public class ProducerController {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//加载xml配置文件
		ApplicationContext context=new ClassPathXmlApplicationContext("producer.xml");
		//获取bean对象
		ProducerService producerService=context.getBean(ProducerService.class);
		for(int i=10;i<20;i++){
			//发送消息
			producerService.sendMessage("test"+i);
		}
	}

}
