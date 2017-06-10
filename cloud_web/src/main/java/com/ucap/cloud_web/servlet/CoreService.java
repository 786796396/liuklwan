package com.ucap.cloud_web.servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;


import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.publics.util.utils.HttpClientUtils;
import com.publics.util.utils.PropertiesUtil;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.servlet.message.Article;
import com.ucap.cloud_web.servlet.message.TextMessage;
import com.ucap.cloud_web.servlet.util.CommonUtils;
import com.ucap.cloud_web.servlet.util.MessageUtil;
import com.ucap.cloud_web.servlet.message.NewsMessage;


/** 
 * 核心服务类 
 *  
 * @author lixuxiang 
 */  
@Controller
@Scope("prototype")
public class CoreService{  

	private static Log logger = LogFactory.getLog(CoreService.class);
	
	private static Properties prop = PropertiesUtil.getProperties("app.properties");
	private static List<String> rmSameList =new ArrayList<String>(); 
	
    /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {  
    	
    	
    	String respContent="";
        String respMessage =null;  
        try {  
            // 默认返回的文本消息内容  
        	//respContent = "请求处理异常，请稍候尝试！";  
        	// xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
            logger.info("requestMap:"+requestMap);
            // 发送方帐号（open_id）  
           String fromUserName = requestMap.get("FromUserName");  
           // 公众帐号  
           String toUserName = requestMap.get("ToUserName");  
           // 消息类型  
           String msgType = requestMap.get("MsgType");  
           
   		   String createTime = requestMap.get("CreateTime");
   		   
   		   String msgId = requestMap.get("MsgId");
   		   //微信用户发送的消息内容
   		   String contentStr= requestMap.get("Content");  
   		   
           
   		   logger.info("msgId:"+msgId+"  createTime:"+createTime+"  fromUserName:"+fromUserName+"	toUserName:"+toUserName);
           
   		   Boolean bool=false;//判断内存集合中是否存在keyStr
   		   String keyStr="";
   		  // rmSameList.clear();
   		   
   		   if(StringUtils.isNotEmpty(msgId)){//消息类型排重  通过信息号id排重
   			   keyStr=msgId;
   		   }else{//推送事件排重    通过创建时间和微信客户唯一标识排重
   			   keyStr=fromUserName+createTime;
   		   }
   		   logger.info("keyStr:"+keyStr);
           
           if(rmSameList!=null && rmSameList.size()>0){
        	   for(int i=0;i<rmSameList.size();i++){
        		   if(rmSameList.get(i).equals(keyStr)){
        			   bool=true;
        		   }
        	   }
           }
   		   
           if(!bool){
        	   rmSameList.add(keyStr);
               // 文本消息  
               if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	   
            	   if("集约化".equals(contentStr) || "案例".equals(contentStr)){//这两种是要发送图文消息
                       /*if("集约化".equals(contentStr)){
                    	   respContent="<a href='http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504214933&idx=1&sn=2cb9b0a789e43729425c721c6e3dedc9&chksm=72aa19cc45dd90da9328cad09168a98760d05458490a44da868ee60bc9e7a34db34da0da6969&scene=0#wechat_redirect'>集约化信息</a>";
                       }else if("案例".equals(contentStr)){
                    	   respContent="<a href='http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504214932&idx=1&sn=d3d38caadb182873eaebc33ac59e8c16&chksm=72aa19cd45dd90dba5da17a3f1c5de74a6e26c1711f519746de830eb722de7eae7ee952fce11&scene=0#wechat_redirect'>案例</a>";
                       }*/
            		   
            		   //封装多图文消息
            		   NewsMessage newsMessage=new NewsMessage(); 
            		   newsMessage.setToUserName(fromUserName);
            		   newsMessage.setFromUserName(toUserName);
            		   newsMessage.setCreateTime(new Date().getTime());
            		   newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            		  
            		   //图文消息集合
            		   List<Article> artList=new ArrayList<Article>();
            		   if("集约化".equals(contentStr)){
            			   newsMessage.setArticleCount(4);
            			   /*String localUrl=prop.getProperty("localUrl");
            			   logger.info(localUrl+"/images/wein/0.jpg");*/
            			   Article article1=new Article();
            			   article1.setTitle("政府网站集约化视点一：集约化就是做网站群吗？");
            			   article1.setDescription("集约化就是做网站群吗？       ——政府网站集约化概念解析政府网站集约化概念最早由我国政府信息化专家于2");
            			   article1.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_jpg/CiaCEYDEVU5o3UibMWXjavsou5kiamWicn0Z44VCZaAib6vNNUWsECicBPk5nGm4CnXyvEeIveHT3jEfVv2yZiadTGmyQ/0?wx_fmt=jpeg");
            			   article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504214933&idx=1&sn=2cb9b0a789e43729425c721c6e3dedc9&chksm=72aa19cc45dd90da9328cad09168a98760d05458490a44da868ee60bc9e7a34db34da0da6969&scene=0#wechat_redirect");
            			   artList.add(article1);
            			   
            			   Article article2=new Article();
            			   article2.setTitle("政府网站集约化视点二：你必须要知道的几种集约化类型（1）");
            			   article2.setDescription("不管是出于政府网站自身建设需要，还是迫于社会公众对网上政府公共服务改革的迫切要求，集约化已经成为当前政府网站");
            			   article2.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_png/CiaCEYDEVU5qkm3KDyGT1QlA8c4gQmXwmThI3mpib8Y9XythH48xJibh2V4ibZg8pEU7Sl8rHJjFpiar9Cuyf7W2LSw/0?wx_fmt=png");
            			   article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504214933&idx=2&sn=83e5799eca9eb7f7f82cef4c4bbd8593&chksm=72aa19cc45dd90da498bf59049e7dc8df4c181e07bae1b4187042abc117d8a2fcd7acbaab4ce&scene=0#wechat_redirect");
            			   artList.add(article2);
            			   
            			   Article article3=new Article();
            			   article3.setTitle("政府网站集约化视点二：你必须要知道的几种集约化类型（2）");
            			   article3.setDescription("你必须要知道的几种集约化类型—政府网站集约化建设类型解析政府网站集约化建设有三种类型：基础设施集约化、应用支");
            			   article3.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_png/CiaCEYDEVU5qkm3KDyGT1QlA8c4gQmXwmThI3mpib8Y9XythH48xJibh2V4ibZg8pEU7Sl8rHJjFpiar9Cuyf7W2LSw/0?wx_fmt=png");
            			   article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504214933&idx=3&sn=02489689f1ba990dbdb4f51132d494ea&chksm=72aa19cc45dd90daf8292670a4d73962344139f7dad69b0e4babf592ff0be3eb6a1e02f4e5df&scene=0#wechat_redirect");
            			   artList.add(article3);
            			   
            			   Article article4=new Article();
            			   article4.setTitle("政府网站集约化视点二：你必须要知道的几种集约化类型（3）");
            			   article4.setDescription("政府网站集约化建设有三种类型：基础设施集约化、应用支撑平台集约化和服务集约化。上期介绍了应用支撑平台集约化的");
            			   article4.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_png/CiaCEYDEVU5qkm3KDyGT1QlA8c4gQmXwmThI3mpib8Y9XythH48xJibh2V4ibZg8pEU7Sl8rHJjFpiar9Cuyf7W2LSw/0?wx_fmt=png");
            			   article4.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504214933&idx=4&sn=4c28fb0d469f6a369fc80eaa9b65784c&chksm=72aa19cc45dd90da9935a72cf7e9e02109bbfb372936a00592f77e89633384107b644288b300&scene=0#wechat_redirect");
            			   artList.add(article4);
            		   }else if("案例".equals(contentStr)){
            			   newsMessage.setArticleCount(5);
            			  
            			   Article article1=new Article();
            			   article1.setTitle("开普互联助力文化部网站监管");
            			   article1.setDescription("为摸清文化行业内网站的“健康家底”，做好辖区内网站的监督与管理，2016年7月，国家文化部与开普互联就网站监");
            			   article1.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_jpg/CiaCEYDEVU5rqzBkmlH974Qvib3T4wDNHAlSFrV0aX3d0QUmDaJcnb0leAcMDeDsAjXg7Y0QEPfUhShXb8GlPExA/0?wx_fmt=jpeg");
            			   article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504214932&idx=1&sn=d3d38caadb182873eaebc33ac59e8c16&chksm=72aa19cd45dd90dba5da17a3f1c5de74a6e26c1711f519746de830eb722de7eae7ee952fce11&scene=0#wechat_redirect");
            			   artList.add(article1);
            			   
            			   Article article2=new Article();
            			   article2.setTitle("开普互联助力国家发展改革委开启政务大厅“微服务”");
            			   article2.setDescription("为积极贯彻落实党中央国务院关于“互联网+政务服务”重要部署，进一步拓展服务渠道，提升政务服务效能，由开普互联");
            			   article2.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_png/CiaCEYDEVU5qkm3KDyGT1QlA8c4gQmXwmKORrbnZO7VxETTr10iaNcmzvP1YiaScfNVw2ibibWpibDIamcEGWulnc44g/0?wx_fmt=png");
            			   article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504214932&idx=2&sn=3ba63de5e22cc115d59e341fd6fe84c6&chksm=72aa19cd45dd90db66265991fd00193fe4a9ca854b05c68912365510930a54544c26e133df2c&scene=0#wechat_redirect");
            			   artList.add(article2);
            			   
            			   Article article3=new Article();
            			   article3.setTitle("首都之窗升级云搜索门户 信息化发展迈上新台阶");
            			   article3.setDescription("为加快信息化建设进程，更好地服务人民群众。日前，由开普互联承建的北京市政府首都之窗网站群云搜索门户（简称首都）");
            			   article3.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_png/CiaCEYDEVU5rqzBkmlH974Qvib3T4wDNHAVxu5MWHTAn9KpsFSpzfPib7tOaygUzTrnnx9zUjoevBA4BabaibYiakSQ/0?wx_fmt=png");
            			   article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504214932&idx=3&sn=25dd7960735f25a904598e485b71e092&chksm=72aa19cd45dd90db11bbad5b8c27317a06dc6d561ffc72dec125c3ab0b416154d1b007de6048&scene=0#wechat_redirect");
            			   artList.add(article3);
            			   
            			   Article article4=new Article();
            			   article4.setTitle("句容政府携手开普互联构建一体化为民服务平台");
            			   article4.setDescription("日前，江苏省句容市人民政府网站完成升级上线。（www.jurong.gov.cn）句容人民政府门户网站是句容");
            			   article4.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_png/CiaCEYDEVU5rqzBkmlH974Qvib3T4wDNHARrxPB5KXfA2OnckBHEOWuP97ibMtUyX9BSEkiaAKNMnn353hJpnibvrfg/0?wx_fmt=png");
            			   article4.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504214932&idx=4&sn=e46c6dc65bd50de8c0c4b16f7514cfb3&chksm=72aa19cd45dd90dbed55bc7bb7791cb3135ca75d38823cdbd912ea6c0453cc960b2322fe8198&scene=0#wechat_redirect");
            			   artList.add(article4);
            			   
            			   Article article5=new Article();
            			   article5.setTitle("开普互联助力四川省人民政府构建智能搜索门户");
            			   article5.setDescription("四川省人民政府网站经过多年发展，积累了丰富的便民信息和服务资源。然而，随着网站信息的剧增，“信息过载”却给网");
            			   article5.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_png/CiaCEYDEVU5rqzBkmlH974Qvib3T4wDNHAsbAsJOhchLvId2hDyC0kAgy4qzqn9bYWAQVHiasoyc8mGZjMZKBlMZg/0?wx_fmt=png");
            			   article5.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504214932&idx=5&sn=df1574d657ae17ad8a145c15b2fe4fd4&chksm=72aa19cd45dd90dbee2b621a2494c48ee12bc193acbc257896e755349c5575a79f308686017d&scene=0#wechat_redirect");
            			   artList.add(article5);
            		   }
            		   newsMessage.setArticles(artList);
            		   respMessage = MessageUtil.newsMessageToXml(newsMessage);
            	   }else{//其他发送文本消息
            		// 回复文本消息  
                       TextMessage textMessage = new TextMessage();
                       textMessage.setToUserName(fromUserName);  
                       textMessage.setFromUserName(toUserName);  
                       textMessage.setCreateTime(new Date().getTime());  
                       textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
                       textMessage.setFuncFlag(0);  
                       
                       if("错别字".equals(contentStr) || "严重错别字".equals(contentStr)){
                    	   respContent="<a href='http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=2651698440&idx=2&sn=04cf49bbd2f8a021e69df5a48a0ec460#rd'>《政府网站常出现的错别字》</a>";
                       }else if("帮助".equals(contentStr)){
                    	   respContent="<a href='https://jg.kaipuyun.cn/ce/zhxg/help.shtml'>开普云监管帮助中心</a>";
                       }else if("抽查".equals(contentStr)){
                    	   respContent="<a href='http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=2651698515&idx=1&sn=aa51c5b0f682f71f240f2e157e1fbb7f#rd'>国务院办公厅关于2016年第二次全国政府网站抽查情况的通报</a>";
                       }else if("购买".equals(contentStr)|| "价格".equals(contentStr)|| "钱".equals(contentStr)){
                    	   respContent="购买与支持请拨打客服电话4000-976-005";
                       }else if("版本".equals(contentStr) || "版本".equals(contentStr)){
                    	   respContent="<a href='https://www.kaipuyun.cn/ce/banben/version.shtml'>开普云监管版本介绍</a>";
                       }else if("健康指数".equals(contentStr) || "健康指数".equals(contentStr)){
                    	   respContent="健康指数是一个网站或站群在一个监测周期内的健康情况、更新情况的综合指标评价指数。这一指数是基于开普云监管平台的全国所有政府网站的大数据收集与分析得出，是开普云监管独有的。此指数可以反映出全国政府网站的综合运行情况，以及本站(群)与全国平均水平的对比。";
                       }else if("检测".equals(contentStr) ||  "监测".equals(contentStr) || "监管".equals(contentStr)){
                    	   respContent="<a href='https://www.kaipuyun.cn/'>https://www.kaipuyun.cn</a>";
                       }else{
                    	   respContent="收到您的消息啦！如果可以，请您详细描述问题，云菇凉尽力为您解答。如您有急事请拨打客服电话4000-976-005，我们会有专人为您服务。";
                       }
                       textMessage.setContent(respContent);  
             	       respMessage = MessageUtil.textMessageToXml(textMessage);
            	   }
               }
               // 图片消息  
               else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                   respContent = "收到您的消息啦！如果可以，请您详细描述问题，云菇凉尽力为您解答。如您有急事请拨打客服电话4000-976-005，我们会有专人为您服务。";  
               }  
               // 地理位置消息  
               else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                   respContent = "收到您的消息啦！如果可以，请您详细描述问题，云菇凉尽力为您解答。如您有急事请拨打客服电话4000-976-005，我们会有专人为您服务。";  
               }  
               // 链接消息  
               else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                   respContent = "收到您的消息啦！如果可以，请您详细描述问题，云菇凉尽力为您解答。如您有急事请拨打客服电话4000-976-005，我们会有专人为您服务。";  
               }  
               // 音频消息  
               else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                   respContent = "收到您的消息啦！如果可以，请您详细描述问题，云菇凉尽力为您解答。如您有急事请拨打客服电话4000-976-005，我们会有专人为您服务。";  
               }  
               // 事件推送  
               else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                   // 事件类型  
                   String eventType = requestMap.get("Event");
                   
                   String eventKey=requestMap.get("EventKey");
                   
                   logger.info("======================eventType:"+eventType+"  eventKey:"+eventKey);
                   String urlStr=(String) prop.get("localUrl");
                   String weiUrl=(String) prop.get("requestUrl");
                   String bindUrl=urlStr+"/rest/index/approveAccount";
                   
                   //扫码
                   if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
              		   String paramStr="openId="+fromUserName+"&id="+eventKey;
              		   HttpClientUtils.sendGetCommand(paramStr, bindUrl);
                	   
              		   System.out.println(MessageUtil.EVENT_TYPE_UNSUBSCRIBE+" bindUrl:"+bindUrl);
              		   System.out.println(MessageUtil.EVENT_TYPE_UNSUBSCRIBE+" paramStr:"+paramStr);
              		   
                       TextMessage textMessage = new TextMessage();
                       textMessage.setToUserName(fromUserName);  
                       textMessage.setFromUserName(toUserName);  
                       textMessage.setCreateTime(new Date().getTime());  
                       textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
                       textMessage.setFuncFlag(0);  
                       
                       respContent="亲，您来啦！开普云等候您多时了！您可以使用本公众号实时查看网站监测情况、接收问题预警消息。 \n<a href='"+weiUrl+"/newToken_checkResultIndex.action?openId="+fromUserName+"'>快点击这里，绑定您的网站吧！</a>";
                       //respContent="Hello，云菇凉终于等到您啦，欢迎关注我们！云监管平台以科学、规范、全面的监管服务标准，满足政府网站日常监测、辅助抽查、站群监管、大数据分析等常态化监管需求。请您<a href='"+weiUrl+"/newToken_accountBindIndex.action?fromType=&openId="+fromUserName+"'>绑定</a>网站标识码/组织单位用户名，实时查看监测结果、接收预警提醒。";
                      
                       textMessage.setContent(respContent);  
                       respMessage = MessageUtil.textMessageToXml(textMessage);
           	           logger.info("CoreService respMessage:"+respMessage);
                   }
                   // 订阅  
                   if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                	   
                       TextMessage textMessage = new TextMessage();
                       textMessage.setToUserName(fromUserName);  
                       textMessage.setFromUserName(toUserName);  
                       textMessage.setCreateTime(new Date().getTime());  
                       textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
                       textMessage.setFuncFlag(0);  
                	   
                	   if(StringUtils.isNotEmpty(eventKey)){
                  		   
                  		   String id=eventKey.substring(8);
                  		   String paramStr="openId="+fromUserName+"&id="+id;
                  		   
                  		   logger.info("paramStr:"+paramStr);
                  		  
                  		   System.out.println(MessageUtil.EVENT_TYPE_UNSUBSCRIBE+" bindUrl:"+bindUrl);
                  		   System.out.println(MessageUtil.EVENT_TYPE_UNSUBSCRIBE+" paramStr:"+paramStr);
                  		   
                  		   HttpClientUtils.sendGetCommand(paramStr, bindUrl);
                  		  // respContent="Hello，云菇凉终于等到您啦，欢迎关注我们！云监管平台以科学、规范、全面的监管服务标准，满足政府网站日常监测、辅助抽查、站群监管、大数据分析等常态化监管需求。请您<a href='"+weiUrl+"/token_accountBindIndex.action?fromType=&openId="+fromUserName+"'>绑定</a>网站标识码/组织单位用户名，实时查看监测结果、接收预警提醒。";
                  		 respContent="亲，您来啦！开普云等候您多时了！您可以使用本公众号实时查看网站监测情况、接收问题预警消息。 \n<a href='"+weiUrl+"/newToken_accountBindIndex.action?openId="+fromUserName+"'>快点击这里，绑定您的网站吧！</a>";
                	   }else{
                		   respContent="亲，您来啦！开普云等候您多时了！您可以使用本公众号实时查看网站监测情况、接收问题预警消息。\n<a href='"+weiUrl+"/newToken_accountBindIndex.action?openId="+fromUserName+"'>快点击这里，绑定您的网站吧！</a>";
                		  // respContent="Hello，云菇凉终于等到您啦，欢迎关注我们！云监管平台以科学、规范、全面的监管服务标准，满足政府网站日常监测、辅助抽查、站群监管、大数据分析等常态化监管需求。请您<a href='"+weiUrl+"/token_accountBindIndex.action?fromType=&openId="+fromUserName+"'>绑定</a>网站标识码/组织单位用户名，实时查看监测结果、接收预警提醒。";
                	   }
                	  
                       textMessage.setContent(respContent);  
                       respMessage = MessageUtil.textMessageToXml(textMessage);
           	           logger.info("CoreService respMessage:"+respMessage);
                	   
                   }  
                   // 取消订阅  并解绑
                   else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { 
              		   bindUrl=urlStr+"/rest/index/removeBindAccount";
              		   String paramStr="openId="+fromUserName;
              		   
              		   System.out.println(MessageUtil.EVENT_TYPE_UNSUBSCRIBE+" bindUrl:"+bindUrl);
              		   System.out.println(MessageUtil.EVENT_TYPE_UNSUBSCRIBE+" paramStr:"+paramStr);
              		   
              		   logger.info(MessageUtil.EVENT_TYPE_UNSUBSCRIBE+"  openId:"+fromUserName);
              		   HttpClientUtils.sendGetCommand(paramStr, bindUrl);
                	   
                       // 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                   } 
                   //自定义菜单view页面直接跳转事件
                   else if(eventType.equals(MessageUtil.EVENT_TYPE_VIEW)){


                   }
                    // 自定义菜单点击事件  
                   else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                       // 联系方式点击事件  
                       if (eventKey.equals(MessageUtil.ECENT_TYPE_CLICK_V1003_CLOUD)) { 
                    	   // 回复文本消息  
                           TextMessage textMessage = new TextMessage();
                           textMessage.setToUserName(fromUserName);  
                           textMessage.setFromUserName(toUserName);  
                           textMessage.setCreateTime(new Date().getTime());  
                           textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
                           textMessage.setFuncFlag(0);  
                           respContent = "地址：北京市海淀区知春路23号量子银座6层  \n邮编：100191 \n电话：010-82350961 \n客服邮箱：jg_rp@ucap.com.cn \n官方网站：https://www.kaipuyun.cn/ \n销售及客服热线：\n4000-976-005 \n如需了解分公司联系方式，请访问公司网站： \nhttp://www.ucap.com.cn/ucap/lxwm/column_sercom.shtml";  
                           
                           textMessage.setContent(respContent);  
                 	       respMessage = MessageUtil.textMessageToXml(textMessage);
                       }else if(eventKey.equals(MessageUtil.ECENT_TYPE_CLICK_V1004_CLOUD)){
                    	   //封装多图文消息
                		   NewsMessage newsMessage=new NewsMessage(); 
                		   newsMessage.setToUserName(fromUserName);
                		   newsMessage.setFromUserName(toUserName);
                		   newsMessage.setCreateTime(new Date().getTime());
                		   newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                		   newsMessage.setArticleCount(2);
                		   //图文消息集合
                		   List<Article> artList=new ArrayList<Article>();
                		   
            			   /*String localUrl=prop.getProperty("localUrl");
            			   logger.info(localUrl+"/images/wein/0.jpg");*/
            			   Article article1=new Article();
            			   article1.setTitle("开普云受邀出席“第六届中国政府门户网站发展论坛”");
            			   article1.setDescription("开普云聚焦政府网站建设的“新常态、新目标、新服务”第六届中国政府门户网站发展论坛现场2016年10月28日至");
            			   article1.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_jpg/CiaCEYDEVU5oWnsibL09Sy828pw84LugB8PU4xITuuUaogC3JJD00ibwfRHq7AdMfqVXENUqFicDOs8ZvovpO49u0g/0?wx_fmt=jpeg");
            			  // article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504215014&idx=1&sn=9bc00ba51ff4820836af063c840b9c01&chksm=72aa19bf45dd90a912d4a75fd6dc2260efb517ff7de67845602ccf1adf3c41c31015d3480e28&scene=0#wechat_redirect");
            			   article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzI3ODA0MjcwMQ==&mid=502571015&idx=1&sn=55fc115bd11d80ef35173ff350af8e68&chksm=735d0125442a883338769c12cc40db1aa9b5a7f4f81a69852775f15bc1bad6eddc8d07a6dd2a&scene=0#wechat_redirect");
            			   artList.add(article1);
            			   
            			   Article article2=new Article();
            			   article2.setTitle("政务公开要透明高效  舆情回应要主动及时”");
            			   article2.setDescription("“政务公开是政府必须依法履行的职责。只要不涉及国家安全等事宜，政务公开就是常态，不公开是例外！”李克强总理在");
            			   article2.setPicUrl("https://mmbiz.qlogo.cn/mmbiz_jpg/CiaCEYDEVU5oY95OsuAtNDKfmwLB4bcVvS5CDNibKSpyw2BMmbYqcqrdZphUFrtWbicrHcTJlv18ZsmibibChHltrJw/0?wx_fmt=jpeg");
            			   //article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0NTE0NDg4Ng==&mid=504215014&idx=2&sn=d97c6d92203bf69e80efb281991bcd81&chksm=72aa19bf45dd90a9d70dc05499b727cf2743f9d8e9f68a2510d0bd5c0984e4f7f14fc2370f66&scene=0#wechat_redirect");
            			   article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzI3ODA0MjcwMQ==&mid=502571015&idx=2&sn=71677519fd2859b71c24af5511fb7db3&chksm=735d0125442a8833f81043bb8547ab23c1f14110563bad5a0b6e3a1c303ea87302c258ad7e42&scene=0#wechat_redirect");
            			   artList.add(article2);
                		   
            			   newsMessage.setArticles(artList);
                		   respMessage = MessageUtil.newsMessageToXml(newsMessage);
                       }
                   }  
               } 
           }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return respMessage;  
    } 
}