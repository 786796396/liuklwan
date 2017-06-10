package com.ucap.cloud_web.constant;

/**
 * <p>Description:抽查进度表状态（0：未启动，1：检查中，2：检查完成） </p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum SpotScheduleStateType {

	/**
	 * 未启动
	 */
	NOT_START(0,"未启动"),
	/**
	 * 检查中
	 */
	IN_CHECK(1,"检查中"),
	
	/**
	 * 检查完成
	 */
	CHECK_FINISH(2,"检查完成")
	
	;
	
	private Integer code;
	private String name;
	
	public static String getNameByCode(Integer code) {
		for (SpotScheduleStateType c : SpotScheduleStateType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
	}
	
	private SpotScheduleStateType(Integer code, String name) {
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
