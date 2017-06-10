package com.ucap.cloud_web.util;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>描述: 签名工具类</p>
 * <p>@Package：com.ucap.cloud_backweb.utils </p>
 * <p>Title: SignUtils </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：masl@ucap.com.cn </p>
 * <p>@date：2016年11月24日上午11:42:47 </p>
 * <p>@version V1.0</p>
 */
public class SignUtils {

	public static void main(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		//token|action|mod|timestamp|
		//action=login&mod=site&password=014ece3fa40fa6ca5246f1a6f22db328&timestamp=1479977355&token=77F8376614F7E69CE00393EBDE2F7E80&username=guoshui001
		params.put("token", "77F8376614F7E69CE00393EBDE2F7E80");
		params.put("action", "login");
		params.put("mod", "site");
		params.put("timestamp", "1479977355");
		params.put("password", "014ece3fa40fa6ca5246f1a6f22db328");
		params.put("username", "guoshui001");

		//我的规则是这样的将所有参数（去除 sign 后）进行排序并拼接参数（如：name=212&age=4）后再经 md5加密得到

		String str = createSign(params, false);
		System.out.println(str);

		//str = createSign(params, true);
		//System.out.println(str);
	}

	/**
	 * @描述:生成签名
	 * @作者:masl@ucap.com.cn
	 * @时间:2016年11月24日下午1:24:35 
	 * @param token
	 * @param action
	 * @param mod
	 * @param timestamp
	 * @return 
	 * @return String
	 */
	/*public static String createSign(String token, String action, String mod, long timestamp) {
		if(StringUtils.isEmpty(token)||StringUtils.isEmpty(action)
				||StringUtils.isEmpty(mod)
				||timestamp == 0)
		{
			return "";
		}
		try {
			Object[] vals = 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}*/

	/**
	 * @描述:生成签名（规则:将所有参数（去除 sign 后）进行排序并拼接参数（如：name=212&age=4）后再经 md5加密得到）
	 * @作者:masl@ucap.com.cn
	 * @时间:2016年11月24日上午11:32:25 
	 * @param params
	 * @param encode
	 * @return String(0:生成错误，1：参数缺失)
	 */
	public static String createSign(Map<String, String> params, boolean encode) {
		try {
			Set<String> keysSet = params.keySet();
			Object[] keys = keysSet.toArray();
			Arrays.sort(keys);
			StringBuffer temp = new StringBuffer();
			boolean first = true;

			//--------参数验证 start--------
			String paramVar = "";//"token|action|mod|timestamp|";
			for (Object key : keys) {
				paramVar += key.toString() + "|";
			}
			if (!(paramVar.contains("token|") 
					&& paramVar.contains("action|") 
					&& paramVar.contains("mod|") 
					&& paramVar.contains("timestamp|"))) {
				return "1";
			}
			//--------参数验证 end--------

			for (Object key : keys) {
				//System.out.println(key);
				if (first) {
					first = false;
				} else {
					temp.append("&");
				}
				temp.append(key).append("=");
				Object value = params.get(key);
				String valueString = "";
				if (null != value) {
					valueString = String.valueOf(value);
				}
				if (encode) {
					temp.append(URLEncoder.encode(valueString, "UTF-8"));
				} else {
					temp.append(valueString);
				}
			}
			System.out.println(temp.toString());
//			String s="action=insert&faccounts=753964&fpassword=e10adc3949ba59abbe56e057f20f883e&mod=organization&organization=新测试&timestamp=1487644772810&token=77F8376614F7E69CE00393EBDE2F7E80";
//			String md5str=MD5Utils.md5(s).toUpperCase();
//			System.out.println(md5str);
			return MD5Utils.md5(temp.toString()).toUpperCase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "0";
	}
}
