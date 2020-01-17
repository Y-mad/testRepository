package com.cy.pj.jtsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.jtsys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {
	int getRowCount(@Param("username") String username);
	List<SysUserDeptVo> findPageObjects(@Param("username")String  username,
																			@Param("startIndex")Integer startIndex,
																			@Param("pageSize")Integer pageSize);
	int validById(@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	int insertObject(SysUser entity);
	SysUserDeptVo findObjectById(Integer id);
	int updateObject(SysUser entity);
	int updatePassword(
			@Param("password")String password,
			@Param("salt")String salt,
			@Param("id")Integer id);
	@Select("select count(*) from sys_users where ${column}=#{value}")
	int isExist(@Param("column")String column,@Param("value")String value);
}
