package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.NoticeSender;import com.ucap.cloud_web.service.INoticeSenderService;import com.ucap.cloud_web.dao.NoticeSenderDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.NoticeSenderRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>Na.Y<br>* <b>日期：</b> 2016-09-21 13:43:19 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class NoticeSenderServiceImpl implements INoticeSenderService {


	@Autowired	private NoticeSenderDao noticeSenderDao;	@Override	public int add(NoticeSender noticeSender){		return noticeSenderDao.add(noticeSender);	}
	@Override	public NoticeSender get(Integer id){		return noticeSenderDao.get(id);	}
	@Override	public void update(NoticeSender noticeSender){		noticeSenderDao.update(noticeSender);	}
	@Override	public void delete(Integer id){		noticeSenderDao.delete(id);	}
	@Override	public PageVo<NoticeSender> query(NoticeSenderRequest request) {		List<NoticeSender> noticeSender = queryList(request);		PageVo<NoticeSender> pv = new PageVo<NoticeSender>();		int count = queryCount(request);		pv.setData(noticeSender);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(NoticeSenderRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return noticeSenderDao.queryCount(param);	}
	@Override	public List<NoticeSender> queryList(NoticeSenderRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<NoticeSender> list = noticeSenderDao.query(param);		return list;	}
}

