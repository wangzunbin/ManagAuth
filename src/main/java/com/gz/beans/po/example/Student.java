package com.gz.beans.po.example;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Student {


	@Length(max=10,message="id应该在0-10之内")
	private String id;
	@NotBlank(message="name不能为空")
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", cards=" 
				+ "]";
	}
}
