package com.gz.common.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.gz.common.ResponseRel;
import com.gz.common.valid.BeanValidator;
import com.gz.common.valid.GenericValidator;
import com.gz.common.valid.ParamValidator;

/**
 * <br/>功能: 参数校验切面
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月22日
 * <br/>修改日期: 2018年4月22日
 * <br/>修改列表:
 */
@Component
@Aspect
public class ParamValidAspect {

	/*验证com.gz.controller及其子包中以update开头和insert开头的方法*/
	/*验证com.gz.controller及其子包中以BeanValid结尾的方法*/
	@Pointcut("execution(* com.gz.controller..*.insert* (..)) || execution(* com.gz.controller..*.update* (..)) || execution(* com.gz.controller..*.*BeanValid(..))")
	public void beanValidatorPointcut(){}
	
	/*验证com.gz.controller及其子包中以ParamValid结尾的方法*/
	@Pointcut("execution(* com.gz.controller..*.*ParamValid(..))")
	public void paramValidatorPointcut(){}
	
	@Around("beanValidatorPointcut()")
	public ResponseRel beanValidatorAdvice(ProceedingJoinPoint pjp) throws Throwable {
		GenericValidator validator = new BeanValidator();
		validator.validate(pjp);
		Object[] args = pjp.getArgs();
		Object result = pjp.proceed(args);
		return (ResponseRel)result;
	}
	
	@Around("paramValidatorPointcut()")
	public ResponseRel paramValidatorPointcut(ProceedingJoinPoint pjp) throws Throwable {
		GenericValidator validator = new ParamValidator();
		validator.validate(pjp);
		Object[] args = pjp.getArgs();
		Object result = pjp.proceed(args);
		return (ResponseRel)result;
	}
}
