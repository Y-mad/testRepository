package com.cy.pj.jtsys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.Node;
import com.cy.pj.jtsys.entity.SysMenu;

@Mapper
public interface SysMenuDao {
	List<Map<String,Object>> findObjects();
	@Select("select count(*) from sys_menus where parentId=#{id};")
	int getChildCount(Integer id);
	@Delete("delete from sys_menus where id=#{id};")
	int  deleteObject(Integer id);
	@Select("select id,name,parentId from sys_menus")
	List<Node> findZtreeMenuNodes();
	int insertObject(SysMenu entity);
	int updateObject(SysMenu entity);
}
