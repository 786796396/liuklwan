package com.ucap.cloud_web.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.dto.PaAdviceDetailRequest;
import com.ucap.cloud_web.dto.PaAdviceRequest;
import com.ucap.cloud_web.entity.PaAdvice;
import com.ucap.cloud_web.entity.PaAdviceDetail;
import com.ucap.cloud_web.service.IPaAdviceDetailService;
import com.ucap.cloud_web.service.IPaAdviceService;
/**
 * <p>Description: 留言回复</p>
 * <p>Title: PaTaskAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：linhb </p>
 * <p>@date：2016年8月23日上午11:01:07 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PaAdviceAction extends BaseAction{
	private static Logger logger = Logger.getLogger(PaAdviceAction.class);
	@Autowired
	private IPaAdviceService paAdviceServiceImpl;
	@Autowired
	private IPaAdviceDetailService paAdviceDetailServiceImpl;
	
	/**
	 * 跳转到自评页面
	 * linhb - 2016-8-23
	 */
	public String paAdvice(){
		return "success";
	}
	/**
	 * 跳转到自评页面
	 * linhb - 2016-8-23
	 */
	public String paAdviceOrg(){
		return "success";
	}
	/**
	 * 添加留言
	 * linhb - 2016-8-26
	 */
	public void addAdvice(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			
			String adTitle = request.getParameter("adTitle");
			String adContext = request.getParameter("adContext");
			String adLinkPersion = request.getParameter("adLinkPersion");
			String adPhone = request.getParameter("adPhone");
			String siteCode = request.getParameter("siteCode");
			logger.info("addAdvice()===addAdvice====添加留言==");
			PaAdvice paAdvice = new PaAdvice();
			paAdvice.setTitle(adTitle);
			paAdvice.setSenderName(adLinkPersion);
			paAdvice.setContent(adContext);
			paAdvice.setNoReadNum(0);
			paAdvice.setBackNoReadNum(0);
			paAdvice.setSiteCode(siteCode);
			paAdvice.setSenderPhone(adPhone);
			paAdviceServiceImpl.add(paAdvice);
			map.put("success", "true");
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	/**
	 * 获取留言
	 * linhb - 2016-8-26
	 */
	public void getAdviceList(){
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			
			String siteCode = request.getParameter("siteCode");
			logger.info("getAdviceList()===getAdviceList====获取留言list==");
			PaAdviceRequest aRequest = new PaAdviceRequest();
			aRequest.setSiteCode(siteCode);
			aRequest.setPageSize(Integer.MAX_VALUE);
			List<QueryOrder> queryList=new ArrayList<QueryOrder>();
			QueryOrder queryOrder=new QueryOrder("create_time",QueryOrderType.DESC);
			queryList.add(queryOrder);
			aRequest.setQueryOrderList(queryList);
			aRequest.setPageSize(Integer.MAX_VALUE);
			List<PaAdvice> list = paAdviceServiceImpl.queryList(aRequest);
			map.put("list",list);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取留言 回复 详情
	 * linhb - 2016-8-26
	 */
	public void getAdviceDetailList(){
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			
			String adviceId = request.getParameter("adviceId");
			logger.info("getAdviceDetailList()===getAdviceDetailList====获取留言 回复 详情list==");
			PaAdviceDetailRequest aRequest = new PaAdviceDetailRequest();
			if(StringUtils.isNotEmpty(adviceId)){
				aRequest.setAdviceId(Integer.parseInt(adviceId));
			}else{
				return;
			}
			
			
			PaAdviceRequest aaRequest = new PaAdviceRequest();
			aaRequest.setId(Integer.parseInt(adviceId));
			aaRequest.setNoReadNum(0);
			paAdviceServiceImpl.updateById(aaRequest);
			List<QueryOrder> queryList=new ArrayList<QueryOrder>();
			QueryOrder queryOrder=new QueryOrder("create_time",QueryOrderType.ASC);
			queryList.add(queryOrder);
			aRequest.setQueryOrderList(queryList);
			aRequest.setPageSize(Integer.MAX_VALUE);
			List<PaAdviceDetail> list = paAdviceDetailServiceImpl.queryList(aRequest);
			map.put("list",list);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加留言 回复
	 * linhb - 2016-8-26
	 */
	public void addRelays(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			
			String adviceId = request.getParameter("adviceId");
			String context = request.getParameter("context");
			logger.info("addRelays()===addRelays====添加留言 回复==");
			PaAdviceDetail paAdviceDetail = new PaAdviceDetail();
			PaAdvice paAdvice = new PaAdvice();
			if(StringUtils.isNotEmpty(adviceId)&&StringUtils.isNotEmpty(context)){
				paAdvice = paAdviceServiceImpl.get(Integer.parseInt(adviceId));
			}else{
				return;
			}
			paAdviceDetail.setAdviceId(Integer.parseInt(adviceId));
			paAdviceDetail.setAdviceState((short)1);
			paAdviceDetail.setName(paAdvice.getSenderName());
			paAdviceDetail.setContent(context);

			paAdviceDetailServiceImpl.add(paAdviceDetail);
			
			PaAdviceRequest aaRequest = new PaAdviceRequest();
			aaRequest.setId(Integer.parseInt(adviceId));
			aaRequest.setBackNoReadNum(paAdvice.getBackNoReadNum()+1);
			paAdviceServiceImpl.updateById(aaRequest);
			map.put("success", "true");
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	

}
