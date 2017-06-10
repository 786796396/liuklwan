package com.ucap.cloud_web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.publics.util.entity.CreateBeanUitls;
import com.publics.util.entity.CreateEntityUitls;
import com.publics.util.page.PageVo;
import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.bizService.BigDataHomeBizService;
import com.ucap.cloud_web.bizService.impl.BigDataHomeBizServiceImpl;
import com.ucap.cloud_web.dto.BigOrgDailyRequest;
import com.ucap.cloud_web.dto.ChannelPointRequest;
import com.ucap.cloud_web.dto.ComboRequest;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.ConnectionBusinessDetailRequest;
import com.ucap.cloud_web.dto.ConnectionChannelDetailRequest;
import com.ucap.cloud_web.dto.ConnectionHomeDetailRequest;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.CorrectContentDetailRequest;
import com.ucap.cloud_web.dto.UpdateContentCountRequest;
import com.ucap.cloud_web.dtoResponse.BigOrgDailyResponse;
import com.ucap.cloud_web.entity.ChannelPoint;
import com.ucap.cloud_web.entity.Combo;
import com.ucap.cloud_web.entity.ConnectionAll;
import com.ucap.cloud_web.entity.ConnectionBusinessDetail;
import com.ucap.cloud_web.entity.ConnectionChannelDetail;
import com.ucap.cloud_web.entity.ConnectionHomeDetail;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.CorrectContentDetail;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.service.IBigOrgDailyService;
import com.ucap.cloud_web.service.IChannelPointService;
import com.ucap.cloud_web.service.IComboService;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IConnectionBusinessDetailService;
import com.ucap.cloud_web.service.IConnectionChannelDetailService;
import com.ucap.cloud_web.service.IConnectionHomeDetailService;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.ICorrectContentDetailService;
import com.ucap.cloud_web.service.ICountNumService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IUpdateContentCountService;
import com.ucap.cloud_web.service.IVerifyUrlLinkService;
import com.ucap.cloud_web.timer.TaskDetectionPeroidCount;
import com.ucap.cloud_web.util.SendEmail;

public class NoraTest extends BaseTest {

	/**
	 * 创建生成实体类，dao层，service层，实体对应的dto,sqlMapper
	 */
	public void testCreateEntityAndBeans() {

		String jdbc = "jdbc:mysql://120.26.247.147:9008/cloud";
		String user = "cloud";
		String passWord = "ecm";
		// String path =
		// "F:/Workspaces/MyEclipse 10/cloud_web/src/main/java/com/ucap/cloud_web/";
		String path = "E:/MyEclipse10.7/space/cloud_web/src/main/java/com/ucap/cloud_web/";
		String author = "zhaoDY";

		List<String> list = new ArrayList<String>();
		list.add("big_data_trend");
		for (String tableName : list) {
			CreateEntityUitls.entity(jdbc, user, passWord, tableName, path,
					author);

			CreateBeanUitls.createBean(jdbc, user, passWord, tableName, path,
					author);

		}

	}

	// -------------------------1.重点栏目检测表测试start--------------------------
	// 1.ChannelPoint,重点栏目检测表
	@Autowired
	private IChannelPointService channelPointServiceImpl;

	// 创建
	public void testAddChannelPoint() {

		ChannelPoint channelPoint = new ChannelPoint();
		channelPoint.setSiteCode("110000");
		channelPoint.setChannelName("政务信息");
		channelPoint.setChannelUrl("http://zhengwu.beijing.gov.cn");
		channelPoint.setJumpPageUrl("http://zhengwu.beijing.gov.cn");
		channelPoint.setDicChannelId(1);
		channelPoint.setDicChannelSonId(3);
		channelPoint.setWebsiteInfoId(1);
		channelPointServiceImpl.add(channelPoint);
	}

	// 单个获取
	public void testGetChannelPoint() {
		ChannelPoint channelPoint = channelPointServiceImpl.get(1);
		System.out.println("channelPoint===" + channelPoint.toString());
	}

	// 修改
	public void testUpdateChannelPoint() {
		ChannelPoint channelPoint = channelPointServiceImpl.get(1);
		channelPoint.setSiteCode("110002");
		channelPoint.setChannelName("政务信息2");
		channelPoint.setChannelUrl("http://zhengwu.beijing.gov.cn2");
		channelPoint.setJumpPageUrl("http://zhengwu.beijing.gov.cn2");
		channelPoint.setDicChannelId(2);
		channelPoint.setDicChannelSonId(2);
		channelPoint.setWebsiteInfoId(2);
		channelPointServiceImpl.update(channelPoint);
	}

	// 删除：直接删除
	public void testDeleteChannelPoint() {
		channelPointServiceImpl.delete(1);
	}

	// 分页查询：模糊查询
	public void testQueryChannelPoint() {
		ChannelPointRequest request = new ChannelPointRequest();
		request.setPageNo(0);
		request.setPageSize(10);
		request.setSiteCode("110000");
		PageVo<ChannelPoint> page = channelPointServiceImpl.query(request);
		List<ChannelPoint> list = page.getData();

		for (ChannelPoint channelPoint : list) {
			System.out.println("channelPoint=====" + channelPoint.toString());
		}
	}

