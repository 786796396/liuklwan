package com.ucap.cloud_web.entity;


import java.util.Date;


/**
* <br>
* <b>作者：</b>SunJiang<br>
* <b>日期：</b> 2015-11-20 16:56:11 <br>
* <b>版权所有：<b>版权所有(C) 2015<br>
*/
public class DetectionResult {

		
	//检测结果列表 id
	private int id;

	//网站标识码
	private String siteCode;

	//网站名称
	private String siteName;
	
	//url
	private String url;
	
	//扫描时间（yyyy-mm-dd）
	private String scanDate;
	
	//折算分数
	private double convertScores;
	
	//健康指数
	private String indexCount;
	
	//全国健康指数（平均）
	private String indexCountAvg;
	
	//领先全国比
	private String leadAvgRate;
	
	//相比昨天数（健康指数增加或减少数）
	private String leadYesterday;

	//相比昨天比（健康指数增加或减少比）
	private String leadYesterdayRate;

	//不连通次数
	private int connNum;
	
	//栏目不连通次数
	private int connChannel;
	
	//业务系统连不通  栏目个数
	private int connChannelNum;

	//业务系统不连通次数
	private int connBusiness;
	
	//业务系统连不通  栏目个数
	private int connBusinessNum;

	//首页不连通次数
	private int connHome;

	//首页连通性总次数（连通成功+失败）
//	private int connHomeSum;
	

	//不可用链接数
	private int linkNum;
	
	//全站死链数
	private int linkAll;

	//首页死链数
	private int linkHome;
	

	//内容保障问题个数
	private int contGuaranteNum;
	
	//内容保障：首页不更新
	private int securityHome;

	//内容保障：栏目不更新
	private int securityChannel;

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
	
	//网站安全个数
	private int websiteSafe;
	
	//内容正确性个数
	private int contCorrectNum;

	//内容更新个数
	private int contUpdate;
	
	//首页更新数
	private int updateHome;

	//栏目更新数
	private int updateChannel;

	// 无声安全问题个数
	private int silentNum;

	// 0未检测，1检测中，2已结束
	private int silentType;

	//创建时间
	private Date createTime;

	//修改时间
	private Date modifyTime;
	
	// 网站主管单位
	private String director;
	// 办公地址
	private String address;
	// 负责人
	private String principalName;
	// 负责人手机
	private String telephone;
	// 联系人手机
	private String telephone2;
	// 负责人办公电话
	private String mobile;
	// 联系人办公电话
	private String mobile2;
	// 登录验证邮箱--负责人
	private String email;
	// 邮箱--联系人
	private String email2;
	// 联系人
	private String linkmanName;
	// 0非门户，1门户
	private int isorganizational;

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

	public int getSecurityFatalError() {
		return securityFatalError;
	}

