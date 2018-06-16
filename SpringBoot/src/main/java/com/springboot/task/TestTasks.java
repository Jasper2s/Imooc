package com.springboot.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTasks {
	
	//每隔三秒执行任务
	//@Scheduled(fixedRate=3000)
	//cron表达式，在线生成地址http://cron.qqe2.com/
	//从第4秒开始到50秒，每隔1秒打印一次
	//@Scheduled(cron="4-50 * * * * ? ")
	public void reportCurrentTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(new Date()));
	}

}
