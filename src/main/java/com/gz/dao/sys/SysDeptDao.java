package com.gz.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gz.beans.po.sys.SysDept;

public interface SysDeptDao {
    int deleteByPrimaryKey(Integer id);
    
    int insertSelective(SysDept record);

    int updateByIdSelective(SysDept record);

    List<SysDept> findSysDept();
    
    List<SysDept> findSysDeptChildByLevel(String level);
    
    SysDept selectById(Integer id);
    
    int checkSysDeptName(@Param("id") Integer id, @Param("name")String name, @Param("parentId")Integer parentId);
    
}