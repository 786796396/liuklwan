package com.ucap.cloud_web.entity;

import java.util.List;

/**
 * 描述： 大数据分析 解析xml返回集合对象
 * 包：com.ucap.cloud_web.entity
 * 文件名称：BigDateResults
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-5-26下午1:42:40 
 * @version V1.0
 */
public class Result {
	private String taskid;//任务id
	private String name;//组织单位名称
	private String sitenum;//网站个数
	private Integer linkerrsitenum;//当日连不通网站数量(100%连不通)
	private Integer linkerrsitenum7;//7日连不通网站数量(100%连不通)
	private Integer linkerrsitenum14;//14日连不通网站数量(100%连不通)
	private Integer linkerrsitenum30;//30日连不通网站数量(100%连不通)
	private Double linkerrsiteprop;//当日连不通网站占比 /linkerrsiteprop>
	private Double linkerrsiteprop7;//7日连不通网站占比均值 /linkerrsiteprop7>
	private Double linkerrsiteprop14;//14日连不通网站占比均值 /linkerrsiteprop14>
	private Double linkerrsiteprop30;//30日连不通网站占比均值 /linkerrsiteprop30>
	private Double indexdeadnum;//当日死链均值 /indexdeadnum>
	private Double indexdeadnum7;//7日死链均值 /indexdeadnum7>
	private Double indexdeadnum14;//14日死链均值 /indexdeadnum14>
	private Double indexdeadnum30;//30日死链均值 /indexdeadnum30>
	private Double indexdeadprop;//当日死链占比均值 /indexdeadprop>
	private Double indexdeadprop7;//7日死链占比均值 /indexdeadprop7>
	private Double indexdeadprop14;//14日死链占比均值 /indexdeadprop14>
	private Double indexdeadprop30;//30日死链占比均值 /indexdeadprop30>
	private Integer indexdeadsitenum;//当日出现死链站点数 /indexdeadsitenum>
	private Double indexdeadsiteprop;//当日出现死链站点占比 /indexdeadsiteprop>
	private Double indexdeadsiteprop7;//7日出现死链站点占比 /indexdeadsiteprop7>
	private Double indexdeadsiteprop14;//14日出现死链站点占比 /indexdeadsiteprop14>
	private Double indexdeadsiteprop30;//30日出现死链站点占比 /indexdeadsiteprop30>
	private Integer noupdatesitenum;//当日未更新站点数 
	private Integer noupdatesitenum7;//7日未更新站点数 /noupdatesitenum7>
	private Integer noupdatesitenum14;//14日未更新站点数 /noupdatesitenum14>
	private Integer noupdatesitenum30;//>30日未更新站点数 /noupdatesitenum30>
	private Double noupdatesiteprop;//>当日未更新网站占比 /noupdatesiteprop>
	private Double noupdatesiteprop7;//>7日未更新网站占比 /noupdatesiteprop7>
	private Double noupdatesiteprop14;//>14日未更新网站占比 /noupdatesiteprop14>
	private Double noupdatesiteprop30;//>30日未更新网站占比 /noupdatesiteprop30>
	private Integer updatenum;//>当日更新量总和 /updatenum>
	private Integer updatenum7;//>7日更新量总和 /updatenum7>
	private Integer updatenum14;//>14日更新量总和 /updatenum14>
	private Integer updatenum30;//>30日更新量总和 /updatenum30>
	private Double aveupdatenum;//>当日更新量均值 /aveupdatenum>
	private Double aveupdatenum7;//>7日更新量均值 /aveupdatenum7>
	private Double aveupdatenum14;//>14日更新量均值 /aveupdatenum14>
	private Double aveupdatenum30;//>30日更新量均值 /aveupdatenum30>
	
	private String sitecode;//站点标识
	private Double linkerrprop;//>当日连不通占比 /linkerrprop>
	private Double linkerrprop7;//>7日连不通占比均值 /linkerrprop7>
	private Double linkerrprop14;//>14日连不通占比均值 /linkerrprop14>
	private Double linkerrprop30;//>30日连不通占比均值 /linkerrprop30>
	private String  updatestatus;//>当日是否更新 /updatestatus>
	private String  updatestatus7;//>7日是否更新 /updatestatus7>
	private String  updatestatus14;//>14日是否更新 /updatestatus14>
	private String  updatestatus30;//>30日是否更新 /updatestatus30>
	
	private String healthindex;//昨日健康指数
	private String healthindex7;//昨日健康指数7天
	private String healthindex14;//昨日健康指数14天
	private String healthindex30;//昨日健康指数30天
	
