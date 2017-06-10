package com.ucap.cloud_web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.publics.util.entity.CreateBeanUitls;
import com.publics.util.entity.CreateEntityUitls;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDetectionOrgCountService;

/**
 * <p>Description: </p>
 * <p>@Package：com.ucap.cloud_web </p>
 * <p>Title: YangShuaiTest </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：yangshuai </p>
 * <p>@date：2016-5-24下午3:35:26 </p>
 */
public class YangShuaiTest extends BaseTest{

	/*@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	
	@Autowired
	private IDetectionOrgCountService detectionOrgCountServiceImpl;*/
	
	/**
	 * @Description: 
	 * @author: yangshuai --- 2016-5-24下午3:35:26
	 */
	public void testCreateEntityAndBeans() {

		String jdbc = "jdbc:mysql://192.168.1.39:3306/cloud";
		String user = "ecm";
		String passWord = "ecm";
		String path = "E:/MyWork/cloud_web/src/main/java/com/ucap/cloud_web/";
		String author = "yangshuai";

		List<String> list = new ArrayList<String>();
//		list.add("dic_config");
//		list.add("spot_check_notice");
		list.add("config_advert");
		for (String tableName : list) {
			CreateEntityUitls.entity(jdbc, user, passWord, tableName, path,author);
			CreateBeanUitls.createBean(jdbc, user, passWord, tableName, path,author);
		}
	}
	
	/*public void testProvinceBm(){
		System.out.println("---------start----------");
//		int level = 1;
		
		int level = 2;
		String siteCode = "440000";
		
		int level = 3;
		String siteCode = "440900";
		
		if(level==1){
			List<DatabaseInfo> dataList = null;
			//获取所有省
			Map<String, Object> paramMap = new HashMap<String, Object>();
			List<DatabaseInfo> ProDataList = databaseInfoServiceImpl.queryProvince(paramMap);
			//获取部委下一级站群的所有组织机构
			List<DatabaseInfo> bmDataList = databaseInfoServiceImpl.queryBm(paramMap);
			ProDataList.addAll(bmDataList);
			dataList = ProDataList;
			for (DatabaseInfo databaseInfo : dataList) {
				System.out.println(databaseInfo.getName());
			}
		}else{
			DatabaseOrgInfoRequest databaseOrgInfoRequest = new DatabaseOrgInfoRequest();
			databaseOrgInfoRequest.setPageSize(Integer.MAX_VALUE);
			if(level == 2){
				databaseOrgInfoRequest.setSiteCodeLike(siteCode.substring(0, 2));
				databaseOrgInfoRequest.setEndSiteCodeLike(siteCode.substring(4, 6));
			}else{//3
				databaseOrgInfoRequest.setSiteCodeLike(siteCode.substring(0, 4));
			}
			databaseOrgInfoRequest.setNoEqualsSiteCode(siteCode);
			List<DatabaseOrgInfo> dataOrgList = databaseOrgInfoServiceImpl.queryList(databaseOrgInfoRequest);
			for (DatabaseOrgInfo databaseOrgInfo : dataOrgList) {
				System.out.println(">>>>>>>"+databaseOrgInfo.getName());
			}
			System.out.println("======end======");
		}
	}*/
	
	/*public void testProvinceBm(){
		System.out.println("---------start----------");
		int level = 1;
		String siteCode = "bm0100";
		
		int level = 2;
		String siteCode = "440000";
		
		int level = 3;
		String siteCode = "440900";
		
		if(level==1){
			List<DatabaseInfo> dataList = null;
			//获取所有省
			Map<String, Object> paramMap = new HashMap<String, Object>();
			List<DatabaseInfo> ProDataList = databaseInfoServiceImpl.queryProvince(paramMap);
			//获取部委下一级站群的所有组织机构
			List<DatabaseInfo> bmDataList = databaseInfoServiceImpl.queryBm(paramMap);
			ProDataList.addAll(bmDataList);
			dataList = ProDataList;
			for (DatabaseInfo databaseInfo : dataList) {
				System.out.println(databaseInfo.getName());
			}
		}else{
			DatabaseOrgInfoRequest databaseOrgInfoRequest = new DatabaseOrgInfoRequest();
			databaseOrgInfoRequest.setPageSize(Integer.MAX_VALUE);
			if(level == 2){
				databaseOrgInfoRequest.setSiteCodeLike(siteCode.substring(0, 2));
				databaseOrgInfoRequest.setEndSiteCodeLike(siteCode.substring(4, 6));
			}else{//3
				databaseOrgInfoRequest.setSiteCodeLike(siteCode.substring(0, 4));
			}
			databaseOrgInfoRequest.setNoEqualsSiteCode(siteCode);
			List<DatabaseOrgInfo> dataOrgList = databaseOrgInfoServiceImpl.queryList(databaseOrgInfoRequest);
			for (DatabaseOrgInfo databaseOrgInfo : dataOrgList) {
				System.out.println(">>>>>>>"+databaseOrgInfo.getName());
			}
			System.out.println("======end======");
		}
	}*/
	