	// count查询
	public void testQueryCountChannelPoint() {
		ChannelPointRequest request = new ChannelPointRequest();
		request.setPageNo(0);
		request.setPageSize(10);
		request.setSiteCode("110000");
		int count = channelPointServiceImpl.queryCount(request);
		System.out.println("count==" + count);
	}

	// List查询
	public void testFindPageChannelPoint() {
		ChannelPointRequest request = new ChannelPointRequest();
		request.setPageNo(0);
		request.setPageSize(10);
		request.setSiteCode("110000");
		List<ChannelPoint> list = channelPointServiceImpl.queryList(request);
		for (ChannelPoint channelPoint : list) {
			System.out.println("channelPoint=====" + channelPoint.toString());
		}
	}

	// -------------------------1.重点栏目检测表测试start--------------------------
	// -------------------------1.套餐表测试start--------------------------
	// 1.Combo,套餐表测试
	@Autowired
	private IComboService comboServiceImpl;

	public void testAddCombo() {
		String indicatorInfo = "1,2,3,5,7,8,10,12,13,17,19,21";
		Combo combo = new Combo();
		combo.setComboCode("D");
		combo.setIndicatorInfo(indicatorInfo);
		// combo.setIsValid(0);
		combo.setRemark("私人定制");
		comboServiceImpl.add(combo);
	}

	public void testGetCombo() {
		Combo combo = comboServiceImpl.get(1);
		System.out.println("combo==" + combo.getRemark());
	}

	public void testQueryListCombo() {

		ComboRequest request = new ComboRequest();
		request.setIsValid(0);

		List<Combo> comboList = comboServiceImpl.queryList(request);

		for (Combo combo : comboList) {
			System.out.println("combo==" + combo.getRemark());
		}
	}

	public void testUpdateCombo() {

		String indicatorInfo = "1,2";

		Combo combo = comboServiceImpl.get(5);
		combo.setComboCode("D2");
		combo.setIndicatorInfo(indicatorInfo);
		combo.setIsValid(1);
		combo.setRemark("私人定制2");
		comboServiceImpl.update(combo);
		System.out.println("combo==" + combo.toString());
	}

	public void testDeleteCombo() {
		int comboId = 5;
		comboServiceImpl.delete(comboId);
	}

	// -------------------------套餐表测试end--------------------------

	// -------------------------2.连通性汇总表测试start--------------------------
	// 2.ConnectionAll 连通性汇总表
	@Autowired
	private IConnectionAllService connectionAllServiceImpl;

	public void testAddConnectionAll() {
		ConnectionAll connectionAll = new ConnectionAll();
		connectionAll.setSiteCode("120000");
		connectionAll.setScanDate("2015-11-03");
		// 类型（1首页面连通性、2、业务系统连通性、3关键栏目连通性）
		connectionAll.setType(3);
		// 名称（业务系统名称，关键栏目名称，空（首页））
		connectionAll.setName("全国政府网站信息2");
		connectionAll.setConnectionSum(100);
		connectionAll.setErrorNum(20);
		connectionAll.setErrorProportion("0.02");
		connectionAll.setSuccessNum(80);
		connectionAll.setSuccessProportion("0.08");
		connectionAll.setUrl("http://test2.com");
		// connectionAll.set
		connectionAllServiceImpl.add(connectionAll);
	}

	public void testGetConnectionAll() {
		ConnectionAll connectionAll = connectionAllServiceImpl.get(1);
		System.out.println("conn====" + connectionAll.toString());
	}

	public void testUpdateConnectionAll() {
		ConnectionAll connectionAll = connectionAllServiceImpl.get(1);
		connectionAll.setSiteCode("120000");
		connectionAll.setScanDate("2015-11-04");
		// 类型（1首页面连通性、2、业务系统连通性、3关键栏目连通性）
		connectionAll.setType(2);
		// 名称（业务系统名称，关键栏目名称，空（首页））
		connectionAll.setName("全国政府网站信息2");
		connectionAll.setConnectionSum(1002);
		connectionAll.setErrorNum(20);
		connectionAll.setErrorProportion("0.012");
		connectionAll.setSuccessNum(902);
		connectionAll.setSuccessProportion("0.092");
		connectionAll.setUrl("http://test.com2");
		connectionAllServiceImpl.update(connectionAll);
		System.out.println("conn====" + connectionAll.toString());
	}

	public void testDeleteConnectionAll() {
		connectionAllServiceImpl.delete(1);
	}

	public void testQueryListConnectionAll() {
		ConnectionAllRequest request = new ConnectionAllRequest();
		request.setType(3);
		request.setSiteCode("110000");
		request.setScanDate("2015-11-04");
		List<ConnectionAll> listAll = connectionAllServiceImpl
				.queryList(request);
		for (ConnectionAll connectionAll : listAll) {
			System.out.println("connectionAll==" + connectionAll.toString());
		}
	}

