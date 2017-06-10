package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.YunOpenDetail;import com.ucap.cloud_web.service.IYunOpenDetailService;import com.ucap.cloud_web.dao.YunOpenDetailDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.YunOpenDetailRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-10 19:03:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class YunOpenDetailServiceImpl implements IYunOpenDetailService {


	@Autowired	private YunOpenDetailDao yunOpenDetailDao;	@Override	public int add(YunOpenDetail yunOpenDetail){		return yunOpenDetailDao.add(yunOpenDetail);	}
	@Override	public YunOpenDetail get(Integer id){		return yunOpenDetailDao.get(id);	}
	@Override	public void update(YunOpenDetail yunOpenDetail){		yunOpenDetailDao.update(yunOpenDetail);	}
	@Override	public void delete(Integer id){		yunOpenDetailDao.delete(id);	}
	@Override	public PageVo<YunOpenDetail> query(YunOpenDetailRequest request) {		List<YunOpenDetail> yunOpenDetail = queryList(request);		PageVo<YunOpenDetail> pv = new PageVo<YunOpenDetail>();		int count = queryCount(request);		pv.setData(yunOpenDetail);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(YunOpenDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return yunOpenDetailDao.queryCount(param);	}
	@Override	public List<YunOpenDetail> queryList(YunOpenDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<YunOpenDetail> list = yunOpenDetailDao.query(param);		return list;	}
}

