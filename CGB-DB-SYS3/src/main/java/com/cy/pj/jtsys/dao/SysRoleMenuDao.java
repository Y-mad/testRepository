package com.cy.pj.jtsys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysRoleMenuDao {
	@Delete("delete from sys_role_menus where menu_id=#{menuid};")
		 int deleteObjectsByMenuId(Integer menuid);
	@Delete("delete from sys_role_menus where role_id=#{roleid};")
		int deleteObjectsByRoleId(Integer roleid);
	int insertObjects(@Param("roleId")Integer roleId,@Param("menuIds")Integer[] menuIds);
}
