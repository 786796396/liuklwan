/**
 * 
 */
package com.ucap.cloud_web.dtoResponse;

/**描述： 
 * 包：com.ucap.cloud_web.dtoResponse
 * 文件名称：ConnectionAllHomeResponse
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年3月30日下午3:56:29 
 * @version V1.0
 */
public class ConnectionAllHomeResponse {

	// 网站标识码
	private String siteCode;
	private String siteName;
	private String homePageUrl;
	private String jumpPageUrl;
	// 任务队列（1：5分钟、2：10分钟、3：15分钟、4：30分钟、5：60分钟、6：1天）
	private Integer queue;
	// 扫描时间（yyyy-dd-mm）
	private String scanDate;
	// 总次数
	private Integer connectionSum;
	// 成功次数
	private Integer successNum;
	// 成功占比
	private String successProportion;
	// 超时次数
	private Integer errorNum;
	// 超时占比
	private String errorProportion;
//	关键栏目数量/业务系统数量（非删除）
	private Integer channelNum;

	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
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

	public Integer getQueue() {
		return queue;
	}

	public void setQueue(Integer queue) {
		this.queue = queue;
	}
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public Integer getConnectionSum() {
		return connectionSum;
	}

	public void setConnectionSum(Integer connectionSum) {
		this.connectionSum = connectionSum;
	}

	public Integer getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}
	public String getSuccessProportion() {
		return successProportion;
	}
	public void setSuccessProportion(String successProportion) {
		this.successProportion = successProportion;
	}

	public Integer getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}
	public String getErrorProportion() {
		return errorProportion;
	}
	public void setErrorProportion(String errorProportion) {
		this.errorProportion = errorProportion;
	}
	public Integer getChannelNum() {
		return channelNum;
	}
	public void setChannelNum(Integer channelNum) {
		this.channelNum = channelNum;
	}

}
