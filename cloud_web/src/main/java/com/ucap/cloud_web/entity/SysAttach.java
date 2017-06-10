package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:24 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SysAttach {


	//	private int id;
	//表名	private String tbName;
	//表主键	private Integer tbKey;
	//表列名	private String tbCol;
	//保存路径	private String path;
	//访问路径	private String url;
	//文件后缀（不带 . 号）	private String suffix;
	//别名	private String aliasName;
	//添加人id	private int userId;
	//添加人id	private int modifyUserId;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 表名 */	public void setTbName(String tbName){
		this.tbName=tbName;
	}
	/** get 表名 */	public String getTbName(){
		return tbName;
	}
	public Integer getTbKey() {
		return tbKey;
	}

	public void setTbKey(Integer tbKey) {
		this.tbKey = tbKey;
	}

	/** set 表列名 */	public void setTbCol(String tbCol){
		this.tbCol=tbCol;
	}
	/** get 表列名 */	public String getTbCol(){
		return tbCol;
	}
	/** set 保存路径 */	public void setPath(String path){
		this.path=path;
	}
	/** get 保存路径 */	public String getPath(){
		return path;
	}
	/** set 访问路径 */	public void setUrl(String url){
		this.url=url;
	}
	/** get 访问路径 */	public String getUrl(){
		return url;
	}
	/** set 文件后缀（不带 . 号） */	public void setSuffix(String suffix){
		this.suffix=suffix;
	}
	/** get 文件后缀（不带 . 号） */	public String getSuffix(){
		return suffix;
	}
	/** set 别名 */	public void setAliasName(String aliasName){
		this.aliasName=aliasName;
	}
	/** get 别名 */	public String getAliasName(){
		return aliasName;
	}
	/** set 添加人id */	public void setUserId(int userId){
		this.userId=userId;
	}
	/** get 添加人id */	public int getUserId(){
		return userId;
	}
	/** set 添加人id */	public void setModifyUserId(int modifyUserId){
		this.modifyUserId=modifyUserId;
	}
	/** get 添加人id */	public int getModifyUserId(){
		return modifyUserId;
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

