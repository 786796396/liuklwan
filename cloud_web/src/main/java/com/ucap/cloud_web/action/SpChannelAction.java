/**
 * 
 */
package com.ucap.cloud_web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.bizService.DatabaseTreeBizService;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.constant.ErrorInfoType;
import com.ucap.cloud_web.constant.HandleType;
import com.ucap.cloud_web.constant.UserType;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.DetectionOrgCountRequest;
import com.ucap.cloud_web.dto.DicItemRequest;
import com.ucap.cloud_web.dto.ErrorInfoRequest;
import com.ucap.cloud_web.dto.MTaskoverviewRequest;
import com.ucap.cloud_web.dto.SpArticleRequest;
import com.ucap.cloud_web.dto.SpChannelRequest;
import com.ucap.cloud_web.dto.SpSiteChannelRequest;
import com.ucap.cloud_web.dto.SpSiteRequest;
import com.ucap.cloud_web.dto.StatisticsSiteChannelRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DetectionOrgCount;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.ErrorInfo;
import com.ucap.cloud_web.entity.SpArticle;
import com.ucap.cloud_web.entity.SpChannel;
import com.ucap.cloud_web.entity.SpSite;
import com.ucap.cloud_web.entity.SpSiteChannel;
import com.ucap.cloud_web.entity.StatisticsSiteChannel;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDetectionOrgCountService;
import com.ucap.cloud_web.service.IDicItemService;
import com.ucap.cloud_web.service.IErrorInfoService;
import com.ucap.cloud_web.service.IMTaskoverviewService;
import com.ucap.cloud_web.service.ISpArticleService;
import com.ucap.cloud_web.service.ISpChannelService;
import com.ucap.cloud_web.service.ISpSiteChannelService;
import com.ucap.cloud_web.service.ISpSiteService;
import com.ucap.cloud_web.service.IStatisticsSiteChannelService;
import com.ucap.cloud_web.util.CacheUtils;

import net.sf.json.JSONObject;

