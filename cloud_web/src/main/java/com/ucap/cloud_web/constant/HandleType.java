/**
 * 
 */
package com.ucap.cloud_web.constant;

/**描述： 
 * 包：com.ucap.cloud_web.constant
 * 文件名称：HandleType
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年5月3日下午5:48:19 
 * @version V1.0
 */
public enum HandleType {
	MINUSONE(-1,"未处理"),
	ZERO(0,"受理"),
	ONE(1,"已办结"),
	TWO(2,"不予受理(无效)"),
	THREE(3,"不予受理"),
	FOUR(4,"线下受理"),
	FIVE(5,"已处理待审核"),
	SIX(6,"初审驳回"),
	SEVEN(7,"终审待审核"),
	EIGHT(8,"退回重新处理"),
	NINE(9,"退回重新审核"),
	TEN(10,"不予受理（业务问题）"),
	ELEVEN(11,"不予受理(不属实)"),
	TWELVE(12,"不予受理（不在普查范围）"),
	THIRTEEN(13,"不再受理(重复)"),
	FOURTEEN(14,"已转办（未曝光但已经受理）"),
	FIFTEEN(15,"未曝光已办结"),
	SIXTEEN(16,"已转办（运维组是已转办，省部级组织单位是待转办）"); 
	
	private Integer code;
	private String name;
	
	public static String getNameByCode(Integer code) {
		for (HandleType c : HandleType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
	}
	
	private HandleType(Integer code, String name) {
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
