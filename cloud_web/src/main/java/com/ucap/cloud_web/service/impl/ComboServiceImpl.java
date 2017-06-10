package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.ComboDao;
import com.ucap.cloud_web.dto.ComboRequest;
import com.ucap.cloud_web.entity.Combo;
import com.ucap.cloud_web.service.IComboService;


/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class ComboServiceImpl implements IComboService {


	@Autowired	private ComboDao comboDao;	@Override	public void add(Combo combo){		comboDao.add(combo);	}
	@Override	public Combo get(Integer id){		return comboDao.get(id);	}
	@Override	public void update(Combo combo){		comboDao.update(combo);	}
	@Override	public void delete(Integer id){		comboDao.delete(id);	}

	@Override	public List<Combo> queryList(ComboRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<Combo> list = comboDao.query(param);		return list;	}

	@Override
	public int queryCount(ComboRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return comboDao.queryCount(param);
	}
}

