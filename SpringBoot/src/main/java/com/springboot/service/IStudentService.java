package com.springboot.service;

import java.util.List;

import com.springboot.pojo.Student;

public interface IStudentService {
	
	public void save(Student student);
	
	public void update(Student student);
	
	public void delete(String id);
	
	public Student getStudent(String id);
	
	public List<Student> getAllStudent();
	
	public List<Student> getStudetList(Student student);
	
	public List<Student> queryStuentListPage(Student student,Integer pageIndex,Integer pageSize);
	
	public Student getStudnetById(String id);

}
