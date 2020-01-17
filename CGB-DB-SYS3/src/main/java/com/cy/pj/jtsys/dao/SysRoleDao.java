package com.cy.pj.jtsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.jtsys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {
		int getRowCount(@Param("name") String name);
		List<SysRole> findPageObjects(@Param("name")String  name,
			      @Param("startIndex")Integer startIndex,
			      @Param("pageSize")Integer pageSize);
		@Delete("delete from sys_roles where id=#{roleid};")
		int deleteObject(Integer roleid);
		int insertObject(SysRole entity);
		int updateObject(SysRole entity);
		SysRoleMenuVo findObjectById(Integer id);
		List<CheckBox> findObjects();
}
