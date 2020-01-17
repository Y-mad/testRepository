package com.cy.pj.jtsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 这个Controller主要是用来返回页面
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/")
public class PageController {
		
		@RequestMapping("helloJtsys")
		public String helloJtsys() {
			return "starter";
		}
		@RequestMapping("log/log_list")
		public String doLogUI() {
			return "sys/log_list";
		}
		@RequestMapping("doPageUI")
		public String doPageUI() {
			return "common/page";
		}
		//rest表达式{}
		@RequestMapping("{module}/{moduleUI}")
		public String doModuleUI(@PathVariable String moduleUI) {//@PathVariable("moduleUI")
			return "sys/"+moduleUI;
		}
}
