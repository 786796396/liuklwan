package com.ucap.cloud_web.entity;


import java.util.Date;
import java.util.List;


/**


	//





	
	//指标id
	private Integer quotaId;
	
	//指标id
	private Integer quotaIdId;
	
	//指标父 id
	private Integer quotaParentId;
	
	//打分细则
	private String scoreRules;


	
	//自评得分
	private Float ratingScore;








	
	//栏目url 
	private String channelUrl;
	
	//栏目名称
	private String channelName;

	//截图url
	private String imgUrl;
	//考评内容
	private String appraisalContent;
	
	//对应的栏目个数
	private Integer chanlCount;
	
	//自评 对应的附件 地址
	private String path;
	//自评 对应的附件 别名
	private String aliasName;
	//每个三级指标对应的  栏目 附件 集合数据
	private List<PaRatingDetail> tableList;
	//自评栏目关联表  id；
	private Integer paRatingChannelId;
	
	
	
	private Boolean isEnd;
	private Integer layer;
	private String quotaName;
	
	

	public Integer getQuotaIdId() {
		return quotaIdId;
	}

	public void setQuotaIdId(Integer quotaIdId) {
		this.quotaIdId = quotaIdId;
	}

	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}


	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}

	public String getQuotaName() {
		return quotaName;
	}

	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}

	public Float getRatingScore() {
		return ratingScore;
	}

	public void setRatingScore(Float ratingScore) {
		this.ratingScore = ratingScore;
	}

	public Integer getPaRatingChannelId() {
		return paRatingChannelId;
	}

	public void setPaRatingChannelId(Integer paRatingChannelId) {
		this.paRatingChannelId = paRatingChannelId;
	}

	public List<PaRatingDetail> getTableList() {
		return tableList;
	}

	public void setTableList(List<PaRatingDetail> tableList) {
		this.tableList = tableList;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public Integer getChanlCount() {
		return chanlCount;
	}

	public void setChanlCount(Integer chanlCount) {
		this.chanlCount = chanlCount;
	}

	public String getScoreRules() {
		return scoreRules;
	}

	public void setScoreRules(String scoreRules) {
		this.scoreRules = scoreRules;
	}

	public Integer getQuotaId() {
		return quotaId;
	}

	public void setQuotaId(Integer quotaId) {
		this.quotaId = quotaId;
	}

	public Integer getQuotaParentId() {
		return quotaParentId;
	}

	public void setQuotaParentId(Integer quotaParentId) {
		this.quotaParentId = quotaParentId;
	}


	public String getAppraisalContent() {
		return appraisalContent;
	}

	public void setAppraisalContent(String appraisalContent) {
		this.appraisalContent = appraisalContent;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/** set  */
		this.id=id;
	}

		return id;
	}

		this.ratingId=ratingId;
	}

		return ratingId;
	}

		this.quota1=quota1;
	}

		return quota1;
	}

		this.quota2=quota2;
	}

		return quota2;
	}

		this.quota3=quota3;
	}

		return quota3;
	}

		this.ratingExplain=ratingExplain;
	}

		return ratingExplain;
	}

		this.ratingStauts=ratingStauts;
	}

		return ratingStauts;
	}

		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	/** set 打分状态 1：已打分，2：未打分 */
		this.scoreStauts=scoreStauts;
	}

		return scoreStauts;
	}

		this.scoreExplain=scoreExplain;
	}

		return scoreExplain;
	}

		this.problemType=problemType;
	}

		return problemType;
	}

		this.problemExplain=problemExplain;
	}

		return problemExplain;
	}

		this.downCount=downCount;
	}

		return downCount;
	}

		this.scoreUserId=scoreUserId;
	}

		return scoreUserId;
	}

		this.createTime=createTime;
	}

		return createTime;
	}

		this.modifyTime=modifyTime;
	}

		return modifyTime;
	}

