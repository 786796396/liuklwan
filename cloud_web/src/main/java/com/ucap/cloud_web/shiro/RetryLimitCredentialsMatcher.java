package com.ucap.cloud_web.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * 描述：校验用户的登录密码  
 * 包：com.ucap.cloud_web.shiro
 * 文件名称：RetryLimitCredentialsMatcher
 * 公司名称：开普互联
 * 作者：lixuxiang 
 * 时间：2015-11-20上午11:13:28 
 * @version V1.0
 */
public class RetryLimitCredentialsMatcher extends SimpleCredentialsMatcher {

	private static Logger _logger = Logger.getLogger(Log.class);

	// 集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发
	private Cache<String, AtomicInteger> passwordRetryCache;

	public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	/**
	 * 密码验证，以及密码的错误次数，锁住用户
	 */
	public boolean doCredentialsMatch(AuthenticationToken authToken,
			AuthenticationInfo info) {
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authToken;
		String username = (String) token.getPrincipal();
		// 获得用户的登录次数
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (null == retryCount) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > 10) {
			_logger.info("username: " + username
					+ " tried to login more than 10 times in period");
			throw new ExcessiveAttemptsException("username: " + username
					+ " tried to login more than 10 times in period");
		}

		// 获得系统中的密码
		Object dbLoginPassword = this.getCredentials(info);
		// 获得填写的密码

		String inputPassword = String.valueOf(token.getPassword());

		// 校验密码验证是否通过
		boolean matches = super.equals(inputPassword, dbLoginPassword);
		// boolean matches = super.doCredentialsMatch(token, info);
		// 校验通过，移除登录缓存
		if (matches) {
			// clear retry data
			passwordRetryCache.remove(username);
		}
		return matches;
	}

}
