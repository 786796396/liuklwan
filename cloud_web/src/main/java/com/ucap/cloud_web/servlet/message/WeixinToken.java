package com.ucap.cloud_web.servlet.message;

/**
 * <p>Description: </p>微信网页授权信息
 * <p>@Package：com.ucap.cloud_web.entity </p>
 * <p>Title: WeixinOauth2Token </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-12-22下午2:31:30 </p>
 */
public class WeixinToken {
	//网页授权接口调用凭证
	private String code;
	//用户标识
	private String openId;
	//网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	private String accessToken;
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
}

