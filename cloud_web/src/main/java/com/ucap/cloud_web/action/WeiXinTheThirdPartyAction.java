
package com.ucap.cloud_web.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.PropertiesUtil;
import com.ucap.cloud_web.servlet.util.CommonUtils;
import com.ucap.cloud_web.util.aes.AddSHA1;
import com.ucap.cloud_web.util.aes.AesException;
import com.ucap.cloud_web.util.aes.WXBizMsgCrypt;
import com.ucap.cloud_web.util.wxThird.WeiXinThirdPartUtil;

import net.sf.json.JSONObject;

/**
 * 微信公众账号第三方平台全网发布源码（java）
 * @author： jeewx开源社区
 * 
 * @date 20150801
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class WeiXinTheThirdPartyAction extends BaseAction{

	
	
	//private final String APPID = "???";
	
	private static Properties prop = PropertiesUtil.getProperties("app.properties");
	/**
	 * 微信全网测试账号
	 */
	//private static String COMPONENT_APPID="wxa829aad816b74d04";
	//private static String COMPONENT_APPSECRET="c3b4efddad00645cc4eb1ef5944964f4";
	private static String COMPONENT_APPID;
	private static String COMPONENT_APPSECRET;
	private final static String COMPONENT_ENCODINGAESKEY = "R3DeCcnNEX5fDJwzQloGdz8bXipA7SMsQlSebzXxyrV";
	private final static String COMPONENT_TOKEN = "winxin";
	public final static Map<String,String> ticketMap=new HashMap<String,String>();
	/*static{
		COMPONENT_APPID= prop.getProperty("weixinThirdPart_appid");//微信第三方平台开发者id
		COMPONENT_APPSECRET=prop.getProperty("weixinThirdPart_secret");//微信第三方平台开发秘钥
		String ticket=ticketMap.get("ComponentVerifyTicket");
		WeiXinThirdPartUtil.getAccessToken(COMPONENT_APPID,COMPONENT_APPSECRET,ticket);
	}*/
	public void init(){
		COMPONENT_APPID= prop.getProperty("weixinThirdPart_appid");//微信第三方平台开发者id
		COMPONENT_APPSECRET=prop.getProperty("weixinThirdPart_secret");//微信第三方平台开发秘钥
		String ticket=ticketMap.get("ComponentVerifyTicket");
		WeiXinThirdPartUtil.getAccessToken(ticket);
	}
	public void test(){
		System.out.println("test===COMPONENT_APPID::::"+COMPONENT_APPID);
		System.out.println("test===COMPONENT_APPSECRET::::"+COMPONENT_APPID);
		System.out.println("test===ticket::::"+ticketMap.get("ComponentVerifyTicket"));
		System.out.println("test===Accusse_token::::"+WeiXinThirdPartUtil.getAccessToken());
		System.out.println("test===getPreAuthCode::::"+WeiXinThirdPartUtil.getPreAuthCode());
		
	}
	 /**
     * 授权事件接收
     * 接受微信服务器的推送消息
     * @param request
     * @param response
     * @throws IOException
     * @throws AesException
     * @throws DocumentException
     */
   
    public void getWinXinPushMessage() {
    	try{
    		processAuthorizeEvent(request);
            output(response, "success"); // 输出响应的内容。 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    /**
     * 处理授权事件的推送
     * 
     * @param request
     * @throws IOException
     * @throws AesException
     * @throws DocumentException
     */
    public void processAuthorizeEvent(HttpServletRequest request) {
    	System.out.println("开始获取微信推送信息‘’‘’‘’‘’‘’‘’‘’‘’‘’‘’‘’‘’‘");
    	//获得微信推送的参数
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String signature = request.getParameter("signature");
        String msgSignature = request.getParameter("msg_signature");
        System.out.println("信息：：nonce：："+nonce+":::timestamp::"+timestamp+":::signature::"+signature+"::msgSignature:::"+msgSignature);
        try{
        	if (!StringUtils.isNotBlank(msgSignature)){
        		return;// 微信推送给第三方开放平台的消息一定是加过密的，无消息加密无法解密消息
        	} 
            boolean isValid = checkSignature(COMPONENT_TOKEN, msgSignature, timestamp, nonce);
        	isValid = true;
        	if (isValid) {
                StringBuilder sb = new StringBuilder();
                BufferedReader in = request.getReader();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                //微信推送的加密为信息
                String xml = sb.toString();
                //加密信息的解密工具类
                WXBizMsgCrypt pc = new WXBizMsgCrypt(COMPONENT_TOKEN, COMPONENT_ENCODINGAESKEY, COMPONENT_APPID);
                //微信推送的加密信息，解密后的xml
                xml = pc.DecryptMsg(msgSignature, timestamp, nonce, xml);
                //获得微信推送的ticket，并将其保存到map中
                processAuthorizationEvent(xml);
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
        
    }
    public void test2(){
    	
    	WXBizMsgCrypt pc;
		try {
			pc = new WXBizMsgCrypt(COMPONENT_TOKEN, COMPONENT_ENCODINGAESKEY, COMPONENT_APPID);
			String str=request.getParameter("str");
	    	pc.decrypt(str);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    /**
     * 保存Ticket
     * @param xml
     */
    public void processAuthorizationEvent(String xml){
    	Document doc;
		try {
			doc = DocumentHelper.parseText(xml);
			Element rootElt = doc.getRootElement();
			String ticket = rootElt.elementText("ComponentVerifyTicket");
			if(StringUtils.isNotEmpty(ticket)){
				ticketMap.put("ComponentVerifyTicket", ticket);
				System.out.println("::ticketticketticketticketticketticket::"+ticket);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    }
   
    /**
     * 授权成功后的回调方法
     * @param 
     * @param 
     */
    public void getAuthorizationCode(){
    	String authCode=request.getParameter("auth_code");
    	String authURL="https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=";
    	StringBuilder sb=new StringBuilder();
    	sb.append(authURL);
    	String accessToken=WeiXinThirdPartUtil.getAccessToken();
    	sb.append(accessToken+"&authorization_code=");
    	sb.append(authCode);
    	JSONObject responseJson = CommonUtils.httpRequest(sb.toString(),"GET",null);
    	String authorizer_refresh_token=responseJson.getString("authorizer_refresh_token");
    	System.err.println("======================authCode===================="+authCode);
    	System.err.println("=================authorizer_refresh_token========================="+authorizer_refresh_token);
    	output(response,"success授权成功");
    }
    /**
     * 工具类：回复微信服务器"文本消息"
     * @param response
     * @param returnvaleue
     */
    public void output(HttpServletResponse response,String returnvaleue){
		try {
			PrintWriter pw = response.getWriter();
			pw.write(returnvaleue);
			pw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /** 
     * 判断是否加密 
     * @param token 
     * @param signature 
     * @param timestamp 
     * @param nonce 
     * @return 
     */  
    public static boolean checkSignature(String token,String signature,String timestamp,String nonce){  
        System.out.println("###token:"+token+";signature:"+signature+";timestamp:"+timestamp+"nonce:"+nonce);  
           boolean flag = false;  
           if(signature!=null && !signature.equals("") && timestamp!=null && !timestamp.equals("") && nonce!=null && !nonce.equals("")){  
              String sha1 = "";  
              String[] ss = new String[] { token, timestamp, nonce };   
              Arrays.sort(ss);    
              for (String s : ss) {    
               sha1 += s;    
              }  
              sha1 = AddSHA1.SHA1(sha1);
              if (sha1.equals(signature)){  
               flag = true;  
              }  
           }  
           return flag;  
       }  
}  
    
   

