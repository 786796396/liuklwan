package com.ucap.cloud_web.action;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.StringUtils;
/***
 * 描述：填报单位访问量 
 * 包：com.ucap.cloud_web.action
 * 文件名称：JcVisitSitecodeAction
 * 公司名称：开普互联
 * 作者：shaoxl@ucap.com.cn
 * 时间：2016年12月14日上午10:08:30 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class JcVisitSitecodeAction extends BaseAction {
	
	/***
	 * 描述：初始化值 
	 * 作者：shaoxl@ucap.com.cn
	 * 时间：2016年12月14日上午10:08:30 
	 * @version V1.0
	 */
	public String index(){
		// siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		if (StringUtils.isNotEmpty(siteCode)) {
			setCurrentShiroUser(siteCode);
		}
		//从session中获取10位填报单位网站标识码
		String	siteCodeTwo = getCurrentUserInfo().getChildSiteCode();
		request.setAttribute("siteCode", siteCodeTwo);
		return "success";
	}	

}
