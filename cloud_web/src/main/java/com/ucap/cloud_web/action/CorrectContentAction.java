package com.ucap.cloud_web.action;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.CorrectType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.constant.TrueOrFalseType;
import com.ucap.cloud_web.dto.CorrectContentDetailRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.WebsiteInfoRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.CorrectContentDetail;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.service.ICorrectContentDetailService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.MonitoringCacheUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;





/**<p>Description: 内容正确性</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: AccuracyOfContentAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：zhurk </p>
 * <p>@date：2015-11-25下午3:03:14 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CorrectContentAction extends BaseAction {
	private static Logger logger = Logger.getLogger(CorrectContentAction.class);
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private ICorrectContentDetailService correctContentDetailServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private DicUtils dicUtils;
 
	private Map<String, Object> returnMap=null;
 

	/************************************* 日常监测-疑似错别字页面 开始********************************************/
	/**
	 * 
	 * @描述:跳转疑似错别字页面--组织单位
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月20日下午2:51:41
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
	 * @描述:疑似错别字数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月21日上午11:00:11
	 */
	@SuppressWarnings("unchecked")
	public void correctContentTable() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String correctType = request.getParameter("correctType"); // 错误类型
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间

		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			List<ContractInfo> crmlist = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
			if (CollectionUtils.isEmpty(crmlist)) {
				resultMap.put("success", "other");
			} else {
				Integer homeUrlNum = 0; // 首页url总数
				Integer problemNum = 0; // 问题总数
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("orgSiteCode", siteCode);
				hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
				hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
				if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
					hashMap.put("siteType", siteType);
				}
				if (StringUtils.isNotEmpty(correctType) && !correctType.equals("0")) {
					hashMap.put("correctType", correctType);
				}
				hashMap.put("startDate", startDate);
				hashMap.put("endDate", endDate);

				String cacheCorrectContent = CacheType.getNameByCode("9");
				String conkey = cacheCorrectContent + siteCode + startDate + endDate + correctType + siteType; // 缓存名
				List<CorrectContentDetailRequest> correctList = (List<CorrectContentDetailRequest>) MonitoringCacheUtils
						.get(conkey); // 查询缓存中是否存在
				if (correctList == null) {
					correctList = correctContentDetailServiceImpl.getCorrectContentList(hashMap);
					MonitoringCacheUtils.put(conkey, correctList); // 将数据存到缓存中
				}

				if (correctList != null && correctList.size() > 0) {
					for (CorrectContentDetailRequest correct : correctList) {
						Map<String, Object> item = new HashMap<String, Object>();
						item.put("siteCode", correct.getSiteCode());
						item.put("siteName", correct.getSiteName());
						// 跳转url
						String jumpPageUrl = correct.getJumpPageUrl();
						// 如果跳转url为空，取首页URL
						if (StringUtils.isNotEmpty(jumpPageUrl)) {
							item.put("url", jumpPageUrl);
						} else {
							if (StringUtils.isNotEmpty(correct.getHomePageUrl())) {
								item.put("url", correct.getHomePageUrl());
							} else {
								item.put("url", "");
							}
						}
						// 每个网站对应的内容不正确性个数
						item.put("questionCount", correct.getWrongNum());
						items.add(item);
						homeUrlNum++;
						problemNum += correct.getWrongNum();
					}
				}
				resultMap.put("body", items);
				resultMap.put("homeUrlNum", homeUrlNum);
				resultMap.put("problemNum", problemNum);
				resultMap.put("size", correctList.size()); // 总条数
				resultMap.put("success", "true");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询日常监测-疑似错别字数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}

	}

	/**
	 * 
	 * @描述:疑似错别字excel导出
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月21日下午2:50:24
	 */
	@SuppressWarnings("unchecked")
	public void correctContentTableExcel() {
		String siteType = request.getParameter("siteType"); // 网站类别
		String correctType = request.getParameter("correctType"); // 错误类型
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			Object[] obj1 = new Object[] { "序号", "网站标识码", "网站名称", "网站URL", "问题个数" };
			list.add(obj1);
			String fileName = "日常监测-疑似错别字(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "疑似错别字";

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			if (StringUtils.isNotEmpty(correctType) && !correctType.equals("0")) {
				hashMap.put("correctType", correctType);
			}
			hashMap.put("startDate", startDate);
			hashMap.put("endDate", endDate);
			// 根据网站标识码集合、扫描时间
			String cacheCorrectContentExcel = CacheType.getNameByCode("10");
			String conkey = cacheCorrectContentExcel + siteCode + startDate + endDate + correctType + siteType; // 缓存名
			List<CorrectContentDetailRequest> correctList = (List<CorrectContentDetailRequest>) MonitoringCacheUtils
					.get(conkey); // 查询缓存中是否存在
			if (correctList == null) {
				correctList = correctContentDetailServiceImpl.getCorrectContentList(hashMap);
				MonitoringCacheUtils.put(conkey, correctList); // 将数据存到缓存中
			}

			if (correctList != null && correctList.size() > 0) {
				for (int i = 0; i < correctList.size(); i++) {
					CorrectContentDetailRequest correct = correctList.get(i);
					Object[] obj = new Object[5];
					obj[0] = i + 1;
					obj[1] = correct.getSiteCode();
					if (StringUtils.isNotEmpty(correct.getSiteName())) {
						obj[2] = correct.getSiteName();
					} else {
						obj[2] = "";
					}
					String jumpPageUrl = correct.getJumpPageUrl();
					if (StringUtils.isNotEmpty(jumpPageUrl)) {
						obj[3] = CommonUtils.setHttpUrl(jumpPageUrl); // 判断网址前缀
					} else {
						obj[3] = CommonUtils.setHttpUrl(correct.getHomePageUrl()); // 判断网址前缀
					}
					obj[4] = correct.getWrongNum();
					list.add(obj);
				}
			}
			ExportExcel.organNotUpdateOrOtherExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/************************************* 日常监测-疑似错别字页面 结束********************************************/

	/**
	 * @Description: 内容正确性初始化页面
	 * @author cuichx --- 2016-1-25下午5:07:30
	 * @return
	 */
	public String index() {
		
		returnMap=new HashMap<String, Object>();
		
		//siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		
		if(StringUtils.isNotEmpty(siteCode)){
			setCurrentShiroUser(siteCode);
		}
		
		
		//从session中获取10位填报单位网站标识码
		siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		try {
			//开始日期
			String startDate = DateUtils.getNextDay(new Date(), -7);
			//结束日期
			String endDate = DateUtils.getNextDay(new Date(), -1);
			returnMap.put("beginScanDate", startDate);//开始日期
			returnMap.put("endScanDate", endDate);//结束日期
			WebsiteInfoRequest siteRequest=new WebsiteInfoRequest();
			siteRequest.setNowTime(DateUtils.formatStandardDateTime(new Date()));//用于唯一确定网站
			siteRequest.setSiteCode(siteCode);
			/**老合同信息**/
			List<ContractInfo> contractList = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
			if (contractList != null && contractList.size() > 0) {
				String serviceBeginDate = DateUtils.formatStandardDate(contractList.get(0).getContractBeginTime());
				returnMap.put("serviceBeginDate", serviceBeginDate);
			}
			/** 新产品信息 **/
			// String serviceBeginDate = null;
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// List<CrmProductsResponse> crmList = getCrmProductsList(siteCode,
			// productTypeArr,
			// DateUtils.formatStandardDate(new Date()), null, null);
			// if (CollectionUtils.isNotEmpty(crmList)) {
			// serviceBeginDate = crmList.get(0).getBeginTime();
			// }
			// returnMap.put("serviceBeginDate", serviceBeginDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	/** @Description: 内容正确性折线图
	 * @author zhurk --- 2015-11-26上午9:28:08                
	*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void correctContentLine(){
		Map<String,String> dayMap=new HashMap<String, String>();
		List<Object> resultList=new ArrayList<Object>();
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		try{
			//获取昨天的日期
			String endScanDate=DateUtils.getYesterdayStr();
			//获取前90天时间的数据
			String beginScanDate=DateUtils.getNextDay(DateUtils.parseStandardDate(endScanDate), -90);
			String dayStr=DateUtils.getTwoDay(endScanDate, beginScanDate);
			if(StringUtils.isNotEmpty(dayStr)){
				Integer dayInt=Integer.valueOf(dayStr);
				for(int i=0;i<=dayInt;i++){
					String dateStr=DateUtils.getNextDay(new Date(), -i);
					dayMap.put(dateStr, dateStr);
				}
			}
			
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("siteCode", siteCode);
			hashMap.put("endScanDate", endScanDate);
			hashMap.put("beginScanDate", beginScanDate);
//			hashMap.put("correctTypeIn", "1,3");
			hashMap.put("exposure", TrueOrFalseType.TRUE.getCode());//是否曝光
			//List<CorrectContentInfoRequest> queryList=correctContentInfoServiceImpl.getCorrectLine(hashMap);
			List<CorrectContentDetailRequest> correctList= correctContentDetailServiceImpl.queryCorrectLine(hashMap);
			for (int i = 0; i < correctList.size(); i++) {
				CorrectContentDetailRequest correctContent=correctList.get(i);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("wrongNum", correctContent.getWrongNum());//问题总数
				map.put("scanDate", correctContent.getScanDate());//日期
				//移除掉存在的日期
				dayMap.remove(correctContent.getScanDate());
				resultList.add(map);
			}
			if(dayMap.size()>0){
				for (String key : dayMap.keySet()) {  
					HashMap<String, Object> map = new HashMap<String, Object>();
					String value = dayMap.get(key);
					map.put("wrongNum", 0);//问题总数
					map.put("scanDate", value);//日期
					//移除掉存在的日期
					resultList.add(map);
				}
			}
			List<Object> listSort=new ArrayList<Object>();
			listSort.addAll(resultList);
			//借助commons-collections包的ComparatorUtils   
	       //BeanComparator，ComparableComparator和ComparatorChain都是实现了Comparator这个接口   
	        Comparator mycmp = ComparableComparator.getInstance();      
	        mycmp = ComparatorUtils.nullLowComparator(mycmp);  //允许null
//	        if(isAsc){
//	        mycmp = ComparatorUtils.reversedComparator(mycmp); //逆序      
//	        }
	        Comparator cmp = new BeanComparator("scanDate", mycmp);     
	        Collections.sort(listSort, cmp); 
			logger.info("correctContentLine resultList:"+listSort);
			writerPrint(JSONArray.fromObject(listSort).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("内容正确性折线图  :"+"siteCode"+siteCode);
		}
	}
	/** @Description: 内容正确性分页
	 * @author zhurk --- 2015-11-25下午3:33:17                
	*/
	public void correctContentPage() {
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		// 从session中获取10位填报单位网站标识码
		String siteCode = getCurrentUserInfo().getChildSiteCode();
		if (StringUtils.isEmpty(siteCode)) {
			siteCode = getCurrentUserInfo().getSiteCode();
		}
		
		try {
			String beginScanDate = request.getParameter("beginScanDate");//开始日期
			String endScanDate = request.getParameter("endScanDate");//结束日期
		//	String correctTypeStr=request.getParameter("correctType");//内容正确性类型
			
			String selectType=request.getParameter("selectType");
			String key = request.getParameter("key");// 获取关键字查询的关键字
			String pos = request.getParameter("pos");//页码
			String size = request.getParameter("size");//每页条数
			
			logger.info("correctContentPage beginScanDate:"+beginScanDate+" endScanDate:"+endScanDate+" selectType:"+selectType+" key:"+key+" pos:"+pos+" size:"+size);
			
			//将查询条件封装到dto对象中
			CorrectContentDetailRequest contentRequest = new CorrectContentDetailRequest();
			contentRequest.setSiteCode(siteCode);
			
			
			if(StringUtils.isNotEmpty(beginScanDate) && StringUtils.isNotEmpty(endScanDate)){
				contentRequest.setBeginScanDate(beginScanDate);
				contentRequest.setEndScanDate(endScanDate);
			}else{
				contentRequest.setBeginScanDate(DateUtils.getNextDay(new Date(), -7));
				contentRequest.setEndScanDate(DateUtils.getYesterdayStr());
			}
			contentRequest.setExposure(TrueOrFalseType.TRUE.getCode());//是否曝光
/*			if(StringUtils.isEmpty(correctTypeStr)){
				int[] correctTypeArray={CorrectType.SERIOUS.getCode(),CorrectType.COMMON.getCode()};
				contentRequest.setCorrectTypeArray(correctTypeArray);
			}*/
			if (StringUtils.isNotEmpty(key)) {
				contentRequest.setKeyWord(key);
			}
			if (StringUtils.isNotEmpty(pos)) {
				contentRequest.setPageNo(Integer.parseInt(pos));
			}
			if (StringUtils.isNotEmpty(size)) {
				contentRequest.setPageSize(Integer.parseInt(size));
			}
			if(StringUtils.isNotEmpty(selectType)){
				contentRequest.setCorrectType(Integer.valueOf(selectType));
			}else{
//				contentRequest.setNotType("2");
			}
			List<Object> list=new ArrayList<Object>();
			
			//添加默认排序字段---问题类型倒排序
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			contentRequest.setQueryOrderList(querySiteList);
			
			//分页查询
			PageVo<CorrectContentDetail> query = correctContentDetailServiceImpl.query(contentRequest);
			List<CorrectContentDetail> data = query.getData();
			if (data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					CorrectContentDetail correctContentDetail = data.get(i);
					int beginNum=(Integer.valueOf(pos)-1) * Integer.valueOf(size);
					map.put("dataNumber", beginNum + i+1);
					Integer correctType = correctContentDetail
							.getCorrectType();
					for (CorrectType correctTypes : CorrectType.values()) {
						if (correctType == correctTypes.getCode()) {
							map.put("errorType", correctTypes.getName());
						}
					}
					
					//问题描述   格式： “把推荐词(多个推荐词之间用|分隔) 写成了 错误词汇”
					
					//错误词汇
					if(StringUtils.isNotEmpty(correctContentDetail.getWrongTerm())){
						map.put("wrongTerm", correctContentDetail.getWrongTerm());
						
					}else{
						map.put("wrongTerm", "");
					}
					
					//推荐词汇
					if(StringUtils.isNotEmpty(correctContentDetail.getExpectTerms())){
						map.put("expectTerms", correctContentDetail.getExpectTerms());
						
					}else{
						map.put("expectTerms", "");
					}
					

					map.put("url", correctContentDetail.getUrl());
					String imgUrl=correctContentDetail.getImgUrl();
					
					
					if (correctContentDetail.getOperator() == -1) {
						// 机器扫描
						DatabaseInfoRequest databaseInfoRequest=new DatabaseInfoRequest();
						databaseInfoRequest.setSiteCode(siteCode);
						List<DatabaseInfo> databaseList=databaseInfoServiceImpl.queryList(databaseInfoRequest);
						if(databaseList!=null && databaseList.size()>0){
							DatabaseInfo databaseInfo=databaseList.get(0);
							if (org.apache.commons.lang3.StringUtils.isNotBlank(correctContentDetail.getUrl())) {
							map.put("imgUrl", urlAdapterVar.getWronglyImg() + "encodeurl=" + databaseInfo.getEncodeUrl()
									+ "&url=" + correctContentDetail.getUrl());
							}else {
								map.put("imgUrl", "");
							}
						}
					} else {
						// 人工添加
						if (org.apache.commons.lang3.StringUtils.isNotBlank(imgUrl)) {
							map.put("imgUrl", correctContentDetail.getUrl());
							//map.put("imgUrl", urlAdapterVar.getLinkUrlReport() + imgUrl);
						} else {
							map.put("imgUrl", "");
						}
					}
					
					map.put("operator", correctContentDetail.getOperator());

					list.add(map);
				}
			}
			resultMap.put("body", list);
			resultMap.put("totalRecords", query.getRecordSize());
			resultMap.put("iTotalDisplayRecords", query.getRecordSize());
			resultMap.put("hasMoreItems", true);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("内容正确性分页查询    :" + "siteCode：" + siteCode);
		}
	}
	/** @Description: 内容正确性监测结果导出Excel
	 * @author zhurk --- 2015-11-25下午6:06:23                
	*/
	public  void correctContentExcel(){
		
		
//		String scanDate =request.getParameter("scanDate");
		String beginScanDate = request.getParameter("beginScanDate");//开始日期
		String endScanDate = request.getParameter("endScanDate");//结束日期
		String selectType=request.getParameter("selectType");
		String key = request.getParameter("key");// 获取关键字查询的关键字
		
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj1 = new Object[]{"序号","错误类型","疑似错误","推荐修改","网页URL","定位"};
		list.add(obj1);
		String fileName = "错别字监测结果("+beginScanDate+"至"+endScanDate+").xls";
		String title = "错别字监测结果"; 
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		
		//将查询条件封装到dto对象中
		CorrectContentDetailRequest contentRequest = new CorrectContentDetailRequest();
		contentRequest.setSiteCode(siteCode);
		
		if(StringUtils.isNotEmpty(beginScanDate) && StringUtils.isNotEmpty(endScanDate)){
			contentRequest.setBeginScanDate(beginScanDate);
			contentRequest.setEndScanDate(endScanDate);
		}else{
			contentRequest.setBeginScanDate(DateUtils.getNextDay(new Date(), -7));
			contentRequest.setEndScanDate(DateUtils.getYesterdayStr());
		}
		if(StringUtils.isNotEmpty(selectType)){
			contentRequest.setCorrectType(Integer.valueOf(selectType));
		}
		if(StringUtils.isNotEmpty(key)){
			contentRequest.setKeyWord(key);
		}
		contentRequest.setPageSize(Integer.MAX_VALUE);
		
		try {
			List<CorrectContentDetail> queryList=correctContentDetailServiceImpl.queryList(contentRequest);
			if(queryList.size()>0){
				for (int i = 0; i < queryList.size(); i++){
					CorrectContentDetail correctContentDetail=queryList.get(i);
					Object[] obj = new Object[6];
					obj[0]=i+1;
					
					//出来内容正确性类型为空的情况
					Integer correctType = correctContentDetail.getCorrectType();
					if(correctType!=null){
						for (CorrectType correctTypes : CorrectType.values()) {
							if(correctType==correctTypes.getCode()){
								obj[1]=correctTypes.getName();
							}
						}
					}else{
						obj[1]="";
					}

					
					//错误词汇
					if(StringUtils.isNotEmpty(correctContentDetail.getWrongTerm())){
						obj[2]=correctContentDetail.getWrongTerm();
					}else{
						obj[2]="";
					}
					
					//推荐词汇
					if(StringUtils.isNotEmpty(correctContentDetail.getExpectTerms())){
						obj[3]=correctContentDetail.getExpectTerms();
					}else{
						obj[3]="";
					}
					
					if(StringUtils.isNotEmpty(correctContentDetail.getUrl())){
						obj[4]=correctContentDetail.getUrl();
					}else{
						obj[4]="";
					}
					
					
/*					String imgUrl=correctContentDetail.getImgUrl();
					if(StringUtils.isNotEmpty(imgUrl)){
						//截图和快照前面都需要添加路径  截图加本项目访问路径
						if(imgUrl.startsWith("htm")){
							obj[5]=urlAdapterVar.getImgUrl()+imgUrl;
						}else{
							String path=webPath;
							String appPath=request.getContextPath();
							obj[5]=path+appPath+imgUrl;
						}
					}else{
						obj[5]="无";
					}*/
					
					if (correctContentDetail.getOperator() == -1) {
						// 机器扫描
						DatabaseInfoRequest databaseInfoRequest=new DatabaseInfoRequest();
						databaseInfoRequest.setSiteCode(siteCode);
						List<DatabaseInfo> databaseList=databaseInfoServiceImpl.queryList(databaseInfoRequest);
						if(databaseList!=null && databaseList.size()>0){
							DatabaseInfo databaseInfo=databaseList.get(0);
							obj[5]= urlAdapterVar.getWronglyImg() + "encodeurl=" + databaseInfo.getEncodeUrl()
									+ "&url=" + correctContentDetail.getUrl();
						}
					} else {
						// 人工添加
						obj[5]= urlAdapterVar.getLinkUrl()+correctContentDetail.getImgUrl();
					}
					list.add(obj);
				}
			}
			ExportExcel.correctContentExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("错别字监测结果Excel导出："+"siteCode:"+siteCode);
		}
		
	}
	public Map<String, Object> getReturnMap() {
		return returnMap;
	}
	public void setReturnMap(Map<String, Object> returnMap) {
		this.returnMap = returnMap;
	}
}