/**描述： 个性化  栏目信息
 * 包：com.ucap.cloud_web.action
 * 文件名称：SpChannelAction
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2016年11月22日上午11:53:04 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SpChannelAction extends BaseAction {

	@Autowired
	private IMTaskoverviewService MTaskoverviewServiceImpl;
	@Autowired
	private ISpSiteService spSiteServiceImpl;
	@Autowired
	private ISpChannelService spChannelServiceImpl;
	@Autowired
	private ISpArticleService spArticleServiceImpl;
	@Autowired
	private ISpSiteChannelService spSiteChannelServiceImpl;
	@Autowired
	private IDicItemService dicItemServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private IDetectionOrgCountService detectionOrgCountServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	private DatabaseTreeBizService databaseTreeBizServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private IStatisticsSiteChannelService statisticsSiteChannelServiceImpl;
	@Autowired
	private IErrorInfoService errorInfoServiceImpl;
	@Autowired
	private DicUtils dicUtils;

	public final String CACHE_DATA_LIST = "dataList";
	public final String CACHE_SPSITE_LIST = "spSiteList";
	public final String CACHE_SPARTICLE = "spArticle";
	public final String CACHE_SPCHANNEL = "spChannel";
	public final String CACHE_SPSITECHANNEL = "spSiteChannel";
	public final String CACHE_MTASKOVERVIEW = "mTaskoverview";
	public final String CACHE_DETECTIONORGCOUNT = "detectionOrgCount";
	
	/**
	 * 
	 * @描述:个性化登录验证
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月9日下午2:12:33
	 */
	@SuppressWarnings("unchecked")
	public void personalizedLogin(){
		String userName = request.getParameter("userName");
		Map<String,Object> map = new HashMap<String, Object>();
		StringBuffer u = request.getRequestURL();
		String uri = u.toString();
		try {
			DatabaseTreeInfoRequest requset = new DatabaseTreeInfoRequest();
			Map<String, Object> naMap = spSiteServiceImpl.domainName(uri); // 根据url获取该域名下siteCode
			String siteCode = (String) naMap.get("siteCode");
			if(StringUtils.isNotEmpty(userName)){
				if (userName.equals(siteCode)) {
					map.put("value", 0);
				} else {
					String code = "";
					String key = CACHE_DATA_LIST + siteCode; // 缓存名
					List<DatabaseTreeInfo> codeList = (List<DatabaseTreeInfo>) CacheUtils.get(key); // 查询缓存中是否存在
					if (codeList == null) {
						requset.setExclusiveCode(siteCode);
						codeList = databaseTreeBizServiceImpl.getDatabaseTreeInfoList(requset);
						CacheUtils.put(key, codeList); // 将数据存到缓存中
					}
					if (codeList == null || codeList.size() <= 0) {
						map.put("value", 1); // 0是本域帐号 1 不是
						map.put("message", "该域名下帐号信息有误!");
					}
					if (codeList != null && codeList.size() > 0) {
						for (DatabaseTreeInfo da : codeList) {
							code = da.getSiteCode();
							if (StringUtils.isNotEmpty(code)) {
								if (!userName.equals(code)) {
									map.put("value", 1); // 0是本域帐号 1 不是
									map.put("message", "非该域名下帐号登录!");
								} else {
									map.put("value", 0);
									map.remove("message");
									break;
								}
							}
						}
					}
				}
			}
			map.put("success", "true");
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	
	/**
	 * 
	 * @描述:跳转个性化首页
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月22日上午11:56:19
	 * @return
	 */
	public String index() {
		String siteCode = "";
		StringBuffer u = request.getRequestURL();
		String uri = u.toString();
		Map<String, Object> naMap = spSiteServiceImpl.domainName(uri);
		siteCode = (String) naMap.get("siteCode");
		if (StringUtils.isNotEmpty(siteCode)) {
			DatabaseOrgInfo d = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(siteCode);
			if (d != null) {
				request.setAttribute("name", d.getName());
			}
		}
		request.setAttribute("siteCode", siteCode);
		return "success";
	}

	/**
	 * 
	 * @描述:跳转专属模版B页面
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月27日下午6:58:24
	 * @return
	 */
	public String templateB() {
		List<DicItem> dicList = dicUtils.getDictList("wtType");
		request.setAttribute("dicList", dicList); // 网站类别

		String siteCode = request.getParameter("siteCode");
		String templateType = request.getParameter("templateType");

		if (StringUtils.isNotEmpty(siteCode) && StringUtils.isNotEmpty(templateType)) {
			String displayModule = ""; // 显示模块（1:日常检测,2:网站概况,3:栏目,4:大数据,5:政府网站基础信息数据库,6:政府网站网民找错数据）
			Integer loginConfig = null;
			SpSiteRequest req = new SpSiteRequest();
			req.setStatus(1); // 状态（0：未开通，1：开通，2：停用）
			req.setDelFlag(0); // 0：正常，1：删除
			req.setSiteCode(siteCode);
			req.setTemplateType(Integer.valueOf(templateType));
			req.setNowDate(DateUtils.formatStandardDate(new Date())); // 当前时间
			List<SpSite> spList = spSiteServiceImpl.queryList(req);
			if (CollectionUtils.isNotEmpty(spList)) {
				SpSite sp = spList.get(0);
				displayModule = sp.getDisplayModule();
				loginConfig = sp.getLoginConfig();
			} else {
				// 到期
				return "bigDataError";
			}
			if (StringUtils.isNotEmpty(siteCode)) {
				DatabaseOrgInfo d = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(siteCode);
				if (d != null) {
					request.setAttribute("name", d.getName());
				}
			}

			request.setAttribute("siteCode", siteCode);
			request.setAttribute("loginConfig", loginConfig);
			request.setAttribute("templateType", templateType);
			request.setAttribute("displayModule", displayModule);
		} else {
			// 没有传值跳转报错页面，同大数据引用地址一样
			return "bigDataError";
		}
		return "success";
	}

	/**
	 * 
	 * @描述:跳转详情页面
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年11月24日上午10:45:53
	 * @return
	 */
	public String sqDetail() {
		String id = request.getParameter("id");
		String siteCode = request.getParameter("siteCode");

		request.setAttribute("id", id);
		request.setAttribute("siteCode", siteCode);
		return "success";
	}

	/**
	 * 
	 * @描述:个性化基本信息查询
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月7日下午1:17:50
	 */
	public void spClannelBasic(){
		Map<String,Object> map = new HashMap<String, Object>();
		String siteCode = "";
		String displayModule = "";
		String loginConfig = "";
		try {
			StringBuffer u = request.getRequestURL();
			String uri = u.toString();
			Map<String, Object> naMap = spSiteServiceImpl.domainName(uri);
			if (naMap != null && naMap.size() > 0) {
				siteCode = (String) naMap.get("siteCode");
				displayModule = (String) naMap.get("displayModule");
				loginConfig = (String) naMap.get("loginConfig");
				map.put("type", 1); // 1 有 2 没有
			} else {
				map.put("type", 2);
			}

			if (StringUtils.isNotEmpty(siteCode)) {
				DatabaseOrgInfo d = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(siteCode);
				if(d != null){
					map.put("name", d.getName()+"网站云监管");
				}
			}

			map.put("success", "true");
			map.put("siteCode", siteCode);
			map.put("displayModule", displayModule);
			map.put("loginConfig", loginConfig);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("errorMsg", "查询个性化基本信息数据异常！");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	
	/**
	 * 
	 * @描述:错误页面
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月4日下午8:57:37
	 * @return
	 */
	public String spClannelError() {
		return "bigDataError";
	}

	/**
	 * 
	 * @描述:站点配置信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月22日下午6:48:26
	 */
	@SuppressWarnings("unchecked")
	public void spConfiguration() {
		String siteCode = request.getParameter("siteCode");
		SpSiteRequest request = new SpSiteRequest();
		Map<String, Object> spMap = new HashMap<String, Object>();
		try {
			request.setSiteCode(siteCode);
			request.setStatus(1);
			request.setDelFlag(0); // 0：正常，1：删除
			request.setPageSize(Integer.MAX_VALUE);

			String key = CACHE_SPSITE_LIST + siteCode; // 缓存名
			List<SpSite> spList = (List<SpSite>) CacheUtils.get(key); // 查询缓存中是否存在
			if (spList == null) {
				spList = spSiteServiceImpl.queryList(request);
			}
			if (spList != null && spList.size() > 0) {
				CacheUtils.put(key, spList); // 将数据存到缓存中
				for (SpSite sp : spList) {
					spMap.put("siteShowName", sp.getSiteShowName()); // 站点名称
					if (StringUtils.isNotEmpty(sp.getLogo())) {
						spMap.put("logo", urlAdapterVar.getSpUrl() + sp.getLogo()); // 徽章
					}
					spMap.put("url", sp.getUrl()); // 地址
					spMap.put("bottomText", sp.getBottomText()); // 底部文字
					spMap.put("displayModule", sp.getDisplayModule());
				}
			}
			spMap.put("success", "true");
			writerPrint(JSONObject.fromObject(spMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			spMap.put("success", "false");
			spMap.put("errorMsg", "查询站点配置信息数据异常！");
			writerPrint(JSONObject.fromObject(spMap).toString());
		}
	}
	
	/**
	 * @Description: 健康指数
	 * @author qinjy
	 */
	@SuppressWarnings("unchecked")
	public void getIndexSum(){
		String menuType = request.getParameter("menuType");
		String siteCode = request.getParameter("siteCode");
		try {
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			String scanDate = DateUtils.getNextDay(new Date(), -1);
			String type=queryByType(menuType, siteCode);
			
			/***查询 DetectionOrgCount表 start*****/
			DetectionOrgCountRequest detectionOrgCountRequest=new DetectionOrgCountRequest();
			detectionOrgCountRequest.setScanDate(scanDate);
			detectionOrgCountRequest.setSiteCode(siteCode);
			detectionOrgCountRequest.setType(type);
			List<QueryOrder> queryOrderOrgList=new ArrayList<QueryOrder>();
			QueryOrder queryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryOrderOrgList.add(queryOrder);
			detectionOrgCountRequest.setQueryOrderList(queryOrderOrgList);
			
			String key = CACHE_DETECTIONORGCOUNT + siteCode;
			List<DetectionOrgCount> detectionOrgCountList = (List<DetectionOrgCount>) CacheUtils.get(key);  // 查询缓存中是否存在
			if(detectionOrgCountList == null){
				detectionOrgCountList = detectionOrgCountServiceImpl.queryList(detectionOrgCountRequest);
			}
			CacheUtils.put(key, detectionOrgCountList);   // 将数据存到缓存中
			
			for(int i=0;i<detectionOrgCountList.size();i++){
				DetectionOrgCount detectionOrgCount =detectionOrgCountList.get(i);
				String indexCount=detectionOrgCount.getIndexCount();//健康指数（平均）
				if(null == indexCount){
					indexCount="0";
				}
				String leadAvgRate=detectionOrgCount.getLeadAvgRate();//领先全国
				if(null == leadAvgRate){
					leadAvgRate="0";
				}
				String leadYesterday=detectionOrgCount.getLeadYesterday();//相比昨天数
				if(null == leadYesterday){
					leadYesterday="0";
				}
				String leadYesterdayRate=detectionOrgCount.getLeadYesterdayRate();//相比昨天比
				if(null == leadYesterdayRate){
					leadYesterdayRate="0";
				}
				//领先全国比
				map.put("leadSum",leadAvgRate);
				//折算分数
				map.put("convertScores",indexCount);
				//健康分数
				map.put("totalSumNumber",indexCount);
				//相差多少
				map.put("differential", leadYesterday);
				map.put("differentialRate", leadYesterdayRate);
			}
			if(detectionOrgCountList.size()==0){
				//领先全国比
				map.put("leadSum",0);
				//折算分数
				map.put("convertScores",0);
				//健康分数
				map.put("totalSumNumber",0);
				//相差多少
				map.put("differential", 0);
				map.put("differentialRate", 0);
			}
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @描述:根据menuType获取 DetectionOrgCount表type
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月22日下午17:36:19 
	 * @return
	 */
	public String queryByType(String menuType, String siteCode) {
		Integer t = 0;
		t = Integer.valueOf(menuType);
		String type = "0";
		try {
			if (t == 0) {
				type = "0";
			} else if (t == UserType.TYPE_PROVINCE.getCode()) {//省部门

				type = DatabaseLinkType.DEPARTMENT.getCode().toString();

			} else if (t == UserType.TYPE_PROVINCE.getCode()) {//省登录--下级单位（市级）
				type = DatabaseLinkType.UNIT.getCode().toString();

			} else if (t == UserType.TYPE_CITY.getCode()) {//市登录--市部门
				type = DatabaseLinkType.DEPARTMENT.getCode().toString();

			} else if (t == UserType.TYPE_COUNTY.getCode()) {//县级登录  本级部门

				type = DatabaseLinkType.DEPARTMENT.getCode().toString();

			} else if (t == UserType.TYPE_COUNTY.getCode()) {//市登录--下级单位
				type = DatabaseLinkType.UNIT.getCode().toString();

			}else if (t == 8) {//其他
				type = DatabaseLinkType.OTHER.getCode().toString();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return type;

	}
	
	/**
	 * 
	 * @描述:通过id 获取文章数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年11月24日上午11:14:12 
	 * @return
	 */
	public void getArticle() {
		Map<String,Object> spMap = new HashMap<String, Object>();
		String id = request.getParameter("id");//文章id
		String siteCode = request.getParameter("siteCode");
		try{
			if(StringUtils.isNotEmpty(id)){
				String key = CACHE_SPARTICLE + id;// 缓存名
				SpArticle spArticle = (SpArticle) CacheUtils.get(key);  // 查询缓存中是否存在
				if(spArticle == null){
					spArticle = spArticleServiceImpl.get(Integer.parseInt(id));
					CacheUtils.put(key, spArticle);   // 将数据存到缓存中
				}
				if(spArticle.getChannelId()>0){//栏目id
					SpChannel  sChannel = spChannelServiceImpl.get(spArticle.getChannelId());
					spMap.put("channelName", sChannel.getClannelName());
				}else{
					spMap.put("channelName", "");
				}
				if(StringUtils.isNotEmpty(siteCode)){//siteCode
					SpSiteRequest request = new SpSiteRequest();
					request.setSiteCode(siteCode);
					List<SpSite>  list = spSiteServiceImpl.queryList(request);
					if(list.size()>0){
						spMap.put("logo", urlAdapterVar.getSpUrl()+list.get(0).getLogo());
						spMap.put("buttom", list.get(0).getBottomText());
					}
				}else{
					spMap.put("logo", "");
					spMap.put("buttom","");
				}
				String con = spArticle.getContent();
				String cont = null;
				if(StringUtils.isNotEmpty(con)){
					cont = con.replace("/upload", urlAdapterVar.getSpUrl()+"/upload");  //将字符串/upload替换成完整的url
				}
				spMap.put("content", cont);
				spMap.put("title",spArticle.getTitle());
				spMap.put("sourceName", spArticle.getSourceName());
				spMap.put("sourceUrl", spArticle.getSourceUrl());
				spMap.put("date", DateUtils.formatStandardFullDateTime(spArticle.getCreateTime()));
				spMap.put("success", "true");
				
				writerPrint(JSONObject.fromObject(spMap).toString());
			}else{
				spMap.put("success", "false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			spMap.put("success", "false");
			spMap.put("errorMsg", "查询文章数据异常！");
			writerPrint(JSONObject.fromObject(spMap).toString());
		}
	}
	
	/**
	 * 栏目文章信息
	 * @描述:
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月23日下午4:10:43
	 */
	@SuppressWarnings("unchecked")
	public void spColumnArticle () {
		Map<String,Object> spMap = new HashMap<String, Object>();
		String isBigData = request.getParameter("isBigData");
		String siteCode = request.getParameter("siteCode");
		int isBig = 0;
		if(StringUtils.isNotEmpty(isBigData)){
			isBig = Integer.valueOf(isBigData);
		}
		try{
			DicItemRequest dicreq = new DicItemRequest();
			DicItem dic = new DicItem();
			dicreq.setEnName("NOTICE_ID");  // 字典（通知公告）
			List<DicItem> dicList =  dicItemServiceImpl.queryList(dicreq);
			if(dicList != null && dicList.size() > 0){
				dic = dicList.get(0);
			}
			String v = dic.getValue();
			int va = 0;
			if(StringUtils.isNotEmpty(v)){
				va = Integer.valueOf(v);
			}
			SpChannelRequest req = new SpChannelRequest();
			req.setId(va);
			
			String key = CACHE_SPCHANNEL + va;// 缓存名
			List<SpChannel> spnList = (List<SpChannel>) CacheUtils.get(key);  // 查询缓存中是否存在
			if(spnList == null){
				spnList = spChannelServiceImpl.queryList(req);
			}
			SpChannel spn = new SpChannel();
			if(spnList != null && spnList.size() > 0){
				CacheUtils.put(key, spnList);   // 将数据存到缓存中
				spn = spnList.get(0);
			}
			
			
			ArrayList<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			queryOrderList.add(new QueryOrder("create_time",QueryOrderType.DESC));
			
			//置顶
			ArrayList<Object> articlist = new ArrayList<Object>();
			SpArticleRequest artreq = new SpArticleRequest();
			artreq.setDelFlag(0);  // 0：正常，1：删除
			artreq.setChannelId(va);
			artreq.setPageSize(6);
			artreq.setSiteCode(siteCode);
			
			artreq.setIsTop(1);  // 0：无置顶，1：置顶
			List<SpArticle> artTopList = spArticleServiceImpl.getSpArtList(artreq);
			SpArticle spTop = new SpArticle();
			if(artTopList != null && artTopList.size() > 0){
				spTop = artTopList.get(0);
				Map<String,Object> artTopMap = new HashMap<String, Object>();
				artTopMap.put("artId", spTop.getId());
				artTopMap.put("title", spTop.getTitle());
				articlist.add(artTopMap);
			}
			
			//无置顶
			artreq.setIsTop(0);  // 0：无置顶，1：置顶
			List<SpArticle> artList = spArticleServiceImpl.getSpArtList(artreq);
			if(artList != null && artList.size() > 0){
				for(SpArticle art : artList){
					Map<String,Object> artmap = new HashMap<String, Object>();
					artmap.put("artId", art.getId());
					artmap.put("title", art.getTitle());
					articlist.add(artmap);
				}
			}
			
			// 除公告外的栏目信息及文章信息
			SpSiteChannelRequest spSch = new SpSiteChannelRequest();
			if(isBig == 0){       // 是否开启大数据（0：不开启，1：开启）
				spSch.setPageSize(4);
			}else if(isBig == 1){
				spSch.setPageSize(2);
			}
			
			spSch.setSiteCode(siteCode);
			spSch.setSort(0);
			spSch.setNotId(1);
			spSch.setQueryOrderList(queryOrderList);
			String spSchkey = CACHE_SPSITECHANNEL + siteCode;
			List<SpSiteChannel> spSchQueryList = (List<SpSiteChannel>) CacheUtils.get(spSchkey);  // 查询缓存中是否存在
			if(spSchQueryList == null){
				spSchQueryList = spSiteChannelServiceImpl.queryList(spSch);
			}
			
			SpArticle art = new SpArticle();
			SpArticleRequest articreq = new SpArticleRequest();
			ArrayList<Object> chlist = new ArrayList<Object>();
			ArrayList<Object> slist = new ArrayList<Object>();
			SpChannelRequest request = new SpChannelRequest();
			
			if(spSchQueryList != null && spSchQueryList.size() > 0){
				CacheUtils.put(spSchkey, spSchQueryList);   // 将数据存到缓存中
				for(SpSiteChannel spSitc : spSchQueryList){
					if(spSitc.getChannelId() != 1){   // 除公告的栏目
						request.setDelFlag(0);  // 0：正常，1：删除
						request.setId(spSitc.getChannelId());
						List<SpChannel> spList = spChannelServiceImpl.queryList(request);
						if(spList != null && spList.size() > 0){
							for(SpChannel spc : spList){
								Map<String,Object> chamap = new HashMap<String, Object>();
								chamap.put("chId", spc.getId());
								chamap.put("clannelName", spc.getClannelName());
								
								// 有置顶
								articreq.setDelFlag(0); // 0：正常，1：删除
								articreq.setIsTop(1);   //  是否置顶（0：否，1：是）
								articreq.setSort(0);
								articreq.setChannelId(spc.getId());
								articreq.setSiteCode(siteCode);
								articreq.setPageSize(Integer.MAX_VALUE);
								List<SpArticle> articList  = spArticleServiceImpl.getSpArtList(articreq);
								if(articList != null && articList.size() > 0){
									art = articList.get(0);
									chamap.put("aId", art.getId());
									if(art.getTitle() != null){
										chamap.put("title", art.getTitle());
									}
									if(art.getSummary() != null){
										chamap.put("summary", art.getSummary());
									}
								}
								
								// 无置顶
								articreq.setIsTop(0);   //  是否置顶（0：否，1：是）
								articreq.setPageSize(5);
								List<SpArticle> articleList = spArticleServiceImpl.getSpArtList(articreq);
								if(articleList != null && articleList.size() > 0){
									for(SpArticle s : articleList){
										Map<String,Object> sMap = new HashMap<String, Object>();
										sMap.put("spaId", s.getId());
										sMap.put("chaId", s.getChannelId());
										sMap.put("sTitle", s.getTitle());
										if(s.getCreateTime() != null){
											String day = new SimpleDateFormat("yyyy/MM/dd").format(s.getCreateTime());
											sMap.put("createTime", day);
										}
										slist.add(sMap);
									}
								}
								chlist.add(chamap);
							}
						}
					}
				}
			}
			
			spMap.put("noticeName", spn.getClannelName());  
			spMap.put("articlist", articlist);  // 文章信息
			spMap.put("chlist", chlist);  
			spMap.put("slist", slist);  
			spMap.put("success", "true");
			writerPrint(JSONObject.fromObject(spMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			spMap.put("success", "false");
			spMap.put("errorMsg", "查询栏目文章信息数据异常！");
			writerPrint(JSONObject.fromObject(spMap).toString());
		}
	}
	
	/**
	 * 
	 * @描述:最近7天的监测数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月22日下午6:48:26
	 */
	@SuppressWarnings("unchecked")
	public void monitoringData () {
		String siteCode = request.getParameter("siteCode");
		Date yesday = DateUtils.getYesterdaytime();    //  昨天的时间
		Date weekday = DateUtils.getOneWeekdaytime();  //  7天前的时间 
		String yes = DateUtils.formatShortDate(yesday);
		String week = DateUtils.formatShortDate(weekday);
		
		Map<String,Object> mtMap=new HashMap<String, Object>();
		ArrayList<Object> timelist = new ArrayList<Object>();
		ArrayList<Object> countlist = new ArrayList<Object>();
		ArrayList<Object> deadlist = new ArrayList<Object>();
		ArrayList<Object> datesitelist = new ArrayList<Object>();
		try {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("yes", yes);
			hashMap.put("week", week);
			hashMap.put("taskid", siteCode);
			String key = CACHE_MTASKOVERVIEW + siteCode;// 缓存名
			List<MTaskoverviewRequest> mtList = (List<MTaskoverviewRequest>) CacheUtils.get(key);  // 查询缓存中是否存在
			// if(mtList == null){
				mtList = MTaskoverviewServiceImpl.getMTaskoverMap(hashMap);
			// }
			if(mtList != null && mtList.size() > 0){
				CacheUtils.put(key, mtList);   // 将数据存到缓存中
				for(MTaskoverviewRequest mt : mtList){
					if(mt.getCountday() != null){
						//工具类没有对应的方法
						Date format = new SimpleDateFormat("yyyyMMdd").parse(mt.getCountday());
						String day = new SimpleDateFormat("yyyy/MM/dd").format(format);
						timelist.add(day);
					}
					
					Double linkRemainder = (double) 100; 
					Double indexRemainder = (double) 100; 
					Double linkNum = mt.getLinkerrsiteprop();
					Double indexNum = mt.getIndexdeadprop();
					if(linkNum != null){
						linkRemainder = linkRemainder-linkNum;
					}
					if(indexNum != null){
						indexRemainder = indexRemainder-indexNum;
					}
					
					countlist.add(linkRemainder);  // 当日连不通网站占比
					deadlist.add(indexRemainder);     // 当日死链占比均值   
					datesitelist.add(mt.getUpdatenum());  // 当日更新网站条数
				}
			}
			mtMap.put("timelist", timelist);
			mtMap.put("countlist", countlist);
			mtMap.put("deadlist", deadlist);
			mtMap.put("datesitelist", datesitelist);
			writerPrint(JSONObject.fromObject(mtMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			mtMap.put("errorMsg", "查询最近7天的监测数据异常！");
			writerPrint(JSONObject.fromObject(mtMap).toString());
		}
	}
	
	/************************************** 模版B **************************************************/

	/**
	 * 
	 * @描述:查询sitecode下所以的站点
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月9日下午2:02:10 
	 * @param siteCode
	 */
	public String getSiteCodeList(String siteCode){
		List<DatabaseTreeInfo> treeList = new ArrayList<DatabaseTreeInfo>();
		String infoList = null;
		try
		{
			DatabaseTreeInfoRequest treeReq = new DatabaseTreeInfoRequest();
			treeReq.setSiteCode(siteCode);
			treeList = databaseTreeInfoServiceImpl.queryList(treeReq);  // 查询sitecode的code码
			infoList = treeList.get(0).getCode();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return infoList;
	}
	
	/**
	 * 
	 * @描述:政府网站数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月2日下午3:35:29
	 */
	public void getDatabaseInfoBycode() {
		String siteCode = request.getParameter("siteCode");
		String typeVal = request.getParameter("aaaTypeVal"); // 区别
		String queryInput = request.getParameter("queryInput");
		String size = request.getParameter("size");
		String pageNo = request.getParameter("pageNo");

		Map<String, Object> map = new HashMap<String, Object>();
		List<DatabaseInfo> infoList = new ArrayList<DatabaseInfo>();
		ArrayList<Object> list = new ArrayList<Object>();
		DatabaseInfoRequest req = new DatabaseInfoRequest();
		try {
			Integer isexp = null;
			String code = getSiteCodeList(siteCode);
			req.setCode(code);
			req.setIsBigdata(DatabaseTreeInfoType.ISBIGDATA.getCode());
			req.setTypeVal(typeVal);
			req.setQueryInput(queryInput);
			req.setPageNo(Integer.valueOf(pageNo));
			req.setPageSize(Integer.valueOf(size));
			req.setPaging(1); // 区分是否取前100条
			PageVo<DatabaseInfo> pageVo = databaseInfoServiceImpl.getDatabaseInfoBycode(req);
			req.setTypeVal(null);
			req.setQueryInput(null);
			Integer countNum = databaseInfoServiceImpl.getDatabaseInfoCount(req);
			infoList = pageVo.getData();
			if (CollectionUtils.isNotEmpty(infoList)) {
				int num = 0;
				for (DatabaseInfo info : infoList) {
					HashMap<String, Object> errInfoMap = new HashMap<String, Object>();
					num++;
					errInfoMap.put("num", num);
					errInfoMap.put("siteCode", info.getSiteCode());
					errInfoMap.put("director", info.getDirector());
					errInfoMap.put("name", info.getName());
					if (StringUtils.isNotEmpty(info.getJumpUrl())) {
						errInfoMap.put("url", info.getJumpUrl());// 跳转url地址
					} else {
						errInfoMap.put("url", info.getUrl());// 首页url地址
					}
					isexp = info.getIsexp();
					if (isexp != null && isexp != 0) {
						errInfoMap.put("isexp", DatabaseInfoType.getNameByCode(isexp));
					} else {
						errInfoMap.put("isexp", "");
					}
					list.add(errInfoMap);
				}
			}
			map.put("success", "true");
			map.put("body", list);
			map.put("countNum", countNum);
			// 默认值
			map.put("totalRecords", pageVo.getRecordSize());
			map.put("iTotalDisplayRecords", pageVo.getRecordSize());
			map.put("hasMoreItems", true);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("errorMsg", "查询专属页面政府网站数据异常！");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}

	/**
	 * 
	 * @描述:获取曝光台数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月28日下午4:19:16
	 */
	@SuppressWarnings("unused")
	public void getErrorInfoList() {
		String typeVal = request.getParameter("siteTypeVal"); // 类型
		String siteCode = request.getParameter("siteCode");
		String exposureKeyId = request.getParameter("exposureKeyId"); // 标识码及网站名称
		String size = request.getParameter("size");
		String pageNo = request.getParameter("pageNo");
		Map<String, Object> map = new HashMap<String, Object>();
		List<ErrorInfo> errList = new ArrayList<ErrorInfo>();
		ArrayList<Object> list = new ArrayList<Object>();
		ErrorInfoRequest req = new ErrorInfoRequest();
		try {
			String time = "";
			Integer pro = null;
			Integer sta = null;
			ArrayList<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			queryOrderList.add(new QueryOrder("transact_time", QueryOrderType.DESC));
			if (!typeVal.equals("-1")) {
				req.setTypeVal(Integer.valueOf(typeVal));
			}
			
			String code = getSiteCodeList(siteCode);
			req.setCode(code);
			req.setIsBigdata(DatabaseTreeInfoType.ISBIGDATA.getCode());
			req.setPaging(1);
			if (StringUtils.isNotEmpty(exposureKeyId)) {
				req.setExposureKeyId(exposureKeyId);
			}
			req.setPageNo(Integer.valueOf(pageNo));
			req.setPageSize(Integer.valueOf(size));
			req.setQueryOrderList(queryOrderList);
			PageVo<ErrorInfo> pageVo = errorInfoServiceImpl.getErrorInfoList(req);
			errList = pageVo.getData();
			if (CollectionUtils.isNotEmpty(errList)) {
				int num = 0;
				for (ErrorInfo er : errList) {
					HashMap<String, Object> errInfoMap = new HashMap<String, Object>();
					num++;
					errInfoMap.put("num", num);
					errInfoMap.put("id", er.getId());
					errInfoMap.put("siteCode", er.getSiteCode());
					errInfoMap.put("name", er.getName());
					pro = er.getProblemId();
					if (pro != null) {
						errInfoMap.put("problemId", ErrorInfoType.getNameByCode(pro));
					} else {
						errInfoMap.put("problemId", "");
					}

					if (er.getReviewTime() != null) {
						time = DateUtils.formatStandardDate(er.getReviewTime());
					}
					errInfoMap.put("reviewTime", time);
					sta = er.getStatus();
					if (sta != null) {
						errInfoMap.put("status", HandleType.getNameByCode(sta));
					} else {
						errInfoMap.put("status", "");
					}
					list.add(errInfoMap);
				}
			}
			map.put("success", "true");
			map.put("body", list);
			// 默认值
			map.put("totalRecords", pageVo.getRecordSize());
			map.put("iTotalDisplayRecords", pageVo.getRecordSize());
			map.put("hasMoreItems", true);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("errorMsg", "查询专属页面曝光台数据异常！");
			writerPrint(JSONObject.fromObject(map).toString());

		}
	}

	/**
	 * 
	 * @描述:根据id获取数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月28日下午4:19:16
	 */
	@SuppressWarnings("unused")
	public void getErrorInfoById() {
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		ErrorInfo errInfo = new ErrorInfo();
		try {
			Integer problemId = null; // 问题类型
			String errName = "";
			String proName = null;
			String errorUrl = ""; // 地址
			String description = ""; // 描述
			String transactUnit = ""; // 受理单位
			String transactResult = ""; // 受理结果
			Integer status = null; // 办理状态
			String staName = "";
			errInfo = errorInfoServiceImpl.get(Integer.valueOf(id));
			if (errInfo != null) {
				problemId = errInfo.getProblemId();
				if (problemId != null) {
					proName = ErrorInfoType.getNameByCode(problemId);
				} else {
					proName = "";
				}
				errName = errInfo.getName();
				errorUrl = errInfo.getErrorUrl();
				description = errInfo.getDescription();
				transactUnit = errInfo.getTransactUnit();
				transactResult = errInfo.getTransactResult();
				status = errInfo.getStatus();
				if (status != null) {
					staName = HandleType.getNameByCode(status);
				} else {
					staName = "";
				}
			}
			map.put("success", "true");
			map.put("errName", errName);
			map.put("problemId", proName);
			map.put("errorUrl", errorUrl);
			map.put("description", description);
			map.put("transactUnit", transactUnit);
			map.put("transactResult", transactResult);
			map.put("status", staName);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("errorMsg", "查询专属页面曝光台数据异常！");
			writerPrint(JSONObject.fromObject(map).toString());

		}
	}

	/**
	 * 
	 * @描述:获取地图数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月3日下午7:06:04
	 */
	public void getMapInfoList(){
		String orgSiteCode = request.getParameter("orgSiteCode");
		Map<String, Object> map = new HashMap<String, Object>();
		List<StatisticsSiteChannel> staList = new ArrayList<StatisticsSiteChannel>();
		ArrayList<Object> list = new ArrayList<Object>();
		StatisticsSiteChannelRequest req = new StatisticsSiteChannelRequest();
		try {
			Integer siteNum = null;  // 总网站数
			Integer normalNum = null;  // 正常网站数
			Integer shutSiteNum = null;  // 关停网站数
			Integer exceptSiteNum = null;  // 例外网站数
			
			req.setIsBm(0); // 是否部委 0
			req.setIsBigdata(DatabaseTreeInfoType.ISBIGDATA.getCode()); // 是否大数据关系
			req.setOrgSiteCode(orgSiteCode);
			req.setTallyDate(DateUtils.getYesterdayStr()); // 昨天
			staList = statisticsSiteChannelServiceImpl.getMapInfoList(req);
			if(CollectionUtils.isNotEmpty(staList)){
				for (StatisticsSiteChannel sta : staList) {
					HashMap<String, Object> staMap = new HashMap<String, Object>();
					staMap.put("siteCode", sta.getSiteCode());
					staMap.put("name", sta.getOrgName());
					siteNum = sta.getSiteNum();
					shutSiteNum = sta.getShutSiteNum();
					exceptSiteNum = sta.getExceptSiteNum();
					if(siteNum != null && shutSiteNum != null && exceptSiteNum != null){
						normalNum = siteNum - shutSiteNum - exceptSiteNum;
					} else {
						normalNum = 0;
					}
					staMap.put("normalNum", normalNum);
					staMap.put("shutSiteNum", shutSiteNum);
					staMap.put("exceptSiteNum", exceptSiteNum);
					list.add(staMap);
				}
			}
			map.put("success", "true");
			map.put("body", list);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("errorMsg", "查询专属页面地图数据异常！");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
}
