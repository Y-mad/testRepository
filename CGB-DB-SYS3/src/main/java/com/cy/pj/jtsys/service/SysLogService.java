package com.cy.pj.jtsys.service;


import com.cy.pj.common.vo.PageObject;
import com.cy.pj.jtsys.entity.SysLog;

public interface SysLogService {
	PageObject<SysLog> findPageObjects(String username,Integer pageCurrent);
	int deleteObjects(Integer ...ids);
	void saveObject(SysLog entity);
}
