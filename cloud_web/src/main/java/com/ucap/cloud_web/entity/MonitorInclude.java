package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-01 16:07:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class MonitorInclude {


	//标识ID
	private Integer id;
	//采集日期	private Date crawlDate;
	//网站标识码	private String siteCode;
	//站点	private String webSite;
	//域	private String domain;
	//百度收录数_站点	private Integer baiduSlWebsite;
	//360收录数_站点	private Integer qihuSlWebsite;
	//搜狗收录数_站点	private Integer sogouSlWebsite;
	//谷歌收录数_站点	private Integer googleSlWebsite;
	//百度收录数_域	private Integer baiduSlDomainsite;
	//360收录数_域	private Integer qihuSlDomainsite;
	//搜狗收录数_域	private Integer sogouSlDomainsite;
	//谷歌收录数_域	private Integer googleSlDomainsite;
	//备案方	private String beianDanwei;
	//安全状态(安全网站|危险网站)	private Integer anquanZhuangtai;
	//漏洞检测(通过|存在低危漏洞)	private Integer anquanDjc;
	//恶意代码检测(通过)	private Integer anquanDmjc;
	//被篡改检测(通过|被恶意篡改)	private Integer anquanCgjc;
	//欺诈内容检测(通过)	private Integer anquanNrjc;
	//虚假或欺诈网站监控(通过)	private Integer jiankongQizha;
	//挂马或恶意网站监控(通过)	private Integer jiankongGuama;
	//违法内容网站监控(通过)	private Integer jiankongWeifa;
	//服务器漏洞监控(通过)	private Integer jiankongFuwuqi;
	//扫描日期	private String scanDate;
	//引擎端唯一id	private String reference;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//站点  跳转url
	private String jumpUrl;
	
	//站点url
	private String url;
	
	//站点名称
	private String name;

	
	
		public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** set 标识ID
 */	public void setId(Integer id){
		this.id=id;
	}
	/** get 标识ID
 */	public Integer getId(){
		return id;
	}
	/** set 采集日期 */	public void setCrawlDate(Date crawlDate){
		this.crawlDate=crawlDate;
	}
	/** get 采集日期 */	public Date getCrawlDate(){
		return crawlDate;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 站点 */	public void setWebSite(String webSite){
		this.webSite=webSite;
	}
	/** get 站点 */	public String getWebSite(){
		return webSite;
	}
	/** set 域 */	public void setDomain(String domain){
		this.domain=domain;
	}
	/** get 域 */	public String getDomain(){
		return domain;
	}
	/** set 百度收录数_站点 */	public void setBaiduSlWebsite(Integer baiduSlWebsite){
		this.baiduSlWebsite=baiduSlWebsite;
	}
	/** get 百度收录数_站点 */	public Integer getBaiduSlWebsite(){
		return baiduSlWebsite;
	}
	/** set 360收录数_站点 */	public void setQihuSlWebsite(Integer qihuSlWebsite){
		this.qihuSlWebsite=qihuSlWebsite;
	}
	/** get 360收录数_站点 */	public Integer getQihuSlWebsite(){
		return qihuSlWebsite;
	}
	/** set 搜狗收录数_站点 */	public void setSogouSlWebsite(Integer sogouSlWebsite){
		this.sogouSlWebsite=sogouSlWebsite;
	}
	/** get 搜狗收录数_站点 */	public Integer getSogouSlWebsite(){
		return sogouSlWebsite;
	}
	/** set 谷歌收录数_站点 */	public void setGoogleSlWebsite(Integer googleSlWebsite){
		this.googleSlWebsite=googleSlWebsite;
	}
	/** get 谷歌收录数_站点 */	public Integer getGoogleSlWebsite(){
		return googleSlWebsite;
	}
	/** set 百度收录数_域 */	public void setBaiduSlDomainsite(Integer baiduSlDomainsite){
		this.baiduSlDomainsite=baiduSlDomainsite;
	}
	/** get 百度收录数_域 */	public Integer getBaiduSlDomainsite(){
		return baiduSlDomainsite;
	}
	/** set 360收录数_域 */	public void setQihuSlDomainsite(Integer qihuSlDomainsite){
		this.qihuSlDomainsite=qihuSlDomainsite;
	}
	/** get 360收录数_域 */	public Integer getQihuSlDomainsite(){
		return qihuSlDomainsite;
	}
	/** set 搜狗收录数_域 */	public void setSogouSlDomainsite(Integer sogouSlDomainsite){
		this.sogouSlDomainsite=sogouSlDomainsite;
	}
	/** get 搜狗收录数_域 */	public Integer getSogouSlDomainsite(){
		return sogouSlDomainsite;
	}
	/** set 谷歌收录数_域 */	public void setGoogleSlDomainsite(Integer googleSlDomainsite){
		this.googleSlDomainsite=googleSlDomainsite;
	}
	/** get 谷歌收录数_域 */	public Integer getGoogleSlDomainsite(){
		return googleSlDomainsite;
	}
	/** set 备案方 */	public void setBeianDanwei(String beianDanwei){
		this.beianDanwei=beianDanwei;
	}
	/** get 备案方 */	public String getBeianDanwei(){
		return beianDanwei;
	}
	/** set 安全状态(安全网站|危险网站) */	public void setAnquanZhuangtai(Integer anquanZhuangtai){
		this.anquanZhuangtai=anquanZhuangtai;
	}
	/** get 安全状态(安全网站|危险网站) */	public Integer getAnquanZhuangtai(){
		return anquanZhuangtai;
	}
	/** set 漏洞检测(通过|存在低危漏洞) */	public void setAnquanDjc(Integer anquanDjc){
		this.anquanDjc=anquanDjc;
	}
	/** get 漏洞检测(通过|存在低危漏洞) */	public Integer getAnquanDjc(){
		return anquanDjc;
	}
	/** set 恶意代码检测(通过) */	public void setAnquanDmjc(Integer anquanDmjc){
		this.anquanDmjc=anquanDmjc;
	}
	/** get 恶意代码检测(通过) */	public Integer getAnquanDmjc(){
		return anquanDmjc;
	}
	/** set 被篡改检测(通过|被恶意篡改) */	public void setAnquanCgjc(Integer anquanCgjc){
		this.anquanCgjc=anquanCgjc;
	}
	/** get 被篡改检测(通过|被恶意篡改) */	public Integer getAnquanCgjc(){
		return anquanCgjc;
	}
	/** set 欺诈内容检测(通过) */	public void setAnquanNrjc(Integer anquanNrjc){
		this.anquanNrjc=anquanNrjc;
	}
	/** get 欺诈内容检测(通过) */	public Integer getAnquanNrjc(){
		return anquanNrjc;
	}
	/** set 虚假或欺诈网站监控(通过) */	public void setJiankongQizha(Integer jiankongQizha){
		this.jiankongQizha=jiankongQizha;
	}
	/** get 虚假或欺诈网站监控(通过) */	public Integer getJiankongQizha(){
		return jiankongQizha;
	}
	/** set 挂马或恶意网站监控(通过) */	public void setJiankongGuama(Integer jiankongGuama){
		this.jiankongGuama=jiankongGuama;
	}
	/** get 挂马或恶意网站监控(通过) */	public Integer getJiankongGuama(){
		return jiankongGuama;
	}
	/** set 违法内容网站监控(通过) */	public void setJiankongWeifa(Integer jiankongWeifa){
		this.jiankongWeifa=jiankongWeifa;
	}
	/** get 违法内容网站监控(通过) */	public Integer getJiankongWeifa(){
		return jiankongWeifa;
	}
	/** set 服务器漏洞监控(通过) */	public void setJiankongFuwuqi(Integer jiankongFuwuqi){
		this.jiankongFuwuqi=jiankongFuwuqi;
	}
	/** get 服务器漏洞监控(通过) */	public Integer getJiankongFuwuqi(){
		return jiankongFuwuqi;
	}
	/** set 扫描日期 */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 扫描日期 */	public String getScanDate(){
		return scanDate;
	}
	/** set 引擎端唯一id */	public void setReference(String reference){
		this.reference=reference;
	}
	/** get 引擎端唯一id */	public String getReference(){
		return reference;
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

