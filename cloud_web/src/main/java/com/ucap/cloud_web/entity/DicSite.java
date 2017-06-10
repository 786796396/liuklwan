package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-30 15:53:27 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class DicSite {


	//站点网站类型码表id	private int id;
	//站点网站类型编码（1,2,3,4,5,6）	private int siteTypeCode;
	//站点网站类型：1省部级政府门户，2省级政府部门网站（含参公事业单位、部委子站），3市级政府门户 4地市级政府部门网站（含参公事业单位） 5县级政府门户（不设区的市，按镇计算）6县级政府部门	private String remark;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 站点网站类型码表id */	public void setId(int id){
		this.id=id;
	}
	/** get 站点网站类型码表id */	public int getId(){
		return id;
	}
	/** set 站点网站类型编码（0,1,2,3,4,5） */	public void setSiteTypeCode(int siteTypeCode){
		this.siteTypeCode=siteTypeCode;
	}
	/** get 站点网站类型编码（0,1,2,3,4,5） */	public int getSiteTypeCode(){
		return siteTypeCode;
	}
	/** set 站点网站类型：0省部级政府门户，1省级政府部门网站（含参公事业单位、部委子站），2市级政府门户 3地市级政府部门网站（含参公事业单位） 4县级政府门户（不设区的市，按镇计算）5县级政府部门 */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 站点网站类型：0省部级政府门户，1省级政府部门网站（含参公事业单位、部委子站），2市级政府门户 3地市级政府部门网站（含参公事业单位） 4县级政府门户（不设区的市，按镇计算）5县级政府部门 */	public String getRemark(){
		return remark;
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

