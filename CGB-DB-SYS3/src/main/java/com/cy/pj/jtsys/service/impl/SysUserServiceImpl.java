package com.cy.pj.jtsys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.util.Assert;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.jtsys.dao.SysUserDao;
import com.cy.pj.jtsys.dao.SysUserRoleDao;
import com.cy.pj.jtsys.entity.SysUser;
import com.cy.pj.jtsys.service.SysUserService;
import com.cy.pj.sys.vo.SysUserDeptVo;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@Transactional
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
    @Transactional(readOnly = true)
	@RequiredLog(operation = "查找用户")
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		//1.对参数进行校验
				if(pageCurrent==null||pageCurrent<1)
				throw new IllegalArgumentException("当前页码值无效");
				//2.查询总记录数并进行校验
				int rowCount=sysUserDao.getRowCount(username);
				if(rowCount==0)
				throw new ServiceException("没有找到对应记录");
				//3.查询当前页记录
				int pageSize=2;
				int startIndex=(pageCurrent-1)*pageSize;
				List<SysUserDeptVo> records=
				sysUserDao.findPageObjects(username,
				startIndex, pageSize);
				//4.对查询结果进行封装并返回
				return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
	@RequiredLog(operation = "权限设置")
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		//校验
		Assert.isServiceValid(id==null||id<=0, "参数(id)不合法");
		Assert.isServiceValid(valid!=1&&valid!=0, "valie不合法");
		Assert.isEmpty(modifiedUser, "修改用户名不能为空");
		Assert.isServiceValid(valid!=1&&valid!=0, "valie不合法");
		int row = sysUserDao.validById(id, valid, modifiedUser);
		if (row==0) {
			throw new ServiceException("此记录不存在");
		}
		return row;
	}
	@RequiredLog(operation = "添加用户")
	@Override
    public int saveObject(SysUser entity, Integer[] roleIds) {
    	long start=System.currentTimeMillis();
    	log.info("start:"+start);
    	//1.参数校验
    	if(entity==null)
    		throw new ServiceException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getUsername()))
    		throw new ServiceException("用户名不能为空");
    	if(StringUtils.isEmpty(entity.getPassword()))
    		throw new ServiceException("密码不能为空");
    	if(roleIds==null || roleIds.length==0)
    		throw new ServiceException("至少要为用户分配角色");
    	//2.保存用户自身信息
        //2.1对密码进行加密
    	String source=entity.getPassword();
    	String salt=UUID.randomUUID().toString();
    	SimpleHash sh=new SimpleHash(//Shiro框架
    			"MD5",//algorithmName 算法
    			 source,//原密码
    			 salt, //盐值
    			 1);//hashIterations表示加密次数
    	entity.setSalt(salt);
    	entity.setPassword(sh.toHex());
    	int rows=sysUserDao.insertObject(entity);
    	//3.保存用户角色关系数据
    	sysUserRoleDao.insertObjects(entity.getId(), roleIds);
    	long end=System.currentTimeMillis();
    	log.info("end:"+end);
    	log.info("total time :"+(end-start));
    	//4.返回结果
    	return rows;
	}
	@RequiredLog(operation = "通过id查找")
	@Override
	public Map<String, Object> findObjectById(Integer userId) {
		//1.合法性验证
				if(userId==null||userId<=0)
				throw new ServiceException(
				"参数数据不合法,userId="+userId);
				//2.业务查询
				SysUserDeptVo user=
				sysUserDao.findObjectById(userId);
				if(user==null)
				throw new ServiceException("此用户已经不存在");
				List<Integer> roleIds=
				sysUserRoleDao.findRoleIdsByUserId(userId);
				//3.数据封装
				Map<String,Object> map=new HashMap<>();
				map.put("user", user);
				map.put("roleIds", roleIds);
				return map;
	}
	@Override
	public int updateObject(SysUser entity, Integer[] roleIds) {
		//1.参数有效性验证
				if(entity==null)
					throw new IllegalArgumentException("保存对象不能为空");
				if(StringUtils.isEmpty(entity.getUsername()))
					throw new IllegalArgumentException("用户名不能为空");
				if(roleIds==null||roleIds.length==0)
					throw new IllegalArgumentException("必须为其指定角色");
				//其它验证自己实现，例如用户名已经存在，密码长度，...
				//2.更新用户自身信息
				int rows=sysUserDao.updateObject(entity);
				//3.保存用户与角色关系数据
				sysUserRoleDao.deleteObjectsByUserId(entity.getId());
				sysUserRoleDao.insertObjects(entity.getId(),
						roleIds);
				//4.返回结果
				return rows;
			}	
	@Override
	public int updatePassword(String password, String newPassword, String cfgPassword) {
		//1.判定新密码与密码确认是否相同
				if(StringUtils.isEmpty(newPassword))
				throw new IllegalArgumentException("新密码不能为空");
				if(StringUtils.isEmpty(cfgPassword))
				throw new IllegalArgumentException("确认密码不能为空");
				if(!newPassword.equals(cfgPassword))
				throw new IllegalArgumentException("两次输入的密码不相等");
				//2.判定原密码是否正确
				if(StringUtils.isEmpty(password))
				throw new IllegalArgumentException("原密码不能为空");
				//获取登陆用户
				SysUser user=(SysUser)SecurityUtils.getSubject().getPrincipal();
				SimpleHash sh=new SimpleHash("MD5",
				password, user.getSalt(), 1);
				if(!user.getPassword().equals(sh.toHex()))
				throw new IllegalArgumentException("原密码不正确");
				//3.对新密码进行加密
				String salt=UUID.randomUUID().toString();
				sh=new SimpleHash("MD5",newPassword,salt, 1);
				//4.将新密码加密以后的结果更新到数据库
				int rows=sysUserDao.updatePassword(sh.toHex(), salt,user.getId());
				if(rows==0)
				throw new ServiceException("修改失败");
				return rows;
			}
	@Override
	public boolean isExist(String column, String value) {
		int row = sysUserDao.isExist(column, value);
		if (row>0) {
			throw new ServiceException("记录已存在");
		}
		return true;
	}
}
