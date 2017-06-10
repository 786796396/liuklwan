package com.ucap.cloud_web.constant;
/**
 * <p>Description: 内容更新类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ContentUpdateType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015年11月12日下午7:11:39 </p>
 */
public enum ContentUpdateType {
	HOME(0,"首页"),
	CHANNEL(1,"栏目");
	private Integer code;
	private String name;
	private ContentUpdateType(Integer code, String name) {
		this.code = code;
		this.name = name;
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
