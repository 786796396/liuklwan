package com.ucap.cloud_web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.dto.DetectionOrgCountRequest;
import com.ucap.cloud_web.dto.DicConfigRequest;
import com.ucap.cloud_web.entity.DetectionOrgCount;
import com.ucap.cloud_web.service.IDetectionOrgCountService;
import com.ucap.cloud_web.service.IDicConfigService;

/**
 * <p>Description: 配置</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: ConnBusinessDetailAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：yangshuai </p>
 * <p>@date：2016-5-24 下午3:59:34 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DicConfigAction extends BaseAction {

	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	
	@Autowired
	private IDetectionOrgCountService detectionOrgCountServiceImpl;
	
	/**
	 * @Description: 获取监测数据
	 * @author: yangshuai --- 2016-5-24下午4:09:22
	 * @return
	 * @throws Exception
	 */
	public void getDicConfig() {
		//封装返回数据
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String configId = request.getParameter("configId");//省级组织单位编码
		try {
			Map<String, Object>  params = new HashMap<String, Object>();
			
			if(StringUtils.isNotEmpty(configId)){
				String[] codeArray = configId.split(",");
				params.put("configIds", codeArray);
				
				List<DicConfigRequest> dicConfig = dicConfigServiceImpl.queryListByMap(params);
				resultMap.put("dicConfig",dicConfig);
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}else{
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 获取领先全国网站昨天的百分比
	 * @author: yangshuai --- 2016-7-6下午4:09:22
	 * @return
	 */
	public void getDetection() {
		//封装返回数据
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String siteCode = request.getParameter("siteCode");//省级组织单位编码
		try {
			DetectionOrgCountRequest detectionOrgCountRequest = new DetectionOrgCountRequest();
			detectionOrgCountRequest.setSiteCode(siteCode);
			detectionOrgCountRequest.setScanDate(DateUtils.getYesterdayStr());
			detectionOrgCountRequest.setType("0");
			List<DetectionOrgCount> detectionOrgCountList = detectionOrgCountServiceImpl.queryList(detectionOrgCountRequest);
			if(detectionOrgCountList != null && detectionOrgCountList.size()>0){
				DetectionOrgCount detectionOrgCount = detectionOrgCountList.get(0);
				resultMap.put("detectionOrgCount",detectionOrgCount);
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

}
