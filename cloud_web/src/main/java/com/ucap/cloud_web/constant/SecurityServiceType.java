package com.ucap.cloud_web.constant;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Description: 内容保障问题--服务不实用类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum SecurityServiceType {

	/**
	 * 办事指南要素缺失
	 */
	GUIDE_LOSE(1,"办事指南要素缺失"),
	/**
	 * 办事指南要素不准确
	 */
	GUIDE_NOT_CORRECT(2,"办事指南要素不准确"),
	/**
	 * 附件未提供下载
	 */
	ACCESSORY_NOT_UPLOAD(3,"附件未提供下载"),
	/**
	 * 附件无法下载
	 */
	ACCESSORY_UPLOAD_FAILURE(4,"附件无法下载"),
	/**
	 * 其他
	 */
	OTHERS(5,"其他")
	
	
	;

	
	private Integer code;
	private String name;
	
	
	public static String getNameByCode(Integer code) {
		for (SecurityServiceType c : SecurityServiceType.values()) {
			if (c.getCode()==code) {
				return c.getName();
			}
		}
		return null;
	}
	private SecurityServiceType(Integer code, String name) {
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
