package com.ucap.cloud_web.entity;


import java.util.Date;


/**


	//大数据xml数据id

	//tab标签id -1：当前登录组织单位数据汇总  0:下级地方站群 1：下级地方门户 2：本级站点 
	//3：下级部门站群 4：下级部门门户 5：监测网站数量 6：首页不更新（网站数） 7：监测不连通率占比
	private Integer tabId;



		this.id=id;
	}

		return id;
	}

		this.siteCode=siteCode;
	}

		return siteCode;
	}

		this.dataXml=dataXml;
	}

		return dataXml;
	}

		this.createTime=createTime;
	}

		return createTime;
	}

		this.modifyTime=modifyTime;
	}

		return modifyTime;
	}

	public Integer getTabId() {
		return tabId;
	}

	public void setTabId(Integer tabId) {
		this.tabId = tabId;
	}

