package com.ucap.cloud_web.entity;


import java.util.Date;


/**


	//









	
	//项目名称
	private String projectName;
	
	//  自评状态（1：已提交，2：未自评，3：填报中）
	private Integer ratingState;
	
	//任务对象关联表  id
	private Integer targetTaskId;

	//项目评估对象id
	private Integer appraisalId;

	//是否开放（1：开放，2：关闭）
	private Integer isOpen;
	
	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getAppraisalId() {
		return appraisalId;
	}

	public void setAppraisalId(Integer appraisalId) {
		this.appraisalId = appraisalId;
	}

	public Integer getTargetTaskId() {
		return targetTaskId;
	}

	public void setTargetTaskId(Integer targetTaskId) {
		this.targetTaskId = targetTaskId;
	}

	public Integer getRatingState() {
		return ratingState;
	}

	public void setRatingState(Integer ratingState) {
		this.ratingState = ratingState;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/** set  */
		this.id=id;
	}

		return id;
	}

		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/** set 项目表id（pa_project） */
		this.projectId=projectId;
	}

		return projectId;
	}

		this.taskName=taskName;
	}

		return taskName;
	}


		this.stauts=stauts;
	}

		return stauts;
	}

		this.ratingProgress=ratingProgress;
	}

		return ratingProgress;
	}

		this.markProgress=markProgress;
	}

		return markProgress;
	}

		this.createTime=createTime;
	}

		return createTime;
	}

		this.modifyTime=modifyTime;
	}

		return modifyTime;
	}

