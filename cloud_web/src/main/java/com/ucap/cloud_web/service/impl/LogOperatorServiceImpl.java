package com.ucap.cloud_web.service.impl;

import java.util.List;import java.util.Map;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dao.LogOperatorDao;
import com.ucap.cloud_web.dto.LogOperatorRequest;
import com.ucap.cloud_web.entity.LogOperator;
import com.ucap.cloud_web.service.ILogOperatorService;
import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2016-09-29 15:30:25 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class LogOperatorServiceImpl implements ILogOperatorService {


	@Autowired	private LogOperatorDao logOperatorDao;	@Override	public int add(LogOperator logOperator){		return logOperatorDao.add(logOperator);	}
	@Override	public LogOperator get(Integer id){		return logOperatorDao.get(id);	}
	@Override	public void update(LogOperator logOperator){		logOperatorDao.update(logOperator);	}
	@Override	public void delete(Integer id){		logOperatorDao.delete(id);	}
		/**
	 * @Description: 操作日志表：分页
	 * @author: 
	 */
	public PageVo<LogOperator> query(LogOperatorRequest request) {		List<LogOperator> logOperator = queryList(request);		PageVo<LogOperator> pv = new PageVo<LogOperator>();		int count = queryCount(request);		pv.setData(logOperator);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	/**
	 * @Description: 操作日志表：获取总行数
	 * @author: 
	 */	public int queryCount(LogOperatorRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return logOperatorDao.queryCount(param);	}
	/**
	 * @Description: 操作日志表：获取对象
	 * @author: 
	 */	public List<LogOperator> queryList(LogOperatorRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<LogOperator> list = logOperatorDao.query(param);		return list;	}
}

