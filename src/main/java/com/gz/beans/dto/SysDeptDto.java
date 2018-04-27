package com.gz.beans.dto;

import java.util.List;

import com.gz.beans.po.sys.SysDept;

/**
 * <br/>功能: 系统部门数据处理实体
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月24日
 * <br/>修改日期: 2018年4月24日
 * <br/>修改列表:
 */
public class SysDeptDto extends SysDept{

	private List<SysDeptDto> sysDepts;

	public List<SysDeptDto> getSysDepts() {
		return sysDepts;
	}

	public void setSysDepts(List<SysDeptDto> sysDepts) {
		this.sysDepts = sysDepts;
	}
	
	@Override
	public String toString() {
		return "SysDeptDto [sysDepts=" + sysDepts + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getParentId()="
				+ getParentId() + ", getLevel()=" + getLevel() + ", getSeq()="
				+ getSeq() + ", getRemark()=" + getRemark()
				+ ", getOperator()=" + getOperator() + ", getOperateTime()="
				+ getOperateTime() + ", getOperateIp()=" + getOperateIp()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
}
