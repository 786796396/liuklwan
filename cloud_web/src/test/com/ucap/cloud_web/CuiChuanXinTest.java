package com.ucap.cloud_web;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.publics.util.entity.CreateBeanUitls;
import com.publics.util.entity.CreateEntityUitls;
import com.publics.util.page.PageVo;
import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.dto.DicIndicatorRequest;
import com.ucap.cloud_web.dto.DicInteractProblemRequest;
import com.ucap.cloud_web.dto.DicServiceUnuserfulRequest;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.EarlyInfoRequest;
import com.ucap.cloud_web.dto.LinkAllDetailRequest;
import com.ucap.cloud_web.dto.LinkAllInfoRequest;
import com.ucap.cloud_web.dto.LinkHomeAvailableRequest;
import com.ucap.cloud_web.dto.LinkHomeTrendRequest;
import com.ucap.cloud_web.dto.ReportManageLogRequest;
import com.ucap.cloud_web.dto.ReportWordResultRequest;
import com.ucap.cloud_web.entity.DicIndicator;
import com.ucap.cloud_web.entity.DicInteractProblem;
import com.ucap.cloud_web.entity.DicServiceUnuserful;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.entity.EarlyInfo;
import com.ucap.cloud_web.entity.LinkAllDetail;
import com.ucap.cloud_web.entity.LinkAllInfo;
import com.ucap.cloud_web.entity.LinkHomeAvailable;
import com.ucap.cloud_web.entity.LinkHomeTrend;
import com.ucap.cloud_web.entity.ReportManageLog;
import com.ucap.cloud_web.entity.ReportWordResult;
import com.ucap.cloud_web.service.IDicIndicatorService;
import com.ucap.cloud_web.service.IDicInteractProblemService;
import com.ucap.cloud_web.service.IDicServiceUnuserfulService;
import com.ucap.cloud_web.service.IEarlyDetailService;
import com.ucap.cloud_web.service.IEarlyInfoService;
import com.ucap.cloud_web.service.ILinkAllDetailService;
import com.ucap.cloud_web.service.ILinkAllInfoService;
import com.ucap.cloud_web.service.ILinkHomeAvailableService;
import com.ucap.cloud_web.service.ILinkHomeTrendService;
import com.ucap.cloud_web.service.IReportManageLogService;
import com.ucap.cloud_web.service.IReportWordResultService;


public class CuiChuanXinTest extends BaseTest {
	
	@Autowired
	private IDicIndicatorService dicIndicatorServiceImpl;


	@Autowired
	private  IDicInteractProblemService dicInteractProblemServiceImpl;
	
	@Autowired
	private  IDicServiceUnuserfulService dicServiceUnuserfulServiceImpl;

	
	@Autowired
	private  IEarlyDetailService earlyDetailServiceImpl;
	@Autowired
	private  IEarlyInfoService earlyInfoServiceImpl;
	@Autowired
	private  ILinkAllDetailService linkAllDetailServiceImpl;
	@Autowired
	private  ILinkAllInfoService linkAllInfoServiceImpl;
	@Autowired
	private  ILinkHomeAvailableService linkHomeAvailableServiceImpl;
	@Autowired
	private  ILinkHomeTrendService linkHomeTrendServiceImpl;
	@Autowired
	private  IReportManageLogService reportManageLogServiceImpl;
	@Autowired
	private  IReportWordResultService reportWordResultServiceImpl;
	
	
	
