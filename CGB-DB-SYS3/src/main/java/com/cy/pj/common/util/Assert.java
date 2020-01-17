package com.cy.pj.common.util;

import com.cy.pj.common.exception.ServiceException;

public class Assert {
		public static void isEmpty(String content,String message) {
			if (content==null||"".equals(content)) {
				throw new IllegalArgumentException(message);
			}
		}
		public static void isArgumentValid(boolean statement,String message) {
			if(statement) {
				throw new IllegalArgumentException(message);
			}
		}
		public static void isServiceValid(boolean statement,String message) {
			if (statement) {
				throw new ServiceException(message);
			}
		}
		
}
