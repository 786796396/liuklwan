package com.ucap.cloud_web.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.UserType;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.MTaskdetailRequest;
import com.ucap.cloud_web.dto.MTaskoverviewRequest;
import com.ucap.cloud_web.dto.MonitorIncludeRequest;
import com.ucap.cloud_web.dto.MonitorOrgIncludeRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.MonitorInclude;
import com.ucap.cloud_web.entity.MonitorOrgInclude;
import com.ucap.cloud_web.entity.Result;
import com.ucap.cloud_web.service.IBigDataAnalysisService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IMTaskdetailService;
import com.ucap.cloud_web.service.IMTaskoverviewService;
import com.ucap.cloud_web.service.IMonitorIncludeService;
import com.ucap.cloud_web.service.IMonitorOrgIncludeService;

@Service
public class BigDataAnalysisServiceImpl implements IBigDataAnalysisService{
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IMTaskdetailService MTaskdetailServiceImpl;
	@Autowired
	private IMTaskoverviewService MTaskoverviewServiceImpl;
	@Autowired
	private IMonitorIncludeService monitorIncludeServiceImpl;
	@Autowired
	private IMonitorOrgIncludeService monitorOrgIncludeServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	/**
	 * @Description: 拼装 调用大数据分析接口的地址参数---任务标识码
	 * @author cuichx --- 2016-5-27下午3:29:03     
	 * @param list
	 * @return
	 */
	@Override
	public  Map<String,Object> listToStr(List<DatabaseInfo> list){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String buf="";
		for(int i=0;i<list.size();i++){
			String id=list.get(i).getSiteCode();
			if(list.size()-1==i){
				buf+=id;
			}else{
				buf+=id+",";
			}
		String siteCode=list.get(i).getSiteCode();
		resultMap.put(siteCode, list.get(i));
		}
		resultMap.put("list", list);
		resultMap.put("siteCodeStr", buf);
		
		return resultMap;
	}
	
