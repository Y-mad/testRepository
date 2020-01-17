package com.cy.pj.common.config;

import java.util.concurrent.Executor;

import org.springframework.scheduling.annotation.AsyncConfigurer;

public class SpringAsyncConfig implements AsyncConfigurer {
		@Override
		public Executor getAsyncExecutor() {
			// TODO Auto-generated method stub
			return AsyncConfigurer.super.getAsyncExecutor();
		}
		
}
