package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:15:59 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SpotCheckInfo {


	//抽查服务统计表id	private int id;
	//网站标识码	private String siteCode;
	//合同信息表id
	private int contractInfoId;
	//抽查总次数	private int spotSum;
	//已抽查数	private int spotNum;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	
	public int getContractInfoId() {
		return contractInfoId;
	}

	public void setContractInfoId(int contractInfoId) {
		this.contractInfoId = contractInfoId;
	}

	/** set 抽查服务统计表id */	public void setId(int id){
		this.id=id;
	}
	/** get 抽查服务统计表id */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 抽查总次数 */	public void setSpotSum(int spotSum){
		this.spotSum=spotSum;
	}
	/** get 抽查总次数 */	public int getSpotSum(){
		return spotSum;
	}
	/** set 已抽查数 */	public void setSpotNum(int spotNum){
		this.spotNum=spotNum;
	}
	/** get 已抽查数 */	public int getSpotNum(){
		return spotNum;
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

