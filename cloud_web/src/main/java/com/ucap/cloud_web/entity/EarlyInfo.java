package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 10:47:46 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class EarlyInfo {


	//预警表id	private int id;
	//网站标识码	private String siteCode;
	//网站名称	private String siteName;
	//预警级别	private String earlyLevel;
	//新预警数	private int newEarlyNum;
	//预警总数	private int earlySum;

	// 预警类型：1首页连通性，2首页不更新，3栏目不更新，4空白栏目，5互动回应，6内容正确性，7升级改版或者临时关停
	private Integer type;
	
	//升级改版或者临时关停类型 1.首页连不通比例超过80%，2关键栏目连不通比例超过80%，3网站首页更新量为0，4人工检查时发现，5插码挂码
	private Integer updateGradeType;
	
	//检查类型（0：抽查，1：正常合同）
	private Integer checkType;
		//查看状态（0否、1是）	private int state;
	//创建时间	private Date createTime;
	
	private int servicePeriodId;//服务周期id
	
	
	
	public int getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(int servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public Integer getUpdateGradeType() {
		return updateGradeType;
	}

	public void setUpdateGradeType(Integer updateGradeType) {
		this.updateGradeType = updateGradeType;
	}

	/** set 预警表id */	public void setId(int id){
		this.id=id;
	}
	/** get 预警表id */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 网站名称 */	public void setSiteName(String siteName){
		this.siteName=siteName;
	}
	/** get 网站名称 */	public String getSiteName(){
		return siteName;
	}
	/** set 预警级别 */	public void setEarlyLevel(String earlyLevel){
		this.earlyLevel=earlyLevel;
	}
	/** get 预警级别 */	public String getEarlyLevel(){
		return earlyLevel;
	}
	/** set 新预警数 */	public void setNewEarlyNum(int newEarlyNum){
		this.newEarlyNum=newEarlyNum;
	}
	/** get 新预警数 */	public int getNewEarlyNum(){
		return newEarlyNum;
	}
	/** set 预警总数 */	public void setEarlySum(int earlySum){
		this.earlySum=earlySum;
	}
	/** get 预警总数 */	public int getEarlySum(){
		return earlySum;
	}

	/** get 类型：1网站连通性，2内容保障问题，3内容正确性 */
	public Integer getType() {
		return type;
	}

	/** set 类型：1网站连通性，2内容保障问题，3内容正确性 */
	public void setType(Integer type) {
		this.type = type;
	}
		/** set 查看状态（0否、1是） */	public void setState(int state){
		this.state=state;
	}
	/** get 查看状态（0否、1是） */	public int getState(){
		return state;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

