 ## ManagAuth
### 环境搭建
```
eclipse
```
### 工程启动步骤

```
tomcat
```
### 技术选型
```
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
```
## 目录结构
	ManagAuth-----------------------------项目名
	├─com.gz.beans------------------------实体
	    ├─com.gz.beans.dto----------------传输实体
	    ├─com.gz.beans.po-----------------持久化实体
	    ├─com.gz.beans.vo-----------------视图实体
	├─com.gz.common-----------------------通用类
	    ├─com.gz.common.constant----------常量类
	    ├─com.gz.common.exception---------异常类
	    ├─com.gz.common.interceptor-------拦截器
	    ├─com.gz.common.valid-------------参数校验
	├─com.gz.controller-------------------controller层
	├─com.gz.dao--------------------------持久化层
	├─com.gz.util-------------------------工具类
	├─com.gz.service----------------------业务层 
	
### 资料
