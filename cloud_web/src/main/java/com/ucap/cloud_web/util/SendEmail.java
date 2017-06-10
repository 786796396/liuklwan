package com.ucap.cloud_web.util;

import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.publics.util.mail.MailSenderInfo;
import com.publics.util.mail.SimpleMailSender;
import com.publics.util.utils.FreemarkerUtils;
import com.publics.util.utils.PropertiesUtil;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.timer.TaskSendEarly;

public class SendEmail {
	
	private static Logger logger = Logger.getLogger(SendEmail.class);//日志信息
	private static Properties prop = PropertiesUtil.getProperties("app.properties");
	private static Properties prop2 = PropertiesUtil.getProperties("mail.properties");
	
	/**
	 * @Description: 发送邮件
	 * @author sunjiang --- 2016-3-31下午6:57:55     
	 * @param title	邮件标题
	 * @param ftl	邮件模板
	 * @param mapParam	内容参数
	 * @param email	收件人的地址
	 * @return
	 * @throws Exception
	 */
	public static boolean sendEmail(String title,String ftl,Map<Object, Object> mapParam,String email) throws Exception {
		
		try {
			if(StringUtils.isEmpty(email)){
				return false;
			}
			MailSenderInfo info = new MailSenderInfo();
			info.setMailServerHost(prop2.getProperty("sendMail_MailServerHost"));
			info.setMailServerPort(prop2.getProperty("sendMail_ServerPort"));
			info.setFromAddress(javax.mail.internet.MimeUtility.encodeText(prop2.getProperty("sendMail_FromAddress"),
					javax.mail.internet.MimeUtility.mimeCharset("utf-8"), null)+ prop2.getProperty("sendMail_FromAddressMail"));
			info.setUserName(prop2.getProperty("sendMail_UserName"));
			info.setPassword(prop2.getProperty("sendMail_Password"));

			info.setToAddress(email);
			info.setSubject(title);

			String html = FreemarkerUtils.getTemplateOutput(ftl, mapParam);

			info.setContent(html);

			info.setValidate(true);
			if (SimpleMailSender.sendHtmlMail(info)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("======SendEmail sendEmail()=======邮件发送异常");
			return false;
		}

	}
	

	/**
	 * @Description: 发送首页信息不更新预警邮件
	 * @author cuichx --- 2016-6-12下午6:03:14     
	 * @param title
	 * @param ftl
	 * @param mapParam
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public static boolean sendEmailHomeNotUpdate(String title,String ftl,Map<String, Object> mapParam,String email) throws Exception {
		
		try {
			MailSenderInfo info = new MailSenderInfo();
			info.setMailServerHost(prop2.getProperty("sendMail_MailServerHost"));
			info.setMailServerPort(prop2.getProperty("sendMail_ServerPort"));
			info.setFromAddress(javax.mail.internet.MimeUtility.encodeText(prop2.getProperty("sendMail_FromAddress"),
					javax.mail.internet.MimeUtility.mimeCharset("utf-8"), null)+ prop2.getProperty("sendMail_FromAddressMail"));
			info.setUserName(prop2.getProperty("sendMail_UserName"));
			info.setPassword(prop2.getProperty("sendMail_Password"));

			info.setToAddress(email);
			info.setSubject(title);

			String html = FreemarkerUtils.getTemplateOutput(ftl, mapParam);

			info.setContent(html);

			info.setValidate(true);
			if (SimpleMailSender.sendHtmlMail(info)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("======SendEmail sendEmailHomeNotUpdate()=======邮件发送异常");
			return false;
		}

	}

}
