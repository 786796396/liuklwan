package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.YunOpenInfo;import com.ucap.cloud_web.service.IYunOpenInfoService;import com.ucap.cloud_web.dao.YunOpenInfoDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.YunOpenInfoRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-10 19:06:11 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class YunOpenInfoServiceImpl implements IYunOpenInfoService {


	@Autowired	private YunOpenInfoDao yunOpenInfoDao;	@Override	public int add(YunOpenInfo yunOpenInfo){		return yunOpenInfoDao.add(yunOpenInfo);	}
	@Override	public YunOpenInfo get(Integer id){		return yunOpenInfoDao.get(id);	}
	@Override	public void update(YunOpenInfo yunOpenInfo){		yunOpenInfoDao.update(yunOpenInfo);	}
	@Override	public void delete(Integer id){		yunOpenInfoDao.delete(id);	}
	@Override	public PageVo<YunOpenInfo> query(YunOpenInfoRequest request) {		List<YunOpenInfo> yunOpenInfo = queryList(request);		PageVo<YunOpenInfo> pv = new PageVo<YunOpenInfo>();		int count = queryCount(request);		pv.setData(yunOpenInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(YunOpenInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return yunOpenInfoDao.queryCount(param);	}
	@Override	public List<YunOpenInfo> queryList(YunOpenInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<YunOpenInfo> list = yunOpenInfoDao.query(param);		return list;	}
}

