package com.gz.dao.example;

import java.util.List;

import com.gz.beans.po.example.Student;

/**
 * <br/>功能:
 * <br/>版本: 1.0
 * <br/>开发人员: gz
 * <br/>创建日期: 2018年1月21日
 * <br/>修改日期: 2018年1月21日
 * <br/>修改列表:
 */
public interface StudentDao {

	List<Student> findStudent(String id);
}
