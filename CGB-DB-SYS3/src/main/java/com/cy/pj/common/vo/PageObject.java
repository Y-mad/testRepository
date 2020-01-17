package com.cy.pj.common.vo;

import java.io.Serializable;
import java.util.List;

import com.cy.pj.jtsys.entity.SysLog;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Administrator
 *
 */
@Data
@NoArgsConstructor
public class PageObject<T> implements Serializable {
	private static final long serialVersionUID = 4382188489836324755L;
	/**当前页的页码值*/
	private Integer pageCurrent=2;
    /**页面大小*/
    private Integer pageSize=3;
    /**总行数(通过查询获得)*/
    private Integer rowCount=0;
    /**总页数(通过计算获得)*/
    private Integer pageCount=0;
    /**当前页记录*/
    private List<T> records;
	public PageObject(Integer pageCurrent, Integer pageSize, Integer rowCount, List<T> records) {
		super();
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.records = records;
		this.pageCount = (rowCount-1)/pageSize+1;
	}
}
