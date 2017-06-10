package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-30 16:35:27 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SecurityHomeSituation {


	//主键	private Integer id;
	//站点标识码	private String siteCode;
	//服务周期ID	private Integer servicePeriodId;
	//最后更新日期	private String modifyDate;
	//发现/扫描日期	private String scanDate;
	//用户id	private Integer userId;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	//删除状态:1.删除，0.正常	private short delFlag;
	/** set 主键 */	public void setId(Integer id){
		this.id=id;
	}
	/** get 主键 */	public Integer getId(){
		return id;
	}
	/** set 站点标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 站点标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 服务周期ID */	public void setServicePeriodId(Integer servicePeriodId){
		this.servicePeriodId=servicePeriodId;
	}
	/** get 服务周期ID */	public Integer getServicePeriodId(){
		return servicePeriodId;
	}
	/** set 最后更新日期 */	public void setModifyDate(String modifyDate){
		this.modifyDate=modifyDate;
	}
	/** get 最后更新日期 */	public String getModifyDate(){
		return modifyDate;
	}
	/** set 发现/扫描日期 */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 发现/扫描日期 */	public String getScanDate(){
		return scanDate;
	}
	/** set 用户id */	public void setUserId(Integer userId){
		this.userId=userId;
	}
	/** get 用户id */	public Integer getUserId(){
		return userId;
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
	/** set 删除状态:1.删除，0.正常 */	public void setDelFlag(short delFlag){
		this.delFlag=delFlag;
	}
	/** get 删除状态:1.删除，0.正常 */	public short getDelFlag(){
		return delFlag;
	}
}