	private Integer linkerrnum;//连不通个数
	private String lastupdatedate;//最后更新时间
	private Integer noupdateday;//未更新天数
	private String updatecodes;//更新任务首页返回码
	private String province;//省
	private String city;//市
	private String county;//县
	private String url;//网址
	//通用性
	private String encodeurl;  // md5加密后url
	private String failnum; // 连不通次数
	//通用性返回结果集
	private List<Detail> details;
	
	//采集时间
	private String crawlDate;
	//百度收录数_站点
	private Integer baiduSlWebsite;
	//百度收录数_域
	private Integer baiduSlDomainsite;
	//百度收录平均数_站点
	private Integer baiduSiteAvg;
	//百度收录平均数_域
	private Integer baiduDomainAvg;
	//组织单位采集数量
	private Integer siteSum;
	//扫描时间
	private String scanDate;
	
	//关键栏目连通性 
	private String stime;//时间
	private String code;// 问题代码
	
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSitenum() {
		return sitenum;
	}
	public void setSitenum(String sitenum) {
		this.sitenum = sitenum;
	}
	public Integer getLinkerrsitenum() {
		return linkerrsitenum;
	}
	public void setLinkerrsitenum(Integer linkerrsitenum) {
		this.linkerrsitenum = linkerrsitenum;
	}
	public Integer getLinkerrsitenum7() {
		return linkerrsitenum7;
	}
	public void setLinkerrsitenum7(Integer linkerrsitenum7) {
		this.linkerrsitenum7 = linkerrsitenum7;
	}
	public Integer getLinkerrsitenum14() {
		return linkerrsitenum14;
	}
	public void setLinkerrsitenum14(Integer linkerrsitenum14) {
		this.linkerrsitenum14 = linkerrsitenum14;
	}
	public Integer getLinkerrsitenum30() {
		return linkerrsitenum30;
	}
	public void setLinkerrsitenum30(Integer linkerrsitenum30) {
		this.linkerrsitenum30 = linkerrsitenum30;
	}
	public Double getLinkerrsiteprop() {
		return linkerrsiteprop;
	}
	public void setLinkerrsiteprop(Double linkerrsiteprop) {
		this.linkerrsiteprop = linkerrsiteprop;
	}
	public Double getLinkerrsiteprop7() {
		return linkerrsiteprop7;
	}
	public void setLinkerrsiteprop7(Double linkerrsiteprop7) {
		this.linkerrsiteprop7 = linkerrsiteprop7;
	}
	public Double getLinkerrsiteprop14() {
		return linkerrsiteprop14;
	}
	public void setLinkerrsiteprop14(Double linkerrsiteprop14) {
		this.linkerrsiteprop14 = linkerrsiteprop14;
	}
	public Double getLinkerrsiteprop30() {
		return linkerrsiteprop30;
	}
	public void setLinkerrsiteprop30(Double linkerrsiteprop30) {
		this.linkerrsiteprop30 = linkerrsiteprop30;
	}

