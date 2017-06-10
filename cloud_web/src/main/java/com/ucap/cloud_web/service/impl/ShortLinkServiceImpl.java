package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.ShortLink;import com.ucap.cloud_web.service.IShortLinkService;import com.ucap.cloud_web.dao.ShortLinkDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.ShortLinkRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-16 11:23:43 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class ShortLinkServiceImpl implements IShortLinkService {


	@Autowired	private ShortLinkDao shortLinkDao;	@Override	public int add(ShortLink shortLink){		return shortLinkDao.add(shortLink);	}
	@Override	public ShortLink get(Integer id){		return shortLinkDao.get(id);	}
	@Override	public void update(ShortLink shortLink){		shortLinkDao.update(shortLink);	}
	@Override	public void delete(Integer id){		shortLinkDao.delete(id);	}
	@Override	public PageVo<ShortLink> query(ShortLinkRequest request) {		List<ShortLink> shortLink = queryList(request);		PageVo<ShortLink> pv = new PageVo<ShortLink>();		int count = queryCount(request);		pv.setData(shortLink);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ShortLinkRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return shortLinkDao.queryCount(param);	}
	@Override	public List<ShortLink> queryList(ShortLinkRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ShortLink> list = shortLinkDao.query(param);		return list;	}
}

