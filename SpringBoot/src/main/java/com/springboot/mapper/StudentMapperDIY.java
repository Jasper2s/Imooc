package com.springboot.mapper;

import java.util.List;

import com.springboot.pojo.Student;
import com.springboot.until.MyMapper;

public interface StudentMapperDIY extends MyMapper<Student> {
	
	//注意：此方法名和StudentMapperDIY.xml文件中的id="getStudnetById"对应
	public List<Student> getStudnetById(String id);
}