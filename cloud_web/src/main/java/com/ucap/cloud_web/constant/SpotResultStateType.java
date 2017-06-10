package com.ucap.cloud_web.constant;

/**
 * <p>Description:抽查结果表中状态    抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成） </p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum SpotResultStateType {

	/**
	 * 未提交
	 */
	NOT_SUBMIT(-1,"未提交"),
	/**
	 * 未启动
	 */
	NOT_OPEN(0,"未启动"),
	
	/**
	 * 检查中
	 */
	IN_CHECK(1,"检查中"),
	
	/**
	 * 扫描完成
	 */
	SCAN_FINISH(2,"扫描完成"),
	
	/**
	 * 报告完成
	 */
	REPORT_FINISH(3,"报告完成"),
	
	/**
	 * 报告完成已通过
	 */
	REPORT_PASS(4,"报告完成"),
	
	/**
	 * 报告完成未通过
	 */
	REPORT_NOT_PASS(5,"报告完成")
	;
	
	private Integer code;
	private String name;
	
	public static String getNameByCode(Integer code) {
		for (SpotResultStateType c : SpotResultStateType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
	}
	
	private SpotResultStateType(Integer code, String name) {
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
