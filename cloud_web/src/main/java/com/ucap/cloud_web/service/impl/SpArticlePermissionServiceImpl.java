package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SpArticlePermission;import com.ucap.cloud_web.service.ISpArticlePermissionService;import com.ucap.cloud_web.dao.SpArticlePermissionDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SpArticlePermissionRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:32:48 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SpArticlePermissionServiceImpl implements ISpArticlePermissionService {


	@Autowired	private SpArticlePermissionDao spArticlePermissionDao;	@Override	public int add(SpArticlePermission spArticlePermission){		return spArticlePermissionDao.add(spArticlePermission);	}
	@Override	public SpArticlePermission get(Integer id){		return spArticlePermissionDao.get(id);	}
	@Override	public void update(SpArticlePermission spArticlePermission){		spArticlePermissionDao.update(spArticlePermission);	}
	@Override	public void delete(Integer id){		spArticlePermissionDao.delete(id);	}
	@Override	public PageVo<SpArticlePermission> query(SpArticlePermissionRequest request) {		List<SpArticlePermission> spArticlePermission = queryList(request);		PageVo<SpArticlePermission> pv = new PageVo<SpArticlePermission>();		int count = queryCount(request);		pv.setData(spArticlePermission);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SpArticlePermissionRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return spArticlePermissionDao.queryCount(param);	}
	@Override	public List<SpArticlePermission> queryList(SpArticlePermissionRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SpArticlePermission> list = spArticlePermissionDao.query(param);		return list;	}
}

