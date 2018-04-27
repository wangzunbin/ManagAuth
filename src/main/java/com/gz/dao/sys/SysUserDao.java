package com.gz.dao.sys;

import org.apache.ibatis.annotations.Param;

import com.gz.beans.po.sys.SysUser;

public interface SysUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    int checkSysUserMail(@Param("mail")String mail,Integer id);
    
    int checkSysUserTelephone(@Param("telephone")String telephone,Integer id);
    
    int checkSysUserName(@Param("username")String username,Integer id);
    
    SysUser getByUserName(String userName);
}