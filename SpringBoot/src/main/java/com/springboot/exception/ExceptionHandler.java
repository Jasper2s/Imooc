package com.springboot.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.until.JSONResult;

//@ControllerAdvice
@RestControllerAdvice
public class ExceptionHandler {
	
	public static final String ERROR_VIEW="error";
	
	//当发生异常时，被该类捕获
	@org.springframework.web.bind.annotation.ExceptionHandler(value=Exception.class)
	public Object errorHandler(HttpServletRequest request,HttpServletResponse response,Exception e){
		//e.printStackTrace();
		if(isAjax(request)){
			System.out.println("*********");
			return JSONResult.errorException(e.getMessage());
		}else{
			ModelAndView mv=new ModelAndView();
			mv.addObject("exception", e);
			mv.addObject("url", request.getRequestURL());
			mv.setViewName(ERROR_VIEW);
			return mv;
		}
		
	}
	
	/**
	 * 判断请求是否为ajax请求
	 */
	public static boolean isAjax(HttpServletRequest request){
		return(request.getHeader("X-Requested-With")!=null
				&&"XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}

}
