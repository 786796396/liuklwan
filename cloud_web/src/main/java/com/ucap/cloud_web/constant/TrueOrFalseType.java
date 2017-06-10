package com.ucap.cloud_web.constant;

/**
 * <p>Description: 是否类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum TrueOrFalseType {
	/**
	 * 是
	 */
	TRUE(1,"是"),
	/**
	 * 否
	 */
	FALSE(0,"否")
	
	
	;
	
	
	private Integer code;
	private String name;
	
	private TrueOrFalseType(Integer code, String name) {
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
