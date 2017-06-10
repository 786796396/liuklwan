package com.ucap.cloud_web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.publics.util.entity.CreateBeanUitls;
import com.publics.util.entity.CreateEntityUitls;
import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.SecurityBlankDetailRequest;
import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dto.SecurityChannelRequest;
import com.ucap.cloud_web.dto.SecurityResponseRequest;
import com.ucap.cloud_web.dto.SecurityServcieRequest;
import com.ucap.cloud_web.dto.TaskAllRequest;
import com.ucap.cloud_web.dto.TimedTaskLogRequest;
import com.ucap.cloud_web.entity.DicChannel;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.entity.SecurityBlankDetail;
import com.ucap.cloud_web.entity.SecurityBlankInfo;
import com.ucap.cloud_web.entity.SecurityChannel;
import com.ucap.cloud_web.entity.SecurityResponse;
import com.ucap.cloud_web.entity.SecurityServcie;
import com.ucap.cloud_web.entity.TaskAll;
import com.ucap.cloud_web.entity.TaskDaily;
import com.ucap.cloud_web.entity.TimedTaskLog;
import com.ucap.cloud_web.service.IDicChannelService;
import com.ucap.cloud_web.service.IEarlyDetailService;
import com.ucap.cloud_web.service.ISecurityBlankDetailService;
import com.ucap.cloud_web.service.ISecurityBlankInfoService;
import com.ucap.cloud_web.service.ISecurityChannelService;
import com.ucap.cloud_web.service.ISecurityResponseService;
import com.ucap.cloud_web.service.ISecurityServcieService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.service.ITaskAllService;
import com.ucap.cloud_web.service.ITaskDailyService;
import com.ucap.cloud_web.service.ITimedTaskLogService;
import com.ucap.cloud_web.util.SendEmail;

public class SunJiangTest extends BaseTest {
	@Autowired
	private ISecurityBlankDetailService securityBlankDetailServiceImpl;
	@Autowired
	private ISecurityBlankInfoService securityBlankInfoServiceImpl;
	@Autowired
	private ISecurityChannelService securityChannelServiceImpl;
	@Autowired
	private ISecurityResponseService securityResponseServiceImpl;
	@Autowired
	private ISecurityServcieService securityServcieServiceImpl;
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private ITaskAllService taskAllServiceImpl;
	@Autowired
	private ITaskDailyService taskDailyServiceImpl;
	@Autowired
	private ITimedTaskLogService timedTaskLogServiceImpl;

	/**
	 * 创建生成实体类，dao层，service层，实体对应的dto,sqlMapper
	 */
	public void testCreateEntityAndBeans() {
//		
//		 try {  
//	            HttpClient client = new HttpClient();  
//	  
//	            Protocol easyHttps1 = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);  
//	            Protocol.registerProtocol("https", easyHttps1);  
//	  
//	            HostConfiguration hc = new HostConfiguration();  
//	            hc.setHost("127.0.0.1", 80, easyHttps1);  
//	  
//	            String url = "https://localhost:8080/";  
//	            PostMethod method = new PostMethod(url);  
//	            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");  
//	            int t = client.executeMethod(hc, method);  
//	            InputStream is = method.getResponseBodyAsStream();  
//	            BufferedReader br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "utf-8"));  
//	            StringBuffer response = new StringBuffer();  
//	            String readLine;  
//	            while (((readLine = br.readLine()) != null)) {  
//	                response.append(readLine);  
//	            }  
//	            System.out.print(response);  
//	            System.out.print(t);  
//	        } catch (Exception e) {  
//	            System.out.print(e);  
//	        }  
//	    }  

//		String jdbc = "jdbc:mysql://192.168.10.41:3306/cloud";
//		String user = "root";
//		String passWord = "toptime";
//		// String tableName = "channel_find";
////		String path = "C:/Users/Administrator/Desktop/index/";
//		String path = "E:/ucap/svn/cloud/cloud_web/src/main/java/com/ucap/cloud_web/";
//		String author = "SunJiang";
//
//		List<String> list = new ArrayList<String>();
//		list.add("database_link");
//
//		// list.add("security_channel");
//		/*
//		 * list.add("security_blank_detail"); list.add("security_blank_info");
//		 * list.add("security_home"); list.add("security_response");
//		 * list.add("security_servcie"); list.add("service_period");
//		 * list.add("task_all"); list.add("task_daily");
//		 * list.add("timed_task_log");
//		 * 
//		 * ``list.add("website_info"); ``list.add("spot_check_info");
//		 * ``list.add("spot_check_result"); ``list.add("spot_check_schedule");
//		 * 
//		 * //sunJQ 7 list.add("update_channel_detail");
//		 * list.add("update_channel_info"); list.add("update_content_count");
//		 * list.add("update_home_detail");
//		 */
//		// 18
//
//		for (String tableName : list) {
//			CreateEntityUitls.entity(jdbc, user, passWord, tableName, path,
//					author);
//
//			CreateBeanUitls.createBean(jdbc, user, passWord, tableName, path,
//					author);
//
//		}

	}
	@Autowired
	private IEarlyDetailService earlyDetailServiceImpl;
	
