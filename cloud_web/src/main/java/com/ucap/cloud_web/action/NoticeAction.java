package com.ucap.cloud_web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.NoticeReceiverRequest;
import com.ucap.cloud_web.dto.NoticeSenderRequest;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.NoticeReceiver;
import com.ucap.cloud_web.entity.NoticeSender;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.INoticeReceiverService;
import com.ucap.cloud_web.service.INoticeSenderService;
import com.ucap.cloud_web.shiro.ShiroUser;
import com.ucap.cloud_web.util.FileUtils;

/**
 * 描述： 通知action 包：com.ucap.cloud_web.action 文件名称：NoticeReceiverAction 公司名称：开普互联
 * 作者：Na.Y@ucap.com.cn 时间：2016-7-20下午2:58:39
 * 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class NoticeAction extends BaseAction {

	@Autowired
	private INoticeSenderService noticeSenderServiceImpl;

	@Autowired
	private INoticeReceiverService noticeReceiverServiceImpl;

	@Autowired
	private UrlAdapterVar urlAdapterVar;// 配置文件

	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;

	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;


	private String noticeReportFileName;
	private String noticeReport;// 上传报告附件
	private String noticeReportContentType;
	private int contentLengh = 20;

	/**
	 * @Description: 组织单位：获取发送的通知
	 * @author Na.Y --- 2015-11-12下午02:07:36
	 */
	public String indexOrg() {
		return "success";
	}

	/**
	 * @Description: 组织单位：获取发送的通知反馈列表
	 * @author Na.Y --- 2015-11-12下午02:07:36
	 */
	public String indexOrgFeed() {

		request.setAttribute("noticeSenderId",
				request.getParameter("noticeSenderId"));
		return "success";
	}

	/**
	 * @Description: 填报单位：获取填报单位收到的所有通知
	 * @author Na.Y --- 2015-11-12下午02:07:36
	 */
	public String indexReceiver() {

		return "success";
	}

	/**
	 * @Description: 组织单位-通知列表
	 * @author Na.Y --- 2016年9月20日下午4:00:57
	 */
	public void getNoticeSender() {
		try {

			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getSiteCode();
			}

			if (StringUtils.isEmpty(siteCode)) {
				return;
			}

			// 查询条件：主题（模糊查询）
			String topicSearch = request.getParameter("topicSearch");
			String publishDateStart = request.getParameter("publishDateStart");
			String publishDateEnd = request.getParameter("publishDateEnd");

			NoticeSenderRequest noticeSenderRequest = new NoticeSenderRequest();
			noticeSenderRequest.setOrgSiteCode(siteCode);
			noticeSenderRequest.setTopic(topicSearch);
			noticeSenderRequest.setPublishDateStart(publishDateStart);
			noticeSenderRequest.setPublishDateEnd(publishDateEnd);
			List<QueryOrder> listQueryOrder = new ArrayList<QueryOrder>();
			QueryOrder queryOrder = new QueryOrder("modify_time",
					QueryOrderType.DESC);
			listQueryOrder.add(queryOrder);
			noticeSenderRequest.setQueryOrderList(listQueryOrder);
			noticeSenderRequest.setPageSize(Integer.MAX_VALUE);

			List<NoticeSender> listNoticeSender = noticeSenderServiceImpl
					.queryList(noticeSenderRequest);

			Map<String, Object> resultMap = new HashMap<String, Object>();

			if (CollectionUtils.isEmpty(listNoticeSender)) {
				resultMap.put("size", 0);
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return;
			}

			List<Object> items = new ArrayList<Object>();
			for (NoticeSender noticeSender : listNoticeSender) {
				Map<String, Object> item = new HashMap<String, Object>();

				item.put("noticeSenderId", noticeSender.getId());
				item.put("topic", noticeSender.getTopic());
				item.put("contents", noticeSender.getContents());
				item.put(
						"contentsSubStr",
						getStrByLength(noticeSender.getContents(), contentLengh));
				item.put("affix", noticeSender.getAffixName());
				item.put("publishDate", noticeSender.getPublishDate());
				item.put("status", noticeSender.getStatus());

				String feedResult = "";
				// 是否下级反馈（1：是，0：否）
				if (noticeSender.getIsFeedback() == 1) {
					NoticeReceiverRequest noticeReceiverRequest = new NoticeReceiverRequest();
					noticeReceiverRequest.setNoticeSenderId(noticeSender
							.getId());
					int receiverSumCount = noticeReceiverServiceImpl
							.queryCount(noticeReceiverRequest);
					// 已反馈数
					noticeReceiverRequest.setStatus(2);
					int feedCount = noticeReceiverServiceImpl
							.queryCount(noticeReceiverRequest);
					feedResult = feedCount + "/" + receiverSumCount;
				} else {
					feedResult = "未开通";
				}

				item.put("feedResult", feedResult);

				items.add(item);
			}

			resultMap.put("items", items);
			resultMap.put("size", items.size());
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 组织单位：获取通知反馈列表
	 * @author Na.Y --- 2016年9月20日下午4:00:57
	 */
	public void getNoticeFeedBack() {
		try {

			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getSiteCode();
			}

			if (StringUtils.isEmpty(siteCode)) {
				return;
			}

			String noticeSenderIdStr = request.getParameter("noticeSenderId");

			if (StringUtils.isEmpty(noticeSenderIdStr)) {
				return;
			}

			String feedDateStart = request.getParameter("feedDateStart");
			String feedDateEnd = request.getParameter("feedDateEnd");
			// 查询条件：网站标识码，网站名称（模糊查询）
			String siteSearch = request.getParameter("siteSearch");

			NoticeReceiverRequest noticeReceiverRequest = new NoticeReceiverRequest();
			noticeReceiverRequest.setNoticeSenderId(Integer
					.parseInt(noticeSenderIdStr));
			noticeReceiverRequest.setSiteSearch(siteSearch);
			noticeReceiverRequest.setFeedDateStart(feedDateStart);
			noticeReceiverRequest.setFeedDateEnd(feedDateEnd);

			List<NoticeReceiver> listNoticeReceiver = noticeReceiverServiceImpl
					.queryNoticeReceiverList(noticeReceiverRequest);

			Map<String, Object> resultMap = new HashMap<String, Object>();

			if (CollectionUtils.isEmpty(listNoticeReceiver)) {
				resultMap.put("receiverSumCount", 0);
				resultMap.put("feedCount", 0);
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return;
			}

			List<Object> items = new ArrayList<Object>();
			int feedCount = 0;
			for (NoticeReceiver noticeReceiver : listNoticeReceiver) {
				Map<String, Object> item = new HashMap<String, Object>();

				item.put("noticeReceiverId", noticeReceiver.getId());
				item.put("siteCode", noticeReceiver.getSiteCode());
				item.put("siteName", noticeReceiver.getSiteName());
				item.put("feedContents", noticeReceiver.getFeedContents());
				item.put(
						"feedContentsSubStr",
						getStrByLength(noticeReceiver.getFeedContents(),
								contentLengh));
				item.put("feedAffixName", noticeReceiver.getFeedAffixName());
				item.put("feedAffixUrl", noticeReceiver.getFeedAffixUrl());
				item.put("feedDate", noticeReceiver.getFeedDate());
				item.put("status", noticeReceiver.getStatus());
				if (noticeReceiver.getStatus() == 2) {
					feedCount += 1;
				}
				items.add(item);
			}

			resultMap.put("items", items);
			resultMap.put("receiverSumCount", items.size());
			resultMap.put("feedCount", feedCount);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 组织单位-通过id查询通知
	 * @author Na.Y --- 2016年9月20日下午4:00:57
	 */
	public void getNoticeSenderById() {
		try {

			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getSiteCode();
			}

			if (StringUtils.isEmpty(siteCode)) {
				return;
			}
	
			String idStr = request.getParameter("id");

			if (StringUtils.isEmpty(idStr)) {
				return;
			}
			NoticeSender noticeSender = noticeSenderServiceImpl.get(Integer
					.parseInt(idStr));

			if (null == noticeSender) {
				return;
			}

			writerPrint(JSONObject.fromObject(noticeSender).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 填报单位-反馈列表
	 * @author Na.Y --- 2016年9月20日下午4:00:57
	 */
	public void getNoticeReceiverFeed() {
		try {

			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getSiteCode();
			}

			if (StringUtils.isEmpty(siteCode)) {
				return;
			}

			String feedDateStart = request.getParameter("feedDateStart");
			String feedDateEnd = request.getParameter("feedDateEnd");
			// 查询条件：通知主题（模糊查询）
			String topicSearch = request.getParameter("topicSearch");

			NoticeReceiverRequest noticeReceiverRequest = new NoticeReceiverRequest();
			noticeReceiverRequest.setSiteCode(siteCode);
			noticeReceiverRequest.setTopic(topicSearch);
			noticeReceiverRequest.setFeedDateStart(feedDateStart);
			noticeReceiverRequest.setFeedDateEnd(feedDateEnd);

			List<NoticeReceiver> listNoticeReceiver = noticeReceiverServiceImpl
					.queryNoticeSenderAndReceiver(noticeReceiverRequest);

			Map<String, Object> resultMap = new HashMap<String, Object>();

			if (CollectionUtils.isEmpty(listNoticeReceiver)) {
				resultMap.put("receiverSumCount", 0);
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return;
			}

			List<Object> items = new ArrayList<Object>();
			for (NoticeReceiver noticeReceiver : listNoticeReceiver) {
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("topic", noticeReceiver.getTopic());
				item.put("contents", noticeReceiver.getContents());
				item.put("contentsSubStr",getStrByLength(noticeReceiver.getContents(),contentLengh));
				item.put("affixName", noticeReceiver.getAffixName());
				
				item.put("feedDeadlineDate", noticeReceiver.getFeedDeadlineDate());
				//是否超过截止日期
				boolean isOverDeadLineDate=false;
				try {
					if(StringUtils.isNotEmpty(noticeReceiver.getFeedDeadlineDate())){
						int interValDays = DateUtils.getDaysBetween2Days(DateUtils.parseStandardDate(noticeReceiver.getFeedDeadlineDate()), DateUtils.getNow());
						if(interValDays>0){
							isOverDeadLineDate =true;
						}
					}
				} catch (Exception e) {
					continue;
				}
				
				
				if(isOverDeadLineDate){
					item.put("isOverDeadLineDate", 1);
				}else{
					item.put("isOverDeadLineDate", 0);
				}
			

				if (!StringUtils.isEmpty(noticeReceiver.getAffixUrl())) {
					item.put("affixUrl", noticeReceiver.getAffixUrl());
				} else {
					item.put("affixUrl", "");
				}

				item.put("noticeReceiverId", noticeReceiver.getId());
				item.put("noticeSenderId", noticeReceiver.getNoticeSenderId());
				item.put("feedDate", noticeReceiver.getFeedDate());
				item.put("isRead", noticeReceiver.getIsRead());
				item.put("status", noticeReceiver.getStatus());
				items.add(item);
			}

			resultMap.put("items", items);
			resultMap.put("receiverSumCount", items.size());
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static String getStrByLength(String srcStr, int length) {

		if (StringUtils.isEmpty(srcStr)) {
			return "";
		}

		if (srcStr.length() <= length) {
			return FileUtils.trimBlank(srcStr);
		} else {

			return FileUtils.trimBlank(srcStr).substring(0, length);
		}
	}

	/**
	 * @Description: 组织单位:创建通知
	 * @author: Na.Y --- 2016-9-20下午7:51:59
	 * @return
	 */
	public String createOrUpdateNotice() {

		String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
		ShiroUser shiroUser = getCurrentUserInfo();
		if (shiroUser != null) {
			siteCode = shiroUser.getSiteCode();
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(siteCode.length()!=6){
			resultMap.put("errorMsg", "创建或修改通知失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			return null;
		}

		// 判断是增加，还是修改
		NoticeSender noticeSender;
		boolean isAdd = true;
		String id = request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			isAdd = false;
			noticeSender = noticeSenderServiceImpl.get(Integer.parseInt(id));
		} else {
			noticeSender = new NoticeSender();
		}

		String topic = request.getParameter("topic");// 主题
		String contents = request.getParameter("contents");// 内容
		String affixName = request.getParameter("affixName");// 附件名称
		String isFeedback = request.getParameter("isFeedback");// 是否下级反馈（1：是，0：否）

		String feedDeadlineDate = request.getParameter("feedDeadlineDate");// 反馈截止日期（yyyy-mm-dd）

		
		try {
			String fileName = "";
			if (!StringUtils.isEmpty(noticeReportFileName)
					&& !StringUtils.isEmpty(noticeReport)) {

				fileName = urlAdapterVar.getWordfilepath() + siteCode + "_"
						+ DateUtils.formatShortFullDateTime(new Date()) + "_"
						+ noticeReportFileName;
				FileInputStream fis = new FileInputStream(noticeReport);

				FileUtils
						.writeFile(fis, urlAdapterVar.getWebPaths() + fileName);

			}

			noticeSender.setOrgSiteCode(siteCode);
			noticeSender.setTopic(topic);
			noticeSender.setContents(contents);
			if(StringUtils.isNotEmpty(fileName)){
				noticeSender.setAffixUrl(fileName);
			}
			if (StringUtils.isNotEmpty(affixName)) {
				noticeSender.setAffixName(affixName);// 原文件名
			} else {
				noticeSender.setAffixName(noticeReportFileName);// 原文件名
			}

			noticeSender.setFeedDeadlineDate(feedDeadlineDate);
			try {
				noticeSender.setIsFeedback(Integer.parseInt(isFeedback));
			} catch (Exception e) {
			}

			noticeSender.setStatus(0);

			if (isAdd) {
				noticeSenderServiceImpl.add(noticeSender);
				resultMap.put("success", "创建通知成功");
			} else {
				noticeSenderServiceImpl.update(noticeSender);
				resultMap.put("success", "修改通知成功");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "创建或修改通知失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
		return null;
	}

	/**
	 * @Description: 组织单位：删除通知
	 * @author Na.Y --- 2016-9-14下午4:24:16
	 */
	public void deleteNoticeSender() {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String noticeSenderStr = request.getParameter("noticeSenderId");// 通知表id

		if (StringUtils.isEmpty(noticeSenderStr)) {
			resultMap.put("errorMsg", "通知表id为空");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			return;
		}

		try {

			// 抽查进度表Id
			int noticeSenderId = Integer.valueOf(noticeSenderStr);
			noticeSenderServiceImpl.delete(noticeSenderId);
			resultMap.put("success", "删除通知成功");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("errorMsg", "通知删除异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 组织单位：发布通知
	 * @author Na.Y --- 2016-9-14下午4:24:16
	 */
	public void publishNotice() {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
		ShiroUser shiroUser = getCurrentUserInfo();
		if (shiroUser != null) {
			siteCode = shiroUser.getSiteCode();
		}

		if (StringUtils.isEmpty(siteCode)) {
			resultMap.put("errorMsg", "未获取到登录帐号");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			return;
		}

		String noticeSenderStr = request.getParameter("noticeSenderId");// 通知表id

		if (StringUtils.isEmpty(noticeSenderStr)) {
			resultMap.put("errorMsg", "通知表id为空");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			return;
		}

		try {

			// 通知表Id
			int noticeSenderId = Integer.valueOf(noticeSenderStr);
			NoticeSender noticeSender = noticeSenderServiceImpl
					.get(noticeSenderId);
			if (noticeSender != null) {

				if (noticeSender.getStatus() == 1) {
					resultMap.put("success", "该通知已发布");
					writerPrint(JSONObject.fromObject(resultMap).toString());
					return;
				}
				noticeSender.setStatus(1);
				noticeSender.setPublishDate(DateUtils.getTodayStandardStr());
				noticeSenderServiceImpl.update(noticeSender);

				// 创建填报单位接收记录
				DatabaseTreeInfoRequest treeinfoRequest = new DatabaseTreeInfoRequest();
				treeinfoRequest.setOrgSiteCode(siteCode);
				treeinfoRequest.setPageSize(Integer.MAX_VALUE);
				treeinfoRequest.setIsLink(1);
				treeinfoRequest.setSiteCodeLength(10);
				List<DatabaseTreeInfo> listTreeInfo = databaseTreeInfoServiceImpl
						.queryList(treeinfoRequest);
				
				// 填报单位反馈状态（0：无需反馈，1：未提交，2：已提交，3：退回）
				int status = 0;
				if (noticeSender.getIsFeedback() == 1) {
					status = 1;
				}

				for (DatabaseTreeInfo treeInfo : listTreeInfo) {
					NoticeReceiver noticeReceiver = new NoticeReceiver();
					noticeReceiver.setSiteCode(treeInfo.getSiteCode());
					noticeReceiver.setIsRead(0);
					noticeReceiver.setNoticeSenderId(noticeSender.getId());
					noticeReceiver.setStatus(status);
					noticeReceiverServiceImpl.add(noticeReceiver);
				}
			}
			resultMap.put("success", "发布通知成功");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("errorMsg", "发布通知异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}

	}

	/**
	 * @Description: 组织单位：退回通知反馈
	 * @author Na.Y --- 2016-9-14下午4:24:16
	 */
	public void returnNoticeFeed() {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
		ShiroUser shiroUser = getCurrentUserInfo();
		if (shiroUser != null) {
			siteCode = shiroUser.getSiteCode();
		}

		if (StringUtils.isEmpty(siteCode)) {
			resultMap.put("errorMsg", "未获取到登录帐号");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			return;
		}

		String noticeReceiverIdStr = request.getParameter("noticeReceiverId");// 通知表id

		if (StringUtils.isEmpty(noticeReceiverIdStr)) {
			resultMap.put("errorMsg", "通知反馈表id为空");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			return;
		}

		try {

			int noticeReceiverId = Integer.valueOf(noticeReceiverIdStr);
			NoticeReceiver noticeReceiver = noticeReceiverServiceImpl
					.get(noticeReceiverId);
			if (noticeReceiver != null) {

				if (noticeReceiver.getStatus() != 2) {
					resultMap.put("success", "该通知未提交");
					writerPrint(JSONObject.fromObject(resultMap).toString());
					return;
				}
				noticeReceiver.setStatus(3);
				noticeReceiver.setReturnTime(DateUtils.getNow());
				noticeReceiverServiceImpl.update(noticeReceiver);

			}
			resultMap.put("success", "退回通知成功");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("errorMsg", "退回通知异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}

	}

	/**
	 * @Description: 填报单位-查看通知 通过id查询通知
	 * @author Na.Y --- 2016年9月20日下午4:00:57
	 */
	public void readNotice() {
		try {

			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getSiteCode();
			}

			if (StringUtils.isEmpty(siteCode)) {
				return;
			}

			String noticeReceiverIdStr = request
					.getParameter("noticeReceiverId");
			if (StringUtils.isEmpty(noticeReceiverIdStr)) {
				return;
			}

			NoticeReceiver noticeReceiver = noticeReceiverServiceImpl
					.get(Integer.parseInt(noticeReceiverIdStr));
			if (null == noticeReceiver) {
				return;
			}

			if (noticeReceiver.getIsRead() != 1) {
				noticeReceiver.setIsRead(1);
				noticeReceiverServiceImpl.update(noticeReceiver);
			}

			NoticeSender noticeSender = noticeSenderServiceImpl
					.get(noticeReceiver.getNoticeSenderId());

			if (null == noticeSender) {
				return;
			}

			DatabaseOrgInfoRequest databaseOrgInfoRequest = new DatabaseOrgInfoRequest();
			databaseOrgInfoRequest.setStieCode(noticeSender.getOrgSiteCode());
			List<DatabaseOrgInfo> listDatabaseOrgInfo = databaseOrgInfoServiceImpl
					.queryList(databaseOrgInfoRequest);
			noticeSender.setOrgSiteName(listDatabaseOrgInfo.get(0).getName());
			writerPrint(JSONObject.fromObject(noticeSender).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 填报单位-查看通知与反馈
	 * @author Na.Y --- 2016年9月20日下午4:00:57
	 */
	public void getNoticeSenderAndReceiver() {
		try {

			Map<String, Object> resultMap = new HashMap<String, Object>();

			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getSiteCode();
			}

			if (StringUtils.isEmpty(siteCode)) {
				return;
			}

			String noticeReceiverIdStr = request
					.getParameter("noticeReceiverId");
			if (StringUtils.isEmpty(noticeReceiverIdStr)) {
				return;
			}

			NoticeReceiver noticeReceiver = noticeReceiverServiceImpl
					.get(Integer.parseInt(noticeReceiverIdStr));
			if (null == noticeReceiver) {
				return;
			}

			// 修改状态为已读
			if (noticeReceiver.getIsRead() == 0) {
				noticeReceiver.setIsRead(1);
				noticeReceiverServiceImpl.update(noticeReceiver);
			}

			NoticeSender noticeSender = noticeSenderServiceImpl
					.get(noticeReceiver.getNoticeSenderId());

			if (null == noticeSender) {
				return;
			}

			resultMap.put("topic", noticeSender.getTopic());
			resultMap.put("noticeSenderId", noticeSender.getId());
			resultMap.put("contents", noticeSender.getContents());
			resultMap.put("affixName", noticeSender.getAffixName());
			resultMap.put("affixUrl", noticeSender.getAffixUrl());
			resultMap.put("feedContents", noticeReceiver.getFeedContents());
			resultMap.put("feedAffixName", noticeReceiver.getFeedAffixName());
			resultMap.put("affixUrl", noticeReceiver.getAffixUrl());

			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 填报单位：提交反馈
	 * @author: Na.Y --- 2016-9-20下午7:51:59
	 * @return
	 */
	public String submitFeedBack() {

		String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
		ShiroUser shiroUser = getCurrentUserInfo();
		if (shiroUser != null) {
			siteCode = shiroUser.getSiteCode();
		}

		// 反馈附件名称
		String feedAffixName = request.getParameter("feedAffixName");
		String noticeReceiverIdStr = request.getParameter("noticeReceiverId");
		if (StringUtils.isEmpty(noticeReceiverIdStr)) {
			return null;
		}

		NoticeReceiver noticeReceiver = noticeReceiverServiceImpl.get(Integer
				.parseInt(noticeReceiverIdStr));
		if (null == noticeReceiver) {
			return null;
		}

		String feedContents = request.getParameter("feedContents");// 反馈内容
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String fileName = "";
			
			if(!"".equals(feedAffixName)&& feedAffixName != null){
				if(noticeReportContentType != null){
					if(!"application/pdf".equals(noticeReportContentType ) && !"application/msword".equals(noticeReportContentType ) && !"application/msword".equals(noticeReportContentType ) && !"image/jpeg".equals(noticeReportContentType ) && !"image/png".equals(noticeReportContentType )){
						resultMap.put("errorMsg", "只能上传jpg、png、pdf和word文件");
						writerPrint(JSONObject.fromObject(resultMap).toString());
						return null;
					}
				}
			}
			
			if (!StringUtils.isEmpty(noticeReportFileName)
					&& !StringUtils.isEmpty(noticeReport) ) {

				fileName = urlAdapterVar.getWordfilepath() + siteCode + "_"
						+ DateUtils.formatShortFullDateTime(new Date())
						+ noticeReportFileName;
				FileInputStream fis = new FileInputStream(noticeReport);

				FileUtils
						.writeFile(fis, urlAdapterVar.getWebPaths() + fileName);

			}

			if (!StringUtils.isEmpty(feedAffixName)) {
				noticeReceiver.setFeedAffixName(feedAffixName);// 原文件名
			} else {
				noticeReceiver.setFeedAffixName(noticeReportFileName);// 原文件名
			}

			noticeReceiver.setFeedContents(feedContents);
			
			if(StringUtils.isNotEmpty(fileName)){
				noticeReceiver.setFeedAffixUrl(fileName);
			}
			
			noticeReceiver.setFeedDate(DateUtils.getTodayStandardStr());
			noticeReceiver.setStatus(2);

			noticeReceiverServiceImpl.update(noticeReceiver);
			resultMap.put("success", "提交反馈成功");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "提交反馈成功异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
		return null;
	}


	/**
	 * @Description: 下载反馈附件
	 * @author: Na.Y --- 2016-9-23下午4:29:54
	 * @return
	 */
	public void downLoadFeedAffix() {
		try {
			String noticeReceiverIdStr = request
					.getParameter("noticeReceiverId");
			if (StringUtils.isEmpty(noticeReceiverIdStr)) {
				writerPrint("文件不存在");
				return;
			}

			NoticeReceiver noticeReceiver = noticeReceiverServiceImpl
					.get(Integer.parseInt(noticeReceiverIdStr));

			if (null != noticeReceiver&&StringUtils.isNotEmpty(noticeReceiver.getFeedAffixUrl())) {
				String wordUrl = urlAdapterVar.getWebPaths()
						+ noticeReceiver.getFeedAffixUrl();
				File file = new File(wordUrl);
				if (file.exists()) {

					String aliasName = noticeReceiver.getFeedAffixName();
					aliasName = FileUtils.getAliasName(aliasName, wordUrl);

					FileUtils.outPutFile(response, wordUrl, aliasName);
				} else {
					writerPrint("文件不存在");
				}

			}else{
				writerPrint("文件不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			writerPrint("文件下载异常");
		}
	}

	/**
	 * @Description: 下载通知附件
	 * @author: Na.Y --- 2016-9-23下午4:29:54
	 * @return
	 */
	public void downLoadNoticeSendAffix() {
		try {
			String noticeSenderIdStr = request.getParameter("noticeSenderId");
			if (StringUtils.isEmpty(noticeSenderIdStr)) {
				writerPrint("文件不存在");
				return;
			}

			NoticeSender noticeSenderQuery = noticeSenderServiceImpl.get(Integer
					.parseInt(noticeSenderIdStr));

			String affixUrl = noticeSenderQuery.getAffixUrl();

			if (null != noticeSenderQuery&&StringUtils.isNotEmpty(affixUrl)) {
				String wordUrl = "";
				if (affixUrl.startsWith(urlAdapterVar.getWebPaths())) {
					wordUrl = affixUrl;
				} else {
					wordUrl = urlAdapterVar.getWebPaths() + affixUrl;
				}
				File file = new File(wordUrl);
				if (file.exists()) {
					String aliasName = noticeSenderQuery.getAffixName();
					aliasName = FileUtils.getAliasName(aliasName, wordUrl);

					FileUtils.outPutFile(response, wordUrl, aliasName);

				} else {
					writerPrint("文件不存在");
				}

			}else{
				writerPrint("文件不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			writerPrint("文件下载异常");
		}
	}

	public INoticeSenderService getNoticeSenderServiceImpl() {
		return noticeSenderServiceImpl;
	}

	public void setNoticeSenderServiceImpl(
			INoticeSenderService noticeSenderServiceImpl) {
		this.noticeSenderServiceImpl = noticeSenderServiceImpl;
	}

	public String getNoticeReportFileName() {
		return noticeReportFileName;
	}

	public void setNoticeReportFileName(String noticeReportFileName) {
		this.noticeReportFileName = noticeReportFileName;
	}

	public String getNoticeReport() {
		return noticeReport;
	}

	public void setNoticeReport(String noticeReport) {
		this.noticeReport = noticeReport;
	}

	public String getNoticeReportContentType() {
		return noticeReportContentType;
	}

	public void setNoticeReportContentType(String noticeReportContentType) {
		this.noticeReportContentType = noticeReportContentType;
	}

}
