package com.ucap.cloud_web.constant;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
* @Title: ValidateUrlConstant.java
* @Description:不需要登录才有权限的地址
* @version V1.0
*/

public enum ValidateUrlConstant {
	/**云搜索，云分析开通记录接口 */
	DATABASEINFOOPENSTATEMODIFY("databaseInfo_openStateModify.action"),
	
	DATABASEINFOOPENSTATEMODIFY2("databaseInfo_addYunInfoAndDetail.action"),
	/**云分析登录接口 */
	USERSLOGINYIANALYZE("users_loginYiAnalyze.action"),
	/**云分析登录验证 */
	USERSLOGINDES("users_loginDes.action"),
	/**焦点推荐 */
	BIGDATATREND("bigDataTrend_*"),
	/**个性化登录 */
	USERSLOGINFORMSP("users_loginFormSP.action"),
	/**个性化 */
	SPCHANNEL("spChannel_*"),
	/**安全扫描iframe*/
	YSMONITORSILENTRESULT("ysmonitorSilentResult_*"),
	/**后台调用预警发送接口*/
	SENDEARLY("sendEarly_*"),
	TEST_DEMO_WEIXIN("accountBindInfo_*"),
	/**大数据外部引用--查询数据地址*/
	BIGDATAANALYSIS("bigDataAnalysis_*"),
	/**收费登录--form表单提交地址*/
	LOGINSFORM("users_loginsForm.action"),
	
	/**忘记密码--组织单位忘记密码发送邮件*/
	SENDPASSWORDEMAIL("users_sendPasswordEmail.action"),
	
	/**免费登录--form表单提交地址*/
	LOGINFORM("users_loginForm.action"),
	
	/**忘记密码--验证用户名*/
	AJAXUSERNAME("databaseInfo_ajaxUserName.action"),
	
	/**忘记密码--验证码*/
	AJAXCAPTCHA("databaseInfo_ajaxCaptcha.action"),
	
	/**登录*/
	LOGIN_URL("users_login.action"),
	
	/**登录--不需要验证*/
	LOGIN_INDEX("users_loginIndex.action"),
	
	/**获取登录页面的健康分数以及监测站点个数*/
	GETLOGINNUM("users_getLoginNum.action"),
	
	/**宣传首页登录页面*/
	LOGIN_URLS("users_logins.action"),
	
	/**忘记密码*/
	FORGOT_URL("users_forgotPasswordUI.action"),
	
	/**网页版健康指数数据*/
	INDEX_URL("/index/get"),
	
	/**微信公众号开发servlet*/
	SERVLET_URL("/servlet/CoreServlet"),
	
	/**微信公众号开发接口路径*/
	REST_URL("/rest/token"),
	
	
	/**微信公众号开发-管理中心页面初始化--组织单位*/
	NEW_TOKEN_ACCOUNT_MSG("newToken_managementCenterIndex.action"),
	/**微信公众号开发-绑定账户页面初始化--组织单位*/
	NEW_TOKEN_ACCOUNT_BIND("newToken_accountBindIndex.action"),
	/**微信公众号开发-保存绑定账户信息--组织单位*/
	NEW_TOKEN_ACCOUNT_BIND_SAVE("newToken_saveAccountBind.action"),
	/**微信公众号开发-管理中心--填报单位*/
	NWE_TOKEN_ACCOUNT_MSG_TB("newToken_getManagementCenterTB.action"),
	/**微信公众号开发-管理中心--组织单位*/
	NEW_TOKEN_ACCOUNT_MSG1("newToken_getManagementCenter.action"),
	NEW_TOKEN_REMOVE_ACCOUNT_BIND("newToken_removeBindAccount.action"),
	/**微信公众号开发-绑定成功页面--组织单位*/
	NEW_TOKEN_ACCOUNT_BIND_SUCCESS_SAVE("newToken_removeAccountBindIndex.action"),
	/**微信公众号开发-改变接收报告和预警通知的状态--组织单位*/
	NEW_TOKEN_CHANGE_STATE_VALUE("newToken_changeStatusValue.action"),
	/**微信公众号开发-检测结果页面初始化--填报单位*/
	NEW_TOKEN_CHECKRESULT_TB_INDEX("newToken_checkResultTBIndex.action"),
	/**填报单位首页面加载数据*/
	NEW_TOKEN_CHECKRESULT_TB("newToken_checkResultTB.action"),
	/**微信公众号开发-检测结果页面初始化--组织单位*/
	NEW_TOKEN_CHECKRESULT_ORG_INDEX("newToken_checkResultOrgIndex.action"),
	/**组织单位首页面加载数据*/
	NEW_TOKEN_CHECKRESULT_ORG("newToken_checkResultOrg.action"),
	/**微信公众号开发-健康指数排名页面--组织单位*/
	NEW_TOKEN_INDEX_COUNT("newToken_indexCountIndex.action"),
	/**微信公众号开发-健康指数排名页面--组织单位*/
	NEW_TOKEN_NDEX_ALL_COUNT("newToken_getIndexCountAll.action"),
	/**微信公众号开发-健康指数排名页面--组织单位*/
	NEW_TOKEN_INDEX_COUNT_GET("newToken_getIndexCount.action"),
	
