package com.ucap.cloud_web.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 描述：发送短信
 * 
 * 包：com.ucap.cloud_web.util 
 * 文件名称：YunpianSmsUtil 
 * 公司名称：开普互联 作者：lixuxiang
 * 时间：2015-12-7下午03:12:23
 * 
 * @version V1.0
 */
public class YunpianSmsUtil {

	// 编码格式。发送编码格式统一用UTF-8
	private static String ENCODING = "UTF-8";
	// 模板发送接口的http地址
	private static String URI_TPL_SEND_SMS = "http://yunpian.com/v1/sms/tpl_send.json";
	private static String APIKEY = "e67b0653d337560cdd4ad0802bfdc0e0";

	/**
	 * 通过模板发送短信
	 * 
	 * 监测报告通知模板：
	 * 组织单位：(tpl_id=1148989,tpl_value="#number#=2&#total#=10&#email#=http://www.ucap.com.cn/")
	 * 模板样式：【开普云监测】第#number#期网站群云监测报告共#total#份已生成并发送到您的邮箱（#email#），请及时查收。
	 * 填报单位：(tpl_id=1149089,tpl_value="#number#=2&#email#=http://www.ucap.com.cn/")
	 * 模板样式：【开普云监测】第#numbere#期网站云监测报告已生成并发送到您的邮箱（#email#），请及时查收。
	 * 
	 * 预警信息通知模板:
	 * 组织单位：(tpl_id=1149127,tpl_value="#datetime#=2015-10-9 10:43:16&#name#=广州市人民政府网站&#sitecode#=BM54150010")
	 * 模板样式：【开普云检测】2015-10-9 10:43:16，广州市人民政府网站（BM54150010）首页打不开，请及时处理。
	 * 填报单位:(tpl_id=1149131,tpl_value="#datetime#=2015-10-9 10:43:16")
	 * 模板样式：【开普云检测】2015-10-9 10:43:16，首页打不开，请及时处理。
	 * 
	 * @param tpl_id  模板id
	 * @param tpl_value 模板变量值
	 * @param mobile 接受的手机号(可多个，中间以“,”分割)
	 * @return json格式字符串 
	 * @throws IOException 
	 */
	public static String tplSendSms(String tpl_id, String tpl_value, String mobile) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("apikey", APIKEY);
		params.put("tpl_id", tpl_id);
		params.put("tpl_value", tpl_value);
		params.put("mobile", mobile);
		return post(URI_TPL_SEND_SMS, params);
	}

	/**
	 * 基于HttpClient 4.3的通用POST方法
	 * 
	 * @param url
	 *            提交的URL
	 * @param paramsMap
	 *            提交<参数，值>Map
	 * @return 提交响应
	 */

	public static String post(String url, Map<String, String> paramsMap) {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost method = new HttpPost(url);
			if (paramsMap != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> param : paramsMap.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return responseText;
	}
}
