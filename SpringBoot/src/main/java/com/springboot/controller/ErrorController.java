package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.until.JSONResult;

//@RestController
@Controller
@RequestMapping("err")
public class ErrorController {
	
	@RequestMapping("error")
	public String getError(){
		int i=1/0;
		return "error";
	}
	
	@RequestMapping("ajaxerror")
	public String ajaxError(){
		return "ajaxerror";
	}
	
	@RequestMapping("getAjaxerror")
	public JSONResult getAjaxError(){
		int i=1/0;
		return JSONResult.ok();
	}

}
