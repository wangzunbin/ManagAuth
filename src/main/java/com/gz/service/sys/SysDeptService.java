package com.gz.service.sys;

import java.util.List;

import com.gz.beans.dto.SysDeptDto;
import com.gz.beans.vo.sys.SysDeptVo;

/**
 * <br/>功能: 部门业务层
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月24日
 * <br/>修改日期: 2018年4月24日
 * <br/>修改列表:
 */
public interface SysDeptService {

	void insertSysDept(SysDeptVo sysDeptVo);
	
	void updateSysDept(SysDeptVo sysDeptVo);
	
	List<SysDeptDto> findSysDeptDtoTree();
}
