package com.ucap.cloud_web.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IServicePeriodService;



/**
 * @描述：服务中心-网站监测服务
 * @包：com.ucap.cloud_web.action
 * @文件名称：ServiceCenterMonitorAction 服务中心-网站监测服务
 * @公司名称：开普互联
 * @author luocheng@ucap.com.cn
 * @时间：2017/4/10
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ServiceCenterMonitorAction extends BaseAction {

	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	/**
	 * @描述:[跳转] 网站检测服务 主页面
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/4/10
	 * @return
	 */
	public String index() {
		boolean isAdvancedContract = false; //全面检测  全站死链是否开通
		boolean isCorrect = false; //错别字是否开通
		boolean isDepth = false; //深度检测是否开通
		
		// 组织单位登录
		isAdvancedContract = hasOrgAdvancedContract(false, false);
		isCorrect = hasOrgAdvancedContract(true, false);
		isDepth = hasOrgAdvancedContract(false, true);
		
		if(isAdvancedContract){
			request.setAttribute("isAdvancedContract", 1);
		}else{
			request.setAttribute("isAdvancedContract", 2);
		}
		
		if(isCorrect){
			request.setAttribute("isCorrect", 1);
		}else{
			request.setAttribute("isCorrect", 2);
		}
		
		if(isDepth){
			request.setAttribute("isDepth", 1);
		}else{
			request.setAttribute("isDepth", 2);
		}
		
		return "success";
	}
	
	
	public String indexTb() {
		boolean isAdvancedContract = false; //全面检测  全站死链是否开通
		boolean isCorrect = false; //错别字是否开通
		boolean isDepth = false; //深度检测是否开通
		
		// 填报单位登录
		isAdvancedContract = hasTbAdvancedContract(false, false);
		isCorrect = hasTbAdvancedContract(true, false);
		isDepth = hasTbAdvancedContract(false, true);
		
		if(isAdvancedContract){
			request.setAttribute("isAdvancedContract", 1);
		}else{
			request.setAttribute("isAdvancedContract", 2);
		}
		
		if(isCorrect){
			request.setAttribute("isCorrect", 1);
		}else{
			request.setAttribute("isCorrect", 2);
		}
		
		if(isDepth){
			request.setAttribute("isDepth", 1);
		}else{
			request.setAttribute("isDepth", 2);
		}
		
		return "success";
	}
	
	
	
	/**
	 * @描述: 网站检测服务 组织单位查询服务开通状态 (都为false则验证是否有高级版任务)
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/4/10
	 * @param isCorrect 验证错别字  true验证   false不验证
	 * @param isDepth 验证栏目深度检测  true验证 false不验证
	 * @return
	 */
	public boolean hasOrgAdvancedContract(boolean isCorrect,boolean isDepth) {
		String siteCode = getCurrentUserInfo().getSiteCode();  // 获得当前登录的组织单位siteCode
		try {
			// 获得当前组织单位的合同 判断是否存在合同
			List<ContractInfo> contractList = getContractInfoList(siteCode,
					DateUtils.formatStandardDate(new Date()));
			if (CollectionUtils.isNotEmpty(contractList)) {
				
				if(isDepth){
					return true;
				}
				
				if(isCorrect){ //验证错别字
					if(1 == contractList.get(contractList.size()-1).getIsCorrect()){ //已开通错别字
						return true;
					}else{
						return false;
					}
				}
				
				ServicePeriodRequest spRequest = new ServicePeriodRequest();
				spRequest.setSiteCode(siteCode);
				// 有合同 需要区分是否有高级版任务
				Integer currentInfoId = contractList.get(contractList.size()-1).getId(); // 当前合同的id
				String serviceBeginDate = DateUtils
						.formatStandardDate(contractList.get(contractList.size()-1)
								.getContractBeginTime()); // 当前合同的开始时间

				spRequest.setComboI(4); // 查询高级服务周期
				spRequest.setContractInfoId(currentInfoId);
				spRequest.setStartDateTime(serviceBeginDate);

				List<ServicePeriodRequest> servicePeriodBasicList = databaseBizServiceImpl
						.queryByRelationPeriodBasic(spRequest);
				if(CollectionUtils.isNotEmpty(servicePeriodBasicList) && servicePeriodBasicList.size() > 0){
					//当前合同有高级版任务  再判断当前时间是否在合同时间内  在时间内则是开通状态
					Date nowDate = new Date();
					ContractInfo contractInfo = contractList.get(contractList.size()-1);
					Date startTime = contractInfo.getContractBeginTime();
					Date endTime  =contractInfo.getContractEndTime();
					if(startTime != null && endTime != null){
						//当前时间在合同生效和结束时间内
						if(nowDate.getTime() >= startTime.getTime() && nowDate.getTime() <= endTime.getTime()){
							return true;
						}else{
							return false;
						}
					}
					
				}else{
					return false;
				}
			} else {
				// 不存在合同
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * @描述: 网站检测服务 填报单位查询服务开通状态  (都为false则验证是否有高级版任务)
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/4/10
	 * @param isCorrect 验证错别字  true验证   false不验证
	 * @param isDepth 验证栏目深度检测 true验证 false不验证
	 * @return
	 */
	public boolean hasTbAdvancedContract(boolean isCorrect,boolean isDepth) {
		String siteCode =getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			 siteCode = getCurrentUserInfo().getSiteCode();  //当前登录siteCode
		}
		try {
			// 获得当前组织单位的合同 判断是否存在合同
			List<ContractInfo> contractList = getContractInfoList(siteCode,
					DateUtils.formatStandardDate(new Date()));
			if (CollectionUtils.isNotEmpty(contractList)) { //当前填报单位有合同
				
				if(isDepth){ 
					return true;
				}
				
				if(isCorrect){ //验证错别字
					if(1 == contractList.get(contractList.size()-1).getIsCorrect()){ //已开通错别字
						return true;
					}else{
						return false;
					}
				}
				
				//查询是否有高级版任务周期
				
				ServicePeriodRequest spRequest = new ServicePeriodRequest();
				spRequest.setSiteCode(siteCode);
				// 有合同 需要区分是否有高级版任务
				Integer currentInfoId = contractList.get(contractList.size()-1).getId(); // 当前合同的id
				String serviceBeginDate = DateUtils
						.formatStandardDate(contractList.get(contractList.size()-1)
								.getContractBeginTime()); // 当前合同的开始时间

				spRequest.setComboI(4); // 查询高级服务周期
				spRequest.setContractInfoId(currentInfoId);
				spRequest.setStartDateTime(serviceBeginDate);//高级版周期时间在当前合同开始时间

				List<ServicePeriodRequest> servicePeriodBasicList = databaseBizServiceImpl
						.queryByRelationPeriodBasic(spRequest);
				if(CollectionUtils.isNotEmpty(servicePeriodBasicList) && servicePeriodBasicList.size() > 0){
					//当前合同有高级版任务  再判断当前时间是否在合同时间内  在时间内则是开通状态
					Date nowDate = new Date();
					ContractInfo contractInfo = contractList.get(contractList.size()-1);
					Date startTime = contractInfo.getContractBeginTime();
					Date endTime  =contractInfo.getContractEndTime();
					if(startTime != null && endTime != null){
						//当前时间在合同生效和结束时间内
						if(nowDate.getTime() >= startTime.getTime() && nowDate.getTime() <= endTime.getTime()){
							return true;
						}else{
							return false;
						}
					}
					
				}else{
					return false;
				}
			} else {
				// 当前填报单位自己没有合同查询其组织是否有合同  是否允许查看
				// 查询挂接关系
				DatabaseTreeInfoRequest treeRequese = new DatabaseTreeInfoRequest();
				ServicePeriodRequest periodRequest = new ServicePeriodRequest();
				treeRequese.setSiteCode(siteCode);
				treeRequese.setIsLink(1);
				treeRequese.setPageSize(Integer.MAX_VALUE);
				List<DatabaseTreeInfo> treeList = databaseTreeInfoServiceImpl
						.queryList(treeRequese);
				if (treeList.size() > 0) {
					for (DatabaseTreeInfo databaseTree : treeList) {
						// 查询合同
						List<ContractInfo> contractInfoList = getContractInfoList(
								databaseTree.getOrgSiteCode(),
								DateUtils.formatStandardDate(new Date()));
						if (contractInfoList.size() > 0) { // 组织单位有合同
							ContractInfo contractInfo = contractInfoList
									.get(contractInfoList.size() - 1);
							if (contractInfo.getIsSearchTb() == 1) { // [组织单位合同允许下级查看]
								
								if(isDepth){
									return true;
								}
								
								if(isCorrect){ //验证错别字
									if(1 == contractInfo.getIsCorrect()){ //已开通错别字
										return true;
									}else{
										return false;
									}
								}

								periodRequest.setSiteCode(contractInfo
										.getSiteCode());
								Integer currentInfoId = contractInfo.getId(); // 当前合同的id
								String serviceBeginDate = DateUtils
										.formatStandardDate(contractInfo
												.getContractBeginTime()); // 当前合同的开始时间

								periodRequest.setComboI(4); // 查询高级服务周期
								periodRequest.setContractInfoId(currentInfoId);
								periodRequest
										.setStartDateTime(serviceBeginDate);// 高级版周期时间在当前合同开始时间
								List<ServicePeriodRequest> servicePeriodBasicList = databaseBizServiceImpl
										.queryByRelationPeriodBasic(periodRequest);

								if (CollectionUtils
										.isNotEmpty(servicePeriodBasicList)
										&& servicePeriodBasicList.size() > 0) {
									// 当前合同有高级版任务 再判断当前时间是否在合同时间内 在时间内则是开通状态
									Date nowDate = new Date();
									Date startTime = contractInfo
											.getContractBeginTime();
									Date endTime = contractInfo
											.getContractEndTime();
									if (startTime != null && endTime != null) {
										// 当前时间在合同生效和结束时间内
										if (nowDate.getTime() >= startTime
												.getTime()
												&& nowDate.getTime() <= endTime
														.getTime()) {
											return true;
										} else {
											return false;
										}
									}

								} else {
									return false;
								}
							}

						}
					}
				}

				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}



}
