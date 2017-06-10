package com.ucap.cloud_web.constant;

import org.apache.commons.lang.StringUtils;
/**
 * 描述： 日常监测大数据图表单选按钮类型
 * 包：com.ucap.cloud_web.constant
 * 文件名称：BigDataDailyRadioType
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2017-3-7下午1:29:05 
 * @version V1.0
 */
public enum BigDataDailyRadioType {
	//监测不连通(占比)
	LINKERRSITEPROP("2", "linkerrsiteprop"),
	//首页死链(平均数)（组织单位table用得到）
	INDEXDEADNUMAVG("3", "indexdeadnum"),
	//首页不更新(网站数)
	NOUPDATESITENUM("4", "noupdatesitenum"),
	//首页不更新（占比）
	NOUPDATESITEPROP("5","noupdatesiteprop"),
	//平均更新量(条/站)
	AVEUPDATENUM("6","aveupdatenum"),
	//监测更新量(条)
	UPDATENUM("7","updatenum"),
	//首页未更新天数（天）
	NOUPDATEDAY("8","noupdateday"),
	//首页死链（个）（填报单位table用得到）
	INDEXDEADNUM("9", "indexdeadnum"),
	//监测不连通率(占比)
	LINKERRPROP("10", "linkerrprop"),
	//健康指数
	HEALTHINDEX("11","healthindex");
	
	private String code;
	private String name;
	
	private BigDataDailyRadioType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static String getNameByCode(String code) {
		for (BigDataDailyRadioType c : BigDataDailyRadioType.values()) {
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
