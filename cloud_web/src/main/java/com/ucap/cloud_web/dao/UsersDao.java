package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.entity.MenuModel;
import com.ucap.cloud_web.entity.Permission;
import com.ucap.cloud_web.entity.Users;

/**
 * @{#} LyRegionDao.java Create on 2015-7-27  
 * Copyright (c) 2012 by lixuxiang.
 */
public interface UsersDao extends GenericDao<Users> {

	public Users getUsers(String userName);
	public Users getUser(Map<String, Object> params);
	
	public List<Permission> getAdmin();
	public List<Permission> getGuest(String userName);
	
	public List<MenuModel> getAdminMenuModel();
	public List<MenuModel> getGuestMenuModel(Integer userId);
	
	
	
	/**
	 * 根据邮箱查找用户
	 * @return
	 */
	//public Users getUserByEmail(String email);
	
	
	/**
	 * 更新用户密码
	 * @param email
	 * @return
	 */
	public void modifyPassword(Users users);
	
	/**
	 * @Description:根据邮箱获取用户
	 * @author kefan-- 2015-11-13 下午6:49:46
	 * @param email
	 * @return
	 */
	public Users getUsersByEmailAndUserName(Users users);
	
		
	
	
}