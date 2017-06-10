package com.ucap.cloud_web.timer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.EarlyType;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.EarlyInfoRequest;
import com.ucap.cloud_web.dto.LinkHomeAvailableRequest;
import com.ucap.cloud_web.dto.MTaskdetailRequest;
import com.ucap.cloud_web.dto.MTaskoverviewRequest;
import com.ucap.cloud_web.dto.SecurityBlankDetailRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.dto.SecurityResponseRequest;
import com.ucap.cloud_web.dto.UpdateContentCountRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseLink;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DicConfig;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.entity.EarlyInfo;
import com.ucap.cloud_web.entity.MTaskoverview;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.service.IEarlyDetailService;
import com.ucap.cloud_web.service.IEarlyInfoService;
import com.ucap.cloud_web.service.ILinkHomeAvailableService;
import com.ucap.cloud_web.service.IMTaskdetailService;
import com.ucap.cloud_web.service.IMTaskoverviewService;
import com.ucap.cloud_web.service.ISecurityBlankDetailService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.service.ISecurityResponseService;
import com.ucap.cloud_web.service.IUpdateContentCountService;



/**
 * <p>Description:统计所有的预警数据放入预警详情表--每日统计一次（统计昨天的数据）
	 * 	7首页连不通比例超过3%,
	 * 	9首页不更新超过10天,
	 *  10空白栏目超过2个,
	 *  11栏目不更新超过8个
 *  </p>
 * <p>@Package：com.ucap.cloud_web.timer </p>
 * <p>Title: TaskAddEarlyDetail </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2016-7-13下午4:38:18 </p>
 */
@Service("taskAddEarly")
public class TaskAddEarly {

	private static Logger logger = Logger.getLogger(TaskAddEarly.class);//日志信息
	
	
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	
	@Autowired
	private IEarlyDetailService earlyDetailServiceImpl;
	
	@Autowired
	private IEarlyInfoService earlyInfoServiceImpl;
	
	@Autowired
	private IConnectionAllService connectionAllServiceImpl;
	
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	
/*	@Autowired
	private IDatabaseLinkService databaseLinkServiceImpl;*/
	
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	
	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;
	
	@Autowired
	private IUpdateContentCountService updateContentCountServiceImpl;
	
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	
	@Autowired
	private ISecurityResponseService securityResponseServiceImpl;
	
	@Autowired
	private ISecurityBlankDetailService securityBlankDetailServiceImpl;
	
	@Autowired
	private IMTaskdetailService MTaskdetailServiceImpl;
	
	@Autowired
	private IMTaskoverviewService MTaskoverviewServiceImpl;
	
	@Autowired
	private ILinkHomeAvailableService linkHomeAvailableServiceImpl;
	
	@Autowired
	private IDicConfigService  dicConfigServiceImpl;
	
