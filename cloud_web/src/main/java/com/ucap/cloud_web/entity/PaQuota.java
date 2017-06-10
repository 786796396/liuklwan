package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaQuota {


	//	private int id;
	//项目评估对象表id（pa_appraisal）	private Integer appraisalId;
	//父id	private Integer parentId;
	//权重	private Float weight;
	//级别（1：一级，2：二级，3：三级）	private short layer;

	
	//打分细则
	private String scoreRules;	//内容	private String content;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//是否最终级别 1 是   2 不是
	private Integer isFinal;
	
	//
	private String name;
	private String layer3;
	private String layer2;
	private String layer1;

	
		public Integer getIsFinal() {
		return isFinal;
	}

	public void setIsFinal(Integer isFinal) {
		this.isFinal = isFinal;
	}

	public String getScoreRules() {
		return scoreRules;
	}

	public void setScoreRules(String scoreRules) {
		this.scoreRules = scoreRules;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAppraisalId() {
		return appraisalId;
	}

	public void setAppraisalId(Integer appraisalId) {
		this.appraisalId = appraisalId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}



	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getLayer3() {
		return layer3;
	}

	public void setLayer3(String layer3) {
		this.layer3 = layer3;
	}

	public String getLayer2() {
		return layer2;
	}

	public void setLayer2(String layer2) {
		this.layer2 = layer2;
	}

	public String getLayer1() {
		return layer1;
	}

	public void setLayer1(String layer1) {
		this.layer1 = layer1;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 级别（1：一级，2：二级，3：三级） */	public void setLayer(short layer){
		this.layer=layer;
	}
	/** get 级别（1：一级，2：二级，3：三级） */	public short getLayer(){
		return layer;
	}
	/** set 内容 */	public void setContent(String content){
		this.content=content;
	}
	/** get 内容 */	public String getContent(){
		return content;
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

