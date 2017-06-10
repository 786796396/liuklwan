package com.ucap.cloud_web.constant;


/**
 * <p>Description: 升级改版或者临时关停类型 </p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: EarlyUpdateGradeType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2016-6-13下午2:22:31 </p>
 * 
 * 升级改版或者临时关停类型 1.首页连不通比例超过80%，2关键栏目连不通比例超过80%，3网站首页更新量为0，4人工检查时发现，5插码挂码
 */
public enum EarlyUpdateGradeType {
	/**
	 * 首页连不通比例超过80%
	 */
	CON_HOME(1,"首页连不通比例超过80%"),
	/**
	 * 键栏目连不通比例超过80%
	 */
	POINT_CHANNEL(2,"键栏目连不通比例超过80%"),
	/**
	 * 网站首页更新量为0
	 */
	SITE_UPDATE(3,"网站首页更新量为0"),
	/**
	 * 人工检查时发现
	 */
	PERSON_CHECK(4,"人工检查时发现"),
	/**
	 * 插码挂码
	 */
	INSERT_CODE(5,"插码挂码");
	
	
	private Integer code;
	private String name;
	private EarlyUpdateGradeType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static String getNameByCode(Integer code) {
		for (EarlyUpdateGradeType c : EarlyUpdateGradeType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
	}
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
