package com.ucap.cloud_web.dto;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class ComboRequest extends Query {

	// 是否有效（0：否，1：是）
	private int isValid;
	//多个套餐code  A,B
	private String comboCodes;
	
	public String getComboCodes() {
		return comboCodes;
	}

	public void setComboCodes(String comboCodes) {
		this.comboCodes = comboCodes;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

}
