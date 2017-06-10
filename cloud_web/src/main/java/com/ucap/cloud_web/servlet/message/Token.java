package com.ucap.cloud_web.servlet.message;

/**
 * <p>Description: </p>凭证
 * <p>@Package：com.ucap.cloud_web.servlet.message </p>
 * <p>Title: Token </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-12-22下午5:16:54 </p>
 */
public class Token {
	//接口访问凭证
	private String accessToken;
	//凭证有效期  单位：秒
	private int expiresIn;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	
}
