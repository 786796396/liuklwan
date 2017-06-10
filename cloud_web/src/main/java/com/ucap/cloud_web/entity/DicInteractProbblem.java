package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 10:46:00 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class DicInteractProbblem {


	//互动回应差问题类型码表id	private int id;
	//问题类型编码(0、1、2、3、4、5、6)	private int nteractProbblemCode;
	//问题类型说明：0栏目未开设、1栏目不可用、21年内无有效信息和留言、3留言超过3个月未回复、41年内未开展活动、51年内开展活动次数少、6其他	private String remark;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 互动回应差问题类型码表id */	public void setId(int id){
		this.id=id;
	}
	/** get 互动回应差问题类型码表id */	public int getId(){
		return id;
	}
	/** set 问题类型编码(0、1、2、3、4、5、6) */	public void setNteractProbblemCode(int nteractProbblemCode){
		this.nteractProbblemCode=nteractProbblemCode;
	}
	/** get 问题类型编码(0、1、2、3、4、5、6) */	public int getNteractProbblemCode(){
		return nteractProbblemCode;
	}
	/** set 问题类型说明：0栏目未开设、1栏目不可用、21年内无有效信息和留言、3留言超过3个月未回复、41年内未开展活动、51年内开展活动次数少、6其他 */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 问题类型说明：0栏目未开设、1栏目不可用、21年内无有效信息和留言、3留言超过3个月未回复、41年内未开展活动、51年内开展活动次数少、6其他 */	public String getRemark(){
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

