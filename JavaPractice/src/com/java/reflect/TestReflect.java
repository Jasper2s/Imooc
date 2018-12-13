package com.java.reflect;

import java.lang.reflect.Field;

public class TestReflect {
	
	public static void main(String[] args) {
		try {
			Class<?> cls=Class.forName("com.java.reflect.Student");
			Student s=(Student) cls.newInstance();
			Field[] fs=cls.getDeclaredFields();
			for(Field f:fs) {
				f.setAccessible(true);//注意：否则会报错IllegalAccessException异常
				System.out.println("设置属性之前："+f.getName()+"==="+f.get(s));
				
				f.set(s, "Tom");
				System.out.println("设置属性之后："+f.getName()+"==="+f.get(s));
			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
