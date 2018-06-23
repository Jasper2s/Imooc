package com.ajaxserver.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajaxserver.utils.JSONResult;
import com.ajaxserver.vo.UserVO;

@RestController
@RequestMapping("/ajaxserver")
//@CrossOrigin//表示该类中所有的方法都支持跨域，也可以加在方法上
public class AjaxServerController {
	
	/**
	 * 注意：由于在Ajax请求时要求响应数据必须时JSON格式
	 * 所以在返回数据时都需要转为JSON格式
	 * @return
	 */
	@RequestMapping("hello")
	public JSONResult getString(){
		System.out.println("AjaxServerController-->getString");
		return JSONResult.ok("Hello world!");
	}
	
	@RequestMapping("getuser")
	public JSONResult getUser(UserVO userVO){
		System.out.println("AjaxServerController-->getUser");
		System.out.println(userVO.getName());
		return JSONResult.ok(userVO);
	}
	
	@RequestMapping("getcookie")
	public JSONResult getCookie(@CookieValue(value="cookie")String cookie){
		System.out.println("AjaxServerController-->getCookie");
		System.out.println(cookie);
		return JSONResult.ok(cookie);
	}

}
