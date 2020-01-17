package com.cy.pj.jtsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleDao {
	@Delete("delete from sys_user_roles where role_id=#{roleid};")
		int deleteObjectsByRoleid(Integer roleid);
	int insertObjects(@Param("userId")Integer userId,@Param("roleIds")Integer[] roleIds);
	List<Integer> findRoleIdsByUserId(Integer id);
	int deleteObjectsByUserId(Integer userId);
}
