package com.ucap.cloud_web.action;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.constant.UpdateStatusType;
import com.ucap.cloud_web.dto.BigDataDailyRequest;
import com.ucap.cloud_web.dto.BigOrgDailyRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dtoResponse.BigDataDailyResponse;
import com.ucap.cloud_web.dtoResponse.BigOrgDailyResponse;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.service.IBigDataDailyService;
import com.ucap.cloud_web.service.IBigOrgDailyService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.util.ExportExcel;
/**
 * 描述： 日常监测统计
 * 包：com.ucap.cloud_web.action
 * 文件名称：DailyMonitoringStatisticsAction
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-6-27下午2:15:51 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DailyMonitoringStatisticsAction extends BaseAction{
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	private IBigDataDailyService bigDataDailyServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IBigOrgDailyService bigOrgDailyServiceImpl;
	private Map<String, Object> rsMap=null;
	private String siteName;//组织单位名称
	private Integer dayNum;//天数
	private String returnType;//0返回null
	private String copyWrite;//需要复制的String

	public static boolean isDecimal(String str){  
		  return Pattern.compile("^[0-9]+(\\.[0-9]+)?%$").matcher(str).matches();  
	}  
	/** 
     * 将字符串复制到剪切板。 
     */  
    public  void setSysClipboardText() {  
    	try {
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();  
            Transferable tText = new StringSelection(copyWrite);  
            clip.setContents(tText, null);  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
 
    }  
	
	public String dailyMonitoringStatistics(){
		try {
			rsMap=new HashMap<String, Object>();
			String orgSiteCode=getCurrentUserInfo().getSiteCode();
			int tabHide = 1;// 0 5个全显示 ;1前3个  2 后3个  3本级站点；
			//是否存在下级组织单位 如果有返回的是siteCode的id  没有返回null
			DatabaseTreeInfo isNextOrg = databaseBizServiceImpl.isNextOrg(orgSiteCode);
			//  非null有下级战群  null 没有下级战群
			if(isNextOrg != null){
				if(orgSiteCode.equals("bm0100")){
					tabHide=0;//bm0100显示5个tab标签
				}else{
					if(isNextOrg.getIsBm()==0){
						tabHide=1;//显示 下级地方站群  本级站点 下级地方门户
					}else if(isNextOrg.getIsBm()==1){
						tabHide=2;//显示 部委下级地方站群  本级站点 部委下级地方门户
					}
				}
			}else{
				tabHide=3;//本级站点
			}
			//查询组织单位名字
			DatabaseOrgInfoRequest databaseOrgInfoRequest=new DatabaseOrgInfoRequest();
			databaseOrgInfoRequest.setStieCode(orgSiteCode);
			List<DatabaseOrgInfo> querylist=databaseOrgInfoServiceImpl.queryList(databaseOrgInfoRequest);
			if(querylist.size()>0){
				siteName=querylist.get(0).getName();
			}
			//查询level
			DatabaseTreeInfoRequest databaseTree=new DatabaseTreeInfoRequest();
			databaseTree.setSiteCode(orgSiteCode);
			List<DatabaseTreeInfo> databaseTreeList=databaseTreeInfoServiceImpl.queryList(databaseTree);
			if(databaseTreeList.size()>0){
				int level=databaseTreeList.get(0).getLevel();
				rsMap.put("loginLevel", level);//当前登录的level 跟下面的level不冲突
				rsMap.put("level", level);
			}
			
			rsMap.put("tabHide", tabHide);
			rsMap.put("orgSiteCode", orgSiteCode);
			rsMap.put("siteName", siteName);
//			logger.info("DailyMonitoringStatisticsAction dailyMonitoringStatistics() tabHide:"+rsMap.get("tabHide")+" level:"+level+" orgSiteCode:"+orgSiteCode+" siteName:"+siteName);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if("0".equals(returnType)){
			writerPrint(JSONObject.fromObject(rsMap).toString());
			return null;
		}else{
			return "success";
		}
	}

	
	/**
	 * @Description: 导出站点站点excel  监测网站数量  首页不更新  点击数字后的
	 * @author cuichx --- 2016-5-30下午4:02:22
	 */
	public void exportSiteExcelDate(){
		
		String orgSiteCode=request.getParameter("orgSiteCode");//组织机构编码
		String dayNum=request.getParameter("dayNum");//时间段
		String level=request.getParameter("level");
		Integer urlType=Integer.valueOf(request.getParameter("urlType"));
		String scanDate=queryDate();
		String code=request.getParameter("code");
		
		try {
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			
			String title = "";

			String fileName ="";
			
			if(2==urlType){//网站个数
				title="站点个数详情";
				fileName="站点个数详情("+DateUtils.formatStandardDate(new Date())+"_"+dayNum+"天).xls";
			}else if(3==urlType){//首页不更新站点数
				title="首页不更新站点详情";
				fileName="首页不更新站点详情("+DateUtils.formatStandardDate(new Date())+"_"+dayNum+"天).xls";
			}else if(4==urlType){//网站不连通个数详情
				title="网站不连通站点详情";
				fileName="网站不连通站点详情("+DateUtils.formatStandardDate(new Date())+"_"+dayNum+"天).xls";
			}
			
			Object[] obj1 = new Object[]{"组织单位名称/编码","省","市","区/县","健康指数","连不通比例（连不通次数／总次数）","首页死链(个)","首页不更新","网站更新量（条）","最后更新日期","未更新天数","网址"};
			list.add(obj1);
			
			BigDataDailyRequest sRequest = new BigDataDailyRequest();
			sRequest.setCountDay(scanDate);
			if(urlType==3){
				if(dayNum.equals("1")){
					sRequest.setNoupdatestatusStr("updatestatus");
				}else{
					sRequest.setNoupdatestatusStr("updatestatus"+dayNum);
				}
				sRequest.setNoupdatestatus("0");//0：不更新 1：更新 -1：未知
			}
			sRequest.setSiteCodeLength("10");
			sRequest.setCodeLike(code);
			List<BigDataDailyResponse> resultlist = bigDataDailyServiceImpl.queryNatives(sRequest);
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("level", level);
			if(StringUtils.isNotEmpty(orgSiteCode)){
				if("bm".equals(orgSiteCode.substring(0, 2))){//部委
					if("1".equals(level)){
						paramMap.put("siteCodeLike", orgSiteCode.substring(0, 4));
					}else{
						paramMap.put("siteCodeLike", orgSiteCode);
					}
				}else{//非部委
					if("1".equals(level)){
						paramMap.put("siteCodeLike", orgSiteCode.substring(0, 2));
					}else if("2".equals(level)){
						if("11,12,50,31".contains(orgSiteCode.substring(0, 2))){//直辖市坐特殊处理
							paramMap.put("siteCodeLike", orgSiteCode);
						}else{
							paramMap.put("siteCodeLike", orgSiteCode.substring(0, 4));
						}
						
					}else if("3".equals(level)){
						paramMap.put("siteCodeLike", orgSiteCode);
					}
					
				}
			}
				if(resultlist!=null && resultlist.size()>0){
					for (int i = 0; i < resultlist.size(); i++) {
						BigDataDailyResponse result=resultlist.get(i);
						Object[] obj = new Object[12];
						
						String lastupdatedate="";//最后更新时间
						String updateStatus="";//首页不更新
						Integer noupdateday=result.getNoupdateday();//未更新天数
						String noupdatedayStr="";
						//未更新天数不等于-1
							if(noupdateday != -1){
								if(noupdateday>14){
									updateStatus=UpdateStatusType.getNameByCode(0);//超过两周
								}else{
									updateStatus=UpdateStatusType.getNameByCode(1);//正常更新
								}
								lastupdatedate=String.valueOf(result.getLastupdatedate());//最后更新日期
								noupdatedayStr=String.valueOf(result.getNoupdateday());//未更新天数
							}else{
								//未更新天数等于-1  都是未知
								updateStatus=UpdateStatusType.getNameByCode(-1);//未知
								lastupdatedate="未知";//最后更新日期
								noupdatedayStr="未知";//未更新天数
							}
							obj[7]=updateStatus;//首页不更新
							obj[9]=lastupdatedate;//最后更新日期
							obj[10]=noupdatedayStr;//未更新天数
						if("1".equals(dayNum)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex();//健康指数
							obj[5]=result.getLinkerrprop()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum();//首页死链(个)
							obj[8]=result.getUpdatenum();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else if("7".equals(dayNum)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex7();//健康指数
							obj[5]=result.getLinkerrprop7()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum7();//首页死链(个)
							obj[8]=result.getUpdatenum7();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else if("14".equals(dayNum)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex14();//健康指数
							obj[5]=result.getLinkerrprop14()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum14();//首页死链(个)
							obj[8]=result.getUpdatenum14();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else {
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex30();//健康指数
							obj[5]=result.getLinkerrprop30()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum30();//首页死链(个)
							obj[8]=result.getUpdatenum30();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}
						
						String url=result.getUrl();
						obj[11]=url;
						list.add(obj);
					}
		}
		ExportExcel.bigDataSiteExcel(fileName,title,list);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * @Description: 导出excel数据
	 * @author cuichx --- 2016-5-30上午11:27:23
	 */
	public void exportExcelData(){
		
		String orgSiteCode=request.getParameter("orgSiteCode");//组织机构编码
		String level=request.getParameter("level");//组织机构级别
		String searchDate=request.getParameter("searchDate");//时间段
		String name=request.getParameter("name");//省、市、县名称
		String tabHide=request.getParameter("tabHide");////点击按钮展示个数
		
		try {
			//封装要导出的数据
			List<Map<String, Object>> dtList=new ArrayList<Map<String,Object>>();  
			List<Object[]> stationGroupList = new ArrayList<Object[]>();;//存放下级地方站群数据，用于定位
			List<Object[]> stationList = new ArrayList<Object[]>();;//存放下级地方站群数据，用于定位
			String stationGroupTitle = "";//存放下级地方站群title
			Object[] stationGroupObj = null;//存放下级地方站群表头
			if("0".equals(tabHide)){//国办bm0100  展示5个按钮  下级地方站群  下级地方门户  本级站点  下级部门站群 下级部门门户
				//excel名称
				String fileName = "中华人民共和国国务院办公厅_大数据分析("+DateUtils.formatStandardDate(new Date())+"_"+searchDate+"天).xls";
				for(int i=0;i<5;i++){
					Map<String, Object> dataMap=new HashMap<String, Object>();
					
					List<Object[]> list=null;
					if(i==0 || i==3){//下级地方站群和下级部门站群
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","网站个数","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新(网站数)","首页不更新(占比)","平均更新量(条/站)","监测更新量(条)"};
						stationGroupObj = obj1;
						list.add(obj1);
					}else{//下级地方门户  本级站点  下级部门门户
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","省","市","区/县","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新","监测更新量(条)","最后更新时间","未更新天数","网址"};
						list.add(obj1);
					}

					//shell名称 
					String title="";
					if(i==0){//下级地方站群
						stationGroupTitle="下级地方站群";
						stationList = totalExcelData(orgSiteCode, level, "0", searchDate, name);
					}else if(i==1){//下级地方门户
						title="下级地方门户";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						dtList.add(dataMap);
					}else if(i==2){//本级站点
						title="本级站点";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						dtList.add(0,dataMap);
					}else if(i==3){//下级部门站群
						title="下级部门站群";
						List<Object[]> excelList=totalExcelData(orgSiteCode, level, "1", searchDate, name);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						dtList.add(dataMap);
					}else{//下级部门门户
						title="下级部门门户";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						dtList.add(dataMap);
					}
				}
				
				//处理下级地方站群数据，进行shell页定位
				stationGroupList.add(stationGroupObj);
				stationGroupList.addAll(stationList);
				Map<String, Object> dataMapMap=new HashMap<String, Object>();
				dataMapMap.put("title", stationGroupTitle);
				dataMapMap.put("list", stationGroupList);
				dtList.add(2,dataMapMap);
				ExportExcel.bigDataExcel(fileName,dtList,searchDate);
			}else if ("2".equals(tabHide)) {//部委  展示3个按钮  本级站点  下级部门站群 下级部门门户
				//excel名称
				String fileName = name+"_大数据分析("+DateUtils.formatStandardDate(new Date())+"_"+searchDate+"天).xls";
				for(int i=2;i<5;i++){
					Map<String, Object> dataMap=new HashMap<String, Object>();
					ArrayList<Object[]> list=null;
					if(i==3){//下级地方站群和下级部门站群
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","网站个数","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新(网站数)","首页不更新(占比)","平均更新量(条/站)","监测更新量(条)"};
						list.add(obj1);
					}else{//下级地方门户  本级站点  下级部门门户
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","省","市","区/县","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新","监测更新量(条)","最后更新时间","未更新天数","网址"};
						list.add(obj1);
					}

					//shell名称 
					String title="";
					if(i==2){//本级站点
						title="本级站点";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						
						dtList.add(0,dataMap);
						
					}else if(i==3){//下级部门站群
						title="下级部门站群";
						List<Object[]> excelList=totalExcelData(orgSiteCode, level, "1", searchDate, name);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						
						dtList.add(dataMap);
					}else{//下级部门门户
						title="下级部门门户";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						
						dtList.add(dataMap);
					}
				}
				ExportExcel.bigDataExcel(fileName,dtList,searchDate);
			}else if ("1".equals(tabHide)) {
				//省级  展示3个按钮  下级地方站群 下级地方门户 本级站点
				//excel名称
				String fileName = name+"_大数据分析("+DateUtils.formatStandardDate(new Date())+"_"+searchDate+"天).xls";
				for(int i=0;i<3;i++){
					Map<String, Object> dataMap=new HashMap<String, Object>();
					ArrayList<Object[]> list=null;
					if(i==0){//下级地方站群和下级部门站群
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","网站个数","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新(网站数)","首页不更新(占比)","平均更新量(条/站)","监测更新量(条)"};
						stationGroupObj = obj1;
						list.add(obj1);
					}else{//下级地方门户  本级站点  下级部门门户
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","省","市","区/县","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新","监测更新量(条)","最后更新时间","未更新天数","网址"};
						list.add(obj1);
					}

					//shell名称 
					String title="";
					if(i==0){//下级地方站群
						stationGroupTitle="下级地方站群";
						stationList=totalExcelData(orgSiteCode, level, "0", searchDate, name);
					}else if(i==1){//下级地方门户
						title="下级地方门户";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						
						dtList.add(dataMap);
					}else{//本级站点
						title="本级站点";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						
						dtList.add(0,dataMap);
					}
				}
				//处理下级地方站群数据进行shell页定位
				stationGroupList.add(stationGroupObj);
				stationGroupList.addAll(stationList);
				Map<String, Object> dataMapMap=new HashMap<String, Object>();
				dataMapMap.put("title", stationGroupTitle);
				dataMapMap.put("list", stationGroupList);
				dtList.add(2,dataMapMap);
				ExportExcel.bigDataExcel(fileName,dtList,searchDate);
			
			}else{// 本级站点
				//excel名称
				String fileName = name+"_大数据分析("+DateUtils.formatStandardDate(new Date())+"_"+searchDate+"天).xls";
					Map<String, Object> dataMap=new HashMap<String, Object>();
					ArrayList<Object[]> list=null;
					list = new ArrayList<Object[]>();
					Object[] obj1 = new Object[]{"组织单位名称/编码","省","市","区/县","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新(网站数)","监测更新量(条)","最后更新时间","未更新天数","网址"};
					list.add(obj1);

					//shell名称 
					String title="本级站点";
					List<Object[]> excelList=siteExcelData(orgSiteCode, level, "2", searchDate);
					list.addAll(excelList);
					
					dataMap.put("title", title);
					dataMap.put("list", list);
					
					dtList.add(dataMap);
				ExportExcel.bigDataExcel(fileName,dtList,searchDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * @Description: 下级地方站群  下级部门站群导出excel数据
	 * @author cuichx --- 2016-5-30下午12:01:00     
	 * @param orgSiteCode 组织机构编码
	 * @param level 组织机构级别
	 * @param typeFlag bm0100时，0-下一级站群  1下级部门站群
	 * @param searchDate 时间段
	 * @param name 省、市、县名称
	 */
	public List<Object[]> totalExcelData(String orgSiteCode,String level,String typeFlag,String searchDate,String name){
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			Integer tabId=null;
			//封装调用汇总接口参数
			//获取下一级站群的所有组织机构
			Map<String, Object> paramMap=new HashMap<String, Object>();
			List<BigOrgDailyResponse> resultlist = new ArrayList<BigOrgDailyResponse>();
			String scanDate=queryDate();
			DatabaseTreeInfo isNextOrg = databaseBizServiceImpl.isNextOrg(orgSiteCode);
			if(StringUtils.isNotEmpty(level)){
				paramMap.put("level", level);//组织机构级别
			}
			if(StringUtils.isNotEmpty(orgSiteCode)){
				if(!"bm0100".equals(orgSiteCode)){//
					if("bm".equals(orgSiteCode.substring(0, 2))){
						tabId=3;
						/**
						 * 部委
						 * 		下级部门站群---获取部委2级或者3级组织机构的统计数据
						 */
						BigOrgDailyRequest rRequest = new BigOrgDailyRequest();
						if(isNextOrg != null){
							rRequest.setIsBm(isNextOrg.getIsBm());
							rRequest.setParentId(isNextOrg.getParentId());
							rRequest.setCountDay(scanDate);
							resultlist = bigOrgDailyServiceImpl.getOrgData(rRequest);
						}
					}else{
						tabId=0;
						/**
						 * 非部委 
						 * 		下一级站群---获取每个市或者县的统计数据
						 */
						BigOrgDailyRequest rRequest = new BigOrgDailyRequest();
						if(isNextOrg != null){
							rRequest.setIsBm(isNextOrg.getIsBm());
							rRequest.setParentId(isNextOrg.getParentId());
							rRequest.setCountDay(scanDate);
							resultlist = bigOrgDailyServiceImpl.getOrgData(rRequest);
						}
						
					}
				}else{//国办bm0100
					if("0".equals(typeFlag)){//国办   下一级站群 导出excel
						tabId=0;
						
						BigOrgDailyRequest rRequest = new BigOrgDailyRequest();
						if(isNextOrg != null){
							rRequest.setIsBm(0);
							rRequest.setParentId(isNextOrg.getParentId());
							rRequest.setCountDay(scanDate);
							resultlist = bigOrgDailyServiceImpl.getOrgData(rRequest);
						}
						
					}else{//国办  下级部门站群导出excel
						tabId=3;
						
						BigOrgDailyRequest rRequest = new BigOrgDailyRequest();
						if(isNextOrg != null){
							rRequest.setIsBm(1);
							rRequest.setParentId(isNextOrg.getParentId());
							rRequest.setCountDay(scanDate);
							resultlist = bigOrgDailyServiceImpl.getOrgData(rRequest);
						}
					}
				}
				
					if(resultlist!=null && resultlist.size()>0){
						for(int i=0;i<resultlist.size();i++){
							Object[] obj = new Object[9];
							if("1".equals(searchDate)){//昨天
								
								BigOrgDailyResponse result=resultlist.get(i);
								if(!result.getTaskid().equals("bm8000")){
									obj[0]=result.getName()+" ("+result.getTaskid()+")";//组织单位名称/编码
									obj[1]=result.getSitenum();//网站个数
									obj[2]=result.getHealthindex();//健康指数
									
									obj[3]=result.getLinkerrsiteprop()+"%";//监测不连通率（占比）
									obj[4]=result.getIndexdeadnum();//首页不可用链接(个)
									obj[5]=result.getNoupdatesitenum();//首页不更新(网站数)
									obj[6]=result.getNoupdatesiteprop()+"%";//首页不更新(占比)
									obj[7]=result.getAveupdatenum();//平均更新量(条/站)
									obj[8]=result.getUpdatenum();//监测更新量(条)
								};
						}else if("7".equals(searchDate)){//七天
								BigOrgDailyResponse result=resultlist.get(i);
								if(!result.getTaskid().equals("bm8000")){
									obj[0]=result.getName()+" ("+result.getTaskid()+")";//组织单位名称/编码
									obj[1]=result.getSitenum();//网站个数
									obj[2]=result.getHealthindex7();//健康指数
									
									obj[3]=result.getLinkerrsiteprop7()+"%";//监测不连通率（占比）
									obj[4]=result.getIndexdeadnum7();//首页不可用链接(个)
									obj[5]=result.getNoupdatesitenum7();//首页不更新(网站数)
									obj[6]=result.getNoupdatesiteprop7()+"%";//首页不更新(占比)
									obj[7]=result.getAveupdatenum7();//平均更新量(条/站)
									obj[8]=result.getUpdatenum7();//监测更新量(条)
								}
						}else if("14".equals(searchDate)){//两周
								BigOrgDailyResponse result=resultlist.get(i);
								if(!result.getTaskid().equals("bm8000")){
									obj[0]=result.getName()+" ("+result.getTaskid()+")";//组织单位名称/编码
									obj[1]=result.getSitenum();//网站个数
									obj[2]=result.getHealthindex14();//健康指数
									
									obj[3]=result.getLinkerrsiteprop14()+"%";//监测不连通率（占比）
									obj[4]=result.getIndexdeadnum14();//首页不可用链接(个)
									obj[5]=result.getNoupdatesitenum14();//首页不更新(网站数)
									obj[6]=result.getNoupdatesiteprop14()+"%";//首页不更新(占比)
									obj[7]=result.getAveupdatenum14();//平均更新量(条/站)
									obj[8]=result.getUpdatenum14();//监测更新量(条)
								}
						}else{//一个月
							
								BigOrgDailyResponse result=resultlist.get(i);
								if(!result.getTaskid().equals("bm8000")){
									obj[0]=result.getName()+" ("+result.getTaskid()+")";//组织单位名称/编码
									obj[1]=result.getSitenum();//网站个数
									obj[2]=result.getHealthindex30();//健康指数
									
									obj[3]=result.getLinkerrsiteprop30()+"%";//监测不连通率（占比）
									obj[4]=result.getIndexdeadnum30();//首页不可用链接(个)
									obj[5]=result.getNoupdatesitenum30();//首页不更新(网站数)
									obj[6]=result.getNoupdatesiteprop30()+"%";//首页不更新(占比)
									obj[7]=result.getAveupdatenum30();//平均更新量(条/站)
									obj[8]=result.getUpdatenum30();//监测更新量(条)
								}
						}
							list.add(obj);
							
						}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
		
	}
	
	/**
	 * @Description: 下级地方门户 本级站点 下级部门门户导出excel数据
	 * @author cuichx --- 2016-5-30下午1:02:32     
	 * @param orgSiteCode	组织机构编码
	 * @param level	组织机构级别
	 * @param typeFlag	1下一级门户  2本级站点  4部委门户
	 * @param searchDate	时间段
	 */
	public List<Object[]> siteExcelData(String orgSiteCode,String level,String typeFlag,String searchDate){
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			Integer tabId=null;
			//封装调用汇总接口参数
			List<BigDataDailyResponse> resultlist = new ArrayList<BigDataDailyResponse>();
			String scanDate=queryDate();
			//获取下一级站群的所有组织机构
			Map<String, Object> paramMap=new HashMap<String, Object>();
			DatabaseTreeInfo isNextOrg = databaseBizServiceImpl.isNextOrg(orgSiteCode);
			if(StringUtils.isNotEmpty(level)){
				paramMap.put("level", level);//组织机构级别
			}
			if(!"bm0100".equals(orgSiteCode)){//非国办 
				
				if("1".equals(typeFlag)){//1下一级门户---获取组织机构门户网站网站的统计数据
					tabId=1;
					
					BigDataDailyRequest rRequest = new BigDataDailyRequest();
					rRequest.setIsBm(isNextOrg.getIsBm());
					rRequest.setParentId(isNextOrg.getParentId());
					rRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryOrganizations(rRequest);
				}else if("2".equals(typeFlag)){//2本级部门---获取组织机构本级部门网站的统计数据
					tabId=2;
					paramMap.remove("level");
					
					BigDataDailyRequest sRequest = new BigDataDailyRequest();
					sRequest.setOrgSiteCode(orgSiteCode);
					sRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryNatives(sRequest);
				}else{//4部委门户---获取部委门户网站网站的统计数据
					tabId=4;
					
					BigDataDailyRequest rRequest = new BigDataDailyRequest();
					rRequest.setIsBm(isNextOrg.getIsBm());
					rRequest.setParentId(isNextOrg.getParentId());
					rRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryOrganizations(rRequest);
				}
			}else{//国办 
				if("1".equals(typeFlag)){//1下一级门户---获取组织机构门户网站网站的统计数据
					tabId=1;

					BigDataDailyRequest rRequest = new BigDataDailyRequest();
					rRequest.setIsBm(0);//bm0100  会展示5个tab  需要从页面区别部委还是地方
					rRequest.setParentId(isNextOrg.getParentId());
					rRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryOrganizations(rRequest);
				}else if("2".equals(typeFlag)){//2本级部门---获取组织机构本级部门网站的统计数据
					tabId=2;
					paramMap.remove("level");
					BigDataDailyRequest sRequest = new BigDataDailyRequest();
					sRequest.setOrgSiteCode(orgSiteCode);
					sRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryNatives(sRequest);
				}else{//4部委门户---获取部委门户网站网站的统计数据
					tabId=4;
					
					BigDataDailyRequest rRequest = new BigDataDailyRequest();
					rRequest.setIsBm(1);//bm0100  会展示5个tab  需要从页面区别部委还是地方
					rRequest.setParentId(isNextOrg.getParentId());
					rRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryOrganizations(rRequest);
				}
			}
				if(resultlist!=null && resultlist.size()>0){
					for(int i=0;i<resultlist.size();i++){

						BigDataDailyResponse result= resultlist.get(i);
						Object[] obj = new Object[12];
						
						String lastupdatedate="";//最后更新时间
						String updateStatus="";//首页不更新
						Integer noupdateday=result.getNoupdateday();//未更新天数
						String noupdatedayStr="";
						//未更新天数不等于-1
							if(noupdateday != -1){
								if(noupdateday>14){
									updateStatus=UpdateStatusType.getNameByCode(0);//超过两周
								}else{
									updateStatus=UpdateStatusType.getNameByCode(1);//正常更新
								}
								lastupdatedate=String.valueOf(result.getLastupdatedate());//最后更新日期
								noupdatedayStr=String.valueOf(result.getNoupdateday());//未更新天数
							}else{
								//未更新天数等于-1  都是未知
								updateStatus=UpdateStatusType.getNameByCode(-1);//未知
								lastupdatedate="未知";//最后更新日期
								noupdatedayStr="未知";//未更新天数
							}
							obj[7]=updateStatus;//首页不更新
							obj[9]=lastupdatedate;//最后更新日期
							obj[10]=noupdatedayStr;//未更新天数
						if("1".equals(searchDate)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex();//健康指数
							obj[5]=result.getLinkerrprop()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum();//首页死链(个)
							obj[8]=result.getUpdatenum();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else if("7".equals(searchDate)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex7();//健康指数
							obj[5]=result.getLinkerrprop7()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum7();//首页死链(个)
							obj[8]=result.getUpdatenum7();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else if("14".equals(searchDate)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex14();//健康指数
							obj[5]=result.getLinkerrprop14()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum14();//首页死链(个)
							obj[8]=result.getUpdatenum14();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else {
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex30();//健康指数
							obj[5]=result.getLinkerrprop30()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum30();//首页死链(个)
							obj[8]=result.getUpdatenum30();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}
						
						String url=result.getUrl();
						obj[11]=url;
						list.add(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @Title: 未知状态控制
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-9上午10:39:29
	 * @return
	 */
	public Integer unknownType(String updatecodes,String lastupdatedate){
		Integer updatecodesType=1;
		
		String[] arr = updatecodes.split(",");
		for(int i=0;i<arr.length;i++){
			if(arr[i].equals("200") || arr[i].equals("301") || arr[i].equals("302")){
				updatecodesType=1;//连通
				break;
			}else{
				updatecodesType=-1;//没有连通
			}
		}
		if(Integer.valueOf(lastupdatedate) ==0){
			updatecodesType=-1;//没有连通
		}
		return updatecodesType;
	}
	
	
	
	public String getSiteName() {
		return siteName;
	}



	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}



	public Integer getDayNum() {
		return dayNum;
	}



	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}



	public String getReturnType() {
		return returnType;
	}



	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}



	public Map<String, Object> getRsMap() {
		return rsMap;
	}



	public void setRsMap(Map<String, Object> rsMap) {
		this.rsMap = rsMap;
	}

	public String getCopyWrite() {
		return copyWrite;
	}

	public void setCopyWrite(String copyWrite) {
		this.copyWrite = copyWrite;
	}
}
