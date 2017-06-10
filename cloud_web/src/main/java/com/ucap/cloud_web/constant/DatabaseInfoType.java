package com.ucap.cloud_web.constant;

/**
 * 基本信息状态
 * 
 * @author: lixuxiang
 */
public enum DatabaseInfoType {
	
	NORMAL(1,"正常"),
	CHANGE(2,"例外"),
	SHUT(3,"关停");
	
	private Integer code;
	private String name;
	
	public static String getNameByCode(Integer code) {
		for (DatabaseInfoType c : DatabaseInfoType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
	}
	
	private DatabaseInfoType(Integer code, String name) {
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