	/**首页更新量---组织单位*/
	FREE_TOKEN_CONN_HOME_ORG_INDEX("freeToken_connHomeOrgIndex.action"),
	FREE_TOKEN_CONN_HOME_ORG_GET("freeToken_connHomeOrgData.action"),
	FREE_TOKEN_CONN_HOME_PER_ORG_GET("freeToken_connHomePerOrgData.action"),
	/**首页更新量---组织单位*/
	FREE_TOKEN_CONN_HOME_TB_INDEX("freeToken_connHomeTbIndex.action"),
	FREE_TOKEN_CONN_HOME_TB_GET("freeToken_getConnHomeTbData.action"),
	
	/**首页更新量---组织单位*/
	FREE_TOKEN_UPDATE_HOME_ORG_INDEX("freeToken_updateHomeOrgIndex.action"),
	FREE_TOKEN_UPDATE_HOME_ORG_GET("freeToken_getUpdateHomeData.action"),
	/**首页更新量---填报单位*/
	FREE_TOKEN_UPDATE_HOME_TB_INDEX("freeToken_updateHomeTbIndex.action"),
	FREE_TOKEN_UPDATE_HOME_TB_GET("freeToken_getUpdateHomeTbData.action"),
	
	/**首页更新不及时---组织单位*/
	FREE_TOKEN_SECURITY_HOME_ORG_INDEX("freeToken_securityHomeOrgIndex.action"),
	FREE_TOKEN_SECURITY_HOME_ORG_GET("freeToken_getsecurityHomeOrgData.action"),
	/**首页更新不及时---填报单位*/
	FREE_TOKEN_SECURITY_HOME_TB_INDEX("freeToken_securityHomeTbIndex.action"),
	
	/**首页不可用链接---组织单位*/
	FREE_TOKEN_LINK_HOME_ORG_INDEX("freeToken_linkHomeOrgIndex.action"),
	FREE_TOKEN_LINK_HOME_ORG_GET("freeToken_getLinkHomeOrgData.action"),
	/**首页不可用链接---填报单位*/
	FREE_TOKEN_LINK_HOME_TB_INDEX("freeToken_linkHomeTbIndex.action"),
	FREE_TOKEN_LINK_HOME_TB_GET("freeToken_getLinkHomeTbData.action"),
	
	/**关键栏目---组织单位*/
	COST_TOKEN_CONN_CHANNEL_ORG_INDEX("costToken_connChannelOrgIndex.action"),
	COST_TOKEN_CONN_CHANNEL_ORG_GET("costToken_getConnChannelOrgData.action"),
	/**关键栏目---填报单位*/
	COST_TOKEN_CONN_CHANNEL_TB_INDEX("costToken_connChannelTbIndex.action"),
	COST_TOKEN_CONN_CHANNEL_TB_GET("costToken_getConnChannelTbData.action"),
	
