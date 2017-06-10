package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-02 19:42:09 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class AccountBindInfoLog {


	//	private int id;
	//用户的标识，对当前公众号唯一	private String openid;
	//用户的昵称	private String nickname;
	//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知	private int sex;
	//用户所在城市	private String city;
	//用户所在省份	private String province;
	//用户所在国家	private String country;
	//用户的语言，简体中文为zh_CN	private String language;
	//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。	private String headimgurl;
	//用户关注时间	private String subscribeTime;
	//公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注	private String remark;
	//用户所在的分组ID（暂时兼容用户分组旧接口）	private String groupid;
	//	用户被打上的标签ID列表	private String tagidList;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 用户的标识，对当前公众号唯一 */	public void setOpenid(String openid){
		this.openid=openid;
	}
	/** get 用户的标识，对当前公众号唯一 */	public String getOpenid(){
		return openid;
	}
	/** set 用户的昵称 */	public void setNickname(String nickname){
		this.nickname=nickname;
	}
	/** get 用户的昵称 */	public String getNickname(){
		return nickname;
	}
	/** set 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */	public void setSex(int sex){
		this.sex=sex;
	}
	/** get 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */	public int getSex(){
		return sex;
	}
	/** set 用户所在城市 */	public void setCity(String city){
		this.city=city;
	}
	/** get 用户所在城市 */	public String getCity(){
		return city;
	}
	/** set 用户所在省份 */	public void setProvince(String province){
		this.province=province;
	}
	/** get 用户所在省份 */	public String getProvince(){
		return province;
	}
	/** set 用户所在国家 */	public void setCountry(String country){
		this.country=country;
	}
	/** get 用户所在国家 */	public String getCountry(){
		return country;
	}
	/** set 用户的语言，简体中文为zh_CN */	public void setLanguage(String language){
		this.language=language;
	}
	/** get 用户的语言，简体中文为zh_CN */	public String getLanguage(){
		return language;
	}
	/** set 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 */	public void setHeadimgurl(String headimgurl){
		this.headimgurl=headimgurl;
	}
	/** get 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 */	public String getHeadimgurl(){
		return headimgurl;
	}
	/** set 用户关注时间 */	public void setSubscribeTime(String subscribeTime){
		this.subscribeTime=subscribeTime;
	}
	/** get 用户关注时间 */	public String getSubscribeTime(){
		return subscribeTime;
	}
	/** set 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 */	public String getRemark(){
		return remark;
	}
	/** set 用户所在的分组ID（暂时兼容用户分组旧接口） */	public void setGroupid(String groupid){
		this.groupid=groupid;
	}
	/** get 用户所在的分组ID（暂时兼容用户分组旧接口） */	public String getGroupid(){
		return groupid;
	}
	/** set 	用户被打上的标签ID列表 */	public void setTagidList(String tagidList){
		this.tagidList=tagidList;
	}
	/** get 	用户被打上的标签ID列表 */	public String getTagidList(){
		return tagidList;
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

