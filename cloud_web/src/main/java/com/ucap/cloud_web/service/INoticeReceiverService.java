package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.NoticeReceiverRequest;import com.ucap.cloud_web.entity.NoticeReceiver;


/*** <br>* <b>作者：</b>Na.Y<br>* <b>日期：</b> 2016-09-21 13:42:55 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface INoticeReceiverService {


	/**	* 添加数据	* @param noticeReceiver			对象			(必填)	*/	public int add(NoticeReceiver noticeReceiver);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return noticeReceiver	*/	public NoticeReceiver get(Integer id);

	/**	* 修改数据	* @param NoticeReceiver			对象			(必填)	*/	public void update(NoticeReceiver noticeReceiver);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<NoticeReceiver>	*/	public PageVo<NoticeReceiver> query(NoticeReceiverRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(NoticeReceiverRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<NoticeReceiver>	*/	public List<NoticeReceiver> queryList(NoticeReceiverRequest request);
	
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

