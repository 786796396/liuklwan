package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.YunOpenState;import com.ucap.cloud_web.service.IYunOpenStateService;import com.ucap.cloud_web.dao.YunOpenStateDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.YunOpenStateRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liukl<br>* <b>日期：</b> 2017-03-28 17:41:00 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class YunOpenStateServiceImpl implements IYunOpenStateService {


	@Autowired	private YunOpenStateDao yunOpenStateDao;	@Override	public int add(YunOpenState yunOpenState){		return yunOpenStateDao.add(yunOpenState);	}
	@Override	public YunOpenState get(Integer id){		return yunOpenStateDao.get(id);	}
	@Override	public void update(YunOpenState yunOpenState){		yunOpenStateDao.update(yunOpenState);	}
	@Override	public void delete(Integer id){		yunOpenStateDao.delete(id);	}
	@Override	public PageVo<YunOpenState> query(YunOpenStateRequest request) {		List<YunOpenState> yunOpenState = queryList(request);		PageVo<YunOpenState> pv = new PageVo<YunOpenState>();		int count = queryCount(request);		pv.setData(yunOpenState);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(YunOpenStateRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return yunOpenStateDao.queryCount(param);	}
	@Override	public List<YunOpenState> queryList(YunOpenStateRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<YunOpenState> list = yunOpenStateDao.query(param);		return list;	}
}