	// -------------------------2.连通性汇总表测试end--------------------------

	// -------------------------3.业务系统连通性测试start--------------------------
	// 3.ConnectionBusinessDetail,业务系统连通性
	@Autowired
	private IConnectionBusinessDetailService connectionBusinessDetailServiceImpl;

	public void testAddConnectionBusinessDetail() {
		ConnectionBusinessDetail connectionBusinessDetail = new ConnectionBusinessDetail();
		connectionBusinessDetail.setConnectionAllId(2);
		connectionBusinessDetail.setSiteCode("110000");
		connectionBusinessDetail.setScanDate("2015-11-04");
		connectionBusinessDetail.setSystemName("北京市政府网站");
		connectionBusinessDetail.setState(1);// 1:超时，0：不超时
		connectionBusinessDetail.setQuestionCode("200");
		connectionBusinessDetail.setQuestionDescribe("服务成功处理的请求");
		connectionBusinessDetail.setScanTime("2015.11.04 10:50:35");
		connectionBusinessDetail.setUrl("http://beijing.com");
		connectionBusinessDetailServiceImpl.add(connectionBusinessDetail);
	}

	public void testGetConnectionBusinessDetail() {
		ConnectionBusinessDetail connectionBusinessDetail = connectionBusinessDetailServiceImpl
				.get(6);
		System.out.println("connectionBusinessDetail=="
				+ connectionBusinessDetail);
	}

	public void testUpdateConnectionBusinessDetail() {
		ConnectionBusinessDetail connectionBusinessDetail = connectionBusinessDetailServiceImpl
				.get(6);
		System.out.println("connectionBusinessDetail======before"
				+ connectionBusinessDetail);
		connectionBusinessDetail.setSiteCode("1100002");
		connectionBusinessDetail.setScanDate("2015-11-05");
		connectionBusinessDetail.setSystemName("北京市政府网站2");
		connectionBusinessDetail.setState(0);// 1:超时，0：不超时
		connectionBusinessDetail.setQuestionCode("2002");
		connectionBusinessDetail.setQuestionDescribe("服务成功处理的请求2");
		connectionBusinessDetail.setScanTime("2015.11.04 10:52:35");
		connectionBusinessDetail.setUrl("http://beijing2.com");
		connectionBusinessDetailServiceImpl.update(connectionBusinessDetail);
		System.out.println("connectionBusinessDetail======after"
				+ connectionBusinessDetail);
	}

	public void testQueryListConnectionBusinessDetail() {

		ConnectionBusinessDetailRequest request = new ConnectionBusinessDetailRequest();

		request.setSiteCode("110000");
		request.setScanDate("2015-11-01");
		request.setState(1);
		List<ConnectionBusinessDetail> list = connectionBusinessDetailServiceImpl
				.queryList(request);

		for (ConnectionBusinessDetail connectionBusinessDetail : list) {
			System.out.println("connectionBusinessDetail======"
					+ connectionBusinessDetail);
		}

	}

	public void testQueryCountConnectionBusinessDetail() {

		ConnectionBusinessDetailRequest request = new ConnectionBusinessDetailRequest();
		request.setSiteCode("110000");
		request.setScanDate("2015-11-01");
		request.setState(1);
		int count = connectionBusinessDetailServiceImpl.queryCount(request);
		System.out.println("count===" + count);

	}

	public void testQueryConnectionBusinessDetail() {

		ConnectionBusinessDetailRequest request = new ConnectionBusinessDetailRequest();
		request.setSiteCode("110000");
		request.setScanDate("2015-11-01");
		request.setState(1);
		request.setPageNo(0);
		request.setPageSize(10);
		PageVo<ConnectionBusinessDetail> page = connectionBusinessDetailServiceImpl
				.query(request);

		List<ConnectionBusinessDetail> list = page.getData();

		for (ConnectionBusinessDetail connectionBusinessDetail : list) {
			System.out.println("connectionBusinessDetail======"
					+ connectionBusinessDetail);
		}
	}

	// -------------------------3.业务系统连通性测试end--------------------------

	// -------------------------4.关键栏目连通性测试start--------------------------
	// 4.ConnectionChannelDetail,关键栏目连通性
	@Autowired
	private IConnectionChannelDetailService connectionChannelDetailServiceImpl;

	public void testAddConnectionChannelDetail() {
		ConnectionChannelDetail ConnectionChannelDetail = new ConnectionChannelDetail();
		ConnectionChannelDetail.setConnectionAllId(3);
		ConnectionChannelDetail.setSiteCode("110000");
		ConnectionChannelDetail.setScanDate("2015-11-04");
		ConnectionChannelDetail.setChannelName("北京市政府网站");
		ConnectionChannelDetail.setState(1);// 1:超时，0：不超时
		ConnectionChannelDetail.setQuestionCode("200");
		ConnectionChannelDetail.setQuestionDescribe("服务成功处理的请求");
		ConnectionChannelDetail.setScanTime("2015.11.04 10:50:35");
		ConnectionChannelDetail.setUrl("http://beijing.com");
		connectionChannelDetailServiceImpl.add(ConnectionChannelDetail);
	}

