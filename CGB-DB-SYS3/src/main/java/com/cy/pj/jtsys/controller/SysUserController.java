package com.cy.pj.jtsys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.jtsys.entity.SysUser;
import com.cy.pj.jtsys.service.SysUserService;

@RestController
@RequestMapping("/user/")
public class SysUserController {
	@Autowired
	private 	SysUserService sysUserService;
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
				 String username,Integer pageCurrent) {
			 return new JsonResult(
				sysUserService.findPageObjects(username,
						pageCurrent));
		 }
	@RequestMapping("doValidById")
	public JsonResult doValidById(Integer id,Integer valid){
			        sysUserService.validById(
					id,valid, "admin");//"admin"用户将来是登陆用户
			return new JsonResult("update ok");
	}
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(
			SysUser entity,
			Integer[] roleIds){
		sysUserService.saveObject(entity,roleIds);
		return new JsonResult("save ok");
	}
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(
			Integer id){
		Map<String,Object> map=
		sysUserService.findObjectById(id);
		return new JsonResult(map);
	}
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(
	    SysUser entity,Integer[] roleIds){
		sysUserService.updateObject(entity,roleIds);
		return new JsonResult("update ok");
	}
	@RequestMapping("doUpdatePassword")
	public JsonResult doUpdatePassword(
				 String pwd,
				 String newPwd,
				 String cfgPwd) {
			 sysUserService.updatePassword(pwd, newPwd, cfgPwd);
			 return new JsonResult("update ok");
	}
	@RequestMapping("isExist")
	public JsonResult doExist(String column,String value) {
		return new JsonResult(sysUserService.isExist(column, value));
	}
}
