package com.ucap.cloud_web.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionSupport;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.bizService.BigDataHomeBizService;
import com.ucap.cloud_web.bizService.DatabaseTreeBizService;
import com.ucap.cloud_web.constant.AccountBindStatus;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.constant.UserType;
import com.ucap.cloud_web.dto.AccountBindInfoRequest;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.entity.AccountBindInfo;
import com.ucap.cloud_web.entity.ConfigLinkExcept;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.RelationsPeriod;
import com.ucap.cloud_web.entity.ReportWordResult;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IAccountBindInfoService;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IRelationsPeriodService;
import com.ucap.cloud_web.service.IReportWordResultService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.servlet.util.CommonUtils;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.shiro.ShiroUser;
import com.ucap.cloud_web.util.MonitoringCacheUtils;

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static Log logger = LogFactory.getLog(BaseAction.class);
	private static final long serialVersionUID = 8022100782822206540L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	protected String param;
	protected String redirect;
	protected Integer pageSize = 10;
	protected Integer pageNo = 1;
	protected Integer recordSize = 0;

	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IRelationsPeriodService relationsPeriodServiceImpl;
	@Autowired
	private IReportWordResultService reportWordResultServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private DatabaseTreeBizService databaseTreeBizServiceImpl;
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	@Autowired
	private IAccountBindInfoService accountBindInfoServiceImpl;
	@Autowired
	private BigDataHomeBizService bigDataHomeBizServiceImpl;
	public String nicknameUrl="https://api.weixin.qq.com/cgi-bin/user/info";
	// 判断url中是否包含mail参数,如果有此参数则出现邮箱注册
	private String mail;

	// cookie id
	protected static final String COOKIE_ID = "ref";
	private List<DatabaseTreeInfo> siteList;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	protected PrintWriter getWriter() {
		try {
			response.setCharacterEncoding("utf-8");
			return response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void OutputJson(Object object) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setCharacterEncoding("utf-8");
		String json = null;
		try {
			out = httpServletResponse.getWriter();
			json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}

	public void OutputJson(Object object, String type) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
		httpServletResponse.setContentType(type);
		httpServletResponse.setCharacterEncoding("utf-8");
		String json = null;
		try {
			out = httpServletResponse.getWriter();
			json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}
	
	//判断死链是否过滤
	public int filteringUrl (String url, List<ConfigLinkExcept> list) {
		int addflag = 0;
		for (ConfigLinkExcept configLinkExcept : list) {
			String configLinkUrl = configLinkExcept.getUrl();
			boolean flag = false;
			try {
				 flag = url.trim().startsWith(configLinkUrl.trim());
			} catch (Exception e) {
				continue;
			}
			
			if (flag) {
				addflag = 1;
				break;
			}
		}
		return addflag;
	}
	
	protected void writerPrint(String value) {
		try {
			this.response.setCharacterEncoding("utf-8");
			this.response.setContentType("text/html; charset=utf-8");
			this.response.getWriter().print(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 查询绑定账户信息
	 * @author cuichx --- 2017-3-17下午12:51:25     
	 * @param openId
	 * @return
	 */
	public List<AccountBindInfo> getAccountList(String openId){
		
		AccountBindInfoRequest accountBindInfoRequest=new AccountBindInfoRequest();
		accountBindInfoRequest.setOpenId(openId);
		accountBindInfoRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
		//通过客户唯一标识查询账户绑定信息表
		List<AccountBindInfo> accountList=accountBindInfoServiceImpl.queryList(accountBindInfoRequest);
		if(accountList!=null && accountList.size()>0){
			return accountList;
		}else{
			return null;
		}
	}
	/**
	 * @Description: 获取信息用户昵称
	 * @author cuichx --- 2017-3-17下午12:48:12     
	 * @param openId
	 * @return
	 */
	public String getNickname(String openId){
		/**
		 * 获取微信昵称
		 */
		String nickname="";
    	//获取访问权限access_token
    	String access_token=CommonUtils.getTokenFromServlet();
    	//拼装请求url
    	String requestUrl=nicknameUrl+"?access_token="+
    				access_token+"&openid="+openId+"&lang=zh_CN";
    	//发送https请求获取微信用户的昵称
    	JSONObject jsonStr=CommonUtils.httpRequest(requestUrl, "POST", null);
    	logger.info("httpRequest nickname_jsonStr:"+jsonStr);
    	
    	if(jsonStr!=null){
    		nickname=(String) jsonStr.get("nickname");
    		//过滤掉微信昵称中的emoji表情字符串
    		nickname=CommonUtils.removeFaceCharacter(nickname);
    		
    		logger.info(((String) jsonStr.get("nickname"))+":removeFaceCharacter after:"+nickname);
    	}
    	return nickname;
	}
	/**
	 * @Description: 获取健康分数  保留两位小数
	 * @author cuichx --- 2016-3-25上午11:55:50     
	 * @param convertScores 折算分数
	 * @param isCost 是否收费  0-免费 1-收费
	 * @return
	 */
	public String getHealthScores(double convertScores, int isCost) {
		double totalSum = (convertScores - 60) / 60 * 1 * 1200;
		String totalSumNumber = "";
		if (isCost == 0) {//免费版
			totalSumNumber = com.publics.util.utils.StringUtils.formatDouble(2, totalSum + 1600);
		} else {
			totalSumNumber = com.publics.util.utils.StringUtils.formatDouble(2, totalSum + 1600);
		}
		return totalSumNumber;
	}

	/**
	 * 
	 * 描述:获取当前cookies
	 * 
	 * 作者：lxx 2015-11-16上午11:52:45
	 * 
	 * @param key
	 * @param value
	 */
	public Cookie[] getCookies() {
		return request.getCookies();
	}

	/**
	 * 
	 * 描述:通过cookies获取当前sessionId
	 * 
	 * 作者：lxx 2015-11-16上午11:52:45
	 * 
	 * @param key
	 * @param value
	 */
	public String getSessionId() {
		String sessionId = null;
		Cookie[] cookies = getCookies();
		if (cookies != null) {
			for (Cookie one : cookies) {
				if (COOKIE_ID.equalsIgnoreCase(one.getName())) {
					sessionId = one.getValue();
				}
			}
		}
		return sessionId;
	}

	public static boolean isGoodJson(String json) {
		if (StringUtils.isBlank(json)) {
			return false;
		}
		try {
			new JsonParser().parse(json);
			return true;
		} catch (JsonParseException e) {
			return false;
		}
	}

	/**
	 * 
	 * 描述:获取当前session对象
	 * 
	 * 作者：lxx 2015-11-16上午11:52:45
	 * 
	 * @param key
	 * @param value
	 */
	protected HttpSession getSession() {
		return request.getSession();
	}

	/**
	 * 
	 * 描述:添加session
	 * 
	 * 作者：lxx 2015-11-16上午11:52:45
	 * 
	 * @param key
	 * @param value
	 */
	protected void add2Session(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 
	 * 描述:通过key删除session
	 * 
	 * 作者：lxx 2015-11-16上午11:52:45
	 * 
	 * @param key
	 * @param value
	 */
	protected void removeSession(String key) {
		getSession().removeAttribute(key);
	}

	/**
	 * 
	 * 描述:获取path路径
	 * 
	 * 作者：lxx 2015-11-16上午11:52:27
	 * 
	 * @return
	 */
	protected String getBasePath() {
		String basePath = "";

		int port = request.getServerPort();
		if (port != 80) {
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		} else {
			basePath = request.getScheme() + "://" + request.getServerName() + request.getContextPath();
		}

		return basePath;
	}

	/**
	 * 
	 * 描述:获取ip地址
	 * 
	 * 作者：lxx 2015-11-16上午11:52:09
	 * 
	 * @return
	 */
	public String getIpAddr() {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}

				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * @Description:获取当前登录用户的信息
	 * @author kefan-- 2015-11-17 下午12:33:41
	 * 
	 * @return 如果用户已经过期，则返回null
	 */

	public ShiroUser getCurrentUserInfo() {
		HttpSession session = getSession();
		if (session != null) {
			ShiroUser info = (ShiroUser) session.getAttribute(Constants.SHIRO_USER);
			if (info != null) {
				return info;
			}
		}
		return null;
	}

	/**
	 * 
	 * 描述:获取当前登入6位siteCode
	 * 
	 * 作者：lxx 2015-11-18下午06:07:55
	 * 
	 * @return
	 */
	public String getCurrentSiteCode() {
		HttpSession session = request.getSession();
		if (session != null) {
			ShiroUser info = (ShiroUser) session.getAttribute(Constants.SHIRO_USER);
			if (info != null) {
				if ("admin".equals(info.getSiteCode())){
					return info.getSiteCode();
				}else{
					if(info.getSiteCode()!= null){
						if(info.getSiteCode().length() == 6){
							return info.getSiteCode();
						}else{
							DatabaseTreeInfoRequest dRequest = new DatabaseTreeInfoRequest();
							dRequest.setSiteCode(info.getSiteCode());
							dRequest.setIsLink(1);
							List<DatabaseTreeInfo> dLink = databaseTreeInfoServiceImpl.queryList(dRequest);
							if(dLink.size()>0){
								return dLink.get(0).getOrgSiteCode();
							}else{
								return null;
							}
						}
					}
				}
				
			}
		}
		return null;
	}

	/**
	 * @Description:页面周期时间控件初始化数据 页面周期时间控件初始化数据
	 *     处理思路：
	 * @author cuichx --- 2015-11-18下午4:06:24
	 */
	public Map<String, Object> timeTool(String siteCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<Object> serviceList = new ArrayList<Object>();
			/**老合同信息**/
			List<ServicePeriod> servicePdList = getServicePeriodList(0);
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode(),
			// CrmProductsType.CHECK.getCode() };
			// List<ServicePeriod> servicePdList = getAllServicePeriodList(0,
			// productTypeArr);
			if (servicePdList != null && servicePdList.size() > 0) {
				for (int i = 0; i < servicePdList.size(); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					ServicePeriod servicePeriod = servicePdList.get(i);
					map.put("id", servicePeriod.getId());//服务周期id
					map.put("startDate", DateUtils.formatDate(servicePeriod.getStartDate()));//服务周期开始时间
					map.put("endDate", DateUtils.formatDate(servicePeriod.getEndDate()));//服务周期结束时间

					serviceList.add(map);
				}
				resultMap.put("serviceList", serviceList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * @Description: 处理组织单位跳转到填报单位，ShiroUser的修改
	 * @author cuichx --- 2016-3-6下午9:32:44
	 */
	public void setCurrentShiroUser(String siteCode) {
		if (StringUtils.isNotEmpty(siteCode)) {
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser.getSiteCode().length() == 6) {//组织单位跳转到填报，需要保存到session中
				shiroUser.setChildSiteCode(siteCode);
				shiroUser.setType(1);
				DatabaseInfo databaseInfo = databaseInfoServiceImpl.findByDatabaseInfoCode(siteCode);
				if (databaseInfo != null) {
					shiroUser.setSiteName(databaseInfo.getName());

					if (StringUtils.isNotEmpty(databaseInfo.getJumpUrl())) {
						databaseInfo.setUrl(databaseInfo.getJumpUrl());
					}
					shiroUser.setUrl(databaseInfo.getUrl());
					/**
					 * 修改填报单位的付费状态   与组织单位的付费状态一致
					 * 	这样就保证了  组织单位买单能看填报单位详情  组织单位没有买单就不能看
					 */
					if(getCurrentUserInfo().getIsOrgCost()==1){
						shiroUser.setIscost(1);
					}else{
						shiroUser.setIscost(0);
					}
					shiroUser.setOrgToIsScan(databaseInfo.getIsScan());
					shiroUser.setOrgToIsexp(databaseInfo.getIsexp());
					shiroUser.setOrgToInfo("1");
				}
				/**老合同信息**/
				ServicePeriod servicePd = getCurrentPeriod();
				/**新产品信息**/
				// Integer[] productTypeArr = {
				// CrmProductsType.DETECTION.getCode() };
				// ServicePeriod servicePd =
				// getCurrentServicePeriod(productTypeArr);
				if (servicePd != null) {
					//获取当前期数
					int periodId = servicePd.getId();
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("servicePeriodId", periodId);
					paramMap.put("siteCode", siteCode);

					//通过网站标识码、当前周期期数、当前时间进行连表查询（站点信息表、服务周期表、报告结果表）
					List<ReportWordResult> reportWordList = reportWordResultServiceImpl.findSiteByMap(paramMap);

					if (reportWordList != null && reportWordList.size() > 0) {
						shiroUser.setPdfUrl(reportWordList.get(0).getPdfUrl());
					}
					removeSession(Constants.SHIRO_USER);
					add2Session(Constants.SHIRO_USER, shiroUser);
				}
			}
		}
	}

	/**
	 * 
	 * @描述:修改新菜单引导页状态，ShiroUser的修改
	 * @作者:liukl@ucap.com.cn
	 * @时间:2017年4月17日10:52:23
	 * @param siteCode
	 */
	public void changeGuideState(String siteCode){
		try {
			if(StringUtils.isNotEmpty(siteCode)){
				ShiroUser shiroUser = getCurrentUserInfo();
				shiroUser.setGuideState(1);
				removeSession(Constants.SHIRO_USER);
				add2Session(Constants.SHIRO_USER, shiroUser);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 根据标识码，获取正式合同
	 * @author cuichx --- 2016-7-4上午11:25:11
	 * @param siteCode
	 *            网站标识码
	 * @param nowDate
	 *            格式日期字符串（标准格式：yyyy-MM-dd）
	 * @return
	 */
	public List<ContractInfo> getContractInfoList(String siteCode, String nowDate) {
		List<ContractInfo> conList = null;
		try {

			ContractInfoRequest conRequest = new ContractInfoRequest();
			conRequest.setSiteCode(siteCode);
			conRequest.setTypeFlag(1);// 非抽查合同
			Integer[] conStatues = { 0, 1, 2 };
			conRequest.setExecuteStatusArr(conStatues);// 合同状态为0-初始化 1-服务中

			if (StringUtils.isNotEmpty(nowDate)) {
				conRequest.setNowTime(nowDate);
			}

			conRequest.setPageSize(Integer.MAX_VALUE);
			// 排序
			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("contract_end_time", QueryOrderType.DESC);
			queryOrderList.add(siteQueryOrder);
			conRequest.setQueryOrderList(queryOrderList);
			conList = contractInfoServiceImpl.queryList(conRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conList;
	}

	/**
	 * @Description: 根据siteCode获取当前周期
	 * @author cuichx --- 2016-3-6上午2:11:21
	 * @param comboIdArr
	 * 1常态化检测 2普通版 3 高级版
	 * @return
	 */
	
	 public ServicePeriod getCurrentPeriod() {
	 ServicePeriod servicePeriod = null;
	 try {
	 /**
	 * 组织单位：直接查询合同信息表和服务周期表，查询有结果--存在周期，否则不存在周期对象
	 *
	 * 填报单位： 通过网站标识码查询合同信息表， 如果存在，说明该填报单位为自己买单， 直接查询服务周期表，获取当前周期
	 * 如果不存在，说明是省级组织单位买单，
	 * 通过网站标识码+当前时间，查询服务周期中间表，获取服务周期id，再通过服务周期id,查询服务周期表，获取当前周期对象
	 *
	 *
	 */
	 // 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();// 组织单位跳转到填报单位(组织单位登录)
	 if (StringUtils.isEmpty(siteCode)) {
	 siteCode = getCurrentUserInfo().getSiteCode();// 获取当前登录人的网站标识码
	 }
	 /**
	 * 根据网站标识码查询客户查询合同信息表 如果合同信息表存在，通过合同信息表id查询服务周期表+状态为1服务中，获取当前服务周期
	 */
	 ContractInfoRequest contractInfoRequest = new ContractInfoRequest();
	 contractInfoRequest.setSiteCode(siteCode);
	 contractInfoRequest.setNowTime(DateUtils.formatStandardDateTime(new
	 Date()));
	 List<ContractInfo> contractList =
	 contractInfoServiceImpl.queryList(contractInfoRequest);
	 if (contractList != null && contractList.size() > 0) {// 买单的组织单位和买单填报单位
	 ContractInfo contractInfo = contractList.get(0);
	 int contractId = contractInfo.getId();
	
	 ServicePeriodRequest servicePD = new ServicePeriodRequest();
	 servicePD.setContractInfoId(contractId);
	 int[] comboIdArr = { 3, 4 };
	 servicePD.setComboIdArr(comboIdArr);// 套餐类型为普通版和高级版
	 servicePD.setStatus(1);// 服务中
	
	 List<ServicePeriod> serviceList =
	 servicePeriodServiceImpl.queryList(servicePD);
	 if (serviceList != null && serviceList.size() > 0) {
	 servicePeriod = serviceList.get(0);
	 }
	 } else {
	 // 未买单的组织单位---返回空--不知道如何处理
	
	 // 未买单的填报单位，做如下处理
	 if (siteCode.length() == 10) {
	 RelationsPeriodRequest relaRequest = new RelationsPeriodRequest();
	 relaRequest.setSiteCode(siteCode);
	 relaRequest.setNowTime(DateUtils.formatStandardDate(new Date()));
	 List<RelationsPeriod> relaList =
	 relationsPeriodServiceImpl.queryList(relaRequest);
	 if (relaList != null && relaList.size() > 0) {
	 RelationsPeriod relaPeriod = relaList.get(0);
	 int servicePeriodId = relaPeriod.getServicePeriodId();
	
	 ServicePeriodRequest serviceRequest = new ServicePeriodRequest();
	 serviceRequest.setId(servicePeriodId);
	 int[] statasArray = { 1, 2 };// 服务中（当前周期）和已完成服务（上一周期之前）
	 serviceRequest.setStatasArray(statasArray);
	 int[] comboIdArr = { 3, 4 };
	 serviceRequest.setComboIdArr(comboIdArr);// 套餐类型为普通版和高级版
	
	 List<ServicePeriod> servicePdList =
	 servicePeriodServiceImpl.queryList(serviceRequest);
	 if (servicePdList != null && servicePdList.size() > 0) {
	 servicePeriod = servicePdList.get(0);
	 }
	 }
	 }
	 }
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 return servicePeriod;
	 }

	/**
	 * @Description: 根据网站标识码、服务周期完成状态，获取上一周期对象
	 * @author cuichx --- 2016-3-6上午2:10:16
	 * @param comboIdArr
	 *            1常态化检测 2普通版 3 高级版
	 * @return
	 */
	public ServicePeriod getPrePeriod() {
		ServicePeriod servicePeriod = null;
		try {
			/**
			 * 组织单位：直接查询合同信息表和服务周期表，查询有结果--存在周期，否则不存在周期对象
			 *
			 * 填报单位： 通过网站标识码查询合同信息表， 如果存在，说明该填报单位为自己买单， 直接查询服务周期表（已完成），获取上一周期对象
			 * 如果不存在，说明是省级组织单位买单，
			 * 通过网站标识码，查询服务周期中间表（查询结果按周期结束时间倒排序），获取服务周期id，再通过服务周期id,查询服务周期表，
			 * 获取上一周期对象
			 *
			 *
			 */
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();// 组织单位跳转
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			// 根据客户id，查询合同信息表
			ContractInfoRequest contractInfoRequest = new ContractInfoRequest();
			contractInfoRequest.setSiteCode(siteCode);
			List<ContractInfo> contractList = contractInfoServiceImpl.queryList(contractInfoRequest);
			if (contractList != null && contractList.size() > 0) {// 买单的填报单位和买单组织单位
				ContractInfo contractInfo = contractList.get(0);
				int contractId = contractInfo.getId();

				ServicePeriodRequest servicePD = new ServicePeriodRequest();
				servicePD.setContractInfoId(contractId);
				servicePD.setStatus(2);// 已完成服务

				List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder = new QueryOrder("start_date", QueryOrderType.DESC);
				querySiteList.add(siteQueryOrder);
				int[] comboIdArr = { 3, 4 };
				servicePD.setComboIdArr(comboIdArr);// 套餐类型为普通版和高级版
				servicePD.setQueryOrderList(querySiteList);

				List<ServicePeriod> serviceList = servicePeriodServiceImpl.queryList(servicePD);
				if (serviceList != null && serviceList.size() > 0) {
					servicePeriod = serviceList.get(0);// 获取上一周期对象
				}
			} else {
				// 未买单的组织单位--暂时不知道如何处理

				// 未买单的填报单位，做如下处理
				if (siteCode.length() == 10) {
					RelationsPeriodRequest relaRequest = new RelationsPeriodRequest();
					relaRequest.setSiteCode(siteCode);

					List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
					QueryOrder siteQueryOrder = new QueryOrder("start_date", QueryOrderType.DESC);
					querySiteList.add(siteQueryOrder);

					List<RelationsPeriod> relaList = relationsPeriodServiceImpl.queryList(relaRequest);
					if (relaList != null && relaList.size() >= 2) {// 如果集合长度小于2，不存在在上一周期对象
						RelationsPeriod relaPeriod = relaList.get(1);
						int servicePeriodId = relaPeriod.getServicePeriodId();

						ServicePeriodRequest serviceRequest = new ServicePeriodRequest();
						serviceRequest.setId(servicePeriodId);
						int[] statasArray = { 1, 2 };// 服务中（当前周期）和已完成服务（上一周期之前）
						serviceRequest.setStatasArray(statasArray);
						int[] comboIdArr = { 3, 4 };
						serviceRequest.setComboIdArr(comboIdArr);// 套餐类型为普通版和高级版

						List<ServicePeriod> servicePdList = servicePeriodServiceImpl.queryList(serviceRequest);
						if (servicePdList != null && servicePdList.size() > 0) {
							servicePeriod = servicePdList.get(0);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicePeriod;
	}

	/**
	 * @Description: 获取所有服务周期（当前周期+已完成周期）--周期正序排列
	 * @author cuichx --- 2016-3-17下午5:03:55
	 * @param sort
	 *            1正序排列， 0-倒序排列
	 * @return
	 */
	public List<ServicePeriod> getServicePeriodList(int sort) {
		List<ServicePeriod> serviceList = null;
		try {
			/**
			 * 组织单位：直接查询合同信息表和服务周期表，查询有结果--存在周期，否则不存在周期对象
			 *
			 * 填报单位： 通过网站标识码查询合同信息表， 如果存在，说明该填报单位为自己买单， 直接查询服务周期表（已完成），获取上一周期对象
			 * 如果不存在，说明是省级组织单位买单，
			 * 通过网站标识码，查询服务周期中间表（查询结果按周期结束时间倒排序），获取服务周期id，再通过服务周期id,查询服务周期表，
			 * 获取上一周期对象
			 *
			 *
			 */
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();// 组织单位跳转
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}

			// 根据网站标识码，查询合同信息表
			ContractInfoRequest contractInfoRequest = new ContractInfoRequest();
			contractInfoRequest.setSiteCode(siteCode);
			List<ContractInfo> contractList = contractInfoServiceImpl.queryList(contractInfoRequest);
			if (contractList != null && contractList.size() > 0) {// 买单的组织单位和买单的填报单位
				ContractInfo contractInfo = contractList.get(0);
				int contractId = contractInfo.getId();

				ServicePeriodRequest servicePD = new ServicePeriodRequest();
				servicePD.setContractInfoId(contractId);
				int[] statasArray = { 1, 2 };// 服务中（当前周期）和已完成服务（上一周期之前）
				servicePD.setStatasArray(statasArray);
				int[] comboIdArr = { 3, 4 };
				servicePD.setComboIdArr(comboIdArr);// 套餐类型为普通版和高级版

				// 按周期开始时间排序
				List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder = null;
				if (sort == 1) {
					siteQueryOrder = new QueryOrder("start_date", QueryOrderType.ASC);
				} else {
					siteQueryOrder = new QueryOrder("start_date", QueryOrderType.DESC);
				}
				querySiteList.add(siteQueryOrder);
				servicePD.setQueryOrderList(querySiteList);

				serviceList = servicePeriodServiceImpl.queryList(servicePD);
			} else {
				// 未买单的组织单位--暂时不知道如何处理

				// 未买单的填报单位，做如下处理
				if (siteCode.length() == 10) {
					RelationsPeriodRequest relaRequest = new RelationsPeriodRequest();
					relaRequest.setSiteCode(siteCode);

					List<RelationsPeriod> relaList = relationsPeriodServiceImpl.queryList(relaRequest);
					if (relaList != null && relaList.size() > 0) {

						ServicePeriodRequest servicerPeriodRequest = new ServicePeriodRequest();
						servicerPeriodRequest.setList(relaList);
						int[] statasArray = { 1, 2 };// 服务中（当前周期）和已完成服务（上一周期之前）
						servicerPeriodRequest.setStatasArray(statasArray);
						int[] comboIdArr = { 3, 4 };
						servicerPeriodRequest.setComboIdArr(comboIdArr);// 套餐类型为普通版和高级版
						// 按周期开始时间排序
						List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
						QueryOrder siteQueryOrder = null;
						if (sort == 1) {
							siteQueryOrder = new QueryOrder("start_date", QueryOrderType.ASC);
						} else {
							siteQueryOrder = new QueryOrder("start_date", QueryOrderType.DESC);
						}

						querySiteList.add(siteQueryOrder);

						serviceList = servicePeriodServiceImpl.queryList(servicerPeriodRequest);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceList;
	}

	/************************************ 新产品信息 *****************************************************/
	/**
	 * 
	 * @描述:根据标识码、产品分类、日期获取产品信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月8日下午2:11:18
	 * @param siteCode
	 * @param productTypeArr
	 *            产品分类（可随时增加）:1.全面检测 2.抽查
	 * @param nowDate
	 * @param executeStatusArr
	 * @param typeFlag
	 *            只查询 1 组织单位 2 填报单位(注意：传typeFlag值就不要排序)
	 * @param orderTypesArr
	 *            订单分类 1云监管订单/2云监测订单/3云安全订单/4云搜索订单/5本地部署订单
	 * @return
	 */
/*	public List<CrmProductsResponse> getCrmProductsList(String siteCode, Integer[] productTypeArr, String nowDate,
			Integer[] executeStatusArr, Integer typeFlag) {
		List<CrmProductsResponse> crmlist = new ArrayList<CrmProductsResponse>();
		CrmProductsRequest crmReq = new CrmProductsRequest();
		List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
		QueryOrder siteQueryOrder = new QueryOrder("co.order_end_time", QueryOrderType.DESC);
		queryOrderList.add(siteQueryOrder);
		try {
			crmReq.setSiteCode(siteCode);
			if (productTypeArr != null) {
				crmReq.setProductTypeArr(productTypeArr);
			}
			if (executeStatusArr != null) {
				crmReq.setExecuteStatusArr(executeStatusArr); // 执行状态
																// 作废：-1，初始化：0，服务中：1，服务结束：2
			} else {
				crmReq.setNotExecuteStatus(-1);// 执行状态 作废：-1，初始化：0，服务中：1，服务结束：2
			}
			if (StringUtils.isNotEmpty(nowDate)) {
				crmReq.setNowTime(nowDate);
			}

			if (typeFlag != null) {
				if (typeFlag == 1) {
					crmReq.setOrgFlag(1);
				} else {
					crmReq.setTbFlag(1);
				}
			} else {
				crmReq.setQueryOrderList(queryOrderList);
			}
			crmlist = crmProductsServiceImpl.getCrmProductsList(crmReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return crmlist;
	}*/

	/**
	 * 
	 * @描述:获取产品下服务中周期
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月10日上午11:33:18
	 * @param productType
	 *            产品分类（可随时增加）:1.全面检测 2.抽查 3 安全
	 * @return
	 */
	/*public ServicePeriod getCurrentServicePeriod(Integer[] productTypeArr) {
		ServicePeriod servicePeriod = new ServicePeriod();
		List<CrmProductsResponse> crmlist = new ArrayList<CrmProductsResponse>();
		// 从session中获取10位填报单位网站标识码
		String siteCode = getCurrentUserInfo().getChildSiteCode();// 组织单位跳转到填报单位(组织单位登录)
		if (StringUtils.isEmpty(siteCode)) {
			siteCode = getCurrentUserInfo().getSiteCode();// 获取当前登录人的网站标识码
		}
		try {
			CrmProductsRequest crmReq = new CrmProductsRequest();
			crmReq.setExecuteStatus(1); // 执行状态 作废：-1，初始化：0，服务中：1，服务结束：2
			crmReq.setProductTypeArr(productTypeArr);
			crmReq.setSiteCode(siteCode);
			crmReq.setNowTime(DateUtils.formatStandardDateTime(new Date()));
			crmlist = crmProductsServiceImpl.getCrmProductsList(crmReq);

			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("start_date", QueryOrderType.DESC);
			queryOrderList.add(siteQueryOrder);

			if (CollectionUtils.isNotEmpty(crmlist)) { // 买服务的组织单位或填报单位
				CrmProductsResponse crm = crmlist.get(0);
				int contractId = crm.getId();

				ServicePeriodRequest servicePD = new ServicePeriodRequest();
				servicePD.setQueryOrderList(queryOrderList);
				servicePD.setContractInfoId(contractId);
				servicePD.setStatus(1);// 服务中
				int[] comboIdArr = { 3, 4 };
				servicePD.setComboIdArr(comboIdArr);// 套餐类型为普通版和高级版
				List<ServicePeriod> serviceList = servicePeriodServiceImpl.queryList(servicePD);
				if (CollectionUtils.isNotEmpty(serviceList)) {
					servicePeriod = serviceList.get(0);
				}
			} else { // 未买单的组织单位---返回空--不知道如何处理 未买单的填报单位，做如下处理
				if (siteCode.length() == 10) {
					RelationsPeriodRequest relaRequest = new RelationsPeriodRequest();
					relaRequest.setSiteCode(siteCode);
					relaRequest.setNowTime(DateUtils.formatStandardDate(new Date()));
					relaRequest.setQueryOrderList(queryOrderList);
					List<RelationsPeriod> relaList = relationsPeriodServiceImpl.queryList(relaRequest);
					if (relaList != null && relaList.size() > 0) {
						RelationsPeriod relaPeriod = relaList.get(0);
						int servicePeriodId = relaPeriod.getServicePeriodId();

						ServicePeriodRequest serviceRequest = new ServicePeriodRequest();
						serviceRequest.setQueryOrderList(queryOrderList);
						serviceRequest.setId(servicePeriodId);
						int[] statasArray = { 1, 2 };// 服务中（当前周期）和已完成服务（上一周期之前）
						serviceRequest.setStatasArray(statasArray);
						int[] comboIdArr = { 3, 4 };
						serviceRequest.setComboIdArr(comboIdArr);// 套餐类型为普通版和高级版
						List<ServicePeriod> servicePdList = servicePeriodServiceImpl.queryList(serviceRequest);
						if (servicePdList != null && servicePdList.size() > 0) {
							servicePeriod = servicePdList.get(0);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicePeriod;
	}*/

	/**
	 * 
	 * @描述:获取产品下所以周期
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月10日下午5:29:37
	 * @param sort
	 *            1正序排列， 0倒序排列
	 * @param productTypeArr
	 *            产品分类（可随时增加）:1.全面检测 2.抽查 3 安全
	 * @return
	 */
	/*public List<ServicePeriod> getAllServicePeriodList(int sort, Integer[] productTypeArr) {
		List<ServicePeriod> serviceList = new ArrayList<ServicePeriod>();
		List<CrmProductsResponse> crmlist = new ArrayList<CrmProductsResponse>();
		try {
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();// 组织单位跳转
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}

			// 按周期开始时间排序
			List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = null;
			if (sort == 1) {
				siteQueryOrder = new QueryOrder("start_date", QueryOrderType.ASC);
			} else {
				siteQueryOrder = new QueryOrder("start_date", QueryOrderType.DESC);
			}
			querySiteList.add(siteQueryOrder);

			CrmProductsRequest crmReq = new CrmProductsRequest();
			crmReq.setNotExecuteStatus(-1); // 执行状态 作废：-1，初始化：0，服务中：1，服务结束：2
			crmReq.setProductTypeArr(productTypeArr);
			crmReq.setSiteCode(siteCode);
			crmlist = crmProductsServiceImpl.getCrmProductsList(crmReq);

			if (CollectionUtils.isNotEmpty(crmlist)) {// 买服务的组织单位或填报单位
				CrmProductsResponse crm = crmlist.get(0);
				int contractId = crm.getId();

				ServicePeriodRequest servicePD = new ServicePeriodRequest();
				servicePD.setQueryOrderList(querySiteList);
				servicePD.setContractInfoId(contractId);
				int[] statasArray = { 1, 2 };// 服务中（当前周期）和已完成服务（上一周期之前）
				servicePD.setStatasArray(statasArray);
				int[] comboIdArr = { 3, 4 };
				servicePD.setComboIdArr(comboIdArr);// 套餐类型为普通版和高级版
				serviceList = servicePeriodServiceImpl.queryList(servicePD);
			} else {
				// 未买单的组织单位--暂时不知道如何处理 //未买单的填报单位，做如下处理
				if (siteCode.length() == 10) {
					RelationsPeriodRequest relaRequest = new RelationsPeriodRequest();
					relaRequest.setSiteCode(siteCode);
					List<RelationsPeriod> relaList = relationsPeriodServiceImpl.queryList(relaRequest);
					if (relaList != null && relaList.size() > 0) {
						ServicePeriodRequest servicePD = new ServicePeriodRequest();
						servicePD.setQueryOrderList(querySiteList);
						servicePD.setList(relaList);
						int[] statasArray = { 1, 2 };// 服务中（当前周期）和已完成服务（上一周期之前）
						servicePD.setStatasArray(statasArray);
						int[] comboIdArr = { 3, 4 };
						servicePD.setComboIdArr(comboIdArr);// 套餐类型为普通版和高级版
						serviceList = servicePeriodServiceImpl.queryList(servicePD);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceList;
	}*/

	/****************************************************************************************/

	

	/**
	 * @Description: 根据组织机构编码，确定组织单位页面展示数据 isOrgCost=1 展示标准版 isOrgAdvance=1展示高级版
	 * @author cuichx --- 2017-3-22下午7:04:07     
	 * @param siteCode
	 */
/*	public Map<String, Object>  weiXinShowOrg(String siteCode){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		//组织单位--是否收费 1收费 2免费
		Integer isOrgCost=2;
		//组织单位--是否高级版 1-高级版  2非高级版
		Integer isOrgAdvance=2;
		//组织单位--是否安全 1-安全  2非安全
		Integer isOrgSafte=2;
		
		try {
			//根据网站标识码查询订单产品表
			List<CrmProductsResponse> crmList=getCrmProductsList(siteCode, 1);
			if(crmList!=null && crmList.size()>0){
				isOrgCost=1;//收费
				CrmProductsResponse crm = crmList.get(0);
				//合同id
				int contractId = crm.getId();
				
				ServicePeriodRequest pdRequest=new ServicePeriodRequest();
				pdRequest.setContractInfoId(contractId);
				pdRequest.setNowTime(DateUtils.formatStandardDate(new Date()));
				
				List<ServicePeriod> pdList=servicePeriodServiceImpl.queryList(pdRequest);
				if(pdList!=null && pdList.size()>0){
					for (ServicePeriod servicePeriod : pdList) {
						if(servicePeriod.getComboId()==4){//高级版
							isOrgAdvance=1;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("isOrgCost", isOrgCost);
		resultMap.put("isOrgAdvance", isOrgAdvance);
		
		return resultMap;
	}
	
	*/
	

	/**
	 * @Description: 根据组织机构编码，确定组织单位页面展示数据 isOrgCost=1 展示标准版 isOrgAdvance=1展示高级版===老合同
	 * @author cuichx --- 2017-3-22下午7:04:07     
	 * @param siteCode
	 */
	public Map<String, Object>  weiXinShowOrg(String siteCode){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		//组织单位--是否收费 1收费 2免费
		Integer isOrgCost=2;
		//组织单位--是否高级版 1-高级版  2非高级版
		Integer isOrgAdvance=2;
		//组织单位--是否安全 1-安全  2非安全
		Integer isOrgSafte=2;
		
		//服务周期id
		Integer servicePeroidId=0;
		try {
			//查询合同信息表
			ContractInfoRequest contractRequest=new ContractInfoRequest();
			contractRequest.setSiteCode(siteCode);
			contractRequest.setTypeFlag(1);//非抽查合同
			contractRequest.setExecuteStatus(1);//状态是执行中的
			
			List<ContractInfo>  contractList=contractInfoServiceImpl.queryList(contractRequest);
			if(contractList!=null && contractList.size()>0){//自己买单
				isOrgCost=1;//收费
				ServicePeriod servicePeriod=getAdvanceServicePeriod(siteCode, null);
				if(servicePeriod!=null){
					isOrgAdvance=1;
					servicePeroidId=servicePeriod.getId();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("servicePeroidId", servicePeroidId);
		resultMap.put("isOrgCost", isOrgCost);
		resultMap.put("isOrgAdvance", isOrgAdvance);
		
		return resultMap;
	}
	
	/**
	 * @Description: 根据网站标识，确定填报单位页面展示数据 isCost=1 展示标准版 isAdvance=1展示高级版
	 * @author cuichx --- 2017-3-22下午3:45:16     
	 * @param siteCode
	 */
	/*public Map<String, Object> weiXinShowTb(String siteCode){
		//填报单位--是否收费 1收费 2免费
		Integer isCost=2;
		//填报单位--是否高级版 1-高级版  2非高级版
		Integer isAdvance=2;
		//填报单位--是否安全 1-安全  2非安全
		Integer isSafte=2;
		//根据网站标识码查询订单产品表
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
				
		try {
			List<CrmProductsResponse> crmList=getCrmProductsList(siteCode, 2);
			if(crmList!=null && crmList.size()>0){
				isCost=1;//收费
				CrmProductsResponse crm = crmList.get(0);
				//合同id
				int contractId = crm.getId();
				
				ServicePeriodRequest pdRequest=new ServicePeriodRequest();
				pdRequest.setContractInfoId(contractId);
				pdRequest.setNowTime(DateUtils.formatStandardDate(new Date()));
				
				List<ServicePeriod> pdList=servicePeriodServiceImpl.queryList(pdRequest);
				if(pdList!=null && pdList.size()>0){
					for (ServicePeriod servicePeriod : pdList) {
						if(servicePeriod.getComboId()==4){//高级版
							isAdvance=1;
						}
					}
				}
			}else{//查询组织单位是否买单
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("executeStatus", 1);//执行中
				map.put("productType", 1);//全面监测
				map.put("isSearchTb", 1);//填报单位允许查看
				map.put("siteCode", siteCode);//10位网站标识码
				List<Integer> crmOrgList=crmProductsServiceImpl.queryByMap(map);
				if(crmOrgList!=null && crmOrgList.size()>0){
					isCost=1;//收费
					//判断这些合同中是否有高级版
					ServicePeriodRequest spRequest=new ServicePeriodRequest();
					spRequest.setContractInfoIdList(crmOrgList);
					spRequest.setComboI(4);
					
					List<ServicePeriod> spList=servicePeriodServiceImpl.queryList(spRequest);
					if(spList!=null && spList.size()>0){
						isAdvance=1;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("isCost", isCost);
		resultMap.put("isAdvance", isAdvance);
		
		return resultMap;
		
	}*/
	/**
	 * @Description: 根据网站标识，确定填报单位页面展示数据 isCost=1 展示标准版 isAdvance=1展示高级版=====老合同
	 * @author cuichx --- 2017-3-22下午3:45:16     
	 * @param siteCode
	 */
	public Map<String, Object> weiXinShowTb(String siteCode){
		//填报单位--是否收费 1收费 2免费
		Integer isCost=2;
		//填报单位--是否高级版 1-高级版  2非高级版
		Integer isAdvance=2;
		//填报单位--是否安全 1-安全  2非安全
		Integer isSafte=2;
		//根据网站标识码查询订单产品表
		
		//服务周期id
		Integer servicePeroidId=0;
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
				
		try {
			//查询合同信息表
			ContractInfoRequest contractRequest=new ContractInfoRequest();
			contractRequest.setSiteCode(siteCode);
			contractRequest.setTypeFlag(1);//非抽查合同
			contractRequest.setExecuteStatus(1);//状态是执行中的
			
			List<ContractInfo>  contractList=contractInfoServiceImpl.queryList(contractRequest);
			if(contractList!=null && contractList.size()>0){//自己买单
				isCost=1;//收费
				//获取最新的服务周期
				ServicePeriod  servicePeriod =getAdvanceServicePeriod(siteCode,null);
				if(servicePeriod!=null){
					isAdvance=1;
					servicePeroidId=servicePeriod.getId();
				}
			}else{//组织单位是否买单
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("executeStatus", 1);//执行中
				map.put("isSearchTb", 1);//填报单位允许查看
				map.put("siteCode", siteCode);//10位网站标识码
				map.put("typeFlag", 1);//非抽查合同
				List<ContractInfo> conList=contractInfoServiceImpl.queryOrgContractBySite(map);
				if(conList!=null && conList.size()>0){
					isCost=1;//收费
					//判断这些合同中是否有高级版
					ServicePeriod  servicePeriod =getAdvanceServicePeriod(null,conList);
					if(servicePeriod!=null){
						isAdvance=1;
						servicePeroidId=servicePeriod.getId();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("isCost", isCost);
		resultMap.put("isAdvance", isAdvance);
		resultMap.put("servicePeroidId", servicePeroidId);
		return resultMap;
		
	}
	/**
	 * @Description: 获取高级版服务周期
	 * @author cuicx --- 2017年5月19日10:30:37   
	 * @return
	 */
	public ServicePeriod getAdvanceServicePeriod(String siteCode,List<ContractInfo> conList){
		
		Map<String, Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(siteCode)){
			map.put("siteCode", siteCode);
		}
		if(conList!=null && conList.size()>0){
			map.put("conList", conList);
		}
		map.put("executeStatus", 1);//服务中合同
		map.put("contractCodeSpot", "false");
		map.put("comboId", 4);//高级版
		map.put("status", 1);//周期服务中
		map.put("executeStatus", 1);

		List<ServicePeriod> serviceList=servicePeriodServiceImpl.queryAdvanceService(map);
		if(serviceList!=null && serviceList.size()>0){
			return serviceList.get(0);
		}else{
			return null;
		}
		
		
	}
	/**
	 * @Description:填报单位获取高级版服务周期  1、填报单位自己有高级版 2、填报单位没有高级版但是其组织单位有高级版并且允许填报单位查看
	 * @author cuichx --- 2017-5-18下午2:05:23
	 */
	public ServicePeriod getAdvanceTbInfo(String siteCode){
		
		//获取最新的服务周期
		ServicePeriod servicePeriodTB =getAdvanceServicePeriod(siteCode,null);
		if(servicePeriodTB!=null){//1、填报单位自己有高级版 
			return servicePeriodTB;
		}else{
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("executeStatus", 1);//执行中
			map.put("isSearchTb", 1);//填报单位允许查看
			map.put("siteCode", siteCode);//10位网站标识码
			map.put("typeFlag", 1);//非抽查合同
			List<ContractInfo> conList=contractInfoServiceImpl.queryOrgContractBySite(map);
			if(conList!=null && conList.size()>0){
				//判断这些合同中是否有高级版
				ServicePeriod  servicePeriodORG =getAdvanceServicePeriod(null,conList);
				if(servicePeriodORG!=null){
					return servicePeriodORG;
				}else{
					return null;
				}
			}else{
				return null;
			}
		}
	}
	
	
	/************************************ 新产品信息---微信使用 *****************************************************/
	/**
	 * @Description: 根据标识码、产品分类、日期获取产品信息
	 * @author cuichx --- 2017-3-22下午3:56:39     
	 * @param siteCode
	 * @param productType  产品分类（可随时增加）:1.全面检测 2.抽查
	 * @param nowDate
	 * @param executeStatus  执行状态 作废：-1，初始化：0，服务中：1，服务结束：2
	 * @param orderTypes 订单分类 1云监管订单/2云监测订单/3云安全订单/4云搜索订单/5本地部署订单
	 * @param typeFlag 只查询 1 组织单位 2 填报单位(注意：传typeFlag值就不要排序)
	 * @return
	 */
	/*public List<CrmProductsResponse> getCrmProductsList(String siteCode,Integer typeFlag) {
		List<CrmProductsResponse> crmlist = new ArrayList<CrmProductsResponse>();
		CrmProductsRequest crmReq = new CrmProductsRequest();
		List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
		QueryOrder siteQueryOrder = new QueryOrder("co.order_end_time", QueryOrderType.DESC);
		queryOrderList.add(siteQueryOrder);
		try {
			crmReq.setSiteCode(siteCode);
			crmReq.setProductType(1);
			crmReq.setExecuteStatus(1); 
			crmReq.setOrderTypes(1);

			if (typeFlag != null) {
				if (typeFlag == 1) {
					crmReq.setOrgFlag(1);
				} else {
					crmReq.setTbFlag(1);
				}
			} else {
				crmReq.setQueryOrderList(queryOrderList);
			}
			crmlist = crmProductsServiceImpl.getCrmProductsList(crmReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return crmlist;
	}*/
	/*
	 * public List<CrmProductsResponse> getCrmProductsList(String
	 * siteCode,Integer typeFlag) { List<CrmProductsResponse> crmlist = new
	 * ArrayList<CrmProductsResponse>(); CrmProductsRequest crmReq = new
	 * CrmProductsRequest(); List<QueryOrder> queryOrderList = new
	 * ArrayList<QueryOrder>(); QueryOrder siteQueryOrder = new
	 * QueryOrder("co.order_end_time", QueryOrderType.DESC);
	 * queryOrderList.add(siteQueryOrder); try { crmReq.setSiteCode(siteCode);
	 * crmReq.setProductType(1); crmReq.setExecuteStatus(1);
	 * crmReq.setOrderTypes(1);
	 * 
	 * if (typeFlag != null) { if (typeFlag == 1) { crmReq.setOrgFlag(1); } else
	 * { crmReq.setTbFlag(1); } } else {
	 * crmReq.setQueryOrderList(queryOrderList); } crmlist =
	 * crmProductsServiceImpl.getCrmProductsList(crmReq); } catch (Exception e)
	 * { e.printStackTrace(); } return crmlist; }
	 */
	

	/**
	 * @Description: 根据当前登录的用户获取  订单内所有的siteCode(微信版专用)
	 * @author cuichx --- 2016-3-22上午11:21:30     
	 * @param leval 当前级别
	 * @param menuType 类型 
	 * @param siteCode  网站标识码
	 * @return
	 */
	public List<DatabaseInfo> queryDatebaseInfoListByType2(int level, String menuType, String siteCode) {

		DatabaseTreeInfoRequest databaseTreeInfoRequest = new DatabaseTreeInfoRequest();
		databaseTreeInfoRequest.setOrgSiteCode(siteCode);
		databaseTreeInfoRequest.setIsLink(1);
		databaseTreeInfoRequest.setPageSize(Integer.MAX_VALUE);

		List<DatabaseInfo> currentNextSiteCode = new ArrayList<DatabaseInfo>();

		try {
			//int level = getCurrentUserInfo().getUserType();
			if (StringUtils.isEmpty(menuType)) {//全部
				databaseTreeInfoRequest.setTypeIn(DatabaseLinkType.ISORGANIZATIONAL.getCode() + "," + DatabaseLinkType.DEPARTMENT.getCode() + "," + DatabaseLinkType.UNIT.getCode() + "," + DatabaseLinkType.OTHER.getCode());

			} else if (menuType.equals("1") && level == UserType.TYPE_PROVINCE.getCode()) {//省部门

				databaseTreeInfoRequest.setType(DatabaseLinkType.DEPARTMENT.getCode());

			} else if (menuType.equals("2") && level == UserType.TYPE_PROVINCE.getCode()) {//省登录--下级单位（市级）

				databaseTreeInfoRequest.setType(DatabaseLinkType.UNIT.getCode());

			} else if (menuType.equals("3") && level == UserType.TYPE_CITY.getCode()) {//市登录--市部门

				databaseTreeInfoRequest.setType(DatabaseLinkType.DEPARTMENT.getCode());

			} else if (menuType.equals("4") && level == UserType.TYPE_CITY.getCode()) {//市登录--下级单位

				databaseTreeInfoRequest.setType(DatabaseLinkType.UNIT.getCode());

			} else if (menuType.equals("3") && level == UserType.TYPE_COUNTY.getCode()) {//县登录  本级部门

				databaseTreeInfoRequest.setType(DatabaseLinkType.DEPARTMENT.getCode());

			} else if (menuType.equals("5")) {//省登录、市登录、县登录--门户网站

				databaseTreeInfoRequest.setType(DatabaseLinkType.ISORGANIZATIONAL.getCode());

			} else if (menuType.equals("6")) {//例外

				databaseTreeInfoRequest.setType(DatabaseLinkType.EXCEPTION.getCode());

			} else if (menuType.equals("7")) {//关停

				databaseTreeInfoRequest.setType(DatabaseLinkType.CLOSE.getCode());

			} else if (menuType.equals("8")) {//其他

				databaseTreeInfoRequest.setType(DatabaseLinkType.OTHER.getCode());
			}

			List<DatabaseTreeInfo> queryList = databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
			if (!CollectionUtils.isEmpty(queryList)) {
				for (DatabaseTreeInfo databaseTreeInfo : queryList) {

					DatabaseInfoRequest dRequest = new DatabaseInfoRequest();
					dRequest.setSiteCode(databaseTreeInfo.getSiteCode());
					List<DatabaseInfo> list = databaseInfoServiceImpl.queryList(dRequest);
					if(list.size()>0){
						currentNextSiteCode.add(list.get(0));
					}

				}
			}
			//为了防止currentNextSiteCode为空，程序报错
			if (currentNextSiteCode.size() < 1) {
				DatabaseInfo databaseInfo = new DatabaseInfo();
				databaseInfo.setSiteCode("0");
				currentNextSiteCode.add(databaseInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentNextSiteCode;
	}

	/**
	 * @Description: 根据当前登录的用户获取  订单内所有的siteCode
	 * @author sunjiang --- 2016-1-20上午10:53:28     
	 * @param menuType 类型  
	 * @param parapm	参数
	 * @param siteCode  网站标识码
	 * @return
	 */
	public List<DatabaseInfo> queryDatebaseInfoListByType(String menuType, String siteCode) {
		DatabaseTreeInfoRequest databaseTreeInfoRequest = new DatabaseTreeInfoRequest();
		databaseTreeInfoRequest.setOrgSiteCode(siteCode);

		databaseTreeInfoRequest.setPageSize(Integer.MAX_VALUE);

		List<DatabaseInfo> currentNextSiteCode = new ArrayList<DatabaseInfo>();

		try {
			int level = getCurrentUserInfo().getUserType();

			//			Map<String,Object> parapm = new HashMap<String, Object>();
			//			
			//			parapm.put("siteCode", siteCode);
			//			parapm.put("isScan", "1");

			if (StringUtils.isEmpty(menuType)) {//全部

				databaseTreeInfoRequest.setTypeIn(DatabaseLinkType.ISORGANIZATIONAL.getCode() + "," + DatabaseLinkType.DEPARTMENT.getCode() + "," + DatabaseLinkType.UNIT.getCode() + "," + DatabaseLinkType.OTHER.getCode());
				//				if(level==UserType.TYPE_PROVINCE.getCode()){
				//					parapm.put("sheng", "sheng,");
				//					
				//				}else if(level==UserType.TYPE_CITY.getCode()){
				//					
				//					if(siteCode.equals("310100")||siteCode.equals("110100")||siteCode.equals("120000")||siteCode.equals("500100")){
				//						parapm.put("siteCode", siteCode.substring(0, 2));
				//						parapm.put("zhishi", "zhishi");
				//					}else{
				//						
				//						
				//						if(siteCode.substring(0, 2).equals("bm")){
				//							
				//							parapm.put("siteCode", siteCode.substring(0, 4));
				//							if(siteCode.substring(4, 6).equals("00")){
				//								parapm.put("sheng", "sheng,");
				//							}else{
				//								parapm.put("level", "2");
				//							}
				//						}else{
				//							
				//							if(siteCode.equals("310100")||siteCode.equals("110100")||siteCode.equals("120000")||siteCode.equals("500100")){
				//								parapm.put("siteCode", siteCode.substring(0, 2));
				//							}else{
				//								parapm.put("siteCode", siteCode.substring(0, 4));
				//							}
				//							parapm.put("shi", "shi");
				//							
				//							
				//							
				//						}
				//					}
				//				}else if(level==UserType.TYPE_COUNTY.getCode()){
				//					if(!StringUtils.isEmpty(menuType)){
				//						
				//						parapm.put("isorganizational", "0");
				//					}
				//				}

				//&&siteCode.substring(0, 2).equals("bm")
			} else if (menuType.equals("1") && level == UserType.TYPE_PROVINCE.getCode()) {//省部门
				//				parapm.put("isorganizational", "0");
				//				parapm.put("level", "1");

				databaseTreeInfoRequest.setType(DatabaseLinkType.DEPARTMENT.getCode());

			} else if (menuType.equals("2") && level == UserType.TYPE_PROVINCE.getCode()) {//省登录--下级单位（市级）
				//				parapm.put("level", "2");
				//				parapm.put("isorganizational", "1");
				//				parapm.put("siteCode", siteCode.substring(0, 2));

				databaseTreeInfoRequest.setType(DatabaseLinkType.UNIT.getCode());

			} else if (menuType.equals("3") && level == UserType.TYPE_CITY.getCode()) {//市登录--市部门

				//				if(siteCode.substring(0, 2).equals("bm")){
				//					
				//					parapm.put("siteCode", siteCode.substring(0, 4));
				//					
				//					if(siteCode.substring(4, 6).equals("00")){
				//						parapm.put("bmsheng", "bmsheng,");
				//					}else{
				//						parapm.put("level", "2");
				//					}
				//					
				//				}else{
				//					parapm.put("level", "2");
				//				}
				//				parapm.put("siteCode", siteCode.substring(0, 4));
				//				
				//				parapm.put("isorganizational", "0");

				databaseTreeInfoRequest.setType(DatabaseLinkType.DEPARTMENT.getCode());

			} else if (	menuType.equals("4") && (level == UserType.TYPE_CITY.getCode()
					|| level == UserType.TYPE_ADMIN.getCode()//bm0100 下属单位
					|| level == UserType.TYPE_COUNTY.getCode())) {//市登录--下级单位
				//				if(siteCode.substring(0, 2).equals("bm")){
				//					
				//					parapm.put("siteCode", siteCode.substring(0, 4));
				//					parapm.put("level", "2");
				//				}else{
				//					if(siteCode.equals("310100")||siteCode.equals("110100")||siteCode.equals("120000")||siteCode.equals("500100")){
				//						parapm.put("level", "2");
				//					}else{
				//						parapm.put("siteCode", siteCode.substring(0, 4));
				//						parapm.put("level", "3");
				//					}
				//				}
				//				parapm.put("isorganizational", "1");

				databaseTreeInfoRequest.setType(DatabaseLinkType.UNIT.getCode());

			}else if (menuType.equals("3") && level == UserType.TYPE_COUNTY.getCode()) {//部委登录  本级部门

				//				parapm.put("isorganizational", "0");

				databaseTreeInfoRequest.setType(DatabaseLinkType.DEPARTMENT.getCode());

			} else if (menuType.equals("5")) {//省登录、市登录、县登录--门户网站
				//				parapm.put("isorganizational", "1");

				databaseTreeInfoRequest.setType(DatabaseLinkType.ISORGANIZATIONAL.getCode());

			} else if (menuType.equals("6")) {//例外

				//				parapm.put("isexp", "2");
				databaseTreeInfoRequest.setType(DatabaseLinkType.EXCEPTION.getCode());

			} else if (menuType.equals("7")) {//关停

				//				parapm.put("isexp", "3");

				databaseTreeInfoRequest.setType(DatabaseLinkType.CLOSE.getCode());

			} else if (menuType.equals("8")) {//其他

				databaseTreeInfoRequest.setType(DatabaseLinkType.OTHER.getCode());
				//				parapm.put("isexp", "4");

			}
			if(menuType != null && !"".equals(menuType)){
				int type = databaseTreeInfoRequest.getType();
				if(type == 1 || type == 2 || type == 3 || type ==6){
					databaseTreeInfoRequest.setType(type);
					databaseTreeInfoRequest.setIsexp(1);
				}else if(type == 4){
					databaseTreeInfoRequest.setType(null);
					databaseTreeInfoRequest.setIsexp(2);
				}else if(type == 5){
					databaseTreeInfoRequest.setType(null);
					databaseTreeInfoRequest.setIsexp(3);
				}
			}else{
				databaseTreeInfoRequest.setIsexp(1);
			}
			List<DatabaseTreeInfo> queryList = databaseTreeInfoServiceImpl.queryListJoinInfo(databaseTreeInfoRequest);
			if (!CollectionUtils.isEmpty(queryList)) {
				for (DatabaseTreeInfo databaseTreeInfo : queryList) {
					DatabaseInfoRequest dRequest = new DatabaseInfoRequest();
					dRequest.setSiteCode(databaseTreeInfo.getSiteCode());
					List<DatabaseInfo> list = databaseInfoServiceImpl.queryList(dRequest);
					if(list.size()>0){
						currentNextSiteCode.add(list.get(0));
					}
				}

			}

			//			currentNextSiteCode = databaseInfoServiceImpl.querySiteCodeList(parapm);
			//为了防止currentNextSiteCode为空，程序报错
			if (currentNextSiteCode.size() < 1) {
				DatabaseInfo databaseInfo = new DatabaseInfo();
				databaseInfo.setSiteCode("0");
				currentNextSiteCode.add(databaseInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return currentNextSiteCode;

	}

	/**
	 * @Description: 根据当前登录的用户和menuType获取 DetectionOrgCount表type
	 * @author liujc --- 2016年5月25日10:19:26     
	 * @param menuType 类型  
	 * @param siteCode  网站标识码
	 * @return String type 类型  
	 */
	public String queryDetectionOrgCountByType(String menuType, String siteCode) {

		String type = "0";
		try {
			int level = getCurrentUserInfo().getUserType();
			logger.info("BaseAction queryDetectionOrgCountByType() menuType===="+menuType);
			logger.info("BaseAction queryDetectionOrgCountByType() level======"+level);
			if (StringUtils.isEmpty(menuType)) {
				type = "0";
			} else if (menuType.equals("1") && level == UserType.TYPE_PROVINCE.getCode()) {//省部门

				type = DatabaseLinkType.DEPARTMENT.getCode().toString();
				//				databaseLinkRequest.setType();

			} else if (menuType.equals("2") && level == UserType.TYPE_PROVINCE.getCode()) {//省登录--下级单位（市级）
				type = DatabaseLinkType.UNIT.getCode().toString();

			} else if (menuType.equals("3") && level == UserType.TYPE_CITY.getCode()) {//市登录--市部门

				type = DatabaseLinkType.DEPARTMENT.getCode().toString();
				//				databaseLinkRequest.setType(DatabaseLinkType.DEPARTMENT.getCode());

			} else if (menuType.equals("3") && level == UserType.TYPE_COUNTY.getCode()) {//县级登录  本级部门

				type = DatabaseLinkType.DEPARTMENT.getCode().toString();
				//				databaseLinkRequest.setType(DatabaseLinkType.DEPARTMENT.getCode());

			} else if (menuType.equals("4") && (level == UserType.TYPE_CITY.getCode()
					|| level == UserType.TYPE_ADMIN.getCode()//bm0100 下属单位
					|| level == UserType.TYPE_COUNTY.getCode())) {//市登录--下级单位
				type = DatabaseLinkType.UNIT.getCode().toString();
				//				databaseLinkRequest.setType(DatabaseLinkType.UNIT.getCode());

			}else if (menuType.equals("8")) {//其他
				type = DatabaseLinkType.OTHER.getCode().toString();
				//				databaseLinkRequest.setType(DatabaseLinkType.OTHER.getCode());
				//				parapm.put("isexp", "4");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return type;

	}

	/**
	 * 
	 * 描述:获取当前登入人的订单里面下面所有的siteCode和网站名称
	 * 
	 * 作者：lxx	2015-11-20上午11:50:29
	 * @return
	 */
	public List<DatabaseInfo> getCurrentNextSiteCode() {
		String currentSiteCode = getCurrentSiteCode();

		DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
		databaseInfoRequest.setSiteCodeLike(currentSiteCode);

		List<DatabaseInfo> list = databaseInfoServiceImpl.queryList(databaseInfoRequest);
		//为了防止currentNextSiteCode为空，程序报错
		if (list.size() < 1) {
			DatabaseInfo databaseInfo = new DatabaseInfo();
			databaseInfo.setSiteCode("0");
			list.add(databaseInfo);
		}
		return list;
	}

	/**
	 * 
	 * 描述:截取当前siteCode规则
	 * 
	 * 作者：lxx	2015-11-25下午02:06:46
	 * @return
	 */
	public String getSiteCodeLike() {

		String siteCode = getCurrentSiteCode();
		if ("admin".equals(siteCode)) {
			return "";
		} else if (siteCode.endsWith("0000")) {
			return siteCode.substring(0, 2);
		} else if (siteCode.endsWith("00")) {
			return siteCode.substring(0, 4);
		} else {
			return siteCode;
		}
	}
	
	public DatabaseInfo getDatabaseInfo(String siteCode){
		DatabaseInfoRequest request = new DatabaseInfoRequest();
		request.setSiteCode(siteCode);
		List<DatabaseInfo> list = databaseInfoServiceImpl.queryList(request);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 
	 * @描述:百分率
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年1月22日下午3:01:41
	 * @param dividend  被除数 
	 * @param divisor 除数
	 * @return
	 */
	public String totalPercentage(float dividend, float divisor) {
		String per = com.publics.util.utils.StringUtils
				.getPrettyNumber(com.publics.util.utils.StringUtils.formatDouble(2, (double) dividend / divisor * 100));
		return per;
	}

	/**
	 * 
	 * @描述:是否存在下级单位
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年2月14日上午11:43:42
	 * @return
	 */
	public List<DatabaseTreeInfo> getTreeInfoList(String siteCode) {
		List<DatabaseTreeInfo> treeInfoList = new ArrayList<DatabaseTreeInfo>();
		DatabaseTreeInfoRequest request = new DatabaseTreeInfoRequest();
		try {
			if (StringUtils.isNotEmpty(siteCode)) {
				request.setOrgSiteCode(siteCode);
				treeInfoList = databaseTreeBizServiceImpl.getDatabaseTreeInfoList(request);
			}else{
				HttpSession session = getSession();
				String loginSiteCode = String.valueOf(session.getAttribute(Constants.LOGIN_SITECODE));
				request.setOrgSiteCode(loginSiteCode);
				treeInfoList = databaseTreeBizServiceImpl.getDatabaseTreeInfoList(request);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return treeInfoList;
	}

	/**
	 * 
	 * @描述:获取组织单位对应的网站标识码集合
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年2月16日下午5:25:25
	 * @param siteCode
	 * @param siteType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DatabaseTreeInfo> getTreeSiteCodeList(String siteCode, String siteType) {
		siteList = new ArrayList<DatabaseTreeInfo>();
		try {
			String cacheInfo = CacheType.MONITORING_DATABASETREEINFO.getName();
			String key = cacheInfo + siteCode + siteType; // 缓存名
			siteList = (List<DatabaseTreeInfo>) MonitoringCacheUtils.get(key); // 查询缓存中是否存在
			if (siteList == null) {
				DatabaseTreeInfoRequest request = new DatabaseTreeInfoRequest();
				request.setIsLink(DatabaseTreeInfoType.ISLINK.getCode());
				request.setIsexp(DatabaseInfoType.NORMAL.getCode());
				request.setOrgSiteCode(siteCode);
				if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
					request.setLayerType(Integer.valueOf(siteType));
				}
				siteList = databaseTreeBizServiceImpl.getDatabaseTreeInfoList(request);
				MonitoringCacheUtils.put(key, siteList); // 将数据存到缓存中
			}
			if (siteList == null || siteList.size() <= 0) { // 为了防止siteList为空，程序报错
				DatabaseTreeInfo tree = new DatabaseTreeInfo();
				tree.setSiteCode("0");
				siteList.add(tree);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siteList;
	}
	/**
	 * @描述:获取概览页面 配置日期
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-5-3上午9:36:34 
	 * @return
	 */
	public String queryHomePageDate(){
		String val = bigDataHomeBizServiceImpl.queryHomePageDate();
		
		return  val;
	}
	/**
	 * @描述:大数据获取配置查询日期
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-5-3上午11:19:13 
	 * @return
	 */
	public String queryDate(){
		String  scanDate = bigDataHomeBizServiceImpl.queryDate();
		return scanDate;
	}
	/**
	 * @Description: 获取页面上送的json数据
	 * @author cuichx --- 2015-10-9上午10:58:21
	 * @return
	 * @throws IOException
	 */
	protected JSONObject getJSONObject() throws IOException {
		StringBuilder stb = new StringBuilder();
		String s = null;
		BufferedReader br = request.getReader();
		while ((s = br.readLine()) != null) {
			stb.append(s);
		}
		if (stb == null || stb.length() <= 0) {
			return null;
		}
		JSONObject jo = JSONObject.fromObject(stb.toString());
		return jo;
	}
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(Integer recordSize) {
		this.recordSize = recordSize;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
