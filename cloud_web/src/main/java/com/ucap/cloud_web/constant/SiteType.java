package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>栏目类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ChannelType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-13下午3:40:28 </p>
 */
public enum SiteType {
	/**
	 * 省门户
	 */
	SHENG_MENHU(1,"省部级政府门户"),
	/**
	 * 省部门
	 */
	SHENG_LEVAL(2,"省级政府部门网站（含参公事业单位、部委子站）"),
	/**
	 * 市门户
	 */
	SHI_MENHU(3,"市级政府门户"),
	/**
	 * 市部门
	 */
	SHI_LEVAL(4,"地市级政府部门网站（含参公事业单位）"),
	/**
	 * 县门户
	 */
	XIAN_MENHU(5,"县级政府门户（不设区的市，按镇计算）"),
	/**
	 * 县政府
	 */
	XIAN_LEVAL(6,"县级政府部门网站（含参公事业单位）"),
	/**
	 * 乡镇街道网站
	 */
	XIANG_ZHEN(7,"乡镇街道网站");
	
	
	private Integer code;
	private String name;
	
	private SiteType(Integer code, String name) {
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