	public void testGetConnectionChannelDetail() {
		ConnectionChannelDetail ConnectionChannelDetail = connectionChannelDetailServiceImpl
				.get(1);
		System.out.println("ConnectionChannelDetail=="
				+ ConnectionChannelDetail);
	}

	public void testUpdateConnectionChannelDetail() {
		ConnectionChannelDetail ConnectionChannelDetail = connectionChannelDetailServiceImpl
				.get(3);
		System.out.println("ConnectionChannelDetail======before"
				+ ConnectionChannelDetail);
		ConnectionChannelDetail.setSiteCode("1100002");
		ConnectionChannelDetail.setScanDate("2015-11-05");
		ConnectionChannelDetail.setChannelName("北京市政府网站2");
		ConnectionChannelDetail.setState(0);// 1:超时，0：不超时
		ConnectionChannelDetail.setQuestionCode("2002");
		ConnectionChannelDetail.setQuestionDescribe("服务成功处理的请求2");
		ConnectionChannelDetail.setScanTime("2015.11.04 10:52:35");
		ConnectionChannelDetail.setUrl("http://beijing2.com");
		connectionChannelDetailServiceImpl.update(ConnectionChannelDetail);
		System.out.println("ConnectionChannelDetail======after"
				+ ConnectionChannelDetail);
	}

	public void testQueryListConnectionChannelDetail() {

		ConnectionChannelDetailRequest request = new ConnectionChannelDetailRequest();

		request.setSiteCode("110000");
		request.setScanDate("2015-11-01");
		request.setState(1);
		List<ConnectionChannelDetail> list = connectionChannelDetailServiceImpl
				.queryList(request);

		for (ConnectionChannelDetail ConnectionChannelDetail : list) {
			System.out.println("ConnectionChannelDetail======"
					+ ConnectionChannelDetail);
		}

	}

	public void testQueryCountConnectionChannelDetail() {

		ConnectionChannelDetailRequest request = new ConnectionChannelDetailRequest();
		request.setSiteCode("110000");
		request.setScanDate("2015-11-01");
		request.setState(1);
		int count = connectionChannelDetailServiceImpl.queryCount(request);
		System.out.println("count===" + count);

	}

	public void testQueryConnectionChannelDetail() {

		ConnectionChannelDetailRequest request = new ConnectionChannelDetailRequest();
		request.setSiteCode("110000");
		request.setScanDate("2015-11-04");
		request.setState(1);
		request.setPageNo(0);
		request.setPageSize(10);
		PageVo<ConnectionChannelDetail> page = connectionChannelDetailServiceImpl
				.query(request);

		List<ConnectionChannelDetail> list = page.getData();

		for (ConnectionChannelDetail ConnectionChannelDetail : list) {
			System.out.println("ConnectionChannelDetail======"
					+ ConnectionChannelDetail);
		}
	}

	// -------------------------4.关键栏目连通性测试end--------------------------

	// -------------------------5.首页连通性测试start--------------------------
	// 5.ConnectionHomeDetail,首页连通性
	@Autowired
	private IConnectionHomeDetailService connectionHomeDetailServiceImpl;

	public void testAddConnectionHomeDetail() {
		ConnectionHomeDetail ConnectionHomeDetail = new ConnectionHomeDetail();
		ConnectionHomeDetail.setConnectionAllId(3);
		ConnectionHomeDetail.setSiteCode("110000");
		ConnectionHomeDetail.setScanDate("2015-11-04");
		ConnectionHomeDetail.setState(1);// 1:超时，0：不超时
		ConnectionHomeDetail.setQuestionCode("200");
		ConnectionHomeDetail.setQuestionDescribe("服务成功处理的请求");
		ConnectionHomeDetail.setScanTime("2015.11.04 10:50:35");
		connectionHomeDetailServiceImpl.add(ConnectionHomeDetail);
	}

	public void testGetConnectionHomeDetail() {
		ConnectionHomeDetail ConnectionHomeDetail = connectionHomeDetailServiceImpl
				.get(1);
		System.out.println("ConnectionHomeDetail==" + ConnectionHomeDetail);
	}

	public void testUpdateConnectionHomeDetail() {
		ConnectionHomeDetail ConnectionHomeDetail = connectionHomeDetailServiceImpl
				.get(3);
		System.out.println("ConnectionHomeDetail======before"
				+ ConnectionHomeDetail);
		ConnectionHomeDetail.setSiteCode("1100002");
		ConnectionHomeDetail.setScanDate("2015-11-05");
		ConnectionHomeDetail.setState(0);// 1:超时，0：不超时
		ConnectionHomeDetail.setQuestionCode("2002");
		ConnectionHomeDetail.setQuestionDescribe("服务成功处理的请求2");
		ConnectionHomeDetail.setScanTime("2015.11.04 10:52:35");
		connectionHomeDetailServiceImpl.update(ConnectionHomeDetail);
		System.out.println("ConnectionHomeDetail======after"
				+ ConnectionHomeDetail);
	}

