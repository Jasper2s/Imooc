package com.springboot.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.springboot.interceptor.OneInterceptor;
import com.springboot.interceptor.TwoInterceptor;

//配置拦截器
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Interceptor调用顺序
		registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");
		//TwoInterceptor拦截器拦截两个路径/two/**和/one/**
		registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**")
													 .addPathPatterns("/one/**");
		
		super.addInterceptors(registry);
	}

}
