package com.cy.menu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.common.vo.Node;
import com.cy.pj.jtsys.dao.SysMenuDao;
import com.cy.pj.jtsys.entity.SysMenu;

@SpringBootTest
public class MenuTests {
		@Autowired
		private SysMenuDao dao;
		@Autowired
		private SysMenu sysMenu;
		@Test
		public void testDao() {
			//dao.findObjects().forEach((map)->System.out.println(map));//lambda表达式(jdk8的特性)
		/*
		 * int a = dao.getChildCount(8); System.out.println(a);
		 */
			//List<Node> list = dao.findZtreeMenuNodes();
			//System.out.println(list);
			int r = dao.insertObject(sysMenu);
			System.out.println(r);
		}
}
