package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DicIndicator;import com.ucap.cloud_web.service.IDicIndicatorService;import com.ucap.cloud_web.dao.DicIndicatorDao;import org.springframework.stereotype.Service;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DicIndicatorRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:17:59 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class DicIndicatorServiceImpl implements IDicIndicatorService {


	@Autowired	private DicIndicatorDao dicIndicatorDao;	@Override	public void add(DicIndicator dicIndicator){		dicIndicatorDao.add(dicIndicator);	}
	@Override	public DicIndicator get(Integer id){		return dicIndicatorDao.get(id);	}
	@Override	public void update(DicIndicator dicIndicator){		dicIndicatorDao.update(dicIndicator);	}
	@Override	public void delete(Integer id){		dicIndicatorDao.delete(id);	}

	@Override	public List<DicIndicator> queryList(DicIndicatorRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DicIndicator> list = dicIndicatorDao.query(param);		return list;	}
}

