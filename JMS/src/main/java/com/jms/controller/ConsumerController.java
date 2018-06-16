package com.jms.controller;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class ConsumerController {
	
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("consumer.xml");
	}

}