	public Double getIndexdeadprop() {
		return indexdeadprop;
	}
	public void setIndexdeadprop(Double indexdeadprop) {
		this.indexdeadprop = indexdeadprop;
	}
	public Double getIndexdeadprop7() {
		return indexdeadprop7;
	}
	public void setIndexdeadprop7(Double indexdeadprop7) {
		this.indexdeadprop7 = indexdeadprop7;
	}
	public Double getIndexdeadprop14() {
		return indexdeadprop14;
	}
	public void setIndexdeadprop14(Double indexdeadprop14) {
		this.indexdeadprop14 = indexdeadprop14;
	}
	public Double getIndexdeadprop30() {
		return indexdeadprop30;
	}
	public void setIndexdeadprop30(Double indexdeadprop30) {
		this.indexdeadprop30 = indexdeadprop30;
	}
	public Integer getIndexdeadsitenum() {
		return indexdeadsitenum;
	}
	public void setIndexdeadsitenum(Integer indexdeadsitenum) {
		this.indexdeadsitenum = indexdeadsitenum;
	}
	public Double getIndexdeadsiteprop() {
		return indexdeadsiteprop;
	}
	public void setIndexdeadsiteprop(Double indexdeadsiteprop) {
		this.indexdeadsiteprop = indexdeadsiteprop;
	}
	public Double getIndexdeadsiteprop7() {
		return indexdeadsiteprop7;
	}
	public void setIndexdeadsiteprop7(Double indexdeadsiteprop7) {
		this.indexdeadsiteprop7 = indexdeadsiteprop7;
	}
	public Double getIndexdeadsiteprop14() {
		return indexdeadsiteprop14;
	}
	public void setIndexdeadsiteprop14(Double indexdeadsiteprop14) {
		this.indexdeadsiteprop14 = indexdeadsiteprop14;
	}
	public Double getIndexdeadsiteprop30() {
		return indexdeadsiteprop30;
	}
	public void setIndexdeadsiteprop30(Double indexdeadsiteprop30) {
		this.indexdeadsiteprop30 = indexdeadsiteprop30;
	}
	public Integer getNoupdatesitenum() {
		return noupdatesitenum;
	}
	public void setNoupdatesitenum(Integer noupdatesitenum) {
		this.noupdatesitenum = noupdatesitenum;
	}
	public Integer getNoupdatesitenum7() {
		return noupdatesitenum7;
	}
	public void setNoupdatesitenum7(Integer noupdatesitenum7) {
		this.noupdatesitenum7 = noupdatesitenum7;
	}
	public Integer getNoupdatesitenum14() {
		return noupdatesitenum14;
	}
	public void setNoupdatesitenum14(Integer noupdatesitenum14) {
		this.noupdatesitenum14 = noupdatesitenum14;
	}
	public Integer getNoupdatesitenum30() {
		return noupdatesitenum30;
	}
	public void setNoupdatesitenum30(Integer noupdatesitenum30) {
		this.noupdatesitenum30 = noupdatesitenum30;
	}
	public Double getNoupdatesiteprop() {
		return noupdatesiteprop;
	}
	public void setNoupdatesiteprop(Double noupdatesiteprop) {
		this.noupdatesiteprop = noupdatesiteprop;
	}
	public Double getNoupdatesiteprop7() {
		return noupdatesiteprop7;
	}
	public void setNoupdatesiteprop7(Double noupdatesiteprop7) {
		this.noupdatesiteprop7 = noupdatesiteprop7;
	}
	public Double getNoupdatesiteprop14() {
		return noupdatesiteprop14;
	}
	public void setNoupdatesiteprop14(Double noupdatesiteprop14) {
		this.noupdatesiteprop14 = noupdatesiteprop14;
	}
	public Double getNoupdatesiteprop30() {
		return noupdatesiteprop30;
	}
	public void setNoupdatesiteprop30(Double noupdatesiteprop30) {
		this.noupdatesiteprop30 = noupdatesiteprop30;
	}
	public Integer getUpdatenum() {
		return updatenum;
	}
	public void setUpdatenum(Integer updatenum) {
		this.updatenum = updatenum;
	}
	public Integer getUpdatenum7() {
		return updatenum7;
	}
	public void setUpdatenum7(Integer updatenum7) {
		this.updatenum7 = updatenum7;
	}
	public Integer getUpdatenum14() {
		return updatenum14;
	}
	public void setUpdatenum14(Integer updatenum14) {
		this.updatenum14 = updatenum14;
	}
	public Integer getUpdatenum30() {
		return updatenum30;
	}
	public void setUpdatenum30(Integer updatenum30) {
		this.updatenum30 = updatenum30;
	}
	public Double getAveupdatenum() {
		return aveupdatenum;
	}
	public void setAveupdatenum(Double aveupdatenum) {
		this.aveupdatenum = aveupdatenum;
	}
	public Double getAveupdatenum7() {
		return aveupdatenum7;
	}
	public void setAveupdatenum7(Double aveupdatenum7) {
		this.aveupdatenum7 = aveupdatenum7;
	}
	public Double getAveupdatenum14() {
		return aveupdatenum14;
	}
	public void setAveupdatenum14(Double aveupdatenum14) {
		this.aveupdatenum14 = aveupdatenum14;
	}
	public Double getAveupdatenum30() {
		return aveupdatenum30;
	}
	public void setAveupdatenum30(Double aveupdatenum30) {
		this.aveupdatenum30 = aveupdatenum30;
	}
	public String getSitecode() {
		return sitecode;
	}
	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}
	public Double getLinkerrprop() {
		return linkerrprop;
	}
	public void setLinkerrprop(Double linkerrprop) {
		this.linkerrprop = linkerrprop;
	}
	public Double getLinkerrprop7() {
		return linkerrprop7;
	}
	public void setLinkerrprop7(Double linkerrprop7) {
		this.linkerrprop7 = linkerrprop7;
	}
	public Double getLinkerrprop14() {
		return linkerrprop14;
	}
	public void setLinkerrprop14(Double linkerrprop14) {
		this.linkerrprop14 = linkerrprop14;
	}
	public Double getLinkerrprop30() {
		return linkerrprop30;
	}
	public void setLinkerrprop30(Double linkerrprop30) {
		this.linkerrprop30 = linkerrprop30;
	}
	public String getUpdatestatus() {
		return updatestatus;
	}
	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}
	public String getUpdatestatus7() {
		return updatestatus7;
	}
	public void setUpdatestatus7(String updatestatus7) {
		this.updatestatus7 = updatestatus7;
	}
	public String getUpdatestatus14() {
		return updatestatus14;
	}
	public void setUpdatestatus14(String updatestatus14) {
		this.updatestatus14 = updatestatus14;
	}
	public String getUpdatestatus30() {
		return updatestatus30;
	}
	public void setUpdatestatus30(String updatestatus30) {
		this.updatestatus30 = updatestatus30;
	}
	public String getLastupdatedate() {
		return lastupdatedate;
	}
	public void setLastupdatedate(String lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}
	public Integer getNoupdateday() {
		return noupdateday;
	}
	public void setNoupdateday(Integer noupdateday) {
		this.noupdateday = noupdateday;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getUrl() {
		return url;
	}
	public Double getIndexdeadnum() {
		return indexdeadnum;
	}
	public void setIndexdeadnum(Double indexdeadnum) {
		this.indexdeadnum = indexdeadnum;
	}
	public Double getIndexdeadnum7() {
		return indexdeadnum7;
	}
	public void setIndexdeadnum7(Double indexdeadnum7) {
		this.indexdeadnum7 = indexdeadnum7;
	}
	public Double getIndexdeadnum14() {
		return indexdeadnum14;
	}
	public void setIndexdeadnum14(Double indexdeadnum14) {
		this.indexdeadnum14 = indexdeadnum14;
	}
	public Double getIndexdeadnum30() {
		return indexdeadnum30;
	}
	public void setIndexdeadnum30(Double indexdeadnum30) {
		this.indexdeadnum30 = indexdeadnum30;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUpdatecodes() {
		return updatecodes;
	}
	public void setUpdatecodes(String updatecodes) {
		this.updatecodes = updatecodes;
	}
	public Integer getLinkerrnum() {
		return linkerrnum;
	}
	public void setLinkerrnum(Integer linkerrnum) {
		this.linkerrnum = linkerrnum;
	}
	public List<Detail> getDetails() {
		return details;
	}
	public void setDetails(List<Detail> details) {
		this.details = details;
	}
	public String getEncodeurl() {
		return encodeurl;
	}
	public void setEncodeurl(String encodeurl) {
		this.encodeurl = encodeurl;
	}
	public String getCrawlDate() {
		return crawlDate;
	}
	public void setCrawlDate(String crawlDate) {
		this.crawlDate = crawlDate;
	}
	public Integer getBaiduSlWebsite() {
		return baiduSlWebsite;
	}
	public void setBaiduSlWebsite(Integer baiduSlWebsite) {
		this.baiduSlWebsite = baiduSlWebsite;
	}
	public Integer getBaiduSlDomainsite() {
		return baiduSlDomainsite;
	}
	public void setBaiduSlDomainsite(Integer baiduSlDomainsite) {
		this.baiduSlDomainsite = baiduSlDomainsite;
	}
	public Integer getBaiduSiteAvg() {
		return baiduSiteAvg;
	}
	public void setBaiduSiteAvg(Integer baiduSiteAvg) {
		this.baiduSiteAvg = baiduSiteAvg;
	}
	public Integer getBaiduDomainAvg() {
		return baiduDomainAvg;
	}
	public void setBaiduDomainAvg(Integer baiduDomainAvg) {
		this.baiduDomainAvg = baiduDomainAvg;
	}
	public Integer getSiteSum() {
		return siteSum;
	}
	public void setSiteSum(Integer siteSum) {
		this.siteSum = siteSum;
	}
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public String getFailnum() {
		return failnum;
	}

	public void setFailnum(String failnum) {
		this.failnum = failnum;
	}
	public String getHealthindex() {
		return healthindex;
	}
	public void setHealthindex(String healthindex) {
		this.healthindex = healthindex;
	}
	public String getHealthindex7() {
		return healthindex7;
	}
	public void setHealthindex7(String healthindex7) {
		this.healthindex7 = healthindex7;
	}
	public String getHealthindex14() {
		return healthindex14;
	}
	public void setHealthindex14(String healthindex14) {
		this.healthindex14 = healthindex14;
	}
	public String getHealthindex30() {
		return healthindex30;
	}
	public void setHealthindex30(String healthindex30) {
		this.healthindex30 = healthindex30;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
