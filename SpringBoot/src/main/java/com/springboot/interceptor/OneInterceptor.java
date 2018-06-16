package com.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class OneInterceptor implements HandlerInterceptor{

	/**
	 * 在请求处理之前调用(controller方法调用之前)
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("被OneInterceptor拦截，并放行........");
		//true表示放行  false表示不放行
		return true;
	}

	/**
	 * 在请求处理之后调用，但是在视图被渲染之前调用(Controller方法调用之后)
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 在整个请求结束之后被调用,也就是在DispatcherServlet渲染了对应的视图之后执行
	 * 主要用于进行资源清理工作
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
