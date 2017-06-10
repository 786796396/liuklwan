package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-30 15:52:30 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class DicSiteRequest extends Query {
	
	//站点网站类型编码（0,1,2,3,4,5）
	private Integer siteTypeCode;

	//站点网站类型：0省部级政府门户，1省级政府部门网站（含参公事业单位、部委子站），2市级政府门户 3地市级政府部门网站（含参公事业单位） 4县级政府门户（不设区的市，按镇计算）5县级政府部门
	private String remark;

	public Integer getSiteTypeCode() {
		return siteTypeCode;
	}

	public void setSiteTypeCode(Integer siteTypeCode) {
		this.siteTypeCode = siteTypeCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	
	
	

}

