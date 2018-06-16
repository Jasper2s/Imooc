package com.java.base;

import java.util.UUID;

import org.junit.Test;

public class TestDemo {
	
	@Test
	public void test(){
		System.out.println("qiuzhiwen");
	}
	
	@Test
	public void test2(){
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}

}
