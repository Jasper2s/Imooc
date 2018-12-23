package com.springmvc.dao;

import java.util.List;

import com.springmvc.vo.StudentVO;

//@Repository("iStudentDao")//这个注解可以不加
/**
 * 注意：Mybatis的Dao层不需要实例化
 * 
 * @author qiuzhiwen
 *
 */
public interface IStudentDao {
	
	public List<StudentVO> getStudent();
	
	public void addStudent(StudentVO vo);

}
