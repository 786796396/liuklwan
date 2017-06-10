package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.dto.ConnectionChannelDetailRequest;
import com.ucap.cloud_web.dto.ConnectionHomeDetailRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.entity.ConnectionChannelDetail;
import com.ucap.cloud_web.entity.ConnectionHomeDetail;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.service.IConnectionChannelDetailService;
import com.ucap.cloud_web.service.IConnectionHomeDetailService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.MonitoringCacheUtils;

import net.sf.json.JSONObject;

/**
 * <p>
 * Description:首页不更新和栏目不更新表
 * </p>
 * <p>
 * 
 * @Package：com.ucap.cloud_web.action </p>
 *                                    <p>
 *                                    Title: SecurityHomeChannelAction
 *                                    </p>
 *                                    <p>
 *                                    Company: 开普互联
 *                                    </p>
 *                                    <p>
 * @author：zhurk </p>
 *               <p>
 * @date：2015-12-14下午4:35:58 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SecurityHomeChannelAction extends BaseAction {
	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;
	@Autowired
	private IConnectionHomeDetailService connectionHomeDetailServiceImpl;
	@Autowired
	private IConnectionChannelDetailService connectionChannelDetailServiceImpl;
	@Autowired
	private DicUtils dicUtils;

	/************************************* 日常监测-首页不更新页面 开始********************************************/

	/**
	 * 
	 * @描述:跳转首页不更新页面--组织单位
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月20日下午3:02:40
	 * @return
	 */
	public String indexOrg() {
		List<DicItem> dicList = dicUtils.getDictList("siteType");
		request.setAttribute("dicList", dicList); // 网站类别
		request.setAttribute("yesterday", DateUtils.getYesterdayStr()); // 昨天
		return "success";
	}

	/**
	 * 
	 * @描述:首页不更新数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月21日下午2:55:51
	 */
	@SuppressWarnings("unchecked")
	public void securityHomeChannelTable() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> itemsWei = new ArrayList<Map<String, Object>>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String scanDate = request.getParameter("scanDate"); // 时间
		String twoWeeks = request.getParameter("twoWeeks"); // 超过2周的
		Integer notDay = 0;

		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("scanDate", scanDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			if (StringUtils.isNotEmpty(twoWeeks)) {
				hashMap.put("twoWeeks", twoWeeks);
			}

			String cacheSecurityHome = CacheType.MONITORING_SECURITYHOME.getName();
			String conkey = cacheSecurityHome + siteCode + scanDate + twoWeeks + siteType; // 缓存名
			List<SecurityHomeChannelRequest> queryList = (List<SecurityHomeChannelRequest>) MonitoringCacheUtils.get(conkey); // 查询缓存中是否存在
			if (queryList == null) {
				queryList = securityHomeChannelServiceImpl.getSecurityHomeList(hashMap);
				MonitoringCacheUtils.put(conkey, queryList); // 将数据存到缓存中
			}
			if (queryList != null && queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					SecurityHomeChannelRequest securityHomeChannel = queryList.get(i);
					Map<String, Object> item = new HashMap<String, Object>();
					Map<String, Object> itemWei = new HashMap<String, Object>();
					
					//新的处理方式
					if(StringUtils.isNotEmpty(securityHomeChannel.getUpdateTime())){
						item.put("siteCode", securityHomeChannel.getSiteCode());
						item.put("siteName", securityHomeChannel.getSiteName());
						// url先取跳转url,再取首页url
						if (StringUtils.isNotEmpty(securityHomeChannel.getJumpPageUrl())) {
							item.put("url", securityHomeChannel.getJumpPageUrl());// url
						} else {
							if (StringUtils.isNotEmpty(securityHomeChannel.getHomePageUrl())) {
								item.put("url", securityHomeChannel.getHomePageUrl());// url
							} else {
								item.put("url", "");
							}
						}
						item.put("lastUpdateTime", securityHomeChannel.getUpdateTime());
						
						item.put("unUpdateDays", securityHomeChannel.getNotUpdateDays());
						notDay = securityHomeChannel.getNotUpdateDays();
						item.put("notDay", notDay);
						items.add(item);
					}else{
						itemWei.put("siteCode", securityHomeChannel.getSiteCode());
						itemWei.put("siteName", securityHomeChannel.getSiteName());
						// url先取跳转url,再取首页url
						if (StringUtils.isNotEmpty(securityHomeChannel.getJumpPageUrl())) {
							itemWei.put("url", securityHomeChannel.getJumpPageUrl());// url
						} else {
							if (StringUtils.isNotEmpty(securityHomeChannel.getHomePageUrl())) {
								itemWei.put("url", securityHomeChannel.getHomePageUrl());// url
							} else {
								itemWei.put("url", "");
							}
						}
						itemWei.put("lastUpdateTime", "未知");
						itemWei.put("unUpdateDays", "未知");
						itemWei.put("notDay", -1); //-1就是未知
						itemsWei.add(itemWei);
					}
					
				}
			}
			items.addAll(itemsWei);
			resultMap.put("success", "true");
			resultMap.put("body", items);
			resultMap.put("size", queryList.size()); // 总条数
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询日常监测-首页不更新数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:首页不更新excel导出
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月21日下午5:07:59
	 */
	@SuppressWarnings("unchecked")
	public void securityHomeChannelTableExcel() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		ArrayList<Object[]> listWei = new ArrayList<Object[]>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String scanDate = request.getParameter("scanDate"); // 时间
		String twoWeeks = request.getParameter("twoWeeks"); // 超过2周的
		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			Object[] obj1 = new Object[] { "序号", "网站标识码", "网站名称", "首页url", "最后更新日期", "未更新天数", "首页不更新" };
			list.add(obj1);
			String fileName = "日常监测-首页不更新(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "首页不更新";
			Integer dataNumber = 0;
			Integer notDay = 0;
			String updateName = "";

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("scanDate", scanDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			if (StringUtils.isNotEmpty(twoWeeks)) {
				hashMap.put("twoWeeks", twoWeeks);
			}

			String cacheSecurityHomeExcel = CacheType.MONITORING_SECURITYHOMEEXCEL.getName();
			String conkey = cacheSecurityHomeExcel + siteCode + scanDate + twoWeeks + siteType; // 缓存名
			List<SecurityHomeChannelRequest> queryList = (List<SecurityHomeChannelRequest>) MonitoringCacheUtils.get(conkey); // 查询缓存中是否存在
			if (queryList == null) {
				queryList = securityHomeChannelServiceImpl.getSecurityHomeList(hashMap);
				MonitoringCacheUtils.put(conkey, queryList); // 将数据存到缓存中
			}

			if (queryList != null && queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					SecurityHomeChannelRequest securityHomeChannel = queryList.get(i);
					Object[] obj = new Object[7];
					Object[] objWei = new Object[7];
					dataNumber = dataNumber + 1;

					if (StringUtils.isNotEmpty(securityHomeChannel.getUpdateTime())) {
						obj[0] = dataNumber;
						obj[1] = securityHomeChannel.getSiteCode();
						obj[2] = securityHomeChannel.getSiteName();
						// url先取跳转url,再取首页url
						if (StringUtils.isNotEmpty(securityHomeChannel.getJumpPageUrl())) {
							obj[3] = securityHomeChannel.getJumpPageUrl();
						} else {
							if (StringUtils.isNotEmpty(securityHomeChannel.getHomePageUrl())) {
								obj[3] = securityHomeChannel.getHomePageUrl();
							} else {
								obj[3] = "";
							}
						}
						obj[4] = securityHomeChannel.getUpdateTime();
						notDay = securityHomeChannel.getNotUpdateDays();
						if (notDay > 14) { // 超过2周
							updateName = "超过2周";
						} else {
							updateName = "正常更新";
						}
						obj[5] = notDay;
						obj[6] = updateName;
						list.add(obj);
					} else {
						objWei[0] = dataNumber;
						objWei[1] = securityHomeChannel.getSiteCode();
						objWei[2] = securityHomeChannel.getSiteName();
						// url先取跳转url,再取首页url
						if (StringUtils.isNotEmpty(securityHomeChannel.getJumpPageUrl())) {
							objWei[3] = securityHomeChannel.getJumpPageUrl();
						} else {
							if (StringUtils.isNotEmpty(securityHomeChannel.getHomePageUrl())) {
								objWei[3] = securityHomeChannel.getHomePageUrl();
							} else {
								objWei[3] = "";
							}
						}
						objWei[4] = "未知";
						objWei[5] = "未知";
						objWei[6] = "未知";
						listWei.add(objWei);
					}
				}
				list.addAll(listWei);
			}
			ExportExcel.securityQuestionExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/************************************* 日常监测-首页不更新页面 结束********************************************/

	/**
	 * @Description: 获取不连通数据
	 * @author Na.Y --- 2016-08-17下午02:26:56
	 */
	public void getConnection() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 获取页面传递的参数
			String siteCode = request.getParameter("siteCode");
			String scanDate = request.getParameter("scanDate");

			String url = request.getParameter("url");

			String type = request.getParameter("type");
			
		
			if(StringUtils.isEmpty(scanDate)){
				scanDate= DateUtils.getYesterdayStr();
			}
		
			// 首页连通性数据
			if (type.equals("1")) {
				ConnectionHomeDetailRequest request = new ConnectionHomeDetailRequest();
				request.setScanDate(scanDate);
				request.setSiteCode(siteCode);
				request.setPageSize(Integer.MAX_VALUE);
				List<QueryOrder> listOrder = new ArrayList<QueryOrder>();
				QueryOrder queryOrder = new QueryOrder("scan_time", QueryOrderType.DESC);
				listOrder.add(queryOrder);
				request.setQueryOrderList(listOrder);
				List<ConnectionHomeDetail> listConne = connectionHomeDetailServiceImpl.queryList(request);

				if (!CollectionUtils.isEmpty(listConne)) {
					List<ConnectionHomeDetail> listConneResult = new ArrayList<ConnectionHomeDetail>();
					for (ConnectionHomeDetail connectionHomeDetail : listConne) {

						connectionHomeDetail.setQuestionDescribe(QuestionType.getNameByCode(connectionHomeDetail
								.getQuestionCode()));

						listConneResult.add(connectionHomeDetail);
					}
					resultMap.put("body", listConneResult);
				}
			} else {
				ConnectionChannelDetailRequest request = new ConnectionChannelDetailRequest();
				request.setScanDate(scanDate);
				request.setSiteCode(siteCode);
				request.setUrl(url);
				request.setPageSize(Integer.MAX_VALUE);
				List<QueryOrder> listOrder = new ArrayList<QueryOrder>();
				QueryOrder queryOrder = new QueryOrder("scan_time", QueryOrderType.DESC);
				listOrder.add(queryOrder);
				request.setQueryOrderList(listOrder);
				List<ConnectionChannelDetail> listConne = connectionChannelDetailServiceImpl.queryList(request);
				if (!CollectionUtils.isEmpty(listConne)) {
					List<ConnectionChannelDetail> listConneResult = new ArrayList<ConnectionChannelDetail>();
					for (ConnectionChannelDetail connectionChannelDetail : listConne) {

						connectionChannelDetail.setQuestionDescribe(QuestionType.getNameByCode(connectionChannelDetail
								.getQuestionCode()));

						listConneResult.add(connectionChannelDetail);
					}
					resultMap.put("body", listConneResult);
					
				}
			}
			
			resultMap.put("scanDate", scanDate);

			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
