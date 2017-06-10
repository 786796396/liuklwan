package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-04-28 16:08:42 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@SuppressWarnings("serial")public class ErrorInfoRequest extends Query {

	private Integer typeVal;

	private String orgSiteCode;

	private String code;
	
	private Integer isBigdata;

	private Integer paging;

	private String exposureKeyId;

	public Integer getTypeVal()
	{
		return typeVal;
	}

	public void setTypeVal(Integer typeVal)
	{
		this.typeVal = typeVal;
	}

	public String getOrgSiteCode()
	{
		return orgSiteCode;
	}

	public void setOrgSiteCode(String orgSiteCode)
	{
		this.orgSiteCode = orgSiteCode;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public Integer getIsBigdata()
	{
		return isBigdata;
	}

	public void setIsBigdata(Integer isBigdata)
	{
		this.isBigdata = isBigdata;
	}

	public Integer getPaging()
	{
		return paging;
	}

	public void setPaging(Integer paging)
	{
		this.paging = paging;
	}

	public String getExposureKeyId()
	{
		return exposureKeyId;
	}

	public void setExposureKeyId(String exposureKeyId)
	{
		this.exposureKeyId = exposureKeyId;
	}
	
}

