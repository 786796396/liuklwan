package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>栏目类型对应的逾期未更新时间
 * 更新
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：luocheng </p>
 * <p>@date：2017-03-27 </p>
 */
public enum ChannelNotUpdateType {
	TWO_WEEK_NOTUPDATE(1,"超过两周逾期未更新"), //动态要闻类  对应栏目类型： 5
	SIX_MOUTH_NOTUPDATE(2,"超过六个月逾期未更新"), //通知公告  政策文件 : 6 7 
	ONE_YEAR_NOTUPDATE(3,"超过一年逾期未更新"), //其他栏目Id在  8,9,10,11,12,13,14,15,16 区间的
	NOMAL_UPDATE(4,"正常更新"),
	FIVE(5,"工作动态"),
	SIX(6,"通知公告"),
	SEVEN(7,"政府文件（政策"),
	TWO_WEEK_UPDATESTR(8,"14天"),
	SIX_MOUTH_UPDATESTR(9,"6个月"),
	ONE_YEAR_UPDATESTR(10,"1年");
	/*EIGHT(8,"人事信息"),
	NINE(9,"规划计划"),
	TEN(10,"领导信息"),
	ELEVEN(11,"机构信息"),
	TWELVE(12,"地区介绍"),
	THIRTEEN(13,"财政信息"),
	FOURTEEN(14,"重大项目"),
	FIVETEEN(15,"统计信息"),
	SIXTEEN(16,"信息公开目录");*/
	
	
	
	private Integer code;
	private String name;
	
	private ChannelNotUpdateType(Integer code, String name) {
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
