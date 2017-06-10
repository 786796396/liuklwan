package com.ucap.cloud_web.constant;

/**
 * <p>Description: 预警发送邮件 标题</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: EarlyMailTitleType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2016-11-30下午4:39:51 </p>
 */
public enum EarlyMailType {

	
	/**
	 * 首页连不通实时预警  ----邮件模版
	 */
	CONN_HOME_SS(1,"earlyMail5.ftl"),
	/**
	 * 首页连不连通率累计超过3%  ----邮件模版
	 */
	CONN_HOME_PERCENT(2,"earlyMail6.ftl"),
	
	/**
	 *疑似错别字  邮件模版  
	 */
	CORRECT_CONNECT(3,"earlyMail7_cbz.ftl"),
	/**
	 * 首页超过10天未更新  -----邮件模版
	 */
	NOT_UPDATE_HOME(4,"earlyMail7_sybgx.ftl"),
	
	/**
	 * 严重问题  
	 * 空白栏目超过2个 
	 * 基本信息不更新的栏目超过6个 
	 * 互动回应栏目长期未回应 ---- 邮件模版
	 */
	SECURITY_MODAL(5,"eearlyMail7.ftl"),
	/**
	 * 日报  -----邮件模版
	 */
	DAY_REPORT(6,"dailyMail.ftl")
	
	;
	
	private Integer code;
	private String name;
	private EarlyMailType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static String getNameByCode(Integer code) {
		for (EarlyMailType c : EarlyMailType.values()) {
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
