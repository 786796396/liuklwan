package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>栏目类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum AccountType {
	/**
	 * 主管单位
	 */
	ACCOUNT_ORG(1,"组织单位(主管单位)"),
	/**
	 * 主办单位
	 */
	ACCOUNT_TB(2,"主办单位")
	
	
	;
	
	
	private Integer code;
	private String name;
	
	private AccountType(Integer code, String name) {
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
