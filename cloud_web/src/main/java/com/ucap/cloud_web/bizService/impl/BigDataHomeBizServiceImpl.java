package com.ucap.cloud_web.bizService.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.bizService.BigDataHomeBizService;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.dto.BigOrgDailyRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.DetectionResultRequest;
import com.ucap.cloud_web.dto.DicConfigRequest;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.JcVisitOrgRequest;
import com.ucap.cloud_web.dto.MTaskoverviewRequest;
import com.ucap.cloud_web.dtoResponse.BigOrgDailyResponse;
import com.ucap.cloud_web.entity.BigOrgDaily;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DetectionResult;
import com.ucap.cloud_web.entity.DicConfig;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.entity.JcVisitOrg;
import com.ucap.cloud_web.entity.MTaskoverview;
import com.ucap.cloud_web.service.IBigOrgDailyService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDetectionResultService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.service.IEarlyDetailService;
import com.ucap.cloud_web.service.IJcVisitOrgService;
import com.ucap.cloud_web.service.IMTaskoverviewService;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.FileUtils;

@Service
public class BigDataHomeBizServiceImpl implements BigDataHomeBizService {

	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;

	@Autowired
	private IEarlyDetailService earlyDetailServiceImpl;

	@Autowired
	private IMTaskoverviewService MTaskoverviewServiceImpl;

	@Autowired
	private IDetectionResultService detectionResultServiceImpl;

	@Autowired
	private UrlAdapterVar urlAdapterVar;

	@Autowired
	private DatabaseBizService databaseBizServiceImpl;

	@Autowired
	private IDicConfigService dicConfigServiceImpl;

	@Autowired
	private IJcVisitOrgService jcVisitOrgServiceImpl;

	@Autowired
	private IBigOrgDailyService bigOrgDailyServiceImpl;
	@Autowired
	private DicUtils dicUtils;
	// 以下为json生成与返回对应的节点

	// 健康指数
	public static String INDEXCOUNT = "indexCount";

	// 健康指数折线图
	public static String INDEXCOUNT_LINE = "indexCountLine";

	// 播报信息
	public static String BROADCAST = "broadcast";

	// 播报信息：数组
	public static String BROADCAST_LIST = "broadcastList";

	// 组织单位下各状态站点分布情况（正常，关停，例外），以及监测站点数
	public static String SITE_STATE = "siteState";

	// 网站监测情况：全国监测页面数量,全国发现问题数量等
	public static String SCAN_PROBLE_NUM = "scanProblemNum";

	// 组织单位用户访问量
	public static String JCVISITORG = "jcVisitOrg";

