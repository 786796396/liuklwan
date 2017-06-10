package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>门户网站和非门户网站
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum PortalType {

	/**
	 * 非门户网站
	 */
	NOT_PORTAL(0,"非门户"),
	/**
	 * 门户网站
	 */
	PORTAL(1,"门户")
	
	
	;
	
	private Integer code;
	private String name;
	
	private PortalType(Integer code, String name) {
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
