package com.cy.pj.common.aspect;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.formula.functions.Now;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class SysExceptionAspect {
	@AfterThrowing(pointcut="bean(*ServiceImpl)",throwing = "e")
	public void doHandleException(JoinPoint jp,Exception e) {
		MethodSignature ms=(MethodSignature)jp.getSignature();
		log.error("{}exception msg is {}",ms.getName(),e.getMessage());
		try {
			 Date date1 = new Date() ;  //获取系统时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日--HH:mm:ss"); //日期格式
				String date = sdf.format(date1);
			PrintStream ps = new PrintStream(new FileOutputStream("F:\\exception.txt",true),true);//可以在application配置中logging配置不需要创造流
			ps.println(date+ms.getName()+e.getMessage());
			ps.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
		} 
	}		
	
	
}
