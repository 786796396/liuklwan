/**
 * 
 */
package com.ucap.cloud_web.constant;

/**描述： 
 * 包：com.ucap.cloud_web.constant
 * 文件名称：CrmProductsType
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年3月8日下午6:03:56 
 * @version V1.0
 */
public enum CrmProductsType {

	DETECTION(1, "全面检测"), CHECK(2, "抽查"), SECURITY(3, "安全");

	private Integer code;
	private String name;

	private CrmProductsType(Integer code, String name) {
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
