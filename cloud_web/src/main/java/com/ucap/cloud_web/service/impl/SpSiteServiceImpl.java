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


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:30:41 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SpSiteServiceImpl implements ISpSiteService {


	@Autowired	private SpSiteDao spSiteDao;	@Override	public int add(SpSite spSite){		return spSiteDao.add(spSite);	}
	@Override	public SpSite get(Integer id){		return spSiteDao.get(id);	}
	@Override	public void update(SpSite spSite){		spSiteDao.update(spSite);	}
	@Override	public void delete(Integer id){		spSiteDao.delete(id);	}
	@Override	public PageVo<SpSite> query(SpSiteRequest request) {		List<SpSite> spSite = queryList(request);		PageVo<SpSite> pv = new PageVo<SpSite>();		int count = queryCount(request);		pv.setData(spSite);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SpSiteRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return spSiteDao.queryCount(param);	}
	@Override	public List<SpSite> queryList(SpSiteRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SpSite> list = spSiteDao.query(param);		return list;	}

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

	
}

