/**
 * 
 */
package com.ucap.cloud_web.dtoResponse;

/**描述： 
 * 包：com.ucap.cloud_web.dtoResponse
 * 文件名称：CrmProductsResponse
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年3月8日下午5:21:14 
 * @version V1.0
 */
public class CrmProductsResponse {

	private Integer id;

	private String productCode; // 产品编码

	private String siteCode;

	private String beginTime;

	private String endTime;

	private Integer isSearchTb; // 填报单位是否能够查看详细信息（1.可以查看 ; 2.不能查看）

	private Integer servicePeriodId; // 服务周期id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getIsSearchTb() {
		return isSearchTb;
	}

	public void setIsSearchTb(Integer isSearchTb) {
		this.isSearchTb = isSearchTb;
	}

	public Integer getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

}
