package com.ucap.cloud_web.constant;

/**
 * <p>Description: 内容保障问题--基本信息--问题类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: SecurityBasicType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2016-4-12上午9:44:34 </p>
 */
public enum SecurityBasicType {
	
	NEWS_NOT_UPDATE(1,"动态要闻类栏目不更新"),
	NOTICE_NOT_UPDATE(2,"通知公告类栏目不更新"),
	POLICY_NOT_UPDATE(3,"政策文件类栏目不更新"),
	PERSONNEL_NOT_UPDATE(4,"人事类栏目不更新"),
	
	
	PLAN_NOT_UPDATE(5,"规划计划类栏目不更新"),
	LONG_UPDATE_NOT_UPDATE(6,"应更新但长期不更新栏目"),
	CHANNEL_INFO_ERROR(7,"栏目信息不准确");
	
	
	private Integer code;
	private String name;
	
	private SecurityBasicType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	
	public static String getNameByCode(Integer code){
		
		for (SecurityBasicType c : SecurityBasicType.values()) {
			if(c.getCode()==code){
				return c.getName();
			}
		}
		return null;
	}
	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}
}
