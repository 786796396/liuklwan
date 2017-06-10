package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DicInteractProbblem;import com.ucap.cloud_web.service.IDicInteractProbblemService;import com.ucap.cloud_web.dao.DicInteractProbblemDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DicInteractProbblemRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:03:47 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class DicInteractProbblemServiceImpl implements IDicInteractProbblemService {


	@Autowired	private DicInteractProbblemDao dicInteractProbblemDao;	@Override	public void add(DicInteractProbblem dicInteractProbblem){		dicInteractProbblemDao.add(dicInteractProbblem);	}
	@Override	public DicInteractProbblem get(Integer id){		return dicInteractProbblemDao.get(id);	}
	@Override	public void update(DicInteractProbblem dicInteractProbblem){		dicInteractProbblemDao.update(dicInteractProbblem);	}
	@Override	public void delete(Integer id){		dicInteractProbblemDao.delete(id);	}
	@Override	public PageVo<DicInteractProbblem> query(DicInteractProbblemRequest request) {		List<DicInteractProbblem> dicInteractProbblem = queryList(request);		PageVo<DicInteractProbblem> pv = new PageVo<DicInteractProbblem>();		int count = queryCount(request);		pv.setData(dicInteractProbblem);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(DicInteractProbblemRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return dicInteractProbblemDao.queryCount(param);	}
	@Override	public List<DicInteractProbblem> queryList(DicInteractProbblemRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DicInteractProbblem> list = dicInteractProbblemDao.query(param);		return list;	}
}

