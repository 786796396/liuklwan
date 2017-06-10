package com.ucap.cloud_web.constant;

/**
 * <p>Description: 连通性的类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ConnectionType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015年11月10日上午10:45:03 </p>
 */
public enum ConnectionType {
	HOME(1,"首页连通性"),
	BUSINESS(2,"业务系统连通性"),
	CHANNEL(3,"关键栏目连通性");
	private Integer code;
	private String name;
	private ConnectionType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	
	
	public static String getNameByCode(int code) {
		for (ConnectionType c : ConnectionType.values()) {
			if (c.getCode()==code) {
				return c.getName();
			}
		}
		return null;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
