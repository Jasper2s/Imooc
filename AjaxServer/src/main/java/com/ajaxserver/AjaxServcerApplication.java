package com.ajaxserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan//扫描组件
public class AjaxServcerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AjaxServcerApplication.class, args);
	}
	
}
