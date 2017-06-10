package com.ucap.cloud_web.service.impl;


import java.util.List;
import com.ucap.cloud_web.service.IDicItemService;
import com.ucap.cloud_web.dto.DicItemRequest;

import org.springframework.beans.factory.annotation.Autowired;


/**


	@Autowired







	@Override
	public List<DicItem> queryImgSize(DicItemRequest dicrequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(dicrequest);
		List<DicItem> list = dicItemDao.queryImgSize(param);
		return list;
	}
	@Override
	public List<DicItem> queryByPEnName(String pEnName){
		if(StringUtils.isEmpty(pEnName)){
			return null;
		}
		return dicItemDao.queryByPEnName(pEnName);
	}

	@Override
	public DicItem getByEnName(String enName) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(enName)){
			return null;
		}
		return dicItemDao.getByEnName(enName);
	}

	@Override
	public List<DicItem> queryByPid(int pId) {
		// TODO Auto-generated method stub
		return dicItemDao.queryByPid(pId);
	}

	@Override
	public int queryCountByEnName(String enName) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(enName)){
			return 0;
		}
		return dicItemDao.queryCountByEnName(enName);
	}
