package com.ucap.cloud_web.service.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @Description: 自定义验证码错误异常
 * @Package com.ucap.cloud_web.service.exception
 * @Title: IncorrectCaptchaException
 * @Company:开普互联
 * @author： kefan
 * @date： 2015-11-14下午3:30:15
 * @version V1.0
 */

public class IncorrectCaptchaException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public IncorrectCaptchaException() {
		super();
	}

	public IncorrectCaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectCaptchaException(String message) {
		super(message);
	}

	public IncorrectCaptchaException(Throwable cause) {
		super(cause);
	}

}
