/**
 * 
 */
package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.dto.MonitorIncludeRequest;
import com.ucap.cloud_web.dtoResponse.SearchEngineResponse;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.MonitorInclude;
import com.ucap.cloud_web.service.IMonitorIncludeService;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;

import net.sf.json.JSONObject;

/**描述： 日常监测-搜索引擎收录
 * 包：com.ucap.cloud_web.action
 * 文件名称：SearchEngineAction
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年3月23日下午5:18:07 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SearchEngineAction extends BaseAction {

	@Autowired
	private IMonitorIncludeService monitorIncludeServiceImpl;
	@Autowired
	private DicUtils dicUtils;

	/**
	 * 
	 * @描述:跳转页面
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月23日下午5:22:13
	 * @return
	 */
	public String index() {
		List<DicItem> dicList = dicUtils.getDictList("siteType");
		request.setAttribute("dicList", dicList); // 网站类别
		request.setAttribute("yesterday", DateUtils.getYesterdayStr()); // 昨天
		return "success";
	}

	/**
	 * 
	 * @描述:搜索引擎收录table
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月22日下午5:48:46
	 */
	public void getSearchEngineList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String monitoringDate = request.getParameter("monitoringDate"); // 监测时间
		// 获取当前组织机构编码
		String siteCode = getCurrentSiteCode();
		try {
			Integer homeUrlNum = 0; // 首页url总数
			Integer baiduSlWebsiteNum = 0; // 收录数_站点总数
			Integer baiduSlDomainsiteNum = 0; // 收录数_域总数

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("scanDate", monitoringDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			List<SearchEngineResponse> seList = monitorIncludeServiceImpl.getSearchEngineList(hashMap);
			if (CollectionUtils.isNotEmpty(seList)) {
				for (SearchEngineResponse se : seList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("siteCode", se.getSiteCode());
					item.put("name", se.getName());
					if (StringUtils.isNotEmpty(se.getJumpUrl())) {
						item.put("url", se.getJumpUrl());// 跳转url地址
					} else {
						item.put("url", se.getUrl());// 首页url地址
					}
					item.put("layerType", se.getLayerType());
					item.put("baiduSlWebsite", se.getBaiduSlWebsite());
					item.put("baiduSlDomainsite", se.getBaiduSlDomainsite());
					items.add(item);

					homeUrlNum++;
					baiduSlWebsiteNum += se.getBaiduSlWebsite(); // 收录数_站点
					baiduSlDomainsiteNum += se.getBaiduSlDomainsite(); // 收录数_域
				}
			}
			resultMap.put("success", "true");
			resultMap.put("homeUrlNum", homeUrlNum);
			resultMap.put("baiduSlWebsiteNum", baiduSlWebsiteNum);
			resultMap.put("baiduSlDomainsiteNum", baiduSlDomainsiteNum);
			resultMap.put("body", items);
			resultMap.put("size", seList.size());
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询搜索引擎收录数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:搜索引擎收录Excel
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月24日下午3:47:19
	 */
	public void getSearchEngineListExcel() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String monitoringDate = request.getParameter("monitoringDate"); // 监测时间
		// 获取当前组织机构编码
		String siteCode = getCurrentSiteCode();
		try {
			Object[] obj1 = new Object[] { "网站标识码", "网站名称", "首页url", "网站类型", "站点收录网页数", "域收录网页数 " };
			list.add(obj1);
			String fileName = "日常监测-搜索引擎收录(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "搜索引擎收录";

			String layerName = null;
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("scanDate", monitoringDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			List<SearchEngineResponse> seList = monitorIncludeServiceImpl.getSearchEngineList(hashMap);
			if (CollectionUtils.isNotEmpty(seList)) {
				for (SearchEngineResponse se : seList) {
					Object[] obj = new Object[6];
					obj[0] = se.getSiteCode();
					obj[1] = se.getName();
					if (StringUtils.isNotEmpty(se.getJumpUrl())) {
						obj[2] = CommonUtils.setHttpUrl(se.getJumpUrl()); // 判断网址前缀
					} else {
						obj[2] = CommonUtils.setHttpUrl(se.getUrl()); // 判断网址前缀
					}

					if (se.getLayerType() != null) {
						if (se.getLayerType() == 1) {
							layerName = DatabaseLinkType.ISORGANIZATIONAL.getName();
						} else if (se.getLayerType() == 2) {
							layerName = DatabaseLinkType.DEPARTMENT.getName();
						} else if (se.getLayerType() == 3) {
							layerName = DatabaseLinkType.UNIT.getName();
						}
					}
					obj[3] = layerName;
					obj[4] = se.getBaiduSlWebsite();
					obj[5] = se.getBaiduSlDomainsite();
					list.add(obj);
				}
			}
			ExportExcel.searchEngineTableExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @描述:趋势图表
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月24日下午3:26:47
	 */
	public void getSearchEngineTrend() {
		String siteCode = request.getParameter("siteCode"); // 网站标识码
		String dateNum = request.getParameter("dateNum"); // 天数
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer num = 0;
			if (StringUtils.isNotEmpty(dateNum)) {
				num = Integer.valueOf(dateNum);
			}
			MonitorIncludeRequest sRequest = new MonitorIncludeRequest();
			sRequest.setBeginScanDate(DateUtils.getNextDay(new Date(), num));
			sRequest.setEndScanDate(DateUtils.getNextDay(new Date(), -1));
			sRequest.setSiteCode(siteCode);
			sRequest.setPageSize(Integer.MAX_VALUE);

			List<QueryOrder> qList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("scan_date", QueryOrderType.ASC);
			qList.add(siteQueryOrder);
			sRequest.setQueryOrderList(qList);

			List<MonitorInclude> list = monitorIncludeServiceImpl.queryList(sRequest);
			resultMap.put("list", list);
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
}
