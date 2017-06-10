package com.ucap.cloud_web.constant;
/**
 * <p>Description: 组织单位-概览页面-检测结果：网站类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: DetectionResultType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015年11月23日下午8:31:34 </p>
 */
public enum DetectionResultType {
	/**
	 * 网站连通性
	 */
	CONNTYPE(1,"网站连通性"),
	/**
	 * 链接可用性
	 */
	LINKTYPE(2,"链接可用性"),
	/**
	 * 内容保障性
	 */
	CONTGUARANTETYPE(3,"内容保障性"),
	/**
	 * 内容正确性
	 */
	CONTCORRECTTYPE(4,"内容正确性"),
	/**
	 * 网络安全
	 */
	WEBSITESAFETYPE(5,"网络安全"),
	/**
	 * 内容更新
	 */
	CONTUPDATETYPE(6,"内容更新");
	
	private Integer code;
	private String name;
	
	private DetectionResultType(Integer code, String name) {
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
