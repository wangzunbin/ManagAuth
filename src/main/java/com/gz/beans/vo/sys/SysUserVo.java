package com.gz.beans.vo.sys;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

/**
 * <br/>功能: 系统用户视图类
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月25日
 * <br/>修改日期: 2018年4月25日
 * <br/>修改列表:
 */
public class SysUserVo {

	private Integer id;
	
	@NotBlank(message = "用户名不能为空")
	@Length(min = 1, max = 15, message = "用户长度应该在1-15之间")
	private String username;
	
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1[0-9]{10}$",message = "手机格式不正确")
	private String telephone;
	
	@NotBlank(message = "邮箱不能为空")
	@Email(message = "邮箱格式不正确")
	private String mail;
	
	@Length(max = 30, message = "密码长度应该小于30")
	private String password;
	
	@NotNull(message = "部门Id不能为空")
	private Integer deptId;
	
	@NotNull(message = "用户状态不能为空")
	@Min(value = 1, message = "状态格式不正确")
	@Max(value = 2, message = "状态格式不正确")
	private Integer status;
	
	@Length(max = 150,message = "备注信息长度应该在0-150之间")
	private String remark = "";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SysUserVo [id=" + id + ", username=" + username
				+ ", telephone=" + telephone + ", mail=" + mail + ", password="
				+ password + ", deptId=" + deptId + ", status=" + status
				+ ", remark=" + remark + "]";
	}

}
