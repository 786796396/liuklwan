package com.ucap.cloud_web.constant;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Description:栏目类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2016-3-31下午4:15:13 </p>
 */
public enum ChannelType {
	
	WORK_ACTIVE("5", "工作动态"),
	NOTICE("6", "通知公告"),
	GOV_POLICY("7", "政府文件（政策）"),
	PERSONNEL_INFO("8","人事信息"), 
	PLAN("9", "规划计划"), 
	LEADER_INFO("10", "领导信息"), 
	ORG_INFO("11", "机构信息"), 
	AREA_INTRODUCE("12", "地区介绍"), 
	FINANCE_INFO("13", "财政信息"), 
	GREAT_PROJECT("14", "重大项目"), 
	STATISTICS_INFO("15", "统计信息"), 
	INFO_OPEN("16", "信息公开目录");

	private String code;
	private String name;

	private ChannelType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getNameByCode(String code) {
		for (ChannelType c : ChannelType.values()) {
			if (StringUtils.equals(c.getCode(), code)) {
				return c.getName();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
}
