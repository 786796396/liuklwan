package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.dto.JcStatisticsRequest;
import com.ucap.cloud_web.entity.JcStatistics;
import com.ucap.cloud_web.service.IJcStatisticsService;

/**
 * <p>Description: 配置</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: ConnBusinessDetailAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：linhb </p>
 * <p>@date：2016-5-24 下午3:59:34 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class JcStatisticsAction extends BaseAction {

	@Autowired
	private IJcStatisticsService jcStatisticsServiceImpl;
	
	
	/**
	 * @Description: 获取数据
	 * @author: linhb --- 2016-10-09下午4:09:22
	 * @return
	 * @throws Exception
	 */
	public void getDatas() {
		//封装返回数据
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			JcStatisticsRequest jRequest = new JcStatisticsRequest();
			
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("visit_date",QueryOrderType.ASC);
			querySiteList.add(siteQueryOrder);
			
			jRequest.setQueryOrderList(querySiteList);
			
			
			String yDay = DateUtils.getNextDay(new Date(), -1);
			String sDay = DateUtils.getNextDay(new Date(), -7);
			
			jRequest.setsDay(sDay);
			jRequest.setyDay(yDay);
			List<JcStatistics> list = jcStatisticsServiceImpl.queryList(jRequest);
			resultMap.put("list", list);
			resultMap.put("success","true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	
}
