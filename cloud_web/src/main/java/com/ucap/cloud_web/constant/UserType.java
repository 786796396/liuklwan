package com.ucap.cloud_web.constant;

/**
 * 描述： 用户登录类型 包：com.ucap.cloud_web.constant 文件名称：UserType 公司名称：开普互联 作者：lixuxiang
 * 时间：2015-11-23下午08:17:50
 * 
 * @version V1.0
 */
public enum UserType {

	TYPE_ADMIN(1, "管理员"), 
	TYPE_PROVINCE(2, "省级用户"), 
	TYPE_CITY(3, "市级用户"), 
	TYPE_COUNTY(4, "县级用户"), 
	TYPE_ORDINARY(5,"填报用户");

	private Integer code;
	private String name;

	private UserType(Integer code, String name) {
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
