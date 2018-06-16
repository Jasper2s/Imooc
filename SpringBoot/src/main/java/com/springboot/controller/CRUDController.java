package com.springboot.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pojo.Student;
import com.springboot.service.IStudentService;
import com.springboot.until.JSONResult;

@RestController
@RequestMapping("crud")
public class CRUDController {
	
	@Autowired
	private IStudentService iStudnetService;
	
	@RequestMapping("insert")
	public void insertStudent(){
		Student student=new Student();
		student.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		student.setName("QQQQQQQQQ");
		student.setAge(20);
		student.setGrade(Float.valueOf("100"));
		student.setBirthday(new Date());
		iStudnetService.save(student);
		
	}
	
	@RequestMapping("delete")
	public void deleteStudent(){
		iStudnetService.delete("e23e578ac25a4440a08ea9e5a7ecaa0a");
	}
	
	@RequestMapping("update")
	public void updateStudent(){
		Student student=new Student();
		student.setId("0d60304509964bb49f6483cd977144ab");
		student.setName("Qiuzhiwen");
		iStudnetService.update(student);
	}
	
	@RequestMapping("getall")
	public JSONResult getAllStudent(){
		List<Student> list=iStudnetService.getAllStudent();
		return JSONResult.ok(list);
	}
	
	@RequestMapping("getstudnet")
	public JSONResult getStudent(){
		Student s=iStudnetService.getStudent("6b354c4bf6544ce4b6f174a45339e1e6");
		return JSONResult.ok(s);
	}
	
	@RequestMapping("getstudentlist")
	public JSONResult getStudentList(){
		Student student=new Student();
		student.setName("Jasper");
		List<Student> list=iStudnetService.getStudetList(student);
		return JSONResult.ok(list);
	}
	
	//分页
	@RequestMapping("page")
	public JSONResult getStudnetListPage(Integer pageIndex){
		if(pageIndex==null){
			pageIndex=1;
		}
		int pageSize=5;
		Student student=new Student();
		student.setName("Jasper");
		List<Student> stuList=iStudnetService.queryStuentListPage(student, pageIndex, pageSize);
		return JSONResult.ok(stuList);
	}
	
	//自定义mapper文件
	@RequestMapping("diymapper")
	public JSONResult getStudentById(){
		Student s=iStudnetService.getStudnetById("0d60304509964bb49f6483cd977144ab");
		return JSONResult.ok(s);
	}

}
