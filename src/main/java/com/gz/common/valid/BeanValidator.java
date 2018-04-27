package com.gz.common.valid;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gz.common.exception.ParamException;

/**
 * <br/>功能: bean对象验证
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月23日
 * <br/>修改日期: 2018年4月23日
 * <br/>修改列表:
 */
public class BeanValidator extends GenericValidator{

	private static final Logger logger = LoggerFactory.getLogger(BeanValidator.class);
	
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	public void validate(ProceedingJoinPoint pjp) {
		Object[] objs = pjp.getArgs();
		for(int i = 0;i < objs.length;i++) {
			validator(objs[i],new Class<?>[0]);
		}
	}
	
	/**
	 * @Description: 验证器
	 * @param object 验证对象
	 * @param groups    
	 */
	protected <T> void validator(T object, Class<?>... groups) {
		Map<String,Object> errorMaps = new HashMap<String,Object>();
		
		Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(object, groups);
		if(CollectionUtils.isNotEmpty(constraintViolationSet)) {
			for(Iterator<ConstraintViolation<T>> iterator = constraintViolationSet.iterator(); iterator.hasNext();) {
				ConstraintViolation<T> error = iterator.next();
				errorMaps.put(((PathImpl)error.getPropertyPath()).getLeafNode().getName(), error.getMessage());
			}
		}
		
		if(MapUtils.isNotEmpty(errorMaps)) {
			logger.debug("【validator】：参数校验结果"+errorMaps);
			throw new ParamException(errorHandler(errorMaps));
		}
	}
}
