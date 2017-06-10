package com.ucap.cloud_web.servlet.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.esotericsoftware.minlog.Log.Logger;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.PropertiesUtil;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.UserType;
import com.ucap.cloud_web.servlet.CoreServlet;
import com.ucap.cloud_web.servlet.message.SNSUserInfo;
import com.ucap.cloud_web.servlet.message.Token;
import com.ucap.cloud_web.servlet.message.WeixinOauth2Token;
import com.ucap.cloud_web.servlet.message.WeixinToken;

/**
 * <p>Description: </p>工具类
 * <p>@Package：com.ucap.cloud_web.servlet.util </p>
 * <p>Title: CommonUtils </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-12-22下午2:52:55 </p>
 */
public class CommonUtils {
	private static Log log = LogFactory.getLog(CommonUtils.class);
	private static Properties prop = PropertiesUtil.getProperties("app.properties");
	//凭证获取  GET
	private final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//获取access_token和关注人openid
	private final static String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=appId&secret=appsecret&code=appCode&grant_type=authorization_code";

	
	private static List<WeixinToken> codeList=new ArrayList<WeixinToken>();

	
	//读取文件中的相关数据
	private static String appid= prop.getProperty("appid");//微信公众号开发者id
	private static String secret=prop.getProperty("secret");//微信公众号开发秘钥
	/**
	 * @Description:获取接口访问凭证 
	 * @author cuichx --- 2015-12-22下午5:20:31     
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static Token getToken(){
		Token token=null;
		if(StringUtils.isNotEmpty(appid) && StringUtils.isNotEmpty(secret)){
			//凭证获取
			String requestUrl=token_url.replace("APPID", appid).replace("APPSECRET", secret);
			log.info("CommonUtils getToken() requestUrl:"+requestUrl);
			//发起GET请求获取数据
			JSONObject jsonObject=httpRequest(requestUrl, "GET", null);
			if(jsonObject!=null){
				try {
					token =new Token();
					token.setAccessToken(jsonObject.getString("access_token"));
					token.setExpiresIn(jsonObject.getInt("expires_in"));
				} catch (Exception e) {
					// 获取Token失败
					e.printStackTrace();
				}
			}
		}
		return token;
	}
	
	
	/**
	 * @Description: 获取网页授权凭证
	 * @author cuichx --- 2015-12-22下午2:31:12     
	 * @param appId  公众账号的唯一标识
	 * @param appsecret 公众账号的秘钥
	 * @param code 
	 * @return
	 */
	public static WeixinOauth2Token getWeixinOauth2Token(String code){
		WeixinOauth2Token wat=null;
		//获取access_token和关注人openid
		log.info("getWeixinOauth2Token appId:"+appid+"   appsecret:"+secret+"   code:"+code);
		if(StringUtils.isNotEmpty(appid) && StringUtils.isNotEmpty(secret) && StringUtils.isNotEmpty(code)){
			String requestUrl = access_token_url.replace("appId", appid).replace("appsecret", secret).replace("appCode", code);
			log.info("WeixinOauth2Token  requestUrl:"+requestUrl);
			Boolean bool=false;
			if (codeList != null && codeList.size() > 0) {
				for (int i = 0; i < codeList.size(); i++) {
					WeixinToken weixinToken = codeList.get(i);
					if (weixinToken.getCode().equals(code)) {
						bool = true;

						wat = new WeixinOauth2Token();
						wat.setOpenId(weixinToken.getOpenId());
						wat.setAccessToken(weixinToken.getAccessToken());
						//codeList.clear();
						codeList.remove(codeList.get(i));
					}
				}
			}
	        if(!bool){//已经发送过该请求，再次请求直接返回空
	    		//获取网页授权凭证
	    		JSONObject jsonObject=CommonUtils.httpRequest(requestUrl, "GET", null);
	    		if(jsonObject!=null){
	    			try {
	    				wat=new WeixinOauth2Token();
	    				wat.setAccessToken(jsonObject.getString("access_token"));
	    				wat.setExpireIn(jsonObject.getInt("expires_in"));
	    				wat.setOpenId(jsonObject.getString("openid"));
	    				wat.setScope(jsonObject.getString("scope"));
	    				
	    				WeixinToken weixinToken = new WeixinToken();
					    weixinToken.setCode(code);
					    weixinToken.setOpenId(jsonObject.getString("openid"));
					    weixinToken.setAccessToken(jsonObject.getString("access_token"));
					    
					    
					    codeList.add(weixinToken);
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
	    		}
	        }
		}
		return wat;
	}
	
	
	/**
	 * @Description: 刷新网页授权凭证
	 * @author cuichx --- 2015-12-22下午5:42:29     
	 * @return
	 */
	public static WeixinOauth2Token refreshOauth2AccessToken(String refreshToken){
		WeixinOauth2Token wat=null;
		String requestUrl="https://api.weixin.qq.com/sns/oauth2" +
				"/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl.replace("APPID", appid);
		requestUrl.replace("REFRESH_TOKEN", refreshToken);
		//获取网页授权凭证
		JSONObject jsonObject=CommonUtils.httpRequest(requestUrl, "GET", null);
		if(jsonObject!=null){
			try {
				wat=new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpireIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_toen"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return wat;
	}
	
	/**
	 * @Description: 通过网页授权获取用户信息
	 * @author cuichx --- 2015-12-22下午6:23:57     
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static SNSUserInfo getSNSUserInfo(String accessToken,String openId){
		SNSUserInfo snsUserInfo=null;
		
		log.info("getSNSUserInfo accessToken:"+accessToken+"  openId:"+openId);
		
		//https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN 
		
		String requestUrl="https://api.weixin.qq.com/sns" +
				"/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
		
		log.info("getSNSUserInfo requestUrl:"+requestUrl);
				
		
		//通过网页授权获取用户信息
		JSONObject jsonObject=CommonUtils.httpRequest(requestUrl, "GET", null);
		if(jsonObject!=null){
			try {
				snsUserInfo=new SNSUserInfo();
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				snsUserInfo.setNickName(jsonObject.getString("nickname"));
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				snsUserInfo.setCountry(jsonObject.getString("country"));
				
				snsUserInfo.setProvince(jsonObject.getString("province"));
				snsUserInfo.setCity(jsonObject.getString("city"));
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				//snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"),List.class));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return snsUserInfo;
	}
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			//if ("GET".equalsIgnoreCase(requestMethod))
			httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
			
			log.info(">>>>>>>>>httpRequest  json>>>>>>>>>>>>>"+jsonObject);
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
	/**
	 * @Description: 获取内存中的access_token,
	 * 			如果内存中的access_token过期的话，重新调用微信获取access_token的接口获取，并更新内存中的数据
	 * @author cuichx --- 2016-1-5上午10:52:39     
	 * @return
	 */
	public static String getTokenFromServlet(){
		
		String access_token="";
		String token_time="";
		
		log.info("CommonUtils CoreServlet.paraMap:"+CoreServlet.paraMap);
		if(CoreServlet.paraMap==null){

			
			log.info("CommonUtils getTokenFromServlet()  appid:"+appid+"  secret:"+secret);
			access_token=CommonUtils.getToken().getAccessToken();
			token_time=DateUtils.formatStandardDateTime(new Date());
			
			log.info("CommonUtils getTokenFromServlet() access_token:"+access_token+"  token_time:"+token_time);
			//更新内存中的access_token和创建时间
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("access_token", access_token);
			map.put("token_time", token_time);
			CoreServlet.setParaMap(map);
		}else{
			access_token=(String) CoreServlet.paraMap.get("access_token");
			token_time=(String) CoreServlet.paraMap.get("token_time");
			
			log.info("CommonUtils getTokenFromServlet() token_time:"+token_time);
			int mt=DateUtils.getMinutesBetween2Days(DateUtils.parseStandardDateTime(token_time), new Date());
			log.info("CommonUtils getTokenFromServlet() mt:"+mt);
			
			if(mt>110){//说明access_token已经超过7200秒，需要重新请求  
				access_token=CommonUtils.getToken().getAccessToken();
				token_time=DateUtils.formatStandardDateTime(new Date());
				
				//更新内存中的access_token和创建时间
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("access_token", access_token);
				map.put("token_time", token_time);
				CoreServlet.setParaMap(map);
			}
		}

		return access_token;
	}

	
	/**
	 * 
	 * 描述:post请求，返回json数据
	 * 
	 * 作者：lxx	2015-11-25下午06:27:51
	 * @param strURL
	 * @param params
	 * @return
	 */
	public static String post(String strURL, String params) {  
        try { 
        	log.info("post strURL="+strURL+"  params="+params);
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  
            int length = (int) connection.getResponseCode();// 获取长度  
            if (length == 200) {  
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                String line;
                String result = "";
                while ((line = in.readLine()) != null) {
                	result += line;
                }
                return result;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return "error"; // 自定义错误信息  
    }
	
	/**
	 * @Description: 过滤掉微信昵称中的emoji表情字符串
	 * @author cuichx --- 2016-2-29下午2:26:33     
	 * @param nickname
	 * @return
	 */
	public  static String removeFaceCharacter(String nickname){
		
		log.info("nickname:"+nickname);
		String returnNickName="";
		
        Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
       if(StringUtils.isNotEmpty(nickname)){
           Matcher emojiMatcher = emoji.matcher(nickname);
           if(emojiMatcher !=null){
               if(emojiMatcher.find()) {
               	returnNickName = emojiMatcher.replaceAll("");
                if("".equals(returnNickName)){
                	returnNickName="ucap";
                }
               } else {
            	   returnNickName = nickname;
               }
           }
       }
        log.info("returnNickName:"+returnNickName);
		
		return returnNickName;
	}
	
	/**
	 * @Description: 判断当前登录的组织单位级别
	 * @author cuichx --- 2016-3-8下午4:54:31     
	 * @return
	 */
	public static String getLevel(String siteCode){
		String level="";
		//用户的类型： 1:国办 2：省份 3:市县 4:填报单位
		if (siteCode.equals("bm0100")) {
			level=UserType.TYPE_ADMIN.getCode()+"";
		}
		// 如果siteCode是以0000结尾的，则表示为组织单位
		if (siteCode.endsWith("0000")) {//省
			level =UserType.TYPE_PROVINCE.getCode()+"";
		} else if (siteCode.endsWith("00")) {//市
			level =UserType.TYPE_CITY.getCode()+"";
		} else {//县
			level =UserType.TYPE_COUNTY.getCode()+"";
		}
		return level;
	}
	
	
	
	
}
