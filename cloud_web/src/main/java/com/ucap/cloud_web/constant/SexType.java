package com.ucap.cloud_web.constant;

import org.apache.commons.lang.StringUtils;

/**
 * 问题代码类型
 * 
 * @author: Nora
 */
public enum SexType {
	
	MALE("1", "男"),
	FEMALE("2", "女"),
	OTHERS("0", "未知");

	private String code;
	private String name;

	private SexType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getNameByCode(String code) {
		for (SexType c : SexType.values()) {
			try {
				if (StringUtils.equals(c.getCode(), code)) {
					return c.name;
				}
			} catch (Exception e) {
				return "";
			}
			
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
}
