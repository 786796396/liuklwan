package com.ucap.cloud_web.constant;

/**
 * <p>Description: 云分析返回错误类型 的枚举类/p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: CloudAnalyzeErrorType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：luocheng </p>
 * <p>@date：2017-3-6 </p>
 */
public enum CloudAnalyzeErrorType {
	
	A(1,"无此账号"),
	B(2,"密码错误"), 
	C(3,"正常登陆");
	
	
	private Integer code;
	private String name;
	
	private CloudAnalyzeErrorType(Integer code, String name) {
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
