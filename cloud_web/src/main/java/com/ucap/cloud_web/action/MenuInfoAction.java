/**
 * 
 */
package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.constant.MenuInfoType;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.MenuInfoRequest;
import com.ucap.cloud_web.entity.MenuInfo;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IMenuInfoService;
import com.ucap.cloud_web.util.CacheUtils;

import net.sf.json.JSONObject;

/**描述： 前台菜单
 * 包：com.ucap.cloud_web.action
 * 文件名称：MenuInfoAction
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年3月28日下午2:17:36 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class MenuInfoAction extends BaseAction {

	@Autowired
	private IMenuInfoService menuInfoServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private DicUtils dicUtils;

	/**
	 * 
	 * @描述:组织单位跳转
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月28日下午2:34:40
	 * @return
	 */
	public String index() {
		return "success";
	}

	/**
	 * 
	 * @描述:填报单位跳转
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月28日下午2:36:08
	 * @return
	 */
	public String indexTB() {
		return "success";
	}

	/**
	 * 
	 * @描述:头部
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月28日下午6:09:10
	 */
	@SuppressWarnings("unchecked")
	public void topMenuInfoList() {
		String type = request.getParameter("type"); // 1 组织 2 填报
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<MenuInfo> topList = new ArrayList<MenuInfo>();

		List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
		QueryOrder siteQueryOrder = new QueryOrder("order_num", QueryOrderType.ASC);
		querySiteList.add(siteQueryOrder);
		try {
			MenuInfoRequest menuReq = new MenuInfoRequest();
			menuReq.setQueryOrderList(querySiteList);
			menuReq.setPageSize(Integer.MAX_VALUE);
			menuReq.setPositionType(MenuInfoType.TOP.getCode());
			menuReq.setIsDisable(MenuInfoType.START.getCode());
			if (type.equals("1")) {
				menuReq.setMenuType(MenuInfoType.ORGANIZATION.getCode());
			} else {
				menuReq.setMenuType(MenuInfoType.FILL.getCode());
			}

			String menuTop = CacheType.MENUINFO_TOP.getName();
			String key = menuTop + type;
			topList = (List<MenuInfo>) CacheUtils.get(key); // 查询缓存中是否存在
			if (CollectionUtils.isEmpty(topList)) {
				topList = menuInfoServiceImpl.queryList(menuReq); // 头部list
				CacheUtils.put(key, topList); // 将数据存到缓存中
			}

			resultMap.put("success", "true");
			resultMap.put("topList", topList);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询菜单数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:菜单列表
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月28日下午2:42:39
	 */
	@SuppressWarnings("unchecked")
	public void indMenuInfoList() {
		String type = request.getParameter("type"); // 1 组织 2 填报
		String pid = request.getParameter("pid"); // 上级

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<MenuInfo> twoLevList = new ArrayList<MenuInfo>();
		List<MenuInfo> threeLevList = new ArrayList<MenuInfo>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
		QueryOrder siteQueryOrder = new QueryOrder("order_num", QueryOrderType.ASC);
		querySiteList.add(siteQueryOrder);
		try {
			MenuInfoRequest menuReq = new MenuInfoRequest();
			menuReq.setQueryOrderList(querySiteList);
			menuReq.setPageSize(Integer.MAX_VALUE);
			menuReq.setIsDisable(MenuInfoType.START.getCode());
			if (type.equals("1")) {
				menuReq.setMenuType(MenuInfoType.ORGANIZATION.getCode());
			} else {
				menuReq.setMenuType(MenuInfoType.FILL.getCode());
			}
			Integer menuId = null;
			if(StringUtils.isNotEmpty(pid)){
				menuId = Integer.valueOf(pid);
			}
			menuReq.setParentId(menuId);
			
			String menuTwo = CacheType.MENUINFO_TWO.getName();
			String key = menuTwo + type + pid;
			twoLevList = (List<MenuInfo>) CacheUtils.get(key); // 查询缓存中是否存在
			if (CollectionUtils.isEmpty(twoLevList)) {
				twoLevList = menuInfoServiceImpl.queryList(menuReq); // 每个头部选项下的2级菜单list
				CacheUtils.put(key, twoLevList); // 将数据存到缓存中
			}

			String val = dicUtils.getValue("backweb_http_path");
			if (CollectionUtils.isNotEmpty(twoLevList)) {
				Integer twoMenuId = null;	
				for (MenuInfo twoMenu : twoLevList) {
					twoMenuId = twoMenu.getId();
					menuReq.setParentId(twoMenuId);

					String menuInfoThree = CacheType.MENUINFO_TWO.getName();
					String threekey = menuInfoThree + type + pid + twoMenuId;
					threeLevList = (List<MenuInfo>) CacheUtils.get(threekey); // 查询缓存中是否存在
					if (CollectionUtils.isEmpty(threeLevList)) {
						threeLevList = menuInfoServiceImpl.queryList(menuReq); // 每个头部选项下的3级菜单list
						CacheUtils.put(threekey, threeLevList); // 将数据存到缓存中
					}
					List<MenuInfo> infoList = new ArrayList<MenuInfo>();
					if(CollectionUtils.isNotEmpty(threeLevList)){
						for(MenuInfo info : threeLevList){
							if(StringUtils.isNotEmpty(info.getImgUrl())){
								info.setImgUrl(val + info.getImgUrl());
							}
							infoList.add(info);
						}
					}

					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", twoMenuId);
					item.put("name", twoMenu.getName());
					item.put("gUrl", twoMenu.getGUrl());
					item.put("imgUrl", val + twoMenu.getImgUrl()); // 追加后台路径
					item.put("trialProduct", twoMenu.getTrialProduct()); // 是否为试用产品    1  是  2  否
					item.put("valueOf", twoMenu.getValueOf()); // 是否携带参数 1 是 2 否
					item.put("onlyValue", twoMenu.getOnlyValue()); // 唯一值
					item.put("threeLevList", infoList);
					items.add(item);
				}
			}
			resultMap.put("success", "true");
			resultMap.put("body", items);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询菜单数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:code级别
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月4日下午9:11:59
	 */
	public void getMenuInfoLevel() {
		String siteCode = request.getParameter("siteCode"); // 1 组织 2 填报
		DatabaseTreeInfoRequest req = new DatabaseTreeInfoRequest();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			req.setSiteCode(siteCode);
			req.setIsBigdata(DatabaseTreeInfoType.ISBIGDATA.getCode());
			req.setDependType(1);
			Integer level = databaseTreeInfoServiceImpl.getSiteCodeLevel(req);
			resultMap.put("success", "true");
			resultMap.put("level", level);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}
