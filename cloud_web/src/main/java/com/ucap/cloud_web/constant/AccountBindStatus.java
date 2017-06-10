package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>栏目类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum AccountBindStatus {

	/**
	 * 绑定
	 */
	ACCOUNT_BIND(1,"绑定"),
	/**
	 * 解绑
	 */
	ACCOUNT_UNBIND(0,"解绑")
	
	
	;
	
	private Integer code;
	private String name;
	
	private AccountBindStatus(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}
	

}
