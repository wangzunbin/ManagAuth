package com.gz.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gz.beans.po.sys.SysUser;
import com.gz.common.ResponseRel;
import com.gz.common.exception.ParamException;
import com.gz.service.sys.LoginService;

/**
 * <br/>功能: 用户登录
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月25日
 * <br/>修改日期: 2018年4月25日
 * <br/>修改列表:
 */
@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping("/login")
	public ResponseRel loginParamValid(
			@Length(min = 1, max = 15, message = "用户长度应该在1-15之间")String userName,
			@Length(max = 30, message = "密码长度应该小于30")String password,
			HttpServletRequest request) {
		
		if(StringUtils.isBlank(userName)) {
			throw new ParamException("登录用户名为空");
		}
		if(StringUtils.isBlank(password)) {
			throw new ParamException("登录密码为空");
		}
		
		SysUser sysUser = loginService.checkLogin(userName, password);
		request.getSession().setAttribute("sysUser", sysUser);
		return ResponseRel.success("登录成功");
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redict:login";
	}
}
