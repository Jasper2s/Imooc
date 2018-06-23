package com.ajaxserver.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

@WebFilter(urlPatterns="/ajaxserver/*")
public class CrossFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("CrossFilter");
		
		HttpServletResponse res=(HttpServletResponse) response;
		HttpServletRequest req=(HttpServletRequest) request;
		
		//满足不同域名的跨域请求
		String origin=req.getHeader("Origin");
		if(StringUtils.isEmpty(origin)){
			res.addHeader("Access-Control-Allow-Origin", origin);
		}
		//res.addHeader("Access-Control-Allow-Origin", "*");
		//注意：带Cookie请求的时候Access-Control-Allow-Origin必须是全匹配，不能使用*号
		//res.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		
		//res.addHeader("Access-Control-Allow-Methods", "*");
		res.addHeader("Access-Control-Allow-Methods", "*");
		
		//告诉浏览器在一个小时内可以缓存设置的头部信息
		//res.addHeader("Access-Control-Max-Age", "3600");
		
		//设置发送请求运行带Cookie
		res.addHeader("Access-Control-Allow-Credentials", "true");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