	/**
	 * 创建生成实体类，dao层，service层，实体对应的dto,sqlMapper
	 */
	public void testCreateEntityAndBeans() {

		String jdbc = "jdbc:mysql://192.168.1.150:3306/cloud";
		String user = "cloud";
		String passWord = "ecm";
		String path = "D:/workSpace/ucapWorkSpace/cloud_web/src/main/java/com/ucap/cloud_web/";
		String author = "cuichx";

		List<String> list = new ArrayList<String>();
		list.add("yun_open_info");
		for (String tableName : list) {
			System.out.println("CreateEntityUitls.entity begin");
			CreateEntityUitls.entity(jdbc, user, passWord, tableName, path,
					author);
			System.out.println("CreateEntityUitls.entity end");
			
			System.out.println("CreateEntityUitls.createBean begin");
			CreateBeanUitls.createBean(jdbc, user, passWord, tableName, path,
					author);
			System.out.println("CreateEntityUitls.createBean end");
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>dic_indicator 指标码表>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	*//**
	 * @Description: 指标码表添加数据
	 * @author cuichx --- 2015-10-30上午11:50:51
	 *//*
	public void testAddDicIndicator(){
		for(int i=0;i<30;i++){
			DicIndicator dicIndicator=new DicIndicator();
			dicIndicator.setParentId(1);
			dicIndicator.setIndicatorName("首页连通性");
			dicIndicator.setPeriod(0);
			dicIndicator.setRemark("考察网站一天内首页打不开的次数，每次访问时间≥15秒即为打不开一次。连续2次不能访问即触发红色预警");
			
			System.out.println("testAddDicIndicator   begin");
			dicIndicatorServiceImpl.add(dicIndicator);
			System.out.println("testAddDicIndicator   end");
		}

	}
	
	*//**
	 * @Description: 根据主键查询指标码表
	 * @author cuichx --- 2015-10-30上午11:51:59
	 *//*
	public void testGetIndicator(){
		
		System.out.println("testGetIndicator  begin");
		DicIndicator dicIndicator=dicIndicatorServiceImpl.get(2);
		System.out.println(dicIndicator.getCreateTime());
		System.out.println("testGetIndicator   end");
		
	}
	*//**
	 * @Description: 更新指标码表
	 * @author cuichx --- 2015-10-29下午4:56:05
	 *//*
	public void testUpdateDicIndicator() {
		
		System.out.println("testUpdateDicIndicator  begin");
		DicIndicator  dicIndicator=new DicIndicator();
		dicIndicator.setId(6);
		//dicIndicator.setIndicatorName("内容正确性");
		dicIndicator.setRemark("内容更新与分析+20分");
		dicIndicator.setModifyTime(new Date());
		
		dicIndicatorServiceImpl.update(dicIndicator);
		System.out.println("testUpdateDicIndicator  end");
		
	}
	
	*//**
	 * @Description: 根据id删除栏目类别码表中的记录
	 * @author cuichx --- 2015-10-29下午4:56:05
	 *//*
	public void testDeleteIndicator() {
		
		System.out.println("testDeleteIndicator begin");
		dicIndicatorServiceImpl.delete(6);
		System.out.println("testDeleteIndicator end");
		
	}
	*//**
	 * @Description:根据条件查询栏目类别码表数据 
	 * @author cuichx --- 2015-10-30上午10:00:14
	 *//*
	public void testQueryListDicIndicator(){
		System.out.println("testQueryListDicIndicator begin");
		DicIndicatorRequest request=new DicIndicatorRequest();
		
		request.setParentId(1);
		
		List<DicIndicator> list=new ArrayList<DicIndicator>();
		list=dicIndicatorServiceImpl.queryList(request);
		for (DicIndicator dicIndicator : list) {
			System.out.println(dicIndicator.getId()+","+dicIndicator.getIndicatorName()+","+DateUtils.formatStandardDateTime(dicIndicator.getModifyTime()));
		}
		System.out.println("testQueryListDicIndicator end");
	}
	
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>dic_interact_problem 互动回应差问题类型码表>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	
	*//**
	 * @Description: 互动回应差码表添加数据
	 * @author cuichx --- 2015-10-30上午11:50:51
	 *//*
	public void testAddDicInteractProbblem(){
		
		DicInteractProblem DicInteractProblem=new DicInteractProblem();
		DicInteractProblem.setInteractProblemCode(2);
		//问题类型说明：0栏目未开设、1栏目不可用、21年内无有效信息和留言、3留言超过3个月未回复、41年内未开展活动、51年内开展活动次数少、6其他"
		DicInteractProblem.setRemark("1年内无有效信息和留言");
		
		System.out.println("testAddDicInteractProblem   begin");
		dicInteractProblemServiceImpl.add(DicInteractProblem);
		System.out.println("testAddDicInteractProblem   end");
	}
	
	*//**
	 * @Description: 根据主键查询互动回应差码表
	 * @author cuichx --- 2015-10-30上午11:51:59
	 *//*
	public void testGetDicInteractProbblem(){
		
		System.out.println("testGetDicInteractProblem  begin");
		DicInteractProblem DicInteractProblem=dicInteractProblemServiceImpl.get(2);
		System.out.println(DicInteractProblem.getInteractProblemCode()+","+DicInteractProblem.getRemark());
		System.out.println("testGetDicInteractProbblem   end");
		
	}
	*//**
	 * @Description: 更新互动回应差码表
	 * @author cuichx --- 2015-10-29下午4:56:05
	 *//*
	public void testUpdateDicInteractProblem() {
		
		System.out.println("testUpdateDicInteractProblem  begin");
		DicInteractProblem  DicInteractProblem=new DicInteractProblem();
		DicInteractProblem.setId(4);
		DicInteractProblem.setInteractProblemCode(3);
		DicInteractProblem.setRemark("留言超过3个月未回复");
		DicInteractProblem.setModifyTime(new Date());
		
		dicInteractProblemServiceImpl.update(DicInteractProblem);
		System.out.println("testUpdateDicInteractProbblem  end");
		
	}
	
	*//**
	 * @Description: 根据id删除更新互动回应差码表中的记录
	 * @author cuichx --- 2015-10-29下午4:56:05
	 *//*
	public void testDeleteDicInteractProblem() {
		
		System.out.println("testDeleteDicInteractProblem begin");
		dicInteractProblemServiceImpl.delete(6);
		System.out.println("testDeleteDicInteractProblem end");
		
	}
	*//**
	 * @Description:根据条件查询互动回应差码表数据 
	 * @author cuichx --- 2015-10-30上午10:00:14
	 *//*
	public void testQueryListDicInteractProblem(){
		System.out.println("testQueryListDicInteractProblem begin");
		DicInteractProblemRequest request=new DicInteractProblemRequest();
		
		request.setInteractProblemCode(0);
		
		List<DicInteractProblem> list=new ArrayList<DicInteractProblem>();
		list=dicInteractProblemServiceImpl.queryList(request);
		for (DicInteractProblem dicInteractProblem : list) {
			System.out.println(dicInteractProblem.getId()+","+dicInteractProblem.getInteractProblemCode()+","
							+dicInteractProblem.getId()+","
							+DateUtils.formatStandardDateTime(dicInteractProblem.getModifyTime()));
		}
		System.out.println("testQueryListDicInteractProblem end");
	}
	
	
	
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>dic_service_unuserful 服务不实用问题类型码表>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		*//**
		 * @Description: 服务不实用问题类型码表添加数据
		 * @author cuichx --- 2015-10-30上午11:50:51
		 *//*
		public void testAddDicServiceUnuserful(){
			
			//问题类型说明：0办事指南要素缺失、1办事指南要素不准确、2附件未提供下载、3附件无法下载、4其他
			DicServiceUnuserful dicServiceUnuserful=new DicServiceUnuserful();
			dicServiceUnuserful.setServiceUnuserfulCode(2);
			dicServiceUnuserful.setRemark("附件未提供下载");
			
			System.out.println("testAddDicServiceUnuserful   begin");
			dicServiceUnuserfulServiceImpl.add(dicServiceUnuserful);
			System.out.println("testAddDicServiceUnuserful   end");
		}
*//**
		 * @Description: 根据主键查询服务不实用问题类型码表
		 * @author cuichx --- 2015-10-30上午11:51:59
		 *//*
		public void testGetDicServiceUnuserful(){
			
			System.out.println("testGetDicServiceUnuserful  begin");
			DicServiceUnuserful dicServiceUnuserful=dicServiceUnuserfulServiceImpl.get(2);
			System.out.println(dicServiceUnuserful.getServiceUnuserfulCode()+","+dicServiceUnuserful.getRemark());
			System.out.println("testGetDicServiceUnuserful   end");
			
		}
		*//**
		 * @Description: 更新服务不实用问题类型码表
		 * @author cuichx --- 2015-10-29下午4:56:05
		 *//*
		public void testUpdateDicServiceUnuserful() {
			
			System.out.println("testUpdateDicServiceUnuserful  begin");
			DicServiceUnuserful  dicServiceUnuserful=new DicServiceUnuserful();
			dicServiceUnuserful.setId(2);
			dicServiceUnuserful.setServiceUnuserfulCode(0);
			dicServiceUnuserful.setRemark("附件无法下载");
			dicServiceUnuserful.setModifyTime(new Date());
			
			dicServiceUnuserfulServiceImpl.update(dicServiceUnuserful);
			System.out.println("testUpdateDicServiceUnuserful  end");
			
		}
		
		*//**
		 * @Description: 根据id删除服务不实用问题类型码表中的记录
		 * @author cuichx --- 2015-10-29下午4:56:05
		 *//*
		public void testDeleteDicServiceUnuserful() {
			
			System.out.println("testDeleteDicServiceUnuserful begin");
			dicServiceUnuserfulServiceImpl.delete(2);
			System.out.println("testDeleteDicServiceUnuserful end");
			
		}
		*//**
		 * @Description:根据条件查询栏目类别码表数据 
		 * @author cuichx --- 2015-10-30上午10:00:14
		 *//*
		public void testQueryListDicServiceUnuserful(){
			System.out.println("testQueryListDicServiceUnuserful begin");
			DicServiceUnuserfulRequest request=new DicServiceUnuserfulRequest();
			
			request.setServiceUnuserfulCode(0);
			
			List<DicServiceUnuserful> list=new ArrayList<DicServiceUnuserful>();
			list=dicServiceUnuserfulServiceImpl.queryList(request);
			for (DicServiceUnuserful dicServiceUnuserful : list) {
				System.out.println(dicServiceUnuserful.getId()+","+dicServiceUnuserful.getServiceUnuserfulCode()+","
								+DateUtils.formatStandardDateTime(dicServiceUnuserful.getModifyTime()));
			}
			System.out.println("testQueryListDicServiceUnuserful end");
		}
		
		

		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>early_detail 预警详情表     >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			*//**
			 *问题总结：     该表中的  DicChannelId（问题类型）  和栏目码表中的id是否一致？？？  
			 *//*
			
				*//**
				 * @Description: 预警详情表添加数据
				 * @author cuichx --- 2015-10-30上午11:50:51
				 *//*
				public void testAddEarlyDetail(){
					System.out.println("testAddEarlyDetail   begin");
					for(int i=0;i<30;i++){
						EarlyDetail earlyDetail=new EarlyDetail();
						earlyDetail.setSiteCode("3301030001");
						earlyDetail.setDicChannelId(1);
						earlyDetail.setQueDescribe("发现qweqwe");
						earlyDetail.setScanTime(DateUtils.formatStandardDateTime(new Date()));//扫描时间
						
						earlyDetailServiceImpl.add(earlyDetail);
					}

					System.out.println("testAddEarlyDetail   end");
				}
				*//**
				 * @Description: 根据主键查询预警详情表
				 * @author cuichx --- 2015-10-30上午11:51:59
				 *//*
				public void testGetEarlyDetail(){
					
					System.out.println("testGetEarlyDetail  begin");
					EarlyDetail earlyDetail=earlyDetailServiceImpl.get(1);
					System.out.println(earlyDetail.getSiteCode()+""+earlyDetail.getDicChannelId()+","
								+earlyDetail.getQueDescribe()+","+earlyDetail.getScanTime());
					System.out.println("testGetEarlyDetail   end");
					
				}
				*//**
				 * @Description: 更新预警详情表
				 * @author cuichx --- 2015-10-29下午4:56:05
				 *//*
				public void testUpdateEarlyDetail() {
					
					System.out.println("testUpdateEarlyDetail  begin");
					EarlyDetail  earlyDetail=new EarlyDetail();
					earlyDetail.setId(1);
					earlyDetail.setDicChannelId(2);
					earlyDetail.setQueDescribe("更新不及时");
					earlyDetail.setScanTime(DateUtils.formatStandardDate(new Date()));
					earlyDetail.setSiteCode("3301030002");
					
					earlyDetailServiceImpl.update(earlyDetail);
					System.out.println("testUpdateEarlyDetail  end");
				}
				
				*//**
				 * @Description: 根据id删除预警详情表中的记录
				 * @author cuichx --- 2015-10-29下午4:56:05
				 *//*
				public void testDeleteEarlyDetail() {
					
					System.out.println("testDeleteEarlyDetail begin");
					earlyDetailServiceImpl.delete(3);
					System.out.println("testDeleteEarlyDetail end");
					
				}
				*//**
				 * @Description:分页查询 
				 * @author cuichx --- 2015-10-30上午9:42:22
				 *//*
				public void testQueryEarlyDetail(){
					
					System.out.println("testQueryEarlyDetail begin");
					EarlyDetailRequest request=new EarlyDetailRequest();
					request.setPageNo(1);
					request.setPageSize(4);
					request.setSiteCode("3301030001");
					
					PageVo<EarlyDetail> pageVo=earlyDetailServiceImpl.query(request);
					
					System.out.println(pageVo.isHasNext());
					System.out.println(pageVo.getPageNo());
					System.out.println(pageVo.getPageSize());
					System.out.println(pageVo.getPageTotal());
					System.out.println(pageVo.getRecordSize());
				
					
					for(EarlyDetail earlyDetail:pageVo.getData()){
						System.out.println(earlyDetail.getId()+","
									+earlyDetail.getScanTime()+","
								    +earlyDetail.getSiteCode()+","
									+DateUtils.formatStandardDateTime(earlyDetail.getCreateTime()));
					}
					System.out.println("testQueryEarlyDetail end");
				}
				
				*//**
				 * @Description: 根据条件查询预警详情表信息
				 * @author cuichx --- 2015-10-30上午9:56:11
				 *//*
				public void testQueryCountEarlyDetail(){
					System.out.println("testQueryCountEarlyDetail begin");
					EarlyDetailRequest request=new EarlyDetailRequest();
					request.setSiteCode("3301030001");
					
					int sum=earlyDetailServiceImpl.queryCount(request);
					
					System.out.println("sum:"+sum);
					System.out.println("testQueryCountEarlyDetail end");
					
				}
				
				*//**
				 * @Description:根据条件查询预警详情表表数据 
				 * @author cuichx --- 2015-10-30上午10:00:14
				 *//*
				public void testQueryListEarlyDetail(){
					System.out.println("testQueryListEarlyDetail begin");
					EarlyDetailRequest request=new EarlyDetailRequest();
					request.setSiteCode("3301030001");
					
					List<EarlyDetail> list=new ArrayList<EarlyDetail>();
					list=earlyDetailServiceImpl.queryList(request);
					for (EarlyDetail earlyDetail : list) {
						System.out.println(earlyDetail.getId()+","+earlyDetail.getSiteCode()+","
										+earlyDetail.getScanTime()+","
										+DateUtils.formatStandardDateTime(earlyDetail.getCreateTime()));
					}
					System.out.println("testQueryListEarlyDetail end");
				}
				
				//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>early_info 预警表     >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					*//**
					 * @Description: 预警详情表添加数据
					 * @author cuichx --- 2015-10-30上午11:50:51
					 *//*
					public void testAddEarlyInfo(){
						
						System.out.println("testAddEarlyInfo   begin");
						EarlyInfo earlyInfo=new EarlyInfo();
						earlyInfo.setSiteCode("1302830002");
						earlyInfo.setSiteName("zcZc");
						earlyInfo.setEarlyLevel("红色预警");
						earlyInfo.setNewEarlyNum(10);
						earlyInfo.setEarlySum(100);
						earlyInfo.setState(0);
						
						earlyInfoServiceImpl.add(earlyInfo);
						System.out.println("testAddEarlyDetail   end");
					}
					*//**
					 * @Description: 根据主键查询预警表
					 * @author cuichx --- 2015-10-30上午11:51:59
					 *//*
					public void testGetEarlyInfo(){
						
						System.out.println("testGetEarlyInfo  begin");
						EarlyInfo earlyInfo=earlyInfoServiceImpl.get(2);
						System.out.println(earlyInfo.getSiteCode()+","+earlyInfo.getSiteName()+","
									+earlyInfo.getNewEarlyNum()+","+earlyInfo.getEarlySum()+","
									+earlyInfo.getEarlyLevel()+","+earlyInfo.getState());
						System.out.println("testGetEarlyInfo   end");
						
					}
					*//**
					 * @Description: 更新预警表
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testUpdateEarlyInfo() {
						
						System.out.println("testUpdateEarlyInfo  begin");
						EarlyInfo  earlyInfo=new EarlyInfo();
						earlyInfo.setSiteCode("3301030001");
						earlyInfo.setSiteName("安徽省shengggggggg");
						earlyInfo.setEarlyLevel("黄色预警");
						earlyInfo.setNewEarlyNum(10);
						earlyInfo.setEarlySum(100);
						earlyInfo.setState(0);
						
						earlyInfoServiceImpl.update(earlyInfo);
						System.out.println("testUpdateEarlyInfo  end");
					}
					
					*//**
					 * @Description: 根据id删除预警表中的记录
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testDeleteEarlyInfo() {
						
						System.out.println("testDeleteEarlyInfo begin");
						earlyInfoServiceImpl.delete(3);
						System.out.println("testDeleteEarlyInfo end");
						
					}
					*//**
					 * @Description:分页查询 
					 * @author cuichx --- 2015-10-30上午9:42:22
					 *//*
					public void testQueryEarlyInfo(){
						
						System.out.println("testQueryEarlyInfo begin");
						EarlyInfoRequest request=new EarlyInfoRequest();
						request.setPageNo(1);
						request.setPageSize(10);
						
						request.setSiteCode("3301030001");
						
						PageVo<EarlyInfo> pageVo=earlyInfoServiceImpl.query(request);
						
						for(EarlyInfo earlyInfo:pageVo.getData()){
							System.out.println(earlyInfo.getId()+","
									    +earlyInfo.getSiteCode()+","
										+DateUtils.formatStandardDateTime(earlyInfo.getCreateTime()));
						}
						System.out.println("testQueryEarlyInfo end");
					}
					
					*//**
					 * @Description: 根据条件查询预警详情表信息
					 * @author cuichx --- 2015-10-30上午9:56:11
					 *//*
					public void testQueryCountEarlyInfo(){
						System.out.println("testQueryCountEarlyInfo begin");
						EarlyInfoRequest request=new EarlyInfoRequest();
						request.setSiteCode("3301030001");
						
						int sum=earlyInfoServiceImpl.queryCount(request);
						
						System.out.println("sum:"+sum);
						System.out.println("testQueryCountEarlyInfo end");
						
					}
					
					*//**
					 * @Description:根据条件查询预警详情表表数据 
					 * @author cuichx --- 2015-10-30上午10:00:14
					 *//*
					public void testQueryListEarlyInfo(){
						System.out.println("testQueryListEarlyInfo begin");
						EarlyInfoRequest request=new EarlyInfoRequest();
						request.setSiteCode("3301030001");
						
						List<EarlyInfo> list=new ArrayList<EarlyInfo>();
						list=earlyInfoServiceImpl.queryList(request);
						for (EarlyInfo earlyInfo : list) {
							System.out.println(earlyInfo.getId()+","+earlyInfo.getSiteCode()+","
											+DateUtils.formatStandardDateTime(earlyInfo.getCreateTime()));
						}
						System.out.println("testQueryListEarlyInfo end");
					}
					//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>link_all_detail 全站连接可用性检测结果详情表    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					
					
					*//**
					 * @Description:  全站连接可用性检测结果详情表添加数据
					 * @author cuichx --- 2015-10-30上午11:50:51
					 *//*
					public void testAddLinkAllDetail(){
						
						System.out.println("testAddLinkAllDetail   begin");
						LinkAllDetail linkAllDetail=new LinkAllDetail();
						linkAllDetail.setSiteCode("3301030001");
						linkAllDetail.setServicePeriodId(1);
						
						linkAllDetail.setUrl("http://www.baidu.com");
						linkAllDetail.setUrlType("0");
						linkAllDetail.setScanLevel(2);
						linkAllDetail.setLinkTitle("崔传新");
						linkAllDetail.setParentTitle("哈哈");
						linkAllDetail.setResourceType(2);
						linkAllDetail.setScope(0);
						linkAllDetail.setErrorCode("500");
						linkAllDetail.setErrorDescribe("服务器无响应！");
						linkAllDetail.setImgUrl("快照url");
						linkAllDetail.setScanTime(DateUtils.formatStandardDate(new Date()));
	
						
						linkAllDetailServiceImpl.add(linkAllDetail);
						System.out.println("testAddLinkAllDetail   end");
					}
					*//**
					 * @Description: 根据主键查 全站连接可用性检测结果详情表
					 * @author cuichx --- 2015-10-30上午11:51:59
					 *//*
					public void testGetLinkAllDetail(){
						
						System.out.println("testGetLinkAllDetail  begin");
						LinkAllDetail linkAllDetail=linkAllDetailServiceImpl.get(2);
						System.out.println(linkAllDetail.getId()+","+linkAllDetail.getSiteCode()+","+linkAllDetail.getErrorCode()+","
									+linkAllDetail.getErrorDescribe()+","+linkAllDetail.getImgUrl()+","
									+linkAllDetail.getLinkAllInfoId()+","+linkAllDetail.getLinkTitle()+","
									+linkAllDetail.getParentTitle()+","+linkAllDetail.getResourceType()+","
									+linkAllDetail.getImgUrl()+","+linkAllDetail.getLinkAllInfoId());
						System.out.println("testGetLinkAllDetail   end");
						
					}
					*//**
					 * @Description: 更新 全站连接可用性检测结果详情表
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testUpdateLinkAllDetail(){
						
						System.out.println("testUpdateLinkAllDetail  begin");
						LinkAllDetail  linkAllDetail=new LinkAllDetail();
						linkAllDetail.setId(2);
						linkAllDetail.setSiteCode("3301030002");
						linkAllDetail.setServicePeriodId(2);
						
						linkAllDetail.setUrl("http://www.baidu.com123");
						linkAllDetail.setUrlType("2");
						linkAllDetail.setScanLevel(3);
						linkAllDetail.setLinkTitle("崔传新123");
						linkAllDetail.setParentTitle("哈哈123");
						linkAllDetail.setResourceType(2);
						linkAllDetail.setScope(1);
						linkAllDetail.setErrorCode("500");
						linkAllDetail.setErrorDescribe("服务器无响应！");
						linkAllDetail.setImgUrl("快照url");
						linkAllDetail.setScanTime(DateUtils.formatStandardDate(new Date()));
						
						linkAllDetailServiceImpl.update(linkAllDetail);
						System.out.println("testUpdateLinkAllDetail  end");
					}
					
					*//**
					 * @Description: 根据id删除 全站连接可用性检测结果详情表中的记录
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testDeleteLinkAllDetail() {
						
						System.out.println("testDeleteLinkAllDetail begin");
						linkAllDetailServiceImpl.delete(2);
						System.out.println("testDeleteLinkAllDetail end");
						
					}
					*//**
					 * @Description:分页查询 
					 * @author cuichx --- 2015-10-30上午9:42:22
					 *//*
					public void testQueryLinkAllDetail(){
						
						System.out.println("testQueryLinkAllDetail begin");
						LinkAllDetailRequest request=new LinkAllDetailRequest();
						request.setPageNo(1);
						request.setPageSize(10);
						request.setServicePeriodId(1);
						request.setSiteCode("3301030001");
						
						PageVo<LinkAllDetail> pageVo=linkAllDetailServiceImpl.query(request);
						
						for(LinkAllDetail linkAllDetail:pageVo.getData()){
							System.out.println(linkAllDetail.getId()+","
									    +linkAllDetail.getSiteCode()+","
										+DateUtils.formatStandardDateTime(linkAllDetail.getCreateTime()));
						}
						System.out.println("testQueryLinkAllDetail end");
					}
					
					*//**
					 * @Description: 根据条件查询 全站连接可用性检测结果详情表信息
					 * @author cuichx --- 2015-10-30上午9:56:11
					 *//*
					public void testQueryCountLinkAllDetail(){
						System.out.println("testQueryCountLinkAllDetail begin");
						LinkAllDetailRequest request=new LinkAllDetailRequest();
						request.setSiteCode("3301030001");
						
						int sum=linkAllDetailServiceImpl.queryCount(request);
						
						System.out.println("sum:"+sum);
						System.out.println("testQueryCountLinkAllDetail end");
						
					}
					
					*//**
					 * @Description:根据条件查询 全站连接可用性检测结果详情表数据 
					 * @author cuichx --- 2015-10-30上午10:00:14
					 *//*
					public void testQueryListLinkAllDetail(){
						System.out.println("testQueryListLinkAllDetail begin");
						LinkAllDetailRequest request=new LinkAllDetailRequest();
						request.setSiteCode("3301030001");
						
						List<LinkAllDetail> list=new ArrayList<LinkAllDetail>();
						list=linkAllDetailServiceImpl.queryList(request);
						for (LinkAllDetail linkAllDetail : list) {
							System.out.println(linkAllDetail.getId()+","+linkAllDetail.getSiteCode()+","
											+DateUtils.formatStandardDateTime(linkAllDetail.getCreateTime()));
						}
						System.out.println("testQueryListLinkAllDetail end");
					}
					
					//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>link_all_info 全站连接可用性趋势表   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					
					
					*//**
					 * @Description:  全站连接可用性趋势表添加数据
					 * @author cuichx --- 2015-10-30上午11:50:51
					 *//*
					public void testAddLinkAllInfo(){
						
						System.out.println("testAddLinkAllInfo   begin");
						LinkAllInfo linkAllInfo=new LinkAllInfo();
						linkAllInfo.setSiteCode("3301030001");
						linkAllInfo.setServicePeriodId(1);
						linkAllInfo.setWebsiteSum(100);
						linkAllInfoServiceImpl.add(linkAllInfo);
						System.out.println("testAddLinkAllInfo   end");
					}

					*//**
					 * @Description: 根据主键查全站连接可用性趋势表
					 * @author cuichx --- 2015-10-30上午11:51:59
					 *//*
					public void testGetLinkAllInfo(){
						
						System.out.println("testGetLinkAllInfo  begin");
						LinkAllInfo linkAllInfo=linkAllInfoServiceImpl.get(2);
						System.out.println(linkAllInfo.getId()+","+linkAllInfo.getSiteCode()+","
									+linkAllInfo.getServicePeriodId()+","+linkAllInfo.getWebsiteSum()+","
									+ DateUtils.formatStandardDateTime(linkAllInfo.getCreateTime()));
						System.out.println("testGetLinkAllInfo   end");
						
					}
					*//**
					 * @Description: 更新全站连接可用性趋势表
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testUpdateLinkAllInfo(){
						
						System.out.println("testUpdateLinkAllInfo  begin");
						LinkAllInfo  linkAllInfo=new LinkAllInfo();
						linkAllInfo.setId(3);
						linkAllInfo.setSiteCode("3301030002");
						linkAllInfo.setServicePeriodId(2);
						linkAllInfo.setWebsiteSum(200);
	
						linkAllInfoServiceImpl.update(linkAllInfo);
						System.out.println("testUpdateLinkAllInfo  end");
					}
					
					*//**
					 * @Description: 根据id删除 全站连接可用性趋势表
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testDeleteLinkAllInfo() {
						
						System.out.println("testDeleteLinkAllInfo begin");
						linkAllInfoServiceImpl.delete(2);
						System.out.println("testDeleteLinkAllInfo end");
						
					}
					*//**
					 * @Description:分页查询 
					 * @author cuichx --- 2015-10-30上午9:42:22
					 *//*
					public void testQueryLinkAllInfo(){
						
						System.out.println("testQueryLinkAllInfo begin");
						LinkAllInfoRequest request=new LinkAllInfoRequest();
						request.setPageNo(1);
						request.setPageSize(10);
						//request.setServicePeriodId(1);
						request.setSiteCode("3301030001");
						
						PageVo<LinkAllInfo> pageVo=linkAllInfoServiceImpl.query(request);
						
						for(LinkAllInfo linkAllInfo:pageVo.getData()){
							System.out.println(linkAllInfo.getId()+","
									    +linkAllInfo.getSiteCode()+","
									    +linkAllInfo.getWebsiteSum()+","
										+DateUtils.formatStandardDateTime(linkAllInfo.getCreateTime()));
						}
						System.out.println("testQueryLinkAllInfo end");
					}
					
					*//**
					 * @Description: 根据条件查询 全站连接可用性趋势表
					 * @author cuichx --- 2015-10-30上午9:56:11
					 *//*
					public void testQueryCountLinkAllInfo(){
						System.out.println("testQueryCountLinkAllInfo begin");
						LinkAllInfoRequest request=new LinkAllInfoRequest();
						
						//request.setServicePeriodId(1);
						request.setSiteCode("3301030001");
						int sum=linkAllInfoServiceImpl.queryCount(request);
						
						System.out.println("sum:"+sum);
						System.out.println("testQueryCountLinkAllInfo end");
						
					}
					
					*//**
					 * @Description:根据条件查询 全站连接可用性趋势表数据 
					 * @author cuichx --- 2015-10-30上午10:00:14
					 *//*
					public void testQueryListLinkAllInfo(){
						System.out.println("testQueryListLinkAllInfo begin");
						LinkAllInfoRequest request=new LinkAllInfoRequest();
						//request.setServicePeriodId(1);
						request.setSiteCode("3301030001");
						
						List<LinkAllInfo> list=new ArrayList<LinkAllInfo>();
						list=linkAllInfoServiceImpl.queryList(request);
						for (LinkAllInfo linkAllInfo : list) {
							System.out.println(linkAllInfo.getId()+","
								    +linkAllInfo.getSiteCode()+","
								    +linkAllInfo.getWebsiteSum()+","
									+DateUtils.formatStandardDateTime(linkAllInfo.getCreateTime()));
						}
						System.out.println("testQueryListLinkAllInfo end");
					}
					
					//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>link_home_available 首页连接可用性表    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					
					
					*//**
					 * @Description:  首页连接可用性表添加数据
					 * @author cuichx --- 2015-10-30上午11:50:51
					 *//*
					public void testAddLinkHomeAvailable(){
						for(int i=0;i<10;i++){
							System.out.println("testAddLinkHomeAvailable   begin");
							LinkHomeAvailable linkHomeAvailable=new LinkHomeAvailable();
							linkHomeAvailable.setSiteCode("3301030001");
							linkHomeAvailable.setScanDate(DateUtils.formatStandardDate(new Date()));
							linkHomeAvailable.setUrl("http://www.baidu.com");
							linkHomeAvailable.setLinkTitle("百度一下，你就知道");
							linkHomeAvailable.setUrlType(0);
							linkHomeAvailable.setResourceType(1);
							linkHomeAvailable.setScope(1);
							linkHomeAvailable.setLinkNotavailableDay(1);
							linkHomeAvailable.setImgUrl("快照url");
							linkHomeAvailable.setQuestionDescribe("请求路径失败！");
							linkHomeAvailable.setQuestionCode("404");
							
							linkHomeAvailableServiceImpl.add(linkHomeAvailable);
							System.out.println("testAddLinkHomeAvailable   end");
						}
					}
					*//**
					 * @Description: 根据主键查 首页连接可用性表
					 * @author cuichx --- 2015-10-30上午11:51:59
					 *//*
					public void testGetLinkHomeAvailable(){
						
						System.out.println("testGetLinkHomeAvailable  begin");
						
						LinkHomeAvailable linkHomeAvailable=linkHomeAvailableServiceImpl.get(2);
						
						System.out.println(linkHomeAvailable.getId()+","+linkHomeAvailable.getSiteCode()+","+linkHomeAvailable.getScanDate()+","
									+linkHomeAvailable.getUrl()+","+linkHomeAvailable.getLinkTitle()+","
									+linkHomeAvailable.getUrlType()+","+linkHomeAvailable.getResourceType()+","
									+linkHomeAvailable.getScope()+","+linkHomeAvailable.getLinkNotavailableDay()+","
									+linkHomeAvailable.getImgUrl()+","+linkHomeAvailable.getQuestionDescribe()+","+linkHomeAvailable.getQuestionCode());
						
						System.out.println("testGetLinkHomeAvailable   end");
						
					}
					*//**
					 * @Description: 更新 首页连接可用性表
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testUpdateLinkHomeAvailable(){
						
						System.out.println("testUpdateLinkHomeAvailable  begin");
						
						LinkHomeAvailable linkHomeAvailable=new LinkHomeAvailable();
						
						linkHomeAvailable.setId(2);
						linkHomeAvailable.setSiteCode("3301030002");
						linkHomeAvailable.setScanDate(DateUtils.formatStandardDate(new Date()));
						linkHomeAvailable.setUrl("http://www.baidu.com");
						linkHomeAvailable.setLinkTitle("百度一下，你就知道");
						linkHomeAvailable.setUrlType(0);
						linkHomeAvailable.setResourceType(1);
						linkHomeAvailable.setScope(1);
						linkHomeAvailable.setLinkNotavailableDay(1);
						linkHomeAvailable.setImgUrl("快照url");
						linkHomeAvailable.setQuestionDescribe("请求路径失败！");
						linkHomeAvailable.setQuestionCode("404");
						
						linkHomeAvailableServiceImpl.update(linkHomeAvailable);
						System.out.println("testUpdateLinkHomeAvailable  end");
					}
					
					*//**
					 * @Description: 根据id删除 首页连接可用性表
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testDeleteLinkHomeAvailable() {
						
						System.out.println("testDeleteLinkHomeAvailable begin");
						linkHomeAvailableServiceImpl.delete(1);
						System.out.println("testDeleteLinkHomeAvailable end");
						
					}
					*//**
					 * @Description:分页查询 
					 * @author cuichx --- 2015-10-30上午9:42:22
					 *//*
					public void testQueryLinkHomeAvailable(){
						
						System.out.println("testQueryLinkHomeAvailable begin");
						LinkHomeAvailableRequest request=new LinkHomeAvailableRequest();
						request.setPageNo(1);
						request.setPageSize(10);
						request.setScanDate(DateUtils.formatStandardDate(new Date()));
						request.setSiteCode("3301030001");
						
						PageVo<LinkHomeAvailable> pageVo=linkHomeAvailableServiceImpl.query(request);
						
						for(LinkHomeAvailable linkHomeAvailable:pageVo.getData()){
							System.out.println(linkHomeAvailable.getId()+","+linkHomeAvailable.getSiteCode()+","
									+linkHomeAvailable.getScanDate()+","+linkHomeAvailable.getQuestionCode()+","
									+linkHomeAvailable.getUrl()+","+linkHomeAvailable.getLinkTitle()+","
									+linkHomeAvailable.getUrlType()+","+linkHomeAvailable.getResourceType()+","
									+linkHomeAvailable.getScope()+","+linkHomeAvailable.getLinkNotavailableDay()+","
									+linkHomeAvailable.getImgUrl()+","+linkHomeAvailable.getQuestionDescribe());
						
						}
						System.out.println("testQueryLinkHomeAvailable end");
					}
					
					*//**
					 * @Description: 根据条件查询 首页连接可用性表
					 * @author cuichx --- 2015-10-30上午9:56:11
					 *//*
					public void testQueryCountLinkHomeAvailable(){
						System.out.println("testQueryCountLinkHomeAvailable begin");
						LinkHomeAvailableRequest request=new LinkHomeAvailableRequest();
						request.setScanDate(DateUtils.formatStandardDate(new Date()));
						request.setSiteCode("3301030001");
						int sum=linkHomeAvailableServiceImpl.queryCount(request);
						
						System.out.println("sum:"+sum);
						System.out.println("testQueryCountLinkHomeAvailable end");
						
					}
					
					*//**
					 * @Description:根据条件查询 首页连接可用性表
					 * @author cuichx --- 2015-10-30上午10:00:14
					 *//*
					public void testQueryListLinkHomeAvailable(){
						System.out.println("testQueryListLinkHomeAvailable begin");
						LinkHomeAvailableRequest request=new LinkHomeAvailableRequest();
						request.setScanDate(DateUtils.formatStandardDate(new Date()));
						//request.setSiteCode("3301030001");
						
						List<LinkHomeAvailable> list=new ArrayList<LinkHomeAvailable>();
						list=linkHomeAvailableServiceImpl.queryList(request);
						for (LinkHomeAvailable linkHomeAvailable : list) {
							System.out.println(linkHomeAvailable.getId()+","+linkHomeAvailable.getSiteCode()+","
									+linkHomeAvailable.getScanDate()+","+linkHomeAvailable.getQuestionCode()+","
									+linkHomeAvailable.getUrl()+","+linkHomeAvailable.getLinkTitle()+","
									+linkHomeAvailable.getUrlType()+","+linkHomeAvailable.getResourceType()+","
									+linkHomeAvailable.getScope()+","+linkHomeAvailable.getLinkNotavailableDay()+","
									+linkHomeAvailable.getImgUrl()+","+linkHomeAvailable.getQuestionDescribe());
						}
						System.out.println("testQueryListLinkHomeAvailable end");
					}
					
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>link_home_trend 首页连接可用性趋势表  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					
					
					*//**
					 * @Description:  首页连接可用性趋势表  添加数据
					 * @author cuichx --- 2015-10-30上午11:50:51
					 *//*
					public void testAddLinkHomeTrend(){
						System.out.println("testAddLinkHomeTrend   begin");
						for(int i=0;i<10;i++){
							LinkHomeTrend linkHomeTrend=new LinkHomeTrend();
							linkHomeTrend.setSiteCode("3301030001");
							linkHomeTrend.setScanDate(DateUtils.formatStandardDate(new Date()));
							linkHomeTrend.setWebsiteSum(100);
							
							linkHomeTrendServiceImpl.add(linkHomeTrend);
						}
						System.out.println("testAddLinkHomeTrend   end");
					}

					*//**
					 * @Description: 根据主键查 首页连接可用性趋势表 
					 * @author cuichx --- 2015-10-30上午11:51:59
					 *//*
					public void testGetLinkHomeTrend(){
						
						System.out.println("testGetLinkHomeTrend  begin");
						
						LinkHomeTrend linkHomeTrend=linkHomeTrendServiceImpl.get(12);
						
						System.out.println(linkHomeTrend.getId()+","+linkHomeTrend.getSiteCode()+","
								+linkHomeTrend.getScanDate()+","+linkHomeTrend.getWebsiteSum()+","
								+DateUtils.formatStandardDateTime(linkHomeTrend.getCreateTime()));
						System.out.println("testGetLinkHomeTrend   end");
						
					}
					*//**
					 * @Description: 更新 首页连接可用性趋势表 
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testUpdateLinkHomeTrend(){
						
						System.out.println("testUpdateLinkHomeTrend  begin");
						
						LinkHomeTrend linkHomeTrend=new LinkHomeTrend();
						linkHomeTrend.setId(1);
						linkHomeTrend.setSiteCode("3301030012");
						linkHomeTrend.setScanDate(DateUtils.formatStandardDate(new Date()));
						linkHomeTrend.setWebsiteSum(100);
						
						linkHomeTrendServiceImpl.update(linkHomeTrend);
						System.out.println("testUpdateLinkHomeTrend  end");
					}
					
					*//**
					 * @Description: 根据id删除 首页连接可用性趋势表 
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testDeleteLinkHomeTrend() {
						
						System.out.println("testDeleteLinkHomeTrend begin");
						linkHomeTrendServiceImpl.delete(1);
						System.out.println("testDeleteLinkHomeTrend end");
						
					}
					*//**
					 * @Description:分页查询 
					 * @author cuichx --- 2015-10-30上午9:42:22
					 *//*
					public void testQueryLinkHomeTrend(){
						
						System.out.println("testQueryLinkHomeTrend begin");
						LinkHomeTrendRequest request=new LinkHomeTrendRequest();
						request.setPageNo(1);
						request.setPageSize(10);
						request.setScanDate(DateUtils.formatStandardDate(new Date()));
						request.setSiteCode("3301030001");
						
						request.setBegeinScanDate("2015-11-02");
						request.setEndScanDate("2015-11-04");
						PageVo<LinkHomeTrend> pageVo=linkHomeTrendServiceImpl.query(request);
						
						for(LinkHomeTrend linkHomeTrend:pageVo.getData()){
							System.out.println(linkHomeTrend.getId()+","+linkHomeTrend.getSiteCode()+","
									+linkHomeTrend.getScanDate()+","+linkHomeTrend.getWebsiteSum()+","
									+DateUtils.formatStandardDateTime(linkHomeTrend.getCreateTime()));
						
						}
						System.out.println("testQueryLinkHomeTrend end");
					}
					
					*//**
					 * @Description: 根据条件查询 首页连接可用性趋势表 
					 * @author cuichx --- 2015-10-30上午9:56:11
					 *//*
					public void testQueryCountLinkHomeTrend(){
						System.out.println("testQueryCountLinkHomeTrend begin");
						LinkHomeTrendRequest request=new LinkHomeTrendRequest();
						request.setPageNo(1);
						request.setPageSize(10);
						request.setScanDate(DateUtils.formatStandardDate(new Date()));
						request.setSiteCode("3301030001");
						
						request.setBegeinScanDate("2015-11-02");
						request.setEndScanDate("2015-11-04");
						int sum=linkHomeTrendServiceImpl.queryCount(request);
						
						System.out.println("sum:"+sum);
						System.out.println("testQueryCountLinkHomeTrend end");
						
					}
					
					*//**
					 * @Description:根据条件查询 首页连接可用性趋势表 
					 * @author cuichx --- 2015-10-30上午10:00:14
					 *//*
					public void testQueryListLinkHomeTrend(){
						System.out.println("testQueryListLinkHomeTrend begin");
						LinkHomeTrendRequest request=new LinkHomeTrendRequest();
						request.setPageNo(1);
						request.setPageSize(10);
						request.setScanDate(DateUtils.formatStandardDate(new Date()));
						request.setSiteCode("3301030001");
						
						request.setBegeinScanDate("2015-11-02");
						request.setEndScanDate("2015-11-04");
						List<LinkHomeTrend> list=new ArrayList<LinkHomeTrend>();
						list=linkHomeTrendServiceImpl.queryList(request);
						for (LinkHomeTrend linkHomeTrend : list) {
							System.out.println(linkHomeTrend.getId()+","+linkHomeTrend.getSiteCode()+","
									+linkHomeTrend.getScanDate()+","+linkHomeTrend.getWebsiteSum()+","
									+DateUtils.formatStandardDateTime(linkHomeTrend.getCreateTime()));
						}
						System.out.println("testQueryListLinkHomeTrend end");
					}
					
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>report_manage_log 报告管理表  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					
					
					*//**
					 * @Description:  报告管理表  添加数据
					 * @author cuichx --- 2015-10-30上午11:50:51
					 *//*
					public void testAddReportManageLog(){
						System.out.println("testAddReportManageLog   begin");
						for(int i=0;i<10;i++){
							ReportManageLog reportManageLog=new ReportManageLog();
							reportManageLog.setServicePeriodId(i+1);
							reportManageLog.setSiteCode("3301030001");
							reportManageLog.setSiteName("哈哈哈");
							reportManageLog.setSendState(1);
							
							reportManageLogServiceImpl.add(reportManageLog);
						}
						System.out.println("testAddReportManageLog   end");
					}
					*//**
					 * @Description: 根据主键查 报告管理表 
					 * @author cuichx --- 2015-10-30上午11:51:59
					 *//*
					public void testGetReportManageLog(){
						
						System.out.println("testGetReportManageLog  begin");
						
						ReportManageLog reportManageLog=reportManageLogServiceImpl.get(1);
						
						System.out.println(reportManageLog.getId()+","+reportManageLog.getSiteCode()+","
								+reportManageLog.getServicePeriodId()+","+reportManageLog.getSendState()+","
								+DateUtils.formatStandardDateTime(reportManageLog.getSendTime())+","
								+DateUtils.formatStandardDateTime(reportManageLog.getCreateTime()));
						System.out.println("testGetReportManageLog   end");
						
					}
					*//**
					 * @Description: 更新 报告管理表 
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testUpdateReportManageLog(){
						
						System.out.println("testUpdateReportManageLog  begin");
						
						ReportManageLog reportManageLog=new ReportManageLog();
						reportManageLog.setId(2);
						reportManageLog.setServicePeriodId(2);
						reportManageLog.setSiteCode("3301030002");
						reportManageLog.setSiteName("哈哈哈");
						reportManageLog.setSendState(1);
						
						reportManageLogServiceImpl.update(reportManageLog);
						System.out.println("testUpdateReportManageLog  end");
					}
					
					*//**
					 * @Description: 根据id删除 报告管理表 
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testDeleteReportManageLog() {
						
						System.out.println("testDeleteReportManageLog begin");
						reportManageLogServiceImpl.delete(1);
						System.out.println("testDeleteReportManageLog end");
						
					}
					*//**
					 * @Description:分页查询 
					 * @author cuichx --- 2015-10-30上午9:42:22
					 *//*
					public void testQueryReportManageLog(){
						
						System.out.println("testQueryReportManageLog begin");
						ReportManageLogRequest request=new ReportManageLogRequest();
						request.setPageNo(1);
						request.setPageSize(10);
						request.setServicePeriodId(2);
						request.setSiteCode("3301030002");
					
						PageVo<ReportManageLog> pageVo=reportManageLogServiceImpl.query(request);
						
						for(ReportManageLog reportManageLog:pageVo.getData()){
							System.out.println(reportManageLog.getId()+","+reportManageLog.getSiteCode()+","
									+reportManageLog.getServicePeriodId()+","+reportManageLog.getSendState()+","
									+DateUtils.formatStandardDateTime(reportManageLog.getSendTime())+","
									+DateUtils.formatStandardDateTime(reportManageLog.getCreateTime()));
						}
						System.out.println("testQueryReportManageLog end");
					}
					
					*//**
					 * @Description: 根据条件查询 报告管理表 
					 * @author cuichx --- 2015-10-30上午9:56:11
					 *//*
					public void testQueryCountReportManageLog(){
						System.out.println("testQueryCountReportManageLog begin");
						ReportManageLogRequest request=new ReportManageLogRequest();
						request.setPageNo(1);
						request.setPageSize(10);
						request.setServicePeriodId(2);
						request.setSiteCode("3301030002");
						
						int sum=reportManageLogServiceImpl.queryCount(request);
						
						System.out.println("sum:"+sum);
						System.out.println("testQueryCountReportManageLog end");
						
					}
					
					*//**
					 * @Description:根据条件查询 报告管理表 
					 * @author cuichx --- 2015-10-30上午10:00:14
					 *//*
					public void testQueryListReportManageLog(){
						System.out.println("testQueryListReportManageLog begin");
						ReportManageLogRequest request=new ReportManageLogRequest();
						request.setPageNo(1);
						request.setPageSize(10);
						request.setServicePeriodId(2);
						request.setSiteCode("3301030002");
			
						List<ReportManageLog> list=new ArrayList<ReportManageLog>();
						list=reportManageLogServiceImpl.queryList(request);
						for (ReportManageLog reportManageLog : list) {
							System.out.println(reportManageLog.getId()+","+reportManageLog.getSiteCode()+","
									+reportManageLog.getServicePeriodId()+","+reportManageLog.getSendState()+","
									+DateUtils.formatStandardDateTime(reportManageLog.getSendTime())+","
									+DateUtils.formatStandardDateTime(reportManageLog.getCreateTime()));
						}
						System.out.println("testQueryListReportManageLog end");
					}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>report_word_result word报告情况  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					
					
					*//**
					 * @Description:  word报告情况  添加数据
					 * @author cuichx --- 2015-10-30上午11:50:51
					 *//*
					public void testAddReportWordResult(){
						System.out.println("testAddReportWordResult   begin");
						for(int i=0;i<10;i++){
							ReportWordResult reportWordResult=new ReportWordResult();
							reportWordResult.setServicePeriodId(i+1);
							reportWordResult.setSiteCode("3301030001");
							reportWordResult.setType(1);
							//reportWordResult.setSaveUrl("http://www.baidu.com");
							
							reportWordResultServiceImpl.add(reportWordResult);
						}
						System.out.println("testAddReportWordResult   end");
					}
					*//**
					 * @Description: 根据主键查 word报告情况 
					 * @author cuichx --- 2015-10-30上午11:51:59
					 *//*
					public void testGetReportWordResult(){
						
						System.out.println("testGetReportWordResult  begin");
						
						ReportWordResult reportWordResult=reportWordResultServiceImpl.get(1);
						
						System.out.println(reportWordResult.getId()+","+reportWordResult.getSiteCode()+","
								+reportWordResult.getServicePeriodId()+","+reportWordResult.getType()+","
								+DateUtils.formatStandardDateTime(reportWordResult.getCreateTime()));
						System.out.println("testGetReportWordResult   end");
					}
					*//**
					 * @Description: 更新 word报告情况 
					 * @author cuichx --- 2015-10-29下午4:56:05
					 *//*
					public void testUpdateReportWordResult(){
						
						System.out.println("testUpdateReportWordResult  begin");
						
						ReportWordResult reportWordResult=new ReportWordResult();
						reportWordResult.setId(1);
						reportWordResult.setServicePeriodId(12);
						reportWordResult.setSiteCode("3301030002");
						reportWordResult.setType(1);
						//reportWordResult.setSaveUrl("http://www.baidu.com");
						
						reportWordResultServiceImpl.update(reportWordResult);
						System.out.println("testUpdateReportWordResult  end");
					}
					*//**
					 * @Description:根据条件查询 word报告情况 
					 * @author cuichx --- 2015-10-30上午10:00:14
					 *//*
					public void testQueryListReportWordResult(){
						System.out.println("testQueryListReportManageLog begin");
						ReportWordResultRequest request=new ReportWordResultRequest();
						request.setPageNo(1);
						request.setPageSize(10);
						
						//request.setServicePeriodId(2);
						request.setSiteCode("3301030001");
			
						List<ReportWordResult> list=new ArrayList<ReportWordResult>();
						list=reportWordResultServiceImpl.queryList(request);
						for (ReportWordResult reportWordResult : list) {
							System.out.println(reportWordResult.getId()+","+reportWordResult.getSiteCode()+","
									+reportWordResult.getServicePeriodId()+","+reportWordResult.getType()+","
									+DateUtils.formatStandardDateTime(reportWordResult.getCreateTime()));
						}
						System.out.println("testQueryListReportManageLog end");
					}
*/
}