	/**业务系统连不通---组织单位*/
	COST_TOKEN_CONN_BUSINESS_ORG_INDEX("costToken_connBusinessOrgIndex.action"),
	COST_TOKEN_CONN_BUSINESS_ORG_GET("costToken_getConnBusinessOrgData.action"),
	/**业务系统---填报单位*/
	COST_TOKEN_CONN_BUSINESS_TB_INDEX("costToken_connBusinessTbIndex.action"),
	COST_TOKEN_CONN_BUSINESS_TB_GET("costToken_getConnBusinessTbData.action"),
	
	/**栏目内容更新量---组织单位*/
	COST_TOKEN_UPDATE_CHANNEL_ORG_INDEX("costToken_updateChannelOrgIndex.action"),
	COST_TOKEN_UPDATE_CHANNEL_ORG_GET("costToken_getUpdateChannelOrgData.action"),
	/**栏目内容更新量---填报单位*/
	COST_TOKEN_UPDATE_CHANNEL_TB_INDEX("costToken_updateChannelTbIndex.action"),
	COST_TOKEN_UPDATE_CHANNEL_TB_GET("costToken_getUpdateChannelTbData.action"),
	
	/**栏目更新不及时---组织单位*/
	COST_TOKEN_NOT_UPDATE_CHANNEL_ORG_INDEX("costToken_securityChannelOrgIndex.action"),
	COST_TOKEN_NOT_UPDATE_CHANNEL_ORG_GET("costToken_getSecurityChannelOrgData.action"),
	/**栏目内容更新量---填报单位*/
	COST_TOKEN_NOT_UPDATE_CHANNEL_TB_INDEX("costToken_securityChannelTbIndex.action"),
	COST_TOKEN_NOT_UPDATE_CHANNEL_TB_GET("costToken_getSecurityChannelTbData.action"),
	
	/**疑似错别字---组织单位*/
	COST_TOKEN_CORRECT_CONTENT_ORG_INDEX("costToken_contCorrectOrgIndex.action"),
	COST_TOKEN_CORRECT_CONTENT_ORG_GET("costToken_getContCorrectOrgData.action"),
	/**疑似错别字---填报单位*/
	COST_TOKEN_CORRECT_CONTENT_TB_INDEX("costToken_contCorrectTbIndex.action"),
	COST_TOKEN_CORRECT_CONTENT_TB_GET("costToken_getContCorrectTbData.action"),
	
	/**互动回应差---组织单位*/
	ADVANCE_TOKEN_SECURITY_RESPONSE_ORG_INDEX("advanceToken_securityResponseOrgIndex.action"),
	ADVANCE_TOKEN_SECURITY_RESPONSE_ORG_GET("advanceToken_getsecurityResponseOrgData.action"),
	/**互动回应差---填报单位*/
	ADVANCE_TOKEN_SECURITY_RESPONSE_TB_INDEX("advanceToken_securityResponseTbIndex.action"),
	ADVANCE_TOKEN_SECURITY_RESPONSE_TB_GET("advanceToken_getsecurityResponseTbData.action"),
	
	/**服务不实用---组织单位*/
	ADVANCE_TOKEN_SECURITY_SERVICE_ORG_INDEX("advanceToken_securityServiceOrgIndex.action"),
	ADVANCE_TOKEN_SECURITY_SERVICE_ORG_GET("advanceToken_getSecurityServiceOrgData.action"),
	/**服务不实用---填报单位*/
	ADVANCE_TOKEN_SECURITY_SERVICE_TB_INDEX("advanceToken_securityServiceTbIndex.action"),
	ADVANCE_TOKEN_SECURITY_SERVICE_TB_GET("advanceToken_getSecurityServiceTbData.action"),
	
	
	/**空白栏目---组织单位*/
	ADVANCE_TOKEN_SECURITY_BLANK_ORG_INDEX("advanceToken_securityBlankOrgIndex.action"),
	ADVANCE_TOKEN_SECURITY_BLANK_ORG_GET("advanceToken_getSecurityBlankOrgData.action"),
	/**空白栏目---填报单位*/
	ADVANCE_TOKEN_SECURITY_BLANK_TB_INDEX("advanceToken_securityBlankTbIndex.action"),
	ADVANCE_TOKEN_SECURITY_BLANK_TB_GET("advanceToken_getSecurityBlankTbData.action"),
	
