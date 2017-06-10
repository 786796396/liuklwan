package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DicInteractProblem;import com.ucap.cloud_web.service.IDicInteractProblemService;import com.ucap.cloud_web.dao.DicInteractProblemDao;import org.springframework.stereotype.Service;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DicInteractProblemRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-30 14:55:01 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class DicInteractProblemServiceImpl implements IDicInteractProblemService {


	@Autowired	private DicInteractProblemDao dicInteractProblemDao;	@Override	public void add(DicInteractProblem dicInteractProblem){		dicInteractProblemDao.add(dicInteractProblem);	}
	@Override	public DicInteractProblem get(Integer id){		return dicInteractProblemDao.get(id);	}
	@Override	public void update(DicInteractProblem dicInteractProblem){		dicInteractProblemDao.update(dicInteractProblem);	}
	@Override	public void delete(Integer id){		dicInteractProblemDao.delete(id);	}	@Override	public List<DicInteractProblem> queryList(DicInteractProblemRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DicInteractProblem> list = dicInteractProblemDao.query(param);		return list;	}
}

