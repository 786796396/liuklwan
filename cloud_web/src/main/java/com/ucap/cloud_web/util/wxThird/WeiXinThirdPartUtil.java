package com.ucap.cloud_web.util.wxThird;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.PropertiesUtil;
import com.ucap.cloud_web.action.WeiXinTheThirdPartyAction;
import com.ucap.cloud_web.servlet.util.CommonUtils;

import net.sf.json.JSONObject;

public class WeiXinThirdPartUtil {
	public static Map<String,String> tokenMap=null;
	private static Properties prop = PropertiesUtil.getProperties("app.properties");
	static String componentAppid="";
	static String componentAppsecret="";
	private static void init(){
		componentAppid=prop.getProperty("weixinThirdPart_appid");
		componentAppsecret=prop.getProperty("weixinThirdPart_secret");
	}
	
		/**
	     * 获得component_access_token
	     */
	public static String getAccessToken(){
		System.out.println("获得token开始=========");
		String access_token="";
		if(tokenMap==null){
			Map<String,String> tokenMapU=WeiXinTheThirdPartyAction.ticketMap;
			String ticket=tokenMapU.get("ComponentVerifyTicket");
			return getAccessToken(ticket);
		}else{
			access_token=(String) tokenMap.get("access_token");
			String token_time=(String) tokenMap.get("token_time");
			int mt=DateUtils.getMinutesBetween2Days(DateUtils.parseStandardDateTime(token_time), new Date());
			if(mt>110){//说明access_token已经超过7200秒，需要重新请求  
				tokenMap=null;
				return getAccessToken();
			}else{
				return tokenMap.get("access_token");
			}
		}
	}
	 /**
     * 获得component_access_token
     * 
     * @param ComponentVerifyTicket
     * 
     *
     */
   public static String getAccessToken(String ticket){
	   if("".equals(componentAppid) || "".equals(componentAppsecret)){
		   init();
	   }
    	String urlFormat = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
	    JSONObject data = new JSONObject();
	    data.put("component_appid", componentAppid);
	    data.put("component_appsecret", componentAppsecret);
	    data.put("component_verify_ticket", ticket);
	    String json = data.toString();
	    JSONObject responseJson = CommonUtils.httpRequest(urlFormat,"POST",json);
	    String component_access_token=responseJson.getString("component_access_token");
	    if(StringUtils.isNotEmpty(component_access_token)&&!"".equals(component_access_token)){
	    	tokenMap=new HashMap<String,String>();
	    	tokenMap.put("access_token", component_access_token);
	    	tokenMap.put("token_time", DateUtils.formatStandardDateTime(new Date()));
	    }
	    return component_access_token;
    }
   /**
    * 获得pre_auth_code
    * 
    * @param component_access_token
    * @param COMPONENT_APPID
    *
    */
  public static String getPreAuthCode(){
   	String PreAuthCodeURL="https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=";
   	StringBuilder sb=new StringBuilder();
   	sb.append(PreAuthCodeURL);
   	String accessToken=WeiXinThirdPartUtil.getAccessToken();
   	System.out.println("huode getPreAuthCode token===="+accessToken);
   	sb.append(accessToken);
   	sb.append("&component_appid");
   	sb.append(componentAppid);
   	JSONObject responseJson = CommonUtils.httpRequest(sb.toString(),"GET",null);
   	String preAuthCode=responseJson.getString("pre_auth_code");
   	goToWinXinAuthorization();
   	return preAuthCode;
   }
   /**
    *获得预授权码后跳转至授权页面
    * url：https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=xxxx&pre_auth_code=xxxxx&redirect_uri=xxxx
    * @param pre_auth_code
    * @param redirect_uri
    */
   public static void goToWinXinAuthorization(){
   	String authorizationURL="https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=";
   	StringBuilder sb=new StringBuilder();
   	sb.append(authorizationURL);
   	sb.append(componentAppid+"&pre_auth_code=");
   	sb.append(getPreAuthCode()+"&redirect_uri=");
   	sb.append("http://jgtest.kaipuyun.cn/cloud_web/weiXinTheThirdParty_getAuthorizationCode.action");
   	JSONObject responseJson = CommonUtils.httpRequest(sb.toString(),"GET",null);
   	System.out.println("跳转页面：：：：：成功===="+responseJson.toString());
   }
}
