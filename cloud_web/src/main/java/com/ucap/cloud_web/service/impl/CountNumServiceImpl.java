package com.ucap.cloud_web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.dto.ConfigLinkExceptRequest;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.ConnectionBusinessDetailRequest;
import com.ucap.cloud_web.dto.CorrectContentDetailRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DetectionPeroidCountRequest;
import com.ucap.cloud_web.dto.LinkAllDetailRequest;
import com.ucap.cloud_web.dto.LinkHomeAvailableRequest;
import com.ucap.cloud_web.dto.SecurityBlankDetailRequest;
import com.ucap.cloud_web.dto.SecurityFatalErrorRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.dto.SecurityOthersRequest;
import com.ucap.cloud_web.dto.SecurityResponseRequest;
import com.ucap.cloud_web.dto.SecurityServcieRequest;
import com.ucap.cloud_web.dto.UpdateHomeDetailRequest;
import com.ucap.cloud_web.entity.ConfigLinkExcept;
import com.ucap.cloud_web.entity.ConnectionAll;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DetectionPeroidCount;
import com.ucap.cloud_web.entity.LinkAllDetail;
import com.ucap.cloud_web.entity.LinkHomeAvailable;
import com.ucap.cloud_web.entity.SecurityHomeChannel;
import com.ucap.cloud_web.entity.UpdateHomeDetail;
import com.ucap.cloud_web.service.IConfigLinkExceptService;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IConnectionBusinessDetailService;
import com.ucap.cloud_web.service.ICorrectContentDetailService;
import com.ucap.cloud_web.service.ICountNumService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDetectionPeroidCountService;
import com.ucap.cloud_web.service.ILinkAllDetailService;
import com.ucap.cloud_web.service.ILinkHomeAvailableService;
import com.ucap.cloud_web.service.ISecurityBlankDetailService;
import com.ucap.cloud_web.service.ISecurityFatalErrorService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.service.ISecurityOthersService;
import com.ucap.cloud_web.service.ISecurityResponseService;
import com.ucap.cloud_web.service.ISecurityServcieService;
import com.ucap.cloud_web.service.IUpdateHomeDetailService;

@Service
public class CountNumServiceImpl implements ICountNumService {

	private static Logger logger = Logger.getLogger(CountNumServiceImpl.class);
	@Autowired
	private ILinkHomeAvailableService linkHomeAvailableServiceImpl;// 首页链接可用性

	@Autowired
	private IConfigLinkExceptService configLinkExceptServiceImpl;

	@Autowired
	private ILinkAllDetailService linkAllDetailServiceImpl;// 全站链接可用性

	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;
	@Autowired
	private ISecurityBlankDetailService securityBlankDetailServiceImpl;// 空白栏目详情
	@Autowired
	private ISecurityResponseService securityResponseServiceImpl;// 互动回应差
	@Autowired
	private ISecurityServcieService securityServcieServiceImpl;// 服务不实用
	@Autowired
	private ICorrectContentDetailService correctContentDetailServiceImpl;// 内容正确性
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;// 基础信息表
	@Autowired
	private IUpdateHomeDetailService updateHomeDetailServiceImpl;// 首页面更新明细表
	@Autowired
	private ISecurityFatalErrorService securityFatalErrorServiceImpl;// 严重错误
	@Autowired
	private ISecurityOthersService securityOthersServiceImpl;// 其他问题

	@Autowired
	private IConnectionBusinessDetailService connectionBusinessDetailServiceImpl;// 业务系统连通性

	@Autowired
	private IConnectionAllService connectionAllServiceImpl;// 连通性数据

	@Autowired
	private IDetectionPeroidCountService detectionPeroidCountServiceImpl;

