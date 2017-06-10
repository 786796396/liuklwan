package com.ucap.cloud_web.constant;
/**
 * <p>Description: 预警类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: SpotTargetType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：Nora </p>
 * <p>@date：2016年01月06日上午10:45:03 </p>
 * 
 * 预警类型：1首页连通性，2首页不更新，3栏目不更新，4空白栏目，5互动回应，6内容正确性
 */
public enum EarlySendType {
	/**
	 * 首页连通性
	 */
	CON_HOME(1,"首页不连通"),
	/**
	 * 内容正确性
	 */
	CONTENT_CORRECT(6,"严重错别字");
	private Integer code;
	private String name;
	private EarlySendType(Integer code, String name) {
		this.code = code;
		this.name = name;
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
