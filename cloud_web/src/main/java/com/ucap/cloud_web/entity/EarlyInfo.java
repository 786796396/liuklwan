package com.ucap.cloud_web.entity;


import java.util.Date;


/**


	//预警表id






	// 预警类型：1首页连通性，2首页不更新，3栏目不更新，4空白栏目，5互动回应，6内容正确性，7升级改版或者临时关停
	private Integer type;
	
	//升级改版或者临时关停类型 1.首页连不通比例超过80%，2关键栏目连不通比例超过80%，3网站首页更新量为0，4人工检查时发现，5插码挂码
	private Integer updateGradeType;
	
	//检查类型（0：抽查，1：正常合同）
	private Integer checkType;
	

	
	private int servicePeriodId;//服务周期id
	
	
	
	public int getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(int servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public Integer getUpdateGradeType() {
		return updateGradeType;
	}

	public void setUpdateGradeType(Integer updateGradeType) {
		this.updateGradeType = updateGradeType;
	}

	/** set 预警表id */
		this.id=id;
	}

		return id;
	}

		this.siteCode=siteCode;
	}

		return siteCode;
	}

		this.siteName=siteName;
	}

		return siteName;
	}

		this.earlyLevel=earlyLevel;
	}

		return earlyLevel;
	}

		this.newEarlyNum=newEarlyNum;
	}

		return newEarlyNum;
	}

		this.earlySum=earlySum;
	}

		return earlySum;
	}

	/** get 类型：1网站连通性，2内容保障问题，3内容正确性 */
	public Integer getType() {
		return type;
	}

	/** set 类型：1网站连通性，2内容保障问题，3内容正确性 */
	public void setType(Integer type) {
		this.type = type;
	}
	
		this.state=state;
	}

		return state;
	}

		this.createTime=createTime;
	}

		return createTime;
	}

