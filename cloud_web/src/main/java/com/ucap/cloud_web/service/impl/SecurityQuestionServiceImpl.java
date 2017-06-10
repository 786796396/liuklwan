package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SecurityQuestion;import com.ucap.cloud_web.service.ISecurityQuestionService;import com.ucap.cloud_web.dao.SecurityQuestionDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SecurityQuestionRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-09-02 09:18:15 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SecurityQuestionServiceImpl implements ISecurityQuestionService {


	@Autowired	private SecurityQuestionDao securityQuestionDao;	@Override	public int add(SecurityQuestion securityQuestion){		return securityQuestionDao.add(securityQuestion);	}
	@Override	public SecurityQuestion get(Integer id){		return securityQuestionDao.get(id);	}
	@Override	public void update(SecurityQuestion securityQuestion){		securityQuestionDao.update(securityQuestion);	}
	@Override	public void delete(Integer id){		securityQuestionDao.delete(id);	}
	@Override	public PageVo<SecurityQuestion> query(SecurityQuestionRequest request) {		List<SecurityQuestion> securityQuestion = queryList(request);		PageVo<SecurityQuestion> pv = new PageVo<SecurityQuestion>();		int count = queryCount(request);		pv.setData(securityQuestion);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityQuestionRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return securityQuestionDao.queryCount(param);	}
	@Override	public List<SecurityQuestion> queryList(SecurityQuestionRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SecurityQuestion> list = securityQuestionDao.query(param);		return list;	}
}

