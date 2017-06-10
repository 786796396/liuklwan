/**
 * 
 */
package com.ucap.cloud_web.constant;

/**描述： 
 * 包：com.ucap.cloud_web.constant
 * 文件名称：MenuInfoType
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年3月28日下午3:43:29 
 * @version V1.0
 */
public enum MenuInfoType {
	ORGANIZATION(1, "组织"), 
	FILL(2, "填报"),
	TOP(1, "上面"),
	LEFT(2, "左面"),
	START(1, "启用"),
	DISABLE(2, "禁用");

	private Integer code;
	private String name;

	private MenuInfoType(Integer code, String name) {
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
