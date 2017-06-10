package com.ucap.cloud_web.constant;

/**
 * <p>Description: 套餐类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: comboType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2016-1-5下午9:50:03 </p>
 */
public enum ComboType {
	
	A("A","常态化检测"),
	B("B","免费体验版"), 
	C("C","标准版"),
	D("D","高级版");
	
	private String code;
	private String name;
	
	private ComboType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
