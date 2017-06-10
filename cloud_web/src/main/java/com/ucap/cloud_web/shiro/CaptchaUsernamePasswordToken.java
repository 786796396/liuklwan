package com.ucap.cloud_web.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Description:密码以及验证码Token
 * 
 * @Package：com.erp.shiro
 * @Title: CaptchaUsernamePasswordToken
 * @Company: 开普互联
 * @author：lixuxiang
 * @date：2015-9-19上午11:08:43
 * @version V1.0
 */

public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
	
	private static final long serialVersionUID = -3217596468830869181L;
	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe, String host,
			String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken() {
		super();
	}
}