	public void testQueryListConnectionHomeDetail() {

		ConnectionHomeDetailRequest request = new ConnectionHomeDetailRequest();

		request.setSiteCode("110000");
		request.setScanDate("2015-11-04");
		request.setState(1);
		List<ConnectionHomeDetail> list = connectionHomeDetailServiceImpl
				.queryList(request);

		for (ConnectionHomeDetail ConnectionHomeDetail : list) {
			System.out.println("ConnectionHomeDetail======"
					+ ConnectionHomeDetail);
		}

	}

	public void testQueryCountConnectionHomeDetail() {

		ConnectionHomeDetailRequest request = new ConnectionHomeDetailRequest();
		request.setSiteCode("110000");
		request.setScanDate("2015-11-04");
		request.setState(1);
		int count = connectionHomeDetailServiceImpl.queryCount(request);
		System.out.println("count===" + count);

	}

	public void testQueryConnectionHomeDetail() {

		ConnectionHomeDetailRequest request = new ConnectionHomeDetailRequest();
		request.setSiteCode("110000");
		request.setScanDate("2015-11-04");
		request.setState(1);
		request.setPageNo(0);
		request.setPageSize(10);
		PageVo<ConnectionHomeDetail> page = connectionHomeDetailServiceImpl
				.query(request);

		List<ConnectionHomeDetail> list = page.getData();

		for (ConnectionHomeDetail connectionHomeDetail : list) {
			System.out.println("connectionHomeDetail======"
					+ connectionHomeDetail);
		}
	}

	// -------------------------5.首页连通性测试end--------------------------

	// -------------------------6 内容正确性统计测试start--------------------------

	// 内容正确性统计
	// 添加：内容正确性统计
	public void testAddCorrectContentInfo() {
		// CorrectContentInfo correctContentInfo = new CorrectContentInfo();
		//
		// // 内容正确性类型（0：确定，1：疑似，2：可能）
		// correctContentInfo.setCorrectType(2);
		// correctContentInfo.setServicePeriodId(1);
		// correctContentInfo.setSiteCode("110000");
		// correctContentInfo.setWrongNum(10);
		// correctContentInfoServiceImpl.add(correctContentInfo);
	}

	// 获取：内容正确性统计
	public void testGetCorrectContentInfo() {
		// CorrectContentInfo correctContentInfo = correctContentInfoServiceImpl
		// .get(1);
		// System.out.println("correctContentInfo===="
		// + correctContentInfo.toString());
	}

	// 更新：内容正确性统计
	public void testUpdateCorrectContentInfo() {
		// CorrectContentInfo correctContentInfo = correctContentInfoServiceImpl
		// .get(1);
		// System.out.println("correctContentInfo====before===="
		// + correctContentInfo.toString());
		//
		// // 内容正确性类型（0：确定，1：疑似，2：可能）
		// correctContentInfo.setCorrectType(2);
		// correctContentInfo.setServicePeriodId(2);
		// correctContentInfo.setSiteCode("120000");
		// correctContentInfo.setWrongNum(20);
		// correctContentInfoServiceImpl.update(correctContentInfo);
		// System.out.println("correctContentInfo====end===="
		// + correctContentInfo.toString());
	}

	// 删除：内容正确性统计

	// -------------------------6 内容正确性统计测试end--------------------------

	// -------------------------7 内容正确性详情测试start--------------------------
	// 内容正确性详情
	@Autowired
	private ICorrectContentDetailService correctContentDetailServiceImpl;

	// 内容正确性详情
	// 添加：内容正确性详情
	public void testAddCorrectContentDetail() {
		CorrectContentDetail correctContentDetail = new CorrectContentDetail();

		correctContentDetail.setCorrectContentInfo(1);
		// 内容正确性类型（0：确定，1：疑似，2：可能）
		correctContentDetail.setCorrectType(2);
		// correctContentDetail.setServicePeriodId(1);
		correctContentDetail.setSiteCode("110000");
		correctContentDetail.setProblemDesc("总理写成总经理");
		correctContentDetail.setUrl("http://Url.test.com");
		correctContentDetail.setImgUrl("http://imgUrl.test.com");
		correctContentDetailServiceImpl.add(correctContentDetail);
	}

	// 获取：内容正确性详情
	public void testGetCorrectContentDetail() {
		CorrectContentDetail correctContentDetail = correctContentDetailServiceImpl
				.get(2);
		System.out.println("correctContentDetail===="
				+ correctContentDetail.toString());
	}

	// 更新：内容正确性详情
	public void testUpdateCorrectContentDetail() {
		CorrectContentDetail correctContentDetail = correctContentDetailServiceImpl
				.get(1);
		System.out.println("correctContentDetail====before===="
				+ correctContentDetail.toString());

		correctContentDetail.setCorrectContentInfo(2);
		// 内容正确性类型（0：确定，1：疑似，2：可能）
		correctContentDetail.setCorrectType(1);
		// correctContentDetail.setServicePeriodId(22);
		correctContentDetail.setSiteCode("110000");
		correctContentDetail.setProblemDesc("总理写成总经理2");
		correctContentDetail.setUrl("http://Url.test.com2");
		correctContentDetail.setImgUrl("http://imgUrl.test.com2");
		correctContentDetailServiceImpl.update(correctContentDetail);
		System.out.println("correctContentDetail====end===="
				+ correctContentDetail.toString());
	}

