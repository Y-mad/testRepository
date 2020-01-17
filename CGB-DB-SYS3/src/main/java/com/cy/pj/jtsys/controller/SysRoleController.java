package com.cy.pj.jtsys.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.jtsys.entity.SysRole;
import com.cy.pj.jtsys.service.SysRoleService;

@RestController
@RequestMapping("/role/")
public class SysRoleController {
		@Autowired
		private SysRoleService sysRoleService;
		@RequestMapping("doFindPageObjects")
		public JsonResult doFindPageObjects(String name,Integer pageCurrent) {
				 return new JsonResult(sysRoleService.findPageObjects(name,pageCurrent));
			 }
		@RequestMapping("doDeleteObject")
		public JsonResult doDeleteObject(Integer id){
				  sysRoleService.deleteObject(id);
				  return new JsonResult("delete ok");
		}
		@RequestMapping("doSaveObject")
		public JsonResult doSaveObject(SysRole entity,Integer[] menuIds) {
			sysRoleService.saveObject(entity, menuIds);
			return new JsonResult("save ok");
		}
		@RequestMapping("doUpdateObject")
		public JsonResult doUpdateObject(SysRole entity,
		Integer[] menuIds){
		/*
		 * Integer[] arr = new Integer[menuIds.length()]; for (int i = 0; i <
		 * menuIds.length(); i++) { arr[i] = Integer.parseInt(menuIds.substring(i, i +
		 * 1));//substring是找出包含起始位置，不包含结束位置，到结束位置的前一位的子串 }
		 */
				  sysRoleService.updateObject(entity,menuIds);
		 return new JsonResult("update ok");
		}
		@RequestMapping("doFindObjectById")
		 public JsonResult doFindObjectById(Integer id){
		    	return new JsonResult(sysRoleService.findObjectById(id));
		 }
		 @RequestMapping("doFindRoles")
		 public JsonResult doFindRoles() {
			 return new JsonResult(sysRoleService.findObjects());
		 }
}
