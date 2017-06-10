package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.PageVo;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.DatabaseInfoCheckType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.dto.BigSiteOverviewRequest;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.MTaskoverviewRequest;
import com.ucap.cloud_web.entity.BigSiteOverview;
import com.ucap.cloud_web.entity.ConnectionAll;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DicConfig;
import com.ucap.cloud_web.entity.Result;
import com.ucap.cloud_web.service.IBigDataAnalysisService;
import com.ucap.cloud_web.service.IBigSiteOverviewService;
import com.ucap.cloud_web.service.ICountNumService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.service.IMTaskoverviewService;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.MonitoringCacheUtils;

/**
 * <p>Description: 站点数据预览 </p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: SiteDataOverviewAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：yangshuai </p>
 * <p>@date：2016-6-24下午2:02:18 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SiteDataOverviewAction extends BaseAction{

	
	private Integer isexp;//站点状态
	private Integer isScan;//是否扫描
	private Integer level;//级别
	private String siteCode;
	
	//封装给页面的数据
	Map<String, Object> resultMap = new HashMap<String, Object>();
	
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private ICountNumService countNumServiceImpl;
	@Autowired
	private IBigDataAnalysisService bigDataAnalysisServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	@Autowired
	private IMTaskoverviewService MTaskoverviewServiceImpl;
	@Autowired
	private IBigSiteOverviewService bigSiteOverviewServiceImpl;
	
	private Object[] objOrg = new Object[] {"序号","组织单位名称/编码", "上报站点", "关停站点", "例外站点","暂不监测站点","全面监测站点"};
	private Object[] objClose = new Object[] {"序号","网站名称", "网站标识码", "省", "市","县","报送状态","监测连通率（占比）"};

	private Object[] objNo = new Object[] {"序号","网站名称", "网站标识码", "省", "市","县","报送状态","不监测原因"};
	private Object[] objNormal = new Object[] {"序号","网站名称", "网站标识码", "省", "市","县","报送状态"};
	/**
	 * linhb 2016-10-10 
	 * 跳转到大屏页面
	 * @return
	 */
	public String fullScreen(){
		return "success";
	}
	/**
	 * @Description: 面包屑默认显示
	 * @author: yangshuai --- 2016-6-24下午2:26:21
	 * @return
	 */
	public String siteDataOverview() {
		try {

			//获取当前登录的  siteCode
			String siteCod = getCurrentSiteCode();
			int id = 0;
			int level = 2;//  ;2有下级战群 3 没有下级战群； 
			id = databaseBizServiceImpl.isHasChridren(siteCod);
			// flag 0 有下级战群 1 没有下级战群
			if(id>0){
				level = 2;
			}else{
				level = 3;
			}
			
			request.setAttribute("siteCode",siteCod);
			request.setAttribute("level",level);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	
	/**
	 * 
	 * @描述:获取对应的 战群  或者  站点
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月28日上午10:11:05
	 */
	public void getDatas(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int level = 2;// 1 bm0100 ;2有下级战群 3 没有下级战群； 
			int id = databaseBizServiceImpl.isHasChridren(siteCode);
			if(id>0){
				level = 2;
				BigSiteOverviewRequest overRequest = new BigSiteOverviewRequest();
				overRequest.setParentId(id);
				overRequest.setPageSize(Integer.MAX_VALUE);
				List<BigSiteOverview> list = bigSiteOverviewServiceImpl.queryList(overRequest);
				resultMap.put("list", list);
			}else{
				level = 3;
				DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
				qRequest.setSiteCode(siteCode);
				qRequest.setPageSize(Integer.MAX_VALUE);
				List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl.queryList(qRequest);
					if(dList.size()>0){
					DatabaseTreeInfoRequest sRequest = new DatabaseTreeInfoRequest();
					sRequest.setParentId(dList.get(0).getId());
					sRequest.setPageSize(Integer.MAX_VALUE);
					List<DatabaseInfo> sList = databaseBizServiceImpl.getNatives(sRequest);
					resultMap.put("list", sList);
				}
			}
			
			resultMap.put("level", level);
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @描述:获取对应的 关停  例外  暂不检测  全面检测 站点
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月28日上午10:11:05
	 */
	public void getOtherDatas(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String clickSiteCode = request.getParameter("clickSiteCode");
			//List<DatabaseInfo> list = new ArrayList<DatabaseInfo>();
			PageVo<DatabaseInfo> query = new PageVo<DatabaseInfo>();
			if(StringUtils.isNotEmpty(clickSiteCode)){
				DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
				qRequest.setSiteCode(clickSiteCode);
				List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl.queryList(qRequest);
				
				// status 1 关停   2 例外   3 暂不检测    4 全面检测
				String status = request.getParameter("status");
				if(dList.size()>0){
					DatabaseTreeInfoRequest rRequest = new DatabaseTreeInfoRequest();
					if("1".equals(status)){
						rRequest.setIsexp(3);
					}else if("2".equals(status)){
						rRequest.setIsexp(2);
					}else if("3".equals(status)){
						rRequest.setIsexp(1);
						rRequest.setIsScan(2);
					}else if("4".equals(status)){
						rRequest.setIsexp(1);
						rRequest.setIsScan(1);
					}
					rRequest.setCode(dList.get(0).getCode());
					String pos = request.getParameter("pos");
					String size = request.getParameter("size");
					String keyWord = request.getParameter("keyWord");
					if(StringUtils.isNotEmpty(pos)){
						rRequest.setPageNo(Integer.parseInt(pos));
					}
					if(StringUtils.isNotEmpty(size)){
						rRequest.setPageSize(Integer.parseInt(size));
					}
					if(StringUtils.isNotEmpty(keyWord)){
						rRequest.setKeyWord(keyWord);
					}
					query  = databaseBizServiceImpl.getNativeDatas(rRequest);
					List<DatabaseInfo> list = query.getData();
					if("1".equals(status)){
						ConnectionAllRequest conRequest = new ConnectionAllRequest();
						for (DatabaseInfo databaseInfo : list) {
							conRequest.setSiteCode(databaseInfo.getSiteCode());
							databaseInfo.setRemark("");
							databaseInfo.setRemark(querySuccessNum(conRequest));
						}
					}
					resultMap.put("pageNo", pageNo);
					resultMap.put("pageSize", size);
					resultMap.put("list", list);
					resultMap.put("totalRecords", query.getData().size());
					resultMap.put("iTotalDisplayRecords", query.getRecordSize());
					resultMap.put("hasMoreItems",true);
				}
			}
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @描述:获取对应的组织单位 关停  例外    全面检测 站点
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年1月09日上午10:11:05
	 */
	 
	public void getInfoStates(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String clickSiteCode = getCurrentSiteCode();
			String cacheStr = CacheType.getNameByCode("16");
			String conkey = cacheStr + clickSiteCode; // 缓存名
			DatabaseTreeInfo dInfo = (DatabaseTreeInfo) MonitoringCacheUtils.get(conkey); // 查询缓存中是否存在
			if (dInfo == null) {
				if(StringUtils.isNotEmpty(clickSiteCode)){
					DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
					qRequest.setSiteCode(clickSiteCode);
					List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl.queryList(qRequest);
					if(dList.size()>0){
						DatabaseTreeInfoRequest rRequest = new DatabaseTreeInfoRequest();
						rRequest.setCode(dList.get(0).getCode());
						dInfo  = databaseBizServiceImpl.getInfoStates(rRequest);
						MonitoringCacheUtils.put(conkey, dInfo); // 将数据存到缓存中
					}
				}
			}
			resultMap.put("info", dInfo);
			if("bm0100".equals(clickSiteCode)){
				// 获取 统计好的 全国上报站点！
				DicConfig dicConfig = dicConfigServiceImpl.get(7);
				resultMap.put("sumAll", dicConfig.getValue());
			}
			resultMap.put("siteCode", clickSiteCode);
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @描述:导出数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2017年1月3日下午3:15:53
	 */

	public void exportData(){
		try {
			ArrayList<Object[]> list=new ArrayList<Object[]>();
			String flag = request.getParameter("flag");
			String title = "站点数据预览-概览明细";
			String fileName = "站点数据预览-概览明细(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			if("1".equals(flag)){//组织单位统计的数据
				int id = databaseBizServiceImpl.isHasChridren(siteCode);
				if(id>0){
					BigSiteOverviewRequest overRequest = new BigSiteOverviewRequest();
					overRequest.setParentId(id);
					overRequest.setPageSize(Integer.MAX_VALUE);
					List<BigSiteOverview> lists = bigSiteOverviewServiceImpl.queryList(overRequest);
					title = "站点数据预览-概览明细";
					fileName = "站点数据预览-概览明细(" + DateUtils.formatStandardDate(new Date()) + ").xls";
					list.add(objOrg);
					for (int i = 0; i < lists.size(); i++) {
						BigSiteOverview result = lists.get(i);
						Object[] obj = new Object[7];
						obj[0] = i + 1;
						
						String name="";//组织单位名称/编码
						if(StringUtils.isNotEmpty(result.getName())){
							name=result.getName()+"/"+result.getSiteCode();
						}
						obj[1]=name;
						
						int spotCount=result.getSumReport();
						obj[2]=spotCount;
						
						int shutDownNum=result.getSumClose();
						obj[3]=shutDownNum;
						
						int excepNum=result.getSumException();
						obj[4]=excepNum;
						
						int noScanNum=result.getSumNo();
						obj[5]=noScanNum;
						
						int isScanNum =result.getSumNormal();
						obj[6]=isScanNum;
						
						list.add(obj);
					}
				}
			}else if("2".equals(flag)){// 点击 关停 例外 等 数据导出 //关键字筛选
				DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
				qRequest.setSiteCode(siteCode);
				List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl.queryList(qRequest);
				// status 1 关停   2 例外   3 暂不检测    4 全面检测
				String status = request.getParameter("status");
				PageVo<DatabaseInfo> query = new PageVo<DatabaseInfo>();
				if(dList.size()>0){
					DatabaseTreeInfoRequest rRequest = new DatabaseTreeInfoRequest();
					if("1".equals(status)){
						rRequest.setIsexp(3);
						list.add(objClose);
					}else if("2".equals(status)){
						rRequest.setIsexp(2);
						list.add(objNormal);
					}else if("3".equals(status)){
						rRequest.setIsexp(1);
						rRequest.setIsScan(2);
						list.add(objNo);
					}else if("4".equals(status)){
						rRequest.setIsexp(1);
						rRequest.setIsScan(1);
						list.add(objNormal);
					}
					rRequest.setCode(dList.get(0).getCode());
					String keyWord = request.getParameter("keyWord");
					rRequest.setPageSize(Integer.MAX_VALUE);
					if(StringUtils.isNotEmpty(keyWord)){
						rRequest.setKeyWord(keyWord);
					}
					query = databaseBizServiceImpl.getNativeDatas(rRequest);
					for (int i=0; i<query.getData().size();i++) {
						DatabaseInfo result = query.getData().get(i);
						Object[] obj = new Object[7];
						if(status.equals("1") || status.equals("3")){
							obj = new Object[8];
						}
						obj[0] = i + 1;
						
						String siteName="";//网站名称
						if(StringUtils.isNotEmpty(result.getName())){
							siteName=result.getName();
						}
						obj[1]=siteName;
						
						String siteCode="";//网站标识码
						if(StringUtils.isNotEmpty(result.getSiteCode())){
							siteCode=result.getSiteCode();
						}
						obj[2]=siteCode;
						
						String province="";//省
						if(StringUtils.isNotEmpty(result.getProvince())){
							province=result.getProvince();
						}
						obj[3]=province;
						
						String city="";//市
						if(StringUtils.isNotEmpty(result.getCity())){
							city=result.getCity();
						}
						obj[4]=city;
						
						String county="";//县
						if(StringUtils.isNotEmpty(result.getCounty())){
							county=result.getCounty();
						}
						obj[5]=county;
						
						//网站状态（1:正常，2：例外，3:关停）
						String isExp = DatabaseInfoType.getNameByCode(result.getIsexp());
						
						obj[6]=isExp;
						
						if("1".equals(status)){
							//关停 监测连通率
							ConnectionAllRequest conRequest = new ConnectionAllRequest();
							conRequest.setSiteCode(result.getSiteCode());
							String errorProportionStr=querySuccessNum(conRequest);
							obj[7]=errorProportionStr;
						}else if("3".equals(status)){
							String isScan = DatabaseInfoCheckType.getNameByCode(result.getIsScan());
							obj[7]=isScan;
						}
						list.add(obj);
					}
					
					fileName = "站点数据预览-站点明细(" + DateUtils.formatStandardDate(new Date()) + ").xls";
					title = "站点数据预览-站点明细";
				}
			}else{//最后一级别的数据获取 //关键字筛选
				
				DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
				qRequest.setSiteCode(siteCode);
				qRequest.setPageSize(Integer.MAX_VALUE);
				List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl.queryList(qRequest);
				if(dList.size()>0){
					DatabaseTreeInfoRequest sRequest = new DatabaseTreeInfoRequest();
					sRequest.setParentId(dList.get(0).getId());
					String keyWord = request.getParameter("keyWord");
					if(StringUtils.isNotEmpty(keyWord)){
						sRequest.setKeyWord(keyWord);
					}
					sRequest.setPageSize(Integer.MAX_VALUE);
					if(checkKeyWord(keyWord)<5){
						sRequest.setIsexp(checkKeyWord(keyWord));
					}
					//关键字  筛选
					List<DatabaseInfo> sList = databaseBizServiceImpl.getNatives(sRequest);
					list.add(objNormal);
					for (int i=0; i<sList.size();i++) {
						DatabaseInfo result = sList.get(i);
						Object[] obj = new Object[7];
						//obj = new Object[8];
						obj[0] = i + 1;
						
						String siteName="";//网站名称
						if(StringUtils.isNotEmpty(result.getName())){
							siteName=result.getName();
						}
						obj[1]=siteName;
						
						String siteCode="";//网站标识码
						if(StringUtils.isNotEmpty(result.getSiteCode())){
							siteCode=result.getSiteCode();
						}
						obj[2]=siteCode;
						
						String province="";//省
						if(StringUtils.isNotEmpty(result.getProvince())){
							province=result.getProvince();
						}
						obj[3]=province;
						
						String city="";//市
						if(StringUtils.isNotEmpty(result.getCity())){
							city=result.getCity();
						}
						obj[4]=city;
						
						String county="";//县
						if(StringUtils.isNotEmpty(result.getCounty())){
							county=result.getCounty();
						}
						obj[5]=county;
						
						//网站状态（1:正常，2：例外，3:关停）
						String isExp = DatabaseInfoType.getNameByCode(result.getIsexp());
						
						obj[6]=isExp;
						
						list.add(obj);
					}
				}
				fileName = "站点数据预览-站点明细(" + DateUtils.formatStandardDate(new Date()) + ").xls";
				title = "站点数据预览-站点明细";
			}
			ExportExcel.spotResultExcel(fileName, title, list);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 面包屑默认显示
	 * @author: yangshuai --- 2016-6-24下午2:26:21
	 * @return
	 */
	public String siteDataOverviewChart() {
		level = getCurrentUserInfo().getUserType();//判断用户类型
		siteCode = getCurrentUserInfo().getSiteCode();
		String siteName = getCurrentUserInfo().getUserName();
		request.setAttribute("siteNameYs", siteName);
		if(siteCode.startsWith("bm")){
			//siteName = getCurrentUserInfo().getSiteCode();
		}
		request.setAttribute("siteCode", siteCode);
		request.setAttribute("siteName", siteName);
		request.setAttribute("level", level);
		request.setAttribute("scanDate", DateUtils.formatDate(DateUtils.getNextDayDate(new Date(), -1)));
		
		return "success";
	}


	
	/**
	 * 判断keyWord中是否包含  关停  例外  正常  字段
	 * @param keyWord
	 * @return
	 */
	public int checkKeyWord(String keyWord){
		int flag = 5;
		if(keyWord != null && !"".equals(keyWord)){
			if(keyWord.length()<3){
				if("正常".equals(keyWord) ||"常".equals(keyWord) ||"正".equals(keyWord)){
					flag = 1;
				}
				if("例外".equals(keyWord) ||"外".equals(keyWord) ||"例".equals(keyWord)){
					flag = 2;
				}
				if("关停".equals(keyWord) ||"停".equals(keyWord) ||"关".equals(keyWord)){
					flag = 3;
				}
			}
		}
		return flag;
	}
	// 监测连通率
	public String querySuccessNum(ConnectionAllRequest conRequest) {

		String yesDay = DateUtils.getYesterdayStr();
		String beforeSeven = DateUtils.getNextDay(DateUtils.getNow(), -7);
		conRequest.setYesterDayStr(yesDay);
		conRequest.setBeforeSeven(beforeSeven);
	
		ConnectionAll connectionAll = countNumServiceImpl.getConnectionHomeData(conRequest.getSiteCode(), beforeSeven,
				yesDay);
	
		if (connectionAll == null) {
			return "";
		} else {
			return connectionAll.getSuccessProportion() + "%";
		}
	}
	
	
	/****************************大数据统计图页面start***********************************************/
	/**
	 * @Title: 统计5个方块数据
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-27上午11:50:12
	 */
	public void dailyStatistics(){
		try {
			Map<String, Object> chartMap=new HashMap<String, Object>();
			Map<String, Object> paramMap=new HashMap<String, Object>();
			String loginSiteCode=getCurrentUserInfo().getSiteCode();
			Map<String, Object> siteCodeMap =new HashMap<String, Object>();
			String level=String.valueOf(getCurrentUserInfo().getUserType());
			
			if(!"bm0100".equals(loginSiteCode)){
				 level=String.valueOf(Integer.valueOf(level));
				 if("bm".equals(loginSiteCode.substring(0, 2))){
					 paramMap.put("siteCodeLike", loginSiteCode);
					 paramMap.put("isexp", 1);
					 level=String.valueOf(databaseInfoServiceImpl.queryMinLevel(paramMap)+1);
					}
			}
			siteCodeMap.put("level", level);
			siteCodeMap.put("orgSiteCode", loginSiteCode);
			siteCodeMap.put("tabId",-1);
			List<Result> resultlist=bigDataAnalysisServiceImpl.bigDataXml(loginSiteCode, 0, siteCodeMap);
			if(resultlist!=null && resultlist.size()>0){
				chartMap.put("body", resultlist);
				Result result=resultlist.get(0);
				
				//首页不连通率占比
				Double linkerrsiteprop=result.getLinkerrsiteprop7();
				chartMap.put("linkerrsiteprop", linkerrsiteprop);
				if(null != result.getLinkerrsiteprop7()){
					//首页连通率占比
					Double linksuccsiteprop=100-result.getLinkerrsiteprop7();
					chartMap.put("linksuccsiteprop", linksuccsiteprop);
				};
				
				//首页更新不及时占比
				Double noupdatesiteprop=result.getNoupdatesiteprop();
				chartMap.put("noupdatesiteprop", noupdatesiteprop);
				if(null != noupdatesiteprop){
					//首页更新及时占比
					Double updatesiteprop=100-noupdatesiteprop;
					chartMap.put("updatesiteprop", updatesiteprop);
				}
				
				//首页不可用链接平均
				Double indexdeadnum=result.getIndexdeadnum();
				chartMap.put("indexdeadnum", indexdeadnum);
				//站点更新量 平均
				Double aveupdatenum=result.getAveupdatenum();
				chartMap.put("aveupdatenum", aveupdatenum);
				//首页更新量
				Integer updatenum=result.getUpdatenum();
				chartMap.put("updatenum", updatenum);
				writerPrint(JSONObject.fromObject(chartMap).toString());
			}else{
				chartMap.put("errorMsg", "不存在统计数据");
				writerPrint(JSONObject.fromObject(chartMap).toString());
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * @Title: 连通率map
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-27上午11:50:12
	 */
	public void dailyMap(){
		Map<String, Object> chartMap=new HashMap<String, Object>();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		String loginSiteCode=getCurrentUserInfo().getSiteCode();
//		Map<String, Object> siteCodeMap =new HashMap<String, Object>();
		try {
		String level=String.valueOf(getCurrentUserInfo().getUserType());
		
		
		DatabaseTreeInfoRequest databaseTreeInfoRequest=new DatabaseTreeInfoRequest();
		databaseTreeInfoRequest.setOrgSiteCode(loginSiteCode);
		databaseTreeInfoRequest.setType(1);

		List<DatabaseInfo> provinceList = bigDataAnalysisServiceImpl.getNextCityAndCountry(databaseTreeInfoRequest);
		if(provinceList!=null && provinceList.size()>0){
			//调用接口封装方法
			Map<String, Object> siteCodeMap=bigDataAnalysisServiceImpl.listToStr(provinceList);
			String siteCodeStr=(String) siteCodeMap.get("siteCodeStr");
			
			if(!"bm0100".equals(loginSiteCode)){
				 level=String.valueOf(Integer.valueOf(level));
				 if("bm".equals(loginSiteCode.substring(0, 2))){
					 paramMap.put("siteCodeLike", loginSiteCode);
					 paramMap.put("isexp", 1);
					 level=String.valueOf(databaseInfoServiceImpl.queryMinLevel(paramMap)+1);
					}
			}
			siteCodeMap.put("level", level);
			siteCodeMap.put("orgSiteCode", loginSiteCode);
			siteCodeMap.put("tabId",0);
			List<Result> resultlist=new ArrayList<Result>();
			List<Result> resultlistMap=new ArrayList<Result>();
			Integer sitenum=0;
			if(!loginSiteCode.equals("bm0100")){
				String scanDate=DateUtils.formatShortDate(DateUtils.getNextDayDate(new Date(), -1));
				MTaskoverviewRequest mTaskoverviewRequest=new MTaskoverviewRequest();
				mTaskoverviewRequest.setTaskid(loginSiteCode);
				mTaskoverviewRequest.setCountday(scanDate);
				//查询 5个色块集合
				List<Result> siteCodeList=MTaskoverviewServiceImpl.queryResultList(mTaskoverviewRequest);
				if(siteCodeList.size()>0){
					resultlist.addAll(siteCodeList);
				}
				//查询监测站点数
				Map<String, Result> errSiteBean=bigDataAnalysisServiceImpl.queryLinkErrSiteNum(Integer.valueOf(level), loginSiteCode);
				if(null != errSiteBean.get(loginSiteCode)){
					sitenum=Integer.valueOf(errSiteBean.get(loginSiteCode).getSitenum());
				}
				//查询地图数据
				resultlistMap=bigDataAnalysisServiceImpl.bigDataXml(siteCodeStr, 0, siteCodeMap);
			}else{
				resultlist=bigDataAnalysisServiceImpl.bigDataXml(siteCodeStr, 0, siteCodeMap);
				resultlistMap=resultlist;
			}
			
			
			if(resultlist!=null && resultlist.size()>0){
				chartMap.put("body", resultlistMap);
				Double linkerrsiteprop=0.00;
				Double noupdatesiteprop=0.00;
				Double indexdeadnum=0.00;
				Double aveupdatenum=0.00;
				Integer updatenum=0;
				int count=31;
				for(Result result : resultlist){
					if(loginSiteCode.equals("bm0100")){
						if(!result.getTaskid().equals("BT0000")){
							
							sitenum=sitenum+Integer.valueOf( result.getSitenum());
							//首页不连通率占比
							 linkerrsiteprop=linkerrsiteprop+result.getLinkerrsiteprop7();
							
							//首页更新不及时占比
							 noupdatesiteprop=noupdatesiteprop+result.getNoupdatesiteprop14();
							
							
							//首页不可用链接平均
							 indexdeadnum=indexdeadnum+result.getIndexdeadnum();
							//站点更新量 平均
							 aveupdatenum=aveupdatenum+result.getAveupdatenum();
							
							//首页更新量
							 updatenum=updatenum+result.getUpdatenum();
							
						}
					}else{
						//首页不连通率占比
						 linkerrsiteprop=result.getLinkerrsiteprop7();
						//首页更新不及时占比
						 noupdatesiteprop=result.getNoupdatesiteprop14();
						//首页不可用链接平均
						 indexdeadnum=result.getIndexdeadnum()*sitenum;
						//站点更新量 平均
						 aveupdatenum=result.getAveupdatenum()*sitenum;
						//首页更新量
						 updatenum=result.getUpdatenum();
					}
		
				
				}
				if(loginSiteCode.equals("bm0100")){
					chartMap.put("linkerrsiteprop", getAvgProportion(linkerrsiteprop,count));
					
					//首页连通率占比
					Double linksuccsiteprop=100-Double.valueOf(getAvgProportion(linkerrsiteprop,count));
					chartMap.put("linksuccsiteprop", linksuccsiteprop);
					
					chartMap.put("noupdatesiteprop", getAvgProportion(noupdatesiteprop,count));
					//首页更新及时占比
					Double updatesiteprop=100-Double.valueOf(getAvgProportion(noupdatesiteprop,count));
					chartMap.put("updatesiteprop", updatesiteprop);
					
					chartMap.put("indexdeadnum", Math.round(sitenum*(indexdeadnum/count)));
					chartMap.put("aveupdatenum", Math.round(sitenum*(aveupdatenum/count)));
					chartMap.put("updatenum", updatenum);
				}else{
					chartMap.put("linkerrsiteprop",linkerrsiteprop);
					
					//首页连通率占比
					Double linksuccsiteprop=100-Double.valueOf(linkerrsiteprop);
					chartMap.put("linksuccsiteprop", linksuccsiteprop);
					
					chartMap.put("noupdatesiteprop", noupdatesiteprop);
					//首页更新及时占比
					Double updatesiteprop=100-Double.valueOf(noupdatesiteprop);
					chartMap.put("updatesiteprop", updatesiteprop);
					
					chartMap.put("indexdeadnum", Math.round(indexdeadnum));
					chartMap.put("aveupdatenum", Math.round(aveupdatenum));
					chartMap.put("updatenum", updatenum);
					}
					
					writerPrint(JSONObject.fromObject(chartMap).toString());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取不连通比例（没有%号），保留2位小数点（平均值）
	 * 
	 * @param num
	 *            数量
	 * @param totalNum
	 *            总数
	 * @return
	 */
	private String getAvgProportion(double sumPoportion, int totalNum) {

		if (sumPoportion == 0 || totalNum == 0) {
			return "0";
		}

		double proportion = sumPoportion / totalNum;

		if (proportion <= 0) {
			return "0";
		}

		if (proportion >= 100) {
			return "100";
		}

		return StringUtils.formatDouble(2, proportion);
	}
	
	

	public Integer getIsexp() {
		return isexp;
	}

	public void setIsexp(Integer isexp) {
		this.isexp = isexp;
	}

	public Integer getIsScan() {
		return isScan;
	}

	public void setIsScan(Integer isScan) {
		this.isScan = isScan;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