	// 删除：内容正确性详情
	public void testDeleteCorrectContentDetail() {
		correctContentDetailServiceImpl.delete(3);
	}

	// 分页查询：内容正确性详情
	public void testQueryCorrectContenDetail() {
		CorrectContentDetailRequest request = new CorrectContentDetailRequest();
		request.setPageNo(0);
		request.setPageSize(10);
		request.setCorrectType(2);
		request.setServicePeriodId(1);
		request.setSiteCode("110000");
		PageVo<CorrectContentDetail> page = correctContentDetailServiceImpl
				.query(request);
		List<CorrectContentDetail> list = page.getData();
		for (CorrectContentDetail correctContentDetail : list) {
			System.out.println("correctContentDetail==="
					+ correctContentDetail.toString());
		}
	}

	// 查询数量：内容正确性详情
	public void testQueryCountCorrectContenDetail() {
		CorrectContentDetailRequest request = new CorrectContentDetailRequest();
		request.setCorrectType(2);
		request.setServicePeriodId(1);
		request.setSiteCode("110000");
		int count = correctContentDetailServiceImpl.queryCount(request);
		System.out.println("count====" + count);

	}

	// 查询List：内容正确性详情
	public void testQueryListCorrectContenDetail() {
		CorrectContentDetailRequest request = new CorrectContentDetailRequest();
		request.setCorrectType(2);
		request.setServicePeriodId(1);
		request.setSiteCode("110000");

		List<CorrectContentDetail> list = correctContentDetailServiceImpl
				.queryList(request);
		for (CorrectContentDetail correctContentDetail : list) {
			System.out.println("correctContentDetail==="
					+ correctContentDetail.toString());
		}
	}

	// -------------------------7 内容正确性统计测试end--------------------------

	// -------------------------8 客户信息测试start--------------------------
	// 客户信息详

	// --------------------------8 客户信息测试 end--------------------------

	@Autowired
	private IVerifyUrlLinkService verifyUrlLinkServiceImpl;

	// 连通性校验
	public void testVerifyUrlLink() throws Exception {
		boolean flag = verifyUrlLinkServiceImpl
				.verifyUrlLink("http://www.beijing.gov.cn");

		System.out.println("flag=====" + flag);
	}

	@Autowired
	private IUpdateContentCountService updateContentCountServiceImpl;

	public void testUpdateContentCount() {

		UpdateContentCountRequest request = new UpdateContentCountRequest();

		updateContentCountServiceImpl.queryCountAnalyzeLine(request);
	}

	@Autowired
	private ICountNumService countNumServiceImpl;

	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;// 基础信息表

	public void testCountNum() {
		String siteCode = "bm34040003";
		String startDate = "2016-09-06";
		String endDate = "2016-09-11";
		// 六天前的日期，用于计算连续7天连通性
		String sixDayAgoDate = DateUtils.getNextDay(endDate, "-6");
		System.out.println("sixDayAgoDate==" + sixDayAgoDate);
		// if (DateUtils.parseDateTime(startDate).getTime() >
		// DateUtils.parseDateTime(sixDayAgoDate).getTime()) {
		// sixDayAgoDate = startDate;
		// }
		int servicePeriodId = 1099;
		// DatabaseInfoRequest databaseRequest = new DatabaseInfoRequest();
		// databaseRequest.setSiteCode(siteCode);
		// List<DatabaseInfo> databaseInfoList =
		// databaseInfoServiceImpl.queryList(databaseRequest);
		// DatabaseInfo databaseInfo = databaseInfoList.get(0);
		// String homeUrl = databaseInfo.getJumpUrl();
		// if (StringUtils.isEmpty(homeUrl)) {
		// homeUrl = databaseInfo.getUrl();
		// }
		// String connectionHome =
		// countNumServiceImpl.getConnectionHomeProportion(databaseInfo,
		// sixDayAgoDate, endDate);
		//
		// System.out.println("connectionHome==="+connectionHome);

		// System.out.println(countNumServiceImpl.getLinkHomeNum(siteCode,
		// startDate, endDate));

		// System.out.println(countNumServiceImpl.getLinkAllNum(siteCode,
		// startDate, endDate, servicePeriodId));

		// System.out.println(countNumServiceImpl.isSecurityHomeOverTwoWeek(siteCode,
		// homeUrl, endDate));

		System.out.println(countNumServiceImpl.getSecurityChannelNum(siteCode,
				startDate, endDate, servicePeriodId));

		// System.out.println(countNumServiceImpl.getSecurityBlankNum(siteCode,
		// startDate, endDate, servicePeriodId));

		// System.out.println(countNumServiceImpl.getSecurityResponseNum(siteCode,
		// startDate, endDate, servicePeriodId));

		// System.out.println(countNumServiceImpl.getSecurityServiceNum(siteCode,
		// startDate, endDate, servicePeriodId,1));

		// System.out.println(countNumServiceImpl.getSecurityServiceNum(siteCode,
		// startDate, endDate, servicePeriodId,3));

		// System.out.println(countNumServiceImpl.getConnectionBusinessNum(siteCode,
		// startDate, endDate));

		// System.out.println(countNumServiceImpl.getCorrectContentNum(siteCode,
		// startDate, endDate));

		// System.out.println(countNumServiceImpl.getSecurityFatalErrorNum(siteCode,
		// startDate, endDate,servicePeriodId,2));
		// System.out.println(countNumServiceImpl.getSecurityOthersNum(siteCode,
		// startDate, endDate,servicePeriodId));

		// System.out.println(countNumServiceImpl.getConnectionHomeData(siteCode,
		// sixDayAgoDate, endDate));

	}

