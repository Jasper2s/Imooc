package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.springboot.mapper.StudentMapper;
import com.springboot.mapper.StudentMapperDIY;
import com.springboot.pojo.Student;
import com.springboot.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{
	
	@Autowired
	private StudentMapper stuMapper;
	
	@Autowired
	private StudentMapperDIY studentMapperDIY;

	//SpringBoot事务处理机制--增/删/改
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Student student) {
		stuMapper.insert(student);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Student student) {
		stuMapper.updateByPrimaryKeySelective(student);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(String id) {
		stuMapper.deleteByPrimaryKey(id);
	}

	//SpringBoot事务处理机制--查
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Student getStudent(String id) {
		return stuMapper.selectByPrimaryKey(id);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Student> getStudetList(Student student) {
		return stuMapper.select(student);
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Student> getAllStudent() {
		return stuMapper.selectAll();
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Student> queryStuentListPage(Student student,
			Integer pageIndex, Integer pageSize) {
		//开始分页
		PageHelper.startPage(pageIndex, pageSize);
		Example example=new Example(Student.class);
		Example.Criteria criteria=example.createCriteria();
		if(!StringUtils.isEmptyOrWhitespace(student.getName())){
			criteria.andLike("name", "%"+student.getName()+"%");
		}
		//排序
		example.orderBy("age").desc();
		return stuMapper.selectByExample(example);
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Student getStudnetById(String id) {
		List<Student> list=studentMapperDIY.getStudnetById(id);
		Student student=null;
		if(list!=null&&list.size()>0){
			student=list.get(0);
		}
		return student;
	}

}
