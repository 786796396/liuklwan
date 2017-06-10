package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**
 * <p>Description:前台页面传递基础数据 </p>
 * <p>@Package：com.ucap.cloud_web.dto </p>
 * <p>Title: DatabaseOrgInfoRequest </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2016-3-29下午1:36:25 </p>
 */@SuppressWarnings("serial")public class DatabaseOrgInfoRequest extends Query {


	
	//网站标识码
	private String stieCode;
	//网站校验码
	private String vcode;
	//
	private String siteCodeLike;
	//不以endSiteCodeLike结尾
	private String endSiteCodeLike;
	//不等于noEqualsSiteCode
	private String noEqualsSiteCode;
	//父组织标识码
	private String parentSiteCodeLike;
	
	public String getSiteCodeLike() {
		return siteCodeLike;
	}
	public void setSiteCodeLike(String siteCodeLike) {
		this.siteCodeLike = siteCodeLike;
	}
	public String getStieCode() {
		return stieCode;
	}
	public void setStieCode(String stieCode) {
		this.stieCode = stieCode;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public String getEndSiteCodeLike() {
		return endSiteCodeLike;
	}
	public void setEndSiteCodeLike(String endSiteCodeLike) {
		this.endSiteCodeLike = endSiteCodeLike;
	}
	public String getNoEqualsSiteCode() {
		return noEqualsSiteCode;
	}
	public void setNoEqualsSiteCode(String noEqualsSiteCode) {
		this.noEqualsSiteCode = noEqualsSiteCode;
	}
	public String getParentSiteCodeLike() {
		return parentSiteCodeLike;
	}
	public void setParentSiteCodeLike(String parentSiteCodeLike) {
		this.parentSiteCodeLike = parentSiteCodeLike;
	}
	
}

