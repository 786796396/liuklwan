package com.ucap.cloud_web.constant;


/**
 * <p>Description:日志类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：Na.Y </p>
 * <p>@date：2016-3-31下午4:15:13 </p>
 */
public enum LogType {
	
	DB_U_B(1, "基本信息表修改-前台");

	private int code;
	private String name;

	private LogType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getNameByCode(int code) {
		for (LogType c : LogType.values()) {
			if (c.getCode()== code) {
				return c.getName();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}
}