	@Override
	public String getConnectionHomeProportion(DatabaseInfo databaseInfo, String startDate, String endDate) {

		try {

			String siteCode = databaseInfo.getSiteCode();

			// 是否扫描 isScan=1 每天96次，isScan!=1 每天1次
			// 每天总连接次数
			int connectionSumPerDay = getConnectionSumPerDay(databaseInfo);
			// 注：ConnectionAll 中只保存了不连通的数据
			int connectionSum = 0;// 总次数=ConnectionAll中查出来的总次数和+其余全部连通的总次数
			int errorNum = 0;// 总失败次数ConnectionAll中查出来的总失败数和
			ConnectionAllRequest connectionAllRequest = new ConnectionAllRequest();
			connectionAllRequest.setSiteCodeEqual(siteCode);// 网站标示码
			connectionAllRequest.setType(1);// 首页连通性
			connectionAllRequest.setStartDate(startDate);// 周期开始时间
			connectionAllRequest.setEndDate(endDate);// 周期结束时间
			connectionAllRequest.setPageSize(Integer.MAX_VALUE);

			List<ConnectionAll> listConnectionAll = connectionAllServiceImpl.queryList(connectionAllRequest);

			if (CollectionUtils.isEmpty(listConnectionAll)) {
				return "0";
			} else {

				// 遍历：ConnectionAll中查出来的总次数和 ，总失败和
				for (ConnectionAll connectionAll : listConnectionAll) {
					connectionSum += connectionAll.getConnectionSum();
					errorNum += connectionAll.getErrorNum();
				}

				int sumDays = DateUtils.getDaysBetween2Days(DateUtils.parseStandardDate(startDate),
						DateUtils.parseStandardDate(endDate)) + 1;

				int errorDays = listConnectionAll.size();

				if (errorDays < sumDays) {
					connectionSum += (sumDays - errorDays) * connectionSumPerDay;
				}

				// 总失败比例
				return getProportion(errorNum, connectionSum);

			}

		} catch (Exception e) {
			return "";
		}

	}

