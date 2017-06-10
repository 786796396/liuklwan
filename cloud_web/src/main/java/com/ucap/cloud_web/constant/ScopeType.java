package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>栏目类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum ScopeType {
	IN_AREA(1,"站内"),
	OUT_AREA(2,"域外");
	
	private Integer code;
	private String name;
	
	private ScopeType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public static String getNameByCode(Integer code) {
		for (ScopeType c : ScopeType.values()) {
			if (c.getCode()==code) {
				return c.getName();
			}
		}
		return null;
	}
	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}
	

}
