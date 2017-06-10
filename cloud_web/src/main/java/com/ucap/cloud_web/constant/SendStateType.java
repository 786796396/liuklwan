package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>栏目类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum SendStateType {

	FAILURE(0,"失败"),
	SUCCESS(1,"成功");
	
	
	private Integer code;
	private String name;
	
	private SendStateType(Integer code, String name) {
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
