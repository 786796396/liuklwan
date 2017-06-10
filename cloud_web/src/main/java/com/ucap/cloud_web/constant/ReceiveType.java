package com.ucap.cloud_web.constant;
/**
 * <p>Description: 微信版-是否接受报告管理或者预警信息</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: DetectionResultType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015年11月23日下午8:31:34 </p>
 */
public enum ReceiveType {
	/**
	 * 接收
	 */
	IS_RECEIVE(1,"接受"),
	/**
	 * 不接受
	 */
	IS_NOT_RECEIVE(0,"不接受");

	
	private Integer code;
	private String name;
	
	private ReceiveType(Integer code, String name) {
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
