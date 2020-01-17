package com.cy.pj.jtsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.jtsys.entity.SysLog;
import com.cy.pj.jtsys.service.SysLogService;
/**
 * 这个Controller主要用来返回json串
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/log/")
public class SysLogController {
	@Autowired
	private SysLogService  sysLogService;
	@RequestMapping("findPageObjects")
	@ResponseBody
	public JsonResult findPageObjects(String username,Integer pageCurrent) {
		PageObject<SysLog> pageObject = sysLogService.findPageObjects(username,pageCurrent);
		JsonResult jsonResult = new JsonResult(pageObject);
		return jsonResult;//底层将此对象转化为JSON串时，会动用get方法获取值
		//return pageObject;
	}
	@RequestMapping("deleteObjects")
	@ResponseBody
	public JsonResult deleteObjects(Integer ...ids) {
		int row = sysLogService.deleteObjects(ids);
		JsonResult jsonResult = new JsonResult("成功删除"+row+"行");
		return jsonResult;
	}
}
