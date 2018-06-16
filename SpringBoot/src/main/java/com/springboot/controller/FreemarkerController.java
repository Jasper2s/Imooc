package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pojo.Resource;

//@RestController
/**
	这里请不要使用@RestController,不然返回前端的只是一串字符串。
	@RestController注解表示返回的内容都是HTTP Content不会被模版引擎处理，
	它默认为该类中的所有的方法都添加了@ResponseBody
 * @author qiuzhiwen
 */

@Controller
@RequestMapping("/ftl")
public class FreemarkerController {
	
	@Autowired
	private Resource resource;
	
	@RequestMapping("/center")
	public String getCenter(){
		return "freemarker/center/center";
	}
	
	@RequestMapping("/index")
	public String getIndex(ModelMap model){
		model.addAttribute("resource", resource);
		return "freemarker/index";
	}

}
