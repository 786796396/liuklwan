/**
 * 
 */
package com.ucap.cloud_web.dtoResponse;

/**描述： 
 * 包：com.ucap.cloud_web.dtoResponse
 * 文件名称：JcVisitSitecodeResponse
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年3月22日下午6:30:53 
 * @version V1.0
 */
public class JcVisitSitecodeResponse {

	private String siteCode;

	private String name;

	private String url;

	private String jumpUrl;

	private Integer layerType; // 层级类型；（-1默认无用,1本级门户，2本级部门，3下属单位，6其他）

	private Integer urlCnt; // 浏览量

	private Integer ipCnt; // 访客量

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

	public Integer getUrlCnt() {
		return urlCnt;
	}

	public void setUrlCnt(Integer urlCnt) {
		this.urlCnt = urlCnt;
	}

	public Integer getIpCnt() {
		return ipCnt;
	}

	public void setIpCnt(Integer ipCnt) {
		this.ipCnt = ipCnt;
	}

}
