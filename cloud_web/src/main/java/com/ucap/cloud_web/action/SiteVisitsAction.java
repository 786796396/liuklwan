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
import com.ucap.cloud_web.dto.JcVisitSitecodeRequest;
import com.ucap.cloud_web.dtoResponse.JcVisitSitecodeResponse;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.JcVisitSitecode;
import com.ucap.cloud_web.service.IJcVisitSitecodeService;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;

import net.sf.json.JSONObject;

/**描述： 日常监测-网站访问量
 * 包：com.ucap.cloud_web.action
 * 文件名称：SiteVisitsAction
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年3月22日下午3:27:56 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SiteVisitsAction extends BaseAction {

	@Autowired
	private IJcVisitSitecodeService jcVisitSitecodeServiceImpl;
	@Autowired
	private DicUtils dicUtils;

	/**
	 * 
	 * @描述:跳转页面
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月22日下午3:32:24
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
	 * @描述:网站访问量table
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月22日下午5:48:46
	 */
	public void getSiteVisitsList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String monitoringDate = request.getParameter("monitoringDate"); // 监测时间
		// 获取当前组织机构编码
		String siteCode = getCurrentSiteCode();
		try {
			Integer homeUrlNum = 0; // 首页url总数
			Integer urlCntNum = 0; // 浏览量总数
			Integer ipCntNum = 0; // 访客量总数

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("visitDate", monitoringDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			List<JcVisitSitecodeResponse> list = jcVisitSitecodeServiceImpl.getSiteVisitsList(hashMap);
			if (CollectionUtils.isNotEmpty(list)) {
				for (JcVisitSitecodeResponse jc : list) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("siteCode", jc.getSiteCode());
					item.put("name", jc.getName());
					if (StringUtils.isNotEmpty(jc.getJumpUrl())) {
						item.put("url", jc.getJumpUrl());// 跳转url地址
					} else {
						item.put("url", jc.getUrl());// 首页url地址
					}
					item.put("layerType", jc.getLayerType());
					item.put("urlCnt", jc.getUrlCnt());
					item.put("ipCnt", jc.getIpCnt());
					items.add(item);

					homeUrlNum++;
					urlCntNum += jc.getUrlCnt();
					ipCntNum += jc.getIpCnt();
				}
			}
			resultMap.put("success", "true");
			resultMap.put("homeUrlNum", homeUrlNum);
			resultMap.put("urlCntNum", urlCntNum);
			resultMap.put("ipCntNum", ipCntNum);
			resultMap.put("body", items);
			resultMap.put("size", list.size());
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询网站访问量数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:网站访问量导出
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月23日下午4:18:38
	 */
	public void getSiteVisitsListExcel() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String monitoringDate = request.getParameter("monitoringDate"); // 监测时间
		// 获取当前组织机构编码
		String siteCode = getCurrentSiteCode();
		try {
			Object[] obj1 = new Object[] { "网站标识码", "网站名称", "首页url", "网站类型", "浏览量", "访客量" };
			list.add(obj1);
			String fileName = "日常监测-网站访问量(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "网站访问量";

			String layerName = null;
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("visitDate", monitoringDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			List<JcVisitSitecodeResponse> jcList = jcVisitSitecodeServiceImpl.getSiteVisitsList(hashMap);
			if (CollectionUtils.isNotEmpty(jcList)) {
				for (JcVisitSitecodeResponse jc : jcList) {
					Object[] obj = new Object[6];
					obj[0] = jc.getSiteCode();
					obj[1] = jc.getName();
					if (StringUtils.isNotEmpty(jc.getJumpUrl())) {
						obj[2] = CommonUtils.setHttpUrl(jc.getJumpUrl()); // 判断网址前缀
					} else {
						obj[2] = CommonUtils.setHttpUrl(jc.getUrl()); // 判断网址前缀
					}
					
					if(jc.getLayerType() != null){
						if(jc.getLayerType() == 1){
							layerName = DatabaseLinkType.ISORGANIZATIONAL.getName();
						}else if(jc.getLayerType() == 2){
							layerName = DatabaseLinkType.DEPARTMENT.getName();
						}else if(jc.getLayerType() == 3){
							layerName = DatabaseLinkType.UNIT.getName();
						} 
					}
					obj[3] = layerName;
					obj[4] = jc.getUrlCnt();
					obj[5] = jc.getIpCnt();
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
	 * @时间:2017年3月23日上午11:28:40
	 */
	public void getSiteVisitsTrend() {
		String siteCode = request.getParameter("siteCode"); // 网站标识码
		String dateNum = request.getParameter("dateNum"); // 天数
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer num = 0;
			if (StringUtils.isNotEmpty(dateNum)) {
				num = Integer.valueOf(dateNum);
			}
			JcVisitSitecodeRequest sRequest = new JcVisitSitecodeRequest();
			sRequest.setEndDate(DateUtils.getNextDay(new Date(), num));
			sRequest.setSiteCode(siteCode);
			sRequest.setPageSize(Integer.MAX_VALUE);

			List<QueryOrder> qList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("visit_date", QueryOrderType.ASC);
			qList.add(siteQueryOrder);
			sRequest.setQueryOrderList(qList);

			List<JcVisitSitecode> list = jcVisitSitecodeServiceImpl.queryList(sRequest);
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
