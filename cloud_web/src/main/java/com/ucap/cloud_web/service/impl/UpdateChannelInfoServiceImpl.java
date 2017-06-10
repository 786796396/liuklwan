package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;
import com.ucap.cloud_web.entity.UpdateChannelInfo;import com.ucap.cloud_web.service.IUpdateChannelInfoService;import com.ucap.cloud_web.dao.UpdateChannelInfoDao;
import org.springframework.stereotype.Service;
import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dto.UpdateChannelInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class UpdateChannelInfoServiceImpl implements IUpdateChannelInfoService {


	@Autowired	private UpdateChannelInfoDao updateChannelInfoDao;	@Override	public void add(UpdateChannelInfo updateChannelInfo){		updateChannelInfoDao.add(updateChannelInfo);	}
	@Override	public UpdateChannelInfo get(Integer id){		return updateChannelInfoDao.get(id);	}
	@Override	public void update(UpdateChannelInfo updateChannelInfo){		updateChannelInfoDao.update(updateChannelInfo);	}
	@Override	public void delete(Integer id){		updateChannelInfoDao.delete(id);	}
	@Override	public PageVo<UpdateChannelInfo> query(UpdateChannelInfoRequest request) {		List<UpdateChannelInfo> updateChannelInfo = queryList(request);		PageVo<UpdateChannelInfo> pv = new PageVo<UpdateChannelInfo>();		int count = queryCount(request);		pv.setData(updateChannelInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(UpdateChannelInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return updateChannelInfoDao.queryCount(param);	}
	@Override	public List<UpdateChannelInfo> queryList(UpdateChannelInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("start", null);
		param.put("pageSize", null);		List<UpdateChannelInfo> list = updateChannelInfoDao.query(param);		return list;	}

	@Override
	public List<SecurityBlankInfoRequest> queryPonitNum(Map<String, Object> map) {
		return updateChannelInfoDao.queryPonitNum(map);
	}

	@Override
	public List<SecurityBlankInfoRequest> queryChannelNum(
			Map<String, Object> map) {
		return updateChannelInfoDao.queryChannelNum(map);
	}
	@Override
	public List<UpdateChannelInfoRequest> queryByGroup(
			Map<String, Object> paramMap) {
		List<UpdateChannelInfoRequest> list = updateChannelInfoDao.queryByGroup(paramMap);
		return list;
	}

	@Override
	public List<UpdateChannelInfoRequest> getChannelNum(Map<String, Object> map) {
		return updateChannelInfoDao.getChannelNum(map);
	}

	@Override
	public List<UpdateChannelInfo> querySumUpdateNum(Map<String, Object> paramMap) {
		return updateChannelInfoDao.querySumUpdateNum(paramMap);
	}

	@Override
	public List<UpdateChannelInfoRequest> queryListSum(
			Map<String, Object> paramMap) {
		return updateChannelInfoDao.queryListSum(paramMap);
	}
}

