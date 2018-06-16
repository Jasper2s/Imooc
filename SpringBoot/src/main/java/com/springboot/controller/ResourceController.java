package com.springboot.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pojo.Resource;
import com.springboot.until.JSONResult;

@RestController
@RequestMapping("/resource")
public class ResourceController {
	
	@Autowired
	private Resource resource;
	
	@RequestMapping("/get")
	public Resource get(){
		return resource;
	}
	
	@RequestMapping("/getResource")
	public JSONResult getResource(){
		Resource bean=new Resource();
		BeanUtils.copyProperties(resource, bean);
		return JSONResult.ok(bean);
	}
	
}
