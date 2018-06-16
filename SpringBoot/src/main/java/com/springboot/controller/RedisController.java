package com.springboot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pojo.Student;
import com.springboot.until.JSONResult;
import com.springboot.until.JsonUtils;
import com.springboot.until.RedisOperator;

@RestController
@RequestMapping("redis")
public class RedisController {
	
	@Autowired
	private StringRedisTemplate strRedis;
	
	@Autowired
	private RedisOperator redis;
	
	@RequestMapping("test")
	public JSONResult test(){
		//strRedis.opsForValue().set("jasper", "Jasper");
		Student s=new Student();
		s.setName("Jasper");
		s.setAge(20);
		s.setBirthday(new Date());
		strRedis.opsForValue().set("json:studnet",JsonUtils.objectToJson(s));
		Student jsonStu=JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:studnet"), Student.class);
		return JSONResult.ok(jsonStu);
	}
	
	@RequestMapping("test2")
	public JSONResult test2(){
		Student s1=new Student();
		s1.setName("Jasper1");
		s1.setAge(21);
		s1.setBirthday(new Date());
		
		Student s2=new Student();
		s2.setName("Jasper1");
		s2.setAge(21);
		s2.setBirthday(new Date());
		
		List<Student> list=new ArrayList<Student>();
		list.add(s1);
		list.add(s2);
		
		redis.set("json:student:studentlist", JsonUtils.objectToJson(list), 2000);
		List<Student> stuList=JsonUtils.jsonToList(redis.get("json:student:studentlist"), Student.class);
		return JSONResult.ok(stuList);
	}

}
