package com.gz.controller.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gz.beans.dto.SysDeptDto;
import com.gz.beans.vo.sys.SysDeptVo;
import com.gz.common.ResponseRel;
import com.gz.service.sys.SysDeptService;

/**
 * <br/>功能: 部门controller
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月24日
 * <br/>修改日期: 2018年4月24日
 * <br/>修改列表:
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {
	
	@Autowired
	private SysDeptService sysDeptService;

	@RequestMapping("/insert")
	public ResponseRel insertSysDept(SysDeptVo sysDeptVo) {
		sysDeptService.insertSysDept(sysDeptVo);
		return ResponseRel.success("新增成功");
	}
	
	@RequestMapping("/update")
	public ResponseRel updateSysDept(SysDeptVo sysDeptVo) {
		sysDeptService.updateSysDept(sysDeptVo);
		return ResponseRel.success("修改成功");
	}
	
	@RequestMapping("/find")
	public ResponseRel findSysDept() {
		List<SysDeptDto> sysDeptDtoTree = sysDeptService.findSysDeptDtoTree();
		return ResponseRel.success(sysDeptDtoTree);
	}
}
