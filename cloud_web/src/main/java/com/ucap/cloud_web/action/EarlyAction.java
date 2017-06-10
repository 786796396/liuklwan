package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.EarlyType;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.EarlyInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DicChannel;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.entity.EarlyInfo;
import com.ucap.cloud_web.entity.ReportWordResult;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDicChannelService;
import com.ucap.cloud_web.service.IEarlyDetailService;
import com.ucap.cloud_web.service.IEarlyInfoService;
import com.ucap.cloud_web.service.IReportWordResultService;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.shiro.ShiroUser;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.MonitoringCacheUtils;
import com.ucap.cloud_web.util.SendEmail;

import net.sf.json.JSONObject;
/**
 * <p>Description: 预警</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: EarlyAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015年11月11日上午11:01:07 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class EarlyAction extends BaseAction{
	private static Logger logger = Logger.getLogger(EarlyAction.class);
	private List<Object> earlyList= new ArrayList<Object>();
	@Autowired
	private IEarlyInfoService earlyInfoServiceImpl;
	@Autowired
	private IEarlyDetailService earlyDetailServiceImpl;
	@Autowired
	private IDicChannelService dicChannelServiceImpl;
	
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	
	@Autowired
	private IReportWordResultService reportWordResultServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	private String earlyType;
	public String index(){
		request.setAttribute("earlyType", earlyType);
		return "success";
	}
	
	/** @Description:获取播报数据
	 * @author linhb --- 2016-10-09下午05:17:46                
	*/
	public void getDatas(){
		//封装返回数据
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String loginSiteCode = getCurrentSiteCode();
			
			
			String cacheStr = CacheType.getNameByCode("17");
			String conkey = cacheStr + loginSiteCode; // 缓存名
			List<EarlyDetail> list = (ArrayList<EarlyDetail>) MonitoringCacheUtils.get(conkey); // 查询缓存中是否存在
			String code = "";
			List<EarlyDetail> allList = new ArrayList<EarlyDetail>();
			if (list == null) {
				
				DatabaseTreeInfoRequest dRequest = new DatabaseTreeInfoRequest();
				dRequest.setSiteCode(loginSiteCode);
				List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl.queryList(dRequest);
				if(dList.size()>0){
					EarlyDetailRequest eRequest = new EarlyDetailRequest();
					eRequest.setYesDay(DateUtils.getNextDay(new Date(),-1));
					code = dList.get(0).getCode();
					eRequest.setCode(code);
					if(!"bm0100".equalsIgnoreCase(loginSiteCode)){
						eRequest.setSiteCode(loginSiteCode);
					}
					list = earlyDetailServiceImpl.queryDatas(eRequest);
					List<EarlyDetail> listt = new ArrayList<EarlyDetail>();
					for (EarlyDetail earlyDetail : list) {
						int shi = (int)(1+Math.random()*24);
						int fen = (int)(1+Math.random()*60);
						int miao = (int)(1+Math.random()*60);
						String shishi = "";
						String fenfen = "";
						String miaomiao = "";
						shishi = CommonUtils.isTen(shi);
						fenfen = CommonUtils.isTen(fen);
						miaomiao = CommonUtils.isTen(miao);
						earlyDetail.setScanTime(earlyDetail.getScanTime()+" "+shishi+":"+fenfen+":"+miaomiao);
						changeEarlyDetail(earlyDetail,listt);
					}
					list.addAll(listt);
					MonitoringCacheUtils.put(conkey, list); // 将数据存到缓存中
				}
			}else{
				DatabaseTreeInfoRequest dRequest = new DatabaseTreeInfoRequest();
				dRequest.setSiteCode(loginSiteCode);
				List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl.queryList(dRequest);
				if(dList.size()>0){
					EarlyDetailRequest eRequest = new EarlyDetailRequest();
					eRequest.setYesDay(DateUtils.getNextDay(new Date(),-1));
					code = dList.get(0).getCode();
				}
			}
			EarlyDetailRequest eRequest = new EarlyDetailRequest();
			eRequest.setToDay(DateUtils.getTodayStandardStr());
			eRequest.setTomDay(DateUtils.getNextDay(new Date(),1));
			eRequest.setCode(code);
			allList = earlyDetailServiceImpl.queryNoConDatas(eRequest);
			allList.addAll(list);
			resultMap.put("list",allList);
			resultMap.put("success","true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("success","false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
		
	}
	/**
	 *  @Description:封装数据
	 * @author linhb --- 2017-1-09下午05:17:46                
	*/
	public void  changeEarlyDetail(EarlyDetail eDetail,List<EarlyDetail> list){
		int i= 0;
		if(eDetail.getSecurityBlank()>0){
			eDetail.setRemark("有空白栏目");
			i++;
		}
		if(eDetail.getSecurityService()>0){
			eDetail.setRemark("服务不实用");
			if(i>0){
				addEarlyDetail(eDetail, list,"服务不实用");
			}
			i++;
		}
		if(eDetail.getSecurityResponse()>0){
			eDetail.setRemark("互动回应差");
			if(i>0){
				addEarlyDetail(eDetail, list,"互动回应差");
			}
			i++;
		}
		if(eDetail.getSecurityBasic()>0){
			eDetail.setRemark("信息不更新");
			if(i>0){
				addEarlyDetail(eDetail, list,"信息不更新");
			}
			i++;
		}
		if(eDetail.getSecurityFatalError()>0){
			eDetail.setRemark("严重错误");
			if(i>0){
				addEarlyDetail(eDetail, list,"严重错误");
			}
			i++;
		}
		if(eDetail.getSecurityQuestion()>0){
			eDetail.setRemark("安全问题");
			if(i>0){
				addEarlyDetail(eDetail, list,"安全问题");
			}
			i++;
		}
	}
	/**
	 *  @Description:封装数据
	 * @author linhb --- 2017-1-09下午05:17:46                
	*/
	public void  addEarlyDetail(EarlyDetail eDetail,List<EarlyDetail> list,String s){
		EarlyDetail earlyDetail = new EarlyDetail();
		earlyDetail.setSiteCodeName(eDetail.getSiteCodeName()); 
		earlyDetail.setRemark(s);
		int shi = (int)(1+Math.random()*24);
		int fen = (int)(1+Math.random()*60);
		int miao = (int)(1+Math.random()*60);
		String shishi = "";
		String fenfen = "";
		String miaomiao = "";
		shishi = CommonUtils.isTen(shi);
		fenfen = CommonUtils.isTen(fen);
		miaomiao = CommonUtils.isTen(miao);
		earlyDetail.setScanTime(eDetail.getScanTime()+" "+shishi+":"+fenfen+":"+miaomiao);
		list.add(earlyDetail);
	}
	/**
	 * @Description:预警统计分页列表 
	 * @author sunjiang --- 2015年11月11日下午4:01:44
	 */
	public void queryList(){
		try {
			EarlyInfoRequest earlyInfoRequest = new EarlyInfoRequest();
			
			//获取当前登录人的网站标识码
			String siteCodeParam = request.getParameter("siteCode");
			if(StringUtils.isNotEmpty(siteCodeParam)){//填报单位处理
				ShiroUser shiroUser = getCurrentUserInfo();
				if(shiroUser.getSiteCode().length()==6){//组织单位跳转到填报，需要保存到session中
					shiroUser.setChildSiteCode(siteCodeParam);
					shiroUser.setType(1);
					DatabaseInfo databaseInfo = databaseInfoServiceImpl.findByDatabaseInfoCode(siteCodeParam);
					if(databaseInfo != null){
						shiroUser.setSiteName(databaseInfo.getName());
						shiroUser.setUrl(databaseInfo.getUrl());
						shiroUser.setIscost(databaseInfo.getIscost());
					}
					/**老合同信息**/
					ServicePeriod servicePd = getCurrentPeriod();
					/**新产品信息**/
					// Integer[] productTypeArr = {
					// CrmProductsType.DETECTION.getCode(),
					// CrmProductsType.CHECK.getCode() };
					// ServicePeriod servicePd =
					// getCurrentServicePeriod(productTypeArr);
					if(servicePd!=null){
						//获取当前期数
						int periodId=servicePd.getId();
						Map<String, Object> paramMap=new HashMap<String, Object>();
						paramMap.put("servicePeriodId", periodId);
						paramMap.put("siteCode", siteCodeParam);
						
						//通过网站标识码、当前周期期数、当前时间进行连表查询（站点信息表、服务周期表、报告结果表）
						List<ReportWordResult> reportWordList = reportWordResultServiceImpl.findSiteByMap(paramMap);
						
						if(reportWordList != null && reportWordList.size()>0){
							shiroUser.setPdfUrl(reportWordList.get(0).getPdfUrl());
						}
						removeSession(Constants.SHIRO_USER);
						add2Session(Constants.SHIRO_USER,shiroUser);
					}
				}
			
			}else{//组织单位
				//组织单位处理
				earlyInfoRequest.setDatabaseInfoList(getCurrentNextSiteCode());
			}
			
			String pos = request.getParameter("pos");
			if(StringUtils.isNotEmpty(pos)){
				earlyInfoRequest.setPageNo(Integer.parseInt(pos));
			}
			String size = request.getParameter("size");
			if(StringUtils.isNotEmpty(size)){
				earlyInfoRequest.setPageSize(Integer.parseInt(size));
			}
			earlyInfoRequest.setType(Integer.valueOf(earlyType));
			logger.info("queryList()===earlyType="+earlyType);
			PageVo<EarlyInfo> query = earlyInfoServiceImpl.queryEarlyInfo(earlyInfoRequest);
			List<EarlyInfo> data = query.getData();
			ArrayList<Object> list = new ArrayList<Object>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(data.size()>0){
				for (int i = 0; i < data.size(); i++) {
					HashMap<String, Object> map_early = new HashMap<String, Object>();
					map_early.put("dataNumber", i+1);
					EarlyInfo earlyInfo = data.get(i);
					String siteCode = earlyInfo.getSiteCode();
					map_early.put("siteCode", siteCode);
					map_early.put("earlyCheck", "<input type='checkbox'  name='table_check' value='"+siteCode+"'>");
					String siteName = earlyInfo.getSiteName();
					map_early.put("siteName", siteName);
					String earlyLevel = earlyInfo.getEarlyLevel();
					map_early.put("earlyLevel", earlyLevel);
					int newEarlyNum = earlyInfo.getNewEarlyNum();
					int earlySum = earlyInfo.getEarlySum();
					if(newEarlyNum!=0){
						map_early.put("newEarlyNum", "<span id='new"+earlyInfo.getSiteCode()+"'><span class='font-fb0012'>"+newEarlyNum+"</span>/"+earlySum+"</span>");
						map_early.put("read", "<pan id='s"+earlyInfo.getSiteCode()+"'><a href='javascript:void()' onclick='changeEarly(\""+newEarlyNum+"\",this)' id='"+earlyInfo.getSiteCode()+"'  class='view-modal' data-toggle='modal'>查看</a><i class='view-xin xin-icon'></i><span>");
					}else{
						map_early.put("newEarlyNum", "0/"+earlySum);
						map_early.put("read", "<a href='javascript:void()' id='"+earlyInfo.getSiteCode()+"' class='view-modal' data-toggle='modal'>查看</a><i class='view-xin'></i>");
					}
					list.add(map_early);
				}
			}
			map.put("body", list);
			map.put("totalRecords", query.getRecordSize());
			map.put("iTotalDisplayRecords", query.getRecordSize());
			map.put("hasMoreItems",true);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description:获取预警详情列表 
	 * @author sunjiang --- 2015年11月11日下午4:02:58
	 */
	public void getEarlyDetail(){
		
		try {
			
			HashMap<String, Object> mapParam = new HashMap<String, Object>();
			
			String siteCode = request.getParameter("siteCode");
			mapParam.put("siteCode", siteCode);
			
			EarlyInfo earlyInfo = earlyInfoServiceImpl.queryNewEarlyNum(mapParam);
			int newEarlyNum = earlyInfo.getNewEarlyNum();
			//新预警数设置为0
			if(newEarlyNum!=0){
				earlyInfo.setNewEarlyNum(0);
				earlyInfoServiceImpl.updateNewEarlyNum(earlyInfo);
			}
			
			String siteName = "";
			
			DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
			databaseInfoRequest.setSiteCode(siteCode);
			List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
			
			if(!CollectionUtils.isEmpty(queryList)){
				siteName = queryList.get(0).getName();
			}
			
			
			EarlyDetailRequest earlyDetailRequest = new EarlyDetailRequest();
			String pos = request.getParameter("pos");
			if(StringUtils.isNotEmpty(pos)){
				earlyDetailRequest.setPageNo(Integer.parseInt(pos));
			}
			String size = request.getParameter("size");
			if(StringUtils.isNotEmpty(size)){
				earlyDetailRequest.setPageSize(Integer.parseInt(size));
			}
			earlyDetailRequest.setSiteCode(siteCode);
			earlyDetailRequest.setType(Integer.valueOf(earlyType));
			PageVo<EarlyDetail> query = earlyDetailServiceImpl.query(earlyDetailRequest);
			List<EarlyDetail> data = query.getData();
			
			ArrayList<Object> list = new ArrayList<Object>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < data.size(); i++) {
				HashMap<String, Object> map_early = new HashMap<String, Object>();
				map_early.put("dataNumber", i+1);
				EarlyDetail earlyDetail = data.get(i);
//				int dicChannelId = earlyDetail.getDicChannelId();
//				DicChannel dicChannel = dicChannelServiceImpl.get(dicChannelId);
				String earlyTypeName=EarlyType.getNameByCode(earlyDetail.getType());
//				String channelName="";
//				if(dicChannel!=null && !"".equals(dicChannel)){
//					channelName= dicChannel.getChannelName();
//				}
				map_early.put("earlyTypeName", earlyTypeName);
				String queDescribe ="";
				if("1".equals(earlyType)){
					queDescribe =earlyDetail.getErrorCode()+"  "+QuestionType.getNameByCode(earlyDetail.getErrorCode());
				}else if("6".equals(earlyType)){
					queDescribe = earlyDetail.getQueDescribe()==null?"无":earlyDetail.getQueDescribe();
				}
				 
				map_early.put("queDescribe", queDescribe);
				String scanTime = earlyDetail.getScanTime();
				map_early.put("scanTime", scanTime);
				list.add(map_early);
			}
			map.put("siteName", siteName);
			map.put("pageTotal", query.getPageTotal());
			map.put("recordSize", query.getRecordSize());
			map.put("PageNo", query.getPageNo());
			map.put("body", list);
			map.put("earlySum", earlyInfo.getEarlySum());
			writerPrint(JSONObject.fromObject(map).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** @Description: 批量发送邮件
	 * @author sunjiaqi --- 2016-1-5下午05:17:46                
	*/
	public void sendEmail(){
		try {
			String parameters=request.getParameter("parameters");//前端页面勾选的数据集合
			String siteCodeParam = request.getParameter("siteCode");//页面url带的参数siteCode
			List<String> siteCodes = new ArrayList<String>();
			if(StringUtils.isNotEmpty(siteCodeParam)){
				siteCodes.add(siteCodeParam);
			}else{
				if (StringUtils.isNotEmpty(parameters)) {
					String[] s = parameters.split(",");// 按照,拆分分别记录
					for(String siteCode:s){
						siteCodes.add(siteCode);
					}
				}
			}
			doSendEmail(siteCodes);	
			writerPrint("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** @Description: 
	 * @author sunjiaqi --- 2016-1-28上午10:34:23                
	*/
	public void doSendEmail(List<String> siteCodes){
		try {
			for(String siteCodeParam: siteCodes){
				
				DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
				databaseInfoRequest.setSiteCode(siteCodeParam);
				List<DatabaseInfo> siteInfoList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
				
				
				if(siteInfoList!=null && siteInfoList.size()>0){
					
					
					DatabaseInfo wi = siteInfoList.get(0);
					String email= "";
					
					String email2 = wi.getEmail2();
					if(!StringUtils.isEmpty(email2)){
						email = email2;
					}else{
						email = wi.getEmail();
					}
					
					String siteName = wi.getName();
					
					
					EarlyInfoRequest earlyInfoRequest = new EarlyInfoRequest();
					earlyInfoRequest.setSiteCode(siteCodeParam);
					//查询earlyInfo表获取新预警数量，如果没有就发送无数据，
					//如果有就去earlyDetail详情表创建时间倒排序获取新预警数的数据
					PageVo<EarlyInfo>  eiList = earlyInfoServiceImpl.queryEarlyInfo(earlyInfoRequest);
					if(eiList!=null && eiList.getData().size()>0){
						EarlyInfo ei = eiList.getData().get(0);
						int newEarlNum = ei.getNewEarlyNum();//获取新预警数据
						if(newEarlNum>0){
							EarlyDetailRequest earlyDetailRequest = new EarlyDetailRequest();
							List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
							QueryOrder q = new QueryOrder("create_time", QueryOrderType.DESC);
							queryOrderList.add(q);
							earlyDetailRequest.setQueryOrderList(queryOrderList);
							earlyDetailRequest.setSiteCode(siteCodeParam);
							earlyDetailRequest.setPageNo(0);
							earlyDetailRequest.setPageSize(newEarlNum);
							List<EarlyDetail> edList = earlyDetailServiceImpl.queryList(earlyDetailRequest);
							
							
							ArrayList<EarlyDetail> datalist = new ArrayList<EarlyDetail>();
							
							if(!CollectionUtils.isEmpty(edList)){
								
								for (EarlyDetail earlyDetail : edList) {
									
									int dicChannelId = earlyDetail.getDicChannelId();
									
									DicChannel dicChannel = dicChannelServiceImpl.get(dicChannelId);
									if(dicChannel!=null){
										
										earlyDetail.setQuestionName(dicChannel.getChannelName());
									}
									
									datalist.add(earlyDetail);
								}
								
								
							}
							
							
							Map<Object, Object> map = new HashMap<Object, Object>();
							map.put("webNum", 1);
							map.put("newEarlNum", newEarlNum);
							map.put("nowTime", DateUtils.getNowTimeStandardStr());
							map.put("edList", datalist);
							map.put("siteName", siteName);
							map.put("siteCode", siteCodeParam);
							SendEmail.sendEmail(siteName+"预警通知", "yujing_t.ftl", map, email);
						}else{
							
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** @Description: 批量导出Excel
	 * @author sunjiaqi --- 2016-1-5下午06:02:13                
	*/
	public void downExcel(){
		String siteCodeParam = request.getParameter("siteCode");
		EarlyInfoRequest earlyInfoRequest = new EarlyInfoRequest();
		String userName = "";
		if(StringUtils.isNotEmpty(siteCodeParam)){
			earlyInfoRequest.setSiteCode(siteCodeParam);
			
			DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
			databaseInfoRequest.setSiteCode(siteCodeParam);
			List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
			if(!CollectionUtils.isEmpty(queryList)){
				userName = queryList.get(0).getName();
			}
			
			
		}else{
			ShiroUser shiroUser  = getCurrentUserInfo();
			userName = shiroUser.getUserName();
			earlyInfoRequest.setDatabaseInfoList(getCurrentNextSiteCode());
		}
		earlyInfoRequest.setPageNo(0);
		earlyInfoRequest.setPageSize(Integer.MAX_VALUE);
		earlyInfoRequest.setType(Integer.valueOf(earlyType));
		PageVo<EarlyInfo> query = earlyInfoServiceImpl.queryEarlyInfo(earlyInfoRequest);
		List<EarlyInfo> data = query.getData();
		//封装数据中查询的结果
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		//excel标题
		Object[] obj1 = new Object[]{"序号","网站标识码","网站名称","预警级别","预警个数"};
		list.add(obj1);
		if(data.size()>0){
			for (int i = 0; i < data.size(); i++) {
				EarlyInfo earlyInfo = data.get(i);
				if(null !=earlyInfo && !"".equals(earlyInfo)){
					
				
				String siteCode = earlyInfo.getSiteCode();
				String siteName = earlyInfo.getSiteName();
				String earlyLevel = earlyInfo.getEarlyLevel();
				int earlySum = earlyInfo.getEarlySum();
				Object[] obj = new Object[5];
				obj[0]=i+1;
				obj[1]=siteCode;
				obj[2]=siteName;
				if(StringUtils.isNotEmpty(earlyLevel)){
					obj[3]=earlyLevel;
				}else{
					obj[3]="";
				}
				
				obj[4]=earlySum;
				list.add(obj);
				}
			}
		}
		String fileName = userName +"预警信息("+DateUtils.formatStandardDate(new Date())+").xls";
		String title = userName + "预警信息"; 
		ExportExcel.spotCheckExcel(fileName, title, list);
	}
	
	
	public List<Object> getEarlyList() {
		return earlyList;
	}

	public void setEarlyList(List<Object> earlyList) {
		this.earlyList = earlyList;
	}
	public String getEarlyType() {
		return earlyType;
	}
	public void setEarlyType(String earlyType) {
		this.earlyType = earlyType;
	}
}
