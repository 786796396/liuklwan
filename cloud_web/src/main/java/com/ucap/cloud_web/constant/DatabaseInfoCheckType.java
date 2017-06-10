package com.ucap.cloud_web.constant;

/**
 * <p>Description: 未监测站点原因</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: DatabaseInfoCheckType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：yangshuai </p>
 * <p>@date：2016-7-5下午4:49:13 </p>
 */
public enum DatabaseInfoCheckType {
	
	ZERO(0,"无发布信息"),
	ONE(1,""),
	TWO(2,"网站安全配置（例如：安全狗）"),
	THREE(3,"网页格式不规范"),
	FOUR(4,"日期格式不规范"),
	FIVE(5,"相同IP的站群"),
	SIX(6,"网页格式不规范"),
	SEVEN(7,"网页格式不规范"),
	EIGHT(8,"网页格式不规范"),
	NINE(9,"网页格式不规范"),
	TEN(10,"网页格式不规范"),
	ELEVEN(11,"网页格式不规范"),
	TWELVE(12,"网站打开失败"),
	THIRTEEN(13,"网站通知关停"),
	FOURTEEN(14,"域名解析失败"),
	FIFTEEN(15,"无发布信息"),
	SIXTEEN(16,"网络防火墙屏蔽"),
	SEVENTEEN(17,"中文域名");
	
	private Integer code;
	private String name;
	
	public static String getNameByCode(Integer code) {
		for (DatabaseInfoCheckType c : DatabaseInfoCheckType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
	}
	
	private DatabaseInfoCheckType(Integer code, String name) {
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
