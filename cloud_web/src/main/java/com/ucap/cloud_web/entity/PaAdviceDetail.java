package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:22 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaAdviceDetail {


	//	private int id;
	//留言表id	private int adviceId;
	//内容	private String content;
	//留言状态 2：未读，1：已读	private short state;
	//状态 1：前台留言，2：后台留言	private short adviceState;
	//回复人id	private int userId;
	//留言人/回复人姓名	private String name;
	//创建时间	private String createTime;
	//修改时间	private Date modifyTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 留言表id */	public void setAdviceId(int adviceId){
		this.adviceId=adviceId;
	}
	/** get 留言表id */	public int getAdviceId(){
		return adviceId;
	}
	/** set 内容 */	public void setContent(String content){
		this.content=content;
	}
	/** get 内容 */	public String getContent(){
		return content;
	}
	/** set 留言状态 2：未读，1：已读 */	public void setState(short state){
		this.state=state;
	}
	/** get 留言状态 2：未读，1：已读 */	public short getState(){
		return state;
	}
	/** set 状态 1：前台留言，2：后台留言 */	public void setAdviceState(short adviceState){
		this.adviceState=adviceState;
	}
	/** get 状态 1：前台留言，2：后台留言 */	public short getAdviceState(){
		return adviceState;
	}
	/** set 回复人id */	public void setUserId(int userId){
		this.userId=userId;
	}
	/** get 回复人id */	public int getUserId(){
		return userId;
	}
	/** set 留言人/回复人姓名 */	public void setName(String name){
		this.name=name;
	}
	/** get 留言人/回复人姓名 */	public String getName(){
		return name;
	}
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}
}

