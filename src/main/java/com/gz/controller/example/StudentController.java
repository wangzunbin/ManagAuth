package com.gz.controller.example;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.beans.po.example.Student;
import com.gz.common.ResponseRel;
import com.gz.service.example.StudentService;

@Controller
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("student")
	@ResponseBody
	public ResponseRel updateStudent(Student student) {
		List<Student> students = studentService.findStudent(student.getId());
		logger.info("学生信息："+student);
		for(Student s : students) {
			logger.info("查询结果："+s);
		}
		return ResponseRel.success(ResponseRel.success("werr"));
	}
	
	@RequestMapping("student1")
	@ResponseBody
	public ResponseRel studentParamValid(@NotBlank(message="参数不能为空") String name) {
		return ResponseRel.success(ResponseRel.success("werr"));
	}
}
