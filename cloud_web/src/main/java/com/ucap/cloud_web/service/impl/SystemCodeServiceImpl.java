package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SystemCode;import com.ucap.cloud_web.service.ISystemCodeService;import com.ucap.cloud_web.dao.SystemCodeDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SystemCodeRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class SystemCodeServiceImpl implements ISystemCodeService {


	@Autowired	private SystemCodeDao systemCodeDao;	@Override	public void add(SystemCode systemCode){		systemCodeDao.add(systemCode);	}
	@Override	public SystemCode get(Integer id){		return systemCodeDao.get(id);	}
	@Override	public void update(SystemCode systemCode){		systemCodeDao.update(systemCode);	}
	@Override	public void delete(Integer id){		systemCodeDao.delete(id);	}
	@Override	public PageVo<SystemCode> query(SystemCodeRequest request) {		List<SystemCode> systemCode = queryList(request);		PageVo<SystemCode> pv = new PageVo<SystemCode>();		int count = queryCount(request);		pv.setData(systemCode);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SystemCodeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return systemCodeDao.queryCount(param);	}
	@Override	public List<SystemCode> queryList(SystemCodeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SystemCode> list = systemCodeDao.query(param);		return list;	}
}

