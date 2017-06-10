/**
 * 
 */
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
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.dto.CfgOtherProductsRequest;
import com.ucap.cloud_web.entity.CfgOtherProducts;
import com.ucap.cloud_web.service.ICfgOtherProductsService;

/**描述： 一键监测
 * 包：com.ucap.cloud_web.action
 * 文件名称：CfgOtherProductsAction
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年1月3日下午1:46:47 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CfgOtherProductsAction extends BaseAction {
	@Autowired
	private ICfgOtherProductsService cfgOtherProductsServiceImpl;
	@Autowired
	private DicUtils dicUtils;

	/**
	 * 
	 * @描述:一键监测动态查询
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年1月3日下午2:11:03
	 */
	public void queryCfgOtherProducts() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		CfgOtherProductsRequest req = new CfgOtherProductsRequest();
		List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
		try {
			String val = dicUtils.getValue("backweb_http_path");
			QueryOrder siteQueryOrder = new QueryOrder("sort", QueryOrderType.ASC);
			querySiteList.add(siteQueryOrder);
			req.setInUse(1);
			req.setPageSize(Integer.MAX_VALUE);
			req.setQueryOrderList(querySiteList);
			List<CfgOtherProducts> cfgList = cfgOtherProductsServiceImpl.queryList(req);
			if (cfgList != null && cfgList.size() > 0) {
				for (CfgOtherProducts cfg : cfgList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("icon", val + cfg.getIcon()); // 图标
					item.put("title", cfg.getTitle()); // 名称
					item.put("description", cfg.getDescription()); // 描述
					item.put("linkUrl", cfg.getLinkUrl()); // 路径
					item.put("specialShow", cfg.getSpecialShow()); // 是否特殊显示：1.是 2.否(特殊显示只的是在弹框外显示）
					item.put("isData", cfg.getIsData()); // 是否传参数 1：是 2：否
					item.put("type", cfg.getType()); //云产品类型(1:一键检测，2云搜索，3云分析，4云网盘)
					items.add(item);
				}
			}
			resultMap.put("body", items);
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询一键监测数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	
	public void queryUrl(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> cfgArrayList = new ArrayList<Map<String, Object>>();
		try {
			String type = request.getParameter("type");
			CfgOtherProductsRequest req = new CfgOtherProductsRequest();
			req.setInUse(1);
			if(type.equals("2")){
				req.setType(2);
			}else if(type.equals("3")){
				req.setType(3);
			}
			req.setPageSize(Integer.MAX_VALUE);
			List<CfgOtherProducts> cfgList = cfgOtherProductsServiceImpl.queryList(req);
			for (CfgOtherProducts cfg : cfgList) {
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("title", cfg.getTitle()); // 名称
				map.put("linkUrl", cfg.getLinkUrl()); // 路径
				map.put("type", cfg.getType()); //云产品类型(1:一键检测，2云搜索，3云分析，4云网盘)
				cfgArrayList.add(map);
			}
			resultMap.put("body",cfgArrayList);
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	public String yunSearch(){
		
		return "success";
	}
	public String yunAnalytics(){
		
		return "success";
	}
	
	
}



