package com.gz.beans.vo.sys;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.gz.beans.po.sys.SysDept;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <br/>功能: 部门视图类
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月22日
 * <br/>修改日期: 2018年4月22日
 * <br/>修改列表:
 */
@SuppressWarnings("unused")
public class SysDeptVo{

	private Integer id;
	
	@NotBlank(message = "部门名称不能为空")
	@Length(max = 15, min = 2, message = "部门名称长度必须在2-15之间")
    private String name;
	
	private Integer parentId=0;
    
    @NotNull
    private Integer seq;
    
    @Length(max = 150, message = "部门备注长度必须在0-150之间")
    private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SysDeptVo [id=" + id + ", name=" + name + ", parentId="
				+ parentId + ", seq=" + seq + ", remark=" + remark + "]";
	}
}
