package com.springboot.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pojo.User;
import com.springboot.until.JSONResult;

//@Controller
@RestController
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/getUser")
	//@ResponseBody
	public User getUser(){
		User user=new User();
		user.setName("Jasper");
		user.setPassword("123");
		user.setAge(20);
		user.setBirthday(new Date());
		user.setDesc(null);
		return user;
		
	}
	
	@RequestMapping("/getUserJson")
	//@ResponseBody
	public JSONResult getUserJson(){
		User user=new User();
		user.setName("Jasper");
		user.setPassword("123");
		user.setAge(20);
		user.setBirthday(new Date());
		user.setDesc(null);
		return JSONResult.ok(user);
	}
}
