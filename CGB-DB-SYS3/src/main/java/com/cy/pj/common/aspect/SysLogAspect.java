package com.cy.pj.common.aspect;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.jtsys.entity.SysLog;
import com.cy.pj.jtsys.service.SysLogService;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class SysLogAspect {
		@Pointcut("bean(sysUserServiceImpl)")
		public void logPointCut() {}
		@Around("logPointCut()")
		public Object around(ProceedingJoinPoint jp) throws Throwable{
			try {
				long start = System.currentTimeMillis();
				log.info("start:"+start);
				Object result =jp.proceed();
				long stop = System.currentTimeMillis();
				log.info("after:"+stop);
				writeLog(jp, stop-start);
				return result;
			} catch (Throwable e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		@Autowired
		private SysLogService sysLogService;
		public void writeLog(ProceedingJoinPoint jp,Long time) {
			SysLog entity = new SysLog();
			Object[] args = jp.getArgs();
			MethodSignature s =(MethodSignature)jp.getSignature();
			// s.getMethod()这样获取方法对象只能在CGlib代理时获得,jdk代理时获得的是接口中的方法而非实现类的方法
			String operation = "operation";
			RequiredLog annotation = s.getMethod().getAnnotation(RequiredLog.class);
			if(annotation!=null) {
			 operation = annotation.operation();
			}
			entity.setTime(time);
			entity.setIp("12");
			entity.setParams(Arrays.toString(jp.getArgs()));
			entity.setMethod(jp.getTarget().getClass().getName()+"."+s.getName());
			entity.setCreatedTime(new Date());
			entity.setOperation(operation);
			entity.setUsername("ysl");
			sysLogService.saveObject(entity);
		}
}
