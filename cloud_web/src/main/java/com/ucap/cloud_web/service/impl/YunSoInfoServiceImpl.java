package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dao.YunSoInfoDao;
import com.ucap.cloud_web.dto.YunSoInfoRequest;
import com.ucap.cloud_web.entity.YunSoInfo;
import com.ucap.cloud_web.service.IYunSoInfoService;
import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>wl@ucap.com.cn<br>* <b>日期：</b> 2016-12-05 16:39:56 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class YunSoInfoServiceImpl implements IYunSoInfoService {


	@Autowired	private YunSoInfoDao yunSoInfoDao;	@Override	public int add(YunSoInfo yunSoInfo){		return yunSoInfoDao.add(yunSoInfo);	}
	@Override	public YunSoInfo get(Integer id){		return yunSoInfoDao.get(id);	}
	@Override	public void update(YunSoInfo yunSoInfo){		yunSoInfoDao.update(yunSoInfo);	}
	@Override	public void delete(Integer id){		yunSoInfoDao.delete(id);	}
	@Override	public PageVo<YunSoInfo> query(YunSoInfoRequest request) {		List<YunSoInfo> yunSoInfo = queryList(request);		PageVo<YunSoInfo> pv = new PageVo<YunSoInfo>();		int count = queryCount(request);		pv.setData(yunSoInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(YunSoInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return yunSoInfoDao.queryCount(param);	}
	@Override	public List<YunSoInfo> queryList(YunSoInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<YunSoInfo> list = yunSoInfoDao.query(param);		return list;	}
}