	// 组织单位下各市连通率和组织单位监测情况
	public static String CONNE_DAILYSCAN = "connectionAndDailyScan";

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void createBigDataHomeJson() {

//		String bmSiteCode = "bm0100";
//
//		// 1.生成bm0100大数据首页数据
//		createBigHomeJsonData(bmSiteCode);
//
//		// 2.生成bm0100下省级+部委组织单位
//		DatabaseTreeInfoRequest request = new DatabaseTreeInfoRequest();
//		request.setOrgSiteCode(bmSiteCode);
//		request.setIsOrg("1");
//		request.setIsBigdata(1);
//		request.setPageSize(Integer.MAX_VALUE);
//		List<DatabaseTreeInfo> listDatabaseTreeInfo = databaseTreeInfoServiceImpl
//				.queryList(request);
		String siteCodes1 ="bm0100,110000,120000,310000,500000,310200,429000,522200,522400,130100,130200,130300,130400,130500,130600,130700,130800,130900,131000,131100,140100,140200,140300,140400,140500,140600,140700,140800,140900,141000,141100,150100,150200,150300,150400,150500,150600,150700,150800,150900,152200,152500,152900,210100,210200,210300,210400,210500,210600,210700,210800,210900,211000,211100,211200,211300,211400,220100,220200,220300,220400,220500,220600,220700,220800,222400,230100,230200,230300,230400,230500,230600,230700,230800,230900,231000,231100,231200,232700,320100,320200,320300,320400,320500,320600,320700,320800,320900,321000,321100,321200,321300,330100,330200,330300,330400,330500,330600,330700,330800,";
		String siteCodes2 ="330900,331000,331100,340100,340200,340300,340400,340500,340600,340700,340800,341000,341100,341200,341300,341500,341600,341700,341800,350100,350200,350300,350400,350500,350600,350700,350800,350900,360100,360200,360300,360400,360500,360600,360700,360800,360900,361000,361100,370100,370200,370300,370400,370500,370600,370700,370800,370900,371000,371100,371200,371300,371400,371500,371600,371700,410100,410200,410300,410400,410500,410600,410700,410800,410900,411000,411100,411200,411300,411400,411500,411600,411700,469000,420100,420200,420300,420500,420600,420700,420800,420900,421000,421100,421200,421300,422800,430100,430200,430300,430400,430500,430600,430700,430800,430900,431000,431100,431200,431300,";
		String siteCodes3 ="512000,513200,513300,513400,520100,520200,520300,520400,522300,522600,522700,530100,530300,530400,530500,530600,530700,530800,530900,532300,532500,532600,532800,532900,533100,533300,533400,540100,542100,542200,542300,542400,542500,542600,610100,610200,610300,610400,610500,610600,610700,610800,610900,611000,433100,440100,440200,440300,440400,440500,440600,440700,440800,440900,441200,441300,441400,441500,441600,441700,441800,441900,442000,445100,445200,445300,450100,450200,450300,450400,450500,450600,450700,450800,450900,451000,451100,451200,451300,451400,460100,460200,460300,510100,510300,510400,510500,510600,510700,510800,510900,511000,511100,511300,511400,511500,511600,511700,511800,511900,";
		String siteCodes4 ="620100,620200,620300,620400,620500,620600,620700,620800,620900,621000,621100,621200,622900,623000,630100,632100,632200,632300,632500,632600,632700,632800,640100,640200,640300,640400,640500,650100,650200,652100,652200,652300,652700,652800,652900,653000,653100,653200,654000,654200,654300,659000,710000,810100,820000,130000,140000,150000,210000,220000,230000,320000,330000,340000,350000,360000,370000,410000,420000,430000,440000,450000,460000,510000,520000,530000,540000,610000,620000,630000,640000,650000";
		String site = siteCodes1+siteCodes2+siteCodes3+siteCodes4;
		String[] s = site.split(",");
		for (int i=0;i<s.length;i++) {
			createBigHomeJsonData(s[i]);
		}

	}