	public void setSecurityFatalError(int securityFatalError) {
		this.securityFatalError = securityFatalError;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getConvertScores() {
		return convertScores;
	}

	public void setConvertScores(double convertScores) {
		this.convertScores = convertScores;
	}

	/** set 检测结果列表 id */
	public void setId(int id){
		this.id=id;
	}

	/** get 检测结果列表 id */
	public int getId(){
		return id;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode(){
		return siteCode;
	}

	/** set 网站名称 */
	public void setSiteName(String siteName){
		this.siteName=siteName;
	}

	/** get 网站名称 */
	public String getSiteName(){
		return siteName;
	}

	/** set 不连通次数 */
	public void setConnNum(int connNum){
		this.connNum=connNum;
	}

	/** get 不连通次数 */
	public int getConnNum(){
		return connNum;
	}

	/** set 不可用链接数 */
	public void setLinkNum(int linkNum){
		this.linkNum=linkNum;
	}

	/** get 不可用链接数 */
	public int getLinkNum(){
		return linkNum;
	}

	/** set 内容保障问题个数 */
	public void setContGuaranteNum(int contGuaranteNum){
		this.contGuaranteNum=contGuaranteNum;
	}

	/** get 内容保障问题个数 */
	public int getContGuaranteNum(){
		return contGuaranteNum;
	}

	/** set 内容正确性个数 */
	public void setContCorrectNum(int contCorrectNum){
		this.contCorrectNum=contCorrectNum;
	}

	/** get 内容正确性个数 */
	public int getContCorrectNum(){
		return contCorrectNum;
	}

	/** set 网站安全个数 */
	public void setWebsiteSafe(int websiteSafe){
		this.websiteSafe=websiteSafe;
	}

	/** get 网站安全个数 */
	public int getWebsiteSafe(){
		return websiteSafe;
	}

	/** set 内容更新个数 */
	public void setContUpdate(int contUpdate){
		this.contUpdate=contUpdate;
	}

	/** get 内容更新个数 */
	public int getContUpdate(){
		return contUpdate;
	}

	/** set 扫描时间（yyyy-mm-dd） */
	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}

	/** get 扫描时间（yyyy-mm-dd） */
	public String getScanDate(){
		return scanDate;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime(){
		return createTime;
	}

	public String getIndexCount() {
		return indexCount;
	}

	public void setIndexCount(String indexCount) {
		this.indexCount = indexCount;
	}

	public String getIndexCountAvg() {
		return indexCountAvg;
	}

	public void setIndexCountAvg(String indexCountAvg) {
		this.indexCountAvg = indexCountAvg;
	}

	public String getLeadAvgRate() {
		return leadAvgRate;
	}

	public void setLeadAvgRate(String leadAvgRate) {
		this.leadAvgRate = leadAvgRate;
	}

	public String getLeadYesterday() {
		return leadYesterday;
	}

	public void setLeadYesterday(String leadYesterday) {
		this.leadYesterday = leadYesterday;
	}

	public String getLeadYesterdayRate() {
		return leadYesterdayRate;
	}

	public void setLeadYesterdayRate(String leadYesterdayRate) {
		this.leadYesterdayRate = leadYesterdayRate;
	}

	public int getConnChannel() {
		return connChannel;
	}

	public void setConnChannel(int connChannel) {
		this.connChannel = connChannel;
	}

	public int getConnBusiness() {
		return connBusiness;
	}

	public void setConnBusiness(int connBusiness) {
		this.connBusiness = connBusiness;
	}

	public int getConnHome() {
		return connHome;
	}

	public void setConnHome(int connHome) {
		this.connHome = connHome;
	}

	public int getLinkAll() {
		return linkAll;
	}

	public void setLinkAll(int linkAll) {
		this.linkAll = linkAll;
	}

	public int getLinkHome() {
		return linkHome;
	}

	public void setLinkHome(int linkHome) {
		this.linkHome = linkHome;
	}

	public int getSecurityHome() {
		return securityHome;
	}

	public void setSecurityHome(int securityHome) {
		this.securityHome = securityHome;
	}

	public int getSecurityChannel() {
		return securityChannel;
	}

	public void setSecurityChannel(int securityChannel) {
		this.securityChannel = securityChannel;
	}

	public int getSecurityBlank() {
		return securityBlank;
	}

	public void setSecurityBlank(int securityBlank) {
		this.securityBlank = securityBlank;
	}

	public int getSecurityService() {
		return securityService;
	}

	public void setSecurityService(int securityService) {
		this.securityService = securityService;
	}

	public int getSecurityResponse() {
		return securityResponse;
	}

	public void setSecurityResponse(int securityResponse) {
		this.securityResponse = securityResponse;
	}

	public int getSecurityBasic() {
		return securityBasic;
	}

	public void setSecurityBasic(int securityBasic) {
		this.securityBasic = securityBasic;
	}

	public int getUpdateHome() {
		return updateHome;
	}

	public void setUpdateHome(int updateHome) {
		this.updateHome = updateHome;
	}

	public int getUpdateChannel() {
		return updateChannel;
	}

	public void setUpdateChannel(int updateChannel) {
		this.updateChannel = updateChannel;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getSilentNum() {
		return silentNum;
	}

	public void setSilentNum(int silentNum) {
		this.silentNum = silentNum;
	}

	public int getSilentType() {
		return silentType;
	}

	public void setSilentType(int silentType) {
		this.silentType = silentType;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public int getIsorganizational() {
		return isorganizational;
	}

	public void setIsorganizational(int isorganizational) {
		this.isorganizational = isorganizational;
	}

}

