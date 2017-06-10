package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SysAttach;import com.ucap.cloud_web.service.ISysAttachService;import com.ucap.cloud_web.dao.SysAttachDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SysAttachRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:24 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SysAttachServiceImpl implements ISysAttachService {


	@Autowired	private SysAttachDao sysAttachDao;	@Override	public void add(SysAttach sysAttach){		sysAttachDao.add(sysAttach);	}
	@Override	public SysAttach get(Integer id){		return sysAttachDao.get(id);	}
	@Override	public void update(SysAttach sysAttach){		sysAttachDao.update(sysAttach);	}
	@Override	public void delete(Integer id){		sysAttachDao.delete(id);	}
	@Override	public PageVo<SysAttach> query(SysAttachRequest request) {		List<SysAttach> sysAttach = queryList(request);		PageVo<SysAttach> pv = new PageVo<SysAttach>();		int count = queryCount(request);		pv.setData(sysAttach);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SysAttachRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return sysAttachDao.queryCount(param);	}
	@Override	public List<SysAttach> queryList(SysAttachRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SysAttach> list = sysAttachDao.query(param);		return list;	}

	@Override
	public void deleteByTbNameAndId(SysAttachRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		sysAttachDao.deleteByTbNameAndId(param);
		
	}
}

