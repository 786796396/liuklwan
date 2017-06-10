package com.ucap.cloud_web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.PropertiesUtil;
import com.ucap.cloud_web.servlet.util.CommonUtils;
import com.ucap.cloud_web.servlet.util.SignUtil;




public class CoreServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private static Log log = LogFactory.getLog(CoreServlet.class);
	
	private static Properties prop = PropertiesUtil.getProperties("app.properties");
	
	public static Map<String, Object> paraMap=null;
	
	private static Log logger = LogFactory.getLog(CoreServlet.class);
	/**
	 * Constructor of the object.
	 */
	public CoreServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/** 
     * 确认请求来自微信服务器 
     */ 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        logger.info("doGet  token >>>>>>>>>>>>>>>>>>>>>begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
      //  setTokenToSession(request);
        
		// 微信加密签名  
        String signature = request.getParameter("signature");  
        // 时间戳  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        // 随机字符串  
        String echostr = request.getParameter("echostr");  
        
        PrintWriter out = response.getWriter();
        
		
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		logger.info("CoreServlet doGet echostr:"+echostr);
		
		out.close();
		out = null;
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the GET method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
	}

	/** 
     * 处理微信服务器发来的消息 
     */ 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
       // setTokenToSession(request);
        
        // 调用核心业务类接收消息、处理消息  
        String respMessage = CoreService.processRequest(request);  
          
        // 响应消息  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();  
        
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the POST method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		//读取文件中的相关数据
		String appid= prop.getProperty("appid");//微信公众号开发者id
		String secret=prop.getProperty("secret");//微信公众号开发秘钥
		
		
		log("init() >>>>>>>>>>>> appid:"+appid+"  secret:"+secret);
		paraMap=new HashMap<String, Object>();
		String access_token=CommonUtils.getToken().getAccessToken();
		String token_time=DateUtils.formatStandardDateTime(new Date());
		
		paraMap.put("access_token", access_token);
		paraMap.put("token_time", token_time);
      
	}


	public static Map<String, Object> getParaMap() {
		return paraMap;
	}

	public static void setParaMap(Map<String, Object> paraMap) {
		CoreServlet.paraMap = paraMap;
	}
}