	/*public void testGetCount(){
		try {
			System.out.println("=====================start===================");
			Map<String, Object> map = new HashMap<String, Object>();
			int level = 3;
			String siteCode = "bm2900";
			
			List<DatabaseInfoRequest> dataCountList = null;
			map.put("siteCode", siteCode);
			if(level == 1){
				map.put("level", level);
				dataCountList = databaseInfoServiceImpl.queryProvinceCountByMap(map);
				List<DatabaseInfoRequest> dataCountList2 = databaseInfoServiceImpl.queryCountBm(map);
				dataCountList.addAll(dataCountList2);
			}else if(level == 4){
				
			}else{
				Integer flag = 1;//判断是否是部委
				if(siteCode.indexOf("bm")>=0){
					flag = 0;
				}
				map.put("flag", flag);
				if(level == 2){
					map.put("level", level);
					map.put("parentSiteCodeLike", siteCode.subSequence(0, 2));
				}else if(level == 3){
					map.put("level", level);
					map.put("parentSiteCodeLike", siteCode.subSequence(0, 4));
				}
				dataCountList = databaseInfoServiceImpl.queryProvinceCountByMap(map);
			}
			for (DatabaseInfoRequest dataCount : dataCountList) {
				System.out.println(dataCount.getSiteCode()+"="+dataCount.getName()+"="+dataCount.getSpotCount()+"="+dataCount.getShutDownNum()+"="+dataCount.getExcepNum()+"="+dataCount.getNoScanNum()+"="+dataCount.getIsScanNum());
			}
			System.out.println("=====================end===================");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int level = 1;
		String siteCode = "bm0100";
		try {
			List<DatabaseInfoRequest> dataCountList = null;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("siteCode", siteCode);
			if(level == 1){
				map.put("level", level);
				dataCountList = databaseInfoServiceImpl.queryProvinceCountByMap(map);
				List<DatabaseInfoRequest> dataCountList2 = databaseInfoServiceImpl.queryCountBm(map);
				dataCountList.addAll(dataCountList2);
			}else if(level == 4){
				
			}else{
				Integer flagBw = 1;//判断是否是部委
				if(siteCode.indexOf("bm")==0){
					flagBw = 0;
				}
				Integer flagZh = 1;//判断是否直辖市或兵团
				if(siteCode.startsWith("BT") || siteCode.startsWith("11") || siteCode.startsWith("31") || siteCode.startsWith("12") || siteCode.startsWith("50")){
					flagZh = 0;
				}
				map.put("flagBw", flagBw);
				map.put("flagZh", flagZh);
				if(level == 2){
					map.put("level", level);
					if(flagZh == 0){//直辖市下组织
						map.put("parentSiteCodeLike", siteCode.substring(0, 2));
					}else{
						if(flagBw == 0){
							map.put("parentSiteCodeLike", siteCode.substring(0, 4));
						}else{
							map.put("parentSiteCodeLike", siteCode.substring(0, 2));
						}
					}
				}else if(level == 3){
					map.put("level", level);
					map.put("parentSiteCodeLike", siteCode.substring(0, 4));
				}
				dataCountList = databaseInfoServiceImpl.queryProvinceCountByMap(map);
				for (DatabaseInfoRequest databaseInfoRequest : dataCountList) {
					System.out.println(databaseInfoRequest.getSiteCode()+"="+databaseInfoRequest.getSpotCount()+"="+databaseInfoRequest.getShutDownNum()+"="+databaseInfoRequest.getExcepNum()+"="+databaseInfoRequest.getNoScanNum()+"="+databaseInfoRequest.getIsScanNum());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	/*public void testGetSime(){
		String siteCode = "bm0100";//省级组织单位编码
		System.out.println("-==-=--=-=-=====-=-=-=-="+siteCode);
		try {
			DetectionOrgCountRequest detectionOrgCountRequest = new DetectionOrgCountRequest();
			detectionOrgCountRequest.setSiteCode(siteCode);
			detectionOrgCountRequest.setScanDate(DateUtils.getYesterdayStr());
			detectionOrgCountRequest.setType("0");
			List<DetectionOrgCount> detectionOrgCount = detectionOrgCountServiceImpl.queryList(detectionOrgCountRequest);
			for (DetectionOrgCount detectionOrgCount2 : detectionOrgCount) {
				System.out.println(detectionOrgCount2.getLeadYesterdayRate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
