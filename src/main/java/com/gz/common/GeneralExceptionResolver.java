package com.gz.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.common.exception.ParamException;

/**
 * <br/>功能: 统一异常处理
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月22日
 * <br/>修改日期: 2018年4月22日
 * <br/>修改列表:
 */
@ControllerAdvice
public class GeneralExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(GeneralExceptionResolver.class);
	
	@ExceptionHandler(ParamException.class)
	@ResponseBody 
	public ResponseRel paramException(ParamException re) {
		logger.error("参数处理异常:{}",re.getMessage());
		return ResponseRel.fail(re.getMessage());
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody 
	public ResponseRel runtimeException(RuntimeException re) {
		logger.error("运行处理异常:{}",re.getMessage());
		return ResponseRel.fail(re.getMessage());
	}
}
