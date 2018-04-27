package com.gz.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.beans.po.sys.SysUser;
import com.gz.common.exception.ParamException;
import com.gz.dao.sys.SysUserDao;
import com.gz.service.sys.LoginService;
import com.gz.utils.PasswordEncryption;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public SysUser checkLogin(String userName,String password) {
		SysUser sysUser = sysUserDao.getByUserName(userName);
		if(sysUser == null) {
			throw new ParamException("登录信息错误");
		}
		if(PasswordEncryption.encry(password).equals(sysUser.getPassword())) {
			throw new ParamException("登录信息错误");
		}
		return sysUser;
	}

}
