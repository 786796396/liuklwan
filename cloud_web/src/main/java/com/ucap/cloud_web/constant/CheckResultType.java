package com.ucap.cloud_web.constant;

/**
 * <p>Description:检查结果（1：合格，0：单项否决,2:未检查） </p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum CheckResultType {


	/**
	 * 单项否决
	 */
	SINGLE_REFUSE(0,"单项否决"),
	/**
	 * 未启动 standard
	 */
	STANDARD(1,"合格"),
	/**
	 * 未检查
	 */
	NOT_CHECK(2,"未检查")
	;
	
	private Integer code;
	private String name;
	
	public static String getNameByCode(Integer code) {
		for (CheckResultType c : CheckResultType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
	}
	
	private CheckResultType(Integer code, String name) {
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
