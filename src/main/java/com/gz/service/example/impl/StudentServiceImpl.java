package com.gz.service.example.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.beans.po.example.Student;
import com.gz.dao.example.StudentDao;
import com.gz.service.example.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao studentDao;
	@Override
	public List<Student> findStudent(String id) {
		return studentDao.findStudent(id);
	}

}
