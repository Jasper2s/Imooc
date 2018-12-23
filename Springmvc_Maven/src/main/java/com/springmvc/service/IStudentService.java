package com.springmvc.service;

import java.util.List;

import com.springmvc.vo.StudentVO;

public interface IStudentService {
	
	public List<StudentVO> getStudent();
	
	public void addStudent(StudentVO vo);

}
