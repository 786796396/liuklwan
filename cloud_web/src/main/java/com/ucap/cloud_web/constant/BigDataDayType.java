package com.ucap.cloud_web.constant;

import org.apache.commons.lang.StringUtils;

/**
 * 描述： 日常监测统计  大数据  时间 下拉框
 * 包：com.ucap.cloud_web.constant
 * 文件名称：bigDataDayType
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-11-29下午2:53:32 
 * @version V1.0
 */
public enum BigDataDayType {
	
	//昨天
	DAYNUM("1", "1"),
	//7天
	DAYNUM7("7", "7"),
	//l4天
	DAYNUM14("14", "14"),
	//30天
	DAYNUM30("30", "30");

    
    private String code;
	private String name;

	private BigDataDayType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getNameByCode(String code) {
		for (BigDataDayType c : BigDataDayType.values()) {
			if (StringUtils.equals(c.getCode(), code)) {
				return c.getName();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
}
