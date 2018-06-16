package com.springboot.task;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncTasks {
	
	@Async
	public Future<Boolean> doTask1() throws Exception{
		long start=System.currentTimeMillis();
		Thread.sleep(1000);
		long end=System.currentTimeMillis();
		System.out.println("Task1耗时："+(end-start)+"毫秒！");
		return new AsyncResult<Boolean>(true);
	}
	
	@Async
	public Future<Boolean> doTask2() throws Exception{
		long start=System.currentTimeMillis();
		Thread.sleep(700);
		long end=System.currentTimeMillis();
		System.out.println("Task2耗时："+(end-start)+"毫秒！");
		return new AsyncResult<Boolean>(true);
	}
	
	@Async
	public Future<Boolean> doTask3() throws Exception{
		long start=System.currentTimeMillis();
		Thread.sleep(600);
		long end=System.currentTimeMillis();
		System.out.println("Task3耗时："+(end-start)+"毫秒！");
		return new AsyncResult<Boolean>(true);
	}

}
