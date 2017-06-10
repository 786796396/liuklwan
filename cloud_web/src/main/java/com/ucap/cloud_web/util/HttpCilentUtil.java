package com.ucap.cloud_web.util;

import java.io.IOException;
import java.net.URLDecoder;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class HttpCilentUtil {
	
	
	public static JSONObject doPost(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);
		JSONObject jsonResult = null;
		try {
			// post请求返回结果
			try {
				HttpResponse result = httpClient.execute(method);
				url = URLDecoder.decode(url, "UTF-8");
				/** 请求发送成功，并得到响应 **/
				if (result.getStatusLine().getStatusCode() == 200) {
					String str = "";
					try {
						/** 读取服务器返回过来的json字符串数据 **/
						str = EntityUtils.toString(result.getEntity());

						jsonResult = JSONObject.fromObject(str);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}
	
	public static String getHttpXml(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost method = new HttpPost(url);
		JSONObject jsonResult = null;
		String str = "";
		try {
			// post请求返回结果
			try {
				HttpResponse result = httpClient.execute(method);
				url = URLDecoder.decode(url, "UTF-8");
				/** 请求发送成功，并得到响应 **/
				if (result.getStatusLine().getStatusCode() == 200) {
					
					try {
						/** 读取服务器返回过来的json字符串数据 **/
						str = EntityUtils.toString(result.getEntity());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}
}
