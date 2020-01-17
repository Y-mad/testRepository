package com.cy.pj.jtsys.service;

import java.util.List;
import java.util.Map;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.Node;
import com.cy.pj.jtsys.entity.SysMenu;

public interface SysMenuService {
	List<Map<String,Object>> findObjects();
	int deleteObject(Integer id);
	List<Node> findZtreeMenuNodes();
	int saveObject(SysMenu entity);
	int updateObject(SysMenu entity);
}
