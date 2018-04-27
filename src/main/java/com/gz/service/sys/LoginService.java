package com.gz.service.sys;

import com.gz.beans.po.sys.SysUser;

/**
 * <br/>功能: 登录业务层
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月25日
 * <br/>修改日期: 2018年4月25日
 * <br/>修改列表:
 */
public interface LoginService {

	SysUser checkLogin(String userName,String password);
}
