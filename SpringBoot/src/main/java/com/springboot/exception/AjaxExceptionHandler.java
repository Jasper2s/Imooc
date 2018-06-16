package com.springboot.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.until.JSONResult;

//@RestControllerAdvice
public class AjaxExceptionHandler {
	
	//@ExceptionHandler(value=Exception.class)
	public JSONResult ajaxExceptionHandler(HttpServletRequest request,Exception exception){
		//exception.printStackTrace();
		System.out.println("0000000000");
		return JSONResult.errorException(exception.getMessage());
	}

}
