package com.ucap.cloud_web.constant;


/**
 * <p>Description: 网站更新类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ConnectionType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015年11月10日上午10:45:03 </p>
 */
public enum UpdateStatusType {
	UNKONWN(-1,"未知"),
	NORMAL(1,"正常更新"),
	TWO_WEAK(0,"超过两周");

	
	private Integer code;
	private String name;
	private UpdateStatusType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	
	
	public static String getNameByCode(Integer code) {
		for (UpdateStatusType c : UpdateStatusType.values()) {
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
