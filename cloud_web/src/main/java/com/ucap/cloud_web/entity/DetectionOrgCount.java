package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-05-20 11:36:07 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class DetectionOrgCount {	
	//检测结果列表 id
	private int id;

	//组织单位编码
	private String siteCode;

	//组织单位名称
	private String siteName;

	//扫描时间（yyyy-mm-dd）
	private String scanDate;

	// 类型（0,全部【除了关停与另外】，2本级部门，3下属单位，6其他）
	private int type;

	//各类型站点数
	private int typeSite;

	//健康指数（平均）
	private String indexCount;

	//全国健康指数（平均）
	private String indexCountAvg;

	//昨日健康指数(平均)
	private String indexCountYester;

	//领先全国比
	private String leadAvgRate;

	//相比昨天数
	private String leadYesterday;

	//相比昨天比
	private String leadYesterdayRate;

	//不连通次数（首页+业务系统+栏目）
	private int connNum;

	//栏目不连通次数
	private int connChannel;

	//栏目不连通站点数
	private int connChannelSite;
	
	//连不通栏目数
	private int connChannelNum;

	//首页不连通数
	private int connHome;

	//首页不连通站点数
	private int connHomeSite;

	//业务系统不连通数
	private int connBusiness;

	//业务系统不连通站点数
	private int connBusinessSite;
	
	private int connBusinessNum;//连不通业务系统栏目数

	//不可用链接数（全站，首页？）
	private int linkNum;

	//全站死链数
	private int linkAll;

	//首页死链数
	private int linkHome;

	//首页有死链站点数
	private int linkHomeSite;

	//内容保障问题个数（7项总和）
	private int contGuaranteNum;

	//内容保障：首页不更新
	private int securityHome;

	//内容保障：首页不更新站点数
	private int securityHomeSite;

	//内容保障：栏目不更新
	private int securityChannel;

	//内容保障：栏目不更新站点数
	private int securityChannelSite;

	//内容保障：空白栏目
	private int securityBlank;

	//内容保障：服务不实用
	private int securityService;

	//内容保障：互动回应差
	private int securityResponse;

	//内容保障：基本信息
	private int securityBasic;

	//内容保障：严重错误
	private int securityFatalError;

	//内容正确性个数
	private int contCorrectNum;

	//内容正确性网站个数
	private int contCorrectSite;

	//网站安全个数
	private int websiteSafe;

	//内容更新个数（首页+栏目）
	private int contUpdate;

	//首页更新数
	private int updateHome;

	//首页有更新站点数
	private int updateHomeSite;

	//栏目更新数
	private int updateChannel;

	//栏目有更新站点数
	private int updateChannelSite;

	//创建时间
	private Date createTime;

	//修改时间
	private Date modifyTime;
	
	

	public int getConnChannelNum() {
		return connChannelNum;
	}

	public void setConnChannelNum(int connChannelNum) {
		this.connChannelNum = connChannelNum;
	}

	public int getConnBusinessNum() {
		return connBusinessNum;
	}

	public void setConnBusinessNum(int connBusinessNum) {
		this.connBusinessNum = connBusinessNum;
	}

	/** set 检测结果列表 id */
	public void setId(int id){
		this.id=id;
	}

	/** get 检测结果列表 id */
	public int getId(){
		return id;
	}

	/** set 组织单位编码 */
	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}

	/** get 组织单位编码 */
	public String getSiteCode(){
		return siteCode;
	}

	/** set 组织单位名称 */
	public void setSiteName(String siteName){
		this.siteName=siteName;
	}

	/** get 组织单位名称 */
	public String getSiteName(){
		return siteName;
	}

	/** set 扫描时间（yyyy-mm-dd） */
	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}

	/** get 扫描时间（yyyy-mm-dd） */
	public String getScanDate(){
		return scanDate;
	}

	/** set  类型（0,全部【除了关停与另外】，2本级部门，3下属单位，6其他） */
	public void setType(int type){
		this.type=type;
	}

	/** get  类型（0,全部【除了关停与另外】，2本级部门，3下属单位，6其他） */
	public int getType(){
		return type;
	}

	/** set 各类型站点数 */
	public void setTypeSite(int typeSite){
		this.typeSite=typeSite;
	}

	/** get 各类型站点数 */
	public int getTypeSite(){
		return typeSite;
	}

	/** set 健康指数（平均） */
	public void setIndexCount(String indexCount){
		this.indexCount=indexCount;
	}

	/** get 健康指数（平均） */
	public String getIndexCount(){
		return indexCount;
	}

	/** set 全国健康指数（平均） */
	public void setIndexCountAvg(String indexCountAvg){
		this.indexCountAvg=indexCountAvg;
	}

	/** get 全国健康指数（平均） */
	public String getIndexCountAvg(){
		return indexCountAvg;
	}

	/** set 昨日健康指数(平均) */
	public void setIndexCountYester(String indexCountYester){
		this.indexCountYester=indexCountYester;
	}

	/** get 昨日健康指数(平均) */
	public String getIndexCountYester(){
		return indexCountYester;
	}

	/** set 领先全国比 */
	public void setLeadAvgRate(String leadAvgRate){
		this.leadAvgRate=leadAvgRate;
	}

	/** get 领先全国比 */
	public String getLeadAvgRate(){
		return leadAvgRate;
	}

	/** set 相比昨天数 */
	public void setLeadYesterday(String leadYesterday){
		this.leadYesterday=leadYesterday;
	}

	/** get 相比昨天数 */
	public String getLeadYesterday(){
		return leadYesterday;
	}

	/** set 相比昨天比 */
	public void setLeadYesterdayRate(String leadYesterdayRate){
		this.leadYesterdayRate=leadYesterdayRate;
	}

	/** get 相比昨天比 */
	public String getLeadYesterdayRate(){
		return leadYesterdayRate;
	}

	/** set 不连通次数（首页+业务系统+栏目） */
	public void setConnNum(int connNum){
		this.connNum=connNum;
	}

	/** get 不连通次数（首页+业务系统+栏目） */
	public int getConnNum(){
		return connNum;
	}

	/** set 栏目不连通次数 */
	public void setConnChannel(int connChannel){
		this.connChannel=connChannel;
	}

	/** get 栏目不连通次数 */
	public int getConnChannel(){
		return connChannel;
	}

	/** set 栏目不连通站点数 */
	public void setConnChannelSite(int connChannelSite){
		this.connChannelSite=connChannelSite;
	}

	/** get 栏目不连通站点数 */
	public int getConnChannelSite(){
		return connChannelSite;
	}

	/** set 首页不连通数 */
	public void setConnHome(int connHome){
		this.connHome=connHome;
	}

	/** get 首页不连通数 */
	public int getConnHome(){
		return connHome;
	}

	/** set 首页不连通站点数 */
	public void setConnHomeSite(int connHomeSite){
		this.connHomeSite=connHomeSite;
	}

	/** get 首页不连通站点数 */
	public int getConnHomeSite(){
		return connHomeSite;
	}

	/** set 业务系统不连通数 */
	public void setConnBusiness(int connBusiness){
		this.connBusiness=connBusiness;
	}

	/** get 业务系统不连通数 */
	public int getConnBusiness(){
		return connBusiness;
	}

	/** set 业务系统不连通站点数 */
	public void setConnBusinessSite(int connBusinessSite){
		this.connBusinessSite=connBusinessSite;
	}

	/** get 业务系统不连通站点数 */
	public int getConnBusinessSite(){
		return connBusinessSite;
	}

	/** set 不可用链接数（全站，首页？） */
	public void setLinkNum(int linkNum){
		this.linkNum=linkNum;
	}

	/** get 不可用链接数（全站，首页？） */
	public int getLinkNum(){
		return linkNum;
	}

	/** set 全站死链数 */
	public void setLinkAll(int linkAll){
		this.linkAll=linkAll;
	}

	/** get 全站死链数 */
	public int getLinkAll(){
		return linkAll;
	}

	/** set 首页死链数 */
	public void setLinkHome(int linkHome){
		this.linkHome=linkHome;
	}

	/** get 首页死链数 */
	public int getLinkHome(){
		return linkHome;
	}

	/** set 首页有死链站点数 */
	public void setLinkHomeSite(int linkHomeSite){
		this.linkHomeSite=linkHomeSite;
	}

	/** get 首页有死链站点数 */
	public int getLinkHomeSite(){
		return linkHomeSite;
	}

	/** set 内容保障问题个数（7项总和） */
	public void setContGuaranteNum(int contGuaranteNum){
		this.contGuaranteNum=contGuaranteNum;
	}

	/** get 内容保障问题个数（7项总和） */
	public int getContGuaranteNum(){
		return contGuaranteNum;
	}

	/** set 内容保障：首页不更新 */
	public void setSecurityHome(int securityHome){
		this.securityHome=securityHome;
	}

	/** get 内容保障：首页不更新 */
	public int getSecurityHome(){
		return securityHome;
	}

	/** set 内容保障：首页不更新站点数 */
	public void setSecurityHomeSite(int securityHomeSite){
		this.securityHomeSite=securityHomeSite;
	}

	/** get 内容保障：首页不更新站点数 */
	public int getSecurityHomeSite(){
		return securityHomeSite;
	}

	/** set 内容保障：栏目不更新 */
	public void setSecurityChannel(int securityChannel){
		this.securityChannel=securityChannel;
	}

	/** get 内容保障：栏目不更新 */
	public int getSecurityChannel(){
		return securityChannel;
	}

	/** set 内容保障：栏目不更新站点数 */
	public void setSecurityChannelSite(int securityChannelSite){
		this.securityChannelSite=securityChannelSite;
	}

	/** get 内容保障：栏目不更新站点数 */
	public int getSecurityChannelSite(){
		return securityChannelSite;
	}

	/** set 内容保障：空白栏目 */
	public void setSecurityBlank(int securityBlank){
		this.securityBlank=securityBlank;
	}

	/** get 内容保障：空白栏目 */
	public int getSecurityBlank(){
		return securityBlank;
	}

	/** set 内容保障：服务不实用 */
	public void setSecurityService(int securityService){
		this.securityService=securityService;
	}

	/** get 内容保障：服务不实用 */
	public int getSecurityService(){
		return securityService;
	}

	/** set 内容保障：互动回应差 */
	public void setSecurityResponse(int securityResponse){
		this.securityResponse=securityResponse;
	}

	/** get 内容保障：互动回应差 */
	public int getSecurityResponse(){
		return securityResponse;
	}

	/** set 内容保障：基本信息 */
	public void setSecurityBasic(int securityBasic){
		this.securityBasic=securityBasic;
	}

	/** get 内容保障：基本信息 */
	public int getSecurityBasic(){
		return securityBasic;
	}

	/** set 内容保障：严重错误 */
	public void setSecurityFatalError(int securityFatalError){
		this.securityFatalError=securityFatalError;
	}

	/** get 内容保障：严重错误 */
	public int getSecurityFatalError(){
		return securityFatalError;
	}

	/** set 内容正确性个数 */
	public void setContCorrectNum(int contCorrectNum){
		this.contCorrectNum=contCorrectNum;
	}

	/** get 内容正确性个数 */
	public int getContCorrectNum(){
		return contCorrectNum;
	}

	/** set 内容正确性网站个数 */
	public void setContCorrectSite(int contCorrectSite){
		this.contCorrectSite=contCorrectSite;
	}

	/** get 内容正确性网站个数 */
	public int getContCorrectSite(){
		return contCorrectSite;
	}

	/** set 网站安全个数 */
	public void setWebsiteSafe(int websiteSafe){
		this.websiteSafe=websiteSafe;
	}

	/** get 网站安全个数 */
	public int getWebsiteSafe(){
		return websiteSafe;
	}

	/** set 内容更新个数（首页+栏目） */
	public void setContUpdate(int contUpdate){
		this.contUpdate=contUpdate;
	}

	/** get 内容更新个数（首页+栏目） */
	public int getContUpdate(){
		return contUpdate;
	}

	/** set 首页更新数 */
	public void setUpdateHome(int updateHome){
		this.updateHome=updateHome;
	}

	/** get 首页更新数 */
	public int getUpdateHome(){
		return updateHome;
	}

	/** set 首页有更新站点数 */
	public void setUpdateHomeSite(int updateHomeSite){
		this.updateHomeSite=updateHomeSite;
	}

	/** get 首页有更新站点数 */
	public int getUpdateHomeSite(){
		return updateHomeSite;
	}

	/** set 栏目更新数 */
	public void setUpdateChannel(int updateChannel){
		this.updateChannel=updateChannel;
	}

	/** get 栏目更新数 */
	public int getUpdateChannel(){
		return updateChannel;
	}

	/** set 栏目有更新站点数 */
	public void setUpdateChannelSite(int updateChannelSite){
		this.updateChannelSite=updateChannelSite;
	}

	/** get 栏目有更新站点数 */
	public int getUpdateChannelSite(){
		return updateChannelSite;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime(){
		return createTime;
	}

	/** set 修改时间 */
	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}

	/** get 修改时间 */
	public Date getModifyTime(){
		return modifyTime;
	}}

