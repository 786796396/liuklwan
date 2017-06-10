package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DicServiceUnuserful;import com.ucap.cloud_web.service.IDicServiceUnuserfulService;import com.ucap.cloud_web.dao.DicServiceUnuserfulDao;import org.springframework.stereotype.Service;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DicServiceUnuserfulRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:04:13 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class DicServiceUnuserfulServiceImpl implements IDicServiceUnuserfulService {


	@Autowired	private DicServiceUnuserfulDao dicServiceUnuserfulDao;	@Override	public void add(DicServiceUnuserful dicServiceUnuserful){		dicServiceUnuserfulDao.add(dicServiceUnuserful);	}
	@Override	public DicServiceUnuserful get(Integer id){		return dicServiceUnuserfulDao.get(id);	}
	@Override	public void update(DicServiceUnuserful dicServiceUnuserful){		dicServiceUnuserfulDao.update(dicServiceUnuserful);	}
	@Override	public void delete(Integer id){		dicServiceUnuserfulDao.delete(id);	}
	@Override	public List<DicServiceUnuserful> queryList(DicServiceUnuserfulRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DicServiceUnuserful> list = dicServiceUnuserfulDao.query(param);		return list;	}
}

