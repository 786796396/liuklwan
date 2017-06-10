package com.ucap.cloud_web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.DesTwo;
import com.ucap.cloud_web.util.HttpClientUtils;
import com.ucap.cloud_web.util.SignUtils;

/**
 * <p>描述:易分析接口测试 </p>
 * <p>@Package：com.ucap.cloud_backweb </p>
 * <p>Title: YfxApiTest </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：liujc@ucap.com.cn </p>
 * <p>@date：2016年11月24日上午11:50:35 </p>
 * <p>@version V1.0</p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class YfxApiTestAction extends BaseAction {
	public final String TOKEN = "77F8376614F7E69CE00393EBDE2F7E80";
	public final String YFX_API_URL = "http://120.27.198.189/web/api/kpy_api.php";
	public final String expirydate="1519660800000";//2018-02-27
	public static void main(String[] args) throws ParseException {
		long ss = System.currentTimeMillis();
//		DateUtils.parseDateTime("2017-02-27");
		SimpleDateFormat sdf  =   new  SimpleDateFormat( "yyyyMMdd" ); 
		Date ddd=sdf.parse("20180227");
		System.out.println(ddd.getTime());
		System.out.println(ss);
		System.out.println(DateUtils.longToDate(ss));
	}

	//获取签名
	public void getSignTest() {
		try {
			Map<String, String> params = getParams();
			OutputJson(getSign(params));
		} catch (Exception e) {
			OutputJson("错误");
		}
	}

	//添加组织单位帐号
	//
	public void addOrgInfoTest() {
		try {
			String url = YFX_API_URL + "?mod=organization&action=insert";
			Map<String, String> params = getParams();
			params.put("mod", "organization");
			params.put("action", "insert");
			//这个是合同的截止时间  现在测试随便写的时间  毫秒级的
			params.put("expirydate", expirydate);
//			if (params.containsKey("fpassword")) { 
//				String pwd = params.get("fpassword");
//				params.remove("fpassword");
//				params.put("fpassword", MD5Utils.md5(pwd));
//			}
			
			VerJson verJson = verParams(params, "organization|faccounts");

			params.remove("token");
			params.remove("action");
			params.remove("mod");
			
			params.put("sign", verJson.getSign());
			params.put("timestamp", verJson.getTimestamp());
			
			if (!verJson.getStatus()) {
				OutputJson(verJson.getErrMsg());
				return;
			}
			String temp=HttpClientUtils.basicPost(url, params);
//			String temp=HttpClientUtils.basicPost(url, params);
			//JSONObject contractInfoUrls = JSONObject.fromObject(temp);
			System.out.println(temp);
			OutputJson(temp);
		} catch (Exception e) {
			OutputJson("错误");
		}
	}

	//编辑组织单位帐号
	//http://www.yeefx.cn/api/kpy_site.php?mod=organization&action=edit
	public void editOrgInfoTest() {
		try {
			String url = YFX_API_URL + "?mod=organization&action=edit";
			Map<String, String> params = getParams();
			params.put("mod", "organization");
			params.put("action", "edit");

//			if (params.containsKey("fpassword")) {
//				String pwd = params.get("fpassword");
//				params.remove("fpassword");
//				params.put("fpassword", MD5Utils.md5(pwd));
//			}
			//这个是合同的截止时间  现在测试随便写的时间  毫秒级的
			params.put("expirydate", expirydate);
			VerJson verJson = verParams(params, "faccounts");
			params.remove("token");
			params.remove("action");
			params.remove("mod");
			
			params.put("sign", verJson.getSign());
			params.put("timestamp", verJson.getTimestamp());
			
			if (!verJson.getStatus()) {
				OutputJson(verJson.getErrMsg());
				return;
			}

			String temp=HttpClientUtils.basicPost(url, params);
			//JSONObject contractInfoUrls = JSONObject.fromObject(temp);
			OutputJson(temp);
		} catch (Exception e) {
			OutputJson("错误");
		}
	}

	//删除组织单位帐号
	//http://www.yeefx.cn/api/kpy_site.php?mod=organization&action=del
	public void delOrgInfoTest() {
		try {
			String url = YFX_API_URL + "?mod=organization&action=del";
			Map<String, String> params = getParams();
			params.put("mod", "organization");
			params.put("action", "del");

			VerJson verJson = verParams(params, "faccounts");
			params = getParams();
			params.remove("token");
			params.put("sign", verJson.getSign());
			params.put("timestamp", verJson.getTimestamp());
			if (!verJson.getStatus()) {
				OutputJson(verJson.getErrMsg());
				return;
			}

			String temp=HttpClientUtils.basicPost(url, params);
			OutputJson(temp);
		} catch (Exception e) {
			OutputJson("错误");
		}
	}

	//添加一个填报单位以及站点
	//http://www.yeefx.cn/api/kpy_site.php?mod=site&action=insert
	public void addSiteInfoTest() {
		try {
			String url = YFX_API_URL + "?mod=site&action=insert";
			Map<String, String> params = getParams();
			params.put("mod", "site");
			params.put("action", "insert");
			//这个是合同的截止时间  现在测试随便写的时间  毫秒级的
			params.put("expirydate", expirydate);
//			if (params.containsKey("spassword")) {
//				String pwd = params.get("spassword");
//				params.remove("spassword");
//				params.put("spassword", MD5Utils.md5(pwd));
//			}
			VerJson verJson = verParams(params, "sitename|siteurl|faccounts|saccounts");

			params.remove("token");
			params.remove("action");
			params.remove("mod");
			
			params.put("sign", verJson.getSign());
			params.put("timestamp", verJson.getTimestamp());
			
			if (!verJson.getStatus()) {
				OutputJson(verJson.getErrMsg());
				return;
			}
			String temp=HttpClientUtils.basicPost(url, params);
			OutputJson(temp);
		} catch (Exception e) {
			OutputJson("错误");
		}
	}

	//编辑填报单位以及站点
	//http://www.yeefx.cn/api/kpy_site.php?mod=site&action=edit
	public void editSiteInfoTest() {
		try {
			String url = YFX_API_URL + "?mod=site&action=edit";
			Map<String, String> params = getParams();
			params.put("mod", "site");
			params.put("action", "edit");
			//这个是合同的截止时间  现在测试随便写的时间  毫秒级的
			params.put("expirydate", expirydate);
//			if (params.containsKey("spassword")) {
//				String pwd = params.get("spassword");
//				params.remove("spassword");
//				params.put("spassword", MD5Utils.md5(pwd));
//			}
			
			VerJson verJson = verParams(params, "faccounts|saccounts");

			params.remove("token");
			params.remove("action");
			params.remove("mod");
			
			params.put("sign", verJson.getSign());
			params.put("timestamp", verJson.getTimestamp());
		
			if (!verJson.getStatus()) {
				OutputJson(verJson.getErrMsg());
				return;
			}
			
			String temp=HttpClientUtils.basicPost(url, params);
			OutputJson(temp);
		} catch (Exception e) {
			OutputJson("错误");
		}
	}

	//删除填报单位以及站点
	//http://www.yeefx.cn/api/kpy_site.php?mod=site&action=del
	public void delSiteInfoTest() {
		try {
			String url = YFX_API_URL + "?mod=site&action=del";
			Map<String, String> params = getParams();
			params.put("mod", "site");
			params.put("action", "del");

			VerJson verJson = verParams(params, "faccounts|saccounts");
			params = getParams();
			params.remove("token");
			params.put("sign", verJson.getSign());
			params.put("timestamp", verJson.getTimestamp());
			if (!verJson.getStatus()) {
				OutputJson(verJson.getErrMsg());
				return;
			}

			String temp=HttpClientUtils.basicPost(url, params);
			OutputJson(temp);
		} catch (Exception e) {
			OutputJson("错误");
		}
	}

	//为已存在的填报单位添加上级组织单位
	//http://www.yeefx.cn/api/ kpy_api.php?mod=site&action=add
	public void addSitePidTest() {
		try {
			String url = YFX_API_URL + "?mod=site&action=add";
			Map<String, String> params = getParams();
			params.put("mod", "site");
			params.put("action", "add");

			VerJson verJson = verParams(params, "faccounts|saccounts");
			params = getParams();
			params.remove("token");
			params.put("sign", verJson.getSign());
			params.put("timestamp", verJson.getTimestamp());
			if (!verJson.getStatus()) {
				OutputJson(verJson.getErrMsg());
				return;
			}

			String temp=HttpClientUtils.basicPost(url, params);
			OutputJson(temp);
		} catch (Exception e) {
			OutputJson("错误");
		}
	}

	//解除组织单位和填报单位的关系
	//http://www.yeefx.cn/api/ kpy_api.php?mod=site&action=remove
	public void removeSitePidTest() {
		try {
			String url = YFX_API_URL + "?mod=site&action=remove";
			Map<String, String> params = getParams();
			params.put("mod", "site");
			params.put("action", "remove");

			VerJson verJson = verParams(params, "faccounts|saccounts");
			params = getParams();
			params.remove("token");
			params.put("sign", verJson.getSign());
			params.put("timestamp", verJson.getTimestamp());
			if (!verJson.getStatus()) {
				OutputJson(verJson.getErrMsg());
				return;
			}

			String temp=HttpClientUtils.basicPost(url, params);
			OutputJson(temp);
		} catch (Exception e) {
			OutputJson("错误");
		}
	}

	//获取js统计代码
	//http://www.yeefx.cn/api/kpy_site.php?mod=site&action=getcode
	public void getCodeTest() {
		try {
			String url = YFX_API_URL + "?mod=site&action=getcode";
			Map<String, String> params = getParams();
			params.put("mod", "site");
			params.put("action", "getcode");

			VerJson verJson = verParams(params, "saccounts");
			params = getParams();
			params.remove("token");
			params.put("sign", verJson.getSign());
			params.put("timestamp", verJson.getTimestamp());
			if (!verJson.getStatus()) {
				OutputJson(verJson.getErrMsg());
				return;
			}
			
			String temp=HttpClientUtils.basicPost(url, params);
			OutputJson(temp);
		} catch (Exception e) {
			OutputJson("错误");
		}
	}

	//登录易分析平台
	//http://www.yeefx.cn/api/kpy_site.php?mod=site&action=login
	public void loginTest() {
		try {
			String url = YFX_API_URL + "?mod=site&action=login";
			Map<String, String> params = getParams();
			params.put("mod", "site");
			params.put("action", "login");
			//这个是合同的截止时间  现在测试随便写的时间  毫秒级的
			//params.put("expirydate", expirydate);
			if (params.containsKey("password")) {
				String pwd = params.get("password");
				params.remove("password");
				params.put("password", DesTwo.encryptDES(pwd, null));
			}
			if (params.containsKey("username")) {
				String username = params.get("username");
				params.remove("username");
				params.put("username", DesTwo.encryptDES(username, null));
			}
			
			VerJson verJson = verParams(params, "password|username");
			params.remove("token");
			params.remove("action");
			params.remove("mod");
			
			params.put("timestamp", verJson.getTimestamp());
			
			params.put("sign", verJson.getSign());
			if (!verJson.getStatus()) {
				OutputJson(verJson.getErrMsg());
				return;
			}
			String temp=HttpClientUtils.basicPost(url, params);
			OutputJson(temp);
		} catch (Exception e) {
			OutputJson("错误");
		}
	}

	/**
	 * @描述:获取签名
	 * @作者:masl@ucap.com.cn
	 * @时间:2016年11月24日下午1:09:05 
	 * @return 
	 * @return String
	 */
	private String getSign(Map<String, String> params) {
		if (params.containsKey("sign")) {
			params.remove("sign");
		}
		//Enumeration<?> pNames = spotCheckResultRequest.getParameterNames();
		//Map<String, Object> params = new HashMap<String, Object>();

		//Map<String, String> newParams = params;
		long timestamp = new Date().getTime();
		params.put("timestamp", String.valueOf(timestamp));

		String sign = SignUtils.createSign(params, false);
		System.out.println("======签名：" + sign + "=======");

		return sign + "|" + timestamp;
	}

	/**
	 * @描述:验证非空参数 
	 * @作者:masl@ucap.com.cn
	 * @时间:2016年11月24日下午3:24:20 
	 * @param noNullParams:非空参数列表（格式：name|age）
	 * @return 
	 * @return boolean
	 */
	public VerJson verParams(Map<String, String> params, String noNullParams) {
		VerJson json = new VerJson();
		String signs = getSign(params);
		String signStr = signs.split("\\|")[0];
		String timestamp = signs.split("\\|")[1];

		json.setSign(signStr);
		json.setTimestamp(timestamp);

		if (signStr.length() < 2) {
			json.setStatus(false);
			json.setErrMsg("签名错误");
			return json;
		}
		// === 参数非空判断  start===
		if (StringUtils.isNotEmpty(noNullParams)) {
			String mString = "";
			for (String str : noNullParams.split("\\|")) {
				if (!params.containsKey(str)) {
					mString += "缺少参数" + str + ";";
					continue;
				}
				if (StringUtils.isEmpty(params.get(str))) {
					mString += str + "不能为空;";
				}
			}
			if (mString.length() > 0) {
				json.setStatus(false);
				json.setErrMsg(mString);
				return json;
			}
		}
		// === 参数非空判断  end===
		return json;
	}

	/**
	 * @描述:获取请求参数
	 * @作者:masl@ucap.com.cn
	 * @时间:2016年11月24日下午3:12:31 
	 * @return 
	 * @return Map<String,String>
	 */
	private Map<String, String> getParams() {
		Enumeration<?> pNames = request.getParameterNames();
		Map<String, String> params = new HashMap<String, String>();
		while (pNames.hasMoreElements()) {
			String pName = (String) pNames.nextElement();
			if ("sign".equals(pName))
				continue;

			Object pValue = request.getParameter(pName);
			String val = "";
			if (pValue != null) {
				val = pValue.toString().trim();
			}
			params.put(pName, val);
		}
		params.put("token", TOKEN);
		//params.put("timestamp", timestamp);
		return params;
	}
}

class VerJson {
	private String sign;
	private String timestamp;
	private String errMsg;
	private String infoMsg;
	private boolean status = true;//默认为  true

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * @return the infoMsg
	 */
	public String getInfoMsg() {
		return infoMsg;
	}

	/**
	 * @param infoMsg the infoMsg to set
	 */
	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

	/**
	 * @return the status
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
}
