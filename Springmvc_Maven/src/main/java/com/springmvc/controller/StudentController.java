package com.springmvc.controller;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.service.IStudentService;
import com.springmvc.util.JSONResult;
import com.springmvc.vo.StudentVO;
/**
 * 学生控制器
 * @author qiuzhiwen
 *
 */
@Controller
@RequestMapping("/student")
public class StudentController {
	
	//日志
	private static Logger log=Logger.getLogger(StudentController.class);
	
	@Autowired
	private IStudentService studentService;
	
	@RequestMapping("/test")
	@ResponseBody
	public JSONResult test(Model model) {
		log.info("StudentController-->test");
		model.addAttribute("test", "HelloWorld!");
		return JSONResult.ok("test");
	}
	
	@RequestMapping("/getStudent")
	@ResponseBody
	public List<StudentVO> getStudent(){
		return studentService.getStudent();
	}
	
	@RequestMapping("/addStudent")
	@ResponseBody
	public JSONResult addStudent() {
		StudentVO vo=new StudentVO();
		vo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		vo.setName("Jasper");
		vo.setAge(22);
		vo.setScore(75.5f);
		try {
			studentService.addStudent(vo);
			log.info("添加学生成功！");
			return JSONResult.ok("添加学生成功！");
		} catch (Exception e) {
			log.error("添加学生失败！");
			return JSONResult.errorException("添加学生失败！");
		}
	}

}
