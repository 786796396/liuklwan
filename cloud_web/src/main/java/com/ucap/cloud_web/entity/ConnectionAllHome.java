package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-30 15:18:32 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class ConnectionAllHome {


	//首页连通性表id	private int id;
	//网站标识码	private String siteCode;
	//网站名称	private String name;
	//首页url	private String url;
	//任务队列（1：5分钟、2：10分钟、3：15分钟、4：30分钟、5：60分钟、6：1天）	private int queue;
	
	//类别（1：首页，2：业务系统，3：关键栏目）
	private int type;
	
	//关键栏目数量/业务系统数量（非删除）
	private int channelNum;
		//扫描时间（yyyy-dd-mm）	private String scanDate;
	//总次数	private int connectionSum;
	//成功次数	private int successNum;
	//成功占比	private String successProportion;
	//超时次数	private int errorNum;
	//超时占比	private String errorProportion;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 首页连通性表id */	public void setId(int id){
		this.id=id;
	}
	/** get 首页连通性表id */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 网站名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 网站名称 */	public String getName(){
		return name;
	}
	/** set 首页url */	public void setUrl(String url){
		this.url=url;
	}
	/** get 首页url */	public String getUrl(){
		return url;
	}
	/** set 任务队列（1：5分钟、2：10分钟、3：15分钟、4：30分钟、5：60分钟、6：1天） */	public void setQueue(int queue){
		this.queue=queue;
	}
	/** get 任务队列（1：5分钟、2：10分钟、3：15分钟、4：30分钟、5：60分钟、6：1天） */	public int getQueue(){
		return queue;
	}
	/** set 扫描时间（yyyy-dd-mm） */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 扫描时间（yyyy-dd-mm） */	public String getScanDate(){
		return scanDate;
	}
	/** set 总次数 */	public void setConnectionSum(int connectionSum){
		this.connectionSum=connectionSum;
	}
	/** get 总次数 */	public int getConnectionSum(){
		return connectionSum;
	}
	/** set 成功次数 */	public void setSuccessNum(int successNum){
		this.successNum=successNum;
	}
	/** get 成功次数 */	public int getSuccessNum(){
		return successNum;
	}
	/** set 成功占比 */	public void setSuccessProportion(String successProportion){
		this.successProportion=successProportion;
	}
	/** get 成功占比 */	public String getSuccessProportion(){
		return successProportion;
	}
	/** set 超时次数 */	public void setErrorNum(int errorNum){
		this.errorNum=errorNum;
	}
	/** get 超时次数 */	public int getErrorNum(){
		return errorNum;
	}
	/** set 超时占比 */	public void setErrorProportion(String errorProportion){
		this.errorProportion=errorProportion;
	}
	/** get 超时占比 */	public String getErrorProportion(){
		return errorProportion;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(int channelNum) {
		this.channelNum = channelNum;
	}

}

