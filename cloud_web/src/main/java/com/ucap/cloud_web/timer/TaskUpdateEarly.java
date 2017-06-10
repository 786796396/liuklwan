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
import com.ucap.cloud_web.constant.ConnectionType;
import com.ucap.cloud_web.constant.EarlyType;
import com.ucap.cloud_web.constant.EarlyUpdateGradeType;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.EarlyInfoRequest;
import com.ucap.cloud_web.dto.UpdateContentCountRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseLink;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.entity.EarlyInfo;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseLinkService;
import com.ucap.cloud_web.service.IEarlyDetailService;
import com.ucap.cloud_web.service.IEarlyInfoService;
import com.ucap.cloud_web.service.IUpdateContentCountService;


/**
 * <p>Description: 免费用户   绑定微信后发送首页连不通日报--日报统计的是昨天的连通情况</p>
 * <p>@Package：com.ucap.cloud_web.timer </p>
 * <p>Title: TaskSendConnDaily </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2016-5-25上午9:01:34 </p>
 */
@Service("taskUpdateEarly")
public class TaskUpdateEarly {
	
	private static Logger logger = Logger.getLogger(TaskUpdateEarly.class);//日志信息
	
	
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	
	@Autowired
	private IDatabaseLinkService databaseLinkServiceImpl;
	
	@Autowired
	private IConnectionAllService connectionAllServiceImpl;
	
	@Autowired
	private IEarlyDetailService earlyDetailServiceImpl;
	
