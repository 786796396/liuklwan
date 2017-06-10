package com.ucap.cloud_web.service.impl;


import java.util.List;
import com.ucap.cloud_web.entity.UpdateChannelInfo;
import org.springframework.stereotype.Service;
import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.UpdateChannelInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;


/**


	@Autowired






		param.put("start", null);
		param.put("pageSize", null);

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
