package com.gz.service.sys.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gz.beans.po.sys.SysUser;
import com.gz.beans.vo.sys.SysUserVo;
import com.gz.common.exception.ParamException;
import com.gz.dao.sys.SysUserDao;
import com.gz.service.sys.SysUserService;
import com.gz.utils.PasswordEncryption;

public class SysUserServiceImpl implements SysUserService{

	private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public void insertSysUser(SysUserVo sysUserVo) {
		
		// 数据校验
		checkSysUserName(sysUserVo.getUsername(),sysUserVo.getId());
		checkSysUserMail(sysUserVo.getMail(),sysUserVo.getId());
		checkSysUserTelephone(sysUserVo.getTelephone(),sysUserVo.getId());
		
		// 参数封装
		SysUser sysUser = new SysUser();
		try {
			BeanUtils.copyProperties(sysUser, sysUserVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("数据转换失败",e);
		}
		sysUser.setPassword(PasswordEncryption.encry(sysUserVo.getPassword()));
		sysUser.setOperator("Insert");// TODO
		sysUser.setOperateIp("127.0.0.1");// TODO
		sysUser.setOperateTime(new Date());
		sysUserDao.insert(sysUser);
	}
	
	@Override
	public void updateSysUser(SysUserVo sysUserVo) {
		
		// 数据校验
		checkSysUserName(sysUserVo.getUsername(),sysUserVo.getId());
		checkSysUserMail(sysUserVo.getMail(),sysUserVo.getId());
		checkSysUserTelephone(sysUserVo.getTelephone(),sysUserVo.getId());
		
		// 参数封装
		SysUser sysUser = new SysUser();
		try {
			BeanUtils.copyProperties(sysUser, sysUserVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("数据转换失败",e);
		}
		sysUser.setPassword(PasswordEncryption.encry(sysUserVo.getPassword()));
		sysUser.setOperator("UpdateUser");// TODO
		sysUser.setOperateIp("127.0.0.1");// TODO
		sysUser.setOperateTime(new Date());
		
		sysUserDao.updateByPrimaryKeySelective(sysUser);
	}

	/**
	 * @Description: 是否存在相同的电话号码
	 * @param telephone
	 * @param id    
	 */
	private void checkSysUserTelephone(String telephone, Integer id) {
		int num = sysUserDao.checkSysUserTelephone(telephone, id);
		if(num > 0) throw new ParamException("存在相同的手机号码");
	}

	/**
	 * @Description: 是否存在相同的Mail地址
	 * @param mail
	 * @param id    
	 */
	private void checkSysUserMail(String mail, Integer id) {
		int num = sysUserDao.checkSysUserMail(mail, id);
		if(num > 0) throw new ParamException("存在相同的邮箱");
	}

	/**
	 * @Description: 是否存在相同的用户名
	 * @param username
	 * @param id    
	 */
	private void checkSysUserName(String username, Integer id) {
		int num = sysUserDao.checkSysUserName(username, id);
		if(num > 0) throw new ParamException("存在相同的用户名");
	}

}
