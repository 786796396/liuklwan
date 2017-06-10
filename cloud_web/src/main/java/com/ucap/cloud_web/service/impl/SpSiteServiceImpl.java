package com.ucap.cloud_web.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.SpSiteDao;
import com.ucap.cloud_web.dto.SpSiteRequest;
import com.ucap.cloud_web.entity.SpSite;
import com.ucap.cloud_web.service.ISpSiteService;


/**


	@Autowired







	@Override
	public Map<String, Object> domainName(String uri) {
		String url = uri.replaceAll("([a-z])((:\\d+)?/.*?$)", "$1");
		Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");  
		Matcher m = p.matcher(url);  
		String siteCode = "";
		String displayModule = ""; // 显示模块（1:日常检测,2:网站概况,3:栏目,4:大数据,5:政府网站基础信息数据库,6:政府网站网民找错数据）
		String loginConfig = null;
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if(m.find()){  
				String ur = m.group();
				String ul = ur.substring(0,ur.indexOf("."));
				SpSiteRequest req = new SpSiteRequest();
				req.setDomain(ul);  // 域名
				req.setStatus(1);   // 状态（0：未开通，1：开通，2：停用）
				req.setDelFlag(0);  // 0：正常，1：删除
				req.setNowDate(DateUtils.formatStandardDate(new Date())); // 当前时间
				List<SpSite> spList = queryList(req);
				if(spList != null && spList.size() > 0){
					SpSite sp = spList.get(0);
					siteCode = sp.getSiteCode();
					displayModule = sp.getDisplayModule();
					loginConfig = sp.getLoginConfig().toString();
				}
				map.put("ul", ul);
				map.put("url", url);
				map.put("siteCode", siteCode);
				map.put("displayModule", displayModule);
				map.put("loginConfig", loginConfig);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			return map;
		}
	}

	@Override
	public List<SpSite> getSpSiteList(HashMap<String, Object> spMap) {
		return spSiteDao.getSpSiteList(spMap);
	}

	

