package com.gz.common.valid;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.executable.ExecutableValidator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gz.common.exception.ParamException;

/**
 * @Description: 方法参数验证
 * @author 弓振
 * @date 2018年2月24日
 */
public class ParamValidator extends GenericValidator{

	private static final Logger logger = LoggerFactory.getLogger(ParamValidator.class);
	
	private final ExecutableValidator executableValidator = Validation.buildDefaultValidatorFactory()
			.getValidator().forExecutables();
	
	public void validate(ProceedingJoinPoint pjp) {
		
		Map<String,Object> errorMaps = new HashMap<String,Object>();
		
		Object object = pjp.getTarget();// 目标对象
		MethodSignature methodSignature = ((MethodSignature) pjp.getSignature());// 方法签名
		Object[] parameterValues = pjp.getArgs();// 参数
		Set<ConstraintViolation<Object>> constraintViolations = validator(object, 
																				methodSignature.getMethod(), 
																				parameterValues);
		if(CollectionUtils.isNotEmpty(constraintViolations)) {
			Iterator<ConstraintViolation<Object>> constraintViolationIterator = constraintViolations.iterator();
			while(constraintViolationIterator.hasNext()) {
				ConstraintViolation<Object> constraintViolation = constraintViolationIterator.next();
				errorMaps.put(getMethodParamName(methodSignature, constraintViolation), constraintViolation.getMessage());
			}
			if(MapUtils.isNotEmpty(errorMaps)) {
				logger.debug("【validator】：参数校验结果"+errorMaps);
				throw new ParamException(errorHandler(errorMaps));
			}
		} 
	}
	
	protected <T> Set<ConstraintViolation<T>> validator(T object,
			   Method method,
			   Object[] parameterValues,
			   Class<?>... groups) {
		Set<ConstraintViolation<T>> set = executableValidator.validateParameters(object, 
																			method, 
																			parameterValues, 
																			groups);
		return set;
	}
	protected <T> Set<ConstraintViolation<T>> validator(T object,
			   Method method,
			   Object[] parameterValues) {
		return validator(object, method, parameterValues, new Class<?>[0]);										
	}
	
	/**
	 * 获得方法参数名称
	 * @param methodSignature
	 * @param constraintViolation
	 * @return
	 */
	private static String getMethodParamName(MethodSignature methodSignature,
			ConstraintViolation<Object> constraintViolation) {
		
		String[] parameterNames = methodSignature.getParameterNames();
		PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();  // 获得校验的参数路径信息
        int paramIndex = pathImpl.getLeafNode().getParameterIndex(); // 获得校验的参数位置
        
        if(parameterNames == null || parameterNames.length == 0 || parameterNames.length <= paramIndex) {
        	return "";
        } else {
        	return parameterNames[paramIndex];
        }
	}
	
}
