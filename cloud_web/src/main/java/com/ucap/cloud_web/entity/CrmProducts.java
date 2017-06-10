package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-08 11:53:59 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class CrmProducts {


	//主键	private int id;
	//订单id	private int orderId;
	//订单编码	private String orderCode;
	//产品id	private int productId;
	//产品编码	private String productCode;
	//产品名称	private String productName;
	//产品parentid	private int productPid;
	//产品parent Name	private String productPname;
	//产品分类（可随时增加）:1.全面检测 2.抽查	private int productType;
	//服务次数/抽查次数	private int checkCount;
	//剩余服务次数	private int remaindercount;
	//检测站点数	private int monitorCount;
	//是否校对错别字（0：否，1：是）is_correct	private Integer isCorrect;
	//作废：-1，初始化：0，服务中：1，服务结束：2	private Integer executeStatus;
	//填报单位是否能够查看详细信息（1.可以查看 ; 2.不能查看）	private Integer isSearchTb;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 主键 */	public void setId(int id){
		this.id=id;
	}
	/** get 主键 */	public int getId(){
		return id;
	}
	/** set 订单id */	public void setOrderId(int orderId){
		this.orderId=orderId;
	}
	/** get 订单id */	public int getOrderId(){
		return orderId;
	}
	/** set 订单编码 */	public void setOrderCode(String orderCode){
		this.orderCode=orderCode;
	}
	/** get 订单编码 */	public String getOrderCode(){
		return orderCode;
	}
	/** set 产品id */	public void setProductId(int productId){
		this.productId=productId;
	}
	/** get 产品id */	public int getProductId(){
		return productId;
	}
	/** set 产品编码 */	public void setProductCode(String productCode){
		this.productCode=productCode;
	}
	/** get 产品编码 */	public String getProductCode(){
		return productCode;
	}
	/** set 产品名称 */	public void setProductName(String productName){
		this.productName=productName;
	}
	/** get 产品名称 */	public String getProductName(){
		return productName;
	}
	/** set 产品parentid */	public void setProductPid(int productPid){
		this.productPid=productPid;
	}
	/** get 产品parentid */	public int getProductPid(){
		return productPid;
	}
	/** set 产品parent Name */	public void setProductPname(String productPname){
		this.productPname=productPname;
	}
	/** get 产品parent Name */	public String getProductPname(){
		return productPname;
	}
	/** set 产品分类（可随时增加）:1.全面检测 2.抽查 */	public void setProductType(int productType){
		this.productType=productType;
	}
	/** get 产品分类（可随时增加）:1.全面检测 2.抽查 */	public int getProductType(){
		return productType;
	}
	/** set 服务次数/抽查次数 */	public void setCheckCount(int checkCount){
		this.checkCount=checkCount;
	}
	/** get 服务次数/抽查次数 */	public int getCheckCount(){
		return checkCount;
	}
	/** set 剩余服务次数 */	public void setRemaindercount(int remaindercount){
		this.remaindercount=remaindercount;
	}
	/** get 剩余服务次数 */	public int getRemaindercount(){
		return remaindercount;
	}
	/** set 检测站点数 */	public void setMonitorCount(int monitorCount){
		this.monitorCount=monitorCount;
	}
	/** get 检测站点数 */	public int getMonitorCount(){
		return monitorCount;
	}
	/** set 是否校对错别字（0：否，1：是）is_correct */	public void setIsCorrect(Integer isCorrect) {
		this.isCorrect=isCorrect;
	}
	/** get 是否校对错别字（0：否，1：是）is_correct */	public Integer getIsCorrect() {
		return isCorrect;
	}
	/** set 作废：-1，初始化：0，服务中：1，服务结束：2 */	public void setExecuteStatus(Integer executeStatus) {
		this.executeStatus=executeStatus;
	}
	/** get 作废：-1，初始化：0，服务中：1，服务结束：2 */	public Integer getExecuteStatus() {
		return executeStatus;
	}
	/** set 填报单位是否能够查看详细信息（1.可以查看 ; 2.不能查看） */	public void setIsSearchTb(Integer isSearchTb) {
		this.isSearchTb=isSearchTb;
	}
	/** get 填报单位是否能够查看详细信息（1.可以查看 ; 2.不能查看） */	public Integer getIsSearchTb() {
		return isSearchTb;
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

