package com.ucap.cloud_web.constant;


/**
 * <p>Description: </p>栏目类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum ServiceUnuserfulType {
	ONE_PROBLEM_TYPE(1,"办事指南要素缺失"),
	TWO_PROBLEM_TYPE(2,"办事指南要素不准确"),
	THREE_PROBLEM_TYPE(3,"附件未提供下载"),
	FOUR_PROBLEM_TYPE(4,"附件无法下载"),
	FIVE_PROBLEM_TYPE(5,"其他");
	
	
	private Integer code;
	private String name;
	
	public static String getNameByCode(Integer code) {
		for (ServiceUnuserfulType c : ServiceUnuserfulType.values()) {
			if(c.getCode()==code){
				return c.name;
			}
		}
		return null;
	}
	
	public static Integer getCodeByName(String name) {
		for (ServiceUnuserfulType c : ServiceUnuserfulType.values()) {
			if(c.getName().equals(name)){
				return c.code;
			}
		}
		return null;
	}
	
	private ServiceUnuserfulType(Integer code, String name) {
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
