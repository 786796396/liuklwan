package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class SystemCode {


	//系统代码自动ID	private int codeId;
	//系统代码编码	private String codeMyid;
	//系统代码名称	private String name;
	//系统代码排序	private int sort;
	//M模块 D词典	private String type;
	//	private String iconcls;
	//	private String state;
	//父项模块ID	private int permissionid;
	//系统代码父项ID	private int parentId;
	//备注	private String description;
	//状态	private String status;
	//创造日期	private Date created;
	//修改日期	private Date lastmod;
	//创建人	private int creater;
	//修改人	private int modifyer;
	/** set 系统代码自动ID */	public void setCodeId(int codeId){
		this.codeId=codeId;
	}
	/** get 系统代码自动ID */	public int getCodeId(){
		return codeId;
	}
	/** set 系统代码编码 */	public void setCodeMyid(String codeMyid){
		this.codeMyid=codeMyid;
	}
	/** get 系统代码编码 */	public String getCodeMyid(){
		return codeMyid;
	}
	/** set 系统代码名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 系统代码名称 */	public String getName(){
		return name;
	}
	/** set 系统代码排序 */	public void setSort(int sort){
		this.sort=sort;
	}
	/** get 系统代码排序 */	public int getSort(){
		return sort;
	}
	/** set M模块 D词典 */	public void setType(String type){
		this.type=type;
	}
	/** get M模块 D词典 */	public String getType(){
		return type;
	}
	/** set  */	public void setIconcls(String iconcls){
		this.iconcls=iconcls;
	}
	/** get  */	public String getIconcls(){
		return iconcls;
	}
	/** set  */	public void setState(String state){
		this.state=state;
	}
	/** get  */	public String getState(){
		return state;
	}
	/** set 父项模块ID */	public void setPermissionid(int permissionid){
		this.permissionid=permissionid;
	}
	/** get 父项模块ID */	public int getPermissionid(){
		return permissionid;
	}
	/** set 系统代码父项ID */	public void setParentId(int parentId){
		this.parentId=parentId;
	}
	/** get 系统代码父项ID */	public int getParentId(){
		return parentId;
	}
	/** set 备注 */	public void setDescription(String description){
		this.description=description;
	}
	/** get 备注 */	public String getDescription(){
		return description;
	}
	/** set 状态 */	public void setStatus(String status){
		this.status=status;
	}
	/** get 状态 */	public String getStatus(){
		return status;
	}
	/** set 创造日期 */	public void setCreated(Date created){
		this.created=created;
	}
	/** get 创造日期 */	public Date getCreated(){
		return created;
	}
	/** set 修改日期 */	public void setLastmod(Date lastmod){
		this.lastmod=lastmod;
	}
	/** get 修改日期 */	public Date getLastmod(){
		return lastmod;
	}
	/** set 创建人 */	public void setCreater(int creater){
		this.creater=creater;
	}
	/** get 创建人 */	public int getCreater(){
		return creater;
	}
	/** set 修改人 */	public void setModifyer(int modifyer){
		this.modifyer=modifyer;
	}
	/** get 修改人 */	public int getModifyer(){
		return modifyer;
	}
}

