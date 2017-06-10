package com.ucap.cloud_web.dao;


import java.util.List;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.NoticeReceiverRequest;
import com.ucap.cloud_web.entity.NoticeReceiver;
/*** <br>* <b>作者：</b>Na.Y<br>* <b>日期：</b> 2016-09-21 13:42:55 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface NoticeReceiverDao extends GenericDao<NoticeReceiver>{
	
	/**
	 * 查询反馈列表，siteCode,siteName可模糊查询，反馈日期开选择时间段查询
	 * @param noticeReceiverRequest
	 * @return
	 */
	public List<NoticeReceiver> queryNoticeReceiverList(NoticeReceiverRequest noticeReceiverRequest);
	
	/**
	 * 查询通知+反馈列表，topic 通知主题可模糊查询 ，反馈日期开选择时间段查询
	 * @param noticeReceiverRequest
	 * @return
	 */
	public List<NoticeReceiver> queryNoticeSenderAndReceiver(NoticeReceiverRequest noticeReceiverRequest);}

