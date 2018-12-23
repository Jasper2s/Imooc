package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.IStudentDao;
import com.springmvc.service.IStudentService;
import com.springmvc.vo.StudentVO;
/**
 * 学生业务处理类
 * @author qiuzhiwen
 *
 */
@Service("studentService")
public class StudentServiceImpl implements IStudentService{
	
	@Autowired
    private IStudentDao iStudentDao;
    
    
    public List<StudentVO> getStudent() {
  	  	return iStudentDao.getStudent();
    }


    @Transactional //事务一致性
	public void addStudent(StudentVO vo) {
		iStudentDao.addStudent(vo);
	}

}
