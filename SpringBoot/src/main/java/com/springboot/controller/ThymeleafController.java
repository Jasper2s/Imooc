package com.springboot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.pojo.Resource;
import com.springboot.pojo.User;

@Controller
@RequestMapping("thy")
public class ThymeleafController {
	
	@Autowired
	private Resource resource;
	
	@RequestMapping("center")
	public String getCenter(){
		return "thymeleaf/center/center";
	}
	
	@RequestMapping("index")
	public String getIndex(ModelMap model){
		//model.addAttribute("resource", resource);
		
		User user=new User();
		user.setName("Jasper");
		user.setAge(20);
		user.setBirthday(new Date());
		model.addAttribute("user", user);
		return "thymeleaf/index";
	}
	
	@RequestMapping("postform")
	public String postForm(User user){
		System.out.println(user.getName());
		return "redirect:index";
	}
	
	@RequestMapping("userlist")
	public String getUserList(ModelMap model){
		User u1=new User();
		u1.setName("Jasper1");
		u1.setAge(21);
		u1.setBirthday(new Date());
		
		User u2=new User();
		u2.setName("Jasper2");
		u2.setAge(22);
		u2.setBirthday(new Date());
		
		List<User> list=new ArrayList<User>();
		list.add(u1);
		list.add(u2);
		model.addAttribute("userList", list);
		return "thymeleaf/index";
		
	}

}
