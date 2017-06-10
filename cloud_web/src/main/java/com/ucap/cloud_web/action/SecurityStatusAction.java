package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.ucap.cloud_web.dto.SecurityStatusRequest;
import com.ucap.cloud_web.entity.SecurityStatus;
import com.ucap.cloud_web.service.ISecurityStatusService;
import com.ucap.cloud_web.shiro.ShiroUser;

/**
 * 描述： 扫描管理
 * 包：com.ucap.cloud_web.action
 * 文件名称：SecurityStatusAction
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-9-2下午4:15:55 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SecurityStatusAction extends BaseAction{
	@Autowired
	private ISecurityStatusService securityStatusServiceImpl;
	
	private Integer type;
	private Integer statusType;
	private Integer queryId;
	/**
	 * @Title: 跳转到安全问题列表页面
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-6下午2:33:03
	 * @return
	 */
	public String toSecurityStatus(){
		try {
			queryStatus();
			request.setAttribute("basePath", getBasePath());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * @Title: 查询扫描状态
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-6下午2:33:30
	 */
	public void queryStatus(){
		Map<String,Object> map_list=new HashMap<String, Object>();
		try {
			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getChildSiteCode();
			}
			request.setAttribute("siteCode", siteCode);
			
			SecurityStatusRequest securityStatusRequest =new SecurityStatusRequest();
			securityStatusRequest.setSiteCode(siteCode);
			securityStatusRequest.setType(type);//安全扫描
			securityStatusRequest.setDelFlag(0);
			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("create_time", QueryOrderType.DESC);
			queryOrderList.add(siteQueryOrder);
			securityStatusRequest.setQueryOrderList(queryOrderList);
			List<SecurityStatus> statusList=securityStatusServiceImpl.queryList(securityStatusRequest);
			Integer status=2;
//			Integer id;
			if(statusList.size()!=0){
				status=statusList.get(0).getStatus();
//				SecurityStatus securityStatus=new SecurityStatus();
//				securityStatus.setSiteCode(siteCode);
//				securityStatus.setType(type);
//				securityStatus.setStatus(1);
//				securityStatus.setIsReaded(1);
//				securityStatus.setDelFlag(0);
//				id=securityStatusServiceImpl.add(securityStatus);
			}
			request.setAttribute("status", status);
			map_list.put("status", status);
//			map_list.put("id", id);
			writerPrint(JSONObject.fromObject(map_list).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	/**
	 * @Title: 修改扫描状态
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-6下午2:33:46
	 */
	public void updateStatus(){
		try {
			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getChildSiteCode();
			}
//			SecurityStatus securityStatus=new SecurityStatus();
//			
//			securityStatus.setId(queryId);
//			securityStatus.setStatus(2);
//			securityStatusServiceImpl.update(securityStatus);
			
			
			
			SecurityStatus securityStatus=new SecurityStatus();
			securityStatus.setSiteCode(siteCode);
			securityStatus.setType(type);
			securityStatus.setStatus(2);
			securityStatus.setIsReaded(1);
			securityStatus.setDelFlag(0);
			securityStatusServiceImpl.add(securityStatus);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatusType() {
		return statusType;
	}
	public void setStatusType(Integer statusType) {
		this.statusType = statusType;
	}
	public Integer getQueryId() {
		return queryId;
	}
	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}
	
	
	
	
}