	/**严重问题---组织单位*/
	ADVANCE_TOKEN_SECURITY_FATAL_ORG_INDEX("advanceToken_securityFatalErrorOrgIndex.action"),
	ADVANCE_TOKEN_SECURITY_FATAL_ORG_GET("advanceToken_getSecurityFatalErrorOrgData.action"),
	/**严重问题---填报单位*/
	ADVANCE_TOKEN_SECURITY_FATAL_TB_INDEX("advanceToken_securityFatalErrorTbIndex.action"),
	ADVANCE_TOKEN_SECURITY_FATAL_TB_GET("advanceToken_getSecurityFatalErrorTbData.action"),
	
	/**全站死链---组织单位*/
	ADVANCE_TOKEN_LINK_ALL_ORG_INDEX("advanceToken_linkAllOrgIndex.action"),
	ADVANCE_TOKEN_LINK_ALL_ORG_GET("advanceToken_getLinkAllOrgOrgData.action"),
	/**全站死链--填报单位*/
	ADVANCE_TOKEN_LINK_ALL_TB_INDEX("advanceToken_linkAllTbIndex.action"),
	ADVANCE_TOKEN_LINK_ALL_TB_GET("advanceToken_getLinkAllTbData.action"),
	
	
	
	
	
	
	/**微信公众号开发-检测结果页面初始化--组织单位*/
	SERVLET_ACTION_URL("token_checkResultIndex.action"),
	
	/**微信公众号开发-检测结果页面发送ajax请求--组织单位*/
	SERVLET_ACTION_URL1("token_getcurrentCheckResult.action"),
	
	/**微信公众号开发-检测结果点击网站连不通-跳转到网站连不通页面*/
	SERVLET_ACTION_CONNECTION_INDEX("token_connectionAllIndex.action"),
	
	/**微信公众号开发-网站连不通页面*/
	SERVLET_ACTION_CONNECTION_INDEX1("token_getConnectionAll.action"),
	
	/**微信公众号开发-检测结果点首页链接不可用-跳转到首页链接不可用页面*/
	SERVLET_ACTION_LINKHOME_INDEX("token_linkHomeIndex.action"),
	
	/**微信公众号开发-首页链接可用性*/
	SERVLET_ACTION_LINKHOME_INDEX2("token_getLinkHome.action"),
	
	/**微信公众号开发-检测结果点首页链接不可用-跳转到首页链接不可用页面*/
	SERVLET_ACTION_SECURITY_INDEX("token_securityIndex.action"),
	
	/**微信公众号开发-首页链接可用性*/
	SERVLET_ACTION_SECURITY_INDEX2("token_getsecurity.action"),
	
	
	/**微信公众号开发-检测结果点错别字-跳转到错别字页面*/
	SERVLET_ACTION_CONTENT_INDEX("token_correctContentIndex.action"),
	
	/**微信公众号开发-错别字页面数据获取*/
	SERVLET_ACTION_CONTENT_INDEX2("token_getCorrectContent.action"),
	
	/**微信公众号开发-检测结果点内容更新-跳转到内容更新页面*/
	SERVLET_ACTION_UPDATE_INDEX("token_updateIndex.action"),
	
	/**微信公众号开发-内容更新页面数据获取*/
	SERVLET_ACTION_UPDATE_INDEX2("token_getUpdate.action"),
	
	/**微信公众号开发-预警详情页面初始化--组织单位*/
	TOKEN_ACTION_EARLY_DETAIL("token_earlyDetailIndex.action"),
	
	/**微信公众号开发-预警详情页面发送ajax请求--组织单位*/
	TOKEN_ACTION_EARLY_DETAIL1("token_getEarlyDetail.action"),
	
