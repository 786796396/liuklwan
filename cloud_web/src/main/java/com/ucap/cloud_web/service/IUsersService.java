package com.ucap.cloud_web.service;

import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.MenuModel;
import com.ucap.cloud_web.entity.Permission;
import com.ucap.cloud_web.entity.Users;
import com.ucap.cloud_web.service.exception.EmailException;

/**
 * <p>
 * Description: 基本信息操作接口
 * </p>
 * <p>
 * 
 * @Package：com.ucap.ucap_web.service </p>
 *                                    <p>
 *                                    Title: IDatabaseInfoService
 *                                    </p>
 *                                    <p>
 *                                    Company: 开普互联
 *                                    </p>
 *                                    <p>
 * @author：lixuxiang </p>
 *                   <p>
 * @date：2015-8-5上午11:27:50 </p>
 */
public interface IUsersService {

	/**
	 * 通过主键获取对象数据
	 * 
	 * @param sitecode
	 *            网站标示码 (必填)
	 * @return DatabaseInfo
	 */
	public Users get(Integer id);

	/**
	 * @Description:
	 * @author kefan-- 2015-11-13 下午6:05:04
	 * @param
	 * @return
	 */
	public Users getUsers(String userName);

	public Users getUser(Map<String, Object> params);

	public List<Permission> getAdmin();

	public List<Permission> getGuest(String userName);

	public List<MenuModel> getAdminMenuModel();

	public List<MenuModel> getGuestMenuModel(Integer userId);

	/**
	 * @Description:发送邮件
	 * @author kefan-- 2015-11-14 下午4:36:55
	 * @param email
	 *            ：收邮件的邮箱
	 * @return：如果邮件发送成功，返回邮件验证码，如果邮件发送失败，返回null
	 * @throws EmailException
	 *             :邮件发送失败，则抛出此异常
	 */
	public String sendEmail(String email) throws EmailException;

	/**
	 * @Description:更新用户信息
	 * @author kefan-- 2015-11-16 上午8:45:04
	 * @param user
	 */
	public void update(Users user);

	/**
	 * @Description:更新密码:实体对象user中至少应该有的属性：主键，密码
	 * @author kefan-- 2015-11-16 上午8:45:23
	 * @param user
	 */
	public void updatePassword(Users user);

	/**
	 * @Description:根据邮箱获取用户
	 * @author kefan-- 2015-11-13 下午6:48:48
	 * @param email
	 * @return
	 */
	public Users getUsersByEmailAndUserName(Users users);
}