	/**
	 * 
	 * 描述:解析xml返回List结果
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-27上午11:11:17
	 * @param siteCodeStr
	 * @param type 0:汇总结果接口   1:站点接口
	 * @param Map<String, Object> siteCodeMap 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Result> bigDataXml(String siteCodeStr,Integer type,Map<String, Object> siteCodeMap){
		List<Result> resultsList=new ArrayList<Result>();
		List<Result> returnList=new ArrayList<Result>();
		try {
			String scanDate=DateUtils.formatShortDate(DateUtils.getNextDayDate(new Date(), -1));
			if(type ==2 ){
				//网站个数
				siteCodeMap.put("tabId",5);
				siteCodeMap.put("noupdatestatus", "");
			}else if(type ==3){
				//首页不更新（站点数）
				siteCodeMap.put("tabId",6);
			}else if(type ==4){
				//不连通个数 监测不连通率占比
				siteCodeMap.put("tabId",7);
			}
		
			List<DatabaseInfo> list=(List<DatabaseInfo>) siteCodeMap.get("list");
			String noupdatestatus=String.valueOf(siteCodeMap.get("noupdatestatus"));
			for(DatabaseInfo databaseInfo:list){
				List<Result> siteCodeList=new ArrayList<Result>();
				String siteCode=databaseInfo.getSiteCode();
				String name=databaseInfo.getName();
				if(siteCode.length()==10){
					MTaskdetailRequest mTaskdetailRequest=new MTaskdetailRequest();
					mTaskdetailRequest.setSiteCode(siteCode);
					mTaskdetailRequest.setCountday(scanDate);
					if(null != noupdatestatus && !"".equals(noupdatestatus) && !"null".equals(noupdatestatus)){
						if(noupdatestatus.equals("1")){
							mTaskdetailRequest.setNoupdatestatusStr("updatestatus");
						}else{
							mTaskdetailRequest.setNoupdatestatusStr("updatestatus"+noupdatestatus);
						}
						mTaskdetailRequest.setNoupdatestatus("0");
					}
					siteCodeList=MTaskdetailServiceImpl.queryResultList(mTaskdetailRequest);
					if(siteCodeList.size()>0){
						siteCodeList.get(0).setName(name);
						 resultsList.addAll(siteCodeList);
					}
				}else if(siteCode.length()==6){
					MTaskoverviewRequest mTaskoverviewRequest=new MTaskoverviewRequest();
					mTaskoverviewRequest.setTaskid(siteCode);
					mTaskoverviewRequest.setCountday(scanDate);
					siteCodeList=MTaskoverviewServiceImpl.queryResultList(mTaskoverviewRequest);
					if(siteCodeList.size()>0){
						 resultsList.addAll(siteCodeList);
					}
				   
				}
				
			}
			
			for(int i=0;i<resultsList.size();i++){
				Result result=resultsList.get(i);
				if(type == 0){
					String id=resultsList.get(i).getTaskid();
					
						DatabaseInfo datainfo=(DatabaseInfo)siteCodeMap.get(id);
						if(null!=datainfo){
						result.setName(datainfo.getName());
						//重新获得监测网站数量  与站点数据概览保持一致
						Map<String, Result> errSiteBean=queryLinkErrSiteNum(Integer.valueOf(siteCodeMap.get("level").toString()),datainfo.getSiteCode());
						if(null != errSiteBean.get(id)){
							Integer linkerrsitenum=errSiteBean.get(id).getLinkerrsitenum();
							result.setLinkerrsitenum(linkerrsitenum);
							result.setLinkerrsitenum7(linkerrsitenum);
							result.setLinkerrsitenum14(linkerrsitenum);
							result.setLinkerrsitenum30(linkerrsitenum);
							result.setSitenum(errSiteBean.get(id).getSitenum());
							returnList.add(result);
						}
					}else{
						returnList.add(result);
					}

				}else if(type > 0){
					String id=result.getSitecode();
					DatabaseInfo datainfo=(DatabaseInfo)siteCodeMap.get(id);
					if(StringUtils.isNotNull(datainfo)){
					if(StringUtils.isNotEmpty(datainfo.getName())){
						result.setName(datainfo.getName());
					}else{
						result.setName("");
					}
					if(StringUtils.isNotEmpty( datainfo.getProvince())){
						result.setProvince(datainfo.getProvince());//省
					}else{
						result.setProvince("");
					}
					if(StringUtils.isNotEmpty( datainfo.getCity())){
						result.setCity(datainfo.getCity());//市
						
					}else{
						result.setCity("");//市
					}
					if(StringUtils.isNotEmpty( datainfo.getCounty())){
						result.setCounty(datainfo.getCounty());//线
					}else{
						result.setCounty("");//线
					}
					String url="";
					if(StringUtils.isNotEmpty(datainfo.getJumpUrl())){
						url=datainfo.getJumpUrl();//跳转url
					}else{
						url=datainfo.getUrl();//首页url
					}
					if(StringUtils.isEmpty(url)){
						url="";
					}
					result.setUrl(url);//网址
					if(StringUtils.isEmpty(result.getHealthindex())){
						result.setHealthindex("400");
					}
					if(StringUtils.isEmpty(result.getHealthindex7())){
						result.setHealthindex("400");
					}
					if(StringUtils.isEmpty(result.getHealthindex14())){
						result.setHealthindex("400");
					}
					if(StringUtils.isEmpty(result.getHealthindex30())){
						result.setHealthindex("400");
					}
					returnList.add(result);
				}
			}
				
			}
			

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnList;
	}
	/**
	 * @Title: 百度搜索引擎 查询数据
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-11-1下午3:40:53
	 * @param siteCodeStr
	 * @param type
	 * @param siteCodeMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Result> baiduData(String siteCodeStr,Integer type,Map<String, Object> siteCodeMap){
		List<Result> resultsList=new ArrayList<Result>();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("siteCode", null);
			String scanDate=monitorOrgIncludeServiceImpl.queryMaxDate(map);
			List<DatabaseInfo> list=(List<DatabaseInfo>) siteCodeMap.get("list");
			for(DatabaseInfo databaseInfo:list){
			
				
				String siteCode=databaseInfo.getSiteCode();
				String name=databaseInfo.getName();
				String url=databaseInfo.getUrl();
				if(siteCode.length()==10){
					MonitorIncludeRequest monitorIncludeRequest=new MonitorIncludeRequest();
					monitorIncludeRequest.setSiteCode(siteCode);
					monitorIncludeRequest.setScanDate(scanDate);
					List<MonitorInclude> monitorIncludeList=monitorIncludeServiceImpl.queryList(monitorIncludeRequest);
					if(monitorIncludeList.size()>0){
						for(MonitorInclude monitorInclude:monitorIncludeList){
							//百度收录数_站点
							Integer baiduSlWebsite=monitorInclude.getBaiduSlWebsite();
							//百度收录数_域
							Integer baiduSlDomainsite=monitorInclude.getBaiduSlDomainsite();
							//采集时间
							Date crawlDate=monitorInclude.getCrawlDate();
							Result result=new Result();
							if(null == crawlDate){
								result.setCrawlDate("未知");
							}else{
								result.setCrawlDate(DateUtils.formatStandardDateTime(crawlDate));
							}
							result.setScanDate(monitorInclude.getScanDate());
							result.setBaiduSlWebsite(baiduSlWebsite);
							result.setBaiduSlDomainsite(baiduSlDomainsite);
							result.setUrl(url);
							result.setSitecode(siteCode);
							result.setName(name);
							resultsList.add(result);
							
						}

						
					}
				}else if(siteCode.length()==6){
					MonitorOrgIncludeRequest monitorOrgIncludeRequest=new MonitorOrgIncludeRequest();
					monitorOrgIncludeRequest.setSiteCode(siteCode);
					monitorOrgIncludeRequest.setScanDate(scanDate);
					List<MonitorOrgInclude> monitorOrgIncludeList=monitorOrgIncludeServiceImpl.queryList(monitorOrgIncludeRequest);
					if(monitorOrgIncludeList.size()>0){
						for(MonitorOrgInclude monitorOrgInclude:monitorOrgIncludeList){
							//百度收录数_站点
							Integer baiduWebsite=monitorOrgInclude.getBaiduSiteSum();
							//百度收录数_域
							Integer baiduDomainSum=monitorOrgInclude.getBaiduDomainSum();
							//百度收录数_站点 均值
							Integer baiduSiteAvg=monitorOrgInclude.getBaiduSiteAvg();
							//百度收录数_域 均值
							Integer baiduDomainAvg=monitorOrgInclude.getBaiduDomainAvg();
							//组织单位 采集数量
							Integer siteSum=monitorOrgInclude.getSiteSum();
							Result result=new Result();
							result.setBaiduSlWebsite(baiduWebsite);
							result.setBaiduSlDomainsite(baiduDomainSum);
							result.setBaiduSiteAvg(baiduSiteAvg);
							result.setBaiduDomainAvg(baiduDomainAvg);
							result.setSitecode(siteCode);
							result.setName(name);
							result.setSiteSum(siteSum);
							resultsList.add(result);

						}
					}
				}
				
			}
			String siteCode="";
			if(list.size()>0){
				siteCode=list.get(0).getSiteCode();
				if(siteCode.length()==10){
					Map<String, Object> returnMap=sortList(list, resultsList, "baiduSlWebsite");
					siteCodeMap.put("xSiteList", returnMap.get("xSiteList"));
					siteCodeMap.put("ySiteList",  returnMap.get("ySiteList"));
					
					Map<String, Object> domainReturnMap=sortList(list, resultsList, "baiduSlDomainsite");
					siteCodeMap.put("xDomainList",  domainReturnMap.get("xDomainList"));
					siteCodeMap.put("yDomainList",  domainReturnMap.get("yDomainList"));
				}else if(siteCode.length()==6){
					Map<String, Object> returnMap=sortList(list, resultsList, "baiduSiteAvg");
					siteCodeMap.put("xSiteList", returnMap.get("xSiteList"));
					siteCodeMap.put("ySiteList",  returnMap.get("ySiteList"));
					
					Map<String, Object> domainReturnMap=sortList(list, resultsList, "baiduDomainAvg");
					siteCodeMap.put("xDomainList",  domainReturnMap.get("xDomainList"));
					siteCodeMap.put("yDomainList",  domainReturnMap.get("yDomainList"));
					
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultsList;
	}


	
	
	/**
	 * @Title: 下一级门户---获取组织机构下级地方门户网站的统计数据
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-13下午1:19:50
	 * @param orgSiteCode
	 * @return
	 */
	@Override
	public List<DatabaseInfo> getNextSitesMH(String orgSiteCode,int type){
		List<DatabaseInfo> dataList=new ArrayList<DatabaseInfo>();
		
		//查询当前选中组织单位  id
		DatabaseTreeInfoRequest databaseTreeInfoRequest =new DatabaseTreeInfoRequest();
		
		databaseTreeInfoRequest.setIsBigdata(1);
		databaseTreeInfoRequest.setSiteCode(orgSiteCode);
		databaseTreeInfoRequest.setPageNo(0);
		databaseTreeInfoRequest.setPageSize(Integer.MAX_VALUE);
		List<DatabaseTreeInfo> list=databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
		
		if(list.size()>0){
			for(DatabaseTreeInfo databaseTreeInfo:list){
				//bm0100的id
				databaseTreeInfoRequest.setIsexp(1);//正常
				databaseTreeInfoRequest.setIsorganizational(1);//门户
				databaseTreeInfoRequest.setSiteCode(null);
				databaseTreeInfoRequest.setSiteCodeLength(10);//填报单位
				databaseTreeInfoRequest.setCodeLike(databaseTreeInfo.getCode());
				databaseTreeInfoRequest.setLevel(databaseTreeInfo.getLevel()+2);// level+2是为了查询下级门户
				dataList=databaseTreeInfoServiceImpl.querySiteDatabaseInfoList(databaseTreeInfoRequest);
					
			}
		}
		
		
		return dataList;
	}
	/**
	 * @Title: 本级站点---获取组织机构本级站点网站的统计数据
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-13下午3:04:38
	 * @return
	 */
	@Override
	public List<DatabaseInfo> getLocalSites(DatabaseTreeInfoRequest request){
		String orgSiteCode=request.getOrgSiteCode();
		List<DatabaseInfo> dataList=new ArrayList<DatabaseInfo>();
		//查询当前选中组织单位  id
		DatabaseTreeInfoRequest databaseTreeInfoRequest =new DatabaseTreeInfoRequest();
		databaseTreeInfoRequest.setIsBigdata(1);
		databaseTreeInfoRequest.setSiteCode(orgSiteCode);
		databaseTreeInfoRequest.setPageNo(0);
		databaseTreeInfoRequest.setPageSize(Integer.MAX_VALUE);
		List<DatabaseTreeInfo> list=databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
		
			if(list.size()>0){
				databaseTreeInfoRequest.setIsexp(1);//正常
				for(DatabaseTreeInfo databaseTreeInfo:list){
					//bm0100的id
					Integer parentId=databaseTreeInfo.getId();
					databaseTreeInfoRequest.setParentId(parentId);
					if(orgSiteCode.equals("bm0100")){
						dataList=databaseTreeInfoServiceImpl.queryBmLocalSites(databaseTreeInfoRequest);
					}else{
						databaseTreeInfoRequest.setSiteCode(null);
						databaseTreeInfoRequest.setSiteCodeLength(10);
						dataList=databaseTreeInfoServiceImpl.querySiteDatabaseInfoList(databaseTreeInfoRequest);
					}
						
				}
			}
		return dataList;
	}
	/**
	 * @Title: 是否是 末级
	 * @Description:
	 * @author liujc@ucap.com.cn	2016年11月29日20:07:21
	 * @return
	 */
	@Override
	public boolean isLocalSites(DatabaseTreeInfoRequest request){
		boolean flag=false;
		String orgSiteCode=request.getOrgSiteCode();
		
		//查询当前选中组织单位  id
		DatabaseTreeInfoRequest databaseTreeInfoRequest =new DatabaseTreeInfoRequest();
		databaseTreeInfoRequest.setIsBigdata(1);
		databaseTreeInfoRequest.setSiteCode(orgSiteCode);
		databaseTreeInfoRequest.setPageNo(0);
		databaseTreeInfoRequest.setPageSize(Integer.MAX_VALUE);
		List<DatabaseTreeInfo> list=databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
		if(list.size()>0){
			for(DatabaseTreeInfo databaseTreeInfo:list){
				//bm0100的id
				Integer parentId=databaseTreeInfo.getId();
				databaseTreeInfoRequest.setParentId(parentId);
				databaseTreeInfoRequest.setSiteCode(null);
				databaseTreeInfoRequest.setSiteCodeLength(6);
				List<DatabaseTreeInfo> nextDataList=databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
				//区县级  没有 站点文件夹  直接挂接站点  如果大于0说明不是区县级   等于0是区县级 直接获取站点
				if(nextDataList.size()>0){
					flag=false;
				}else{
					// 等于0是区县级 直接获取站点
					flag=true;
				}
					
			}
		}
		return flag;
	}
	/**
	 * @Title: 查询下级省市县 和下级部委 2级 3级
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-13下午3:49:41
	 * @param request
	 * @return
	 */
	@Override
	public List<DatabaseInfo>  getNextCityAndCountry(DatabaseTreeInfoRequest request){
		List<DatabaseInfo> dataList=new ArrayList<DatabaseInfo>();
		String orgSiteCode=request.getOrgSiteCode();
		//查询当前选中组织单位  id
		DatabaseTreeInfoRequest databaseTreeInfoRequest =new DatabaseTreeInfoRequest();
		databaseTreeInfoRequest.setIsBigdata(1);
		databaseTreeInfoRequest.setSiteCode(orgSiteCode);
		databaseTreeInfoRequest.setPageNo(0);
		databaseTreeInfoRequest.setPageSize(Integer.MAX_VALUE);
		List<DatabaseTreeInfo> list=databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
		
		if(list.size()>0){
			for(DatabaseTreeInfo databaseTreeInfo:list){
				//bm0100的id
				Integer parentId=databaseTreeInfo.getId();
				databaseTreeInfoRequest.setParentId(parentId);
				databaseTreeInfoRequest.setSiteCode(null);
				databaseTreeInfoRequest.setSiteCodeLength(6);//填报单位
				if(request.getType()==1){
					//非部委
					databaseTreeInfoRequest.setIsBm(0);
				}else if(request.getType()==2){
					//部委
					databaseTreeInfoRequest.setIsBm(1);
				}
				dataList=databaseTreeInfoServiceImpl.queryDatabaseInfoList(databaseTreeInfoRequest);
					
			}
		}
		return dataList;
	}
	/**
	 * @Description:判断用户的类型
	 * @author kefan-- 2015-11-15 下午7:18:20
	 * @return:用户的类型： 1:国办 2：省份 3:市 4:区县
	 */
	@Override
	public int judgeUserType(String siteCode) {

		if (siteCode != null) {
			if (siteCode.equals("bm0100")) {
				return UserType.TYPE_ADMIN.getCode();
			}
			// 如果siteCode是以0000结尾的，则表示为组织单位
			if (siteCode.endsWith("0000")) {
				return UserType.TYPE_PROVINCE.getCode();
			} else if (siteCode.endsWith("00")) {
				return UserType.TYPE_CITY.getCode();
			} else {// 标识填报单位
				return UserType.TYPE_COUNTY.getCode();
			}
		}
		return 1;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object>  sortList(List<DatabaseInfo> list,List<Result> resultsList,String orderName){
		Map<String, Object> returnMap=new HashMap<String, Object>();
		try {
			List<String> ySiteList=new ArrayList<String>();
			List<String> yDomainList=new ArrayList<String>();
			List<String> xSiteList=new ArrayList<String>();
			List<String> xDomainList=new ArrayList<String>();
			List<Result> listSort=new ArrayList<Result>();
			listSort.addAll(resultsList);
			//借助commons-collections包的ComparatorUtils   
	       //BeanComparator，ComparableComparator和ComparatorChain都是实现了Comparator这个接口   
	        Comparator mycmp = ComparableComparator.getInstance();      
	        mycmp = ComparatorUtils.nullLowComparator(mycmp);  //允许null
//	        if(isAsc){
	        mycmp = ComparatorUtils.reversedComparator(mycmp); //逆序      
//	        }
	        Comparator cmp = new BeanComparator(orderName, mycmp);   
	        Collections.sort(listSort, cmp);  
			for(Result result:listSort){
				
				String siteCode="";
				if(list.size()>0){
					siteCode=list.get(0).getSiteCode();
					if(siteCode.length()==10){
						//百度收录数_站点
						Integer baiduSlWebsite=result.getBaiduSlWebsite();
						//百度收录数_域
						Integer baiduSlDomainsite=result.getBaiduSlDomainsite();
						if(orderName.equals("baiduSlWebsite")){
							xSiteList.add(String.valueOf(baiduSlWebsite));
							ySiteList.add(result.getName());
						}else{
							xDomainList.add(String.valueOf(baiduSlDomainsite));
							yDomainList.add(result.getName());
						}
						
					
					}else if(siteCode.length()==6){
						Integer baiduSiteAvg=result.getBaiduSiteAvg();
						Integer baiduDomainAvg=result.getBaiduDomainAvg();
						
						if(orderName.equals("baiduSiteAvg")){
							xSiteList.add(String.valueOf(baiduSiteAvg));
							ySiteList.add(result.getName());
						}else{
							xDomainList.add(String.valueOf(baiduDomainAvg));
							yDomainList.add(result.getName());
						}
					}
					
				}
				
				
			
			}
			returnMap.put("ySiteList", ySiteList);
			returnMap.put("xSiteList", xSiteList);
			returnMap.put("xDomainList", xDomainList);
			returnMap.put("yDomainList", yDomainList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return returnMap;
		
	}
	
	/**
	 * 
	 * @描述: 获得监测网站数量
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-27下午3:48:29 
	 * @param level
	 * @param siteCode
	 * @return
	 */
	public Map<String, Result> queryLinkErrSiteNum(Integer level,String siteCode){

		List<DatabaseInfoRequest> dataCountList = new ArrayList<DatabaseInfoRequest>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteCode", siteCode);
		DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
		qRequest.setSiteCode(siteCode);
		List<DatabaseTreeInfo> listT = databaseTreeInfoServiceImpl.queryList(qRequest);
		if(listT.size()>0){
			for(DatabaseTreeInfo treeInfoBean: listT){
				map.put("code", treeInfoBean.getCode());
			}
		}
		if(level == 1){
			map.put("level", level);
			List<DatabaseInfoRequest> spotCountList= databaseInfoServiceImpl.queryProvinceCountByMap(map);
			map.remove("isexp");
			map.put("isScan", 1);
			//上报站点  关停  例外  正常  
			List<DatabaseInfoRequest> isScanNumList = databaseInfoServiceImpl.queryProvinceScan(map);
			Integer isScanNum=isScanNumList.size();//全面检测
			if(spotCountList.size()>0){
				spotCountList.get(0).setIsScanNum(isScanNum);
			}else{
				spotCountList.get(0).setIsScanNum(0);
			}
			dataCountList.addAll(spotCountList);
		}else if(level == 4){
			
		}else{
			Integer flagBw = 1;//判断是否是部委
			if(siteCode.indexOf("bm")==0){
				flagBw = 0;
			}
			Integer flagZh = 1;//判断是否直辖市或兵团
			if(siteCode.startsWith("BT") || siteCode.startsWith("11") || siteCode.startsWith("31") || siteCode.startsWith("12") || siteCode.startsWith("50")){
				flagZh = 0;
			}
			map.put("flagBw", flagBw);
			map.put("flagZh", flagZh);
			if(level == 2){
				map.put("level", level);
				if(flagZh == 0){//直辖市下组织
					map.put("parentSiteCodeLike", siteCode.substring(0, 2));
				}else{
					if(flagBw == 0){
						map.put("parentSiteCodeLike", siteCode.substring(0, 4));
					}else{
						map.put("parentSiteCodeLike", siteCode.substring(0, 2));
					}
				}
			}else if(level == 3){
				map.put("level", level);
				map.put("parentSiteCodeLike", siteCode.substring(0, 4));
			}
			List<DatabaseInfoRequest> spotCountList= databaseInfoServiceImpl.queryProvinceCountByMap(map);
			map.remove("isexp");
			map.put("isScan", 1);
			//上报站点  关停  例外  正常  
			List<DatabaseInfoRequest> isScanNumList = databaseInfoServiceImpl.queryProvinceScan(map);
			Integer isScanNum=isScanNumList.size();//全面检测
			if(spotCountList.size()>0){
				spotCountList.get(0).setIsScanNum(isScanNum);
			}else{
			spotCountList.get(0).setIsScanNum(0);
			}
			dataCountList.addAll(spotCountList);
		}
		Map<String, Result> resultMap=new HashMap<String, Result>();
		for(int i=0;i<dataCountList.size();i++){
			DatabaseInfoRequest databaseInfoRequest=dataCountList.get(i);
			Result resultBean=new Result();
			Integer isScanNum=databaseInfoRequest.getIsScanNum();//监测站点个数
			resultBean.setSitenum(isScanNum.toString());//监测网站数
			String id=dataCountList.get(i).getSiteCode();//key
			resultMap.put(id, resultBean);
		}
		return resultMap;
	
	}
}
