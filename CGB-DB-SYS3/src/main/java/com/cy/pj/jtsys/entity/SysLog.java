package com.cy.pj.jtsys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 * pojo(普通java对象)
 * po(持久化对象):能在层与层之间传递与表中字段有映射关系
 * vo(值对象-value object) 用于封装业务数据(可能在层与层之间传递,一般和表中字段没有一一对应的关系)
 * @author Administrator
 *
 */
@Data
public class SysLog implements Serializable{
	//实现了序列化接口的类,对象序列化的时候才不会报错
	//(如果序列化后改变对象(类)的结构(如属性)则反序列化报错),此时就需要加入serialVersionUID
	//此ID没写也(序列化时)会自动生成,只是不写则无显式声明,反序列化时有可能报异常
	private static final long serialVersionUID = -5303594466358678178L;
	private Long id;
	private String username;
	private String operation;
	private String method;
	private String params;
	private Long time;
	private String ip;
	private Date createdTime;
}
