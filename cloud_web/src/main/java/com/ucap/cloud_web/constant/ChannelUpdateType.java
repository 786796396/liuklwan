package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>栏目类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum ChannelUpdateType {
	TWO_WEEK_UPDATE(1,"应两周内更新的栏目"),
	SIX_MOUTH_UPDATE(2,"应六个月内更新的栏目"),
	ONE_YEAR_UPDATE(3,"应一年内更新的栏目");
	
	
	private Integer code;
	private String name;
	
	private ChannelUpdateType(Integer code, String name) {
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
