package com.ucap.cloud_web.constant;

/**
 * <p>Description: 内容保障问题---互动回应差类型</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum SecurityResponseType {

	/**
	 * 栏目未开设
	 */
	No_OPEN_CHANNEL(1,"栏目未开设"),
	/**
	 * 栏目不可用
	 */
	NO_USE_CHANNEL(2,"栏目不可用"),
	/**
	 * 栏目不可用
	 */
	NO_INFO_ONE_YEAR(3,"1年内无有效信息和留言"),
	/**
	 * 留言超过3个月未回复
	 */
	NO_RESPONSE_THREE_MOUTH(4,"留言超过3个月未回复"),
	/**
	 * 1年内未开展活动
	 */
	NO_ACTIVITY_ONE_YEAR(5,"1年内未开展活动"),
	/**
	 * 1年内开展活动次数少
	 */
	LOWER_TIMES_ACTIVITY_ONE_YEAR(6,"1年内开展活动次数少"),
	/**
	 * 其他
	 */
	OTHERS(7,"其他")
	;

	
	private Integer code;
	private String name;
	
	private SecurityResponseType(Integer code, String name) {
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