	/**微信公众号开发-报告管理页面初始化--组织单位*/
	TOKEN_ACTION_REPORTMG("token_reportManageLogIndex.action"),
	/**微信公众号开发-报告管理页面发送ajax请求，获取列表数据--组织单位*/
	TOKEN_ACTION_REPORTMG1("token_getReportManageLog.action"),
	
	
	/**微信公众号开发-绑定账户页面初始化--组织单位*/
	TOKEN_ACCOUNT_BIND("token_accountBindIndex.action"),
	/**微信公众号开发-保存绑定账户信息--组织单位*/
	TOKEN_ACCOUNT_BIND_SAVE("token_saveAccountBind.action"),
	
	/**微信公众号开发-管理中心页面初始化--组织单位*/
	TOKEN_ACCOUNT_MSG("token_managementCenterIndex.action"),
	/**微信公众号开发-管理中心--组织单位*/
	TOKEN_ACCOUNT_MSG1("token_getManagementCenter.action"),
	/**微信公众号开发-解除绑定账户--组织单位*/
	TOKEN_REMOVE_ACCOUNT_BIND("token_removeBindAccount.action"),
	/**微信公众号开发-改变接收报告和预警通知的状态--组织单位*/
	TOKEN_CHANGE_STATE_VALUE("token_changeStatusValue.action"),
	
	
	/**微信公众号开发-监测结果--填报单位*/
	TOKEN_CHECK_RESULT_TB("token_checkResultTBIndex.action"),

	/**微信公众号开发-管理中心--填报单位*/
	TOKEN_ACCOUNT_MSG_TB("token_getManagementCenterTB.action"),
	
	/**
	 * 微信公众号开发-预警提醒发送模板信息--填报单位/组织单位
	 */
	TOKEN_ACTION_EARLY_INFO("token_earlyTX.action"),
	
	/**微信公众号开发-健康指数排名页面--组织单位*/
	TOKEN_ACCOUNT_INDEX_COUNT("token_indexCountIndex.action"),
	
	/**微信公众号开发-健康指数排名页面--组织单位*/
	TOKEN_ACCOUNT_INDEX_ALL_COUNT("token_getIndexCountAll.action"),
	
	/**微信公众号开发-健康指数排名页面--组织单位*/
	TOKEN_ACCOUNT_INDEX_COUNT_GET("token_getIndexCount.action"),
	
	/**微信公众号开发-健康指数排名页面--组织单位*/
	TOKEN_REMOVE_ACCOUNT_BIND_INDEX("token_removeAccountBindIndex.action"),
	
	/**微信公众号开发-管理中心--谁关注了我--组织、填报单位*/
	TOKEN_ACCOUNT_BIND_FOCUS("token_focusInit.action"),
	
	/**页面扫描微信公众号二维码--获取微信用户基本信息并保存到数据库*/
	TOKEN_APPROVE_ACCOUNT("token_approveAccount.action"),
	
	/**编辑器上传  文件  截图访问*/
	EWEBEDITOR_FILE_IMG("/ewebeditor/uploadfile"),
	

	;
	
	private String actionName;
	
	private ValidateUrlConstant(String actionName) {
		this.actionName = actionName;
	}

	public static List<ValidateUrlConstant> getAll(){
		ValidateUrlConstant[] values = ValidateUrlConstant.values();
		return Arrays.asList(values);
	}
	
	public static ValidateUrlConstant get(String actionName){
		List<ValidateUrlConstant> all = getAll();
		for (ValidateUrlConstant validateUrl : all) {
			if(validateUrl.getActionName().equals(actionName)){
				return validateUrl;
			}
		}
		return null;
	}
	
	public static String getValidateActionName(){
		String result = StringUtils.EMPTY;
		List<ValidateUrlConstant> all = getAll();
		for (int i = 0; i < all.size(); i++) {
			result += all.get(i).getActionName();
			if(i + 1 < all.size()) result += "|";
		}
		return result;
	}
	public String getActionName() {
		return actionName;
	}
	
}
