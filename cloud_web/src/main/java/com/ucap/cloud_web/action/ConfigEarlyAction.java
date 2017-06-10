package com.ucap.cloud_web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.dto.ConfigEarlyRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.entity.ConfigEarly;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.service.IConfigEarlyService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IEarlyDetailTempService;
import com.ucap.cloud_web.shiro.ShiroUser;

import net.sf.json.JSONObject;

/**
 * 描述： 预警配置action
 * 包：com.ucap.cloud_web.action
 * 文件名称：ConfigEarlyAction
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-7-20下午2:58:39 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ConfigEarlyAction extends BaseAction{
	
	@Autowired
	private IConfigEarlyService configEarlyServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	private IEarlyDetailTempService earlyDetailTempServiceImpl;
	private ConfigEarly configEarly;
	
	
	/**
	 * log日志加载
	 */
	private static Log logger =  LogFactory.getLog(ConfigEarlyAction.class);
	
	/**
	 * @Title:跳转到组织单位预警配置页面 
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-1下午2:17:34
	 * @return
	 */
	public String configEarlyOrg(){
		try {
			//获取当前登录siteCode
			String siteCode=getCurrentUserInfo().getSiteCode();
			//通过参数判断预警引导层级
			String nextChangeLast=request.getParameter("nextChangeLast");
			request.setAttribute("siteCode", siteCode);
			if(StringUtils.isNotEmpty(nextChangeLast)){
				request.setAttribute("nextChangeLast", nextChangeLast);
			}
			
			
			
			//查询组织单位 预警配置信息初始化页面
			ConfigEarlyRequest configEarlyRequest =new ConfigEarlyRequest();
			configEarlyRequest.setSiteCode(siteCode);
			List<ConfigEarly> configEarlyList=configEarlyServiceImpl.queryList(configEarlyRequest);
			if(configEarlyList!=null && configEarlyList.size()>0){
				for(int i=0;i<configEarlyList.size();i++){
					ConfigEarly configEarlyBean=configEarlyList.get(i);
					if(configEarlyBean.getEarlyType()==1){
						request.setAttribute("tabDataOne", configEarlyBean);
					}else if(configEarlyBean.getEarlyType()==2){
						request.setAttribute("tabDataTwo", configEarlyBean);
					}
				}
				//查询组织单位 联系信息  电话  邮箱
				DatabaseOrgInfoRequest databaseOrgInfoRequest=new DatabaseOrgInfoRequest();
				databaseOrgInfoRequest.setStieCode(siteCode);
				List<DatabaseOrgInfo> databaseOrgInfoList=databaseOrgInfoServiceImpl.queryList(databaseOrgInfoRequest);
				if(databaseOrgInfoList.size()>0){
					DatabaseOrgInfo databaseOrgInfo=databaseOrgInfoList.get(0);
					request.setAttribute("databaseOrgInfo", databaseOrgInfo);
				}
				request.setAttribute("errmsg", 1);//用于前台判断预警设置是否已经初始化数据 1-初始化
			}else{
				request.setAttribute("errmsg", 0);//用于前台判断预警设置是否已经初始化数据 0-未初始化
			}
			//获取当前登录人是否收费
//			int iscost=getCurrentUserInfo().getIsOrgCost();
//			request.setAttribute("iscost", iscost);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "success";
	}
	/**
	 * @Title: 跳转到填报单位页面
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-1下午2:21:34
	 * @return
	 */
	public String configEarlyTB(){
		
		logger.info("==========configEarlyTB===========");
		try {
			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			
			//预警引导判断
			String nextChangeLast = request.getParameter("nextChangeLast");
			if(StringUtils.isNotEmpty(nextChangeLast)){
				request.setAttribute("nextChangeLast",nextChangeLast );
			}
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getChildSiteCode();
			}
			
			logger.info("==========siteCode==========="+siteCode);
			request.setAttribute("siteCode", siteCode);
/*			ConfigEarlyRequest configEarlyRequest =new ConfigEarlyRequest();
			configEarlyRequest.setSiteCode(siteCode);
			List<ConfigEarly> configEarlyList=configEarlyServiceImpl.queryList(configEarlyRequest);*/
			
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("siteCode", siteCode);
			List<ConfigEarly> configEarlyList=configEarlyServiceImpl.findByMap(paramMap);
			if(configEarlyList!=null && configEarlyList.size()>0){
				ConfigEarly cEarly = configEarlyList.get(0);
				request.setAttribute("cEarly",cEarly);
				request.setAttribute("errmsg",1);//已经初始化
			}else{
				request.setAttribute("errmsg",0);//未初始化
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}
	/**
	 * @Title: 组织单位修改预警配置信息
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-1下午2:21:54
	 */
	public void updateInfo(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(configEarly.getEarlyType() == 2){//单站
				try {
					logger.info("========earlyDetailTempServiceImpl.updateEarlyDetailTemp========begin");
					ConfigEarlyRequest request = new ConfigEarlyRequest();
					request.setEarlyType(2);
					request.setSiteCodeLength(configEarly.getSiteCode().length());
					request.setSiteCode(configEarly.getSiteCode());
					List<ConfigEarly> config = configEarlyServiceImpl.queryOrgSingleSiteCode(request);
					if(config.size() == 1){
						earlyDetailTempServiceImpl.updateEarlyDetailTemp(config.get(0), configEarly);
					}else{
						logger.info("========earlyDetailTempServiceImpl.updateEarlyDetailTemp========获取原配置不是一条");
					}
					logger.info("========earlyDetailTempServiceImpl.updateEarlyDetailTemp========end");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			logger.info("========updateInfo========begin");
			configEarlyServiceImpl.update(configEarly);
			logger.info("========updateInfo========end");
			resultMap.put("errorMsg", "更新成功！");
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("errorMsg", "更新失败！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
		
	}
	/**
	 * @Title:填报单位修改预警配置信息 
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-1下午2:22:09
	 */
	public void updateInfoTb(){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			try {
				logger.info("========earlyDetailTempServiceImpl.updateEarlyDetailTemp========begin");
				ConfigEarlyRequest request = new ConfigEarlyRequest();
				request.setEarlyType(2);
				request.setSiteCodeLength(configEarly.getSiteCode().length());
				request.setSiteCode(configEarly.getSiteCode());
				List<ConfigEarly> config = configEarlyServiceImpl.queryOrgSingleSiteCode(request);
				if(config.size() == 1){
					earlyDetailTempServiceImpl.updateEarlyDetailTemp(config.get(0), configEarly);
				}else{
					logger.info("========earlyDetailTempServiceImpl.updateEarlyDetailTemp========获取原配置不是一条");
				}
				logger.info("========earlyDetailTempServiceImpl.updateEarlyDetailTemp========end");
			} catch (Exception e) {
				e.printStackTrace();
			}
			configEarlyServiceImpl.update(configEarly);
			resultMap.put("errorMsg", "更新成功！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("errorMsg", "更新失败！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
		
	}
	
	public ConfigEarly getConfigEarly() {
		return configEarly;
	}
	public void setConfigEarly(ConfigEarly configEarly) {
		this.configEarly = configEarly;
	}
	
	
}
