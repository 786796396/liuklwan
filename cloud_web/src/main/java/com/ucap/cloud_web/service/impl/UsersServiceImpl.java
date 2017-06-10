package com.ucap.cloud_web.service.impl;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.mail.MailSenderInfo;
import com.publics.util.mail.SimpleMailSender;
import com.ucap.cloud_web.dao.UsersDao;
import com.ucap.cloud_web.entity.MenuModel;
import com.ucap.cloud_web.entity.Permission;
import com.ucap.cloud_web.entity.Users;
import com.ucap.cloud_web.service.IUsersService;
import com.ucap.cloud_web.service.exception.EmailException;

@Service
public class UsersServiceImpl implements IUsersService {

	@Autowired
	private UsersDao usersDao;

	@Override
	public Users get(Integer id) {
		return usersDao.get(id);
	}

	@Override
	public Users getUsers(String userName) {
		return usersDao.getUsers(userName);
	}

	@Override
	public Users getUser(Map<String, Object> params) {
		return usersDao.getUser(params);
	}

	@Override
	public List<Permission> getAdmin() {
		return usersDao.getAdmin();
	}

	@Override
	public List<Permission> getGuest(String userName) {
		return usersDao.getGuest(userName);
	}

	@Override
	public List<MenuModel> getAdminMenuModel() {
		return usersDao.getAdminMenuModel();
	}

	@Override
	public List<MenuModel> getGuestMenuModel(Integer userId) {
		return usersDao.getGuestMenuModel(userId);
	}

	@Override
	public String sendEmail(String email) throws EmailException {
		// 根据邮箱查找用户
		//Users user = usersDao.getUserByEmail(email);
		// 如果用户不存在说明邮箱输入有错误
//		if (user == null) {
//			throw new EmailException("邮箱不存在或者输入有错误，请核对后重新输入！");
//		}

		// 向用户发送邮件

		// 加载配置文件
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader()
					.getResourceAsStream("email_template.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}

		MailSenderInfo info = new MailSenderInfo();// 封装对象

		// 邮件服务器相关设置
		String from = prop.getProperty("from");
		// 设置邮件服务器
		info.setMailServerHost(this.getHost(from));
		// 设置用户名
		info.setUserName(prop.getProperty("username"));
		// 设置密码
		info.setPassword(prop.getProperty("password"));

		// 设置发送源
		info.setFromAddress(prop.getProperty("from"));
		// 设置发送目的的
		info.setToAddress(email);
		// 设置主题
		info.setSubject(prop.getProperty("subject"));
		// 设置内容:使用占位符替换相关的内容(使用系统随机生成验证码)
		String emailCaptcha = this.createRandom(true, 6);

		info.setContent(MessageFormat.format(prop.getProperty("content"),
				emailCaptcha));
		// 设置邮件校验：必须加，否则出现：530 Authentication required
		info.setValidate(true);
		// 发送邮件
		boolean flag = false;
		try {
			flag = SimpleMailSender.sendHtmlMail(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag) {
			return emailCaptcha;
		}
		return null;

	}

	@Override
	public void update(Users user) {

	}

	@Override
	public void updatePassword(Users users) {

		usersDao.modifyPassword(users);

	}

	/**
	 * 获得邮件服务器
	 * 
	 * @param email
	 *            ：邮件的接收方
	 * @return
	 * @throws EmailException
	 */
	private String getHost(String email) throws EmailException {
		Map<String, String> hostMap = this.getSupportEmail();
		Pattern pattern = Pattern.compile("\\w+@(\\w+)(\\.\\w+){1,2}");
		Matcher matcher = pattern.matcher(email);
		String key = "unSupportEmail";
		if (matcher.find()) {
			key = "smtp." + matcher.group(1);
		}
		if (hostMap.containsKey(key)) {
			return hostMap.get(key);
		} else {
			throw new EmailException("不支持的邮箱类型");
		}
	}

	/**
	 * 获得系统支持的邮件类型
	 * 
	 * @return
	 */
	public Map<String, String> getSupportEmail() {

		Map<String, String> hostMap = new HashMap<String, String>();
		// 126
		hostMap.put("smtp.126", "smtp.126.com");
		// qq
		hostMap.put("smtp.qq", "smtp.qq.com");

		// 163
		hostMap.put("smtp.163", "smtp.163.com");

		// sina
		hostMap.put("smtp.sina", "smtp.sina.com.cn");

		// tom
		hostMap.put("smtp.tom", "smtp.tom.com");

		// 263
		hostMap.put("smtp.263", "smtp.263.net");

		// yahoo
		hostMap.put("smtp.yahoo", "smtp.mail.yahoo.com");

		// hotmail
		hostMap.put("smtp.hotmail", "smtp.live.com");

		// gmail
		hostMap.put("smtp.gmail", "smtp.gmail.com");
		hostMap.put("smtp.port.gmail", "465");

		// ucmp

		return hostMap;
	}

	/**
	 * @Description:java随机生成验证码
	 * @author kefan-- 2015-11-14 下午4:25:51
	 * @param numberFlag
	 *            :生成验证码的种类： true:生成的验证码为数字 false：生成的验证码为字母
	 * @param length
	 *            ：验证码的长度
	 * @return
	 */
	private String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

	@Override
	public Users getUsersByEmailAndUserName(Users users) {
		return this.usersDao.getUsersByEmailAndUserName(users);

	}

}
