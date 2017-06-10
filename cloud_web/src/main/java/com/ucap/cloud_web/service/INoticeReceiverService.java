package com.ucap.cloud_web.service;


import java.util.List;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**
	
	/**
	 * 查询反馈列表，siteCode,siteName可模糊查询
	 * @param noticeReceiverRequest
	 * @return
	 */
	public List<NoticeReceiver> queryNoticeReceiverList(NoticeReceiverRequest noticeReceiverRequest);
	
	/**
	 * 查询通知+反馈列表，topic 通知主题可模糊查询 ，反馈日期开选择时间段查询
	 * @param noticeReceiverRequest
	 * @return
	 */
	public List<NoticeReceiver> queryNoticeSenderAndReceiver(NoticeReceiverRequest noticeReceiverRequest);

}
