package com.ucap.cloud_web.constant;

/**
 * <p>Description: 服务状态---订单信息表中</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum ServiceStateType {
	/**
	 * 未开通
	 */
	NOT_OPEN(0,"未开通"),
	/**
	 * 已开通
	 */
	OPEN(1,"已开通"),
	/**
	 * 停止
	 */
	STOP(2,"停止"),
	/**
	 * 暂停
	 */
	PAUSE(3,"暂停")
	
	
	;
	
	private Integer code;
	private String name;
	
	private ServiceStateType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static String getNameByCode(Integer code) {
		for (ServiceStateType c : ServiceStateType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}
	

}
