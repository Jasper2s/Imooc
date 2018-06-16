package com.springboot.controller;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.task.AsyncTasks;
/**
 * SpringBoot异步执行使用场景：
 * 发送短信/发送邮件/App消息推送/
 * @author qiuzhiwen
 *
 */
@RestController
@RequestMapping("asy")
public class AsyncTasksController {
	
	@Autowired
	private AsyncTasks asyTask;
	
	@RequestMapping("test")
	public String test() throws Exception{
		long start=System.currentTimeMillis();
		
		Future<Boolean> t1=asyTask.doTask1();
		Future<Boolean> t2=asyTask.doTask2();
		Future<Boolean> t3=asyTask.doTask3();
		
		while(!t1.isDone()||!t2.isDone()||!t3.isDone()){
			if(t1.isDone()&&t2.isDone()&&t3.isDone()){
				break;
			}
		}
		
		long end=System.currentTimeMillis();
		String s="任务总耗时:"+(end-start)+"毫秒！";
		System.out.println(s);
		return s;
	}

}