	@Autowired
	private IDicChannelService dicChannelServiceImpl;
	/**
	 * @Description: 测试预警发邮箱
	 * @author sunjiang --- 2016-3-18下午2:08:31
	 */
	public void testSendEmail(){
		try {
			
			EarlyDetailRequest earlyDetailRequest = new EarlyDetailRequest();
			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			queryOrderList.add(new QueryOrder("create_time", QueryOrderType.ASC));
			
			earlyDetailRequest.setQueryOrderList(queryOrderList);
			earlyDetailRequest.setSiteCode("4305000037");
			earlyDetailRequest.setPageSize(Integer.MAX_VALUE);
			
			
			List<EarlyDetail> edList = earlyDetailServiceImpl.queryList(earlyDetailRequest);
			
			ArrayList<EarlyDetail> datalist = new ArrayList<EarlyDetail>();
			
			if(!CollectionUtils.isEmpty(edList)){
				
				for (EarlyDetail earlyDetail : edList) {
					
					int dicChannelId = earlyDetail.getDicChannelId();
					
					DicChannel dicChannel = dicChannelServiceImpl.get(dicChannelId);
					if(dicChannel!=null){
						
						earlyDetail.setQuestionName(dicChannel.getChannelName());
					}
					
					datalist.add(earlyDetail);
				}
				
				
			}
			
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("webNum", 1);
			map.put("newEarlNum", 2);
			map.put("nowTime", DateUtils.getNowStandardStr());
			map.put("edList", datalist);
			map.put("siteName", "AAAAA");
			map.put("siteCode", "1111111");
			
			
			boolean resultSend = SendEmail.sendEmail("AAAAA"+"预警通知", "yujing.ftl", map, "sunjiang@ucap.com.cn");
			System.out.println(resultSend);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: security_blank_detail add
	 * @author sunjiang --- 2015年10月30日上午10:14:36
	 */
	public void testAddSecurityBlankDetail() {
		// add

		SecurityBlankDetail securityBlankDetail = new SecurityBlankDetail();
		securityBlankDetail.setSecurityBlankInfo(11);
		securityBlankDetail.setServicePeriodId(22);
		securityBlankDetail.setSiteCode("110000");
		securityBlankDetail.setChannelName("央视网");
		securityBlankDetail.setUrl("www.baidu.com");
		securityBlankDetail.setPath("www.path.com");
		securityBlankDetail.setImgUrl("www.image.com");
		securityBlankDetailServiceImpl.add(securityBlankDetail);
		System.out.println("SecurityBlankDetail~~~~~~~~add~~~~~~~~~~");
	}

	/**
	 * @Description: security_blank_detail
	 * @author sunjiang --- 2015年10月30日上午10:14:36
	 */
	public void testUpateSecurityBlankDetail() {
		SecurityBlankDetail securityBlankDetail = securityBlankDetailServiceImpl
				.get(1);
		securityBlankDetail.setSecurityBlankInfo(1313123);
		securityBlankDetail.setServicePeriodId(222);
		securityBlankDetail.setSiteCode("110000");
		securityBlankDetail.setChannelName("央视网");
		securityBlankDetail.setUrl("www.baidu.com");
		securityBlankDetail.setPath("www.path.com");
		securityBlankDetail.setImgUrl("www.image.com");
		securityBlankDetailServiceImpl.update(securityBlankDetail);
		System.out.println("SecurityBlankDetail~~~~~~~~update~~~~~~~~~~");
	}

	/**
	 * @Description: security_blank_detail
	 * @author sunjiang --- 2015年10月30日上午10:14:36
	 */
	public void testDeleteSecurityBlankDetail() {
		securityBlankDetailServiceImpl.delete(4);
		System.out.println("SecurityBlankDetail~~~~~~~~delete~~~~~~~~~~");
	}

	/**
	 * @Description: security_blank_detail
	 * @author sunjiang --- 2015年10月30日上午10:41:28
	 */
	public void testGetSecurityBlankDetail() {
		SecurityBlankDetail securityBlankDetail = securityBlankDetailServiceImpl
				.get(1);
		System.out.println(securityBlankDetail.getId() + "~~"
				+ securityBlankDetail.getChannelName() + "~~"
				+ securityBlankDetail.getImgUrl());
	}

	/**
	 * @Description: security_blank_detail
	 * @author sunjiang --- 2015年10月30日上午10:53:02
	 */
	public void testQueryListSecurityBlankDetail() {
		SecurityBlankDetailRequest request = new SecurityBlankDetailRequest();
		request.setPageNo(1);
		List<SecurityBlankDetail> queryList = securityBlankDetailServiceImpl
				.queryList(request);
		for (int i = 0; i < queryList.size(); i++) {
			SecurityBlankDetail securityBlankDetail = queryList.get(i);
			int id = securityBlankDetail.getId();
			String channelName = securityBlankDetail.getChannelName();
			Date createTime = securityBlankDetail.getCreateTime();
			String imgUrl = securityBlankDetail.getImgUrl();
			String path = securityBlankDetail.getPath();
			String siteCode = securityBlankDetail.getSiteCode();
			String url = securityBlankDetail.getUrl();
			System.out
					.println("id:" + id + "channelName:" + channelName
							+ ",createTime:" + createTime + ",imgUrl:" + imgUrl
							+ ",path:" + path + ",siteCode:" + siteCode
							+ ",url:" + url);
		}
	}

	/**
	 * @Description: security_blank_detail query
	 * @author sunjiang --- 2015年11月5日下午5:16:49
	 */
	public void testQuerySecurityBlankDetail() {
		SecurityBlankDetailRequest request = new SecurityBlankDetailRequest();
		request.setChannelName("开普");
		request.setServicePeriodId(111);
		request.setSiteCode("11");
		PageVo<SecurityBlankDetail> query = securityBlankDetailServiceImpl
				.query(request);
		List<SecurityBlankDetail> data = query.getData();
		for (SecurityBlankDetail securityBlankDetail : data) {
			int id = securityBlankDetail.getId();
			int securityBlankInfo = securityBlankDetail.getSecurityBlankInfo();
			int servicePeriodId = securityBlankDetail.getServicePeriodId();
			String channelName = securityBlankDetail.getChannelName();
			String siteCode = securityBlankDetail.getSiteCode();
			System.out.println(id);
			System.out.println(securityBlankInfo);
			System.out.println(servicePeriodId);
			System.out.println(siteCode);
			System.out.println(channelName);
			System.out.println("~~~~~~~~~~~~~~~~");
		}
	}

	/**
	 * @Description: security_blank_info
	 * @author sunjiang --- 2015年10月30日上午11:42:17
	 */
	public void testAddSecurityBlankInfo() {
		SecurityBlankInfo securityBlankInfo = new SecurityBlankInfo();
		securityBlankInfo.setSiteCode("110000");
		securityBlankInfo.setServicePeriodId(2);
		securityBlankInfo.setBlankNum(100);
		securityBlankInfoServiceImpl.add(securityBlankInfo);
		System.out.println("add");
	}

	/**
	 * @Description: security_blank_info update
	 * @author sunjiang --- 2015年10月30日下午2:22:53
	 */
	public void testUpdateSecurityBlankInfo() {
		SecurityBlankInfo securityBlankInfo = securityBlankInfoServiceImpl
				.get(1);
		securityBlankInfo.setSiteCode("220000");
		securityBlankInfo.setServicePeriodId(2);
		securityBlankInfo.setBlankNum(100);
		securityBlankInfoServiceImpl.update(securityBlankInfo);
		System.out.println("update");
	}

	/**
	 * @Description: security_blank_info delete
	 * @author sunjiang --- 2015年10月30日下午2:24:27
	 */
	public void testDeleteSecurityBlankInfo() {
		securityBlankInfoServiceImpl.delete(2);
		System.out.println("delete");
	}

	/**
	 * @Description: security_blank_info get
	 * @author sunjiang --- 2015年10月30日下午2:26:31
	 */
	public void testGetSecurityBlankInfo() {
		SecurityBlankInfo securityBlankInfo = securityBlankInfoServiceImpl
				.get(1);
		int id = securityBlankInfo.getId();
		String siteCode = securityBlankInfo.getSiteCode();
		int servicePeriodId = securityBlankInfo.getServicePeriodId();
		int blankNum = securityBlankInfo.getBlankNum();
		Date createTime = securityBlankInfo.getCreateTime();
		System.out.println("id:" + id + "siteCode:" + siteCode
				+ ",servicePeriodId:" + servicePeriodId + ",blankNum:"
				+ blankNum + ",createTime:" + createTime);
	}

	/**
	 * @Description: security_blank_info queryList
	 * @author sunjiang --- 2015年10月30日下午2:29:22
	 */
	public void testQueryListSecurityBlankInfo() {
		SecurityBlankInfoRequest request = new SecurityBlankInfoRequest();
		request.setPageNo(1);
		request.setPageSize(10);
		List<SecurityBlankInfo> queryList = securityBlankInfoServiceImpl
				.queryList(request);
		for (int i = 0; i < queryList.size(); i++) {
			SecurityBlankInfo securityBlankInfo = queryList.get(i);
			int id = securityBlankInfo.getId();
			String siteCode = securityBlankInfo.getSiteCode();
			int servicePeriodId = securityBlankInfo.getServicePeriodId();
			int blankNum = securityBlankInfo.getBlankNum();
			Date createTime = securityBlankInfo.getCreateTime();
			System.out.println("id:" + id + "siteCode:" + siteCode
					+ ",servicePeriodId:" + servicePeriodId + ",blankNum:"
					+ blankNum + ",createTime:" + createTime);
		}
	}

	/**
	 * @Description: security_channel add
	 * @author sunjiang --- 2015年10月30日下午2:32:45
	 */
	public void testAddSecurityChannel() {
		SecurityChannel securityChannel = new SecurityChannel();
		securityChannel.setDicChannelId(2);
		securityChannel.setSiteCode("110000");
		securityChannel.setScanDate("2015-10-30");
		securityChannel.setLastModifyTime("2015-10-31");
		securityChannel.setChannelName("国家政府网");
		securityChannel.setUrl("www.baidu.com");
		securityChannel.setImgUrl("www.img.com");
		securityChannelServiceImpl.add(securityChannel);
		System.out.println("add");
	}

	/**
	 * @Description: security_channel update
	 * @author sunjiang --- 2015年10月30日下午2:37:44
	 */
	public void testUpdateSecurityChannel() {
		SecurityChannel securityChannel = securityChannelServiceImpl.get(1);
		securityChannel.setDicChannelId(2);
		securityChannel.setSiteCode("110000");
		securityChannel.setScanDate("2015-10-30");
		securityChannel.setLastModifyTime("2015-10-22");
		securityChannel.setChannelName("国家政府网");
		securityChannel.setUrl("www.baidu.com");
		securityChannel.setImgUrl("www.img.com");
		securityChannelServiceImpl.update(securityChannel);
		System.out.println("update");
	}

	/**
	 * @Description: security_channel delete
	 * @author sunjiang --- 2015年10月30日下午2:40:12
	 */
	public void testDeleteSecurityChannel() {
		securityChannelServiceImpl.delete(2);
		System.out.println("delete");
	}

	/**
	 * @Description: security_channel get
	 * @author sunjiang --- 2015年10月30日下午2:41:04
	 */
	public void testGetSecurityChannel() {
		SecurityChannel securityChannel = securityChannelServiceImpl.get(1);
		int dicChannelId = securityChannel.getDicChannelId();
		String siteCode = securityChannel.getSiteCode();
		String scanDate = securityChannel.getScanDate();
		String channelName = securityChannel.getChannelName();
		String url = securityChannel.getUrl();
		String lastModifyTime = securityChannel.getLastModifyTime();
		String imgUrl = securityChannel.getImgUrl();
		Date createTime = securityChannel.getCreateTime();
		System.out.println(dicChannelId);
		System.out.println(siteCode);
		System.out.println(scanDate);
		System.out.println(channelName);
		System.out.println(url);
		System.out.println(lastModifyTime);
		System.out.println(imgUrl);
		System.out.println(createTime);
	}

	/**
	 * @Description: security_channel queryList
	 * @author sunjiang --- 2015年10月30日下午2:45:14
	 */
	public void testQueryListSecurityChannel() {
		SecurityChannelRequest request = new SecurityChannelRequest();
		request.setPageNo(1);
		request.setPageSize(10);
		List<SecurityChannel> queryList = securityChannelServiceImpl
				.queryList(request);
		for (int i = 0; i < queryList.size(); i++) {
			SecurityChannel securityChannel = queryList.get(i);
			int dicChannelId = securityChannel.getDicChannelId();
			String siteCode = securityChannel.getSiteCode();
			String scanDate = securityChannel.getScanDate();
			String channelName = securityChannel.getChannelName();
			String url = securityChannel.getUrl();
			String lastModifyTime = securityChannel.getLastModifyTime();
			String imgUrl = securityChannel.getImgUrl();
			Date createTime = securityChannel.getCreateTime();
			System.out.println(dicChannelId);
			System.out.println(siteCode);
			System.out.println(scanDate);
			System.out.println(channelName);
			System.out.println(url);
			System.out.println(lastModifyTime);
			System.out.println(imgUrl);
			System.out.println(createTime);
			System.out.println("~~~~~~~~~~~~~~~");
		}
	}

	/**
	 * @Description: security_channel query
	 * @author sunjiang --- 2015年11月5日下午2:20:17
	 */
	public void testQuerySecurityChannel() {
		SecurityChannelRequest request = new SecurityChannelRequest();
		request.setChannelName("开普");
		request.setPageNo(4);
		request.setPageSize(3);
		PageVo<SecurityChannel> query = securityChannelServiceImpl
				.query(request);
		List<SecurityChannel> data = query.getData();
		for (SecurityChannel securityChannel : data) {
			int id = securityChannel.getId();
			String channelName = securityChannel.getChannelName();
			System.out.println(id);
			System.out.println(channelName);
			System.out.println("~~~~~~~~~");
		}
		int pageNo = query.getPageNo();
		int pageSize = query.getPageSize();
		int pageTotal = query.getPageTotal();
		int recordSize = query.getRecordSize();
		Boolean isHasNext = query.isHasNext();
		System.out.println("pageNo：" + pageNo);
		System.out.println("pageSize：" + pageSize);
		System.out.println("pageTotal：" + pageTotal);
		System.out.println("recordSize：" + recordSize);
		System.out.println("isHasNext：" + isHasNext);
	}

	/**
	 * @Description: security_home add
	 * @author sunjiang --- 2015年10月30日下午2:47:55
	 */
	public void testAddSecurityHome() {
//		SecurityHome securityHome = new SecurityHome();
//		securityHome.setSiteCode("220000");
//		securityHome.setSiteName("网站");
//		securityHome.setUrl("www.url.com");
//		securityHome.setImgUrl("www.img.com");
////		securityHomeServiceImpl.add(securityHome);
//		System.out.println("add");
	}

	/**
	 * @Description: security_home get
	 * @author sunjiang --- 2015年10月30日下午2:54:10
	 */
	public void testGetSecurityHome() {
//		SecurityHome securityHome = securityHomeServiceImpl.get(1);
//		String siteCode = securityHome.getSiteCode();
//		String siteName = securityHome.getSiteName();
//		String url = securityHome.getUrl();
//		String imgUrl = securityHome.getImgUrl();
//		Date createTime = securityHome.getCreateTime();
//		System.out.println(siteCode);
//		System.out.println(siteName);
//		System.out.println(url);
//		System.out.println(imgUrl);
//		System.out.println(createTime);
	}

	/**
	 * @Description: security_home queryList
	 * @author sunjiang --- 2015年10月30日下午2:58:24
	 */
	public void testQueryListSecurityHome() {
//		SecurityHomeRequest request = new SecurityHomeRequest();
//		request.setPageNo(1);
//		request.setPageSize(10);
//		List<SecurityHome> queryList = securityHomeServiceImpl
//				.queryList(request);
//		for (int i = 0; i < queryList.size(); i++) {
//			SecurityHome securityHome = queryList.get(i);
//			String siteCode = securityHome.getSiteCode();
//			String siteName = securityHome.getSiteName();
//			String url = securityHome.getUrl();
//			String imgUrl = securityHome.getImgUrl();
//			Date createTime = securityHome.getCreateTime();
//			System.out.println(siteCode);
//			System.out.println(siteName);
//			System.out.println(url);
//			System.out.println(imgUrl);
//			System.out.println(createTime);
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		}
	}

	/**
	 * @Description: security_response add
	 * @author sunjiang --- 2015年10月30日下午3:00:00
	 */
	public void testAddSecurityResponse() {
		SecurityResponse securityResponse = new SecurityResponse();
		securityResponse.setDicChannelId(2);
		securityResponse.setSiteCode("110000");
		securityResponse.setServicePeriodId(22);
		securityResponse.setChannelName("政府网");
		securityResponse.setProblemTypeId(1);
		securityResponse.setProblemDesc("问题描述");
		securityResponse.setImgUrl("www.baidu.com");
		securityResponseServiceImpl.add(securityResponse);
		System.out.println("add~~~~~~~~~");
	}

	/**
	 * @Description: security_response update
	 * @author sunjiang --- 2015年10月30日下午3:06:08
	 */
	public void testUpdateSecurityResponse() {
		SecurityResponse securityResponse = securityResponseServiceImpl.get(1);
		securityResponse.setDicChannelId(33);
		securityResponse.setSiteCode("333000");
		securityResponse.setServicePeriodId(22);
		securityResponse.setChannelName("政府网");
		securityResponse.setProblemTypeId(1);
		securityResponse.setProblemDesc("问题描述");
		securityResponse.setImgUrl("www.baidu.com");
		securityResponseServiceImpl.update(securityResponse);
		System.out.println("update~~~~~~~~~");
	}

	/**
	 * @Description: security_response delete
	 * @author sunjiang --- 2015年10月30日下午3:07:28
	 */
	public void testDeleteSecurityResponse() {
		securityResponseServiceImpl.delete(3);
		System.out.println("delete~~~~~~~~~");
	}

	/**
	 * @Description: security_response get
	 * @author sunjiang --- 2015年10月30日下午3:08:30
	 */
	public void testGetSecurityResponse() {
		SecurityResponse securityResponse = securityResponseServiceImpl.get(1);
		int dicChannelId = securityResponse.getDicChannelId();
		String siteCode = securityResponse.getSiteCode();
		int servicePeriodId = securityResponse.getServicePeriodId();
		String channelName = securityResponse.getChannelName();
		int problemTypeId = securityResponse.getProblemTypeId();
		String problemDesc = securityResponse.getProblemDesc();
		String imgUrl = securityResponse.getImgUrl();
		Date createTime = securityResponse.getCreateTime();
		System.out.println(dicChannelId);
		System.out.println(siteCode);
		System.out.println(channelName);
		System.out.println(servicePeriodId);
		System.out.println(problemTypeId);
		System.out.println(problemDesc);
		System.out.println(imgUrl);
		System.out.println(createTime);
	}

	/**
	 * @Description: security_response queryList
	 * @author sunjiang --- 2015年10月30日下午3:19:02
	 */
	public void testQueryListSecurityResponse() {
		SecurityResponseRequest request = new SecurityResponseRequest();
		request.setPageNo(1);
		request.setPageSize(10);
		List<SecurityResponse> queryList = securityResponseServiceImpl
				.queryList(request);
		for (int i = 0; i < queryList.size(); i++) {
			SecurityResponse securityResponse = queryList.get(i);
			int dicChannelId = securityResponse.getDicChannelId();
			String siteCode = securityResponse.getSiteCode();
			int servicePeriodId = securityResponse.getServicePeriodId();
			String channelName = securityResponse.getChannelName();
			int problemTypeId = securityResponse.getProblemTypeId();
			String problemDesc = securityResponse.getProblemDesc();
			String imgUrl = securityResponse.getImgUrl();
			Date createTime = securityResponse.getCreateTime();
			System.out.println(dicChannelId);
			System.out.println(siteCode);
			System.out.println(channelName);
			System.out.println(servicePeriodId);
			System.out.println(problemTypeId);
			System.out.println(problemDesc);
			System.out.println(imgUrl);
			System.out.println(createTime);
			System.out.println("~~~~~~~~~~~~~~~");
		}
	}

	/**
	 * @Description: security_servcie add
	 * @author sunjiang --- 2015年10月30日下午3:19:32
	 */
	public void testAddSecurityServcie() {
		SecurityServcie securityServcie = new SecurityServcie();
		securityServcie.setServicePeriodId(1);
		securityServcie.setSiteCode("110000");
		securityServcie.setServiceItem("item");
		securityServcie.setProblemTypeId(2);
		securityServcie.setProblemDesc("问题描述");
		securityServcie.setUrl("www.baidu.com");
		securityServcie.setImgUrl("www.img.com");
		securityServcieServiceImpl.add(securityServcie);
		System.out.println("add~~~~~~~~~~");
	}

	/**
	 * @Description: security_servcie update
	 * @author sunjiang --- 2015年10月30日下午3:27:45
	 */
	public void testUpdateSecurityServcie() {
		SecurityServcie securityServcie = securityServcieServiceImpl.get(1);
		securityServcie.setServicePeriodId(1);
		securityServcie.setSiteCode("330000");
		securityServcie.setServiceItem("333");
		securityServcie.setProblemTypeId(333);
		securityServcie.setProblemDesc("33问题描述");
		securityServcie.setUrl("www.baidu.com");
		securityServcie.setImgUrl("www.img.com");
		securityServcieServiceImpl.update(securityServcie);
		System.out.println("update~~~~~~~~~~");
	}

	/**
	 * @Description: security_servcie delete
	 * @author sunjiang --- 2015年10月30日下午3:29:17
	 */
	public void testDeleteSecurityServcie() {
		securityServcieServiceImpl.delete(3);
		System.out.println("delete~~~~~~~~~~");
	}

	/**
	 * @Description: security_servcie get
	 * @author sunjiang --- 2015年10月30日下午3:30:57
	 */
	public void testGetSecurityServcie() {
		SecurityServcie securityServcie = securityServcieServiceImpl.get(1);
		Date createTime = securityServcie.getCreateTime();
		int id = securityServcie.getId();
		String imgUrl = securityServcie.getImgUrl();
		String problemDesc = securityServcie.getProblemDesc();
		int problemTypeId = securityServcie.getProblemTypeId();
		String serviceItem = securityServcie.getServiceItem();
		int servicePeriodId = securityServcie.getServicePeriodId();
		String siteCode = securityServcie.getSiteCode();
		String url = securityServcie.getUrl();
		System.out.println(createTime);
		System.out.println(id);
		System.out.println(imgUrl);
		System.out.println(problemDesc);
		System.out.println(problemTypeId);
		System.out.println(serviceItem);
		System.out.println(servicePeriodId);
		System.out.println(siteCode);
		System.out.println(url);
		System.out.println("get~~~~~~~~~~");
	}

	/**
	 * @Description: security_servcie queryList
	 * @author sunjiang --- 2015年10月30日下午3:37:48
	 */
	public void testQueryListSecurityServcie() {
		SecurityServcieRequest request = new SecurityServcieRequest();
		request.setPageNo(1);
		request.setPageSize(10);
		List<SecurityServcie> queryList = securityServcieServiceImpl
				.queryList(request);
		for (int i = 0; i < queryList.size(); i++) {
			SecurityServcie securityServcie = queryList.get(i);
			Date createTime = securityServcie.getCreateTime();
			int id = securityServcie.getId();
			String imgUrl = securityServcie.getImgUrl();
			String problemDesc = securityServcie.getProblemDesc();
			int problemTypeId = securityServcie.getProblemTypeId();
			String serviceItem = securityServcie.getServiceItem();
			int servicePeriodId = securityServcie.getServicePeriodId();
			String siteCode = securityServcie.getSiteCode();
			String url = securityServcie.getUrl();
			System.out.println(createTime);
			System.out.println(id);
			System.out.println(imgUrl);
			System.out.println(problemDesc);
			System.out.println(problemTypeId);
			System.out.println(serviceItem);
			System.out.println(servicePeriodId);
			System.out.println(siteCode);
			System.out.println(url);
			System.out.println("queryList~~~~~~~~~~");
		}
	}


	/**
	 * @Description: service_period delete
	 * @author sunjiang --- 2015年10月30日下午4:12:59
	 */
	public void testDeleteServicePeriod() {
		servicePeriodServiceImpl.delete(3);
		System.out.println("delete~~~~~~~~~~~~~~~");
	}


	/**
	 * @Description: task_all add
	 * @author sunjiang --- 2015年11月3日下午4:51:12
	 */
	public void testAddTaskAll() {
		TaskAll taskAll = new TaskAll();
		taskAll.setWebsiteInfoId(2);
		taskAll.setServicePeriodId(2);
		taskAll.setStatus(1);
		taskAll.setFetchTimes(1);
		taskAll.setStartTime(new Date());
		taskAll.setReturnTaskId("2");
		taskAll.setStartTime(new Date());
		taskAll.setCreateTime(new Date());
		taskAll.setFetchTime(new Date());
		taskAllServiceImpl.add(taskAll);
		System.out.println("add~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	/**
	 * @Description: task_all update
	 * @author sunjiang --- 2015年11月3日下午5:10:12
	 */
	public void testUpdateTaskAll() {
		TaskAll taskAll = taskAllServiceImpl.get(1);
		taskAll.setWebsiteInfoId(22222222);
		taskAllServiceImpl.update(taskAll);
		System.out.println("update~~~~~~~~~~");
	}

	/**
	 * @Description: task_all get
	 * @author sunjiang --- 2015年11月3日下午5:19:13
	 */
	public void testGetTaskAll() {
		TaskAll taskAll = taskAllServiceImpl.get(1);
		Date createTime = taskAll.getCreateTime();
		Date fetchTime = taskAll.getFetchTime();
		int fetchTimes = taskAll.getFetchTimes();
		int id = taskAll.getId();
		String returnTaskId = taskAll.getReturnTaskId();
		int servicePeriodId = taskAll.getServicePeriodId();
		int status = taskAll.getStatus();
		Date startTime = taskAll.getStartTime();
		System.out.println(createTime);
		System.out.println(fetchTime);
		System.out.println(fetchTimes);
		System.out.println(id);
		System.out.println(returnTaskId);
		System.out.println(servicePeriodId);
		System.out.println(status);
		System.out.println(startTime);
		System.out.println("get~~~~~~~~~~");
	}

	/**
	 * @Description: task_all queryList
	 * @author sunjiang --- 2015年11月3日下午5:21:50
	 */
	public void testQueryListTaskAll() {
		TaskAllRequest request = new TaskAllRequest();
		request.setPageNo(1);
		request.setPageSize(10);
		List<TaskAll> queryList = taskAllServiceImpl.queryList(request);
		for (int i = 0; i < queryList.size(); i++) {
			TaskAll taskAll = queryList.get(i);
			Date createTime = taskAll.getCreateTime();
			Date fetchTime = taskAll.getFetchTime();
			int fetchTimes = taskAll.getFetchTimes();
			int id = taskAll.getId();
			String returnTaskId = taskAll.getReturnTaskId();
			int servicePeriodId = taskAll.getServicePeriodId();
			int status = taskAll.getStatus();
			Date startTime = taskAll.getStartTime();
			System.out.println(createTime);
			System.out.println(fetchTime);
			System.out.println(fetchTimes);
			System.out.println(id);
			System.out.println(returnTaskId);
			System.out.println(servicePeriodId);
			System.out.println(status);
			System.out.println(startTime);
			System.out.println("queryList~~~~~~~~~~" + i);
		}
	}

	/**
	 * @Description: task_daily add
	 * @author sunjiang --- 2015年11月3日下午5:24:20
	 */
	public void testAddTaskDaily() {
		TaskDaily taskDaily = new TaskDaily();
		taskDaily.setWebsiteInfoId(1);
		taskDaily.setSiteCode("110000");
		taskDaily.setStartDate(new Date());
		taskDaily.setEndDate(new Date());
		/*taskDaily.setStatus(1);
		taskDaily.setTimes(1);
		taskDaily.setModifyTime(new Date());*/
		taskDailyServiceImpl.add(taskDaily);
		System.out.println("add~~~~~~~~~~~~~~~~~~~");
	}

	/**
	 * @Description: task_daily update
	 * @author sunjiang --- 2015年11月3日下午5:30:23
	 */
	public void testUpdateTaskDaily() {
		TaskDaily taskDaily = taskDailyServiceImpl.get(1);
		taskDaily.setWebsiteInfoId(1);
		taskDaily.setSiteCode("2222222");
		taskDaily.setStartDate(new Date());
	/*	taskDaily.setEndDate(new Date());
		taskDaily.setStatus(1);
		taskDaily.setTimes(1);
		taskDaily.setModifyTime(new Date());*/
		taskDailyServiceImpl.update(taskDaily);
		System.out.println("update~~~~~~~~~~~~~~~~~~~");
	}

	/**
	 * @Description: task_daily delete
	 * @author sunjiang --- 2015年11月3日下午5:40:06
	 */
	public void testDeleteTaskDaily() {
		taskDailyServiceImpl.delete(1);
		System.out.println("delete~~~~~~~~~~~~~~~~~~~");
	}



	

	/**
	 * @Description: timed_task_log add
	 * @author sunjiang --- 2015年11月3日下午5:47:44
	 * @throws SQLException
	 * @throws SerialException
	 */
	public void testAddTimedTaskLog() throws SerialException, SQLException {
		TimedTaskLog timedTaskLog = new TimedTaskLog();
		timedTaskLog.setSiteCode("11000");
		Date now = DateUtils.getNow();
		String formatStandardDateTime = DateUtils.formatStandardDateTime(now);
//		timedTaskLog.setBeginTime(formatStandardDateTime);
//		timedTaskLog.setEndTime(formatStandardDateTime);
		timedTaskLog.setStatus(1);
		timedTaskLog.setType(1);
		timedTaskLog.setErrMesg("adasda");
		timedTaskLogServiceImpl.add(timedTaskLog);
		System.out.println("add~~~~~~~~~~~~~~~");
	}

	/**
	 * @Description: timed_task_log query
	 * @author sunjiang --- 2015年11月4日下午5:29:38
	 */
	public void testQueryTimedTaskLog() {
		TimedTaskLogRequest request = new TimedTaskLogRequest();
		PageVo<TimedTaskLog> page = timedTaskLogServiceImpl.query(request);
		List<TimedTaskLog> data = page.getData();
		for (TimedTaskLog timedTaskLog : data) {
			//String beginTime = timedTaskLog.getBeginTime();
			int id = timedTaskLog.getId();
			String errMesg = timedTaskLog.getErrMesg();
			String siteCode = timedTaskLog.getSiteCode();
			//System.out.println(beginTime);
			System.out.println(id);
			System.out.println(errMesg);
			System.out.println(siteCode);
		}
	}

}
