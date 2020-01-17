package com.cy.pj.common.aspect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysCacheAspect {
	private Map<String, Object> cacheMap=new ConcurrentHashMap<>();
	@Around("@annotation(com.cy.pj.common.annotation.RequiredCache)")
	public Object arount(ProceedingJoinPoint jp) throws Throwable{
		Object obj = cacheMap.get("dept.findObjects");//从缓存中获取第一次运行findObjects时存入的数据
		if (obj!=null) {
			System.out.println(obj);
			return obj;
		}
		Object result = jp.proceed();
		cacheMap.put("dept.findObjects", result);//第一次运行存入数据到map中
		return result;
	}
	@AfterReturning("@annotation(com.cy.pj.common.annotation.CleanCache)")
	public void clean() {
		System.out.println("clean");
		cacheMap.clear();
	}
}
