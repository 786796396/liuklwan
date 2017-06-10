package com.ucap.cloud_web.constant;


/**
 * <p>Description:[服务中心] 开通服务的类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：luocheng </p>
 * <p>@date：2017-4-7 </p>
 */
public enum ServiceOpenType {
	
	OVERALLDETECTION("1", "全面检测"),
	MISSPELLINGS("2", "错别字扫描"),
	LINKALL("3", "全站死链扫描"),
	SAFE("4", "安全扫描"),
	Depth("5", "栏目深度检测"),
	SPOT("6", "抽查"),
	PROTARGET("7", "绩效考评");

	private String code;
	private String name;

	private ServiceOpenType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getNameByCode(int code) {
		for (LogType c : LogType.values()) {
			if (c.getCode()== code) {
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
