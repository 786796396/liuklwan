package com.ucap.cloud_web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.dto.PaTargetRequest;
import com.ucap.cloud_web.entity.PaTarget;
import com.ucap.cloud_web.service.IPaTargetService;
/**
 * <p>Description: 绩效任务</p>
 * <p>Title: PaTaskAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：linhb </p>
 * <p>@date：2016年8月23日上午11:01:07 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PaTargetAction extends BaseAction{
	@Autowired
	private IPaTargetService paTargetServiceImpl;


	/**
	 * 获取评估对象 
	 * linhb - 2016-8-24
	 */
	public void getPaTargetList(){
		try {
			PaTargetRequest paTargetRequest = new PaTargetRequest();
			Map<String,Object> map = new HashMap<String, Object>();
			String appraisalId = request.getParameter("appraisalId");
			String taskId = request.getParameter("taskId");
			String appraisalState = request.getParameter("appraisalState");
			String siteOrName =  request.getParameter("siteOrName");
			if(StringUtils.isNotEmpty(appraisalId) && StringUtils.isNotEmpty(taskId)){//组织单位
				paTargetRequest.setAppraisalId(Integer.parseInt(appraisalId));
				paTargetRequest.setTaskId(Integer.parseInt(taskId));
			}else{
				return;
			}
			
			if(StringUtils.isNotEmpty(appraisalState) && !"null".equals(appraisalState) && !"undefined".equals(appraisalState) ){
				paTargetRequest.setAppraisalState(Integer.parseInt(appraisalState));
			}
			if(StringUtils.isNotEmpty(siteOrName) && !"null".equals(siteOrName) && !"undefined".equals(siteOrName)){
				paTargetRequest.setSiteOrName(siteOrName);
			}
			List<PaTarget> list= paTargetServiceImpl.queryByIds(paTargetRequest);
			map.put("list", list);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
