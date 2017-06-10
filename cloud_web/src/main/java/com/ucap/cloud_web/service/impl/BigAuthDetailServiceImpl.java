package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.BigAuthDetail;import com.ucap.cloud_web.service.IBigAuthDetailService;import com.ucap.cloud_web.dao.BigAuthDetailDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.BigAuthDetailRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>sjy<br>* <b>日期：</b> 2016-06-28 11:44:53 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class BigAuthDetailServiceImpl implements IBigAuthDetailService {


	@Autowired	private BigAuthDetailDao bigAuthDetailDao;	@Override	public void add(BigAuthDetail bigAuthDetail){		bigAuthDetailDao.add(bigAuthDetail);	}
	@Override	public BigAuthDetail get(Integer id){		return bigAuthDetailDao.get(id);	}
	@Override	public void update(BigAuthDetail bigAuthDetail){		bigAuthDetailDao.update(bigAuthDetail);	}
	@Override	public void delete(Integer id){		bigAuthDetailDao.delete(id);	}
	@Override	public PageVo<BigAuthDetail> query(BigAuthDetailRequest request) {		List<BigAuthDetail> bigAuthDetail = queryList(request);		PageVo<BigAuthDetail> pv = new PageVo<BigAuthDetail>();		int count = queryCount(request);		pv.setData(bigAuthDetail);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(BigAuthDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return bigAuthDetailDao.queryCount(param);	}
	@Override	public List<BigAuthDetail> queryList(BigAuthDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<BigAuthDetail> list = bigAuthDetailDao.query(param);		return list;	}

	

	
}