	@Autowired
	TaskDetectionPeroidCount taskDetectionPeroidCount;

	public void testTaskDetectionPeroidCount() {
		taskDetectionPeroidCount.start();
	}

	@Autowired
	private IContractInfoService contractInfoServiceImpl;

	public void testContractInfo() {
		// 2.删除抽查合同表：笔记删除
		ContractInfoRequest contractInfoRequest = new ContractInfoRequest();
		contractInfoRequest.setSiteCode("440000");
		contractInfoRequest.setContractCodeSpotLike("14_1_1");
		List<ContractInfo> listSpotContractInfo = contractInfoServiceImpl
				.queryList(contractInfoRequest);
		if (!CollectionUtils.isEmpty(listSpotContractInfo)) {
			// 抽查合同：与spotCheckSchedule一一对应
			ContractInfo contractInfoSpot = listSpotContractInfo.get(0);
			contractInfoSpot.setExecuteStatus(-1);
			contractInfoServiceImpl.update(contractInfoSpot);
		}

	}

	/**
	 * 获取上级组织单位编码
	 * 
	 * @param siteCode
	 * @return
	 */
	public void testGetUpperOrgCode() {

		// String a = databaseSpotInfoServiceImpl.getUpperOrgCode("4401030005");
		//
		// System.out.println(a);

		// Map<String, Integer> map = new HashMap<String, Integer>();
		// String siteCode = "4401000001";
		// DatabaseSpotInfoRequest databaseSpotInfoRequest = new
		// DatabaseSpotInfoRequest();
		// databaseSpotInfoRequest.setSiteCode(siteCode);
		// databaseSpotInfoRequest.setPageSize(Integer.MAX_VALUE);
		// List<DatabaseSpotInfo> listDatabaseSpotInfo =
		// databaseSpotInfoServiceImpl
		// .queryList(databaseSpotInfoRequest);
		//
		// if (!CollectionUtils.isEmpty(listDatabaseSpotInfo)) {
		//
		// for (DatabaseSpotInfo databaseSpotInfo : listDatabaseSpotInfo) {
		//
		// System.out.println("databaseSpotInfo.getParentId()==="
		// + databaseSpotInfo.getParentId());
		// int parentId = databaseSpotInfo.getParentId();
		// int level = 0;
		// while (parentId != 0) {
		// System.out.println("parentId===" + parentId);
		// DatabaseSpotInfo pDatabase = databaseSpotInfoServiceImpl
		// .get(parentId);
		// if (pDatabase == null) {
		// break;
		// }
		// System.out.println("org===" + pDatabase.getOrgSiteCode()
		// + ",siteCode==" + pDatabase.getSiteCode());
		// parentId = pDatabase.getParentId();
		// level += 1;
		// }
		//
		// map.put(databaseSpotInfo.getOrgSiteCode(), level);
		// }
		//
		// Map sorted_map = MapUtil.sortByValue(map);
		//
		// System.out.println("unsorted map: " + map);
		//
		// // unsorted map: {D=67.3, A=99.5, B=67.4, C=67.4}
		// System.out.println("sorted map: " + sorted_map);
		//
		// }

	}

	public void testEmailSend2() throws Exception {

		Map<Object, Object> map = new HashMap<Object, Object>();
		// emailSendServiceImpl.sendEmail();

		boolean resultSend = SendEmail.sendEmail("云监管日常监测详情报告-", "yujing.ftl",
				map, "yinna317@qq.com");
	}

	@Autowired
	private BigDataHomeBizService bigDataHomeBizServiceImpl;

	@Autowired
	private UrlAdapterVar urlAdapterVar;

