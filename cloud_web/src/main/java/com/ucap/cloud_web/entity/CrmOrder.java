package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-08 11:52:34 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class CrmOrder {


	//主键	private int id;
	//订单接口返回的 id	private int orderId;
	//订单编号	private String orderCode;
	//站点标识码	private String siteCode;
	//组织/填报单位名称	private String siteName;
	//订单开始时间	private String orderBeginTime;
	//订单结束时间	private String orderEndTime;
	//1未生效/2已生效/3已作废/4临时开通/5已关停	private int orderStatus;
	//1云监管订单/2云监测订单/3云安全订单/4云搜索订单/5本地部署订单	private int orderTypes;
	//1锁定/2未锁定	private short lockStatus;
	//1正式订单/2试用订单	private short orderType;
	//订单所有人	private int ownerId;
	//销售姓名	private String saleName;
	//备注（作废理由）	private String remark;
	//第一次初始化的时候需要把云监管现有的合同转化成销售易的订单;合同号也返回	private String protoContractCode;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 主键 */	public void setId(int id){
		this.id=id;
	}
	/** get 主键 */	public int getId(){
		return id;
	}
	/** set 订单接口返回的 id */	public void setOrderId(int orderId){
		this.orderId=orderId;
	}
	/** get 订单接口返回的 id */	public int getOrderId(){
		return orderId;
	}
	/** set 订单编号 */	public void setOrderCode(String orderCode){
		this.orderCode=orderCode;
	}
	/** get 订单编号 */	public String getOrderCode(){
		return orderCode;
	}
	/** set 站点标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 站点标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 组织/填报单位名称 */	public void setSiteName(String siteName){
		this.siteName=siteName;
	}
	/** get 组织/填报单位名称 */	public String getSiteName(){
		return siteName;
	}
	/** set 订单开始时间 */	public void setOrderBeginTime(String orderBeginTime){
		this.orderBeginTime=orderBeginTime;
	}
	/** get 订单开始时间 */	public String getOrderBeginTime(){
		return orderBeginTime;
	}
	/** set 订单结束时间 */	public void setOrderEndTime(String orderEndTime){
		this.orderEndTime=orderEndTime;
	}
	/** get 订单结束时间 */	public String getOrderEndTime(){
		return orderEndTime;
	}
	/** set 1未生效/2已生效/3已作废/4临时开通/5已关停 */	public void setOrderStatus(int orderStatus){
		this.orderStatus=orderStatus;
	}
	/** get 1未生效/2已生效/3已作废/4临时开通/5已关停 */	public int getOrderStatus(){
		return orderStatus;
	}
	/** set 1云监管订单/2云监测订单/3云安全订单/4云搜索订单/5本地部署订单 */	public void setOrderTypes(int orderTypes){
		this.orderTypes=orderTypes;
	}
	/** get 1云监管订单/2云监测订单/3云安全订单/4云搜索订单/5本地部署订单 */	public int getOrderTypes(){
		return orderTypes;
	}
	/** set 1锁定/2未锁定 */	public void setLockStatus(short lockStatus){
		this.lockStatus=lockStatus;
	}
	/** get 1锁定/2未锁定 */	public short getLockStatus(){
		return lockStatus;
	}
	/** set 1正式订单/2试用订单 */	public void setOrderType(short orderType){
		this.orderType=orderType;
	}
	/** get 1正式订单/2试用订单 */	public short getOrderType(){
		return orderType;
	}
	/** set 订单所有人 */	public void setOwnerId(int ownerId){
		this.ownerId=ownerId;
	}
	/** get 订单所有人 */	public int getOwnerId(){
		return ownerId;
	}
	/** set 销售姓名 */	public void setSaleName(String saleName){
		this.saleName=saleName;
	}
	/** get 销售姓名 */	public String getSaleName(){
		return saleName;
	}
	/** set 备注（作废理由） */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 备注（作废理由） */	public String getRemark(){
		return remark;
	}
	/** set 第一次初始化的时候需要把云监管现有的合同转化成销售易的订单;合同号也返回 */	public void setProtoContractCode(String protoContractCode){
		this.protoContractCode=protoContractCode;
	}
	/** get 第一次初始化的时候需要把云监管现有的合同转化成销售易的订单;合同号也返回 */	public String getProtoContractCode(){
		return protoContractCode;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}
}

