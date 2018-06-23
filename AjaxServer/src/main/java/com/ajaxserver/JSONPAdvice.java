package com.ajaxserver;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@SuppressWarnings("deprecation")
@ControllerAdvice
public class JSONPAdvice extends AbstractJsonpResponseBodyAdvice{

	public JSONPAdvice(){
		super("callback");
	}
}
