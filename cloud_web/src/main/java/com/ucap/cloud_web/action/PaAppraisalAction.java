package com.ucap.cloud_web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.dto.PaAppraisalRequest;
import com.ucap.cloud_web.entity.PaAppraisal;
import com.ucap.cloud_web.service.IPaAppraisalService;
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
public class PaAppraisalAction extends BaseAction{
	private static Logger logger = Logger.getLogger(PaAppraisalAction.class);
	@Autowired
	private IPaAppraisalService paAppraisalServiceImpl;
	


	/**
	 * 获取评估对象 名称
	 * linhb - 2016-8-24
	 */
	public void getPaName(){
		try {
			PaAppraisalRequest paAppraisalRequest = new PaAppraisalRequest();
			Map<String,Object> map = new HashMap<String, Object>();
			String projectId = request.getParameter("projectId");
			if(StringUtils.isNotEmpty(projectId)){//组织单位
				paAppraisalRequest.setProjectId(Integer.parseInt(projectId));
			}else{
				return;
			}
			logger.info("paProjectRequest()===paProjectList=");
			paAppraisalRequest.setPageSize(Integer.MAX_VALUE);
			List<PaAppraisal> list= paAppraisalServiceImpl.queryList(paAppraisalRequest);
			map.put("list", list);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
