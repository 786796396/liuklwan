package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.UpdateChannelDetail;import com.ucap.cloud_web.service.IUpdateChannelDetailService;import com.ucap.cloud_web.dao.UpdateChannelDetailDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.UpdateChannelDetailRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class UpdateChannelDetailServiceImpl implements IUpdateChannelDetailService {


	@Autowired	private UpdateChannelDetailDao updateChannelDetailDao;	@Override	public void add(UpdateChannelDetail updateChannelDetail){		updateChannelDetailDao.add(updateChannelDetail);	}
	@Override	public UpdateChannelDetail get(Integer id){		return updateChannelDetailDao.get(id);	}
	@Override	public void update(UpdateChannelDetail updateChannelDetail){		updateChannelDetailDao.update(updateChannelDetail);	}
	@Override	public void delete(Integer id){		updateChannelDetailDao.delete(id);	}
	@Override	public PageVo<UpdateChannelDetail> query(UpdateChannelDetailRequest request) {		List<UpdateChannelDetail> updateChannelDetail = queryList(request);		PageVo<UpdateChannelDetail> pv = new PageVo<UpdateChannelDetail>();
				int count = queryCount(request);		pv.setData(updateChannelDetail);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(UpdateChannelDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("types", request.getTypes());
		param.put("urlArray", request.getUrlArray());		return updateChannelDetailDao.queryCount(param);	}
	@Override	public List<UpdateChannelDetail> queryList(UpdateChannelDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("types", request.getTypes());
		param.put("urlArray", request.getUrlArray());		List<UpdateChannelDetail> list = updateChannelDetailDao.query(param);		return list;	}
}

