package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-28 13:59:38 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class MenuInfo {


	//	private int id;
	// 父级编号	private int parentId;
	//所有父级编号	private String parentIds;
	//1 有下级菜单  0 没有下级菜单	private int isParent;
	//模块名称	private String name;
	//图标地址	private String imgUrl;
	//功能地址	private String gUrl;
	//1组织 2填报	private int menuType;
	//区别是上面还是左侧 1: top 2: left	private int positionType;
	//试用产品  1  是  2  否	private int trialProduct;
	//试用产品类型  	private int productType;
	//是否携带参数   1 是  2 否	private int valueOf;
	//排序	private int orderNum;
	//1启用 2禁用	private int isDisable;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	//添加人	private String createBy;
	//修改人	private String modifyBy;
	
	//唯一值
	private String onlyValue;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set  父级编号 */	public void setParentId(int parentId){
		this.parentId=parentId;
	}
	/** get  父级编号 */	public int getParentId(){
		return parentId;
	}
	/** set 所有父级编号 */	public void setParentIds(String parentIds){
		this.parentIds=parentIds;
	}
	/** get 所有父级编号 */	public String getParentIds(){
		return parentIds;
	}
	/** set 1 有下级菜单  0 没有下级菜单 */	public void setIsParent(int isParent){
		this.isParent=isParent;
	}
	/** get 1 有下级菜单  0 没有下级菜单 */	public int getIsParent(){
		return isParent;
	}
	/** set 模块名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 模块名称 */	public String getName(){
		return name;
	}
	/** set 图标地址 */	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	/** get 图标地址 */	public String getImgUrl(){
		return imgUrl;
	}
	/** set 功能地址 */	public void setGUrl(String gUrl){
		this.gUrl=gUrl;
	}
	/** get 功能地址 */	public String getGUrl(){
		return gUrl;
	}
	/** set 1组织 2填报 */	public void setMenuType(int menuType){
		this.menuType=menuType;
	}
	/** get 1组织 2填报 */	public int getMenuType(){
		return menuType;
	}
	/** set 区别是上面还是左侧 1: top 2: left */	public void setPositionType(int positionType){
		this.positionType=positionType;
	}
	/** get 区别是上面还是左侧 1: top 2: left */	public int getPositionType(){
		return positionType;
	}
	/** set 试用产品  1  是  2  否 */	public void setTrialProduct(int trialProduct){
		this.trialProduct=trialProduct;
	}
	/** get 试用产品  1  是  2  否 */	public int getTrialProduct(){
		return trialProduct;
	}
	/** set 试用产品类型   */	public void setProductType(int productType){
		this.productType=productType;
	}
	/** get 试用产品类型   */	public int getProductType(){
		return productType;
	}
	/** set 是否携带参数   1 是  2 否 */	public void setValueOf(int valueOf){
		this.valueOf=valueOf;
	}
	/** get 是否携带参数   1 是  2 否 */	public int getValueOf(){
		return valueOf;
	}
	/** set 排序 */	public void setOrderNum(int orderNum){
		this.orderNum=orderNum;
	}
	/** get 排序 */	public int getOrderNum(){
		return orderNum;
	}
	/** set 1启用 2禁用 */	public void setIsDisable(int isDisable){
		this.isDisable=isDisable;
	}
	/** get 1启用 2禁用 */	public int getIsDisable(){
		return isDisable;
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
	/** set 添加人 */	public void setCreateBy(String createBy){
		this.createBy=createBy;
	}
	/** get 添加人 */	public String getCreateBy(){
		return createBy;
	}
	/** set 修改人 */	public void setModifyBy(String modifyBy){
		this.modifyBy=modifyBy;
	}
	/** get 修改人 */	public String getModifyBy(){
		return modifyBy;
	}

	public String getOnlyValue()
	{
		return onlyValue;
	}

	public void setOnlyValue(String onlyValue)
	{
		this.onlyValue = onlyValue;
	}
}

