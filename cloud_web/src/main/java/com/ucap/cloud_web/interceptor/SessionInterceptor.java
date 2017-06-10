package com.ucap.cloud_web.interceptor;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ucap.cloud_web.constant.ValidateUrlConstant;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.shiro.ShiroUser;

public class SessionInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -2771516501737587145L;
	private static Log _logger = LogFactory.getLog(SessionInterceptor.class);
	
	private static final String VALIDATE_ACTION_NAME = ValidateUrlConstant.getValidateActionName();
	private static final Pattern VALIDATE_PATTERN = Pattern.compile(VALIDATE_ACTION_NAME);
	
	/**
	 *	拦截所有请求判断是否有session，如果有想先执行，没有跳转登录页面
	 */
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		
		ActionContext invocation = actionInvocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) invocation.get(StrutsStatics.HTTP_REQUEST);
		
		String url = request.getRequestURI();
		// 不进行验证的请求
		if(VALIDATE_PATTERN.matcher(url).find()) { 
			return actionInvocation.invoke();
		}
		
		Map<String, Object> session = invocation.getSession();
		Object obj = session.get(Constants.SHIRO_USER);
		if(null == obj){
			return "login";
		}
		
		_logger.info("系统:"+System.getProperty("os.name")+" 登录用户:"+((ShiroUser)obj).getSiteCode());
		
		return actionInvocation.invoke();
	}

}
