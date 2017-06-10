package com.ucap.cloud_web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.SpotCheckInfoRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.SpotCheckInfo;
import com.ucap.cloud_web.entity.YunSoInfo;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.ISpotCheckInfoService;
import com.ucap.cloud_web.service.IYunSoInfoService;


/**
 * @描述：服务中心-网站服务群监管
 * @包：com.ucap.cloud_web.action
 * @文件名称：ServiceCenterRegulationAction 填报单位-网站服务群监管
 * @公司名称：开普互联
 * @author luocheng@ucap.com.cn
 * @时间：2017/4/6
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ServiceCenterRegulationAction extends BaseAction {


	@Autowired
	private IYunSoInfoService yunSoInfoServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	private ISpotCheckInfoService spotCheckInfoServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;

	/**
	 * @描述:[跳转] 服务中心 网站群监管 主页面
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/4/6
	 * @return
	 */
	public String index() {
		boolean isAdvancedContract = hasAdvancedContract();
		if(isAdvancedContract){
			request.setAttribute("isAdvancedContract", 1);
		}else{
			request.setAttribute("isAdvancedContract", 2);
		}
		
		return "success";
	}
	
	/**
	 * @描述:当前单位是否在当前合同周期内
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/4/10
	 * @return
	 */
	public boolean hasAdvancedContract() {
		String siteCode = getCurrentSiteCode(); // 获得当前登录的组织单位siteCode
		try {
			// 获得当前组织单位的合同 判断是否存在合同
			List<ContractInfo> contractList = getContractInfoList(siteCode,
					DateUtils.formatStandardDate(new Date()));
			if (CollectionUtils.isNotEmpty(contractList)) {
				// 有合同 需抽查合同表里查询
				Integer contractInfoId = contractList.get(contractList.size()-1).getId(); // 当前合同的id
				SpotCheckInfoRequest scRequest = new SpotCheckInfoRequest();
				scRequest.setSiteCode(siteCode);
				scRequest.setContractInfoId(contractInfoId);
				List<SpotCheckInfo>  spotList = spotCheckInfoServiceImpl.queryList(scRequest);
				if(CollectionUtils.isNotEmpty(spotList) && spotList.size() > 0){
					int spotSum = spotList.get(0).getSpotSum();
					//抽查总次数大于0  等于已开通
					if(spotSum > 0){
						return true;
					}else{
						return false;
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
	 * @描述： 服务中心  网站群监管 提交申请
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/4/7
	 * @return
	 */
	public void submitService(){
		String siteCode = getCurrentUserInfo().getSiteCode(); //当前登录用户siteCode
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String siteName = request.getParameter("siteName");
		String relationPerson = request.getParameter("relationPerson");
		String relationPhone = request.getParameter("relationPhone");
		String email = request.getParameter("email");
		String services = request.getParameter("services");
		String serverProStr = services.substring(0, services.length()-1);
		Integer infoId = 0;
		if(siteCode != null && siteCode.length() == 6){
			//组织单位申请开通
			DatabaseOrgInfoRequest orgRequest = new DatabaseOrgInfoRequest();
			orgRequest.setStieCode(siteCode);
			List<DatabaseOrgInfo> orgList = databaseOrgInfoServiceImpl.queryList(orgRequest);
			if(CollectionUtils.isNotEmpty(orgList) && orgList.size() > 0){
				infoId = orgList.get(0).getId();
			}
		}else if(siteCode != null && siteCode.length() > 6){
			//填报单位申请开通
			DatabaseInfoRequest dataInfoRequest = new DatabaseInfoRequest();
			dataInfoRequest.setSiteCode(siteCode);
			List<DatabaseInfo> dataList = databaseInfoServiceImpl.queryList(dataInfoRequest);
			if(CollectionUtils.isNotEmpty(dataList) && dataList.size() > 0){
				infoId = dataList.get(0).getId();
			}
		}
		try {
			YunSoInfo yunSoInfo = new YunSoInfo();
			yunSoInfo.setDatabaseInfoId(infoId); //id
			yunSoInfo.setDirector(siteName);
			yunSoInfo.setSiteCode(siteCode);
			yunSoInfo.setLinkmanName(relationPerson); 
			yunSoInfo.setTelephone(relationPhone);
			yunSoInfo.setEmail(email);
			yunSoInfo.setStatus(0); //服务状态 未启动
			yunSoInfo.setServerPro(serverProStr); //服务项
			yunSoInfo.setSourceType(2); //来源类型： 2 服务中心
			yunSoInfo.setType(0); //开通状态  0 未开通
			
			yunSoInfoServiceImpl.add(yunSoInfo);
			resultMap.put("success", "恭喜您，申请成功！！！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("error", "申请失败了，请您联系管理员!");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

}
