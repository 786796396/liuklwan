package com.ucap.cloud_web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.dto.xstream.Root;
import com.ucap.cloud_web.dto.xstream.XstreamXmlParseBeanUtil;
import com.ucap.cloud_web.service.IVerifyUrlLinkService;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:32 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */

@SuppressWarnings("deprecation")
@Service
public class VerifyUrlLinkServiceImpl implements IVerifyUrlLinkService {

	@Autowired
	UrlAdapterVar urlAdapterVar;

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public boolean verifyUrlLink(String url) {
		try {
			Root root = verifyUrlLinkXmlParse(url);

			if (root.getStatus().equals("1")) {
				return true;
			}

		} catch (Exception e) {
			return false;
		}

		return false;
	}

	private Root verifyUrlLinkXmlParse(String url) throws Exception {
		String httpUrl = urlAdapterVar.getVerifyUrlLink();

		// 使用NameValuePair来保存要传递的Post参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("url", url));

		String resultXml = getResultXml(httpUrl, params);

		logger.info("verifyUrlLinkXmlParse>>>>>resultXml===" + resultXml);

		try {
			return XstreamXmlParseBeanUtil.getRootByXmlStr(resultXml);
			// return null;
		} catch (Exception e) {
			throw new Exception("reqUrl=" + resultXml + ",错误信息="
					+ e.getMessage());
		}
	}

	private String getResultXml(String httpUrl, List<NameValuePair> params)
			throws Exception {

		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params,
				"UTF-8");
		HttpPost httpRequest = new HttpPost(httpUrl);
		httpRequest.setEntity(uefEntity);

		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse httpResponse = httpclient.execute(httpRequest);

		// 请求HttpCLienthttpResponse=httpclient.execute(httpRequest);
		// 请求成功
		String resultXml = "";
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			resultXml = EntityUtils.toString(httpResponse.getEntity());
		}

		// 去掉xml文件的头
		int idx = resultXml.indexOf("?>");
		return resultXml.substring(idx + 2, resultXml.length());

	}
}
