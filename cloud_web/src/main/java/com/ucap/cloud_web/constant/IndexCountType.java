package com.ucap.cloud_web.constant;

/**
 * <p>Description: 健康指数统计表分类</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum IndexCountType {

	/**
	 * 付费
	 */
	NEED_MONEY(1,"付费"),
	/**
	 * 免费
	 */
	NOT_NEED_MONEY(2,"免费"),
	
	/**
	 * 抽查
	 */
	NEED_CHECK(3,"抽查")
	
	;
	
	private Integer code;
	private String name;
	
	private IndexCountType(Integer code, String name) {
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
