package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>栏目类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum DatabaseLinkType {

	/**
	 * 类型（1本级门户，2本级部门，3下属单位，4例外，5关停，6其他）
	 */
	ALL(0,"全部"),
	ISORGANIZATIONAL(1,"本级门户"),
	DEPARTMENT(2,"本级部门"),
	UNIT(3,"下属单位"),
	EXCEPTION(4,"例外 "),
	CLOSE(5,"关停 "),
	OTHER(6,"其他 ");
	
	private Integer code;
	private String name;
	
	private DatabaseLinkType(Integer code, String name) {
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
