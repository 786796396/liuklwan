/**
 * 
 */
package com.ucap.cloud_web.dtoResponse;

/**描述： 
 * 包：com.ucap.cloud_web.dtoResponse
 * 文件名称：SecurityBlankInfoResponse
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年3月7日上午11:42:43 
 * @version V1.0
 */
public class SecurityGuaranteeResponse {

	private String siteCode;
	private String name;
	private String homePageUrl;
	private String jumpPageUrl;
	private Integer isorganizational;
	private Integer columnNum; // 栏目个数
	private Integer securityFatalError;//问题个数
	private Integer layerType;
	private String layerTypeValue;
	
	public String getLayerTypeValue() {
		return layerTypeValue;
	}
	public void setLayerTypeValue(String layerTypeValue) {
		this.layerTypeValue = layerTypeValue;
	}
	public Integer getLayerType() {
		return layerType;
	}
	public void setLayerType(Integer layerType) {
		this.layerType = layerType;
	}
	public Integer getSecurityFatalError() {
		return securityFatalError;
	}
	public void setSecurityFatalError(Integer securityFatalError) {
		this.securityFatalError = securityFatalError;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

	public String getJumpPageUrl() {
		return jumpPageUrl;
	}

	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}
	public Integer getIsorganizational() {
		return isorganizational;
	}
	public void setIsorganizational(Integer isorganizational) {
		this.isorganizational = isorganizational;
	}

	public Integer getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(Integer columnNum) {
		this.columnNum = columnNum;
	}

}
