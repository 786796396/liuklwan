package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: CheckReportResultType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：yangshuai </p>
 * <p>@date：2016-5-14下午4:29:53 </p>
 */
public enum CheckReportResultType {

	/**
	 * 待审核
	 */
	WAIT_CHECK(0,"待审核"),
	/**
	 * 通过
	 */
	CHECK_PASS(1,"通过"),
	/**
	 * 未通过
	 */
	CHECK_NOT_PASS(2,"未通过")
	;
	
	private Integer code;
	private String name;
	
	public static String getNameByCode(Integer code) {
		for (CheckReportResultType c : CheckReportResultType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
	}
	
	private CheckReportResultType(Integer code, String name) {
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
