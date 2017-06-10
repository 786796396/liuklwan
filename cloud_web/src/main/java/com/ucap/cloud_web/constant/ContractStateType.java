package com.ucap.cloud_web.constant;

/**
 * <p>Description: 服务状态---订单信息表中</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum ContractStateType {
	

	/**
	 * 执行中
	 */
	RUNNING(1,"执行中"),
	/**
	 * 结束
	 */
	CLOSE(2,"结束"),
	/**
	 * 意外终止
	 */
	STOP(3,"意外终止")

	
	
	;
	
	private Integer code;
	private String name;
	
	private ContractStateType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public static String getNameByCode(Integer code){
		for (ContractStateType c : ContractStateType.values()) {
			if(c.code==code){
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
