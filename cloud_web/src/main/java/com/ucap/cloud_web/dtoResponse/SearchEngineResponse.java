/**
 * 
 */
package com.ucap.cloud_web.dtoResponse;

/**描述： 
 * 包：com.ucap.cloud_web.dtoResponse
 * 文件名称：SearchEngineResponse
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年3月24日上午10:32:26 
 * @version V1.0
 */
public class SearchEngineResponse {

	private String siteCode;

	private String name;

	private String url;

	private String jumpUrl;

	private Integer layerType; // 层级类型；（-1默认无用,1本级门户，2本级部门，3下属单位，6其他）

	private Integer baiduSlWebsite; // 百度收录数_站点

	private Integer baiduSlDomainsite; // 百度收录数_域

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public Integer getLayerType() {
		return layerType;
	}

	public void setLayerType(Integer layerType) {
		this.layerType = layerType;
	}

	public Integer getBaiduSlWebsite() {
		return baiduSlWebsite;
	}

	public void setBaiduSlWebsite(Integer baiduSlWebsite) {
		this.baiduSlWebsite = baiduSlWebsite;
	}

	public Integer getBaiduSlDomainsite() {
		return baiduSlDomainsite;
	}

	public void setBaiduSlDomainsite(Integer baiduSlDomainsite) {
		this.baiduSlDomainsite = baiduSlDomainsite;
	}

}
