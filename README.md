 1. 统一异常处理

	 com.gz.common.exception.GeneralExceptionResolver
	
 2. 统一参数封装
 
     com.gz.common.ResponseRel
    
 3. controller统一参数验证
 
	 com.gz.common.valid.BeanValidator
	 
	 com.gz.common.valid.ParamValidator
	 
	 com.gz.common.interceptor.ParamValidAspect
	 
> bean对象参数验证：
> 验证com.gz.controller及其子包中以update开头和insert开头的方法	
> 验证com.gz.controller及其子包中以beanValid结尾的方法
> 多个单一参数：
> 验证com.gz.controller及其子包中以paramValid结尾的方法

   