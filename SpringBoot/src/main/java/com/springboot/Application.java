package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描mapper包路径,注意MapperScan导包是import tk.mybatis.spring.annotation.MapperScan;
@MapperScan(basePackages="com.springboot.mapper")
//扫描所有需要的包，包含一些自用的工具类包所在的路径
@ComponentScan(basePackages={"com.springboot"})
//开启定时任务
@EnableScheduling
//开启异步调用任务
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
