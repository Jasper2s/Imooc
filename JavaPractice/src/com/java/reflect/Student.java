package com.java.reflect;

public class Student {
	
	private String name="Jasper";
	private String name2="KangKang";
	
	public Student() {
		System.out.println("The constructor has no params...");
	}
	
	public Student(String name) {
		System.out.println("The constructor has params with "+name);
	}
	
	public String getName() {
		return "getNameValue";
	}

}
