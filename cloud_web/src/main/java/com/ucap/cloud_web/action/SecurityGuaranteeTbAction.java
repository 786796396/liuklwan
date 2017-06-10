package com.ucap.cloud_web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IServicePeriodService;


/**
 * @描述： 内容保障问题
 * @包：com.ucap.cloud_web.action
 * @文件名称：SecurityGuaranteeTbAction 填报单位-深度检测-内容保障问题
 * @公司名称：开普互联
 * @author luocheng@ucap.com.cn
 * @时间：2017/3/27
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SecurityGuaranteeTbAction extends BaseAction {
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;

	/**
	 * @描述:[跳转] 填报-内容保障主页面
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/3/27
	 * @return
	 */
	public String securityGuaranteeMainTb() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		if (StringUtils.isNotEmpty(siteCode)) {
			setCurrentShiroUser(siteCode);
		}
		
		String modeTb = request.getParameter("mode");//跳转选中高级还是标准版
		request.setAttribute("modeTb", modeTb == null ? "":modeTb);
		
		String typeIdTb = request.getParameter("typeId");//跳转选中的type
		request.setAttribute("typeIdTb", typeIdTb == null ? "" :typeIdTb);
		
		String servicePeriodIdTb = request.getParameter("servicePeriodId"); //跳转周期
		request.setAttribute("servicePeriodIdTb", servicePeriodIdTb == null ? "" :servicePeriodIdTb );
		
		if( servicePeriodIdTb != null && !"".equals(servicePeriodIdTb)){
			ServicePeriod spTb = servicePeriodServiceImpl.get(Integer.valueOf(servicePeriodIdTb));
			String spTbStr = sdf.format(spTb.getStartDate()) + "至" + sdf.format(spTb.getEndDate());
			request.setAttribute("spTbStr", spTbStr);
		}
		
		

		String yesterdayStr = DateUtils.getYesterdayStr();
		request.setAttribute("yesterdayStr", yesterdayStr);
		Map<String, Object> basicPeriodMap = essentialinformationMonitoringPeriod();
		if(basicPeriodMap != null){
			@SuppressWarnings("unchecked")
			List<Object> basicList = (List<Object>) basicPeriodMap.get("scanCycleList");
			String isSenior = (String) basicPeriodMap.get("isSenior");
			if(CollectionUtils.isNotEmpty(basicList) && basicList.size() > 0){
				request.setAttribute("basicPeriodList", basicList);//周期集合
				@SuppressWarnings("unchecked")
				Map<String, Object>  nearMap = (Map<String, Object>) basicList.get(0);
				request.setAttribute("nearbasicPeriodList", nearMap.get("cycleDate")); //最近 周期
				request.setAttribute("nearbasicPeriodListId", nearMap.get("id")); //最近 周期Id
				request.setAttribute("isSenior", isSenior); 
			}else{
				request.setAttribute("nearbasicPeriodList", "暂无周期"); 
				request.setAttribute("isSenior", "2"); 
			}
		}
		
		Map<String, Object> securityPeriodMap = securityDetailMonitoringPeriod();
		if(securityPeriodMap != null){
			@SuppressWarnings("unchecked")
			List<Object> securityList = (List<Object>) securityPeriodMap.get("scanCycleList");
			if(CollectionUtils.isNotEmpty(securityList) && securityList.size() > 0){
				request.setAttribute("securityList", securityList);//周期集合
				@SuppressWarnings("unchecked")
				Map<String, Object>  nearMap = (Map<String, Object>) securityList.get(0);
				request.setAttribute("nearSecurityList", nearMap.get("cycleDate")); //最近 周期
				request.setAttribute("nearSecurityListId", nearMap.get("id")); //最近 周期Id
			}else{
				request.setAttribute("nearSecurityList", "暂无周期"); //最近 周期
			}
		}
		
		
		return "success";
	}
	
	
	/**
	 * @Description: 填报单位查询服务周期[基本信息]
	 * @author Liukl --- 2017年2月10日10:30:37   
	 * @return
	 */
	public Map<String, Object>  essentialinformationMonitoringPeriod(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Object> list = new ArrayList<Object>();
		String siteCode = request.getParameter("siteCode");
		//不为空是组织跳过来的   为空是填报自己登录的
		try {
			/*if(StringUtils.isNotEmpty(siteCode)){
				
			}else{
				siteCode = getCurrentUserInfo().getChildSiteCode();
			}*/
			siteCode=getCurrentUserInfo().getSiteCode();
			
			ServicePeriodRequest reques = new ServicePeriodRequest();
			List<ContractInfo> conList = databaseBizServiceImpl.getContractInfoServurity(siteCode, DateUtils.formatStandardDate(new Date()));
			reques.setComboI(4);//高级版任务
			List<ServicePeriodRequest> periodList = null;
			String isSenior = ""; 
			if(conList != null && conList.size()>0){
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
				if(conList != null && conList.size()>0){
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
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * @Description: 获取监测周期(空白 互动 服务不实用 )
	 * @author Liukl --- 2016年12月13日17:56:45
	 */
	public Map<String, Object> securityDetailMonitoringPeriod() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String siteCode = getCurrentUserInfo().getSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getChildSiteCode();
			}
			String siteCodeTB = getCurrentUserInfo().getChildSiteCode();
			if (siteCode.length() == 6) {
				resultMap = databaseBizServiceImpl.characterCycle(siteCode,
						siteCodeTB);
			} else {
				resultMap = databaseBizServiceImpl.characterCycle(siteCode,
						null);
			}
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
