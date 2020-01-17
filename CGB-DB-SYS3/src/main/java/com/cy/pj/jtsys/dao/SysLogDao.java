package com.cy.pj.jtsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.jtsys.entity.SysLog;

@Mapper
public interface SysLogDao {
	/**
	 * 基于条件查询总记录数
	 * @param username 查询条件(例如查询哪个用户的日志信息)
	 * @return 总记录数(基于这个结果可以计算总页数)
	 * 说明：假如如下方法没有使用注解修饰，在基于名字进行查询
	 * 时候会出现There is no getter for property named
	 * 'username' in 'class java.lang.String'
	 */
	//@Select("select count(*) from sys_logs")
		int getRowCount(@Param("username") String username);
		/**
		 * 基于条件分页查询日志信息
		 * @param username  查询条件(例如查询哪个用户的日志信息)
		 * @param startIndex 当前页的起始位置
		 * @param pageSize 当前页的页面大小
		 * @return 当前页的日志记录信息
		 * 数据库中每条日志信息封装到一个SysLog对象中
		 */
		void insert(SysLog entity);
		List<SysLog> findPageObjects(
				      @Param("username")String  username,
				      @Param("startIndex")Integer startIndex,
				      @Param("pageSize")Integer pageSize);
		int deleteObjects(@Param("ids")Integer ... ids);//这里必须加上@Param("ids")不然后面.xml文件中取的时候只能写array(因为不写默认为array)
}
