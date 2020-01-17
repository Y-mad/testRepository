package com.cy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.common.config.PaginationProperties;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.jtsys.controller.SysLogController;
import com.cy.pj.jtsys.dao.SysLogDao;
import com.cy.pj.jtsys.entity.SysLog;

@SpringBootTest
public class SysTests {
	@Autowired
	private SysLogDao dao;
	@Autowired
	private SysLogController controller;
	@Autowired
	private PaginationProperties pp;
	@Test
	public void test() {
		int c =dao.getRowCount("bbb");
		System.out.println(c);
	}
	@Test
	public void testable() {
		Integer i = pp.getPageSize();
		System.out.println(i);
	}
	@Test
	public void testFind() {
		List<SysLog> list = dao.findPageObjects("admin", 20, 5);
		for (SysLog sysLog : list) {
			System.out.println(sysLog);
		}
	}
	@Test
	public void testsDelete() {
			int rows = dao.deleteObjects(2,3,5);
			System.out.println(rows);
	}
	@Test
	public void testsDeleteController() {
		JsonResult d = controller.deleteObjects(36);
		System.out.println(d);
	}
}
