package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DicFatalError;import com.ucap.cloud_web.service.IDicFatalErrorService;import com.ucap.cloud_web.dao.DicFatalErrorDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DicFatalErrorRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2017-05-19 09:38:52 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class DicFatalErrorServiceImpl implements IDicFatalErrorService {


	@Autowired	private DicFatalErrorDao dicFatalErrorDao;	@Override	public int add(DicFatalError dicFatalError){		return dicFatalErrorDao.add(dicFatalError);	}
	@Override	public DicFatalError get(Integer id){		return dicFatalErrorDao.get(id);	}
	@Override	public void update(DicFatalError dicFatalError){		dicFatalErrorDao.update(dicFatalError);	}
	@Override	public void delete(Integer id){		dicFatalErrorDao.delete(id);	}
	@Override	public PageVo<DicFatalError> query(DicFatalErrorRequest request) {		List<DicFatalError> dicFatalError = queryList(request);		PageVo<DicFatalError> pv = new PageVo<DicFatalError>();		int count = queryCount(request);		pv.setData(dicFatalError);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(DicFatalErrorRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return dicFatalErrorDao.queryCount(param);	}
	@Override	public List<DicFatalError> queryList(DicFatalErrorRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DicFatalError> list = dicFatalErrorDao.query(param);		return list;	}
}

