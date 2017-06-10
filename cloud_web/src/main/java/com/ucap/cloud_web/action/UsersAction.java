package com.ucap.cloud_web.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.constant.CloudAnalyzeErrorType;
import com.ucap.cloud_web.constant.CloudAnalyzeType;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DicConfigRequest;
import com.ucap.cloud_web.dto.MenuInfoRequest;
import com.ucap.cloud_web.dtoResponse.VerJsonResponse;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.MenuInfo;
import com.ucap.cloud_web.entity.UserLog;
import com.ucap.cloud_web.entity.Users;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.service.IDicItemService;
import com.ucap.cloud_web.service.IMenuInfoService;
import com.ucap.cloud_web.service.IUserLogService;
import com.ucap.cloud_web.service.IUsersService;
import com.ucap.cloud_web.service.exception.EmailException;
import com.ucap.cloud_web.service.exception.IncorrectCaptchaException;
import com.ucap.cloud_web.shiro.CaptchaUsernamePasswordToken;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.shiro.Json;
import com.ucap.cloud_web.shiro.ShiroUser;
import com.ucap.cloud_web.util.DesTwo;
import com.ucap.cloud_web.util.HttpClientUtils;
import com.ucap.cloud_web.util.SendEmail;
import com.ucap.cloud_web.util.StringEncrypt;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UsersAction extends BaseAction {

	private static Logger logger = Logger.getLogger(UsersAction.class);

	@Autowired
	private IUsersService usersServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IUserLogService userLogServiceImpl;
	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	UrlAdapterVar urlAdapterVar;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IDicItemService dicItemServiceImpl;
	@Autowired
	private IMenuInfoService menuInfoServiceImpl;
	
	
	private String userName;
	private String passWord;
	private String data;
	private String captcha;
	private String type;//首页面 1 监测页面数  2发现问题数  
	private HashMap<String, Object> resultMap = new HashMap<String, Object>();
	// private List<MenuModel> listmenu;
	private int userType;
	Json json = null;
	
	/**
	 * @Description: 获取登录页面的健康指数，以及监测页面的个数  旧方法
	 * @author sunjiang --- 2016-3-11下午3:24:17
	 * 
	 * @author yangshuai --- 修改2016-5-25上午9:56:08
	 * 从本系统库中取得健康指数，以及监测页面的个数;原通过接口获取
	 */
	public void getLoginNumBak(){
		try {
			Map<String, Object>  params = new HashMap<String, Object>();
			String configId = "7,8,9,10,11,12";
			String[] codeArray = configId.split(",");
			params.put("configIds", codeArray);
			
			List<DicConfigRequest> dicConfig = dicConfigServiceImpl.queryListByMap(params);
			//resultMap.put("dicConfig",dicConfig);
			resultMap.put("databaseInfoCount", dicConfig.get(0).getValue());//监测网站数
			resultMap.put("pageNum", dicConfig.get(1).getValue());//监测页面数
			resultMap.put("errorNum", dicConfig.get(2).getValue());//发现问题数
			resultMap.put("healthScores", dicConfig.get(3).getValue());//健康指数
			resultMap.put("differentialRate", dicConfig.get(4).getValue());//上升健康分数比率
			resultMap.put("differential", dicConfig.get(5).getValue());//上升健康指数
			if (Double.parseDouble(dicConfig.get(5).getValue()) <= 0) {
				resultMap.put("bottom", "bottom");//样式
			}

			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 获取登录页面的健康指数，以及监测页面的个数  新方法
	 * @author sunjiang --- 2016-3-11下午3:24:17
	 * 
	 * @author yangshuai --- 修改2016-5-25上午9:56:08
	 * 从本系统库中取得健康指数，以及监测页面的个数;原通过接口获取
	 */
	@SuppressWarnings("unchecked")
	public void getLoginNum(){
		try {
			HashMap<String, Object> returnMap = new HashMap<String, Object>();
			HttpSession session=request.getSession();
			HashMap<String, Object> sessionMap=(HashMap<String, Object>) session.getAttribute("dicConfig");
			int maxNum=0;
			int minNum=0;
			int calculateNum=0;
			if(sessionMap != null && sessionMap.size()>0){
				if(StringUtils.isNotEmpty(type)){
					if(type.equals("1")){
						//监测页面数
						String num=sessionMap.get("pageNum").toString();
						
						maxNum=30;
						minNum=10;
						calculateNum=maxNum-minNum;
						Double randomNum=Math.floor( Math.random()*calculateNum+minNum);
						
						BigDecimal randomNumEnd =new BigDecimal(num);
//						Integer randomNumEnd=num+Integer.valueOf(randomNum.intValue());
						sessionMap.put("pageNum", randomNumEnd.add(new BigDecimal(Integer.valueOf(randomNum.intValue()))));//监测页面数
					}else{
						//发现问题数
						Integer num=Integer.valueOf(sessionMap.get("errorNum").toString());
						maxNum=8;
						minNum=2;
						calculateNum=maxNum-minNum;
						Double randomNum=Math.floor( Math.random()*calculateNum+minNum);
						BigDecimal randomNumEnd =new BigDecimal(num);
//						Integer randomNumEnd=num+Integer.valueOf(randomNum.intValue());
						sessionMap.put("errorNum", randomNumEnd.add(new BigDecimal(Integer.valueOf(randomNum.intValue()))));//发现问题数
					}
					
				}
				session.setAttribute("dicConfig",sessionMap);
				returnMap.putAll(sessionMap);
			}else{
				Map<String, Object>  params = new HashMap<String, Object>();
				String configId = "7,8,9,10,11,12";
				String[] codeArray = configId.split(",");
				params.put("configIds", codeArray);
				List<DicConfigRequest> dicConfig = dicConfigServiceImpl.queryListByMap(params);
				//resultMap.put("dicConfig",dicConfig);
				returnMap.put("databaseInfoCount", dicConfig.get(0).getValue());//监测网站数
				String pageNum=dicConfig.get(1).getValue();
				String errorNum=dicConfig.get(2).getValue();
				returnMap.put("pageNum", pageNum);//监测页面数
				returnMap.put("errorNum", errorNum);//发现问题数
				returnMap.put("healthScores", dicConfig.get(3).getValue());//健康指数
				returnMap.put("differentialRate", dicConfig.get(4).getValue());//上升健康分数比率
				returnMap.put("differential", dicConfig.get(5).getValue());//上升健康指数
				if (Double.parseDouble(dicConfig.get(5).getValue()) <= 0) {
					returnMap.put("bottom", "bottom");//样式
				}
				session.setAttribute("dicConfig",returnMap);
			}
			writerPrint(JSONObject.fromObject(returnMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 收费  登录页面
	 * @author sunjiang --- 2016-3-23下午6:02:35     
	 * @return
	 */
	public String logins() {
		return SUCCESS;
	}
	/**
	 * @Description: 收费  登录form提交
	 * @author sunjiang --- 2016-3-23下午6:02:49
	 */
	public void loginsForm(){
		Subject subject = SecurityUtils.getSubject();
		// 如果用户选择了记住我功能，则直接去登陆
		if (!subject.isAuthenticated() && subject.isRemembered()) {
			String username = subject.getPrincipal().toString();
			Users users = usersServiceImpl.getUsers(username);
			if (users != null) {
				CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
				token.setUsername(username);

				if (!StringUtils.isEmpty(users.getPassword())) {
					token.setPassword(users.getPassword().toCharArray());
				}
				token.setRememberMe(true);
				subject.login(token);
			}
		}
		String memberPass = request.getParameter("memberPass");
		boolean remberMe = false;
		if (!StringUtils.isEmpty(memberPass)) {
			remberMe = Boolean.valueOf(memberPass);
		}
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		if (StringUtils.isNotEmpty(userName)) {
			userName = userName.trim();
		}
		if (StringUtils.isNotEmpty(passWord)) {
			passWord = passWord.trim();
		}
		token.setUsername(userName);
		if (!StringUtils.isEmpty(passWord)) {
			token.setPassword(passWord.toCharArray());
		}
		token.setCaptcha(captcha);
		token.setRememberMe(remberMe);

		json = new Json();
		json.setTitle("登录提示");
		try {
			subject.login(token);
			json.setStatus(true);
			System.out.println(subject.getSession() + ">>>>>>>>>>>>>>>>>");
			addUserLog(userName);
			subject.getSession().setAttribute(Constants.LOGIN_LOGIN_OUT_URL, Constants.LOGIN_LOGINS_OUT_URL);
		} catch (UnknownSessionException use) {
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			logger.error(Constants.UNKNOWN_SESSION_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
		} catch (UnknownAccountException ex) {
			// json.setMessage(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
		} catch (IncorrectCredentialsException ice) {
			json.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
		} catch (LockedAccountException lae) {
			json.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
		} catch (IncorrectCaptchaException ice) {
			json.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		} catch (ExcessiveAttemptsException eae) {
			json.setMessage(Constants.ACCOUNT_IS_LOCKED);
		} catch (AuthenticationException ae) {
			json.setMessage(Constants.AUTHENTICATION_EXCEPTION);
		} catch (Exception e) {
			json.setMessage(Constants.UNKNOWN_EXCEPTION);
		}
		json.setSiteCode(userName);
		writerPrint(JSONObject.fromObject(json).toString());
	}
	
	/**
	 * 
	 * 描述:不需要登录直接展示
	 * 
	 * 作者：lixx@ucap.com.cn	2016-8-22上午11:39:58
	 */
	public void loginIndex(){
		Subject subject = SecurityUtils.getSubject();
		// 如果用户选择了记住我功能，则直接去登陆
		if (!subject.isAuthenticated() && subject.isRemembered()) {
			String username = subject.getPrincipal().toString();
			Users users = usersServiceImpl.getUsers(username);
			if (users != null) {
				CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
				token.setUsername(username);

				if (!StringUtils.isEmpty(users.getPassword())) {
					token.setPassword(users.getPassword().toCharArray());
				}
				token.setRememberMe(true);
				subject.login(token);
			}
		}
		String memberPass = request.getParameter("memberPass");
		boolean remberMe = false;
		if (!StringUtils.isEmpty(memberPass)) {
			remberMe = Boolean.valueOf(memberPass);
		}

		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		
//		String ss=StringEncrypt.encrypt("userName=340100&passWord=hfxx8082", StringEncrypt.PASSWORD_CRYPT_KEY);
//		System.out.println(ss);
//		String q="1A1726E8EB9A5AF9D031914B52EBFB60D83078FE693FD85D5EC480317B80846621D57A3A603C6AD1";
		if(StringUtils.isNotEmpty(data)){
			data = data.trim();
		}
		String dataString = StringEncrypt.decrypt(data, StringEncrypt.PASSWORD_CRYPT_KEY);
		
		if(dataString != null){
			userName = dataString.split("&")[0].split("=")[1];
			passWord = dataString.split("&")[1].split("=")[1];
			System.out.println(userName+"<<<<<<<<<<<<<<>>>>>>>>>>>"+passWord+":"+data);
		}
		
		if(StringUtils.isNotEmpty(userName)){
			userName = userName.trim();
		}
		if(StringUtils.isNotEmpty(passWord)){
			passWord = passWord.trim();
		}
		
		token.setUsername(userName);
		
		if (!StringUtils.isEmpty(passWord)) {
			token.setPassword(passWord.toCharArray());
		}
		token.setCaptcha(captcha);
		token.setRememberMe(remberMe);

		
		json = new Json();
		json.setTitle("登录提示");
		try {
			subject.login(token);
			json.setStatus(true);
			
			subject.getSession().setAttribute(Constants.LOGIN_LOGIN_OUT_URL, Constants.LOGIN_LOGINS_OUT_URL);
			HttpSession session = getSession();
			session.setAttribute(Constants.LOGIN_SITECODE, userName); // sitecode标识码
			try { 
				List<MenuInfo> menuList = new ArrayList<MenuInfo>();
				MenuInfoRequest menuReq = new MenuInfoRequest();
				String gUrl = "";
				if(userName.length() <= 6){
//					menuReq.setOnlyValue("connectionHomeDetail");  // 组织首页
//					menuList = menuInfoServiceImpl.queryList(menuReq);
//					if(CollectionUtils.isNotEmpty(menuList)){
//						gUrl = menuList.get(0).getGUrl(); // 地址
//						response.sendRedirect(request.getContextPath() + "/" + gUrl);
//					}
					response.sendRedirect(request.getContextPath()+"/connectionHomeDetail_indexOrg.action");
				} else {
//					menuReq.setOnlyValue("fillUnit");  // 填报首页
//					menuList = menuInfoServiceImpl.queryList(menuReq);
//					if(CollectionUtils.isNotEmpty(menuList)){
//						gUrl = menuList.get(0).getGUrl(); // 地址
//						response.sendRedirect(request.getContextPath() + "/" + gUrl);
//					}
					response.sendRedirect(request.getContextPath()+"/fillUnit_gailan.action");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnknownSessionException use) {
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			logger.error(Constants.UNKNOWN_SESSION_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
		} catch (UnknownAccountException ex) {
		} catch (IncorrectCredentialsException ice) {
			json.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
		} catch (LockedAccountException lae) {
			json.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
		} catch (IncorrectCaptchaException ice) {
			json.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		} catch (ExcessiveAttemptsException eae) {
			json.setMessage(Constants.ACCOUNT_IS_LOCKED);
		} catch (AuthenticationException ae) {
			json.setMessage(Constants.AUTHENTICATION_EXCEPTION);
		} catch (Exception e) {
			json.setMessage(Constants.UNKNOWN_EXCEPTION);
		}
		json.setSiteCode(userName);
		writerPrint(JSONObject.fromObject(json).toString());
		
	}
	/**
	 * 
	 * @描述:云分析php登录验证接口
	 * @作者:zhaodongy@ucap.com.cn
	 * @时间:2017-2-24下午2:13:22
	 */
	public void loginDes() {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {
			String siteCode = request.getParameter("siteCode");
			String mCode = request.getParameter("mCode");
			String key = request.getParameter("key");
			if (StringUtils.isNotEmpty(siteCode)
					&& StringUtils.isNotEmpty(mCode)) {
				key = "ucap2016";
				if (StringUtils.isNotEmpty(siteCode)
						&& StringUtils.isNotEmpty(mCode)) {
					siteCode = DesTwo.decryptDES(siteCode, key);
					mCode = DesTwo.decryptDES(mCode, key);
					List<DatabaseTreeInfo> treeInfoList = getTreeInfoList(siteCode);
					if (treeInfoList != null && treeInfoList.size() > 0) {
						DatabaseOrgInfoRequest databaseOrgInfoRequest = new DatabaseOrgInfoRequest();
						databaseOrgInfoRequest.setStieCode(siteCode);

						List<DatabaseOrgInfo> queryOrgList = databaseOrgInfoServiceImpl
								.queryList(databaseOrgInfoRequest);
						if (queryOrgList != null && queryOrgList.size() > 0) { // 有此账号
							databaseOrgInfoRequest.setVcode(mCode);
							List<DatabaseOrgInfo> queryOrgList1 = databaseOrgInfoServiceImpl
									.queryList(databaseOrgInfoRequest);
							if (queryOrgList1 != null
									&& queryOrgList1.size() > 0) { // 密码账号相符
								returnMap.put("status",
										CloudAnalyzeErrorType.C.getCode()); // 正常登陆 3
							} else {
								returnMap.put("status",
										CloudAnalyzeErrorType.B.getCode()); // 账号密码不符 2 
							}
						} else {
							returnMap.put("status",
									CloudAnalyzeErrorType.A.getCode()); // 无此账号 1
						}

					} else {
						DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
						databaseInfoRequest.setSiteCode(siteCode);
						List<DatabaseInfo> queryList = databaseInfoServiceImpl
								.queryList(databaseInfoRequest);
						if (queryList != null && queryList.size() > 0) { // 有此账号
							databaseInfoRequest.setVcode(mCode);
							List<DatabaseInfo> queryList1 = databaseInfoServiceImpl
									.queryList(databaseInfoRequest);
							if (queryList1 != null && queryList1.size() > 0) { // 密码账号不符

								returnMap.put("status",
										CloudAnalyzeErrorType.C.getCode()); // 正常登陆 3
							} else {
								returnMap.put("status",
										CloudAnalyzeErrorType.B.getCode()); // 账号密码不符 2
							}
						} else {
							returnMap.put("status",
									CloudAnalyzeErrorType.A.getCode());// 无此账号 1
						}
					}
				} else {
					returnMap.put("status", CloudAnalyzeErrorType.B.getCode());  //账号密码为空  返回密码账号不符
				}
				System.out.println("loginDes===="+JSONObject.fromObject(returnMap).toString());
				writerPrint(JSONObject.fromObject(returnMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("status", CloudAnalyzeErrorType.B.getCode()); //发生 异常
			writerPrint(JSONObject.fromObject(returnMap).toString());
			
		}

	}
	
	//登录易分析平台
		//http://www.yeefx.cn/api/kpy_site.php?mod=site&action=login
		public void loginYiAnalyze() {
			try {
				String cloudUrl= CloudAnalyzeType.YFX_API_URL.getName();
				DicItem  dic = dicItemServiceImpl.getByEnName("YFX_API_URL");
				//字典表里如果有数据 就字典表中的数据
				if(dic != null){
					if(dic.getValue() != null && dic.getValue() != ""){
						cloudUrl = dic.getValue();
					}
				}
				String url = cloudUrl + "?mod=site&action=login";
				System.out.println("loginYiAnalyze=======url=="+url);
				Map<String, String> params = getParams();
				params.put("mod", "site");
				params.put("action", "login");
				//这个是合同的截止时间  现在测试随便写的时间  毫秒级的
				//params.put("expirydate", CloudAnalyzeType.EXPIRYDATE.getName());
				if (params.containsKey("password")) {
					String pwd = params.get("password");
					params.remove("password");
					//params.put("password", DesTwo.encryptDES(pwd, null));  //去除加密
					params.put("password", pwd);
				}
				if (params.containsKey("username")) {
					String username = params.get("username");
					params.remove("username");
					//params.put("username", DesTwo.encryptDES(username, null));
					params.put("username", username);
				}
				
				VerJsonResponse verJson = databaseBizServiceImpl.verParams(params, "password|username");
				params.remove("token");
				params.remove("action");
				params.remove("mod");
				
				params.put("timestamp", verJson.getTimestamp());
				
				params.put("sign", verJson.getSign());
				if (!verJson.getStatus()) {
					OutputJson(verJson.getErrMsg());
					return;
				}
				System.out.println("after----loginYiAnalyze======temp==执行之前");
				String temp=HttpClientUtils.basicPost(url, params);
				System.out.println("loginYiAnalyze======temp=="+temp);
				if(temp.contains("code")){
					writerPrint(temp);
				}else{
					OutputJson(temp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		/**
		 * @描述:获取请求参数
		 * @作者:masl@ucap.com.cn
		 * @时间:2016年11月24日下午3:12:31 
		 * @return 
		 * @return Map<String,String>
		 */
		private Map<String, String> getParams() {
			Enumeration<?> pNames = request.getParameterNames();
			Map<String, String> params = new HashMap<String, String>();
			while (pNames.hasMoreElements()) {
				String pName = (String) pNames.nextElement();
				if ("sign".equals(pName))
					continue;

				Object pValue = request.getParameter(pName);
				String val = "";
				if (pValue != null) {
					val = pValue.toString().trim();
				}
				params.put(pName, val);
			}
			params.put("token", CloudAnalyzeType.TOKEN.getName());
			//params.put("timestamp", timestamp);
			return params;
		}
		
	/**
	 * @Description: 免费 登录form提交
	 * @author sunjiang --- 2016-3-28上午11:52:20
	 */
	public void loginForm(){
		Subject subject = SecurityUtils.getSubject();
		// 如果用户选择了记住我功能，则直接去登陆
		// if (!subject.isAuthenticated() && subject.isRemembered()) {
		// String username = subject.getPrincipal().toString();
		// Users users = usersServiceImpl.getUsers(username);
		// if (users != null) {
		// CaptchaUsernamePasswordToken token = new
		// CaptchaUsernamePasswordToken();
		// token.setUsername(username);
		//
		// if (!StringUtils.isEmpty(users.getPassword())) {
		// token.setPassword(users.getPassword().toCharArray());
		// }
		// token.setRememberMe(true);
		// subject.login(token);
		// }
		// }
		String memberPass = request.getParameter("memberPass");
		boolean remberMe = false;
		if (!StringUtils.isEmpty(memberPass)) {
			remberMe = Boolean.valueOf(memberPass);
		}
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		token.setUsername(userName);
		if (!StringUtils.isEmpty(passWord)) {
			token.setPassword(passWord.toCharArray());
		}
		token.setCaptcha(captcha);
		token.setRememberMe(remberMe);

		json = new Json();
		json.setTitle("登录提示");
		try {
			subject.login(token);
			// 验证成功
			json.setStatus(true);
			System.out.println(subject.getSession() + ">>>>>>>>>>>>>>>>>");
			addUserLog(userName);
			subject.getSession().setAttribute(Constants.LOGIN_LOGIN_OUT_URL, Constants.LOGIN_LOGIN_OUT_URL);
		} catch (UnknownSessionException use) {
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			logger.error(Constants.UNKNOWN_SESSION_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
		} catch (UnknownAccountException ex) {
			// json.setMessage(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
		} catch (IncorrectCredentialsException ice) {
			json.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
		} catch (LockedAccountException lae) {
			json.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
		} catch (IncorrectCaptchaException ice) {
			json.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		} catch (ExcessiveAttemptsException eae) {
			json.setMessage(Constants.ACCOUNT_IS_LOCKED);
		} catch (AuthenticationException ae) {
			json.setMessage(Constants.AUTHENTICATION_EXCEPTION);
		} catch (Exception e) {
			json.setMessage(Constants.UNKNOWN_EXCEPTION);
		}
		json.setSiteCode(userName);
		writerPrint(JSONObject.fromObject(json).toString());
	}
	
	/**
	 * 
	 * @描述:专属页面登录方法
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年2月24日上午10:50:33
	 * @return
	 */
	public String loginFormSP() {
		Subject subject = SecurityUtils.getSubject();
		String memberPass = request.getParameter("memberPass");
		boolean remberMe = false;
		if (!StringUtils.isEmpty(memberPass)) {
			remberMe = Boolean.valueOf(memberPass);
		}

		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		token.setUsername(userName);
		if (!StringUtils.isEmpty(passWord)) {
			token.setPassword(passWord.toCharArray());
		}
		token.setCaptcha(captcha);
		token.setRememberMe(remberMe);

		json = new Json();
		json.setTitle("登录提示");
		try {
			subject.login(token);
			// 验证成功
			json.setStatus(true);
			System.out.println(subject.getSession() + ">>>>>>>>>>>>>>>>>");
			addUserLog(userName);
			subject.getSession().setAttribute(Constants.LOGIN_LOGIN_OUT_URL, Constants.LOGIN_LOGIN_OUT_URL);
		} catch (UnknownSessionException use) {
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			logger.error(Constants.UNKNOWN_SESSION_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
		} catch (UnknownAccountException ex) {
			// json.setMessage(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
		} catch (IncorrectCredentialsException ice) {
			json.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
		} catch (LockedAccountException lae) {
			json.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
		} catch (IncorrectCaptchaException ice) {
			json.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		} catch (ExcessiveAttemptsException eae) {
			json.setMessage(Constants.ACCOUNT_IS_LOCKED);
		} catch (AuthenticationException ae) {
			json.setMessage(Constants.AUTHENTICATION_EXCEPTION);
		} catch (Exception e) {
			json.setMessage(Constants.UNKNOWN_EXCEPTION);
		}
		json.setSiteCode(userName);
		return SUCCESS;
	}

	/**
	 * @Description: 用户登录
	 * @author lixuxiang-- 2015-11-13 下午6:02:05
	 * @param @return
	 * @return String 返回类型
	 */
	public String login() {
		/*
		 * HttpSession session = getSession(); String loginCode =
		 * String.valueOf(session.getAttribute(Constants.LOGIN_CODE)); String
		 * loginSiteCode =
		 * String.valueOf(session.getAttribute(Constants.LOGIN_SITECODE));
		 * 
		 * if (loginCode.equals("2")) { if
		 * (StringUtils.isNotEmpty(loginSiteCode)) { List<DatabaseTreeInfo>
		 * treeInfoList = getTreeInfoList(); if (treeInfoList != null &&
		 * treeInfoList.size() > 0) { // 组织单位登录 return "loginOrg"; } else { //
		 * 填报单位登录 return "loginFill"; } } }
		 */
		return SUCCESS;
	}

	/**
	 * @Description:用户退出（注销）     免费用户
	 * @author lixuxiang-- 2015-11-15 下午1:04:33
	 * @return
	 */
	public String logOut() {

		// 获得当前的用户
		Subject subject = SecurityUtils.getSubject();
		// 注销用户
		// removeSession(Constants.SHIRO_USER);

		if (subject.isAuthenticated()) {
			logger.debug("用户退出");
			subject.logout();
		}

		return "logOut";

	}
	/**
	 * @Description:   收费用户
	 * @author sunjiang --- 2016-3-28下午3:13:08     
	 * @return
	 */
	public String logsOut() {
		
		// 获得当前的用户
		Subject subject = SecurityUtils.getSubject();
		// 注销用户
//		removeSession(Constants.SHIRO_USER);
		
		if (subject.isAuthenticated()) {
			logger.debug("用户退出");
			subject.logout();
		}
		
		return "logsOut";
		
	}

	/**
	 * 
	 * 描述:登入之后的主页面
	 * 
	 * 作者：lxx 2015-11-18下午03:36:47
	 * 
	 * @return
	 */
	public String index() {
		return SUCCESS;
	}

	/**
	 * @Description:忘记密码，跳转到修改密码页面
	 * @author lixuxiang-- 2015-11-15 下午1:16:59
	 * @return
	 */
	public String forgotPasswordUI() {

		return "success";

	}

	/**
	 * @Description:使用ajax发送邮件
	 * @author lixuxiang-- 2015-11-15 下午1:17:25
	 */

	public void ajaxSendEmail() {

		boolean flag = true;

		// 获取邮箱
		String email = request.getParameter("email");

		Map<String, String> errors = new HashMap<String, String>();

		// 验证邮箱是否为空
		if (StringUtils.isEmpty(email)) {
			flag = false;
			errors.put("errorMessage", Constants.EMAIL_IS_NULL);
		}
		// 验证邮箱格式是否有错误
		if (!StringUtils.checkEmail(email)) {
			flag = false;
			errors.put("errorMessage", Constants.EMAIL_IS_INCORRECT);
		}

		if (!flag) {
			errors.put("errorMessage", Constants.EMAIL_IS_NOT_EXIST);
		} else {
			try {
				// 获取邮箱的验证码
				String emailCaptcha = this.usersServiceImpl.sendEmail(email);
				// 将邮箱的验证码放到session中
				if (!StringUtils.isEmpty(emailCaptcha)) {
					ActionContext.getContext().getSession().put("emailCaptcha", emailCaptcha);

				}

				errors.put("errorMessage", "邮件发送成功，请登录邮箱获取验证码！");
			} catch (EmailException e) {
				errors.put("errorMessage", e.getMessage());
			}
		}

		OutputJson(errors);
	}

	/**
	 * @Description:重置密码
	 * @author lixuxiang-- 2015-11-14 下午6:21:31
	 * @return
	 */
	public void ajaxResetPassword() {

		boolean flag = true;

		// 获得用户的唯一标示

		Map<String, String> errors = new HashMap<String, String>();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		// 后台校验用户名和密码
		if (StringUtils.isEmpty(userId)) {
			flag = false;
			errors.put("errorMessage", Constants.USERNAME_IS_NULL);
		}
		if (StringUtils.isEmpty(password)) {
			flag = false;
			errors.put("errorMessage", Constants.INCORRECT_CREDENTIALS_EXCEPTION);
		}

		// 校验失败，返回错误信息

		Users user = usersServiceImpl.get(Integer.parseInt(userId));
		// 校验用户信息的正确性,校验失败，跳转到404页面
		if (user == null) {
			errors.put("errorMessage", Constants.UNKNOWN_ACCOUNT_EXCEPTION);

		} else {
			user.setPassword(password);
			usersServiceImpl.updatePassword(user);
			errors.put("successMsg", Constants.UPDATE_PASSWORD_SUCCESS);
		}
		this.OutputJson(errors);

		// 如果校验没有通过，则请求转发到密码修改页面
		if (!flag) {
			// return "modifyPasswordUI";
		}
		// 如果校验通过

		// 修改成功，跳转到登录页面

		// return "loginUI";
	}

	/**
	 * @Description:使用ajax校验用户名是否存在
	 * @author lixuxiang-- 2015-11-13 下午6:40:48
	 */
	public void ajaxUserName() {

		String userName = request.getParameter("userName");
		Users users = this.usersServiceImpl.getUsers(userName);
		Map<String, String> msg = new HashMap<String, String>();
		if (users == null) {
			msg.put("userNameError", Constants.USERNAME_IS_NOT_EXIST);

		} else {

			// 如果校验通过，将用户的主键标识写出到前台
			// this.OutputJson(users.getUserId());
			msg.put("userId", String.valueOf(users.getUserId()));

		}
		this.OutputJson(msg);

	}

	/**
	 * @Description:使用ajax校验验证码
	 * @author lixuxiang-- 2015-11-13 下午7:09:35
	 */

	public void ajaxCaptcha() {

		String captcha = request.getParameter("captcha");

		String captchaText = (String) request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (!captcha.equalsIgnoreCase(captchaText)) {
			this.OutputJson(Constants.CAPTCHA_IS_NOT_INCORRECT);
		}

	}

	/**
	 * @Description:使用ajax校验邮箱是否存在
	 * @author lixuxiang-- 2015-11-13 下午6:40:48
	 */
	public void ajaxEmail() {

		String email = request.getParameter("email");
		String userName = request.getParameter("userName");

		// 邮箱校验
		if (StringUtils.isEmpty(email)) {
			this.OutputJson(Constants.EMAIL_IS_NULL);
		} else {
			Users users = new Users();
			users.setEmail(email);
			users.setAccount(userName);
			Users usersPo = this.usersServiceImpl.getUsersByEmailAndUserName(users);
			if (usersPo == null) {
				this.OutputJson(Constants.EMAIL_IS_INCORRECT);
			}
		}

	}

	/**
	 * @Description:使用ajax校验邮箱验证码的正确性
	 * @author lixuxiang-- 2015-11-13 下午8:00:19
	 */
	public void ajaxEmailCaptcha() {

		String emailCaptcha = (String) request.getSession().getAttribute("emailCaptcha");
		String email = request.getParameter("emailCaptcha");
		if (StringUtils.isEmpty(emailCaptcha)) {
			this.OutputJson(Constants.EMAILCAPTCHA_IS_NULL);
		} else if (!emailCaptcha.equalsIgnoreCase(email)) {
			this.OutputJson(Constants.EMAILCAPTCHA_IS_INCORRECT);
		}

	}
	
	/**
	 * 
	 * 描述:添加登录日志文件
	 * 
	 * 作者：lxx	2016-3-15下午05:56:11
	 * @param userName
	 */
	public void addUserLog(String userName){
		try {
			UserLog userLog = new UserLog();
			userLog.setSiteCode(userName);
			ShiroUser shiroUser = getCurrentUserInfo();
			userLog.setName(shiroUser.getUserName());
			
			if(userName.length()<=6){
				userLog.setProvince(shiroUser.getUserName());
				userLog.setIsCost(shiroUser.getIsOrgCost() == 1?1:2);
			} else {
				DatabaseInfo databaseInfo = databaseInfoServiceImpl.findByDatabaseInfoCode(userName);
				userLog.setProvince(databaseInfo.getProvince());
				userLog.setCity(databaseInfo.getCity());
				userLog.setCounty(databaseInfo.getCounty());
				userLog.setIsorganizational(databaseInfo.getIsorganizational());
				userLog.setIsCost(shiroUser.getIscost() == 1?1:2);
			}
			userLog.setIp(getIpAddr());
			userLogServiceImpl.add(userLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String empty(){
		return SUCCESS;
	}
	
	
	/**
	 * @Description: 忘记密码发邮件
	 * @author sunjiang --- 2016-4-6下午2:45:56
	 */
	public void sendPasswordEmail(){
		try {
			
			Json json = new Json();
			
			String siteCode = request.getParameter("siteCode");
			
			DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
			databaseInfoRequest.setSiteCode(siteCode);
			List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
			
			if(!CollectionUtils.isEmpty(queryList)){
				DatabaseInfo databaseInfo = queryList.get(0);
				
				String email = databaseInfo.getEmail();
				
				String vcode = databaseInfo.getVcode();
				
				//发送邮件
				HashMap<Object, Object> mapParam = new HashMap<Object, Object>();
				mapParam.put("vcode", vcode);
				mapParam.put("siteCode", siteCode);
				boolean sendEmail = SendEmail.sendEmail("密码找回 - 开普云政府网站云监管平台★", "forgetPassword.ftl", mapParam, email);
//				boolean sendEmail = SendEmail.sendEmail("密码找回 - 开普云政府网站云监管平台★", "forgetPassword.ftl", mapParam, "18601902775@163.com");
//				boolean sendEmail = Boolean.TRUE;
				
				if(sendEmail){
					
					json.setStatus(Boolean.TRUE);
					
				}else{
					
					json.setMessage("邮箱发送失败");
					
				}
				
				writerPrint(JSONObject.fromObject(json).toString());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// /**
	// * 邮箱校验工具类
	// *
	// * @param email
	// * @return true:验证通过 false:验证失败
	// */
	// private boolean checkEmail(String email) {
	// String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
	// return Pattern.matches(regex, email);
	// }

//	/**
//	 * @Description:判断用户的类型
//	 * @author lixuxiang-- 2015-11-15 下午7:18:20
//	 * @return:用户的类型： 1:国办 2：省份 3:市县 4:填报单位
//	 */
//	private int judgeUserType(String siteCode) {
//
//		if (siteCode != null) {
//			if (siteCode.equals("admin")) {
//				return 1;
//			}
//			// 如果siteCode是以0000结尾的，则表示为组织单位
//			if (siteCode.endsWith("0000")) {
//				return 2;
//			} else if (siteCode.endsWith("00")) {
//				return 3;
//			} else {// 标识填报单位
//				return 4;
//			}
//		}
//		return 1;
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Json getJson() {
		return json;
	}

	public void setJson(Json json) {
		this.json = json;
	}


	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}


	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