	@Override
	public ConnectionAll getConnectionHomeData(String siteCode, String startDate, String endDate) {

		ConnectionAll connectionAll = new ConnectionAll();

		try {

			DatabaseInfoRequest databaseRequest = new DatabaseInfoRequest();
			databaseRequest.setSiteCode(siteCode);
			List<DatabaseInfo> databaseInfoList = databaseInfoServiceImpl.queryList(databaseRequest);

			if (CollectionUtils.isEmpty(databaseInfoList)) {
				return null;
			}

			DatabaseInfo databaseInfo = databaseInfoList.get(0);

			// 是否扫描 isScan=1 每天96次，isScan!=1 每天1次 , 以此计算未保存连通数据的总次数
			// isScan=1 总次数为96，isScan!=1 总次数1次，关停例外总次数1次
			// 每天总连接次数
			int connectionSumPerDay = getConnectionSumPerDay(databaseInfo);

			// 注：ConnectionAll 中只保存了不连通的数据
			int connectionSum = 0;// 总次数=不连通中总次数【保存在connectionAll中】+连通总次数【根据is_scan算】
			int errorNum = 0;// 总失败次数ConnectionAll中查出来的总失败数和
			int successNum = 0;// 成功次数
			String successProportion = "";// 成功占比
			String errorProportion = "";// 超时占比

			// 总天数
			int sumDays = DateUtils.getDaysBetween2Days(DateUtils.parseStandardDate(startDate),
					DateUtils.parseStandardDate(endDate)) + 1;
			// 不连通天数
			int errorDays = 0;
			// 连通天数
			int connectionDays = 0;

			// 已保存在ConnectionAll中总次数（不连通中总次数）
			// 总次数=不连通中总次数【保存在connectionAll中】+连通总次数【根据is_scan算】
			int partConnectionSum = 0;
			// 查询不连通数据
			ConnectionAllRequest connectionAllRequest = new ConnectionAllRequest();
			connectionAllRequest.setSiteCodeEqual(siteCode);// 网站标示码
			connectionAllRequest.setType(1);// 首页连通性
			connectionAllRequest.setStartDate(startDate);// 周期开始时间
			connectionAllRequest.setEndDate(endDate);// 周期结束时间
			connectionAllRequest.setPageSize(Integer.MAX_VALUE);
			List<ConnectionAll> listConnectionAll = connectionAllServiceImpl.queryList(connectionAllRequest);

			if (CollectionUtils.isEmpty(listConnectionAll)) {
				errorDays = 0;
				errorNum = 0;
				partConnectionSum = 0;
			} else {

				errorDays = listConnectionAll.size();

				// 遍历：ConnectionAll中查出来的总次数和 ，总失败和
				for (ConnectionAll connection : listConnectionAll) {
					partConnectionSum += connection.getConnectionSum();
					errorNum += connection.getErrorNum();
				}
			}

			connectionDays = sumDays - errorDays;
			// 总次数=不连通中总次数【保存在connectionAll中】+连通总次数【根据is_scan算】
			connectionSum = connectionDays * connectionSumPerDay + partConnectionSum;
			// 成功次数=总次数-失败次数
			successNum = connectionSum - errorNum;
			// 总失败比例
			errorProportion = getProportion(errorNum, connectionSum);
			// 成功比例
			successProportion = getProportion(successNum, connectionSum);

			connectionAll.setConnectionSum(connectionSum);
			connectionAll.setErrorNum(errorNum);
			connectionAll.setSuccessNum(successNum);
			connectionAll.setErrorProportion(errorProportion);
			connectionAll.setSuccessProportion(successProportion);
			connectionAll.setSiteCode(siteCode);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return connectionAll;

	}

	/**
	 * 获取每天连通总次数
	 * 
	 * @param databaseInfo
	 * @return
	 */
	private int getConnectionSumPerDay(DatabaseInfo databaseInfo) {
		// 关停例外，每天扫描一次,正常站点databaseInfo.getIsScan()！=1 每天一次
		if (databaseInfo.getIsexp() != 1 || databaseInfo.getIsScan() != 1) {
			return 1;
		} else {
			// 正常站点：databaseInfo.getIsScan()==1 每天96次
			return 96;
		}

	}

	/**
	 * 获取比例（没有%号），保留2位小数点
	 * 
	 * @param num
	 *            数量
	 * @param totalNum
	 *            总数
	 * @return
	 */
	private String getProportion(int num, int totalNum) {

		if (num == 0 || totalNum == 0) {
			return "0";
		}

		double proportion = num * 1.0 / totalNum * 100;

		if (proportion <= 0) {
			return "0";
		}

		if (proportion >= 100) {
			return "100";
		}

		return StringUtils.formatDouble(2, proportion);
	}

	@Override
	public int getLinkHomeNum(String siteCode, String startDate, String endDate) {
		// 首页链接可用性
		LinkHomeAvailableRequest lhrequest = new LinkHomeAvailableRequest();
		lhrequest.setPageNo(0);
		lhrequest.setPageSize(Integer.MAX_VALUE);
		lhrequest.setSiteCode(siteCode);
		lhrequest.setBeginDate(startDate);
		lhrequest.setLastDate(endDate);
		lhrequest.setGroupBy("url");
		List<LinkHomeAvailable> listLinkAllNum = linkHomeAvailableServiceImpl.queryList(lhrequest);
		if (CollectionUtils.isEmpty(listLinkAllNum)) {
			return 0;
		}

		// 首页死链数
		int sumRealNum = listLinkAllNum.size();

		// 死链不包含url配置表数据
		ConfigLinkExceptRequest configLinkExceptRequest = new ConfigLinkExceptRequest();
		configLinkExceptRequest.setSiteCode(siteCode);
		configLinkExceptRequest.setStatus(0);
		configLinkExceptRequest.setPageSize(Integer.MAX_VALUE);
		List<ConfigLinkExcept> configLinkExceptlist = configLinkExceptServiceImpl.queryList(configLinkExceptRequest);

		if (CollectionUtils.isEmpty(configLinkExceptlist)) {
			return sumRealNum;
		}

		// 去除配置表中不包含的
		for (LinkHomeAvailable linkHomeAvailable : listLinkAllNum) {
			if (isRemove(configLinkExceptlist, linkHomeAvailable.getUrl())) {
				sumRealNum = sumRealNum - 1;
			}
		}

		return sumRealNum;
	}

	/**
	 * 死链url是否去掉
	 * 
	 * @param configLinkExceptlist
	 *            死链排除配置
	 * @param url
	 * @return
	 */
	private boolean isRemove(List<ConfigLinkExcept> configLinkExceptlist, String url) {

		if (CollectionUtils.isEmpty(configLinkExceptlist)) {
			return false;
		}

		for (ConfigLinkExcept configLinkExcept : configLinkExceptlist) {

			try {
				if (url.trim().startsWith(configLinkExcept.getUrl().trim())) {
					return true;
				}
			} catch (Exception e) {
				continue;
			}

		}
		return false;

	}

	@Override
	public int getLinkAllNum(String siteCode, String startDate, String endDate, int servicePeriodId) {
		LinkAllDetailRequest linkAllDetailRequest = new LinkAllDetailRequest();
		linkAllDetailRequest.setSiteCode(siteCode);
		linkAllDetailRequest.setServicePeriodId(servicePeriodId);
		linkAllDetailRequest.setStartDate(startDate);
		linkAllDetailRequest.setEndDate(endDate);
		linkAllDetailRequest.setPageSize(Integer.MAX_VALUE);

		List<LinkAllDetail> listLinkAll = linkAllDetailServiceImpl.queryList(linkAllDetailRequest);

		if (CollectionUtils.isEmpty(listLinkAll)) {
			return 0;
		}

		// 首页死链数
		int sumRealNum = listLinkAll.size();

		// 死链不包含url配置表数据
		ConfigLinkExceptRequest configLinkExceptRequest = new ConfigLinkExceptRequest();
		configLinkExceptRequest.setSiteCode(siteCode);
		configLinkExceptRequest.setStatus(0);
		configLinkExceptRequest.setPageSize(Integer.MAX_VALUE);
		List<ConfigLinkExcept> configLinkExceptlist = configLinkExceptServiceImpl.queryList(configLinkExceptRequest);

		if (CollectionUtils.isEmpty(configLinkExceptlist)) {
			return sumRealNum;
		}

		for (LinkAllDetail linkAllDetail : listLinkAll) {
			if (isRemove(configLinkExceptlist, linkAllDetail.getUrl())) {
				sumRealNum = sumRealNum - 1;
			}
		}

		return sumRealNum;
	}

	@Override
	public int isSecurityHomeOverTwoWeek(String siteCode, String homeUrl, String scanDate) {

		SecurityHomeChannelRequest securityHomeChannelRequest = new SecurityHomeChannelRequest();
		securityHomeChannelRequest.setScanDate(scanDate);
		securityHomeChannelRequest.setType(1);
		securityHomeChannelRequest.setSiteCode(siteCode);

		List<SecurityHomeChannel> securityHomeChannelList = securityHomeChannelServiceImpl
				.queryList(securityHomeChannelRequest);

		if (!CollectionUtils.isEmpty(securityHomeChannelList)) {

			SecurityHomeChannel securityHomeChannel = securityHomeChannelList.get(0);

			// 不能获取最后更新时间：返回-1（未知）
			if (StringUtils.isEmpty(securityHomeChannel.getModifyDate())) {
				return -1;
			}

			// 超过14天未更新：返回1
			if (securityHomeChannel.getNotUpdateNum() > 14) {
				return 1;
			} else {

				// 未超过14天未更新：返回0
				return 0;
			}

		} else {

			// 查询首页更新明细表
			UpdateHomeDetailRequest updateHomeDetailRequest = new UpdateHomeDetailRequest();
			updateHomeDetailRequest.setSiteCode(siteCode);
			updateHomeDetailRequest.setHomeUrl(homeUrl);
			updateHomeDetailRequest.setDate(scanDate);
			List<UpdateHomeDetail> updateHomeDetailList = updateHomeDetailServiceImpl
					.queryList(updateHomeDetailRequest);

			// 有保存昨日更新数据，说明昨日已更新，返回0（未超过14天未更新）
			if (!CollectionUtils.isEmpty(updateHomeDetailList)) {
				return 0;
			}
		}

		return -1;
	}

	@Override
	public int getSecurityChannelNum(String siteCode, String startDate, String endDate,int servicePeroidId) {

		// / 查询出人工的
		List<SecurityHomeChannel> noAuto = securityHomeChannelServiceImpl.getSecurityChannel(siteCode, startDate,
				endDate, servicePeroidId,true);
		List<String> urlList = new ArrayList<String>();

		if (!CollectionUtils.isEmpty(noAuto)) {
			for (SecurityHomeChannel sHomeChannel : noAuto) {
				urlList.add(sHomeChannel.getUrl().trim());
			}
		}

		List<SecurityHomeChannel> auto = securityHomeChannelServiceImpl.getSecurityChannel(siteCode, startDate,
				endDate,servicePeroidId, false);
		// 以人工为主，如果机器扫描的不在人工数据之列，往里加
		if (!CollectionUtils.isEmpty(auto)) {
			for (SecurityHomeChannel securityHomeChannel : auto) {

				try {
					if (!urlList.contains(securityHomeChannel.getUrl().trim())) {
						noAuto.add(securityHomeChannel);
					}
				} catch (Exception e) {
					continue;
				}

			}
		}

		if (CollectionUtils.isEmpty(noAuto)) {
			return 0;
		} else {
			return noAuto.size();
		}

	}

	@Override
	public int getSecurityBlankNum(String siteCode, String startDate, String endDate, int servicePeriodId) {
		// 最新详情
		SecurityBlankDetailRequest sbqDetail = new SecurityBlankDetailRequest();
		sbqDetail.setSiteCode(siteCode);
		sbqDetail.setBeginScanDate(startDate);
		sbqDetail.setEndScanDate(endDate);
		sbqDetail.setServicePeriodId(servicePeriodId);
		int count = 0;
		try {
			count = securityBlankDetailServiceImpl.queryCount(sbqDetail);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return count;

	}

	@Override
	public int getSecurityResponseNum(String siteCode, String startDate, String endDate, int servicePeriodId) {
		SecurityResponseRequest request = new SecurityResponseRequest();
		request.setSiteCode(siteCode);
		request.setStartTime(startDate);
		request.setEndTime(endDate);
		// request.setPageSize(Integer.MAX_VALUE);
		request.setServicePeriodId(servicePeriodId);
		int count = 0;
		try {
			count = securityResponseServiceImpl.queryCount(request);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return count;
	}

	@Override
	public int getSecurityServiceNum(String siteCode, String startDate, String endDate, int servicePeriodId, int type) {
		SecurityServcieRequest securityServcieRequest = new SecurityServcieRequest();
		securityServcieRequest.setSiteCode(siteCode);
		securityServcieRequest.setBeginScanDate(startDate);
		securityServcieRequest.setEndScanDate(endDate);
		if (type == 1) {
			securityServcieRequest.setProblemTypeIdList("(1,2)");
		} else if (type == 3) {
			securityServcieRequest.setProblemTypeIdList("(3,4)");
		}

		securityServcieRequest.setServicePeriodId(servicePeriodId);
		int count = 0;
		try {
			count = securityServcieServiceImpl.queryCount(securityServcieRequest);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return count;
	}

	@Override
	public int getConnectionBusinessNum(String siteCode, String startDate, String endDate) {
		ConnectionBusinessDetailRequest request = new ConnectionBusinessDetailRequest();
		request.setSiteCode(siteCode);
		request.setStartDate(startDate);
		request.setEndDate(endDate);
		request.setGroupBy("url");

		int count = 0;
		try {
			count = connectionBusinessDetailServiceImpl.queryCount(request);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return count;
	}

	@Override
	public int getCorrectContentNum(String siteCode, String startDate, String endDate) {
		CorrectContentDetailRequest contentRequest = new CorrectContentDetailRequest();
		contentRequest.setSiteCode(siteCode);
		contentRequest.setBeginScanDate(startDate);
		contentRequest.setEndScanDate(endDate);
		contentRequest.setCorrectType(3);
		// contentRequest.setExposure(1); //已在sqlMappper里加了此条件过滤
		// 是否删除sqlMapper中默认传了参数
		int count = 0;
		try {
			count = correctContentDetailServiceImpl.queryCount(contentRequest);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return count;
	}

	@Override
	public int getSecurityFatalErrorNum(String siteCode, String startDate, String endDate, int servicePeriodId, int type) {
		SecurityFatalErrorRequest securityFatalErrorRequest = new SecurityFatalErrorRequest();
		securityFatalErrorRequest.setType(type);
		securityFatalErrorRequest.setSiteCode(siteCode);
		securityFatalErrorRequest.setServicePeriodId(servicePeriodId);
		securityFatalErrorRequest.setStartDate(startDate);
		securityFatalErrorRequest.setEndDate(endDate);
		int count = 0;
		try {
			count = securityFatalErrorServiceImpl.queryCount(securityFatalErrorRequest);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return count;
	}

	@Override
	public int getSecurityOthersNum(String siteCode, String startDate, String endDate, int servicePeriodId) {
		SecurityOthersRequest securityOthersRequest = new SecurityOthersRequest();
		securityOthersRequest.setSiteCode(siteCode);
		securityOthersRequest.setServicePeriodId(servicePeriodId);
		securityOthersRequest.setStartDate(startDate);
		securityOthersRequest.setEndDate(endDate);
		int count = 0;
		try {
			count = securityOthersServiceImpl.queryCount(securityOthersRequest);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return count;
	}

	/**
	 * 创建或修改DetectionPeroidCount(高级版按服务周期统计表)
	 * 
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @param servicePeroidId
	 * @return
	 */
	public void createOrUpdateDetectionPeroidCount(String siteCode, String startDate, String endDate,
			int servicePeroidId) {
		// 1.先查询是否存在统计数据,有更新，无新增
		DetectionPeroidCountRequest request = new DetectionPeroidCountRequest();
		request.setSiteCode(siteCode);
		request.setServicePeroidId(servicePeroidId);
		List<DetectionPeroidCount> listDetectionPeroidCount = detectionPeroidCountServiceImpl.queryList(request);

		boolean isAdd;
		DetectionPeroidCount detectionPeroidCount;
		if (CollectionUtils.isEmpty(listDetectionPeroidCount)) {
			detectionPeroidCount = new DetectionPeroidCount();
			isAdd = true;
		} else {
			detectionPeroidCount = listDetectionPeroidCount.get(0);
			isAdd = false;
		}

		detectionPeroidCount = getNewDetectionPeroidCount(detectionPeroidCount, siteCode, startDate, endDate,
				servicePeroidId);

		if (isAdd) {
			detectionPeroidCountServiceImpl.add(detectionPeroidCount);
		} else {
			detectionPeroidCountServiceImpl.update(detectionPeroidCount);
		}

	}

	/**
	 * 赋值 获取新的DetectionPeroidCount
	 * 
	 * @param detectionPeroidCount
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @param servicePeroidId
	 * @return
	 */
	private DetectionPeroidCount getNewDetectionPeroidCount(DetectionPeroidCount detectionPeroidCount, String siteCode,
			String startDate, String endDate, int servicePeroidId) {
		if (null == detectionPeroidCount) {
			detectionPeroidCount = new DetectionPeroidCount();
		}

		DatabaseInfoRequest databaseRequest = new DatabaseInfoRequest();
		databaseRequest.setSiteCode(siteCode);
		List<DatabaseInfo> databaseInfoList = databaseInfoServiceImpl.queryList(databaseRequest);

		if (CollectionUtils.isEmpty(databaseInfoList)) {
			return null;
		}

		DatabaseInfo databaseInfo = databaseInfoList.get(0);
		String homeUrl = databaseInfo.getJumpUrl();
		if (StringUtils.isEmpty(homeUrl)) {
			homeUrl = databaseInfo.getUrl();
		}
		// 六天前的日期，用于计算连续7天连通性
		String sixDayAgoDate = DateUtils.getNextDay(endDate, "-6");
		if (DateUtils.parseDateTime(startDate).getTime() > DateUtils.parseDateTime(sixDayAgoDate).getTime()) {
			sixDayAgoDate = startDate;
		}
		detectionPeroidCount.setSiteCode(siteCode);
		detectionPeroidCount.setServicePeroidId(servicePeroidId);
		detectionPeroidCount.setStartDate(startDate);
		detectionPeroidCount.setEndDate(endDate);
		// 1.首页连通性 ：检测截止日期前七天内首页打不开的次数占全部监测次数的比例(%)
		detectionPeroidCount.setConnErrorProportion(getConnectionHomeProportion(databaseInfo, sixDayAgoDate, endDate));
		// 2.首页死链数据
		detectionPeroidCount.setLinkHome(getLinkHomeNum(siteCode, startDate, endDate));
		// 3.全站死链数据
		detectionPeroidCount.setLinkAll(getLinkAllNum(siteCode, startDate, endDate, servicePeroidId));
		// 4.首页信息未更新天数是否超过两周
		detectionPeroidCount.setSecurityHome(isSecurityHomeOverTwoWeek(siteCode, homeUrl, endDate));

		// 5.获取栏目不更新数量
		detectionPeroidCount.setSecurityChannel(getSecurityChannelNum(siteCode, startDate, endDate,servicePeroidId));

		// 6。获取空白栏目数量
		detectionPeroidCount.setSecurityBlank(getSecurityBlankNum(siteCode, startDate, endDate, servicePeroidId));

		// 7.获取互动回应差数量
		detectionPeroidCount.setSecurityResponse(getSecurityResponseNum(siteCode, startDate, endDate, servicePeroidId));
		// 8.获取服务不实用数量：办事指南
		detectionPeroidCount.setServiceGuide(getSecurityServiceNum(siteCode, startDate, endDate, servicePeroidId, 1));
		// 9.获取服务不实用数量：附件下载
		detectionPeroidCount
				.setServiceDownload(getSecurityServiceNum(siteCode, startDate, endDate, servicePeroidId, 3));

		// 10. 获取服务不实用:在线系统连通性数量
		detectionPeroidCount.setServiceConn(getConnectionBusinessNum(siteCode, startDate, endDate));

		// 11.获取严重错误:严重错别字数量
		detectionPeroidCount.setSeriousCorrect(getCorrectContentNum(siteCode, startDate, endDate));
		// 12.获取严重错误:类型：1 虚假或伪造内容
		detectionPeroidCount
				.setSeriousUnreal(getSecurityFatalErrorNum(siteCode, startDate, endDate, servicePeroidId, 1));

		// 13.获取严重错误:类型：2 反动、暴力或色情内容
		detectionPeroidCount.setSeriousViolence(getSecurityFatalErrorNum(siteCode, startDate, endDate, servicePeroidId,
				2));

		// 14.获取严重错误:类型： 3 其它
		detectionPeroidCount
				.setSeriousOthers(getSecurityFatalErrorNum(siteCode, startDate, endDate, servicePeroidId, 3));

		// 15.获取其他问题数量
		detectionPeroidCount.setOthers(getSecurityOthersNum(siteCode, startDate, endDate, servicePeroidId));

		return detectionPeroidCount;
	}

	/**
	 * 创建或修改DetectionPeroidCount(高级版按服务周期统计表)
	 * 
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @param servicePeroidId
	 * @return
	 */
	public DetectionPeroidCount getDetectionPeroidCount(String siteCode, String startDate, String endDate,
			int servicePeroidId) {

		// 1.先查询是否存在统计数据,有更新，无新增
		DetectionPeroidCountRequest request = new DetectionPeroidCountRequest();
		request.setSiteCode(siteCode);
		request.setServicePeroidId(servicePeroidId);
		List<DetectionPeroidCount> listDetectionPeroidCount = detectionPeroidCountServiceImpl.queryList(request);

		if(!CollectionUtils.isEmpty(listDetectionPeroidCount)){
			return listDetectionPeroidCount.get(0);
		}
		
		DetectionPeroidCount detectionPeroidCount = getNewDetectionPeroidCount(null, siteCode, startDate, endDate,
				servicePeroidId);
		
		detectionPeroidCountServiceImpl.add(detectionPeroidCount);
		
		return detectionPeroidCount;
	
		
	}

}
