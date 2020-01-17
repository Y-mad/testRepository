package com.cy.pj.jtsys.service;


import java.util.List;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.jtsys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

public interface SysRoleService {
	PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);
	int deleteObject(Integer roleid);
	int saveObject(SysRole entity,Integer[] menuIds);
	int updateObject(SysRole entity,Integer[] menuIds);
	SysRoleMenuVo findObjectById(Integer id) ;
	List<CheckBox> findObjects();
}
