package com.ucap.cloud_web.entity;




/**


	//





	//站点任务号
	private String websiteTaskNum;
	//用于判断，表中不存在
	private int flagResult;
	//url,抽查报告路径,表中不存在
	private String url;
	
	//全站死链是否扫描(1:是，2否)
	private Integer isScan;
		this.Id=Id;
	}

		return Id;
	}

		this.siteCode=siteCode;
	}

		return siteCode;
	}

		this.servicePeriodId=servicePeriodId;
	}

		return servicePeriodId;
	}

		this.startDate=startDate;
	}

		return startDate;
	}

		this.endDate=endDate;
	}

		return endDate;
	}

		this.comboId=comboId;
	}

		return comboId;
	}

	public String getWebsiteTaskNum() {
		return websiteTaskNum;
	}

	public void setWebsiteTaskNum(String websiteTaskNum) {
		this.websiteTaskNum = websiteTaskNum;
	}

	public int getFlagResult() {
		return flagResult;
	}

	public void setFlagResult(int flagResult) {
		this.flagResult = flagResult;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIsScan() {
		return isScan;
	}

	public void setIsScan(Integer isScan) {
		this.isScan = isScan;
	}