	@Autowired
	private IEarlyInfoService earlyInfoServiceImpl;
	
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	
	@Autowired
	private IUpdateContentCountService updateContentCountServiceImpl;
	
	
	/**@Description: 连续5天网站出现以下情况并由客服人员验证后触发预警
	 *		1、首页连不通比例超过80%
	 *		2、关键栏目连不通比例超过80%
	 *		3、网站首页更新量为0
	 *		4、人工检查时发现
	 *		5、插码挂码
	 *
	 *		现在的功能只统计前1、2、3
	 * @author cuichx --- 2016-6-12上午9:40:11
	 */
	public void updateGradeData(){
		try {
			//获取所有的收费用户
			Integer[] conStatues={0,1};
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("nowTime", DateUtils.formatStandardDateTime(new Date()));
			map.put("executeStatusArr", conStatues);//合同状态为0-初始化 1-服务中
			map.put("typeFlag",1);//非抽查合同
			
			List<ContractInfo>  conList=contractInfoServiceImpl.queryContractByMap(map);
			if(conList!=null && conList.size()>0){//收费用户
				for (int i = 0; i < conList.size(); i++) {
					String code=conList.get(i).getSiteCode();
					logger.info("code:"+code);
					if(code.length()==10){//填报单位
						updateEarlyDate(code);
					}else{//组织单位
						DatabaseLinkRequest linkRequest=new DatabaseLinkRequest();
						linkRequest.setOrgSiteCode(code);
						linkRequest.setPageSize(Integer.MAX_VALUE);
						List<DatabaseLink>  linkList=databaseLinkServiceImpl.queryList(linkRequest);
						if(linkList!=null && linkList.size()>0){
							for (DatabaseLink databaseLink : linkList) {
								updateEarlyDate(databaseLink.getSiteCode());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 统计数据，并更新预警详情表
	 * @author cuichx --- 2016-6-12下午6:55:46     
	 * @param code
	 * @param type
	 * @param list
	 */
  public void updateEarlyDate(String siteCode){
	  try {
		  String endDate=DateUtils.getNextDay(new Date(), -1);//获取昨天日期
		  String startDate=DateUtils.getNextDay(new Date(), -6);//前6天的日期

		 //封装查询条件
		  Map<String, Object> paramMap=new HashMap<String, Object>();
		  paramMap.put("siteCode", siteCode);
		  paramMap.put("startDate", startDate);
		  paramMap.put("endDate", endDate);
		  paramMap.put("errorProportion", "80");//连不通比例
		  
		  /**
		   * 首页连不通比例超过80%
		   */
		  paramMap.put("type", ConnectionType.HOME.getCode());//首页连不通
		  List<ConnectionAllRequest>  connHomeList= connectionAllServiceImpl.queryNotConnByMap(paramMap);
		  if(connHomeList!=null && connHomeList.size()>0){
			  Integer updateGradeType=EarlyUpdateGradeType.CON_HOME.getCode();
			  Integer type=EarlyType.UPDATE_GRADE.getCode();
			  String url=connHomeList.get(0).getUrl();
			  updateEarly(siteCode,updateGradeType,type, url);
		  }
		  
		  /**
		   * 关键栏目连不通比例超过80%
		   */
		  paramMap.remove("type");
		  paramMap.put("type", ConnectionType.CHANNEL.getCode());//关键栏目连不通
		  List<ConnectionAllRequest>  connList= connectionAllServiceImpl.queryNotConnByMap(paramMap);
		  if(connList!=null && connList.size()>0){
			  Integer updateGradeType=EarlyUpdateGradeType.POINT_CHANNEL.getCode();
			  Integer type=EarlyType.UPDATE_GRADE.getCode();
			  String url=connList.get(0).getUrl();
			  updateEarly(siteCode,updateGradeType,type, url);
		  }
		  
		  /**
		   * 连续5天   网站首页更新量为0
		   */
		  paramMap.remove("type");
		  paramMap.remove("errorProportion");
		  paramMap.put("type", 0);
		  paramMap.put("updateNum", 0);//更新量
		  paramMap.remove("startDate");
		  paramMap.remove("endDate");
		  paramMap.put("beginScanDate", startDate);
		  paramMap.put("endScanDate", endDate);
		  
		  List<UpdateContentCountRequest> updateList=updateContentCountServiceImpl.queryNotUpdateByMap(paramMap);
		  if(updateList!=null && updateList.size()>0){
			  Integer updateGradeType=EarlyUpdateGradeType.SITE_UPDATE.getCode();
			  Integer type=EarlyType.UPDATE_GRADE.getCode();
			  
			  //获取网站网址
			  String url="";
			  DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
			  dataRequest.setSiteCode(siteCode);
			  List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(dataRequest);
			  if(dataList!=null && dataList.size()>0){
				  if(StringUtils.isNotEmpty(dataList.get(0).getJumpUrl())){
					  url=dataList.get(0).getJumpUrl();
				  }else{
					  url=dataList.get(0).getUrl();
				  }
			  }
			  
			  updateEarly(siteCode,updateGradeType,type, url);
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
  }

	/**
	 * @Description: 更新预警数据
	 * @author cuichx --- 2016-6-13下午4:20:10     
	 * @param siteCode
	 * @param updateGradeType
	 * @param type
	 * @param url
	 */
	public void updateEarly(String siteCode,Integer updateGradeType,Integer type,String url){
		
		logger.info("TaskUpdateEarly updateEarly siteCode:"+siteCode+
				"  updateGradeType:"+updateGradeType+
				"  type:"+type+
				"  url:"+url);
		
		
		EarlyDetailRequest earlyRequest=new EarlyDetailRequest();
		earlyRequest.setSiteCode(siteCode);
		earlyRequest.setType(type);
		earlyRequest.setUpdateGradeType(updateGradeType);
		earlyRequest.setCheckType(1);
		earlyRequest.setScanTime(DateUtils.formatStandardDate(new Date()));
		
		List<EarlyDetail> detailList=earlyDetailServiceImpl.queryList(earlyRequest);
		if(detailList==null || (detailList!=null && detailList.size()==0)){
			  /**
			   * 预警详情表里面添加数据
			   */
			 EarlyDetail earlyDetail=new EarlyDetail();
			 earlyDetail.setSiteCode(siteCode);
			 earlyDetail.setDicChannelId(0);
			 earlyDetail.setType(type);//升级改版或者临时关停
			 //1.首页连不通比例超过80%，2关键栏目连不通比例超过80%，3网站首页更新量为0，4人工检查时发现，5插码挂码
			 earlyDetail.setUpdateGradeType(updateGradeType);
			 earlyDetail.setCheckType(1);//检查类型（0：抽查，1：正常合同）
			 earlyDetail.setScanTime(DateUtils.formatStandardDateTime(new Date()));
			 earlyDetail.setSendWxStatus(0);//微信发送状态（-1：不发送，0：未发送，1：发送成功，2：发送失败）
			 earlyDetail.setSendEmailState(0);//自动邮件发送状态（0否，1是）
			 earlyDetail.setUrl(url);
			 earlyDetail.setCheckState(0);//人工审核状态（0未审核，1未通过，2通过）
			 
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
			//1.首页连不通比例超过80%，2关键栏目连不通比例超过80%，3网站首页更新量为0，4人工检查时发现，5插码挂码
			earlyInforequest.setUpdateGradeType(updateGradeType);
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
				earlyInfo.setUpdateGradeType(updateGradeType);//首页连不通比例超过80%
				earlyInfo.setState(0);
				earlyInfoServiceImpl.add(earlyInfo);
			}
		}

	}
}