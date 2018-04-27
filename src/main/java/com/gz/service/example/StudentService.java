package com.gz.service.example;

import java.util.List;

import com.gz.beans.po.example.Student;

public interface StudentService {

	List<Student> findStudent(String id);
}
