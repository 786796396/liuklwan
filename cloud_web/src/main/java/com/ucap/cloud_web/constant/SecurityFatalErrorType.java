package com.ucap.cloud_web.constant;


/**
 * <p>Description: </p>栏目类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum SecurityFatalErrorType {
	ONE_FATAL_TYPE(1,"虚假或伪造内容"),
	TWO_FATAL_TYPE(2,"反动、暴力或色情内容"),
	THREE_FATAL_TYPE(3,"其它");
	
	private Integer code;
	private String name;
	
	public static String getNameByCode(Integer code) {
		for (SecurityFatalErrorType c : SecurityFatalErrorType.values()) {
			if(c.getCode()==code){
				return c.name;
			}
		}
		return null;
	}
	
	public static Integer getCodeByName(String name) {
		for (SecurityFatalErrorType c : SecurityFatalErrorType.values()) {
			if(c.getName().equals(name)){
				return c.code;
			}
		}
		return null;
	}
	
	private SecurityFatalErrorType(Integer code, String name) {
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
