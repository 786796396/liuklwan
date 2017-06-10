package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-12-13 08:58:19 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class JcVisitOrg {


	//主键ID	private int id;
	//组织单位编码	private String siteCode;
	//挂标比例	private String labelingPercent;
	//访问量平均值	private int pvAvg;
	//访客量平均值	private int uvAvg;
	//访问量	private int pv;
	//访客量	private int uv;
	//扫描时间	private String scanDate;
	//挂标站点数	private int siteCnt;

	//名称
	private String name;	//	private Date createTime;
	//	private Date modifyTime;

		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** set 主键ID */	public void setId(int id){
		this.id=id;
	}
	/** get 主键ID */	public int getId(){
		return id;
	}
	/** set 组织单位编码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 组织单位编码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 挂标比例 */	public void setLabelingPercent(String labelingPercent){
		this.labelingPercent=labelingPercent;
	}
	/** get 挂标比例 */	public String getLabelingPercent(){
		return labelingPercent;
	}
	/** set 访问量平均值 */	public void setPvAvg(int pvAvg){
		this.pvAvg=pvAvg;
	}
	/** get 访问量平均值 */	public int getPvAvg(){
		return pvAvg;
	}
	/** set 访客量平均值 */	public void setUvAvg(int uvAvg){
		this.uvAvg=uvAvg;
	}
	/** get 访客量平均值 */	public int getUvAvg(){
		return uvAvg;
	}
	/** set 访问量 */	public void setPv(int pv){
		this.pv=pv;
	}
	/** get 访问量 */	public int getPv(){
		return pv;
	}
	/** set 访客量 */	public void setUv(int uv){
		this.uv=uv;
	}
	/** get 访客量 */	public int getUv(){
		return uv;
	}
	/** set 扫描时间 */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 扫描时间 */	public String getScanDate(){
		return scanDate;
	}
	/** set 挂标站点数 */	public void setSiteCnt(int siteCnt){
		this.siteCnt=siteCnt;
	}
	/** get 挂标站点数 */	public int getSiteCnt(){
		return siteCnt;
	}
	/** set  */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get  */	public Date getCreateTime(){
		return createTime;
	}
	/** set  */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get  */	public Date getModifyTime(){
		return modifyTime;
	}
}

