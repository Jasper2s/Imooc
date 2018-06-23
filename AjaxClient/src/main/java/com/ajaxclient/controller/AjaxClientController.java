package com.ajaxclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ajaxclient")
public class AjaxClientController {
	
	@RequestMapping("/index")
	public String getIndex(){
		return "index";
	}

}
