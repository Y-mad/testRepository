package com.cy.pj.jtsys.service;

import java.util.Map;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.jtsys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

public interface SysUserService {
	PageObject<SysUserDeptVo>findPageObjects(String username,Integer pageCurrent);
	int  validById(Integer id,Integer valid,String modifiedUser);
	int saveObject(SysUser entity,Integer[]roleIds);
	Map<String,Object> findObjectById(Integer userId) ;
	int updateObject(SysUser entity,Integer[] roleIds);
	int updatePassword(String password,
	           String newPassword,
	           String cfgPassword);
	boolean isExist(String column,String value);
}
