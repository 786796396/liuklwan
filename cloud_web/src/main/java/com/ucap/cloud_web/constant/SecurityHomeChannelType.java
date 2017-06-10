package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>栏目类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum SecurityHomeChannelType {

	/**
	 * 首页不更新
	 */
	HOME(1,"首页不更新"),
	/**
	 * 栏目不更新
	 */
	CHANNEL(2,"栏目不更新")
	
	
	;
	
	private Integer code;
	private String name;
	
	
	public static String getNameByCode(int code){
		for (SecurityHomeChannelType c : SecurityHomeChannelType.values()) {
			if(c.getCode()==code){
				return c.getName();
			}
		}
		return null;
	}
	private SecurityHomeChannelType(Integer code, String name) {
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
