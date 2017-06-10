package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-30 14:55:01 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class DicInteractProblemRequest extends Query {
	
	//问题类型编码(0、1、2、3、4、5、6)
	private int interactProblemCode;

	public int getInteractProblemCode() {
		return interactProblemCode;
	}

	public void setInteractProblemCode(int interactProblemCode) {
		this.interactProblemCode = interactProblemCode;
	}
	
}

