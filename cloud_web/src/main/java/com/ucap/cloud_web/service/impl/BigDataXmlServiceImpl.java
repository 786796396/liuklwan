package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.BigDataXml;import com.ucap.cloud_web.service.IBigDataXmlService;import com.ucap.cloud_web.dao.BigDataXmlDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.BigDataXmlRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-07-21 10:29:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class BigDataXmlServiceImpl implements IBigDataXmlService {


	@Autowired	private BigDataXmlDao bigDataXmlDao;	@Override	public void add(BigDataXml bigDataXml){		bigDataXmlDao.add(bigDataXml);	}
	@Override	public BigDataXml get(Integer id){		return bigDataXmlDao.get(id);	}
	@Override	public void update(BigDataXml bigDataXml){		bigDataXmlDao.update(bigDataXml);	}
	@Override	public void delete(BigDataXmlRequest request){		bigDataXmlDao.delete(request);	}
	@Override	public PageVo<BigDataXml> query(BigDataXmlRequest request) {		List<BigDataXml> bigDataXml = queryList(request);		PageVo<BigDataXml> pv = new PageVo<BigDataXml>();		int count = queryCount(request);		pv.setData(bigDataXml);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(BigDataXmlRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return bigDataXmlDao.queryCount(param);	}
	@Override	public List<BigDataXml> queryList(BigDataXmlRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<BigDataXml> list = bigDataXmlDao.query(param);		return list;	}
}

