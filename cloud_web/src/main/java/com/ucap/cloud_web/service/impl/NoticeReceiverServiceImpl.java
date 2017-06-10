package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.NoticeReceiver;import com.ucap.cloud_web.service.INoticeReceiverService;import com.ucap.cloud_web.dao.NoticeReceiverDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.NoticeReceiverRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>Na.Y<br>* <b>日期：</b> 2016-09-21 13:42:55 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class NoticeReceiverServiceImpl implements INoticeReceiverService {


	@Autowired	private NoticeReceiverDao noticeReceiverDao;	@Override	public int add(NoticeReceiver noticeReceiver){		return noticeReceiverDao.add(noticeReceiver);	}
	@Override	public NoticeReceiver get(Integer id){		return noticeReceiverDao.get(id);	}
	@Override	public void update(NoticeReceiver noticeReceiver){		noticeReceiverDao.update(noticeReceiver);	}
	@Override	public void delete(Integer id){		noticeReceiverDao.delete(id);	}
	@Override	public PageVo<NoticeReceiver> query(NoticeReceiverRequest request) {		List<NoticeReceiver> noticeReceiver = queryList(request);		PageVo<NoticeReceiver> pv = new PageVo<NoticeReceiver>();		int count = queryCount(request);		pv.setData(noticeReceiver);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(NoticeReceiverRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return noticeReceiverDao.queryCount(param);	}
	@Override	public List<NoticeReceiver> queryList(NoticeReceiverRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<NoticeReceiver> list = noticeReceiverDao.query(param);		return list;	}

	@Override
	public List<NoticeReceiver> queryNoticeReceiverList(
			NoticeReceiverRequest noticeReceiverRequest) {
//		Map<String, Object> param = QueryUtils.getQueryMap(noticeReceiverRequest);
		List<NoticeReceiver> list = noticeReceiverDao.queryNoticeReceiverList(noticeReceiverRequest);
		return list;
	}

	@Override
	public List<NoticeReceiver> queryNoticeSenderAndReceiver(
			NoticeReceiverRequest noticeReceiverRequest) {
//		Map<String, Object> param = QueryUtils.getQueryMap(noticeReceiverRequest);
		List<NoticeReceiver> list = noticeReceiverDao.queryNoticeSenderAndReceiver(noticeReceiverRequest);
		return list;
	}}

