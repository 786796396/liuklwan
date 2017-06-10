package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class PaRatingDetailRequest extends Query {
	private Integer ratingId;
	//一级指标
	private String quota1;
	
	// 自评详情表的id
	private Integer ratingDetailId;
	
	//自评状态
	private Integer ratingStauts;
	

	public Integer getRatingStauts() {
		return ratingStauts;
	}

	public void setRatingStauts(Integer ratingStauts) {
		this.ratingStauts = ratingStauts;
	}

	public Integer getRatingId() {
		return ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	public String getQuota1() {
		return quota1;
	}

	public void setQuota1(String quota1) {
		this.quota1 = quota1;
	}

	public Integer getRatingDetailId() {
		return ratingDetailId;
	}

	public void setRatingDetailId(Integer ratingDetailId) {
		this.ratingDetailId = ratingDetailId;
	}

}

