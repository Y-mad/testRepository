package com.cy.pj.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cy.pj.common.vo.JsonResult;

@RestControllerAdvice//@RestControllerAdvice=@ControllerAdvice+   @ResponseBody
//controller抛出的异常会给Despatchservlet,
//然后Despatchservlet会找到含有@RestControllerAdvice注解注释的类然后让这个类对异常进行处理
public class GlobalExceptionHandler {
	//JDK中的自带的日志API
		@ExceptionHandler(RuntimeException.class)
		public JsonResult doHandleRuntimeException(
				RuntimeException e){
	    	e.printStackTrace();//也可以写日志
			return new JsonResult(e);//封装异常信息
		}
}
