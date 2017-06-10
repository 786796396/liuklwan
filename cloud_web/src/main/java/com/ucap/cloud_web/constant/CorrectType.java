package com.ucap.cloud_web.constant;

/**
 * <p>
 * Description:内容正确性类型
 * </p>
 * <p>
 * @Package：com.ucap.cloud_web.constant
 * </p>
 * <p>
 * Title: CorrectType
 * </p>
 * <p>
 * Company: 开普互联
 * </p>
 * <p>
 * @author：zhurk
 * </p>
 * <p>
 * @date：2015-12-9下午11:22:56
 * </p>
 */
public enum CorrectType {
	// 内容正确性类型（1：一般错误，2：疑似错误，3：严重错误）
	COMMON(1, "一般错误"), SUSPECTED(2, "疑似错误"), SERIOUS(3, "严重错误"), MANUAL(4, "手工");
	private Integer code;
	private String name;

	public static String getNameByCode(Integer code) {
		for (CorrectType c : CorrectType.values()) {
			if (c.getCode()==code) {
				return c.getName();
			}
		}
		return null;
	}
	
	private CorrectType(Integer code, String name) {
		this.code = code;
		this.name = name;
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
