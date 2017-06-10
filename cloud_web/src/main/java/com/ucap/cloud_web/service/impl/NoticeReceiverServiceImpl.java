package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired







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
	}