	public void testGetOrgMTaskoverview() throws Exception {

		// bigDataHomeBizServiceImpl.getOrgMTaskoverview("440000");

		// 创建文件
		 bigDataHomeBizServiceImpl.createBigHomeJsonData("440000");

		String orgSiteCode = "440000";
		// 读取文件
		String filePath = urlAdapterVar.getCloubWebPaths() + "/json/"
				+ orgSiteCode + ".json";

		 //bigDataHomeBizServiceImpl.createBigDataHomeJson();

		// JSONArray jsonArray =
		// bigDataHomeBizServiceImpl.parseBySiteCodeAndKey("440000",
		// "indexCountLine");

		// System.out.println("json=="+jsonArray.toString());

		//
		// JsonParser parser = new JsonParser(); // 创建JSON解析器
		// JsonArray jsonArray2 = (JsonArray) parser.parse(new FileReader(
		// filePath)); // 创建JsonObject对象
		//
		// JsonObject jsonObject = jsonArray2.get(0).getAsJsonObject();
		// //jsonObject.get();
		// JsonArray array=jsonObject.getAsJsonArray("listEarlyDetail");
		//
		//
		// for (int i = 0; i < array.size(); i++) {
		//
		// JsonObject jsonObject2 = array.get(0).getAsJsonObject();
		// EarlyDetail earlyDetail = new EarlyDetail();
		// earlyDetail.setSiteCodeName("jsonObject2");
		// jsonObject2.get("");
		//
		// }
		// System.out.println("jsonObject=="+jsonObject);

		/*
		 * JSONParser parser = new JSONParser(new FileReader( filePath));
		 * JSONArray jsonArray2 = (JSONArray)parser;
		 */

		/*
		 * String data= FileUtils.readFileUTF8(filePath);
		 * System.out.println(data);
		 */
		// JSONObject jsonObj = JSONObject.fromObject(data);

		// JSONArray arrayA=JSONArray.fromObject(data);
		// System.out.println(arrayA.toString());
		//
		// JSONObject jsonObject = arrayA.getJSONObject(0);
		// jsonObject.get("listEarlyDetail");
		// JSONArray arrayB=jsonObject.getJSONArray("listEarlyDetail");
		// JSONObject jsonObject2 = arrayB.getJSONObject(0);
		// //EarlyDetail earlyDetail = (EarlyDetail)
		// jsonObject2.toBean(jsonObject2, EarlyDetail.class);
		// EarlyDetail earlyDetail = (EarlyDetail)
		// jsonObject2.toBean(jsonObject2, EarlyDetail.class);
		// System.out.println("ea=="+earlyDetail.toString());

	}

	public void testParseSon() throws Exception {
		// 读取文件
		// String filePath = urlAdapterVar.getCloubWebPaths() + "/json/"
		// + "440000" + ".json";
		//
		// String data = FileUtils.readFileUTF8(filePath);
		// // 转成json数组
		// JSONArray arrayA = JSONArray.fromObject(data);
		// // 获取第一个
		// JSONObject jsonObject = arrayA.getJSONObject(0);
		//
		// System.out.println(jsonObject.toString());
		//
		// String key="listEarlyDetail";
		// //jsonObject.getJSONArray(key);
		// JSONObject jsonObject2=jsonObject.getJSONObject(key);
		// System.out.println(jsonObject2.toString());
		// JSONArray arrayB = jsonObject2.getJSONArray("list");
		//
		// List<EarlyDetail> earlyDetailList = new ArrayList<EarlyDetail>();
		// EarlyDetail earlyDetail = new EarlyDetail();
		// earlyDetail.setRemark("测试");
		// earlyDetailList.add(earlyDetail);
		// arrayB.addAll(earlyDetailList);
		//
		// Map<String, Object> resultMap=new HashMap<String, Object>();
		// resultMap.put("list",arrayB);
		// resultMap.put("success","true");
		//
		// System.out.println("22222--------"+JSONObject.fromObject(resultMap).toString());

		JSONObject objectBroadcast = bigDataHomeBizServiceImpl
				.parseBySiteCodeAndKey("440000",
						BigDataHomeBizServiceImpl.BROADCAST);

		JSONArray arrayB = objectBroadcast
				.getJSONArray(BigDataHomeBizServiceImpl.BROADCAST_LIST);

		List<EarlyDetail> listEarlyDetail = bigDataHomeBizServiceImpl
				.getBroadcastConnection("440000");
		arrayB.addAll(listEarlyDetail);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("list", arrayB);
		resultMap.put("success", "true");

		System.out.println(JSONObject.fromObject(resultMap).toString());
	}

	public void testGetJsonResult() {
		JSONObject object = bigDataHomeBizServiceImpl
				.packageIndexCountLine("440000");

		System.out.println(object.toString());
	}

	@Autowired
	IBigOrgDailyService bigOrgDailyServiceImpl;

	public void testQueryBigOrgDaily() {

		String orgSiteCode = "440000";
		String scanDate = "20170306";
		BigOrgDailyRequest rRequest = new BigOrgDailyRequest();
		rRequest.setOrgSiteCode(orgSiteCode);
		rRequest.setCountDay(scanDate);
		List<BigOrgDailyResponse> list = bigOrgDailyServiceImpl
				.getOrgData(rRequest);

		// for (BigOrgDailyResponse bigOrgDailyResponse : list) {
		// System.out.println("ssssssss=="+bigOrgDailyResponse.toString());
		// }
	}
}