	/**=============================日报预警数据统计（定时发送）==============================================*/
	/**
	 * @Description: 站群日报统计数据存放到预警详情表中
	 * 老版本
		 	1、有效监测站点：n 个
			2、100%不连通网站个数：a 个
			3、不连通总次数占连接总次数：b%
			4、平均每个站点的首页发现死链：c 个
			5、首页超过两周未更新的网站数：x 个
			6、首页或栏目共更新：y 条
			7、平均每站点更新：m 条
			
		新版本
			1、有效监测站点：n 个
			2、首页连不通站点数：3个站点22次
			3、首页7日连不通率超3%站点：3个
			4、7日总连不通率：28.57%
			5、首页链接不可用：3站点24条
			6、内容更新量：3站点24条更新
			7、首页更新不及时：3站点
			8、栏目更新不及时：3站点24个栏目
	 * @author cuichx --- 2016-7-25下午6:28:50
	 */
	public void addEarlyDayReport(){
		
		logger.info("==========addEarlyDayReport==============begin");
		/**
		 * 获取所有付费组织机构编码(6位标识码)
		 */
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("nowTime", DateUtils.formatStandardDateTime(new Date()));
		map.put("executeStatus", 1);//合同状态为1-服务中
		map.put("typeFlag",1);//非抽查合同
		map.put("orgFlag", 1);//只查询组织机构合同
		
		List<ContractInfo>  conList=contractInfoServiceImpl.queryContractByMap(map);
		if(conList!=null && conList.size()>0){
			for (int i = 0; i < conList.size(); i++) {
				//组织机构编码
				String orgSiteCode=conList.get(i).getSiteCode();
				String scanDate=DateUtils.getYesterdayStr();//获取昨天日期
				
				logger.info("addEarlyDayReport orgSiteCode:"+orgSiteCode+"  scanDate:"+scanDate);
				List<String> linkList=getScanList(orgSiteCode,1);//全面检测中的站点
				if(linkList!=null && linkList.size()>0){
					////获取有效监测站点 收费 检测中（不包含关停例外）
					int scanNum=linkList.size();
					int notScanNum=0;
					
					List<String> notlinkList=getScanList(orgSiteCode,2);//异常站点
					if(notlinkList!=null && notlinkList.size()>0){
						notScanNum=notlinkList.size();
					}
					//首页连不通站点数：3个站点22次
					int notConnectionNum=getConnDataNum(linkList, scanDate);
					int notConnectionSum=getConnDataSum(linkList, scanDate);
					
					//首页7日连不通率超3%站点：3个
					int notConnectionPer7=getConnPerMore7(linkList);
					
					//7日总连不通率：28.57%
					String notConnectionPer=getConnPer7(orgSiteCode);
					
					//首页链接不可用：3站点24条   至查询确定死链
					int linkHomeNum=getHomeLinkNum(linkList,scanDate,1);
					int linkHomeSum=getHomeLinkNum(linkList,scanDate,2);
					
					//首页更新不及时：3站点  超过14天
					int notUpdateHome=getHomeNotUpdateData(linkList,scanDate);
					
					//内容更新量：3站点24条更新
					int updateNum=getUpdateContentNum(linkList, scanDate);
					int updateAvgNum=getUpdateContentData(linkList,scanDate);

					//栏目更新不及时：3站点24个栏目
					int noUpdateChannelNum=getChannelUpdateNum(linkList,scanDate);
					int noUpdateChannelSum=getChannelUpdateSum(linkList,scanDate);
					
					
					//预警信息表中添加数据
					Map<String, Object> paramMap=new HashMap<String, Object>();
					paramMap.put("scanNum", scanNum);
					paramMap.put("notScanNum", notScanNum);
					paramMap.put("notConnectionNum", notConnectionNum);
					paramMap.put("notConnectionSum", notConnectionSum);
					paramMap.put("notConnectionPer7", notConnectionPer7);
					paramMap.put("notConnectionPer", notConnectionPer);
					paramMap.put("linkHomeNum", linkHomeNum);
					paramMap.put("linkHomeSum", linkHomeSum);
					paramMap.put("updateNum", updateNum);
					paramMap.put("updateAvgNum", updateAvgNum);
					paramMap.put("notUpdateHome", notUpdateHome);
					paramMap.put("noUpdateChannelNum", noUpdateChannelNum);
					paramMap.put("noUpdateChannelSum", noUpdateChannelSum);
					
					paramMap.put("orgSiteCode", orgSiteCode);
					paramMap.put("scanDate", scanDate);
					paramMap.put("orgSendStatus", 2);
					
					addEarlyData(paramMap);
					
				}
			}
		}
	}
	/**================================单站预警数据统计（定时发送）=====================================*/
	/**
	 * @Description: 统计以下几类数据，存放到预警详情表中
	 *  8 首页连不通比例超过3%,
	 * 	10  首页超过10天未更新,
	 *  11 空白栏目超过2个,
	 *  12  栏目不更新超过6个
	 *  13 互动回应栏目超过3个月未回应
	 * @author cuichx --- 2016-7-13下午5:07:52
	 */
	public void addEarlyDay(){
		
		try {
			logger.info("==========addEarlyDay==============begin");
			//获取所有的收费用户（6位或10位）
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("nowTime", DateUtils.formatStandardDateTime(new Date()));
			map.put("executeStatus", 1);//合同状态1-服务中
			map.put("typeFlag",1);//非抽查合同
			List<ContractInfo>  conList=contractInfoServiceImpl.queryContractByMap(map);
			if(conList!=null && conList.size()>0){//收费用户
				for (int i = 0; i < conList.size(); i++) {
					//组织机构编码或网站标识码
					String siteCode=conList.get(i).getSiteCode();
					//String scanDate=DateUtils.getNextDay(new Date(), -1);//获取昨天日期
					if(siteCode.length()==10){//网站标识码
						//当前日期前推7天  首页连不通比例超过3%
						addHomeConnData(siteCode,null);
						
						//同一监测周期  空白栏目个数>2个
						addBlankChannel(siteCode,null);
						
						//同一监测周期  基本信息不更新>6个
						addBasicNotUpdate(siteCode,null);
						
						//同一监测周期  互动回应栏目超过3个月未回应
						addResponseNotUpdate(siteCode,null);
						
					}else{//组织机构编码
						String[] LayerTypeArr={"1","2","3","6"};
						DatabaseTreeInfoRequest treeInfoRequest=new DatabaseTreeInfoRequest();
						treeInfoRequest.setOrgSiteCode(siteCode);//组织机构编码
						treeInfoRequest.setIsLink(1);
						treeInfoRequest.setLayerTypeArr(LayerTypeArr);//层级类型；（-1默认无用,1本级门户，2本级部门，3下属单位，6其他）
						treeInfoRequest.setPageSize(Integer.MAX_VALUE);
						
						List<DatabaseTreeInfo> linkList=databaseTreeInfoServiceImpl.queryList(treeInfoRequest);
						if(linkList!=null && linkList.size()>0){
							 
							//当前日期前推7天  首页连不通比例超过3%
							addHomeConnData(null,linkList);
							 
							//首页超过10天未更新--暂时不用
							//addHomeNotUpdate(null,scanDate,linkList);
							
							//同一监测周期  空白栏目个数>2个
							addBlankChannel(null,linkList);
							
							//同一监测周期  基本信息不更新>6个
							addBasicNotUpdate(null,linkList);
							
							//同一监测周期  互动回应栏目超过3个月未回应
							addResponseNotUpdate(null,linkList);
						 }
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
	/**
	 * @Description:预警详情表和预警统计表中添加或者更新数据 
	 * @author cuichx --- 2016-8-24上午9:07:47     
	 * @param paramMap
	 */
	private void addEarlyData(Map<String, Object> paramMap) {
		String orgSiteCode=String.valueOf(paramMap.get("orgSiteCode"));//组织机构编码
		String scanDate=String.valueOf(paramMap.get("scanDate"));//昨日日期YYYY-MM-DD
		
		String scanNum=String.valueOf(paramMap.get("scanNum"));//有效监测站点个数
		String notScanNum=String.valueOf(paramMap.get("notScanNum"));//监测异常站点个数
		String notConnectionNum=String.valueOf(paramMap.get("notConnectionNum"));//首页连不通（站点数）
		String notConnectionSum=String.valueOf(paramMap.get("notConnectionSum"));//首页连不通（次数）
		
		String notConnectionPer7=String.valueOf(paramMap.get("notConnectionPer7"));//首页7日连不通率超3%站点数
		String notConnectionPer=String.valueOf(paramMap.get("notConnectionPer"));//7日总连不通率
		
		String linkHomeNum=String.valueOf(paramMap.get("linkHomeNum"));//首页链接不可用（站点数）
		String linkHomeSum=String.valueOf(paramMap.get("linkHomeSum"));//首页链接不可用（站点数）
		
		String notUpdateHome=String.valueOf(paramMap.get("notUpdateHome"));//首页未更新天数
		
		String updateNum=String.valueOf(paramMap.get("updateNum"));//内容更新量（站点数）
		String updateAvgNum=String.valueOf(paramMap.get("updateAvgNum"));//内容更新量（条数）
		
		
		String noUpdateChannelNum=String.valueOf(paramMap.get("noUpdateChannelNum"));//栏目更新不及时（站点数）
		String noUpdateChannelSum=String.valueOf(paramMap.get("noUpdateChannelSum"));//栏目更新不及时（栏目数
		
		String orgSendStatus=String.valueOf(paramMap.get("orgSendStatus"));//组织单位是否发送日报
		
		logger.info("addEarlyData orgSiteCode:"+orgSiteCode+"  scanDate:"+scanDate+
				"  scanNum:"+scanNum+"  notConnectionNum:"+notConnectionNum+
				"  notConnectionSum:"+notConnectionSum+"  notConnectionPer7:"+notConnectionPer7+
				"  notConnectionPer:"+notConnectionPer+"  linkHomeNum:"+linkHomeNum+
				"  updateNum:"+updateNum+"  updateAvgNum:"+updateAvgNum+
				"  notUpdateHome:"+notUpdateHome+"  noUpdateChannelNum:"+noUpdateChannelNum+
				"  noUpdateChannelSum:"+noUpdateChannelSum+"  linkHomeSum:"+linkHomeSum);
		/**
		 * 去重操作   
		 * 		根据昨天日期和组织机构编码查看预警详情表，判断是否存在该记录
		 * 		如果不存在，添加到预警详情表中，并修改预警统计表中对应的记录
		 */
		EarlyDetailRequest earlyRequest=new EarlyDetailRequest();
		earlyRequest.setSiteCode(orgSiteCode);
		earlyRequest.setScanTime(scanDate);
		
		List<EarlyDetail> detailList=earlyDetailServiceImpl.queryList(earlyRequest);
		if(detailList==null || (detailList!=null && detailList.size()==0)){
			  /**
			   * 预警详情表里面添加数据
			   */
			 EarlyDetail earlyDetail=new EarlyDetail();
			 earlyDetail.setSiteCode(orgSiteCode);
			 earlyDetail.setDicChannelId(0);
			 earlyDetail.setScanTime(scanDate);
			 earlyDetail.setSendWxStatus(0);//微信发送状态（-1：不发送，0：未发送，1：发送成功，2：发送失败）
			 earlyDetail.setSendSmsStatus(0);//短信发送状态（-1：不发送，0：未发送，1：发送成功，2：发送失败）
			 earlyDetail.setSendEmailState(0);//自动邮件发送状态（-1：不发送，0：未发送，1：发送成功，2：发送失败）
			 
			 earlyDetail.setScanNum(Integer.valueOf(scanNum));////有效监测站点个数
			 earlyDetail.setBlankNum(Integer.valueOf(notScanNum));//异常站点个数
			 earlyDetail.setNotConnectionNum(Integer.valueOf(notConnectionNum));//首页连不通（站点数)
			 earlyDetail.setNotConnectionSum(Integer.valueOf(notConnectionSum));//首页连不通（次数）
			 earlyDetail.setNotConnectionPer7(notConnectionPer7);//首页7日连不通率超3%站点数
			 earlyDetail.setNotConnectionPer(notConnectionPer);//7日总连不通率
			 earlyDetail.setLinkHomeNum(linkHomeNum);//首页链接不可用（站点数）
			 earlyDetail.setLinkHomeSum(Integer.valueOf(linkHomeSum));//首页链接不可用链接（条数）
			 earlyDetail.setNotUpdateHome(Integer.valueOf(notUpdateHome));//首页更新不及时站点数（日报使用）
			 earlyDetail.setUpdateNum(Integer.valueOf(updateNum));//内容更新量（站点数)
			 earlyDetail.setUpdateAvgNum(updateAvgNum);//内容更新量（条数）
			 earlyDetail.setNoUpdateChannelNum(Integer.valueOf(noUpdateChannelNum));//栏目更新不及时（站点数）
			 earlyDetail.setNoUpdateChannelSum(Integer.valueOf(noUpdateChannelSum));////栏目更新不及时（栏目数）
			 earlyDetail.setOrgSendStatus(Integer.valueOf(orgSendStatus));
		
			 
			 earlyDetailServiceImpl.add(earlyDetail);
			 
			 //维护预警信息统计表中的数据
			 DatabaseOrgInfoRequest dataRequest=new DatabaseOrgInfoRequest();
			 dataRequest.setStieCode(orgSiteCode);
			 
			 String siteName="";
			 List<DatabaseOrgInfo> dataList=databaseOrgInfoServiceImpl.queryList(dataRequest);
			 if(dataList!=null && dataList.size()>0){
				 siteName=dataList.get(0).getName();
			 }
		
			/**
			 * 预警统计表  更新或者新增预警统计记录
			 */
			EarlyInfoRequest earlyInforequest = new EarlyInfoRequest();
			earlyInforequest.setSiteCode1(orgSiteCode);
			List<EarlyInfo> earlyInfoList = earlyInfoServiceImpl.queryList(earlyInforequest);
		
			EarlyInfo earlyInfo = null;
			if (earlyInfoList!=null &&  earlyInfoList.size() > 0) {
				earlyInfo = earlyInfoList.get(0);
				earlyInfo.setNewEarlyNum(earlyInfo.getNewEarlyNum() + 1);
				earlyInfo.setEarlySum(earlyInfo.getEarlySum() + 1);
				earlyInfoServiceImpl.update(earlyInfo);
			} else {
				earlyInfo = new EarlyInfo();
				earlyInfo.setSiteCode(orgSiteCode);
				earlyInfo.setSiteName(siteName);
				earlyInfo.setNewEarlyNum(1);
				earlyInfo.setEarlySum(1);
				earlyInfo.setState(0);//查看状态（0否、1是）
				
				earlyInfoServiceImpl.add(earlyInfo);
			}
		}
	

	}

	/**
	 * @Description: 获取当前组织机构下面的有效监测站点（收费、监测中、非关停例外）
	 * @author cuichx --- 2017-3-13下午1:43:04     
	 * @param orgSiteCode
	 * @return
	 */
	public List<String> getScanList(String orgSiteCode,Integer type) {
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orgSiteCode", orgSiteCode);
		if(type==1){//全面监测站点
			map.put("isScan", 1);
		}else if(type==2){
			map.put("notIsScan", 1);
		}
		List<String> scanList=databaseTreeInfoServiceImpl.queryDownSite(map);
		return scanList;
	}
	/**
	 * @Description: 栏目更新不及时--总次数
	 * @author cuichx --- 2017-3-14上午9:18:50     
	 * @param linkList
	 * @param scanDate
	 * @return
	 */
	private int getChannelUpdateSum(List<String> linkList,
			String scanDate){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("linkList", linkList);
		map.put("type", 2);
		map.put("scanDate", scanDate);
		int noUpdateChannelSum=securityHomeChannelServiceImpl.querySumByMap(map);
		return noUpdateChannelSum;
	}
	/**
	 * @Description: 栏目更新不及时（站点数）
	 * @author cuichx --- 2017-3-14上午9:19:08     
	 * @param linkList
	 * @param scanDate
	 * @return
	 */
	private int getChannelUpdateNum(List<String> linkList,
			String scanDate){
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("linkList", linkList);
		map.put("type", 2);
		map.put("scanDate", scanDate);
		int noUpdateChannelNum=securityHomeChannelServiceImpl.querySiteNumByMap(map);
		return noUpdateChannelNum;
	}

	/**
	 * @Description: 首页超过两周未更新的网站数
	 * @author cuichx --- 2016-8-23下午9:49:23     
	 * @param linkList
	 * @param scanDate
	 * @return
	 */
	private int getHomeNotUpdateData(List<String> linkList,
			String scanDate) {
		
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("dataLinkList", linkList);
		paramMap.put("scanDate", scanDate);
		paramMap.put("notUpdateNum", 14);
		paramMap.put("type", 1);
		
		int homeNotUpdateNum=0;
		homeNotUpdateNum=securityHomeChannelServiceImpl.queryHomeNotUpByMap(paramMap);
		
		return homeNotUpdateNum;
	}
	/**
	 * @Description: 内容更新量（站点数）
	 * @author cuichx --- 2017-3-13下午5:58:28     
	 * @param linkList
	 * @param scanDate
	 * @return
	 */
	private int getUpdateContentNum(List<String> linkList,
			String scanDate) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("linkList", linkList);
		paramMap.put("scanDate", scanDate);
		
		
		UpdateContentCountRequest contentRequest=new UpdateContentCountRequest();
		contentRequest.setScanDate(scanDate);
		contentRequest.setLinkList(linkList);
		contentRequest.setGroupBy("site_code");
		
		int updateNum=0;
		updateNum=updateContentCountServiceImpl.queryCount(contentRequest);
		
		return updateNum;
	}
	/**
	 * @Description: 内容更新量（条数）
	 * @author cuichx --- 2016-8-23下午10:05:43     
	 * @param linkList
	 * @param scanDate
	 * @return
	 */
	private int getUpdateContentData(List<String> linkList,
			String scanDate) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("dataLinkList", linkList);
		paramMap.put("scanDate", scanDate);
		
		int updateAvgNum=0;
		updateAvgNum=updateContentCountServiceImpl.queryUpdateConByMap(paramMap);
		
		return updateAvgNum;
	}
	/**
	 * @Description: 首页链接不可用
	 * @author cuichx --- 2017-3-13下午5:39:35     
	 * @param linkList
	 * @param scanDate
	 * @param type 1-站点数  2-条数
	 * @return
	 */
	private Integer getHomeLinkNum(List<String> linkList,String scanDate,Integer type){
		
		
		//查询配置表，获取确认死链的  问题编码
		DicConfig dicConfig=dicConfigServiceImpl.get(20);
		String[] codeArr=dicConfig.getValue().split(",");
		
		LinkHomeAvailableRequest homeRequest=new LinkHomeAvailableRequest();
		homeRequest.setScanDate(scanDate);
		homeRequest.setQuestionCodeArr(codeArr);
		homeRequest.setLinkList(linkList);
		if(type==1){
			homeRequest.setGroupBy("site_code");
		}
		
		int linkHomeNum=0;
		Integer num=linkHomeAvailableServiceImpl.queryCount(homeRequest);
		if(num!=null){
			linkHomeNum=num;
		}
		return linkHomeNum;
	}
	/**
	 * @Description: 首页7日连不通率--组织单位
	 * @author cuichx --- 2017-3-13下午4:09:04     
	 * @param orgSiteCode
	 * @return
	 */
	private String getConnPer7(String orgSiteCode){
		//昨天日期yyyyMMdd
		String scanDate=DateUtils.formatShortDate(DateUtils.getYesterdaytime());
		String notConnectionPer="0%";
		
		MTaskoverviewRequest mRequest=new MTaskoverviewRequest();
		mRequest.setTaskid(orgSiteCode);
		mRequest.setCountday(scanDate);
		List<MTaskoverview> mList=MTaskoverviewServiceImpl.queryList(mRequest);
		if(mList!=null && mList.size()>0){
			notConnectionPer=String.valueOf(mList.get(0).getLinkerrsiteprop7())+"%";
		}
		return notConnectionPer;
	}
	/**
	 * @Description: 首页7日连不通率超3%站点数
	 * @author cuichx --- 2017-3-13下午3:30:07     
	 * @return
	 */
	private int getConnPerMore7(List<String> linkList){
		//昨天日期yyyyMMdd
		String scanDate=DateUtils.formatShortDate(DateUtils.getYesterdaytime());
		
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("dataLinkList", linkList);
		paramMap.put("scanDate", scanDate);
		paramMap.put("linkerrprop7", 3);
		
		int notConnectionPer7=0;
		notConnectionPer7=MTaskdetailServiceImpl.queryConnPer(paramMap);
		
		return notConnectionPer7;
	}
	/**
	 * @Description:  首页连不通(站点数）
	 * @author cuichx --- 2017-3-13下午2:59:40     
	 * @param linkList
	 * @param scanDate
	 * @return
	 */
	private int getConnDataNum(List<String> linkList,String scanDate) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("type", 1);
		paramMap.put("dataLinkList", linkList);
		paramMap.put("scanDate", scanDate);
		paramMap.put("notConnFlag", 1);
		
		int notConnectionNum=0;
		notConnectionNum=connectionAllServiceImpl.queryConnCountByMap(paramMap);
		
		return notConnectionNum;
	}
	
	/**
	 * @Description: 首页连不通(总次数）
	 * @author cuichx --- 2017-3-13下午2:49:01     
	 * @param linkList
	 * @param scanDate
	 * @return
	 */
	private int getConnDataSum(List<String> linkList,String scanDate) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("type", 1);
		paramMap.put("dataLinkList", linkList);
		paramMap.put("scanDate", scanDate);
		paramMap.put("notConnFlag", 1);
		
		int notConnectionSum=0;
		notConnectionSum=connectionAllServiceImpl.queryConnCountByMap2(paramMap);
		
		return notConnectionSum;
	}

	/**
	 * @Description: 获取有效监测站点个数
	 * @author cuichx --- 2016-8-23下午7:29:24    
	 * @param linkList 
	 * @param scanDate 昨天日期
	 */
	@SuppressWarnings("unused")
	private int getScanSite(List<DatabaseLink> linkList, String scanDate) {
		
		DatabaseInfoRequest infoRequest=new DatabaseInfoRequest();
		infoRequest.setIsScan(1);//监测中
		infoRequest.setDatabaseLinkList(linkList);
		infoRequest.setPageSize(Integer.MAX_VALUE);
		
		int scanCount=0;
		scanCount=databaseInfoServiceImpl.queryCount(infoRequest);
		
		return scanCount;
	}

	
	
	/**
	 * @Description: 互动回应栏目超过3个月未回应
	 * @author cuichx --- 2016-10-25下午2:56:02     
	 * @param siteCode
	 * @param linkList
	 * @param scanDate
	 */
	private void addResponseNotUpdate(String siteCode, List<DatabaseTreeInfo> linkList) {
		Integer earlyType=EarlyType.SECURITY_RESPONSE.getCode();//互动回应栏目长期未回应
		
		logger.info("============addResponseNotUpdate=================");
		//封装查询条件
		Map<String, Object> paramMap=new HashMap<String, Object>();
		//paramMap.put("scanDate", DateUtils.formatStandardDate(new Date()));
		paramMap.put("scanDate", DateUtils.getYesterdayStr());
		paramMap.put("nowDate", DateUtils.formatStandardDate(new Date()));
		try {
			if(StringUtils.isNotEmpty(siteCode)){//填报单位买单
				paramMap.put("siteCode",siteCode);
				List<SecurityResponseRequest> responseList=securityResponseServiceImpl.queryResponseNum(paramMap);
				if(responseList!=null && responseList.size()>0){
					for (SecurityResponseRequest securityResponseRequest : responseList) {
						updateEarly(siteCode, null, earlyType, 
								securityResponseRequest.getChannelUrl(),
								null,null,null,null,
								securityResponseRequest.getProblemDesc(),null,
								securityResponseRequest.getServicePeriodId());
					}
				}
			}else if(linkList!=null && linkList.size()>0  ){//组织单位买单
				paramMap.put("linkList",linkList);
				List<SecurityResponseRequest> responseList=securityResponseServiceImpl.queryResponseNum(paramMap);
				if(responseList!=null && responseList.size()>0){
					for (SecurityResponseRequest securityResponseRequest : responseList) {
						updateEarly(securityResponseRequest.getSiteCode(), null, earlyType, 
								securityResponseRequest.getChannelUrl(),
								null,null,null,null,
								securityResponseRequest.getProblemDesc(),null,
								securityResponseRequest.getServicePeriodId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 同一监测周期  基本信息不更新>6个
	 * @author cuichx --- 2016-7-15上午11:54:38     
	 * @param siteCode
	 * @param linkList
	 */
	private void addBasicNotUpdate(String siteCode, List<DatabaseTreeInfo> linkList) {
		Integer earlyType=EarlyType.NOT_UPDATE_CHANNEL.getCode();//空白栏目超过4个
		
		//封装查询条件
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("scanDate", DateUtils.getYesterdayStr());
		paramMap.put("nowDate", DateUtils.formatStandardDate(new Date()));
		paramMap.put("basicNum", 6);
		paramMap.put("type", 2);
		
		try {
			if(StringUtils.isNotEmpty(siteCode)){//填报单位买单
				paramMap.put("siteCode",siteCode);
				List<SecurityHomeChannelRequest> basicList= securityHomeChannelServiceImpl.queryBasicNum(paramMap);
				if(basicList!=null && basicList.size()>0){
					for (SecurityHomeChannelRequest securityBasicInfoRequest : basicList) {
						Integer basicNum=securityBasicInfoRequest.getBasicNum();
						Integer servicePeriodId=securityBasicInfoRequest.getServicePeriodId();
						updateEarly(securityBasicInfoRequest.getSiteCode(), null, earlyType, 
								null, null,null,basicNum,null,null,null,servicePeriodId);
					}
				}
			}else if(linkList!=null && linkList.size()>0  ){//组织单位买单
				paramMap.put("linkList",linkList);
				List<SecurityHomeChannelRequest> basicList= securityHomeChannelServiceImpl.queryBasicNum(paramMap);
				if(basicList!=null && basicList.size()>0){
					for (SecurityHomeChannelRequest securityBasicInfoRequest : basicList) {
						Integer basicNum=securityBasicInfoRequest.getBasicNum();
						Integer servicePeriodId=securityBasicInfoRequest.getServicePeriodId();
						updateEarly(securityBasicInfoRequest.getSiteCode(), null, earlyType,
								null, null,null,basicNum,null,null,null,servicePeriodId);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 同一监测周期  空白栏目个数>=2个
	 * @author cuichx --- 2016-7-14下午3:52:42     
	 * @param siteCode
	 * @param linkList
	 */
	private void addBlankChannel(String siteCode, List<DatabaseTreeInfo> linkList) {
		Integer earlyType=EarlyType.BLANK_CHANNEL_MUCH.getCode();//空白栏目超过2个
		
		//封装查询条件
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("scanDate", DateUtils.getYesterdayStr());
		paramMap.put("nowDate", DateUtils.formatStandardDate(new Date()));
		paramMap.put("blankNum", 2);
		
		try {
			if(StringUtils.isNotEmpty(siteCode)){//填报单位买单
				paramMap.put("siteCode",siteCode);
				
				List<SecurityBlankDetailRequest> blankList=securityBlankDetailServiceImpl.queryBlankNum(paramMap);
				if(blankList!=null && blankList.size()>0){
					for (SecurityBlankDetailRequest securityBlankDetailRequest : blankList) {
						Integer blankNum=securityBlankDetailRequest.getBlankNum();
						Integer servicePeriodId=securityBlankDetailRequest.getServicePeriodId();
						updateEarly(securityBlankDetailRequest.getSiteCode(), null, earlyType, null, null,blankNum,null,null,null,null,servicePeriodId);
					}
				}
/*				
				if(blankList!=null && blankList.size()>0){
					Integer blankNum=blankList.get(0).getBlankNum();
					updateEarly(siteCode, null, earlyType, null, null,blankNum,null,null,null,null);
					
				}*/
			}else if(linkList!=null && linkList.size()>0){//组织单位买单
				paramMap.put("linkList",linkList);
				
				List<SecurityBlankDetailRequest> blankList=securityBlankDetailServiceImpl.queryBlankNum(paramMap);
				if(blankList!=null && blankList.size()>0){
					for (SecurityBlankDetailRequest securityBlankDetailRequest : blankList) {
						Integer blankNum=securityBlankDetailRequest.getBlankNum();
						Integer servicePeriodId=securityBlankDetailRequest.getServicePeriodId();
						updateEarly(securityBlankDetailRequest.getSiteCode(), null, earlyType, null, null,blankNum,null,null,null,null,servicePeriodId);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 单个网站--当前日期前推7天  首页连不通比例超过3%--调用大数据接口表
	 * @author cuichx --- 2016-7-14上午11:24:58     
	 * @param siteCode 网站标识码
	 * @param scanDate 昨天日期
	 */
	private void addHomeConnData(String siteCode,List<DatabaseTreeInfo> linkList) {
		
		Integer earlyType=EarlyType.HOME_CONNECTION_PER.getCode();//首页连不通比例超过3%
		//昨天日期yyyyMMdd
		String scanDate=DateUtils.formatShortDate(DateUtils.getYesterdaytime());
		//封装查询条件
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("scanDate",scanDate);//昨天
		paramMap.put("isScan",1);//全面监测中
		paramMap.put("isCost", 1);//收费
		paramMap.put("errorProportion", 3);//不连通比率
		try {
			if(linkList!=null && linkList.size()>0){//组织单位买单
				paramMap.put("dataLinkList", linkList);
				
				List<MTaskdetailRequest> mtList=MTaskdetailServiceImpl.queryByMap(paramMap);
				if(mtList!=null && mtList.size()>0){
					//之所以用循环--考虑添加或者更新预警信息时去重
					for (MTaskdetailRequest connectionAll : mtList) {
						//前七天 首页连不通占比（>3%）
						String errorProportion=connectionAll.getLinkerrprop7();
						
						updateEarly(connectionAll.getSiteCode(), null, earlyType, null, errorProportion,
								null,null,null,null,null,null);
					}
				}
			}else if(StringUtils.isNotEmpty(siteCode)){//填报单位买单
				paramMap.put("siteCode", siteCode);
				List<MTaskdetailRequest> mtList=MTaskdetailServiceImpl.queryByMap(paramMap);
				if(mtList!=null && mtList.size()>0){
					//前七天 首页连不通占比（>3%）
					String errorProportion=mtList.get(0).getLinkerrprop7();
					updateEarly(siteCode, null, earlyType, null, errorProportion,
							null,null,null,null,null,null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 更新预警数据
	 * @author cuichx --- 2016-6-13下午4:20:10     
	 * @param siteCode 网站标识码 
	 * @param updateGradeType 升级改版或者临时关停类型
	 * @param type 预警类型
	 * @param url 
	 * @param errorProportion 首页连不通占比 
	 * @param blankNum 空白栏目个数 
	 * @param basicNum 基本信息统计个数 
	 * @param homeNoUpdateDays 首页未更新天数
	 * @param problemDesc 问题描述 -----仅互动回应差 需要
	 * @param modifyDate 首页最后更新时间---仅首页不更新超过10天 的需要
	 * @param servicePeriodId 服务周期id---仅人工问题有
	 */
	public void updateEarly(String siteCode,Integer updateGradeType,
			Integer type,String url,String errorProportion,
			Integer blankNum,Integer basicNum,Integer homeNoUpdateDays,
			String problemDesc,String modifyDate,Integer servicePeriodId){
		
		logger.info("TaskUpdateEarly updateEarly siteCode:"+siteCode+
				"  updateGradeType:"+updateGradeType+
				"  type:"+type+
				"  url:"+url+
				"  errorProportion:"+errorProportion);
		try {
			/**
			 * 去重操作   
			 * 		如果组织单位和填报单位都买单，就有可能出现预警信息添加重复的问题，故添加去重操作
			 */
			EarlyDetailRequest earlyRequest=new EarlyDetailRequest();
			earlyRequest.setSiteCode(siteCode);
			earlyRequest.setType(type);
			if(servicePeriodId !=null){
				earlyRequest.setServicePeriodId(servicePeriodId);
			}
			 if(StringUtils.isNotEmpty(url)){
				 earlyRequest.setUrl(url);
			 }
			if(updateGradeType!=null){
				earlyRequest.setUpdateGradeType(updateGradeType);//升级改版或者临时关停类型
			}
			earlyRequest.setCheckType(1);//检查类型（0：抽查，1：正常合同）
			earlyRequest.setScanTime(DateUtils.getYesterdayStr());
			
			List<EarlyDetail> detailList=earlyDetailServiceImpl.queryList(earlyRequest);
			if(detailList==null || (detailList!=null && detailList.size()==0)){
				  /**
				   * 预警详情表里面添加数据
				   */
				 EarlyDetail earlyDetail=new EarlyDetail();
				 earlyDetail.setSiteCode(siteCode);
				 earlyDetail.setDicChannelId(0);
				 earlyDetail.setType(type);//预警类型
				 //1.首页连不通比例超过80%，2关键栏目连不通比例超过80%，3网站首页更新量为0，4人工检查时发现，5插码挂码
				 if(updateGradeType!=null){
					 earlyDetail.setUpdateGradeType(updateGradeType);
					 //只有升级改版才有人工审核类型
					 earlyDetail.setCheckState(0);//人工审核状态（0未审核，1未通过，2通过）
				 }
				 if(StringUtils.isNotEmpty(url)){
					 earlyDetail.setUrl(url);
				 }
				 if(servicePeriodId !=null){
					 earlyDetail.setServicePeriodId(servicePeriodId);
				 }
				 
				 if(StringUtils.isNotEmpty(errorProportion)){
					 earlyDetail.setErrorProportion(errorProportion);
					 //连续监测7天，首页连不通总次数占总连接次数的百分比达到了3%，十五号文的规定首页不连通比例达到5%即单项否决。
					 earlyDetail.setQueDescribe("连续监测7天，首页连不通总次数占总连接次数的百分比达到了"+errorProportion+"%");
				 }
				 if(blankNum!=null){
					 earlyDetail.setBlankNum(blankNum);
					 earlyDetail.setQueDescribe("发现"+blankNum+"个空白栏目！");
				 }
				 if(basicNum!=null){
					 earlyDetail.setNoUpdateChannelNum(basicNum);
					 earlyDetail.setQueDescribe("发现"+basicNum+"个基本信息栏目更新不及时！");
				 }
				 if(homeNoUpdateDays!=null){
					 earlyDetail.setNoUpdateDay(homeNoUpdateDays);
					 earlyDetail.setQueDescribe("首页已有"+homeNoUpdateDays+"天未更新");
					 //最后更新日期
				 }
				 if(type==13){
					 //问题描述  互动回应差中的问题描述
					 if(problemDesc!=null && !"".equals(problemDesc)){
						 earlyDetail.setQueDescribe(problemDesc);
					 }else{
						 earlyDetail.setQueDescribe("");
					 }
				 }
				 if(type==10 && modifyDate!=null && !"".equals(modifyDate)){
					 earlyDetail.setLastUpdateDate(modifyDate);
				 }
				 
				 earlyDetail.setCheckType(1);//检查类型（0：抽查，1：正常合同）
				 earlyDetail.setScanTime(DateUtils.getYesterdayStr());
				 earlyDetail.setSendWxStatus(0);//微信发送状态（-1：不发送，0：未发送，1：发送成功，2：发送失败）
				 earlyDetail.setSendSmsStatus(0);//短信发送状态（-1：不发送，0：未发送，1：发送成功，2：发送失败）
				 earlyDetail.setSendEmailState(0);//自动邮件发送状态（-1：不发送，0：未发送，1：发送成功，2：发送失败）
				 
				 earlyDetail.setOrgSendStatus(2);
				 earlyDetail.setTbSendStatus(2);
					 
				 
				 earlyDetailServiceImpl.add(earlyDetail);
				 
				 //维护预警信息统计表中的数据
				 
				 DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
				 dataRequest.setSiteCode(siteCode);
				 
				 String siteName="";
				 List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(dataRequest);
				 if(dataList!=null && dataList.size()>0){
					 siteName=dataList.get(0).getName();
				 }
			
				/**
				 * 预警统计表  更新或者新增预警统计记录
				 */
				EarlyInfoRequest earlyInforequest = new EarlyInfoRequest();
				earlyInforequest.setSiteCode(siteCode);
				earlyInforequest.setType(type);
				if(servicePeriodId!=null){
					earlyInforequest.setServicePeriodId(servicePeriodId);
				}
				if(updateGradeType!=null){
					//1.首页连不通比例超过80%，2关键栏目连不通比例超过80%，3网站首页更新量为0，4人工检查时发现，5插码挂码
					earlyInforequest.setUpdateGradeType(updateGradeType);
				}
				earlyInforequest.setCheckType(1);
				List<EarlyInfo> earlyInfoList = earlyInfoServiceImpl.queryList(earlyInforequest);
			
				EarlyInfo earlyInfo = null;
				if (earlyInfoList!=null &&  earlyInfoList.size() > 0) {
					earlyInfo = earlyInfoList.get(0);
					earlyInfo.setNewEarlyNum(earlyInfo.getNewEarlyNum() + 1);
					earlyInfo.setEarlySum(earlyInfo.getEarlySum() + 1);
					earlyInfoServiceImpl.update(earlyInfo);
				} else {
					earlyInfo = new EarlyInfo();
					earlyInfo.setSiteCode(siteCode);
					earlyInfo.setSiteName(siteName);
					earlyInfo.setCheckType(1);
					// earlyInfo.setEarlyLevel("红色预警");
					earlyInfo.setNewEarlyNum(1);
					earlyInfo.setEarlySum(1);
					earlyInfo.setType(type);
					if(servicePeriodId!=null){
						earlyInfo.setServicePeriodId(servicePeriodId);
					}
					if(updateGradeType!=null){
						earlyInfo.setUpdateGradeType(updateGradeType);	
					}
					earlyInfo.setState(0);
					
					earlyInfoServiceImpl.add(earlyInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtils.formatDouble(2, (double) (10/11)));
	}
	
	
}