	@Override
	public void createBigHomeJsonData(String orgSiteCode) {

		Map<Object, Object> data = new HashMap<Object, Object>();

		// 1.1 大数据组织单位健康指数
		data.put(INDEXCOUNT, getIndexCount(orgSiteCode));

		// 1.2 健康指数折线图
		data.put(INDEXCOUNT_LINE, getIndexCountLine(orgSiteCode));

		// 2.问题实时播报
		Map<Object, Object> dataEarlyDetail = new HashMap<Object, Object>();
		List<EarlyDetail> listEarlyDetail = getBroadcastSecurity(orgSiteCode);
		dataEarlyDetail.put(BROADCAST_LIST, listEarlyDetail);

		data.put(BROADCAST, dataEarlyDetail);

		// 3.获取组织单位下各状态站点分布情况（正常，关停，例外），以及监测站点数
		data.put(SITE_STATE, getSiteStateInfo(orgSiteCode));

		// 4.网站监测情况：全国监测页面数量,全国发现问题数量
		data.put(SCAN_PROBLE_NUM, getAllScanProblemNum());

		// 5.门户网站用户访问量
		data.put(JCVISITORG, getJcVisitOrg_Seven(orgSiteCode));

		// 6.获取组织单位下各市连通率和组织单位监测情况
		// 组织单位监测情况包含以下：首页连通率 ( 一周 ),首页正常更新率 ( 两周 ),首页不可用链接数( 昨天 ), 内容更新总量 ( 昨天
		// ) ,首页更新总量 ( 昨天 )
		data.put(CONNE_DAILYSCAN, getConnectionAndDailyScan(orgSiteCode));

		// 7.生成json文件
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray
				.fromObject(data);

		// 大数据首页文件输出路径
		String filePath = urlAdapterVar.getBigHomeJsonUrl() + "/" + orgSiteCode
				+ ".json";

		if (!FileUtils.fileExists(filePath)) {
			FileUtils.createDir(filePath);
		}
		try {

			FileUtils.writeToJson(filePath, jsonArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<EarlyDetail> getBroadcastSecurity(String orgSiteCode) {

		try {
			DatabaseTreeInfoRequest dRequest = new DatabaseTreeInfoRequest();
			dRequest.setSiteCode(orgSiteCode);
			List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl
					.queryList(dRequest);

			if (CollectionUtils.isEmpty(dList)) {
				return null;
			}

			DatabaseTreeInfo databaseTreeInfo = dList.get(0);

			String code = databaseTreeInfo.getCode();

			EarlyDetailRequest eRequest = new EarlyDetailRequest();
			eRequest.setYesDay(queryHomePageDate());
			eRequest.setCode(code);
			if (!"bm0100".equalsIgnoreCase(orgSiteCode)) {
				eRequest.setSiteCode(orgSiteCode);
			}

			// 1.从detection_result 汇总数据中查出的问题汇总
			List<EarlyDetail> list = earlyDetailServiceImpl
					.queryDatas(eRequest);

			if (CollectionUtils.isEmpty(list)) {
				return null;
			}

			// 用于返回封装后数据集返回
			List<EarlyDetail> listNewEarlyDetail = new ArrayList<EarlyDetail>();
			// 横向数据转成竖向(封装),且数量大于0的都算作一个问题，比如：空白栏目数量securityBlank=3,实际算作一个问题
			EarlyDetail earlyDetailNew = null;
			for (EarlyDetail earlyDetail : list) {

				if (earlyDetail.getSecurityBlank() > 0) {
					earlyDetailNew = new EarlyDetail();
					earlyDetailNew.setRemark("有空白栏目");
					earlyDetailNew.setScanTime(formatDateToMinute(earlyDetail
							.getScanTime()));
					earlyDetailNew.setSiteCodeName(earlyDetail
							.getSiteCodeName());
					listNewEarlyDetail.add(earlyDetailNew);
				}

				if (earlyDetail.getSecurityService() > 0) {
					earlyDetailNew = new EarlyDetail();
					earlyDetailNew.setRemark("服务不实用");
					earlyDetailNew.setScanTime(formatDateToMinute(earlyDetail
							.getScanTime()));
					earlyDetailNew.setSiteCodeName(earlyDetail
							.getSiteCodeName());
					listNewEarlyDetail.add(earlyDetailNew);
				}

				if (earlyDetail.getSecurityResponse() > 0) {
					earlyDetailNew = new EarlyDetail();
					earlyDetailNew.setRemark("互动回应差");
					earlyDetailNew.setScanTime(formatDateToMinute(earlyDetail
							.getScanTime()));
					earlyDetailNew.setSiteCodeName(earlyDetail
							.getSiteCodeName());
					listNewEarlyDetail.add(earlyDetailNew);
				}

				if (earlyDetail.getSecurityBasic() > 0) {
					earlyDetailNew = new EarlyDetail();
					earlyDetailNew.setRemark("信息不更新");
					earlyDetailNew.setScanTime(formatDateToMinute(earlyDetail
							.getScanTime()));
					earlyDetailNew.setSiteCodeName(earlyDetail
							.getSiteCodeName());
					listNewEarlyDetail.add(earlyDetailNew);
				}

				if (earlyDetail.getSecurityFatalError() > 0) {
					earlyDetailNew = new EarlyDetail();
					earlyDetailNew.setRemark("严重错误");
					earlyDetailNew.setScanTime(formatDateToMinute(earlyDetail
							.getScanTime()));
					earlyDetailNew.setSiteCodeName(earlyDetail
							.getSiteCodeName());
					listNewEarlyDetail.add(earlyDetailNew);
				}

				if (earlyDetail.getSecurityQuestion() > 0) {
					earlyDetailNew = new EarlyDetail();
					earlyDetailNew.setRemark("安全问题");
					earlyDetailNew.setScanTime(formatDateToMinute(earlyDetail
							.getScanTime()));
					earlyDetailNew.setSiteCodeName(earlyDetail
							.getSiteCodeName());
					listNewEarlyDetail.add(earlyDetailNew);
				}
			}

			return listNewEarlyDetail;

		} catch (Exception e) {
			logger.error("大数据分析-大数据首页-查询-获取内容保障播报异常,errorMsg:" + e.getMessage());
			return null;
		}

	}

	@Override
	public List<EarlyDetail> getBroadcastConnection(String orgSiteCode) {

		try {
			DatabaseTreeInfoRequest dRequest = new DatabaseTreeInfoRequest();
			dRequest.setSiteCode(orgSiteCode);
			List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl
					.queryList(dRequest);

			if (CollectionUtils.isEmpty(dList)) {
				return null;
			}

			DatabaseTreeInfo databaseTreeInfo = dList.get(0);

			String code = databaseTreeInfo.getCode();

			EarlyDetailRequest eRequest = new EarlyDetailRequest();
			eRequest.setToDay(DateUtils.getTodayStandardStr());
			eRequest.setTomDay(DateUtils.getNextDay(new Date(), 1));
			eRequest.setCode(code);

			return earlyDetailServiceImpl.queryNoConDatas(eRequest);
		} catch (Exception e) {
			logger.error("大数据分析-大数据首页-查询-获取连通性播报异常,errorMsg:" + e.getMessage());
			return null;
		}

	}

	@Override
	public JSONObject packageIndexCount(String orgSiteCode) {
		// 封装返回数据
		// 从json中获取
		JSONObject objectBroadcast = parseBySiteCodeAndKey(orgSiteCode,
				INDEXCOUNT);

		return objectBroadcast;
	}

	@Override
	public JSONObject packageIndexCountLine(String orgSiteCode) {
		// 封装返回数据
		// 从json中获取
		JSONObject object = parseBySiteCodeAndKey(orgSiteCode, INDEXCOUNT_LINE);

		return object;
	}

	@Override
	public JSONObject packageBroadcastInfo(String orgSiteCode) {
		// 封装返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 1.获取昨日内容保障问题
		// 先从json中获取
		JSONObject objectBroadcast = parseBySiteCodeAndKey(orgSiteCode,
				BROADCAST);
		JSONArray arrayBroadcast = new JSONArray();

		try {
			arrayBroadcast = objectBroadcast
					.getJSONArray(BigDataHomeBizServiceImpl.BROADCAST_LIST);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// 获取实时连通性
		List<EarlyDetail> listEarlyDetail = getBroadcastConnection(orgSiteCode);
		if (!CollectionUtils.isEmpty(listEarlyDetail)) {
			arrayBroadcast.addAll(listEarlyDetail);
		}

		resultMap.put("list", arrayBroadcast);
		resultMap.put("success", "true");

		return JSONObject.fromObject(resultMap);
	}

	@Override
	public JSONObject packageSiteStateInfo(String orgSiteCode) {
		// 封装返回数据
		// 从json中获取
		JSONObject object = parseBySiteCodeAndKey(orgSiteCode, SITE_STATE);

		return object;
	}

	@Override
	public JSONObject packageScanProblemNum(String orgSiteCode) {
		// 封装返回数据
		// 从json中获取
		JSONObject object = parseBySiteCodeAndKey(orgSiteCode, SCAN_PROBLE_NUM);

		return object;
	}

	@Override
	public JSONObject packageJcVisitOrg(String orgSiteCode) {
		// 封装返回数据
		// 从json中获取
		JSONObject object = parseBySiteCodeAndKey(orgSiteCode, JCVISITORG);

		return object;
	}

	@Override
	public JSONObject packageConneDailyScan(String orgSiteCode) {
		// 封装返回数据
		// 从json中获取
		JSONObject object = parseBySiteCodeAndKey(orgSiteCode, CONNE_DAILYSCAN);

		return object;
	}

	public static void main(String[] args) {
		System.out.println(DateUtils.getYesterdayStr());

		System.out.println(DateUtils.getNextDay(new Date(), -1));

		// DateUtils.c
		String scanDate = "2017-03-01";
		// DateUtils.createDate(DateUtils.get, month, date, hours, minutes,
		// seconds)

		System.out.println(DateUtils.getMonthStr(scanDate));

		System.out.println(DateUtils.getDayStr(scanDate));
		// DateUtils.formatStandardFullDateTime(DateUtils.formatStandardDate(""));

		Date date = DateUtils.parseStandardDate(scanDate);
		String a = DateUtils.formatStandardMMDD_HHMMDateTime(date);
		String a1 = DateUtils.formatShortMMDDDate(DateUtils.getNow());
		// String a2=DateUtils.format
		// System.out.println("a1=="+a2);

		// System.out.println(formatDateToMinute("2017-03-01"));

		System.out.println(CommonUtils.getAvgProportion(100.00));
	}

	private String getMTaskDay(Date d, int delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String mdate = "";
			long myTime = d.getTime() / 1000L + (long) (delay * 24 * 60 * 60);
			d.setTime(myTime * 1000L);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 将一个不带时分秒的日期格式（yyyy-mm-dd）,转成带时分秒的格式
	 * 
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1上午11:00:45
	 * @param date
	 * @return
	 */
	private String formatDateToMinute(String date) {
		int shi = (int) (1 + Math.random() * 24);
		int fen = (int) (1 + Math.random() * 60);
		int miao = (int) (1 + Math.random() * 60);
		String shishi = "";
		String fenfen = "";
		String miaomiao = "";
		shishi = CommonUtils.isTen(shi);
		fenfen = CommonUtils.isTen(fen);
		miaomiao = CommonUtils.isTen(miao);
		return date + " " + shishi + ":" + fenfen + ":" + miaomiao;
	}

	@Override
	public Map<String, Object> getIndexCount(String orgSiteCode) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		String healthIndex = null;
		try {

			if (orgSiteCode.equals("bm0100")) {
				// 全国健康指数
				String indexCountAvg = dicConfigServiceImpl.get(10).getValue();
				healthIndex = indexCountAvg;
			} else {
				/** 查询大数据的健康指数 **/
				MTaskoverviewRequest mTaskRequest = new MTaskoverviewRequest();
				String countDay = queryDate(); // 统计时间
				mTaskRequest.setTaskid(orgSiteCode); // 标识码
				mTaskRequest.setCountday(countDay); // 统计时间
				List<MTaskoverview> mTaskList = MTaskoverviewServiceImpl
						.queryList(mTaskRequest);

				if (!CollectionUtils.isEmpty(mTaskList) && mTaskList.size() > 0) {
					MTaskoverview mTask = mTaskList.get(0);
					// 健康指数
					healthIndex = mTask.getHealthindex();

				} else {
					// 健康指数
					resultMap.put("healthIndex", 400);
					resultMap.put("convertScores", 0);
				}

			}

			if (null == healthIndex) {
				healthIndex = "400";
			}

			double convertScores = indexCountToConvertScores(Double
					.parseDouble(healthIndex));
			// 健康指数
			resultMap.put("healthIndex", healthIndex);
			resultMap.put("convertScores", convertScores);

		} catch (Exception e) {
			logger.error("大数据分析-大数据首页-查询-获取健康指数异常,errorMsg:" + e.getMessage());
		}

		return resultMap;
	}

	/**
	 * 健康指数转成折算分数
	 * 
	 * 健康指数=（折算分数-60）/60 *1200+1600
	 * 
	 * 折算分数=（健康指数-1600）/1200 * 60+60
	 * 
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-3下午2:22:13
	 * @param healthIndex
	 * @return
	 */
	private double indexCountToConvertScores(double healthIndex) {

		if (healthIndex == 400) {
			return 0;
		}

		double convertScores = (healthIndex - 1600) / 1200 * 60 + 60;

		if (convertScores < 0) {
			return 0;
		} else {
			return convertScores;
		}

	}

	@Override
	public Map<String, Object> getIndexCountLine(String orgSiteCode) {

		// 组织单位健康指数，门户网站健康指数(如果组织单位是bm0100,则为全国的平均健康指数)，连续7天对比图

		// 返回结果集
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			// 日期数据集合【连续7天】
			List<Object> datelist = new ArrayList<Object>();
			// 门户网站健康指数集合【连续7天】
			List<Object> siteList = new ArrayList<Object>();
			// 组织单位健康指数集合【连续7天】(如果组织单位是bm0100,则为全国的平均健康指数)
			List<Object> allSiteList = new ArrayList<Object>();
			String siteCode = "";// 门户网站标识码
			String siteName = "";// 门户网站名称
			// 获取组织单位的门户 站点
			DatabaseTreeInfoRequest ddRequest = new DatabaseTreeInfoRequest();
			ddRequest.setIsBigdata(1);
			ddRequest.setIsorganizational(1);
			ddRequest.setOrgSiteCode(orgSiteCode);
			ddRequest.setLayerType(1);
			List<DatabaseTreeInfo> listt = databaseTreeInfoServiceImpl
					.getDatabaseTreeInfoList(ddRequest);
			if (listt.size() > 0) {
				siteCode = listt.get(0).getSiteCode();
				siteName = listt.get(0).getName();
			}

			// 获取前7天的开始时间
			String nextDay = DateUtils.getNextDay(queryHomePageDate(), "-6");
			// 获取昨天的日期
			String endDate = DateUtils.getNextDay(queryHomePageDate(), "0");

			// 1.门户网站健康指数(如果组织单位是bm0100,则为全国的平均健康指数)，连续7天健康指数
			// 2.封装对应日期（连续7天）
			// 通过网站标识码、昨天日期查询 检测结果列表
			DetectionResultRequest detectionRequest = new DetectionResultRequest();
			detectionRequest.setSiteCode(siteCode);
			detectionRequest.setBeginScanDate(nextDay);
			detectionRequest.setEndScanDate(endDate);
			detectionRequest.setPageSize(Integer.MAX_VALUE);
			// 排序
			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("scan_date",
					QueryOrderType.ASC);
			queryOrderList.add(siteQueryOrder);
			detectionRequest.setQueryOrderList(queryOrderList);
			List<DetectionResult> detectionList = detectionResultServiceImpl
					.queryList(detectionRequest);

			if (detectionList != null && detectionList.size() > 0) {
				for (DetectionResult detectionResult : detectionList) {
					datelist.add(StringUtils.getPrettyNumber(DateUtils
							.getDayStr(detectionResult.getScanDate())) + "日");// 封装对应日期（连续7天）
					siteList.add(detectionResult.getIndexCount());// 门户网站健康指数(如果组织单位是bm0100,则为全国的平均健康指数)
					if ("bm0100".equals(orgSiteCode)) {
						allSiteList.add(detectionResult.getIndexCountAvg());// 组织单位健康指数，bm0100为全国平均健康指数
					}
				}
			}

			// 3.组织单位健康指数，非bm0100组织单位查询MTaskoverview
			if (!"bm0100".equals(orgSiteCode)) {
				MTaskoverviewRequest mTaskRequest = new MTaskoverviewRequest();
				mTaskRequest.setStartCountday(getMTaskDay(DateUtils.parseStandardDate(queryHomePageDate()), -6));
				mTaskRequest.setEndCountday(getMTaskDay(DateUtils.parseStandardDate(queryHomePageDate()), 0));
				
				mTaskRequest.setTaskid(orgSiteCode); // 标识码
				List<QueryOrder> queryOrderOrgList = new ArrayList<QueryOrder>();
				QueryOrder queryOrder = new QueryOrder("countday",
						QueryOrderType.ASC);
				queryOrderOrgList.add(queryOrder);
				mTaskRequest.setQueryOrderList(queryOrderOrgList);
				mTaskRequest.setPageSize(7);// 此处查询有问题
				List<MTaskoverview> mTaskList = MTaskoverviewServiceImpl
						.queryList(mTaskRequest);
				if (CollectionUtils.isNotEmpty(mTaskList) && mTaskList.size() > 0) {
					for (MTaskoverview mTaskoverview : mTaskList) {
						allSiteList.add(mTaskoverview.getHealthindex());// 健康指数
					}
				}
			}
			resultMap.put("siteName", siteName);// 当前登录的组织单位的门户网站名称
			resultMap.put("datelist", datelist);// 日期数据集合【连续7天】
			resultMap.put("siteList", siteList);// 门户网站健康指数集合【连续7天】
			resultMap.put("allSiteList", allSiteList);// 组织单位健康指数集合【连续7天】(如果组织单位是bm0100,则为全国的平均健康指数)

		} catch (Exception e) {

			logger.error("大数据分析-大数据首页-查询-获取健康指数折线异常,errorMsg:" + e.getMessage());
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> getConnectionAndDailyScan(String orgSiteCode) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 获取组织单位下各市连通率和组织单位监测情况

		// 组织单位监测情况包含以下：
		// 首页连通率 ( 一周 ),首页正常更新率 ( 两周 ),首页不可用链接数( 昨天 ), 内容更新总量 ( 昨天 ) ,首页更新总量 (
		// 昨天 )
		try {
			// 1.组织单位下各市连通率
			// 如果是bm0100，只获取底下所有的省31个（去掉部委的和BT0000）

			String scanDate = getMTaskDay(DateUtils.parseStandardDate(queryHomePageDate()), 0);
			BigOrgDailyRequest rRequest = new BigOrgDailyRequest();
			rRequest.setOrgSiteCode(orgSiteCode);
			rRequest.setCountDay(scanDate);
			if (orgSiteCode.equals("bm0100")) {
				rRequest.setIsBm(0);
				rRequest.setSiteCodeNot("BT0000");
			}
			List<BigOrgDailyResponse> listBigOrgDailyResponse = bigOrgDailyServiceImpl
					.getOrgData(rRequest);

			resultMap.put("body", listBigOrgDailyResponse);

			// 2.查询组织单位大数据监测情况
			// 首页连通率 ( 一周 ),首页正常更新率 ( 两周 ),首页不可用链接数( 昨天 ), 内容更新总量 ( 昨天 ) ,首页更新总量
			// (// 昨天 )
			BigOrgDailyRequest rRequestOrg = new BigOrgDailyRequest();
			rRequestOrg.setTaskid(orgSiteCode);
			List<BigOrgDaily> orgResult = bigOrgDailyServiceImpl
					.queryList(rRequestOrg);

			if (!CollectionUtils.isEmpty(orgResult)) {
				BigOrgDaily bigOrgDaily = orgResult.get(0);


				// 2.1首页连通率 ( 一周 )
				Double linksuccsiteprop = 100 - Double.valueOf(bigOrgDaily
						.getLinkerrsiteprop7());
	
				// 首页不连通率占比( 一周 )
				resultMap.put("linkerrsiteprop",
						bigOrgDaily.getLinkerrsiteprop7());
				// 首页连通率 ( 一周 )
				resultMap.put("linksuccsiteprop",
						CommonUtils.getAvgProportion(linksuccsiteprop));

				// 2.2首页正常更新率 ( 两周 )
				Double updatesiteprop = 100 - Double.valueOf(bigOrgDaily
						.getNoupdatesiteprop14());
				// 首页更新不及时占比 ( 两周 )
				resultMap.put("noupdatesiteprop",
						bigOrgDaily.getNoupdatesiteprop14());
				// 首页更新及时占比
				resultMap.put("updatesiteprop",
						CommonUtils.getAvgProportion(updatesiteprop));

				// 2.3首页不可用链接数( 昨天 )
				resultMap.put("indexdeadnum",
						bigOrgDaily.getIndexdeadtotalnum());

				// 2.4内容更新总量 ( 昨天 )
				resultMap
						.put("aveupdatenum", bigOrgDaily.getUpdatecontentnum());

				// 2.4首页更新量 ( 昨天 )
				resultMap.put("updatenum", bigOrgDaily.getUpdatenum());

			}

		} catch (Exception e) {

			logger.error("大数据分析-大数据首页-查询-获取组织单位下各市连通率和组织单位监测情况异常,errorMsg:"
					+ e.getMessage());
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> getSiteStateInfo(String orgSiteCode) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 获取组织单位下各状态站点分布情况（正常，关停，例外），以及监测站点数
		try {
			DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
			qRequest.setSiteCode(orgSiteCode);
			List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl
					.queryList(qRequest);

			if (CollectionUtils.isEmpty(dList)) {
				return null;
			}

			DatabaseTreeInfoRequest rRequest = new DatabaseTreeInfoRequest();
			rRequest.setCode(dList.get(0).getCode());
			DatabaseTreeInfo dInfo = databaseBizServiceImpl
					.getInfoStates(rRequest);

			resultMap.put("info", dInfo);
			if ("bm0100".equals(orgSiteCode)) {
				// 获取 统计好的 全国上报站点！
				DicConfig dicConfig = dicConfigServiceImpl.get(7);
				resultMap.put("sumAll", dicConfig.getValue());
			}
			resultMap.put("siteCode", orgSiteCode);
			resultMap.put("success", "true");

		} catch (Exception e) {

			logger.error("大数据分析-大数据首页-查询-获取站点各状态数量异常,errorMsg:"
					+ e.getMessage());
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> getAllScanProblemNum() {

		// 全国网站监测情况：全国监测页面数量,全国发现问题数量
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// String configId = "7,8,9,10,11,12";
			String configId = "8,9";

			Map<String, Object> params = new HashMap<String, Object>();
			String[] codeArray = configId.split(",");
			params.put("configIds", codeArray);

			List<DicConfigRequest> dicConfig = dicConfigServiceImpl
					.queryListByMap(params);
			resultMap.put("dicConfig", dicConfig);
		} catch (Exception e) {

			logger.error("大数据分析-大数据首页-查询-获取全国监测页面数量,全国发现问题数量异常,errorMsg:"
					+ e.getMessage());
		}

		return resultMap;

	}

	@Override
	public Map<String, Object> getJcVisitOrg_Seven(String orgSiteCode) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 组织单位用户访问量-连续7天
		try {
			JcVisitOrgRequest jRequest = new JcVisitOrgRequest();
			List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("scan_date",
					QueryOrderType.ASC);
			querySiteList.add(siteQueryOrder);
			jRequest.setQueryOrderList(querySiteList);
			jRequest.setSiteCode(orgSiteCode);
			jRequest.setEndDate(DateUtils.getNextDay(queryHomePageDate(), "-6"));
			List<JcVisitOrg> list = jcVisitOrgServiceImpl.queryList(jRequest);
			resultMap.put("list", list);
			resultMap.put("success", "true");
		} catch (Exception e) {
			logger.error("大数据分析-大数据首页-查询-获取组织单位用户访问量-连续7天异常,errorMsg:"
					+ e.getMessage());
		}

		return resultMap;

	}

	@Override
	public JSONObject parseBySiteCodeAndKey(String orgSiteCode, String key) {
		// 读取文件
		String filePath = urlAdapterVar.getBigHomeJsonUrl() + "/" + orgSiteCode
				+ ".json";

		try {

			if (!FileUtils.fileExists(filePath)) {
				createBigHomeJsonData(orgSiteCode);
			}

			String data = FileUtils.readFileUTF8(filePath);
			// 转成json数组
			JSONArray arrayA = JSONArray.fromObject(data);
			// 获取第一个
			JSONObject jsonObject = arrayA.getJSONObject(0);

			return jsonObject.getJSONObject(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("大数据分析-大数据首页-parseBySiteCodeAndKey-转成JSONOBject异常,errorMsg:"
					+ e.getMessage());
		}
		return null;

	}
	/**
	 * @描述:获取概览页面 配置日期
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-5-3上午9:36:34 
	 * @return
	 */
	public String queryHomePageDate(){
		String val = dicUtils.getValue("home_data_scanDate");
		if("null".equals(val)){
			val = DateUtils.getNextDay(new Date(), -1);
		}
		return  val;
	}
	/**
	 * @描述:大数据获取配置查询日期
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-5-3上午11:19:13 
	 * @return
	 */
	public String queryDate(){
		String  scanDate = DateUtils.formatShortDate(DateUtils.parseStandardDate(queryHomePageDate()));
		return scanDate;
	}
}
