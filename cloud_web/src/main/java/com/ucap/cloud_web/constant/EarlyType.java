package com.ucap.cloud_web.constant;


/**
 * <p>Description: 预警类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: SpotTargetType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：Nora </p>
 * <p>@date：2016年01月06日上午10:45:03 </p>
 * 
 * 预警类型：1首页连通性，2首页不更新，3栏目不更新，4空白栏目，5互动回应，6内容正确性，7升级改版或者临时关停，
 * 		  8首页连不通比例超过3%，9网站疑似被挂码或被篡改，10首页不更新超过10天 ，11空白栏目超过5个，
 * 		  12栏目不更新超过8个，13互动回应差栏目超过3个月未回应
 */
public enum EarlyType {
	/**
	 * 首页连不通
	 */
	CON_HOME(1,"首页连不通"),
	/**
	 * 首页不更新
	 */
	SECURITY_HOME(2,"首页不更新"),
	/**
	 * 栏目不更新
	 */
	SECURITY_CHANNEL(3,"栏目不更新"),
	/**
	 * 空白栏目
	 */
	BLANK_CHANNEL(4,"空白栏目"),
	/**
	 * 互动回应
	 */
	RESPONSE(5,"互动回应"),
	/**
	 * 严重错别字
	 */
	CONTENT_CORRECT(6,"疑似错别字"),
	
	/**
	 * 升级改版或者临时关停
	 */
	UPDATE_GRADE(7,"升级改版或者临时关停"),
	/**
	 * 首页连不通比例超过3%
	 */
	HOME_CONNECTION_PER(8,"首页不连通率超过3%"),
	/**
	 * 严重问题
	 */
	MODIFY_SITE(9,"严重问题"),
	/**
	 * 首页超过10天未更新
	 */
	NOT_UPDATE_HOME(10,"首页超过10天未更新"),
	/**
	 * 空白栏目超过4个
	 */
	BLANK_CHANNEL_MUCH(11,"空白栏目超过2个"),
	/**
	 * 基本信息不更新的栏目超过8个
	 */
	NOT_UPDATE_CHANNEL(12,"基本信息不更新的栏目超过6个"),
	/**
	 * 互动回应差栏目超过3个月未回应
	 */
	SECURITY_RESPONSE(13,"互动回应问题"),
	/**
	 * 互动回应差栏目超过3个月未回应
	 */
	CON_HOME_OK(14,"首页访问正常")
	
	;
	private Integer code;
	private String name;
	private EarlyType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static String getNameByCode(Integer code) {
		for (EarlyType c : EarlyType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
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
