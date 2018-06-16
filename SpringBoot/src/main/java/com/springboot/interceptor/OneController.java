package com.springboot.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//注意：使用拦截器时，要使用@Controller+@ResponseBody形式
//@RestController
@Controller
@RequestMapping("one")
public class OneController {
	
	@RequestMapping("index")
	@ResponseBody
	public String index(){
		return "Hello OneController";
	}

}
