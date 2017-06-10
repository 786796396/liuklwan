package com.ucap.cloud_web.servlet.message;

/**
 * <p>Description: </p>微信网页授权信息
 * <p>@Package：com.ucap.cloud_web.entity </p>
 * <p>Title: WeixinOauth2Token </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-12-22下午2:31:30 </p>
 */
public class WeixinOauth2Token {
	//网页授权接口调用凭证
	private String accessToken;
	//凭证有效时长
	private int expireIn;
	//用于刷新凭证
	private String refreshToken;
	//用户标识
	private String openId;
	//用户授权作用域
	private String scope;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
}

