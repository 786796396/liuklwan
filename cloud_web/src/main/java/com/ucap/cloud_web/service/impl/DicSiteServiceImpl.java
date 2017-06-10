package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DicSite;import com.ucap.cloud_web.service.IDicSiteService;import com.ucap.cloud_web.dao.DicSiteDao;import org.springframework.stereotype.Service;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DicSiteRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-30 15:52:30 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class DicSiteServiceImpl implements IDicSiteService {


	@Autowired	private DicSiteDao dicSiteDao;	@Override	public void add(DicSite dicSite){		dicSiteDao.add(dicSite);	}
	@Override	public DicSite get(Integer id){		return dicSiteDao.get(id);	}
	@Override	public void update(DicSite dicSite){		dicSiteDao.update(dicSite);	}
	@Override	public void delete(Integer id){		dicSiteDao.delete(id);	}	@Override	public List<DicSite> queryList(DicSiteRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DicSite> list = dicSiteDao.query(param);		return list;	}
}

