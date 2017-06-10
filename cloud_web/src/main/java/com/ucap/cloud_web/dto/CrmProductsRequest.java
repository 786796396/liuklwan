package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;


/*** 前台页面传递基础数据<br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-08 11:53:59 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@SuppressWarnings("serial")public class CrmProductsRequest extends Query {

	private String siteCode;// 网站标识码

	private String nowTime;// 现在时间

	private Integer isSearchTb;// 填报单位是否能够查看详细信息（1-可以查看 2-不能查看）

	private Integer productType; // 产品分类（可随时增加）:1.全面检测 2.抽查

	private Integer[] productTypeArr; // 产品分类（可随时增加）:1.全面检测 2.抽查

	private Integer executeStatus; // 执行状态 作废：-1，初始化：0，服务中：1，服务结束：2

	private Integer notExecuteStatus; // 执行状态 作废：-1，初始化：0，服务中：1，服务结束：2

	private Integer[] executeStatusArr; // 执行状态 作废：-1，初始化：0，服务中：1，服务结束：2

	private Integer orgFlag; // 组织单位长度 1

	private Integer tbFlag; // 填报单位长度 2

	private Integer servicePdId; // 服务周期id

	private Integer isLink; // 是否使用link 1

	private Integer isExp; // 1正常，2例外，3关停
	
	private Integer orderTypes;// 1云监管订单/2云监测订单/3云安全订单/4云搜索订单/5本地部署订单

	private Integer protoContractCode; // 第一次初始化的时候需要把云监管现有的合同转化成销售易的订单;合同号也返回

	private List<DatabaseTreeInfo> databaseTreeList;


	public Integer getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(Integer orderTypes) {
		this.orderTypes = orderTypes;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getNotExecuteStatus() {
		return notExecuteStatus;
	}

	public void setNotExecuteStatus(Integer notExecuteStatus) {
		this.notExecuteStatus = notExecuteStatus;
	}

	public Integer getExecuteStatus() {
		return executeStatus;
	}

	public void setExecuteStatus(Integer executeStatus) {
		this.executeStatus = executeStatus;
	}

	public Integer[] getProductTypeArr() {
		return productTypeArr;
	}

	public void setProductTypeArr(Integer[] productTypeArr) {
		this.productTypeArr = productTypeArr;
	}

	public Integer[] getExecuteStatusArr() {
		return executeStatusArr;
	}

	public void setExecuteStatusArr(Integer[] executeStatusArr) {
		this.executeStatusArr = executeStatusArr;
	}

	public Integer getIsSearchTb() {
		return isSearchTb;
	}

	public void setIsSearchTb(Integer isSearchTb) {
		this.isSearchTb = isSearchTb;
	}

	public List<DatabaseTreeInfo> getDatabaseTreeList() {
		return databaseTreeList;
	}

	public void setDatabaseTreeList(List<DatabaseTreeInfo> databaseTreeList) {
		this.databaseTreeList = databaseTreeList;
	}

	public Integer getOrgFlag() {
		return orgFlag;
	}

	public void setOrgFlag(Integer orgFlag) {
		this.orgFlag = orgFlag;
	}

	public Integer getTbFlag() {
		return tbFlag;
	}

	public void setTbFlag(Integer tbFlag) {
		this.tbFlag = tbFlag;
	}

	public Integer getServicePdId() {
		return servicePdId;
	}

	public void setServicePdId(Integer servicePdId) {
		this.servicePdId = servicePdId;
	}

	public Integer getIsLink() {
		return isLink;
	}

	public void setIsLink(Integer isLink) {
		this.isLink = isLink;
	}

	public Integer getIsExp() {
		return isExp;
	}

	public void setIsExp(Integer isExp) {
		this.isExp = isExp;
	}

	public Integer getProtoContractCode() {
		return protoContractCode;
	}

	public void setProtoContractCode(Integer protoContractCode) {
		this.protoContractCode = protoContractCode;
	}

}

