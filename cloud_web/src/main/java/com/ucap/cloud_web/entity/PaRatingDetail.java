package com.ucap.cloud_web.entity;


import java.util.Date;
import java.util.List;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaRatingDetail {


	//	private int id;
	//自评表id（pa_rating）	private int ratingId;
	//一级指标	private String quota1;
	//二级指标	private String quota2;
	//三级指标	private String quota3;
	//自评说明	private String ratingExplain;
	
	//指标id
	private Integer quotaId;
	
	//指标id
	private Integer quotaIdId;
	
	//指标父 id
	private Integer quotaParentId;
	
	//打分细则
	private String scoreRules;
	//自评状态 1：已填，2：未填	private short ratingStauts;
	//绩效得分	private Float score;
	
	//自评得分
	private Float ratingScore;
	//打分状态 1：已打分，2：未打分	private short scoreStauts;
	//打分说明	private String scoreExplain;
	//问题类型（pa_defect_db_type 表中的一级类别名称）	private String problemType;
	//问题说明（pa_defect_db_type 表中的二级类别名称）	private String problemExplain;
	//下载次数	private int downCount;
	//打分人id	private int scoreUserId;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
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

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 自评表id（pa_rating） */	public void setRatingId(int ratingId){
		this.ratingId=ratingId;
	}
	/** get 自评表id（pa_rating） */	public int getRatingId(){
		return ratingId;
	}
	/** set 一级指标 */	public void setQuota1(String quota1){
		this.quota1=quota1;
	}
	/** get 一级指标 */	public String getQuota1(){
		return quota1;
	}
	/** set 二级指标 */	public void setQuota2(String quota2){
		this.quota2=quota2;
	}
	/** get 二级指标 */	public String getQuota2(){
		return quota2;
	}
	/** set 三级指标 */	public void setQuota3(String quota3){
		this.quota3=quota3;
	}
	/** get 三级指标 */	public String getQuota3(){
		return quota3;
	}
	/** set 自评说明 */	public void setRatingExplain(String ratingExplain){
		this.ratingExplain=ratingExplain;
	}
	/** get 自评说明 */	public String getRatingExplain(){
		return ratingExplain;
	}
	/** set 自评状态 1：已填，2：未填 */	public void setRatingStauts(short ratingStauts){
		this.ratingStauts=ratingStauts;
	}
	/** get 自评状态 1：已填，2：未填 */	public short getRatingStauts(){
		return ratingStauts;
	}
	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	/** set 打分状态 1：已打分，2：未打分 */	public void setScoreStauts(short scoreStauts){
		this.scoreStauts=scoreStauts;
	}
	/** get 打分状态 1：已打分，2：未打分 */	public short getScoreStauts(){
		return scoreStauts;
	}
	/** set 打分说明 */	public void setScoreExplain(String scoreExplain){
		this.scoreExplain=scoreExplain;
	}
	/** get 打分说明 */	public String getScoreExplain(){
		return scoreExplain;
	}
	/** set 问题类型（pa_defect_db_type 表中的一级类别名称） */	public void setProblemType(String problemType){
		this.problemType=problemType;
	}
	/** get 问题类型（pa_defect_db_type 表中的一级类别名称） */	public String getProblemType(){
		return problemType;
	}
	/** set 问题说明（pa_defect_db_type 表中的二级类别名称） */	public void setProblemExplain(String problemExplain){
		this.problemExplain=problemExplain;
	}
	/** get 问题说明（pa_defect_db_type 表中的二级类别名称） */	public String getProblemExplain(){
		return problemExplain;
	}
	/** set 下载次数 */	public void setDownCount(int downCount){
		this.downCount=downCount;
	}
	/** get 下载次数 */	public int getDownCount(){
		return downCount;
	}
	/** set 打分人id */	public void setScoreUserId(int scoreUserId){
		this.scoreUserId=scoreUserId;
	}
	/** get 打分人id */	public int getScoreUserId(){
		return scoreUserId;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}
}

