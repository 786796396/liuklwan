package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**

	// 1组织 2填报
	private Integer menuType;
	// 区别是上面还是左侧 1: top 2: left
	private Integer positionType;
	// 1启用 2禁用
	private Integer isDisable;
	// 父级编号
	private Integer parentId;
	//唯一值
	private String onlyValue;

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getPositionType() {
		return positionType;
	}

	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}

	public Integer getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
