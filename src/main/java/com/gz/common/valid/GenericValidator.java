package com.gz.common.valid;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;

import com.alibaba.fastjson.JSONObject;


/**
 * <br/>功能: 参数验证抽象类
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月23日
 * <br/>修改日期: 2018年4月23日
 * <br/>修改列表:
 */
public abstract class GenericValidator {

	public abstract void validate(ProceedingJoinPoint pjp);
	
	protected  String errorHandler(Map<String,Object> errors) {
		return new JSONObject(errors).toString();
	}
}
