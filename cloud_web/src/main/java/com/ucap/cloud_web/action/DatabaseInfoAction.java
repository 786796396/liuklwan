package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.common.util.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;


import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.bizService.ILogCommonService;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.bizService.DatabaseTreeBizService;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.LogType;
import com.ucap.cloud_web.dto.CfgOtherProductsRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.YunOpenStateRequest;
import com.ucap.cloud_web.entity.CfgOtherProducts;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.YunOpenDetail;
import com.ucap.cloud_web.entity.YunOpenInfo;
import com.ucap.cloud_web.entity.YunOpenState;
import com.ucap.cloud_web.service.ICfgOtherProductsService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IYunOpenDetailService;
import com.ucap.cloud_web.service.IYunOpenInfoService;
import com.ucap.cloud_web.service.IYunOpenStateService;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.util.DesTwo;
import com.ucap.cloud_web.util.StringEncrypt;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DatabaseInfoAction extends BaseAction {
	@Autowired
	private DicUtils dicUtils;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private DatabaseTreeBizService databaseTreeBizServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private ILogCommonService logCommonServiceImpl;
	@Autowired
	private IYunOpenStateService yunOpenStateServiceImpl;
	@Autowired
	private IYunOpenInfoService yunOpenInfoServiceImpl;
	@Autowired
	private IYunOpenDetailService yunOpenDetailServiceImpl;
	@Autowired
	private ICfgOtherProductsService cfgOtherProductsServiceImpl;
	private String typeName;
	
	
	private static Log logger =  LogFactory.getLog(ConfigEarlyAction.class);
	
	
	public static void main(String[] args) {
		
		
/*		String aa="fh9wGNc+amq3rMUHrGOmrLlq8aoUclMG9OgAEb5uxH3JxGunT8Lo1BjfzkKG JpWfGMv/QNYc2VyeafZTkqHN6RUmUiR6lc7X1Iahv17ZrkJKtk9zR2/aTBnT uXPN13Tt/u/Z+s3JvfPM0lNr9w/G+jRiWJQ3U3oo/kMcbuOpDS31IL0xnGXJ u1ghuqVnG+om3L0d7qy3kntHUB1eO0qljcaLiYTM0+OUiBfKMrfhmUWxNUQl trrA87QAsX7c4uHJgJXEugxRyLLFa6de64VA9LY9zbWVn6v/PmsAbyTl/is0 xanfTiMPIS95/wqIgaWBZfTyyc4pOQ9NzrgSFj028lS/D8cZ4OYK6JH8f8UR QTHNEPlppejyvw==";
		try {
			System.out.println(DesTwo.decryptDES(aa, "ucap2016"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		List<Map<String, Object>> infoList=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> detailList=new ArrayList<Map<String,Object>>();
		
		Map<String, Object> a=new HashMap<String, Object>();
		a.put("siteCode", "A00011");
		a.put("name", "开普互联");
		
		infoList.add(a);
		for (int i = 0; i < 3; i++) {
			Map<String, Object> b=new HashMap<String, Object>();
			b.put("siteCode", "N00000054"+i);
			b.put("name", "开普云监管测试"+i);
			detailList.add(b);
		}
		map.put("infoList", infoList);
		map.put("detailList", detailList);
		try {
			String encodeStr=DesTwo.encryptDES(JSONObject.fromObject(map).toString(), "ucap2016");
			String decodeStr=DesTwo.decryptDES(encodeStr, "ucap2016");
			JSONObject json=JSONObject.fromObject(decodeStr);
			
			String infoListStr=json.getString("infoList");
			String detailListStr=json.getString("detailList");
			
			JSONArray  jsonArr=JSONArray.fromObject(infoListStr);
			for (int i = 0; i < jsonArr.size(); i++) {
				
				if(i==0){
					JSONObject aa=(JSONObject) jsonArr.get(0);
					System.out.println(aa.get("siteCode"));
					System.out.println(aa.get("name"));
				}
			}
			
			
			JSONArray jsonArr2=JSONArray.fromObject(detailListStr);
			for (int i = 0; i < jsonArr2.size(); i++) {
				JSONObject bb=(JSONObject) jsonArr2.get(i);
				
				System.out.println(bb.get("siteCode"));
				System.out.println(bb.get("name"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 忘记密码--------立即登录
	 * @author sunjiang --- 2016-4-6下午5:52:44     
	 * @return
	 */
	public String readyLogin(){
		String siteCode = request.getParameter("siteCode");
		
		DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
		databaseInfoRequest.setSiteCode(siteCode);
		List<DatabaseInfo> queryList = this.databaseInfoServiceImpl.queryList(databaseInfoRequest);
		
		if(!CollectionUtils.isEmpty(queryList)){
			DatabaseInfo databaseInfo = queryList.get(0);
			int iscost = databaseInfo.getIscost();
			
			if(iscost==0){
				return "logOut";
			}else{
				return "logsOut";
			}
		}
		
		return null;
	}

	/**
	 * @Description:忘记密码，跳转到修改密码页面
	 * @author kefan-- 2015-11-15 下午1:16:59
	 * @return
	 */
	public String forgotPasswordUI() {

		return "success";

	}

	/**
	 * @Description:使用ajax校验用户名是否存在
	 * @author kefan-- 2015-11-13 下午6:40:48
	 */
	public void ajaxUserName() {

		String siteCode = request.getParameter("userName");
		
		DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
		
		if(siteCode.length()>6){
			
			databaseInfoRequest.setSiteCode(siteCode);
			
		}else{
			
			//组织单位找回密码---暂时不做
			databaseInfoRequest.setSiteCode("0");
		}
		
		List<DatabaseInfo> queryList = this.databaseInfoServiceImpl.queryList(databaseInfoRequest);
		Map<String, String> msg = new HashMap<String, String>();
		
		
		if(!CollectionUtils.isEmpty(queryList)){
			DatabaseInfo databaseInfo = queryList.get(0);
			msg.put("userId", String.valueOf(databaseInfo.getId()));
			msg.put("director", String.valueOf(databaseInfo.getDirector()));
			
		}else{
			msg.put("userNameError", Constants.USERNAME_IS_NOT_EXIST);
		}
		this.OutputJson(msg);

	}

	/**
	 * @Description:使用ajax校验验证码
	 * @author kefan-- 2015-11-13 下午7:09:35
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
	 * @author kefan-- 2015-11-13 下午6:40:48
	 */
	public void ajaxEmail() {

		String email = request.getParameter("email");
		String siteCode = request.getParameter("userName");

		// 邮箱校验
		if (StringUtils.isEmpty(email)) {
			this.OutputJson(Constants.EMAIL_IS_NULL);
		} else {
			DatabaseInfo info = new DatabaseInfo();
			info.setSiteCode(siteCode);
			info.setEmail(email);
			DatabaseInfo databaseInfoPo = this.databaseInfoServiceImpl
					.getUsersByEmailAndSiteCode(info);
			if (databaseInfoPo == null) {
				this.OutputJson(Constants.EMAIL_IS_INCORRECT);
			}
		}

	}

	/**
	 * @Description:使用ajax校验邮箱验证码的正确性
	 * @author kefan-- 2015-11-13 下午8:00:19
	 */
	public void ajaxEmailCaptcha() {

		String emailCaptcha = (String) request.getSession().getAttribute(
				"emailCaptcha");
		String email = request.getParameter("emailCaptcha");
		if (StringUtils.isEmpty(emailCaptcha)) {
			this.OutputJson(Constants.EMAILCAPTCHA_IS_NULL);
		} else if (!emailCaptcha.equalsIgnoreCase(email)) {
			this.OutputJson(Constants.EMAILCAPTCHA_IS_INCORRECT);
		}

	}
	
	
	/**
	 * @Description:  1云搜索2云分析 开通状态修改
	 * @author Liukl --- 2017年3月28日17:53:54
	 */
	public void openStateModify(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			String siteCode = request.getParameter("siteCode");
			String yunType = request.getParameter("yunType");//1云搜索和2云分析
			String openState = request.getParameter("openState");//开通状态1申请试用2申请中3登录使用
			YunOpenStateRequest yunRequest = new YunOpenStateRequest();
			if(StringUtils.isNotEmpty(siteCode)){
				yunRequest.setSiteCode(siteCode);
			}else{
				dataMap.put("status", "账号传入失败");
			}
			if(StringUtils.isNotEmpty(yunType)){
				yunRequest.setYunType(Integer.valueOf(yunType));
			}else{
				dataMap.put("status", "云产品传入失败");
			}
			if(StringUtils.isEmpty(openState)){
				dataMap.put("status", "开通状态失败");
			}
			yunRequest.setPageSize(Integer.MAX_VALUE);
			List<YunOpenState> yunList = yunOpenStateServiceImpl.queryList(yunRequest);
			if(StringUtils.isNotEmpty(siteCode) && StringUtils.isNotEmpty(yunType) && StringUtils.isNotEmpty(openState)){
				if(yunList != null && yunList.size()>0){
					for (YunOpenState yunOpenState : yunList) {
						yunOpenState.setModifyTime(new Date());
						if(StringUtils.isNotEmpty(openState)){
							yunOpenState.setOpenState(Integer.valueOf(openState));
						}
						yunOpenStateServiceImpl.update(yunOpenState);
					}
				}else{
					YunOpenState yunState = new YunOpenState();
					if(StringUtils.isNotEmpty(siteCode)){
						yunState.setSiteCode(siteCode);
					}
					if(StringUtils.isNotEmpty(yunType)){
						yunState.setYunType(Integer.valueOf(yunType));
					}
					if(StringUtils.isNotEmpty(openState)){
						yunState.setOpenState(Integer.valueOf(openState));
					}
					yunOpenStateServiceImpl.add(yunState);
				}
				dataMap.put("status", "success");
			}
			writerPrint(JSONObject.fromObject(dataMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * @Description:  1云搜索2云分析 获取开通状态
	 * @author Liukl --- 2017年3月28日17:53:54
	 */
	public void getYunState(){
		try {
			HashMap<String, Object> returnMap = new HashMap<String, Object>();
			String siteCode = getCurrentUserInfo().getSiteCode();
			String yunType = request.getParameter("yunType");//1云搜索和2云分析
			if(StringUtils.isEmpty(siteCode)){
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			YunOpenStateRequest yunRequest = new YunOpenStateRequest();
			if(StringUtils.isNotEmpty(siteCode)){
				yunRequest.setSiteCode(siteCode);
			}
			if(StringUtils.isNotEmpty(yunType)){
				yunRequest.setYunType(Integer.valueOf(yunType));
			}
			yunRequest.setPageSize(Integer.MAX_VALUE);
			List<YunOpenState> yunList = yunOpenStateServiceImpl.queryList(yunRequest);
			if(yunList != null && yunList.size()>0){
				returnMap.put("openState", yunList.get(0).getOpenState());
			}else{
				returnMap.put("openState", "0");
			}
			writerPrint(JSONObject.fromObject(returnMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * @Title: 立即试用云搜索 组织单位
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-26上午10:31:00
	 */
	public void queryDatabaseOrgInfo(){
		String uri = request.getParameter("url");
		try {
			String orgSiteCode = getCurrentUserInfo().getSiteCode();
			String type = request.getParameter("type");//云产品类型(1:一键检测，2云搜索，3云分析，4云网盘)
			if(type.equals("3")){
				if(StringUtils.isEmpty(uri)){
					CfgOtherProductsRequest req = new CfgOtherProductsRequest();
					req.setInUse(1);
					req.setType(3);
					req.setPageSize(Integer.MAX_VALUE);
					List<CfgOtherProducts> cfgList = cfgOtherProductsServiceImpl.queryList(req);
					if(cfgList !=null && cfgList.size()>0){
						uri=cfgList.get(0).getLinkUrl();
					}
				}
				DatabaseOrgInfoRequest databaseOrgRequest = new DatabaseOrgInfoRequest();
				databaseOrgRequest.setStieCode(orgSiteCode);
				List<DatabaseOrgInfo> orgInfoList = databaseOrgInfoServiceImpl.queryList(databaseOrgRequest);
				if(orgInfoList.size()>0){
					for(int i=0;i<orgInfoList.size();i++){
						DatabaseOrgInfo databaseOrgInfo=new DatabaseOrgInfo();
						databaseOrgInfo=orgInfoList.get(i);
						if(i==0){
							Map<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("databaseOrgInfo",databaseOrgInfo);
							if (StringUtils.isEmpty(typeName)) {
								typeName="";
							}
							paramMap.put("typeName",typeName);
							paramMap.put("type",type);
							
							
							//关联tree表获取 本机站点和本机部门站点信息
							//获取站点标识码集合
							String[] typeArr={"1","2","3"};
							Map<String, Object> map=new HashMap<String, Object>();
							map.put("orgSiteCode", databaseOrgInfo.getSiteCode());
							map.put("layerType", typeArr);
							map.put("isexp", 1);
							map.put("isLink", 1);
							
							List<DatabaseInfo>  infoList=databaseInfoServiceImpl.getSiteInfoByTree(map);
							if(infoList!=null && infoList.size()>0){
								paramMap.put("infoList", infoList);
							}
							databaseInfoServiceImpl.getExperienceAddress(paramMap);
							// 数据集合
							Map<String, Object> dataMap = new HashMap<String, Object>();
							dataMap.put("url", uri);//请求路径
							dataMap.put("dataUrl", paramMap.get("encryptData"));//请求参数
							writerPrint(JSONObject.fromObject(dataMap).toString());
						}
					 }
				}else{
					// 数据集合
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("url", urlAdapterVar.getCloudSearchUrl());
					dataMap.put("dataUrl", "");
					dataMap.put("databaseOrgInfo", "");
					writerPrint(JSONObject.fromObject(dataMap).toString());
				}
			}else{
				DatabaseTreeInfoRequest databaseTreeInfoRequest=new DatabaseTreeInfoRequest();
				databaseTreeInfoRequest.setSiteCode(orgSiteCode);
				//0非门户，1门户
				databaseTreeInfoRequest.setIsorganizational(1);
				//填报单位
				List<DatabaseInfo> nextDataList=databaseTreeBizServiceImpl.localLevelSite(databaseTreeInfoRequest);
				if(nextDataList.size()>0){
					for(int i=0;i<nextDataList.size();i++){
						DatabaseInfo databaseInfo=new DatabaseInfo();
						databaseInfo=nextDataList.get(i);
						if(i==0){
							Map<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("databaseInfo",databaseInfo);
							if (StringUtils.isEmpty(typeName)) {
								typeName="";
							}
							paramMap.put("typeName",typeName);
							paramMap.put("type",type);
							databaseInfoServiceImpl.getSelfAddress(paramMap);
							// 数据集合
							Map<String, Object> dataMap = new HashMap<String, Object>();
							dataMap.put("url", uri);//请求路径
							dataMap.put("dataUrl", paramMap.get("encryptData"));//请求参数
							writerPrint(JSONObject.fromObject(dataMap).toString());
						}
					 }
				}else{
					// 数据集合
					Map<String, Object> dataMap = new HashMap<String, Object>();
					if(type.equals("1")){
						String data ="";
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("url", "");	
						// 把需要的参数放到Map中
						map.put("siteCode", orgSiteCode);
						// 请求时间,格式为yyyyMMddHHmmss,有效时间暂定为1小时
						map.put("time", DateUtils.formatShortDateTime(new Date()));
						// 限制使用是否打开,默认为0 关闭 ,1:开启
						map.put("confineStatus", dicUtils.getValue("oneSelfTest_ConfineStatus"));
						// 每日限制使用次数,默认为3
						map.put("confineTime", dicUtils.getValue("oneSelfTest_ConfineTime"));
						// debug开关 0：关闭(默认)，1：开启
						map.put("debug", dicUtils.getValue("oneSelfTest_Debug"));
						data = StringEncrypt.encrypt(JSONObject.fromObject(map).toString(),
							   StringEncrypt.PASSWORD_CRYPT_KEY);
						dataMap.put("url", uri);
						dataMap.put("dataUrl", data);//请求参数
					}else{
						dataMap.put("url", urlAdapterVar.getCloudSearchUrl());
						dataMap.put("dataUrl", "");
						dataMap.put("databaseInfo", "");
					}
					writerPrint(JSONObject.fromObject(dataMap).toString());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: 立即试用云搜索 填报单位
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-26上午10:31:00
	 */
	public void queryDatabaseInfo(){
		String uri = request.getParameter("url");
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			if(StringUtils.isEmpty(uri)){
				CfgOtherProductsRequest req = new CfgOtherProductsRequest();
				req.setInUse(1);
				req.setType(3);
				req.setPageSize(Integer.MAX_VALUE);
				List<CfgOtherProducts> cfgList = cfgOtherProductsServiceImpl.queryList(req);
				if(cfgList !=null && cfgList.size()>0){
					uri=cfgList.get(0).getLinkUrl();
				}
			}
			String type = request.getParameter("type");//云产品类型(1:一键检测，2云搜索，3云分析，4云网盘)
			DatabaseInfoRequest databaseInfoRequest=new DatabaseInfoRequest();
			databaseInfoRequest.setSiteCode(siteCode);
			List<DatabaseInfo> list=databaseInfoServiceImpl.queryList(databaseInfoRequest);
			if(list.size()>0){
				for( DatabaseInfo databaseInfo:list){
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("uri", uri);
					paramMap.put("databaseInfo",databaseInfo);
					if (StringUtils.isEmpty(typeName)) {
						typeName="";
					}
					paramMap.put("typeName",typeName);
					paramMap.put("type",type);
					databaseInfoServiceImpl.getSelfAddress(paramMap);
					// 数据集合
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("url", paramMap.get("uri"));
					dataMap.put("dataUrl", paramMap.get("encryptData"));
					writerPrint(JSONObject.fromObject(dataMap).toString());
				}
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	/**
	 * @Title: 立即试用云专题
	 * @Description:
	 * @author liukl@ucap.com.cn	2017年4月13日16:18:40
	 */
	public void queryTestYun(){
		try {
			String uri = request.getParameter("url");
			
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			String name = Constants.getCurrendUser().getUserName().trim();//获得当前登录站点名称
			Map<String, Object> map = new HashMap<String, Object>();
			if(siteCode.length()>6){
				DatabaseInfoRequest databaseInfoRequest=new DatabaseInfoRequest();
				databaseInfoRequest.setSiteCode(siteCode);
				List<DatabaseInfo> list=databaseInfoServiceImpl.queryList(databaseInfoRequest);
				if(list.size()>0){
					DatabaseInfo databaseInfo = list.get(0);
					map.put("siteCode", siteCode);//网站标识码
					map.put("vcode", databaseInfo.getVcode());//网站校验码
					map.put("siteName", databaseInfo.getName()!=null?databaseInfo.getName():"");//网站名称
					map.put("province", databaseInfo.getProvince()!=null?databaseInfo.getProvince():"");//省
					map.put("city", databaseInfo.getCity()!=null?databaseInfo.getCity():"");//市
					map.put("county", databaseInfo.getCounty()!=null?databaseInfo.getCounty():"");//县
					map.put("level", databaseInfo.getLevel()!=null?databaseInfo.getLevel():"");//级别
					map.put("url", databaseInfo.getUrl()!=null?databaseInfo.getUrl():"");//url地址
					map.put("jumpUrl", databaseInfo.getJumpUrl()!=null?databaseInfo.getJumpUrl():"");//跳转url
					map.put("director", databaseInfo.getDirector()!=null?databaseInfo.getDirector():"");//网站主办单位
					map.put("address", databaseInfo.getAddress()!=null?databaseInfo.getAddress():"");//办公地址
					map.put("linkmanName", databaseInfo.getLinkmanName()!=null?databaseInfo.getLinkmanName():"");//联系人
					map.put("principalName", databaseInfo.getPrincipalName()!=null?databaseInfo.getPrincipalName():"");//负责人
					map.put("mobile", databaseInfo.getMobile()!=null?databaseInfo.getMobile():"");//负责人办公电话
					map.put("telephone", databaseInfo.getTelephone()!=null?databaseInfo.getTelephone():"");//负责人手机
					map.put("principalPost", databaseInfo.getPrincipalPost()!=null?databaseInfo.getPrincipalPost():"");//负责人职务
					map.put("mobile2", databaseInfo.getMobile2()!=null?databaseInfo.getMobile2():"");//联系人办公电话
					map.put("telephone2", databaseInfo.getTelephone2()!=null?databaseInfo.getTelephone2():"");//联系人手机
					map.put("iscost", databaseInfo.getIscost());//是否收费（0免费，1收费）
				}
			}else{
				DatabaseOrgInfoRequest databaseOrgRequest = new DatabaseOrgInfoRequest();
				databaseOrgRequest.setStieCode(siteCode);
				List<DatabaseOrgInfo> orgInfoList = databaseOrgInfoServiceImpl.queryList(databaseOrgRequest);
				
				if(orgInfoList.size()>0){
					DatabaseOrgInfo databaseOrgInfo = orgInfoList.get(0);
					map.put("siteCode", siteCode);//网站标识码
					map.put("vcode", databaseOrgInfo.getVcode());//网站校验码
					map.put("siteName", databaseOrgInfo.getName()!=null?databaseOrgInfo.getName():"");//网站名称
					map.put("province", "");//省
					map.put("city", "");//市
					map.put("county", "");//县
					map.put("level", "");//级别
					map.put("url", "");//url地址
					map.put("jumpUrl", "");//跳转url
					map.put("director", "");//网站主办单位
					map.put("address", "");//办公地址
					map.put("linkmanName", databaseOrgInfo.getLinkmanName()!=null?databaseOrgInfo.getLinkmanName():"");//联系人
					map.put("principalName", databaseOrgInfo.getPrincipalName()!=null?databaseOrgInfo.getPrincipalName():"");//负责人
					map.put("mobile", "");//负责人办公电话
					map.put("telephone", databaseOrgInfo.getPrincipalPhone()!=null?databaseOrgInfo.getPrincipalPhone():"");//负责人手机
					map.put("principalPost", "");//负责人职务
					map.put("mobile2", "");//联系人办公电话
					map.put("telephone2", databaseOrgInfo.getLinkmanPhone()!=null?databaseOrgInfo.getLinkmanPhone():"");//联系人手机
					map.put("iscost", "");//是否收费（0免费，1收费）
				}
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("url", uri);
			dataMap.put("dataUrl", StringEncrypt.encrypt(JSONObject.fromObject(map).toString(),
					StringEncrypt.PASSWORD_CRYPT_KEY));
					
			writerPrint(JSONObject.fromObject(dataMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * @Title: 控制 县级下属 非门户   不显示  试用云搜索
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-26下午3:19:28
	 */
	public void queryCloudSearchHide(){
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			int type=0;
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			DatabaseTreeInfoRequest spotSiteRequest=new DatabaseTreeInfoRequest();
			spotSiteRequest.setIsBigdata(1);
			spotSiteRequest.setSiteCode(siteCode);
			spotSiteRequest.setIsexp(1);
			List<DatabaseInfo> siteList=databaseTreeInfoServiceImpl.queryDatabaseInfoList(spotSiteRequest);
			for(DatabaseInfo databaseInfo :siteList){
				String parentId=databaseInfo.getParentId();
				if(databaseInfo.getIsorganizational()==1){
					type=1;//门户显示
					break;
				}else{
					DatabaseTreeInfoRequest orgRequest=new DatabaseTreeInfoRequest();
					orgRequest.setIsBigdata(1);
					orgRequest.setId(Integer.valueOf(parentId));
					List<DatabaseInfo> orgList=databaseTreeInfoServiceImpl.queryDatabaseInfoList(orgRequest);
					if(orgList.size()>0){
						DatabaseInfo infoBean=orgList.get(0);
						if(infoBean.getParentId().equals("0")){
							type=0;//县级 非门户不显示
							break;
						}else{
							type=1;//县级门户显示
						}
					}
				}
			
			}
			dataMap.put("type", type);
			writerPrint(JSONObject.fromObject(dataMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * liukl 修改用户的引导状态
	 * @param siteCode
	 * @return
	 */
	public void updateGuiteState(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			String siteCode = request.getParameter("siteCode");
			if(StringUtils.isNotEmpty(siteCode)){
				String claseName = Thread.currentThread().getStackTrace()[1].getClassName();
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				changeGuideState(siteCode);//修改引导状态
				//通过登录长度来判断组织单位和填报单位
				if(siteCode.length()<=6){
					DatabaseOrgInfoRequest orgInfoRequest = new DatabaseOrgInfoRequest();
					orgInfoRequest.setStieCode(siteCode);
					List<DatabaseOrgInfo> orgInfoList = databaseOrgInfoServiceImpl.queryList(orgInfoRequest);
					DatabaseOrgInfo orgInfo = null;
					if(orgInfoList.size()>0){
						orgInfoList.get(0).getId();
						orgInfo = new DatabaseOrgInfo();
						orgInfo.setId(orgInfoList.get(0).getId());
						orgInfo.setGuideState(1);
						databaseOrgInfoServiceImpl.update(orgInfo);
						dataMap.put("success", "新菜单页面引导完成");
					}
				}else{
					DatabaseInfoRequest infoRequest = new DatabaseInfoRequest();
					infoRequest.setSiteCode(siteCode);
					List<DatabaseInfo> infoList = databaseInfoServiceImpl.queryList(infoRequest);
					DatabaseInfo oldataBaseInfo = null;
					DatabaseInfo dataBaseInfo = null;
					if(infoList.size()>0){
						oldataBaseInfo = infoList.get(0);
						String oldStr = oldataBaseInfo.toString();
						dataBaseInfo=infoList.get(0);
						dataBaseInfo.setGuideState(1);
						String newStr = dataBaseInfo.toString();
						databaseInfoServiceImpl.update(dataBaseInfo);
						dataMap.put("success", "新菜单页面引导完成");
						
						//添加databaseInfo修改日志 add by Na.Y 20160929
						//当前类名，方法名
						try {
							logCommonServiceImpl.createLog(dataBaseInfo.getSiteCode(), DatabaseInfo.class.getName(), oldStr, newStr,
									LogType.DB_U_B, Constants.getCurrendUser().getUserName(), claseName +"."+methodName);
							logger.info("成功加入日志");
						} catch (Exception e) {
							e.printStackTrace();
							logger.info("edit databaseInfo save log error...errorMsg:" + e.getMessage());
						}
					}
					
					
				}
			}
			writerPrint(JSONObject.fromObject(dataMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * liukl 获取用户引导状态
	 * @param siteCode
	 * @return
	 */
	public void selectGuiteState(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			//根据用户名查询引导状态
			String siteCode = request.getParameter("siteCode");
			if(StringUtils.isNotEmpty(siteCode)){
				if(siteCode.length()<=6){
					DatabaseOrgInfoRequest orgInfoRequest = new DatabaseOrgInfoRequest();
					orgInfoRequest.setStieCode(siteCode);
					List<DatabaseOrgInfo> orgInfoList = databaseOrgInfoServiceImpl.queryList(orgInfoRequest);
					if(orgInfoList.size()>0){
						orgInfoList.get(0).getGuideState();
						dataMap.put("guiDeState", orgInfoList.get(0).getGuideState());
					}
				}else{
					DatabaseInfoRequest infoRequest = new DatabaseInfoRequest();
					infoRequest.setSiteCode(siteCode);
					List<DatabaseInfo> infoList = databaseInfoServiceImpl.queryList(infoRequest);
					if(infoList.size()>0){
						infoList.get(0).getGuideState();
						dataMap.put("guiDeState", infoList.get(0).getGuideState());
					}
				}
			}
			writerPrint(JSONObject.fromObject(dataMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	public DicUtils getDicUtils() {
		return dicUtils;
	}

	public void setDicUtils(DicUtils dicUtils) {
		this.dicUtils = dicUtils;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
