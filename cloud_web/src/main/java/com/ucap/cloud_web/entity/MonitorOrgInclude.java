package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-02 11:00:54 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class MonitorOrgInclude {


	//	private int id;
	//网站标识码	private String siteCode;
	//站点数	private int siteSum;
	//百度收录数_站点	private int baiduSiteSum;
	//360收录数_站点	private int qihuSiteSum;
	//搜狗收录数_站点	private int sogouSiteSum;
	//谷歌收录数_站点	private int googleSiteSum;
	//百度收录数_域	private int baiduDomainSum;
	//360收录数_域	private int qihuDomainSum;
	//搜狗收录数_域	private int sogouDomainSum;
	//谷歌收录数_域	private int googleDomainSum;
	//百度收录平均数_站点	private int baiduSiteAvg;
	//360收录平均数_站点	private int qihuSiteAvg;
	//搜狗收录平均数_站点	private int sogouSiteAvg;
	//谷歌收录平均数_站点	private int googleSiteAvg;
	//百度收录平均数_域	private int baiduDomainAvg;
	//360收录平均数_域	private int qihuDomainAvg;
	//搜狗收录平均数_域	private int sogouDomainAvg;
	//谷歌收录平均数_域	private int googleDomainAvg;
	//扫描日期	private String scanDate;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//网站名称
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 站点数 */	public void setSiteSum(int siteSum){
		this.siteSum=siteSum;
	}
	/** get 站点数 */	public int getSiteSum(){
		return siteSum;
	}
	/** set 百度收录数_站点 */	public void setBaiduSiteSum(int baiduSiteSum){
		this.baiduSiteSum=baiduSiteSum;
	}
	/** get 百度收录数_站点 */	public int getBaiduSiteSum(){
		return baiduSiteSum;
	}
	/** set 360收录数_站点 */	public void setQihuSiteSum(int qihuSiteSum){
		this.qihuSiteSum=qihuSiteSum;
	}
	/** get 360收录数_站点 */	public int getQihuSiteSum(){
		return qihuSiteSum;
	}
	/** set 搜狗收录数_站点 */	public void setSogouSiteSum(int sogouSiteSum){
		this.sogouSiteSum=sogouSiteSum;
	}
	/** get 搜狗收录数_站点 */	public int getSogouSiteSum(){
		return sogouSiteSum;
	}
	/** set 谷歌收录数_站点 */	public void setGoogleSiteSum(int googleSiteSum){
		this.googleSiteSum=googleSiteSum;
	}
	/** get 谷歌收录数_站点 */	public int getGoogleSiteSum(){
		return googleSiteSum;
	}
	/** set 百度收录数_域 */	public void setBaiduDomainSum(int baiduDomainSum){
		this.baiduDomainSum=baiduDomainSum;
	}
	/** get 百度收录数_域 */	public int getBaiduDomainSum(){
		return baiduDomainSum;
	}
	/** set 360收录数_域 */	public void setQihuDomainSum(int qihuDomainSum){
		this.qihuDomainSum=qihuDomainSum;
	}
	/** get 360收录数_域 */	public int getQihuDomainSum(){
		return qihuDomainSum;
	}
	/** set 搜狗收录数_域 */	public void setSogouDomainSum(int sogouDomainSum){
		this.sogouDomainSum=sogouDomainSum;
	}
	/** get 搜狗收录数_域 */	public int getSogouDomainSum(){
		return sogouDomainSum;
	}
	/** set 谷歌收录数_域 */	public void setGoogleDomainSum(int googleDomainSum){
		this.googleDomainSum=googleDomainSum;
	}
	/** get 谷歌收录数_域 */	public int getGoogleDomainSum(){
		return googleDomainSum;
	}
	/** set 百度收录平均数_站点 */	public void setBaiduSiteAvg(int baiduSiteAvg){
		this.baiduSiteAvg=baiduSiteAvg;
	}
	/** get 百度收录平均数_站点 */	public int getBaiduSiteAvg(){
		return baiduSiteAvg;
	}
	/** set 360收录平均数_站点 */	public void setQihuSiteAvg(int qihuSiteAvg){
		this.qihuSiteAvg=qihuSiteAvg;
	}
	/** get 360收录平均数_站点 */	public int getQihuSiteAvg(){
		return qihuSiteAvg;
	}
	/** set 搜狗收录平均数_站点 */	public void setSogouSiteAvg(int sogouSiteAvg){
		this.sogouSiteAvg=sogouSiteAvg;
	}
	/** get 搜狗收录平均数_站点 */	public int getSogouSiteAvg(){
		return sogouSiteAvg;
	}
	/** set 谷歌收录平均数_站点 */	public void setGoogleSiteAvg(int googleSiteAvg){
		this.googleSiteAvg=googleSiteAvg;
	}
	/** get 谷歌收录平均数_站点 */	public int getGoogleSiteAvg(){
		return googleSiteAvg;
	}
	/** set 百度收录平均数_域 */	public void setBaiduDomainAvg(int baiduDomainAvg){
		this.baiduDomainAvg=baiduDomainAvg;
	}
	/** get 百度收录平均数_域 */	public int getBaiduDomainAvg(){
		return baiduDomainAvg;
	}
	/** set 360收录平均数_域 */	public void setQihuDomainAvg(int qihuDomainAvg){
		this.qihuDomainAvg=qihuDomainAvg;
	}
	/** get 360收录平均数_域 */	public int getQihuDomainAvg(){
		return qihuDomainAvg;
	}
	/** set 搜狗收录平均数_域 */	public void setSogouDomainAvg(int sogouDomainAvg){
		this.sogouDomainAvg=sogouDomainAvg;
	}
	/** get 搜狗收录平均数_域 */	public int getSogouDomainAvg(){
		return sogouDomainAvg;
	}
	/** set 谷歌收录平均数_域 */	public void setGoogleDomainAvg(int googleDomainAvg){
		this.googleDomainAvg=googleDomainAvg;
	}
	/** get 谷歌收录平均数_域 */	public int getGoogleDomainAvg(){
		return googleDomainAvg;
	}
	/** set 扫描日期 */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 扫描日期 */	public String getScanDate(){
		return scanDate;
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

