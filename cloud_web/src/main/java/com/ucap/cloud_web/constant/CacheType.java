/**
 * 
 */
package com.ucap.cloud_web.constant;

import org.apache.commons.lang.StringUtils;

/**描述： 缓存变量
 * 包：com.ucap.cloud_web.constant
 * 文件名称：CacheType
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年1月4日下午2:51:07 
 * @version V1.0
 */
public enum CacheType {

	MONITORING_DATABASEINFO("1","monitoringDatabaseInfo"),
	MONITORING_DATABASETREEINFO("2","monitoringDatabaseTreeInfo"),
	MONITORING_CONNECTIONALL("3","monitoringConnectionAll"),
	MONITORING_CONNECTIONALLEXCEL("4","monitoringConnectionAllExcel"),
	MONITORING_LINKHOMETREND("5","monitoringLinkHomeTrend"),
	MONITORING_LINKHOMETRENDEXCEL("6","monitoringLinkHomeTrendExcel"),
	MONITORING_SECURITYHOME("7","monitoringSecurityHome"),
	MONITORING_SECURITYHOMEEXCEL("8","monitoringSecurityHomeExcel"),
	MONITORING_CORRECTCONTENT("9","monitoringCorrectContent"),
	MONITORING_CORRECTCONTENTEXCEL("10","monitoringCorrectContentExcel"),
	MONITORING_UPDATEHOME("11","monitoringUpdateHome"),
	MONITORING_UPDATEHOMEEXCEL("12","monitoringUpdateHomeExcel"),
	MONITORING_COLUMNNUM("13","monitoringColumnNum"),
	MONITORING_SECURITYGUARANTEE_DATABASEINFO("14","monitoringSecurityGuaranteeDatabaseInfo"),
	MONITORING_SECURITYGUARANTEE_DATABASEALLLIST("15","monitoringSecurityGuaranteeDatabaseAllList"),
	SITEDATAOVERVIEW_GETINFOSTATES("16","sitedataoverview_getinfostates"),
	EARLY_GETDATAS("17","early_getdatas"),
	DEPTHLINKALL_TABLELIST("18","DepthLinkAllTableList"),
	DEPTHLINKALL_DATABASELIST("19","DepthLinkAllDatabaseList"),
	MENUINFO_TOP("20","menuInfoTop"),
	MENUINFO_TWO("21","menuInfoTwo"),
	MENUINFO_THREE("22","menuInfoThree");
	
	private String code;
	private String name;

	private CacheType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getNameByCode(String code) {
		for (CacheType c : CacheType.values()) {
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
