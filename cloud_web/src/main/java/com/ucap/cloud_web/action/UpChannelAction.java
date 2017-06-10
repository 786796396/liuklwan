package com.ucap.cloud_web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.constant.ChannelNotUpdateType;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DicChannel;
import com.ucap.cloud_web.entity.SecurityHomeChannel;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDicChannelService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;

/**
 * <p>Description: </p>栏目信息不更新  --填报单位
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: UpChannelAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-11下午6:12:21 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UpChannelAction extends BaseAction {
	
	@Autowired
	private IDicChannelService dicChannelServiceImpl;

	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;
	
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	private String siteBeginServiceDate;
	
	public String getSiteBeginServiceDate() {
		return siteBeginServiceDate;
	}

	public void setSiteBeginServiceDate(String siteBeginServiceDate) {
		this.siteBeginServiceDate = siteBeginServiceDate;
	}
	private String menuType;
	
	private String yesDate;
	
	// 类型为全面检测产品
	// private Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
	// };
	
	public String getYesDate() {
		return yesDate;
	}

	public void setYesDate(String yesDate) {
		this.yesDate = yesDate;
	}
	/**
	 * log日志加载
	 */
	private static Log logger = LogFactory.getLog(LinkAllAction.class);

	/**
	 * @Description: 栏目信息不更新 --页面
	 * @author cuichx --- 2015-11-11下午6:14:30
	 * @return
	 */
	public String upChannelIndex() {
		try {
			// siteCode处理由组织单位跳转到该页面时，session的修改
			String siteCode = request.getParameter("siteCode");
			String mode = request.getParameter("mode");

			String servicePdIdZZ = request.getParameter("servicePeriodId");// 组织单位传来周期Id

			request.setAttribute("servicePdIdZZ", servicePdIdZZ);
			String siteSys = "";
			if (StringUtils.isNotEmpty(siteCode)) {
				setCurrentShiroUser(siteCode);
				siteSys = getCurrentUserInfo().getSiteCode();
			} else {
				// 从session中获取10位填报单位网站标识码
				siteCode = getCurrentUserInfo().getChildSiteCode();
				if (StringUtils.isEmpty(siteCode)) {
					siteCode = getCurrentUserInfo().getSiteCode();
				}
				siteSys = siteCode;
			}
			yesDate = DateUtils.getYesterdayStr();
			request.setAttribute("yesDate", yesDate);
			if (StringUtils.isNotEmpty(mode)) {
				request.setAttribute("modeTB", mode);
			}

			ServicePeriodRequest reques = new ServicePeriodRequest();
			List<ContractInfo> conList = getContractInfoList(siteSys, DateUtils.formatStandardDate(new Date()));
			reques.setComboI(4);// 高级版任务
			reques.setStartDate(DateUtils.formatStandardDate(new Date()));
			List<ServicePeriodRequest> periodList = null;
			if (conList != null && conList.size() > 0) {
				request.setAttribute("isSenior", 2);
				for (ContractInfo contractInfo : conList) {
					reques.setContractInfoId(contractInfo.getId());
				}
				periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
				if (periodList != null && periodList.size() > 0) {
					request.setAttribute("isSenior", 1);
				}
			} else {
				// 填报单位没有合同的时候走组织允许查看
				/*
				 * DatabaseLinkRequest dataLinkRequest=new
				 * DatabaseLinkRequest(); dataLinkRequest.setSiteCode(siteCode);
				 * dataLinkRequest.setPageSize(Integer.MAX_VALUE);
				 * List<DatabaseLink>
				 * dataLinkList=databaseLinkServiceImpl.queryList(
				 * dataLinkRequest);
				 */

				DatabaseTreeInfoRequest dbTreeRequetst = new DatabaseTreeInfoRequest();
				dbTreeRequetst.setSiteCode(siteCode);
				dbTreeRequetst.setIsLink(1);
				dbTreeRequetst.setPageSize(Integer.MAX_VALUE);
				List<DatabaseTreeInfo> treeList = databaseTreeInfoServiceImpl.queryList(dbTreeRequetst);

				String isSearchTb = "";
				if (treeList != null && treeList.size() > 0) {
					for (DatabaseTreeInfo treeInfo : treeList) {
						String zzSiteCode = treeInfo.getOrgSiteCode();
						conList = getContractInfoList(zzSiteCode, DateUtils.formatStandardDate(new Date()));
						reques.setComboI(4);// 高级版任务
						reques.setStartDate(DateUtils.formatStandardDate(new Date()));
						if (conList != null && conList.size() > 0) {
							for (ContractInfo contractInfo : conList) {
								isSearchTb = String.valueOf(contractInfo.getIsSearchTb());
								if (isSearchTb.equals("1")) {
									request.setAttribute("isSenior", 2);
									reques.setContractInfoId(contractInfo.getId());
									periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
									if (periodList != null && periodList.size() > 0) {
										request.setAttribute("isSenior", 1);
								}

							}
						}
					}
				}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * @Description: 填报单位查询服务周期
	 * @author Liukl --- 2017年2月10日10:30:37   
	 * @return getContractInfoServurity
	 */
	public void  essentialinformationMonitoringPeriod(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Object> list = new ArrayList<Object>();
		try {
			String siteCode = request.getParameter("siteCode");
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			ServicePeriodRequest reques = new ServicePeriodRequest();
			List<ContractInfo> conList = databaseBizServiceImpl.getContractInfoServurity(siteCode, DateUtils.formatStandardDate(new Date()));
			reques.setComboI(4);//高级版任务
			List<ServicePeriodRequest> periodList = null;
			String isSenior = "";
			if (conList != null && conList.size() > 0) {
				isSenior = "2";
				for (ContractInfo contractInfo : conList) {
					reques.setContractInfoId(contractInfo.getId());
					reques.setStartDate(DateUtils.formatStandardDate(contractInfo.getContractBeginTime()));
				}
				periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
				if(periodList != null && periodList.size()>0){
					isSenior = "1";
				}
			}else{
				
				DatabaseTreeInfoRequest dbTreeRequetst = new DatabaseTreeInfoRequest();
				dbTreeRequetst.setSiteCode(siteCode);
				dbTreeRequetst.setIsLink(1);
				dbTreeRequetst.setPageSize(Integer.MAX_VALUE);
				List<DatabaseTreeInfo> treeList = databaseTreeInfoServiceImpl.queryList(dbTreeRequetst);
				String isSearchTb = "";
				if(treeList!=null && treeList.size()>0){
				for (DatabaseTreeInfo tree : treeList) {
				String zzSiteCode = tree.getOrgSiteCode();
						conList = databaseBizServiceImpl.getContractInfoServurity(zzSiteCode, DateUtils.formatStandardDate(new Date()));
				reques.setComboI(4);//高级版任务
				reques.setStartDate(DateUtils.formatStandardDate(new Date()));
						if (conList != null && conList.size() > 0) {
							for (ContractInfo contractInfo : conList) {
								isSearchTb = String.valueOf(contractInfo.getIsSearchTb());
						if(isSearchTb.equals("1")){
							isSenior = "2";
									reques.setContractInfoId(contractInfo.getId());
							periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
							if(periodList != null && periodList.size()>0){
							isSenior = "1";
							}
						}
					}
				}else{
					isSenior = "2";
				}
			}}}
			//日期拼接
			if (periodList != null && periodList.size() > 0) {
				for (int i = 0; i < periodList.size(); i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					StringBuffer sb = new StringBuffer(1000);
					ServicePeriodRequest servicePeriod = periodList.get(i);
					int spId = servicePeriod.getId();
					String startDate = servicePeriod.getStartDate();
					String endDate = servicePeriod.getEndDate();
					if (StringUtils.isEmpty(startDate)) {
						startDate = "";
					}
					if (StringUtils.isEmpty(endDate)) {
						endDate = "";
					}
					sb.append(startDate).append("至").append(endDate);   // 样式  2016-04-08至2016-05-08
					map.put("id", spId);
					map.put("cycleDate", sb.toString());
					list.add(map);
				}
			}
			resultMap.put("isSenior", isSenior);//用于判断人工，系统检测
			resultMap.put("scanCycleList", list);
			resultMap.put("size", list.size());
			resultMap.put("errorMsg", "更新成功！");
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "更新失败！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	
	/** 新产品信息 **/
	/**
	 * @Description: 栏目信息不更新 --页面
	 * @author cuichx --- 2015-11-11下午6:14:30
	 * @return
	 */
	// public String upChannelIndex() {
	// try {
	// // siteCode处理由组织单位跳转到该页面时，session的修改
	// String siteCode = request.getParameter("siteCode");
	// String mode = request.getParameter("mode");
	//
	// String servicePdIdZZ = request.getParameter("servicePeriodId");//
	// 组织单位传来周期Id
	//
	// request.setAttribute("servicePdIdZZ", servicePdIdZZ);
	// String siteSys = "";
	// if (StringUtils.isNotEmpty(siteCode)) {
	// setCurrentShiroUser(siteCode);
	// siteSys = getCurrentUserInfo().getSiteCode();
	// } else {
	// // 从session中获取10位填报单位网站标识码
	// siteCode = getCurrentUserInfo().getChildSiteCode();
	// if (StringUtils.isEmpty(siteCode)) {
	// siteCode = getCurrentUserInfo().getSiteCode();
	// }
	// siteSys = siteCode;
	// }
	// yesDate = DateUtils.getYesterdayStr();
	// request.setAttribute("yesDate", yesDate);
	// if (StringUtils.isNotEmpty(mode)) {
	// request.setAttribute("modeTB", mode);
	// }
	//
	// ServicePeriodRequest reques = new ServicePeriodRequest();
	// reques.setComboI(4);// 高级版任务
	// reques.setStartDate(DateUtils.formatStandardDate(new Date()));
	//
	// List<ServicePeriodRequest> periodList = null;
	// List<CrmProductsResponse> crmlist = new ArrayList<CrmProductsResponse>();
	// crmlist = getCrmProductsList(siteSys, productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// request.setAttribute("isSenior", 2);
	// for (CrmProductsResponse crm : crmlist) {
	// reques.setContractInfoId(crm.getId());
	// }
	// periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
	// if (periodList != null && periodList.size() > 0) {
	// request.setAttribute("isSenior", 1);
	// }
	// } else {
	//
	// DatabaseTreeInfoRequest dbTreeRequetst = new DatabaseTreeInfoRequest();
	// dbTreeRequetst.setSiteCode(siteCode);
	// dbTreeRequetst.setPageSize(Integer.MAX_VALUE);
	// List<DatabaseTreeInfo> treeList =
	// databaseTreeInfoServiceImpl.queryList(dbTreeRequetst);
	//
	// String isSearchTb = "";
	// if (treeList != null && treeList.size() > 0) {
	// for (DatabaseTreeInfo treeInfo : treeList) {
	// String zzSiteCode = treeInfo.getOrgSiteCode();
	// crmlist = getCrmProductsList(zzSiteCode, productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	//
	// reques.setComboI(4);// 高级版任务
	// reques.setStartDate(DateUtils.formatStandardDate(new Date()));
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// for (CrmProductsResponse crm : crmlist) {
	// isSearchTb = String.valueOf(crm.getIsSearchTb());
	// if (isSearchTb.equals("1")) {
	// request.setAttribute("isSenior", 2);
	// reques.setContractInfoId(crm.getId());
	// periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
	// if (periodList != null && periodList.size() > 0) {
	// request.setAttribute("isSenior", 1);
	// }
	//
	// }
	// }
	// }
	// }
	// }
	//
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return "success";
	// }

	/**
	 * @Description: 填报单位查询服务周期
	 * @author Liukl --- 2017年2月10日10:30:37
	 * @return
	 */
	// public void essentialinformationMonitoringPeriod(){
	// Map<String, Object> resultMap = new HashMap<String, Object>();
	// List<Object> list = new ArrayList<Object>();
	// try {
	// String siteCode = request.getParameter("siteCode");
	// if(StringUtils.isEmpty(siteCode)){
	// siteCode=getCurrentUserInfo().getSiteCode();
	// }
	// ServicePeriodRequest reques = new ServicePeriodRequest();
	// List<CrmProductsResponse> crmlist = new ArrayList<CrmProductsResponse>();
	// crmlist = getCrmProductsList(siteCode, productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null,
	// null);
	//
	// reques.setComboI(4);//高级版任务
	// List<ServicePeriodRequest> periodList = null;
	// String isSenior = "";
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// isSenior = "2";
	// for (CrmProductsResponse crm : crmlist) {
	// reques.setContractInfoId(crm.getId());
	// reques.setStartDate(DateUtils.formatStandardDate(crm.getBeginTime()));
	// }
	// periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
	// if(periodList != null && periodList.size()>0){
	// isSenior = "1";
	// }
	// }else{
	//
	// DatabaseTreeInfoRequest dbTreeRequetst = new DatabaseTreeInfoRequest();
	// dbTreeRequetst.setSiteCode(siteCode);
	// dbTreeRequetst.setIsBigdata(1);
	// dbTreeRequetst.setPageSize(Integer.MAX_VALUE);
	// List<DatabaseTreeInfo> treeList =
	// databaseTreeInfoServiceImpl.queryList(dbTreeRequetst);
	// String isSearchTb = "";
	// if(treeList!=null && treeList.size()>0){
	// for (DatabaseTreeInfo tree : treeList) {
	// String zzSiteCode = tree.getOrgSiteCode();
	// crmlist = getCrmProductsList(zzSiteCode, productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	// reques.setComboI(4);//高级版任务
	// reques.setStartDate(DateUtils.formatStandardDate(new Date()));
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// for (CrmProductsResponse crm : crmlist) {
	// isSearchTb = String.valueOf(crm.getIsSearchTb());
	// if(isSearchTb.equals("1")){
	// isSenior = "2";
	// reques.setContractInfoId(crm.getId());
	// periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
	// if(periodList != null && periodList.size()>0){
	// isSenior = "1";
	// }
	// }
	// }
	// }else{
	// isSenior = "2";
	// }
	// }}}
	// //日期拼接
	// if (periodList != null && periodList.size() > 0) {
	// for (int i = 0; i < periodList.size(); i++) {
	// HashMap<String, Object> map = new HashMap<String, Object>();
	// StringBuffer sb = new StringBuffer(1000);
	// ServicePeriodRequest servicePeriod = periodList.get(i);
	// int spId = servicePeriod.getId();
	// String startDate = servicePeriod.getStartDate();
	// String endDate = servicePeriod.getEndDate();
	// if (StringUtils.isEmpty(startDate)) {
	// startDate = "";
	// }
	// if (StringUtils.isEmpty(endDate)) {
	// endDate = "";
	// }
	// sb.append(startDate).append("至").append(endDate); // 样式
	// 2016-04-08至2016-05-08
	// map.put("id", spId);
	// map.put("cycleDate", sb.toString());
	// list.add(map);
	// }
	// }
	// resultMap.put("isSenior", isSenior);//用于判断人工，系统检测
	// resultMap.put("scanCycleList", list);
	// resultMap.put("size", list.size());
	// resultMap.put("errorMsg", "更新成功！");
	// resultMap.put("success", "true");
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// } catch (Exception e) {
	// resultMap.put("success", "false");
	// resultMap.put("errorMsg", "更新失败！");
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// e.printStackTrace();
	// }
	// }
	
	/**
	 * @Description: 栏目信息不更新--分页列表
	 * @author cuichx --- 2015-11-11下午6:15:06
	 */
	public void updateChannelPage(){
		
		Map<String,Object> resultMap=new HashMap<String, Object>();
		List<SecurityHomeChannel> noAutoList = new ArrayList<SecurityHomeChannel>();
		List<SecurityHomeChannel> noAutoListAll = new ArrayList<SecurityHomeChannel>();
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			String orgSiteCode = request.getParameter("siteCode");
			
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			if(StringUtils.isEmpty(orgSiteCode)){
				orgSiteCode=getCurrentUserInfo().getSiteCode();
			}
			String conCode = "";
			if(orgSiteCode.length()==6){
				conCode = orgSiteCode;
			}else{
				conCode = siteCode;
			}
			
			String scanDate=DateUtils.getYesterdayStr();
			//获取关键字查询的关键字
			String key=request.getParameter("key");	//模糊查询参数
			String channelType=request.getParameter("type"); //栏目类别参数   0全部   1动态、要闻类    2通知公告、政策文件   3人事、规划
			String servicePdId = request.getParameter("servicePdId");//人工检测周期id
			String systemTwo = request.getParameter("systemTwo");//点击系统检测
			String updateVal = request.getParameter("updateVal"); //0  全部  1逾期未更新 2正常更新
			
			//获取当前合同方法
			/**老合同信息**/
//			getCurrentUserInfo().getIscost()==1
//			getCurrentUserInfo().getContractId();  
			List<ContractInfo> crmlist = getContractInfoList(conCode, DateUtils.formatStandardDate(new Date()));
			if (getCurrentUserInfo().getIscost() == 0) { //为0 提示用户需要付费才可以使用
				// 没有查询到此siteCode的合同,直接返回页面，提示相关信息
				resultMap.put("success", "other");
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return;
			}
			/**新产品信息**/
			// List<CrmProductsResponse> crmlist = new
			// ArrayList<CrmProductsResponse>();
			// crmlist = getCrmProductsList(conCode, productTypeArr,
			// DateUtils.formatStandardDate(new Date()), null, null);
			SecurityHomeChannelRequest homerequest=new SecurityHomeChannelRequest();
			ServicePeriodRequest reques = new ServicePeriodRequest();
			if (CollectionUtils.isNotEmpty(crmlist)) {
				reques.setComboI(4);//高级版任务参数
				reques.setStartDate(DateUtils.formatStandardDate(new Date()));
				List<ServicePeriodRequest> periodList = null;
				for (ContractInfo crm : crmlist) {
					reques.setContractInfoId(crm.getId());// 合同的id
				}
				periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
				//有合同，无高级版任务，用昨天查询
				homerequest.setScanDate(scanDate);
				homerequest.setAutoInput("auto");//机器
				resultMap.put("scanDateSystem", "2");//返回参数，用于判断展示系统检测界面
				request.setAttribute("sssSystem", "2");
				//系统检测按照未更新天数倒排序
				List<QueryOrder> queryList=new ArrayList<QueryOrder>();
				QueryOrder queryOrder=new QueryOrder("not_update_num",QueryOrderType.DESC);
				queryList.add(queryOrder);
				homerequest.setQueryOrderList(queryList);
				if(periodList.size()>0 && periodList != null){
					//有合同，有高级版任务，用周期查询
					if(systemTwo.equals("1")){
						homerequest.setAutoInput(null);//机器
						homerequest.setNoAutoInput("no");//机器
						homerequest.setScanDate(null);
						homerequest.setQueryOrderList(null);
						if(StringUtils.isNotEmpty(servicePdId)){
							homerequest.setServicePeriodId(Integer.valueOf(servicePdId));
						}
						resultMap.put("testing", "1");//1 表示人工检测选中
						resultMap.put("scanDateSystem", "1");//返回参数，高级版
						request.setAttribute("scanDateSystem", "1");
					}else{
						resultMap.put("scanDateSystem", "2");//返回参数，标准版
					}
				}else{
					resultMap.put("testing", "2");//2表示系统检测选中
					resultMap.put("scanDateSystem", "2");//返回参数，标准版
				}
			}else{       
				DatabaseTreeInfoRequest dbTreeRequetst = new DatabaseTreeInfoRequest();
				dbTreeRequetst.setSiteCode(siteCode);
				dbTreeRequetst.setIsLink(1);
				dbTreeRequetst.setPageSize(Integer.MAX_VALUE);
				List<DatabaseTreeInfo> treeList = databaseTreeInfoServiceImpl.queryList(dbTreeRequetst);
				
				String isSearchTb = "";
				if(treeList!=null && treeList.size()>0){
				for (DatabaseTreeInfo tree : treeList) {
				String zzSiteCode = tree.getOrgSiteCode();
						crmlist = getContractInfoList(zzSiteCode, DateUtils.formatStandardDate(new Date()));
						// crmlist = getCrmProductsList(zzSiteCode,
						// productTypeArr,
						// DateUtils.formatStandardDate(new Date()), null,
						// null);
						if (CollectionUtils.isNotEmpty(crmlist)) {
							for (ContractInfo crm : crmlist) {
								isSearchTb = String.valueOf(crm.getIsSearchTb());
						if(isSearchTb.equals("1")){
							reques.setComboI(4);//高级版任务参数
							reques.setStartDate(DateUtils.formatStandardDate(new Date()));
							List<ServicePeriodRequest> periodList = null;
									reques.setContractInfoId(crm.getId());// 合同的id
							periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
							//有合同，无高级版任务，用昨天查询
							homerequest.setScanDate(scanDate);
							homerequest.setAutoInput("auto");//机器
							resultMap.put("scanDateSystem", "2");//返回参数，用于判断展示系统检测界面
							request.setAttribute("sssSystem", "2");
							//系统检测按照未更新天数倒排序
							List<QueryOrder> queryList=new ArrayList<QueryOrder>();
							QueryOrder queryOrder=new QueryOrder("not_update_num",QueryOrderType.DESC);
							queryList.add(queryOrder);
							homerequest.setQueryOrderList(queryList);
							if(periodList.size()>0 && periodList != null){
								//有合同，有高级版任务，用周期查询
								if(systemTwo.equals("1")){
									homerequest.setAutoInput(null);//机器
									homerequest.setNoAutoInput("no");//机器
									homerequest.setScanDate(null);
									homerequest.setQueryOrderList(null);
									if(StringUtils.isNotEmpty(servicePdId)){
										homerequest.setServicePeriodId(Integer.valueOf(servicePdId));
									}
									resultMap.put("testing", "1");//1 表示人工检测选中
									resultMap.put("scanDateSystem", "1");//返回参数，用于判断展示系统检测界面
									request.setAttribute("scanDateSystem", "1");
								}else{
									resultMap.put("scanDateSystem", "2");//返回参数，标准版
								}
							}else{
								resultMap.put("testing", "2");//2表示系统检测选中
								resultMap.put("scanDateSystem", "2");//返回参数，标准版
							}
						}
					}
				}}}
			}
			//通过重点栏目id获取栏目更新趋势表中的数据
			if("1".equals(updateVal)){ //查逾期
				homerequest.setFlag(false);//非全选
			}else if("2".equals(updateVal)){
				homerequest.setFlag(true);//查正常更新
			}
			//下拉框选择的栏目类别
			homerequest.setSelectType(channelType);
			String pos = request.getParameter("pos");
			//获取排序条件
			String sSortDir_0 = request.getParameter("sSortDir_0");//控制升序和降序
			String soraFiel = request.getParameter("mDataProp_"+request.getParameter("iSortCol_0"));//获取要排序的字段
			homerequest.setSiteCode(siteCode);
			homerequest.setType(2);
			if(StringUtils.isNotEmpty(key)){
				homerequest.setTerms(key);
			}
			if(StringUtils.isNotEmpty(pos)){
				homerequest.setPageNo(Integer.parseInt(pos));
			}
			homerequest.setPageSize(Integer.MAX_VALUE);
			//排序字段
			if(soraFiel!=null){
				List<QueryOrder> queryList=new ArrayList<QueryOrder>();
				if(!"".equals(sSortDir_0) && "asc".equals(sSortDir_0)){
					QueryOrder queryOrder=new QueryOrder("not_update_num",QueryOrderType.ASC);
					queryList.add(queryOrder);
					homerequest.setQueryOrderList(queryList);
				}else{//默认排序字段
					QueryOrder queryOrder=new QueryOrder("not_update_num",QueryOrderType.DESC);
					queryList.add(queryOrder);
					homerequest.setQueryOrderList(queryList);
				}
			}
			
			if("1".equals(updateVal)){
				if("0".equals(channelType)){
					homerequest.setSelectType("4");
				}
				// 新的需求  查出  非删除的数据  即可  
				if(systemTwo.equals("1")){
					if(StringUtils.isNotEmpty(servicePdId)){
						noAutoList = securityHomeChannelServiceImpl.queryListTb(homerequest);
					}
				}else if(systemTwo.equals("2")){
					noAutoList = securityHomeChannelServiceImpl.queryListTb(homerequest);
				}
				
				noAutoList = getNotUpdateList(noAutoList,false);
				
			}else if("2".equals(updateVal)){   //查正常更新
				// 新的需求  查出  非删除的数据  即可  
				if(systemTwo.equals("1")){
					if(StringUtils.isNotEmpty(servicePdId)){
						noAutoList = securityHomeChannelServiceImpl.queryListTbNormal(homerequest);
					}
				}else if(systemTwo.equals("2")){
					noAutoList = securityHomeChannelServiceImpl.queryListTbNormal(homerequest);
				}
				noAutoList = getNotUpdateList(noAutoList,true);
			}else{ //查全部  逾期未更新+正常更新
				if("0".equals(channelType)){
					homerequest.setSelectType("4");
				}
				homerequest.setFlag(false);//非全选
				// 新的需求  查出  非删除的数据  即可  
				if(systemTwo.equals("1")){
					if(StringUtils.isNotEmpty(servicePdId)){
						noAutoList = securityHomeChannelServiceImpl.queryListTb(homerequest);
					}
				}else if(systemTwo.equals("2")){
					noAutoList = securityHomeChannelServiceImpl.queryListTb(homerequest);
				}
				
				noAutoList = getNotUpdateList(noAutoList,false);
				
				
				noAutoListAll.addAll(noAutoList);
				
				homerequest.setFlag(true);//非全选
				if("0".equals(channelType)){
					homerequest.setSelectType("0");
				}
				if(systemTwo.equals("1")){
					if(StringUtils.isNotEmpty(servicePdId)){
						noAutoList = securityHomeChannelServiceImpl.queryListTbNormal(homerequest);
					}
				}else if(systemTwo.equals("2")){
					noAutoList = securityHomeChannelServiceImpl.queryListTbNormal(homerequest);
				}
				
				noAutoList = getNotUpdateList(noAutoList,true);
				noAutoListAll.addAll(noAutoList);
				noAutoList = noAutoListAll;
			}
			
			Map<String,Map<String, Object>> groupMap=new HashMap<String, Map<String, Object>>();
			if(noAutoList!=null && noAutoList.size()>0){
				for(int i=0;i<noAutoList.size();i++){
					Map<String,Object> map=new HashMap<String, Object>();
					SecurityHomeChannel homeChannel=new SecurityHomeChannel();
					homeChannel=noAutoList.get(i);
					
					int beginNum = i+1;
					map.put("dataNumber", beginNum);//序号
					
					if(StringUtils.isNotEmpty(homeChannel.getName())){
						map.put("channel_name", homeChannel.getName());//栏目名称
					}else{
						map.put("channel_name", "");//栏目名称
					}
					DicChannel dicChannel=dicChannelServiceImpl.get(homeChannel.getChannelType());//
					if(null ==dicChannel || StringUtils.isEmpty(dicChannel.getChannelName())){
						map.put("dicChannelId","");//栏目类别
					}else{
						map.put("dicChannelId",dicChannel.getChannelName());//栏目类别
					}
					
					//------------------------根据dicChannel  栏目类别判断   需要多少天才判定为逾期未更新？？？
					//问题类型
				/*	String problemTypeName=SecurityBasicType.getNameByCode(homeChannel.getProblemTypId());
					if(StringUtils.isNotEmpty(problemTypeName)){
						map.put("problemTypeId",problemTypeName );
					}else{
						map.put("problemTypeId","");
					}*/
					
					if(StringUtils.isNotEmpty(homeChannel.getNotUpdateStr())){
						map.put("problemTypeId",homeChannel.getNotUpdateStr());
					}else{
						map.put("problemTypeId","");
					}
					
					map.put("url", homeChannel.getUrl());//网址
					map.put("scanDate", homeChannel.getScanDate());//监测时间
					if(StringUtils.isNotEmpty(homeChannel.getModifyDate())){
						map.put("modify_date", homeChannel.getModifyDate());//最后更新时间(0000-00-00 00:00:00)
					}else{
						map.put("modify_date", "");//最后更新时间(0000-00-00 00:00:00)
					}
					
					if(null !=homeChannel.getNotUpdateNum()){
						map.put("unUpdateDays", homeChannel.getNotUpdateNum());//未更新天数
					}else{
						map.put("unUpdateDays", "");//未更新天数
					}
					map.put("flagType", homeChannel.getFlagType());
					String imgUrl=homeChannel.getImageUrl();
					if(StringUtils.isNotEmpty(imgUrl)){
						if(imgUrl.startsWith("htm")){
							map.put("imgUrl", urlAdapterVar.getImgUrl()+imgUrl);//快照
						}else{
							map.put("imgUrl", imgUrl);//问题截图
						}
					}else{
						map.put("imgUrl", "");//快照
					}
					map.put("userId", homeChannel.getUserId());//操作人
					
					//add by Na.Y 20160818 返回最后正常监测时间 start
					if(StringUtils.isNotEmpty(homeChannel.getLastAccessDate())){
						map.put("lastAccessDate", homeChannel.getLastAccessDate());//
					}else{
						map.put("lastAccessDate", "暂无数据");//
					}
					
					
					map.put("siteCode", homeChannel.getSiteCode());
					groupMap.put(""+i, map);
				}
			}
			
			if(systemTwo != null && !"".equals(systemTwo)){
				resultMap.put("scanDateSystem", systemTwo);//返回参数，标准版
			}
			resultMap.put("litImgUrl", urlAdapterVar.getLinkUrl());
			resultMap.put("body", groupMap);
			resultMap.put("success", "true");
			resultMap.put("listSize", groupMap.size());
			logger.info("resultMap="+resultMap);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/** @Description: 内容保障问题-栏目信息不更新监测结果导出Excel
	 * @author zhurk --- 2015-11-25下午12:39:36                
	*/
	public void unUpdateChannelExcel(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String title = "基本信息监测结果";
			ServicePeriod sp = null;
			ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj1 = new Object[]{"序号","栏目名称","栏目类别","问题类型","网址","监测时间","最后更新日期","未更新天数","逾期未更新","快照/截图"};
		
		String servicePdId = request.getParameter("servicePdId");//人工检测周期id
		String systemTwo = request.getParameter("systemTwo");//点击系统检测
		String updateVal = request.getParameter("updateVal"); //0  全部  1逾期未更新 2正常更新
		String fileName = "";
		String userName = Constants.getCurrendUser().getUserName().trim();//获得当前登录站点名称
		if(systemTwo.equals("1")){
			if(StringUtils.isNotEmpty(servicePdId)){
				 sp = servicePeriodServiceImpl.get(Integer.parseInt(servicePdId));
					title = "基本信息监测结果(" + sdf.format(sp.getStartDate())
							+ "至" + sdf.format(sp.getEndDate()) + ")";
					fileName =userName + "-内容保障问题-基本信息监测结果("+sdf.format(sp.getStartDate())
							    + "至" + sdf.format(sp.getEndDate())+").xls";
			}
		}else{
			fileName = userName+"-内容保障问题-基本信息监测结果("+DateUtils.formatStandardDate(new Date())+").xls";
		}
		if("2".equals(systemTwo)){
			obj1 = new Object[]{"序号","栏目名称","栏目类别","问题类型","网址","监测时间","最后更新日期","未更新天数","逾期未更新","快照/截图","最后正常监测时间"};
		}
		list.add(obj1);
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
//		String siteCode = request.getParameter("siteCode");
		String orgSiteCode = request.getParameter("siteCode");
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		String conCode = "";
		if(orgSiteCode.length()==6){
			conCode = orgSiteCode;
		}else{
			conCode = siteCode;
		}
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		String scanDate=DateUtils.getYesterdayStr();
	
		/**老合同信息**/
			List<ContractInfo> crmlist = getContractInfoList(conCode, DateUtils.formatStandardDate(new Date()));
		/**新产品信息**/
			// List<CrmProductsResponse> crmlist = new
			// ArrayList<CrmProductsResponse>();
			// crmlist = getCrmProductsList(conCode, productTypeArr,
			// DateUtils.formatStandardDate(new Date()), null, null);
		SecurityHomeChannelRequest homerequest=new SecurityHomeChannelRequest();
		ServicePeriodRequest reques = new ServicePeriodRequest();
			if (CollectionUtils.isNotEmpty(crmlist)) {
			reques.setComboI(4);//高级版任务参数
			reques.setStartDate(DateUtils.formatStandardDate(new Date()));
			List<ServicePeriodRequest> periodList = null;
				for (ContractInfo crm : crmlist) {
					reques.setContractInfoId(crm.getId());// 合同的id
			}
			periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
			//有合同，无高级版任务，用昨天查询
			homerequest.setScanDate(scanDate);
			homerequest.setAutoInput("auto");//机器
			//系统检测按照未更新天数倒排序
			List<QueryOrder> queryList=new ArrayList<QueryOrder>();
			QueryOrder queryOrder=new QueryOrder("not_update_num",QueryOrderType.DESC);
			queryList.add(queryOrder);
			homerequest.setQueryOrderList(queryList);
			if(periodList.size()>0 && periodList != null){
				//有合同，有高级版任务，用周期查询
				if(systemTwo.equals("1")){
					homerequest.setAutoInput(null);//机器
					homerequest.setNoAutoInput("no");//机器
					homerequest.setScanDate(null);
					homerequest.setQueryOrderList(null);
					if(StringUtils.isNotEmpty(servicePdId)){
						homerequest.setServicePeriodId(Integer.valueOf(servicePdId));
					}
				}
			}
		}else{
			
			DatabaseTreeInfoRequest dbTreeRequetst = new DatabaseTreeInfoRequest();
			dbTreeRequetst.setSiteCode(siteCode);
			dbTreeRequetst.setPageSize(Integer.MAX_VALUE);
			List<DatabaseTreeInfo> treeList = databaseTreeInfoServiceImpl.queryList(dbTreeRequetst);
			
			String isSearchTb = "";
			if(treeList!=null && treeList.size()>0){
				for (DatabaseTreeInfo tree : treeList) {
				String zzSiteCode = tree.getOrgSiteCode();
				/**老合同信息**/
						crmlist = getContractInfoList(zzSiteCode, DateUtils.formatStandardDate(new Date()));
				/**新产品信息**/
						// crmlist = getCrmProductsList(zzSiteCode,
						// productTypeArr,
						// DateUtils.formatStandardDate(new Date()), null,
						// null);
						if (CollectionUtils.isNotEmpty(crmlist)) {
							for (ContractInfo crm : crmlist) {
								isSearchTb = String.valueOf(crm.getIsSearchTb());
							if(isSearchTb.equals("1")){
								reques.setComboI(4);//高级版任务参数
								reques.setStartDate(DateUtils.formatStandardDate(new Date()));
								List<ServicePeriodRequest> periodList = null;
									reques.setContractInfoId(crm.getId());// 合同的id
								periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
								//有合同，无高级版任务，用昨天查询
								homerequest.setScanDate(scanDate);
								homerequest.setAutoInput("auto");//机器
								request.setAttribute("sssSystem", "2");
								//系统检测按照未更新天数倒排序
								List<QueryOrder> queryList=new ArrayList<QueryOrder>();
								QueryOrder queryOrder=new QueryOrder("not_update_num",QueryOrderType.DESC);
								queryList.add(queryOrder);
								homerequest.setQueryOrderList(queryList);
								if(periodList.size()>0 && periodList != null){
									//有合同，有高级版任务，用周期查询
									if(systemTwo.equals("1")){
										homerequest.setAutoInput(null);//机器
										homerequest.setNoAutoInput("no");//机器
										homerequest.setScanDate(null);
										homerequest.setQueryOrderList(null);
										if(StringUtils.isNotEmpty(servicePdId)){
											homerequest.setServicePeriodId(Integer.valueOf(servicePdId));
										}									
									}
								}
							}
						}
					}
				}
			}
		}
		String channelType=request.getParameter("type");
		//获取关键字查询的关键字
		String key=request.getParameter("key");
		//通过重点栏目id获取栏目更新趋势表中的数据
		//通过重点栏目id获取栏目更新趋势表中的数据
		if("1".equals(updateVal)){ //查逾期
			homerequest.setFlag(false);//非全选
		}else if("2".equals(updateVal)){
			homerequest.setFlag(true);//查正常更新
		}
		//下拉框选择的类型
		homerequest.setSelectType(channelType);
		
		if(StringUtils.isNotEmpty(key)){
			homerequest.setTerms(key);
		}
		homerequest.setType(2);
		homerequest.setSiteCode(siteCode);
		List<SecurityHomeChannel>  homeList = new ArrayList<SecurityHomeChannel>();
		List<SecurityHomeChannel>  homeListAll = new ArrayList<SecurityHomeChannel>();
		
		if("1".equals(updateVal)){
			if("0".equals(channelType)){
				homerequest.setSelectType("4");
			}
			// 新的需求  查出  非删除的数据  即可  
			if(systemTwo.equals("1")){
				if(StringUtils.isNotEmpty(servicePdId)){
					homeList = securityHomeChannelServiceImpl.queryListTb(homerequest);
				}
			}else if(systemTwo.equals("2")){
				homeList = securityHomeChannelServiceImpl.queryListTb(homerequest);
			}
			
			homeList = getNotUpdateList(homeList,false);
		}else if("2".equals(updateVal)){   //查正常更新
			// 新的需求  查出  非删除的数据  即可  
			if(systemTwo.equals("1")){
				if(StringUtils.isNotEmpty(servicePdId)){
					homeList = securityHomeChannelServiceImpl.queryListTbNormal(homerequest);
				}
			}else if(systemTwo.equals("2")){
				homeList = securityHomeChannelServiceImpl.queryListTbNormal(homerequest);
			}
			
			homeList = getNotUpdateList(homeList,true);
		}else{ //查全部
			if("0".equals(channelType)){
				homerequest.setSelectType("4");
			}
			homerequest.setFlag(false);//非全选
			// 新的需求  查出  非删除的数据  即可  
			if(systemTwo.equals("1")){
				if(StringUtils.isNotEmpty(servicePdId)){
					homeList = securityHomeChannelServiceImpl.queryListTb(homerequest);
				}
			}else if(systemTwo.equals("2")){
				homeList = securityHomeChannelServiceImpl.queryListTb(homerequest);
			}
			homeList = getNotUpdateList(homeList,false);
			homeListAll.addAll(homeList);
			
			homerequest.setFlag(true);//非全选
			if("0".equals(channelType)){
				homerequest.setSelectType("0");
			}
			if(systemTwo.equals("1")){
				if(StringUtils.isNotEmpty(servicePdId)){
					homeList = securityHomeChannelServiceImpl.queryListTbNormal(homerequest);
				}
			}else if(systemTwo.equals("2")){
				homeList = securityHomeChannelServiceImpl.queryListTbNormal(homerequest);
			}
			homeList = getNotUpdateList(homeList,true);
			homeListAll.addAll(homeList);
			homeList = homeListAll;
		}
		
		if(homeList!=null && homeList.size()>0){
				for(int i=0;i<homeList.size();i++){
					SecurityHomeChannel homeChannel=homeList.get(i);
					int dataNumber=i+1;
					
					Object[] obj = new Object[10];
					if("2".equals(systemTwo)){
						obj =  new Object[11];
					}
					obj[0]=dataNumber;//序号
					if(StringUtils.isNotEmpty(homeChannel.getName())){
						obj[1]=homeChannel.getName();//栏目名称
					}else{
						obj[1]="";//栏目名称
					}
					
					DicChannel dicChannel=dicChannelServiceImpl.get(homeChannel.getChannelType());//
					
					obj[2]=dicChannel.getChannelName() == null?"":dicChannel.getChannelName();//栏目分类
					//问题类型
				/*	String problemTypeName=SecurityBasicType.getNameByCode(homeChannel.getProblemTypId());
					if(StringUtils.isNotEmpty(problemTypeName)){
						obj[3]= problemTypeName;
					}else{
						obj[3]= "";
					}*/
					if(StringUtils.isNotEmpty(homeChannel.getNotUpdateStr())){
						obj[3]= homeChannel.getNotUpdateStr();
					}else{
						obj[3]= "";
					}
					
					obj[4]= CommonUtils.setHttpUrl(homeChannel.getUrl()); //  判断是否有http头     网址
					obj[5]= homeChannel.getScanDate();//扫描时间
					obj[6]=homeChannel.getModifyDate() == null ?"":homeChannel.getModifyDate();//最后更新时间(0000-00-00 00:00:00)
					
					if(StringUtils.isNotEmpty(homeChannel.getModifyDate())){
						obj[7]= homeChannel.getNotUpdateNum();//未更新天数
					}else{
						obj[7]= "";//未更新天数
					}
					if(homeChannel.getFlagType() != null){
						obj[8]= homeChannel.getFlagType()==0?"是":"否";
					}else{
						obj[8]= "未知";
					}
					
					if(homeChannel.getImageUrl() != null && homeChannel.getImageUrl() != ""){
						String imgUrl=homeChannel.getImageUrl(); 
						String[] imgUrlStr=imgUrl.split("\\|");
						if(StringUtils.isNotEmpty(imgUrl)){
							if(imgUrl.startsWith("htm")){
//								obj[9]=urlAdapterVar.getImgUrl() + imgUrl;//快照
								for(int j=0;j<imgUrlStr.length;j++){
									if(imgUrlStr[j]!=""){
										imgUrlStr[j]=urlAdapterVar.getImgUrl()+imgUrlStr[j];
									}
									
								}
								obj[9]=imgUrlStr;
							}else{
								for(int j=0;j<imgUrlStr.length;j++){
									if(StringUtils.isNotEmpty(imgUrlStr[j])){
										imgUrlStr[j]=urlAdapterVar.getLinkUrl()+imgUrlStr[j];
									}
									
								}
								obj[9]=imgUrlStr;
							}
						}else{
							obj[9]=imgUrlStr;//快照
						}
					}else{
						obj[9]="无";//快照
					}
					
					if("2".equals(systemTwo)){
						if(StringUtils.isNotEmpty(homeChannel.getLastAccessDate())){
							obj[10] = homeChannel.getLastAccessDate();
						}else{
							obj[10] = "暂无数据";
						}
					}
					
					list.add(obj);
				}
			}
			ExportExcel.UnUpdateChannelExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
					
	}
	
	/**
	 * @author luocheng  --- 2017-03-27
	 * @param list 
	 * @param flag true表示正常更新的list  false表示逾期的list
	 * @return
	 */
	public List<SecurityHomeChannel> getNotUpdateList(List<SecurityHomeChannel> list,boolean flag){
		if(flag){
			//根据栏目类别判断其应该添加的逾期未更新天数信息   [正常更新]
			for (SecurityHomeChannel shc : list) {
				shc.setNotUpdateStr(ChannelNotUpdateType.NOMAL_UPDATE.getName());
			}
		}else{
			//根据栏目类别判断其应该添加的逾期未更新天数信息    [逾期未更新]
			for (SecurityHomeChannel shc : list) {
				int type = shc.getChannelType();
				if(type == ChannelNotUpdateType.FIVE.getCode()){
					shc.setNotUpdateStr(ChannelNotUpdateType.TWO_WEEK_NOTUPDATE.getName());
				}else if(type == ChannelNotUpdateType.SIX.getCode()|| type == ChannelNotUpdateType.SEVEN.getCode()){
					shc.setNotUpdateStr(ChannelNotUpdateType.SIX_MOUTH_NOTUPDATE.getName());
				}else{
					shc.setNotUpdateStr(ChannelNotUpdateType.ONE_YEAR_NOTUPDATE.getName());
				}
			}
		}
		
		return list;
	}
	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

}
