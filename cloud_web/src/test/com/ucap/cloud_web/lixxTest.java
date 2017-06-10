package com.ucap.cloud_web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseLink;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseLinkService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.util.HttpRequestProxy;


public class lixxTest extends BaseTest{


	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IDatabaseLinkService databaseLinkServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	// @Autowired
	// private IUserLogService userLogServiceImpl;
	
	
//	private Properties prop = PropertiesUtil.getProperties("app.properties");
	
	
	public void test (){
		
//		String jdbc = "jdbc:mysql://192.168.1.39:3306/cloud";
//		String user = "ecm";
//		String passWord = "ecm";
//		String tableName = "database_spot_info"; 
//		String path= "D:/Program/workspace/trunk/cloud/cloud_web/src/main/java/com/ucap/cloud_web/";
//		String author = "lixx";
//		CreateEntityUitls.entity(jdbc, user, passWord, tableName, path, author);
//		CreateBeanUitls.createBean(jdbc, user, passWord, tableName, path, author);
//		System.out.println(">>>>>>>>>>>>>>>>>");
		
		
//		//生成link表
//		getLink();
		
		
		//生成info
//		getInfo();
		
		
		//解析栏目
//		getChannel();
		
		//抽查挂接关系
		getSpot();
		
//		DatabaseSpotInfo databaseSpotInfo = new DatabaseSpotInfo();
//		databaseSpotInfo.setParentId(11111111);
//		databaseSpotInfo.setOrgSiteCode("222222222");
//		databaseSpotInfo.setSiteCode("3333333333");
//		databaseSpotInfo.setIsexp(4);
//		databaseSpotInfo.setIsorganizational(5);
//		databaseSpotInfoServiceImpl.add(databaseSpotInfo);
//		UserLog userLog = new UserLog();
//		userLog.setSiteCode("2222222222222");
//		userLog.setProvince("test");
//		userLog.setCity("test");
//		userLog.setCounty("test");
//		userLogServiceImpl.add(userLog);
		
	}
	
	public void getSpot(){
		
		DatabaseOrgInfoRequest request = new DatabaseOrgInfoRequest();
		request.setPageSize(Integer.MAX_VALUE);
		List<DatabaseOrgInfo> list = databaseOrgInfoServiceImpl.queryList(request);
		String diShui = "11BM010009,11BM010011,11BM010012,11BM010021,11BM010010,11BM010018,11BM010017,11BM010016,11BM010019,11BM010022,11BM010013,11BM010025,11BM010004,11BM010007,11BM010023,11BM010015,11BM010008,11BM010014,11BM010024,11BM010005,11BM010001,11BM010002,11BM010020,11BM010003,11BM010006,12BM010001,12BM010007,12BM010003,12BM010006,1305000048,13BM010005,13BM010010,13BM010011,13BM010006,13BM010002,13BM010008,13BM010001,13BM010012,13BM010004,13BM010003,13BM010007,13BM010009,1400000085,1400000084,1400000089,1408810062,15BM010001,15BM010034,15BM010012,15BM010019,15BM010007,15BM010008,15BM010010,15BM010004,2100000043,2101000044,2102000011,2103000017,2104000053,2105000040,2106000040,2107000045,2108000016,2109000030,2110000046,2110000048,2111000003,2112000042,2112240002,2113000023,2114000045,22BM010001,22BM010008,22BM010007,22BM010009,22BM010003,22BM010014,22BM010011,22BM010004,22BM010012,22BM010010,22BM010013,22BM010002,22BM010006,22BM010005,2300000030,2301000039,2307810047,3101150101,3200000050,3201000007,3202000009,3203000017,3203210041,3204000096,3205000045,3205900039,3206000014,3207000026,3208000028,3209000004,3210000036,3211000055,3212000008,3213000063,3300000032,3301000071,3301000058,3301090038,3301220049,3301850004,3302000089,3302030038,3302040023,3302050006,3302060019,3302110043,3302120057,3302260053,3302820062,3302830042,3302910007,3303000036,3303220014,3303260039,3303270044,3303280004,3303810005,3303820034,3304000034,3304210023,3304240031,3304810027,3304820041,3304830032,3305000009,3305210001,3306000036,3306210001,3306240011,3306810019,3306820023,3307810024,3307820034,3307830031,3308000052,3308220006,3308240017,3308250021,3309000007,3309020037,3309030038,3309210007,3309220021,3310000006,3310020002,3310030009,3310040014,3310210014,3310220046,3310240020,3310810015,3311000024,3311210003,3311230016,3400000014,3401000063,3402000057,3403000002,3404000020,3404210044,3406000064,3407000051,3408000046,3410000047,3411000025,3412000013,3413000020,3415000068,3416000002,3417000003,3418000053,3502000048,3507240014,35BM010002,35BM010006,35BM010003,35BM010004,35BM010007,35BM010005,35BM010009,35BM010010,35BM010008,35BM010001,3600000042,3602000039,3603000007,3604000015,3605000044,3606000039,3606810026,3607000030,3608000043,3609000009,3610000021,3611000035,37BM010018,37BM010016,37BM010002,37BM010009,37BM010004,37BM010003,37BM010015,37BM010006,37BM010008,37BM010017,37BM010007,37BM010014,37BM010010,37BM010013,37BM010001,37BM010011,37BM010012,37BM010005,37BM010032,41BM010238,41BM010086,41BM010074,41BM010057,41BM010029,41BM010130,41BM010016,41BM010121,41BM010035,41BM010089,41BM010120,41BM010148,41BM010023,41BM010141,41BM010066,41BM010030,41BM010093,41BM010049,41BM010132,41BM010171,42BM010100,42BM010033,42BM010023,42BM010070,42BM010067,42BM010069,42BM010015,42BM010077,42BM010019,42BM010102,42BM010058,42BM010006,42BM010040,42BM010034,42BM010060,42BM010037,42BM010025,42BM010010,42BM010024,42BM010032,42BM010076,42BM010063,42BM010106,42BM010013,42BM010028,42BM010003,42BM010087,42BM010079,43BM010007,43BM010015,43BM010004,43BM010002,43BM010014,43BM010003,43BM010013,43BM010008,43BM010011,43BM010009,43BM010001,43BM010005,43BM010012,43BM010006,43BM010010,4400000027,4400000025,4401000087,4402000063,4402820058,4403000077,4403000068,4403000067,4403000040,4403000069,4403000071,4403000070,4403000072,4403000076,4403000074,4403000078,4403000075,4403000073,4404000040,4404900002,4405000047,4406000055,4406060002,4406060045,4407000040,4408000051,4409000036,4412000037,4413000060,4413020048,4414000058,4415000010,4416000035,4417000039,4418000020,4419000128,4420000014,4451000026,4452000077,4453000029,4500000064,4501000051,4502000062,4503000023,4504000047,4505000030,4506000029,4507000050,4508000005,4509000072,4510000027,4511000017,4512000034,4513000026,4514000020,46BM010001,5000000006,5100000050,5101000043,5101050030,5101050028,5101080013,5101150025,5101150031,5101310007,5101840008,5104000028,5105000045,5105040039,5106000036,5106810016,5107000031,5107000096,5107030006,5107250010,5107810014,5108000041,5109000017,5109030020,5111000006,5111020008,5111110005,5111230013,5113000050,5113020070,5113020026,5113220004,5113230007,5113810022,5114000042,5116000038,5119000048,5119020011,5120000002,5132000042,5133000001,5134000032,5201000034,5203000014,5204000009,5226000040,52BM010020,52BM010003,52BM010012,52BM010019,52BM010016,52BM010006,52BM010017,52BM010018,5300000018,5300000059,5301000019,5303000016,5303230010,5303240056,5303810045,5304000021,5305000048,5306000010,5306230040,5306240022,5306290037,5306290037,5307000043,5308000062,5309000046,5309220015,5323000035,5325000051,5325000052,5326000035,5326220050,5326230038,5326260037,5328000040,5329000061,5331000030,5333000044,5333210056,5333230039,5333240043,5334000011,61BM010012,61BM010008,61BM010010,61BM010001,61BM010002,61BM010009,61BM010007,61BM010005,61BM010014,61BM010003,61BM010006,61BM010004,61BM010011,61BM010013,61BM010015,6212240004,62BM010009,62BM010016,62BM010006,62BM010003,62BM010008,62BM010010,62BM010001,62BM010012,62BM010002,62BM010004,62BM010011,62BM010007,62BM010013,62BM010014,62BM010005,62BM010017,63BM010001,63BM010002,63BM010007,63BM010012,63BM010013,63BM010003,63BM010006,63BM010009,63BM010005,63BM010008,63BM010010,63BM010004,63BM010011,6400000069,6400000072,6400000075,6400000078,6400000088,6540900001,65BM010001,65BM010002,65BM010028,65BM010039,65BM010121,65BM010004,65BM010034,65BM010009,65BM010012,65BM010023,65BM010035,65BM010024,65BM010013,65BM010122,65BM010015,65BM010007,65BM010032,65BM010011,65BM010014,65BM010016,65BM010010";
		
		
		String[] strArray={"bm5400"};
		// for (String siteCode : strArray) {
			
//		for (DatabaseOrgInfo databaseOrgInfo : list) {
//			String siteCode = databaseOrgInfo.getSiteCode();
			
//			if(!siteCode.startsWith("bm")&& siteCode.indexOf("BM") < 1){ //地市、区县
				
//				//初始化数据
//				DatabaseSpotInfo databaseSpotInfoShi = new DatabaseSpotInfo();
//				databaseSpotInfoShi.setOrgSiteCode(siteCode);
//				databaseSpotInfoShi.setParentId(0);
//				databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(siteCode));
//				databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//				int id = databaseSpotInfoShi.getId();
//				
//				System.out.println("01地市、区县>>>>>>>"+siteCode+" 网站名称:"+getDatabaseOrgInfoEntity(siteCode));
//				if(siteCode.endsWith("00")&&!siteCode.endsWith("0000")){//地市
//					
//					// 下属地区
//					DatabaseInfoRequest requestDatabaseInfo = new DatabaseInfoRequest();
//					requestDatabaseInfo.setSiteCodeLike(siteCode.substring(0, 4));
//					requestDatabaseInfo.setNotLikeSiteCode("BM");
//					requestDatabaseInfo.setGroupBy("city,county");
//					requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//					List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//					
//					for (DatabaseInfo databaseInfo : queryList) {
//						databaseSpotInfoShi = new DatabaseSpotInfo();
//						databaseSpotInfoShi.setOrgSiteCode(siteCode);
//						databaseSpotInfoShi.setParentId(id);
//						
//						if(databaseInfo.getSiteCode().substring(0, 6).endsWith("00")){
//							databaseSpotInfoShi.setSiteCode(databaseInfo.getSiteCode().substring(0, 4));
//							databaseSpotInfoShi.setName("市级网站");
//						}else {
//							databaseSpotInfoShi.setSiteCode(databaseInfo.getSiteCode().substring(0, 6));
//							databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(databaseInfo.getSiteCode().substring(0, 6)));
//						}
//						
//						databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//						System.out.println("02地市、区县>>>>>>>"+siteCode+" 区县>>>>>>>>>>>>"+databaseInfo.getSiteCode().substring(0, 6)+" 网站名称:"+getDatabaseOrgInfoEntity(databaseInfo.getSiteCode().substring(0, 6)));
//						int xianId = databaseSpotInfoShi.getId();
//						
//						requestDatabaseInfo = new DatabaseInfoRequest();
//						requestDatabaseInfo.setSiteCodeLike(databaseInfo.getSiteCode().substring(0, 6));
//						requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//						List<DatabaseInfo> databaseInfoXian = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//						for (DatabaseInfo databaseInfo2 : databaseInfoXian) {
//							DatabaseSpotInfo databaseSpotInfoXian = new DatabaseSpotInfo();
//							databaseSpotInfoXian.setOrgSiteCode(siteCode);
//							databaseSpotInfoXian.setParentId(xianId);
//							databaseSpotInfoXian.setSiteCode(databaseInfo2.getSiteCode());
//							
//							DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//							databaseSpotInfoXian.setName(databaseInfoObj.getName());
//							databaseSpotInfoXian.setIsexp(databaseInfoObj.getIsexp());
//							databaseSpotInfoXian.setIsorganizational(databaseInfoObj.getIsorganizational());
//							
//							databaseSpotInfoServiceImpl.add(databaseSpotInfoXian);
//							System.out.println("03地市、区县>>>>>>>"+siteCode+" 区县>>>>>>>>>>>>"+databaseInfo.getSiteCode().substring(0, 6)+" 站点>>>>>>"+databaseInfo2.getSiteCode()+" 网站名称:"+databaseInfoObj.getName());
//						}
//					}
//					
//				} else if(!siteCode.endsWith("00")){ //区县
//					
//					DatabaseInfoRequest requestDatabaseInfo = new DatabaseInfoRequest();
//					requestDatabaseInfo.setSiteCodeLike(siteCode);
//					requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//					List<DatabaseInfo> databaseInfoXian = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//					
//					//System.out.println("04区县>>>>>>>"+siteCode+" 网站名称:"+getDatabaseOrgInfoEntity(siteCode));
//					
//					for (DatabaseInfo databaseInfo2 : databaseInfoXian) {
//						DatabaseSpotInfo databaseSpotInfoXian = new DatabaseSpotInfo();
//						databaseSpotInfoXian.setOrgSiteCode(siteCode);
//						databaseSpotInfoXian.setParentId(id);
//						databaseSpotInfoXian.setSiteCode(databaseInfo2.getSiteCode());
//						
//						DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//						databaseSpotInfoXian.setName(databaseInfoObj.getName());
//						databaseSpotInfoXian.setIsexp(databaseInfoObj.getIsexp());
//						databaseSpotInfoXian.setIsorganizational(databaseInfoObj.getIsorganizational());
//						
//						databaseSpotInfoServiceImpl.add(databaseSpotInfoXian);
//						System.out.println("05区县>>>>>>>"+siteCode+" 站点>>>>>>"+databaseInfo2.getSiteCode()+" 网站名称:"+databaseInfoObj.getName());
//					}
//					
//				} else {
//					if(siteCode.startsWith("11")||siteCode.startsWith("12")||siteCode.startsWith("31")||siteCode.startsWith("50")){ //直辖市
//						
//						// 下属部门
//						HashMap<String, Object> hashMap = new HashMap<String, Object>();
//						hashMap.put("siteCodeLike", siteCode.substring(0, 2));
//						hashMap.put("level", '2');
//						hashMap.put("city", "city");
//						List<DatabaseInfo> queryList = databaseInfoServiceImpl.getLocalData(hashMap);
//						
//						for (DatabaseInfo databaseInfo : queryList) {
//							if(databaseInfo.getSiteCode().indexOf("BM")<1){
//								databaseSpotInfoShi = new DatabaseSpotInfo();
//								databaseSpotInfoShi.setOrgSiteCode(siteCode);
//								databaseSpotInfoShi.setParentId(id);
//								
//								if(databaseInfo.getSiteCode().substring(0, 6).endsWith("00")){
//									databaseSpotInfoShi.setSiteCode(databaseInfo.getSiteCode().substring(0, 4));
//									databaseSpotInfoShi.setName("市级网站");
//								} else {
//									databaseSpotInfoShi.setSiteCode(databaseInfo.getSiteCode().substring(0, 6));
//									databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(databaseInfo.getSiteCode().substring(0, 6)));
//								}
//								
//								databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//								System.out.println("06直辖市>>>>>>>"+siteCode+" 网站名称:"+getDatabaseOrgInfoEntity(siteCode));
//								int xianId = databaseSpotInfoShi.getId();
//								
//								
//								DatabaseInfoRequest requestDatabaseInfo = new DatabaseInfoRequest();
//								requestDatabaseInfo.setSiteCodeLike(databaseInfo.getSiteCode().substring(0, 6));
//								requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//								List<DatabaseInfo> databaseInfoXian = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//								for (DatabaseInfo databaseInfo2 : databaseInfoXian) {
//									DatabaseSpotInfo databaseSpotInfoXian = new DatabaseSpotInfo();
//									databaseSpotInfoXian.setOrgSiteCode(siteCode);
//									databaseSpotInfoXian.setParentId(xianId);
//									databaseSpotInfoXian.setSiteCode(databaseInfo2.getSiteCode());
//									
//									DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//									databaseSpotInfoXian.setName(databaseInfoObj.getName());
//									databaseSpotInfoXian.setIsexp(databaseInfoObj.getIsexp());
//									databaseSpotInfoXian.setIsorganizational(databaseInfoObj.getIsorganizational());
//									
//									databaseSpotInfoServiceImpl.add(databaseSpotInfoXian);
//									System.out.println("07直辖市>>>>>>>"+siteCode+" 站点>>>>>>"+databaseInfo2.getSiteCode()+" 网站名称:"+databaseInfoObj.getName());
//								}
//							}
//						}
//						
//					}else { //省份
//						
//						// 下属地市
//						DatabaseInfoRequest requestDatabaseInfo = new DatabaseInfoRequest();
//						requestDatabaseInfo.setSiteCodeLike(siteCode.substring(0, 2));
//						requestDatabaseInfo.setNotLikeSiteCode("BM");
//						requestDatabaseInfo.setGroupBy("city,county");
//						requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//						//System.out.println("08省份>>>>>>>"+siteCode+" 网站名称:"+getDatabaseOrgInfoEntity(siteCode));
//						List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//						for (DatabaseInfo databaseInfo : queryList) {
//							if(databaseInfo.getSiteCode().substring(0,6).endsWith("00")){
//								
//								String siteCodeDiShi = databaseInfo.getSiteCode().substring(0,6);
//								
//								System.out.println("09省份>>>>>>>"+siteCode+" 地市>>>>>"+siteCodeDiShi+" 网站名称:"+getDatabaseOrgInfoEntity(databaseInfo.getSiteCode().substring(0, 6)));
//								
//								if(siteCodeDiShi.endsWith("0000")){//省份本级
//									requestDatabaseInfo = new DatabaseInfoRequest();
//									requestDatabaseInfo.setSiteCodeLike(siteCodeDiShi);
//									requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//									List<DatabaseInfo> queryList3 = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//									
//									databaseSpotInfoShi = new DatabaseSpotInfo();
//									databaseSpotInfoShi.setOrgSiteCode(siteCode);
//									databaseSpotInfoShi.setParentId(id);
//									databaseSpotInfoShi.setSiteCode(siteCodeDiShi.substring(0,4));
////									databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(siteCodeDiShi)+"站点");
//									databaseSpotInfoShi.setName("省级站点");
//									databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//									int benjiId = databaseSpotInfoShi.getId();
//									
//									for (DatabaseInfo databaseInfo2 : queryList3) {
//										databaseSpotInfoShi = new DatabaseSpotInfo();
//										databaseSpotInfoShi.setOrgSiteCode(siteCode);
//										databaseSpotInfoShi.setParentId(benjiId);
//										databaseSpotInfoShi.setSiteCode(databaseInfo2.getSiteCode());
//										
//										DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//										databaseSpotInfoShi.setName(databaseInfoObj.getName());
//										databaseSpotInfoShi.setIsexp(databaseInfoObj.getIsexp());
//										databaseSpotInfoShi.setIsorganizational(databaseInfoObj.getIsorganizational());
//										
//										databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//										System.out.println("10省份本级 >>>>>>>"+siteCode+" 站点>>>>>"+databaseInfo2.getSiteCode()+" 网站名称:"+databaseInfoObj.getName());
//									}
//									
//								} else if(siteCodeDiShi.endsWith("00")&&!siteCodeDiShi.endsWith("0000")){//地市
//									
//									databaseSpotInfoShi = new DatabaseSpotInfo();
//									databaseSpotInfoShi.setOrgSiteCode(siteCode);
//									databaseSpotInfoShi.setParentId(id);
//									databaseSpotInfoShi.setSiteCode(databaseInfo.getSiteCode().substring(0,6));
//									databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(databaseInfo.getSiteCode().substring(0, 6)));
//									databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//									int shiId = databaseSpotInfoShi.getId();
//									
//									// 下属地区
//									requestDatabaseInfo = new DatabaseInfoRequest();
//									requestDatabaseInfo.setSiteCodeLike(siteCodeDiShi.substring(0, 4));
//									requestDatabaseInfo.setNotLikeSiteCode("BM");
//									requestDatabaseInfo.setGroupBy("city,county");
//									requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//									List<DatabaseInfo> queryList2 = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//									
//									for (DatabaseInfo databaseInfo3 : queryList2) {
//										if(databaseInfo3.getSiteCode().substring(0, 6).endsWith("00")){//地市本级
//											requestDatabaseInfo = new DatabaseInfoRequest();
//											requestDatabaseInfo.setSiteCodeLike(databaseInfo3.getSiteCode().substring(0, 6));
//											requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//											List<DatabaseInfo> queryList3 = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//											
//											databaseSpotInfoShi = new DatabaseSpotInfo();
//											databaseSpotInfoShi.setOrgSiteCode(siteCode);
//											databaseSpotInfoShi.setParentId(shiId);
//											databaseSpotInfoShi.setSiteCode(databaseInfo3.getSiteCode().substring(0,4));
////											databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(databaseInfo3.getSiteCode().substring(0,6))+"站点");
//											databaseSpotInfoShi.setName("市级站点");
//											databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//											int benjiId = databaseSpotInfoShi.getId();
//											
//											for (DatabaseInfo databaseInfo2 : queryList3) {
//												databaseSpotInfoShi = new DatabaseSpotInfo();
//												databaseSpotInfoShi.setOrgSiteCode(siteCode);
//												databaseSpotInfoShi.setParentId(benjiId);
//												databaseSpotInfoShi.setSiteCode(databaseInfo2.getSiteCode());
//												
//												DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//												databaseSpotInfoShi.setName(databaseInfoObj.getName());
//												databaseSpotInfoShi.setIsexp(databaseInfoObj.getIsexp());
//												databaseSpotInfoShi.setIsorganizational(databaseInfoObj.getIsorganizational());
//												
//												databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//												System.out.println("11省份 >>>>>>>"+siteCode+" 地市本级>>>>>"+databaseInfo3.getSiteCode().substring(0, 6)+" 站点>>>>>"+databaseInfo2.getSiteCode()+" 网站名称:"+databaseInfoObj.getName());
//											}
//											
//										}else if(!databaseInfo3.getSiteCode().substring(0, 6).endsWith("00")){//下级
//											databaseSpotInfoShi = new DatabaseSpotInfo();
//											databaseSpotInfoShi.setOrgSiteCode(siteCode);
//											databaseSpotInfoShi.setParentId(shiId);
//											databaseSpotInfoShi.setSiteCode(databaseInfo3.getSiteCode().substring(0, 6));
//											databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(databaseInfo3.getSiteCode().substring(0, 6)));
//											databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//											System.out.println("12省份>>>>>>>"+siteCode+" 地市>>>>>"+siteCodeDiShi+" 区县>>>>>"+databaseInfo3.getSiteCode().substring(0, 6)+" 网站名称:"+getDatabaseOrgInfoEntity(databaseInfo.getSiteCode().substring(0, 6)));
//											int xianId = databaseSpotInfoShi.getId();
//											
//											
//											requestDatabaseInfo = new DatabaseInfoRequest();
//											requestDatabaseInfo.setSiteCodeLike(databaseInfo3.getSiteCode().substring(0, 6));
//											requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//											List<DatabaseInfo> databaseInfoXian = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//											for (DatabaseInfo databaseInfo2 : databaseInfoXian) {
//												DatabaseSpotInfo databaseSpotInfoXian = new DatabaseSpotInfo();
//												databaseSpotInfoXian.setOrgSiteCode(siteCode);
//												databaseSpotInfoXian.setParentId(xianId);
//												databaseSpotInfoXian.setSiteCode(databaseInfo2.getSiteCode());
//												
//												DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//												databaseSpotInfoXian.setName(databaseInfoObj.getName());
//												databaseSpotInfoXian.setIsexp(databaseInfoObj.getIsexp());
//												databaseSpotInfoXian.setIsorganizational(databaseInfoObj.getIsorganizational());
//												
//												databaseSpotInfoServiceImpl.add(databaseSpotInfoXian);
//												System.out.println("13省份>>>>>>>"+siteCode+" 地市>>>>>"+siteCodeDiShi+" 区县>>>>>"+databaseInfo3.getSiteCode().substring(0, 6)+" 站点>>>>>>"+databaseInfo2.getSiteCode()+" 网站名称:"+databaseInfoObj.getName());
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
				
//			} else {//部委
//				DatabaseSpotInfo databaseSpotInfoShi = new DatabaseSpotInfo();
//				databaseSpotInfoShi.setOrgSiteCode(siteCode);
//				databaseSpotInfoShi.setParentId(0);
//				databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(siteCode));
//				databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//				int id = databaseSpotInfoShi.getId();
//				
//				if("bm0100".equals(siteCode)){
//					System.out.println("部委>>>>>>>"+siteCode);
//					HashMap<String, Object> hashMapM = new HashMap<String, Object>();
//					hashMapM.put("sheng", "sheng");
//					List<DatabaseInfo> queryList = databaseInfoServiceImpl.getLocalData(hashMapM);
//					for (DatabaseInfo databaseInfo : queryList) {
//						databaseSpotInfoShi = new DatabaseSpotInfo();
//						databaseSpotInfoShi.setOrgSiteCode(siteCode);
//						databaseSpotInfoShi.setParentId(id);
//						databaseSpotInfoShi.setSiteCode(databaseInfo.getSiteCode());
//						
//						DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo.getSiteCode());
//						databaseSpotInfoShi.setName(databaseInfoObj.getName());
//						databaseSpotInfoShi.setIsexp(databaseInfoObj.getIsexp());
//						databaseSpotInfoShi.setIsorganizational(databaseInfoObj.getIsorganizational());
//						
//						databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//						System.out.println("部委>>>>>>>"+siteCode+" 站点>>>>>>"+databaseInfo.getSiteCode());
//					}
//					
//				} else if(siteCode.startsWith("bm1900")){
//					
//						databaseSpotInfoShi = new DatabaseSpotInfo();
//						databaseSpotInfoShi.setOrgSiteCode(siteCode);
//						databaseSpotInfoShi.setParentId(id);
//						databaseSpotInfoShi.setSiteCode("bm1901");
//						databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity("bm1901"));
//						databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//						int haishiId = databaseSpotInfoShi.getId();
//						
//						databaseSpotInfoShi = new DatabaseSpotInfo();
//						databaseSpotInfoShi.setOrgSiteCode(siteCode);
//						databaseSpotInfoShi.setParentId(id);
//						databaseSpotInfoShi.setSiteCode("bm1940");
//						databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity("bm1940"));
//						databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//						int hangWuId = databaseSpotInfoShi.getId();
//						
//						DatabaseOrgInfoRequest databaseOrgInfoRequest = new DatabaseOrgInfoRequest();
//						databaseOrgInfoRequest.setSiteCodeLike("bm19");
//						databaseOrgInfoRequest.setPageSize(Integer.MAX_VALUE);
//						List<DatabaseOrgInfo> databaseOrgInfoList = databaseOrgInfoServiceImpl.queryList(databaseOrgInfoRequest);
//						for (DatabaseOrgInfo databaseOrgInfo2 : databaseOrgInfoList) {
//							
//							databaseSpotInfoShi = new DatabaseSpotInfo();
//							databaseSpotInfoShi.setOrgSiteCode(siteCode);
//							int xianId = 0;
//							if(databaseOrgInfo2.getSiteCode().startsWith("bm1941")||databaseOrgInfo2.getSiteCode().startsWith("bm1942")){
//								databaseSpotInfoShi.setParentId(hangWuId);
//								databaseSpotInfoShi.setSiteCode(databaseOrgInfo2.getSiteCode());
//								databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(databaseOrgInfo2.getSiteCode()));
//								databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//								xianId = databaseSpotInfoShi.getId();
//							}else if(!databaseOrgInfo2.getSiteCode().equals("bm1900")&&!databaseOrgInfo2.getSiteCode().equals("bm1901")&&!databaseOrgInfo2.getSiteCode().equals("bm1940")){
//								databaseSpotInfoShi.setParentId(haishiId);
//								databaseSpotInfoShi.setSiteCode(databaseOrgInfo2.getSiteCode());
//								databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(databaseOrgInfo2.getSiteCode()));
//								databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//								xianId = databaseSpotInfoShi.getId();
//							}
//							if(xianId !=0){
//								DatabaseInfoRequest requestDatabaseInfo = new DatabaseInfoRequest();
//								requestDatabaseInfo.setSiteCodeLike(databaseOrgInfo2.getSiteCode());
//								requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//								List<DatabaseInfo> queryList3 = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//								for (DatabaseInfo databaseInfo2 : queryList3) {
//									databaseSpotInfoShi = new DatabaseSpotInfo();
//									databaseSpotInfoShi.setOrgSiteCode(siteCode);
//									databaseSpotInfoShi.setParentId(xianId);
//									databaseSpotInfoShi.setSiteCode(databaseInfo2.getSiteCode());
//									DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//									databaseSpotInfoShi.setName(databaseInfoObj.getName());
//									databaseSpotInfoShi.setIsexp(databaseInfoObj.getIsexp());
//									databaseSpotInfoShi.setIsorganizational(databaseInfoObj.getIsorganizational());
//									
//									databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//									System.out.println("部委下级>>>>>>>"+siteCode+" 站点>>>>>>"+databaseInfo2.getSiteCode());
//								}
//							}
//							
//						}
//						
//				} else if(siteCode.startsWith("bm2900")){
//					
//					DatabaseOrgInfoRequest orgRequest = new DatabaseOrgInfoRequest();
//					orgRequest.setSiteCodeLike(siteCode.substring(0, 4));
//					orgRequest.setPageSize(Integer.MAX_VALUE);
//					List<DatabaseOrgInfo> databaseOrgInfoList = databaseOrgInfoServiceImpl.queryList(orgRequest);
//					for (DatabaseOrgInfo databaseOrgInfo1 : databaseOrgInfoList) {
//						if(databaseOrgInfo1.getSiteCode().equals(siteCode)){
//							
//							databaseSpotInfoShi = new DatabaseSpotInfo();
//							databaseSpotInfoShi.setOrgSiteCode(siteCode);
//							databaseSpotInfoShi.setParentId(id);
//							databaseSpotInfoShi.setSiteCode(siteCode);
//							databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(siteCode));
//							databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//							int bmID = databaseSpotInfoShi.getId();
//							
//							System.out.println("部委本级>>>>>>>"+siteCode);
//							DatabaseInfoRequest requestDatabaseInfo = new DatabaseInfoRequest();
//							requestDatabaseInfo.setSiteCodeLike(siteCode);
//							requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//							List<DatabaseInfo> queryList3 = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//							for (DatabaseInfo databaseInfo2 : queryList3) {
//								databaseSpotInfoShi = new DatabaseSpotInfo();
//								databaseSpotInfoShi.setOrgSiteCode(siteCode);
//								databaseSpotInfoShi.setParentId(bmID);
//								databaseSpotInfoShi.setSiteCode(databaseInfo2.getSiteCode());
//								
//								DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//								databaseSpotInfoShi.setName(databaseInfoObj.getName());
//								databaseSpotInfoShi.setIsexp(databaseInfoObj.getIsexp());
//								databaseSpotInfoShi.setIsorganizational(databaseInfoObj.getIsorganizational());
//								
//								databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//								System.out.println("部委本级>>>>>>>"+siteCode+" 站点>>>>>>"+databaseInfo2.getSiteCode());
//							}
//							
//						} else {
//							databaseSpotInfoShi = new DatabaseSpotInfo();
//							databaseSpotInfoShi.setOrgSiteCode(siteCode);
//							databaseSpotInfoShi.setParentId(id);
//							databaseSpotInfoShi.setSiteCode(databaseOrgInfo1.getSiteCode());
//							databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(databaseOrgInfo1.getSiteCode()));
//							databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//							int shiId = databaseSpotInfoShi.getId();
//							
//							String str = "";
//							if("bm2901".equals(databaseOrgInfo1.getSiteCode())){
//								str = "11BM010009,11BM010011,11BM010012,11BM010021,11BM010010,11BM010018,11BM010017,11BM010016,11BM010019,11BM010022,11BM010013,11BM010025,11BM010004,11BM010007,11BM010023,11BM010015,11BM010008,11BM010014,11BM010024,11BM010005,11BM010001,11BM010002,11BM010020,11BM010003,11BM010006";
//							}else if("bm2902".equals(databaseOrgInfo1.getSiteCode())){
//								str = "12BM010001,12BM010007,12BM010003,12BM010006";
//							} else if("bm2903".equals(databaseOrgInfo1.getSiteCode())){
//								str = "1305000048,13BM010005,13BM010010,13BM010011,13BM010006,13BM010002,13BM010008,13BM010001,13BM010012,13BM010004,13BM010003,13BM010007,13BM010009";
//							}else if("bm2904".equals(databaseOrgInfo1.getSiteCode())){
//								str = "1400000085,1400000084,1400000089,1408810062";
//							}else if("bm2905".equals(databaseOrgInfo1.getSiteCode())){
//								str = "15BM010001,15BM010034,15BM010012,15BM010019,15BM010007,15BM010008,15BM010010,15BM010004";
//							}else if("bm2906".equals(databaseOrgInfo1.getSiteCode())){
//								str = "2100000043,2101000044,2102000011,2103000017,2104000053,2105000040,2106000040,2107000045,2108000016,2109000030,2110000046,2110000048,2111000003,2112000042,2112240002,2113000023,2114000045";
//							}else if("bm2907".equals(databaseOrgInfo1.getSiteCode())){
//								str = "22BM010001,22BM010008,22BM010007,22BM010009,22BM010003,22BM010014,22BM010011,22BM010004,22BM010012,22BM010010,22BM010013,22BM010002,22BM010006,22BM010005";
//							}else if("bm2908".equals(databaseOrgInfo1.getSiteCode())){
//								str = "2300000030,2301000039,2307810047";
//							}else if("bm2909".equals(databaseOrgInfo1.getSiteCode())){
//								str = "3101150101";
//							}else if("bm2910".equals(databaseOrgInfo1.getSiteCode())){
//								str = "3200000050,3201000007,3202000009,3203000017,3203210041,3204000096,3205000045,3205900039,3206000014,3207000026,3208000028,3209000004,3210000036,3211000055,3212000008,3213000063";
//							}else if("bm2911".equals(databaseOrgInfo1.getSiteCode())){
//								str = "3300000032,3301000071,3301000058,3301090038,3301220049,3301850004,3302000089,3302030038,3302040023,3302050006,3302060019,3302110043,3302120057,3302260053,3302820062,3302830042,3302910007,3303000036,3303220014,3303260039,3303270044,3303280004,3303810005,3303820034,3304000034,3304210023,3304240031,3304810027,3304820041,3304830032,3305000009,3305210001,3306000036,3306210001,3306240011,3306810019,3306820023,3307810024,3307820034,3307830031,3308000052,3308220006,3308240017,3308250021,3309000007,3309020037,3309030038,3309210007,3309220021,3310000006,3310020002,3310030009,3310040014,3310210014,3310220046,3310240020,3310810015,3311000024,3311210003,3311230016";
//							}else if("bm2912".equals(databaseOrgInfo1.getSiteCode())){
//								str = "3400000014,3401000063,3402000057,3403000002,3404000020,3404210044,3406000064,3407000051,3408000046,3410000047,3411000025,3412000013,3413000020,3415000068,3416000002,3417000003,3418000053";
//							}else if("bm2913".equals(databaseOrgInfo1.getSiteCode())){
//								str = "3502000048,3507240014,35BM010002,35BM010006,35BM010003,35BM010004,35BM010007,35BM010005,35BM010009,35BM010010,35BM010008,35BM010001";
//							}else if("bm2914".equals(databaseOrgInfo1.getSiteCode())){
//								str = "3600000042,3602000039,3603000007,3604000015,3605000044,3606000039,3606810026,3607000030,3608000043,3609000009,3610000021,3611000035";
//							}else if("bm2915".equals(databaseOrgInfo1.getSiteCode())){
//								str = "37BM010018,37BM010016,37BM010002,37BM010009,37BM010004,37BM010003,37BM010015,37BM010006,37BM010008,37BM010017,37BM010007,37BM010014,37BM010010,37BM010013,37BM010001,37BM010011,37BM010012,37BM010005,37BM010032";
//							}else if("bm2916".equals(databaseOrgInfo1.getSiteCode())){
//								str = "41BM010238,41BM010086,41BM010074,41BM010057,41BM010029,41BM010130,41BM010016,41BM010121,41BM010035,41BM010089,41BM010120,41BM010148,41BM010023,41BM010141,41BM010066,41BM010030,41BM010093,41BM010049,41BM010132,41BM010171";
//							}else if("bm2917".equals(databaseOrgInfo1.getSiteCode())){
//								str = "42BM010100,42BM010033,42BM010023,42BM010070,42BM010067,42BM010069,42BM010015,42BM010077,42BM010019,42BM010102,42BM010058,42BM010006,42BM010040,42BM010034,42BM010060,42BM010037,42BM010025,42BM010010,42BM010024,42BM010032,42BM010076,42BM010063,42BM010106,42BM010013,42BM010028,42BM010003,42BM010087,42BM010079";
//							}else if("bm2918".equals(databaseOrgInfo1.getSiteCode())){
//								str = "43BM010007,43BM010015,43BM010004,43BM010002,43BM010014,43BM010003,43BM010013,43BM010008,43BM010011,43BM010009,43BM010001,43BM010005,43BM010012,43BM010006,43BM010010";
//							}else if("bm2919".equals(databaseOrgInfo1.getSiteCode())){
//								str = "4400000027,4400000025,4401000087,4402000063,4402820058,4403000077,4403000068,4403000067,4403000040,4403000069,4403000071,4403000070,4403000072,4403000076,4403000074,4403000078,4403000075,4403000073,4404000040,4404900002,4405000047,4406000055,4406060002,4406060045,4407000040,4408000051,4409000036,4412000037,4413000060,4413020048,4414000058,4415000010,4416000035,4417000039,4418000020,4419000128,4420000014,4451000026,4452000077,4453000029";
//							}else if("bm2920".equals(databaseOrgInfo1.getSiteCode())){
//								str = "4500000064,4501000051,4502000062,4503000023,4504000047,4505000030,4506000029,4507000050,4508000005,4509000072,4510000027,4511000017,4512000034,4513000026,4514000020";
//							}else if("bm2921".equals(databaseOrgInfo1.getSiteCode())){
//								str = "46BM010001";
//							}else if("bm2922".equals(databaseOrgInfo1.getSiteCode())){
//								str = "5000000006";
//							}else if("bm2923".equals(databaseOrgInfo1.getSiteCode())){
//								str = "5100000050,5101000043,5101050030,5101050028,5101080013,5101150025,5101150031,5101310007,5101840008,5104000028,5105000045,5105040039,5106000036,5106810016,5107000031,5107000096,5107030006,5107250010,5107810014,5108000041,5109000017,5109030020,5111000006,5111020008,5111110005,5111230013,5113000050,5113020070,5113020026,5113220004,5113230007,5113810022,5114000042,5116000038,5119000048,5119020011,5120000002,5132000042,5133000001,5134000032";
//							}else if("bm2924".equals(databaseOrgInfo1.getSiteCode())){
//								str = "5201000034,5203000014,5204000009,5226000040,52BM010020,52BM010003,52BM010012,52BM010019,52BM010016,52BM010006,52BM010017,52BM010018";
//							}else if("bm2925".equals(databaseOrgInfo1.getSiteCode())){
//								str = "5300000018,5300000059,5301000019,5303000016,5303230010,5303240056,5303810045,5304000021,5305000048,5306000010,5306230040,5306240022,5306290037,5306290037,5307000043,5308000062,5309000046,5309220015,5323000035,5325000051,5325000052,5326000035,5326220050,5326230038,5326260037,5328000040,5329000061,5331000030,5333000044,5333210056,5333230039,5333240043,5334000011";
//							}else if("bm2927".equals(databaseOrgInfo1.getSiteCode())){
//								str = "61BM010012,61BM010008,61BM010010,61BM010001,61BM010002,61BM010009,61BM010007,61BM010005,61BM010014,61BM010003,61BM010006,61BM010004,61BM010011,61BM010013,61BM010015";
//							}else if("bm2928".equals(databaseOrgInfo1.getSiteCode())){
//								str = "6212240004,62BM010009,62BM010016,62BM010006,62BM010003,62BM010008,62BM010010,62BM010001,62BM010012,62BM010002,62BM010004,62BM010011,62BM010007,62BM010013,62BM010014,62BM010005,62BM010017";
//							}else if("bm2929".equals(databaseOrgInfo1.getSiteCode())){
//								str = "63BM010001,63BM010002,63BM010007,63BM010012,63BM010013,63BM010003,63BM010006,63BM010009,63BM010005,63BM010008,63BM010010,63BM010004,63BM010011";
//							}else if("bm2930".equals(databaseOrgInfo1.getSiteCode())){
//								str = "6400000069,6400000072,6400000075,6400000078,6400000088";
//							}else if("bm2931".equals(databaseOrgInfo1.getSiteCode())){
//								str = "6540900001,65BM010001,65BM010002,65BM010028,65BM010039,65BM010121,65BM010004,65BM010034,65BM010009,65BM010012,65BM010023,65BM010035,65BM010024,65BM010013,65BM010122,65BM010015,65BM010007,65BM010032,65BM010011,65BM010014,65BM010016,65BM010010";
//							}
//							
//							if(!"".equals(str)){
//								for (int i=0;i<str.split(",").length;i++) {
//									databaseSpotInfoShi = new DatabaseSpotInfo();
//									databaseSpotInfoShi.setOrgSiteCode(siteCode);
//									databaseSpotInfoShi.setParentId(shiId);
//									databaseSpotInfoShi.setSiteCode(str.split(",")[i]);
//				
//									DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(str.split(",")[i]);
//									databaseSpotInfoShi.setName(databaseInfoObj.getName());
//									databaseSpotInfoShi.setIsexp(databaseInfoObj.getIsexp());
//									databaseSpotInfoShi.setIsorganizational(databaseInfoObj.getIsorganizational());
//									
//									databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//								}
//							}
//							
//							
//							System.out.println("部委下级>>>>>>>"+siteCode);
//							DatabaseInfoRequest requestDatabaseInfo = new DatabaseInfoRequest();
//							requestDatabaseInfo.setSiteCodeLike(databaseOrgInfo1.getSiteCode());
//							requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//							List<DatabaseInfo> queryList3 = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//							for (DatabaseInfo databaseInfo2 : queryList3) {
//								databaseSpotInfoShi = new DatabaseSpotInfo();
//								databaseSpotInfoShi.setOrgSiteCode(siteCode);
//								databaseSpotInfoShi.setParentId(shiId);
//								databaseSpotInfoShi.setSiteCode(databaseInfo2.getSiteCode());
//
//								DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//								databaseSpotInfoShi.setName(databaseInfoObj.getName());
//								databaseSpotInfoShi.setIsexp(databaseInfoObj.getIsexp());
//								databaseSpotInfoShi.setIsorganizational(databaseInfoObj.getIsorganizational());
//								
//								databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//								System.out.println("部委下级>>>>>>>"+siteCode+" 站点>>>>>>"+databaseInfo2.getSiteCode());
//							}
//							
//							
//						}
//					}
//					
//					
//				}
//				else if(siteCode.startsWith("bm3400")) {
//					DatabaseOrgInfoRequest orgRequest = new DatabaseOrgInfoRequest();
//					orgRequest.setSiteCodeLike(siteCode.substring(0, 4));
//					orgRequest.setPageSize(Integer.MAX_VALUE);
//					List<DatabaseOrgInfo> databaseOrgInfoList = databaseOrgInfoServiceImpl.queryList(orgRequest);
//					for (DatabaseOrgInfo databaseOrgInfo1 : databaseOrgInfoList) {
//						if(databaseOrgInfo1.getSiteCode().equals(siteCode)){
//							databaseSpotInfoShi = new DatabaseSpotInfo();
//							databaseSpotInfoShi.setOrgSiteCode(siteCode);
//							databaseSpotInfoShi.setParentId(id);
//							databaseSpotInfoShi.setSiteCode(siteCode);
//							databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(siteCode));
//							databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//							int bmID = databaseSpotInfoShi.getId();
//							
//							DatabaseInfoRequest requestDatabaseInfo = new DatabaseInfoRequest();
//							requestDatabaseInfo.setSiteCodeLike(siteCode);
//							requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//							List<DatabaseInfo> queryList3 = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//							for (DatabaseInfo databaseInfo2 : queryList3) {
//								databaseSpotInfoShi = new DatabaseSpotInfo();
//								databaseSpotInfoShi.setOrgSiteCode(siteCode);
//								databaseSpotInfoShi.setParentId(bmID);
//								databaseSpotInfoShi.setSiteCode(databaseInfo2.getSiteCode());
//								
//								DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//								databaseSpotInfoShi.setName(databaseInfoObj.getName());
//								databaseSpotInfoShi.setIsexp(databaseInfoObj.getIsexp());
//								databaseSpotInfoShi.setIsorganizational(databaseInfoObj.getIsorganizational());
//								
//								databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//								System.out.println("部委本级>>>>>>>"+siteCode+" 站点>>>>>>"+databaseInfo2.getSiteCode());
//							}
//						}else {
//							
//						}
//						
//					}
//					
//				}
//				else {
//					DatabaseOrgInfoRequest orgRequest = new DatabaseOrgInfoRequest();
//					orgRequest.setSiteCodeLike(siteCode.substring(0, 4));
//					orgRequest.setPageSize(Integer.MAX_VALUE);
//					List<DatabaseOrgInfo> databaseOrgInfoList = databaseOrgInfoServiceImpl.queryList(orgRequest);
//					System.out.println("》》》》》》》》》》》》》》"+databaseOrgInfoList.size());
//					for (DatabaseOrgInfo databaseOrgInfo1 : databaseOrgInfoList) {
//						if(databaseOrgInfo1.getSiteCode().equals(siteCode)){
//							
//							databaseSpotInfoShi = new DatabaseSpotInfo();
//							databaseSpotInfoShi.setOrgSiteCode(siteCode);
//							databaseSpotInfoShi.setParentId(id);
//							databaseSpotInfoShi.setSiteCode(siteCode);
//							databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(siteCode));
//							databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//							int bmID = databaseSpotInfoShi.getId();
//							
//							System.out.println("部委本级>>>>>>>"+siteCode);
//							DatabaseInfoRequest requestDatabaseInfo = new DatabaseInfoRequest();
//							requestDatabaseInfo.setSiteCodeLike(siteCode);
//							requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//							List<DatabaseInfo> queryList3 = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//							for (DatabaseInfo databaseInfo2 : queryList3) {
//								databaseSpotInfoShi = new DatabaseSpotInfo();
//								databaseSpotInfoShi.setOrgSiteCode(siteCode);
//								databaseSpotInfoShi.setParentId(bmID);
//								databaseSpotInfoShi.setSiteCode(databaseInfo2.getSiteCode());
//								
//								DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//								databaseSpotInfoShi.setName(databaseInfoObj.getName());
//								databaseSpotInfoShi.setIsexp(databaseInfoObj.getIsexp());
//								databaseSpotInfoShi.setIsorganizational(databaseInfoObj.getIsorganizational());
//								
//								databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//								System.out.println("部委本级>>>>>>>"+siteCode+" 站点>>>>>>"+databaseInfo2.getSiteCode());
//							}
//							
//						} else {
//								databaseSpotInfoShi = new DatabaseSpotInfo();
//								databaseSpotInfoShi.setOrgSiteCode(siteCode);
//								databaseSpotInfoShi.setParentId(id);
//								databaseSpotInfoShi.setSiteCode(databaseOrgInfo1.getSiteCode());
//								databaseSpotInfoShi.setName(getDatabaseOrgInfoEntity(databaseOrgInfo1.getSiteCode()));
//								databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//								int shiId = databaseSpotInfoShi.getId();
//							
//							
//								System.out.println("部委下级>>>>>>>"+siteCode);
//								DatabaseInfoRequest requestDatabaseInfo = new DatabaseInfoRequest();
//								requestDatabaseInfo.setSiteCodeLike(databaseOrgInfo1.getSiteCode());
//								requestDatabaseInfo.setPageSize(Integer.MAX_VALUE);
//								List<DatabaseInfo> queryList3 = databaseInfoServiceImpl.queryList(requestDatabaseInfo);
//								for (DatabaseInfo databaseInfo2 : queryList3) {
//									databaseSpotInfoShi = new DatabaseSpotInfo();
//									databaseSpotInfoShi.setOrgSiteCode(siteCode);
//									databaseSpotInfoShi.setParentId(shiId);
//									databaseSpotInfoShi.setSiteCode(databaseInfo2.getSiteCode());
//
//									DatabaseInfo databaseInfoObj = getDatabaseInfoEntity(databaseInfo2.getSiteCode());
//									databaseSpotInfoShi.setName(databaseInfoObj.getName());
//									databaseSpotInfoShi.setIsexp(databaseInfoObj.getIsexp());
//									databaseSpotInfoShi.setIsorganizational(databaseInfoObj.getIsorganizational());
//									
//									databaseSpotInfoServiceImpl.add(databaseSpotInfoShi);
//									System.out.println("部委下级>>>>>>>"+siteCode+" 站点>>>>>>"+databaseInfo2.getSiteCode());
//								}
//							}
//							
//							
//					}
//					
//				}
//				
//			}
//			
//			
//		}
//		System.out.println("组织单位集合大小:"+list.size());
		
	}
	
	public DatabaseInfo getDatabaseInfoEntity(String siteCode){
		DatabaseInfo databaseInfo = databaseInfoServiceImpl.findByDatabaseInfoCode(siteCode);
		return databaseInfo;
	}
	public String getDatabaseOrgInfoEntity(String siteCode){
		DatabaseOrgInfoRequest request = new DatabaseOrgInfoRequest();
		request.setStieCode(siteCode);
		List<DatabaseOrgInfo> list = databaseOrgInfoServiceImpl.queryList(request);
		if(list.size()>0){
			return list.get(0).getName();
		} else {
			return null;
		}
	}
	
	public void getChannel(){
		
		try {
			
			int size = 50 ;
			int x = 1 ;
			for (x = 1; x < 200; x++) {
				String reqUrl = "http://puchatest.kaipuyun.cn:9080/ecmws/api/listSelfCensor";
				String param = "sortAsc=false&sortField=modified&size=" + size + "&pos=" + x;
				
				String temp = HttpRequestProxy.sendGet(reqUrl, param);
				if (temp != "") {
					JSONObject json = new JSONObject(temp);
					JSONObject jsonObj = json.getJSONObject("items");
					
					JSONArray listJson = jsonObj.getJSONArray("pageObjects");
					for (int i = 0; i < listJson.length(); i++) {
						JSONObject jsonObject = (JSONObject) listJson.get(i);
//						System.out.println(object);
						
//						String obj = "{\"tbdw\":\"城中区民政局\",\"xian\":\"城中区\",\"rootName\":\"xnczq.gov.cn\",\"icp\":\"青ICP备09000835号\",\"lmmc_1_6\":\"无\",\"lmmc_1_7\":\"民政风采\",\"wzmc\":\"城中区民政局\",\"lmdz_1_1\":\"http://mzj.xnczq.gov.cn/mzjj.htm\\nhttp://mzj.xnczq.gov.cn/jgsz/jzjj.htm\",\"lmdz_1_2\":\"http://mzj.xnczq.gov.cn/gzdt.htm\",\"lmdz_1_3\":\"无\",\"lmdz_1_4\":\"http://mzj.xnczq.gov.cn/jcdt.htm\",\"lmdz_1_5\":\"无\",\"inner_lastmodified\":\"2015-4-17 16:8:32\",\"isorganizational\":0,\"shi\":\"西宁市\",\"sheng\":\"青海省\",\"isexp\":5,\"vcode\":\"7P6BVY\",\"inner_uuid\":\"82a0f73e-a3c0-43a2-85ee-4f2a7659e28b-3906544556\",\"lmdz_1_7\":\"http://mzj.xnczq.gov.cn/mzfc.htm\",\"url\":\"http://mzj.xnczq.gov.cn/index.htm\",\"lmdz_1_6\":\"无\",\"lmdz_2_3\":\"无\",\"lmdz_2_4\":\"http://mzj.xnczq.gov.cn/bzxx_list.jsp?urltype=tree.TreeTempUrl&wbtreeid=1084\",\"sitecode\":\"6301030022\",\"lmmc_1_1\":\"民政简介；机构设置\",\"lmdz_2_1\":\"无\",\"lmdz_2_2\":\"无\",\"lmmc_1_3\":\"无\",\"lmmc_2_4\":\"民意信箱\",\"lmmc_1_2\":\"工作动态\",\"lmmc_2_3\":\"无\",\"lmmc_1_5\":\"无\",\"lmmc_2_2\":\"无\",\"lmmc_1_4\":\"政策法规\",\"lmmc_2_1\":\"无\",\"lmmc_3_3\":\"无\",\"lmmc_3_2\":\"无\",\"lmmc_3_1\":\"办事指南\",\"lmdz_3_2\":\"无\",\"lmdz_3_3\":\"无\",\"lmdz_3_1\":\"http://mzj.xnczq.gov.cn/bszn/jzjj.htm\"}";
//						String obj = "{\"tbdw\":\"珠海市香洲区食品药品监督管理局\",\"xian\":\"香洲区\",\"rootName\":\"zhxz.cn\",\"icp\":\"粤ICP备05056941号\",\"lmmc_1_6\":\"公告通告\",\"lmmc_1_7\":\"无\",\"wzmc\":\"香洲区食品药品监督管理局\",\"lmdz_1_1\":\"http://fda.zhxz.cn/zwgk/jgzn/\",\"lmdz_1_2\":\"http://fda.zhxz.cn/dtzx/gzdt/\",\"lmdz_1_3\":\"http://fda.zhxz.cn/dtzx/ggtg/\",\"lmdz_1_4\":\"http://fda.zhxz.cn/zwgk/zcfg/\",\"lmdz_1_5\":\"http://fda.zhxz.cn/dtzx/ggtg/\",\"inner_lastmodified\":\"2015-4-14 15:54:43\",\"isorganizational\":0,\"shi\":\"珠海市\",\"sheng\":\"广东省\",\"isexp\":0,\"vcode\":\"SQNHLY\",\"inner_uuid\":\"dbd07e88-0720-466b-80aa-8b550d9b5466-3440683815\",\"lmdz_1_7\":\"无\",\"url\":\"http://fda.zhxz.cn/\",\"lmdz_1_6\":\"http://fda.zhxz.cn/dtzx/ggtg/\",\"lmdz_2_3\":\"无\",\"lmdz_2_4\":\"无\",\"sitecode\":\"4404020029\",\"lmmc_1_1\":\"机构职能\",\"lmdz_2_1\":\"http://fda.zhxz.cn/wshd/ldxx/；http://www.zhxz.gov.cn/wlwz2011/\",\"lmdz_2_2\":\"http://www.zhxz.gov.cn/wlwz2011/\",\"lmmc_1_3\":\"公告通告\",\"lmmc_2_4\":\"无\",\"lmmc_1_2\":\"工作动态\",\"lmmc_2_3\":\"无\",\"lmmc_1_5\":\"公告通告\",\"lmmc_2_2\":\"咨询投诉\",\"lmmc_1_4\":\"政策法规\",\"lmmc_2_1\":\"领导信箱；咨询投诉\",\"lmmc_3_3\":\"无\",\"lmmc_3_2\":\"广东省网上办事大厅珠海香洲区分厅\",\"lmmc_3_1\":\"办事指南\",\"lmdz_3_2\":\"http://xz.zhwsbs.gov.cn/\",\"lmdz_3_3\":\"无\",\"lmdz_3_1\":\"http://fda.zhxz.cn/bsfw/bszn/spscxk/\"}";
//						String obj = "{\"tbdw\":\"广州市花都区农林局\",\"xian\":\"花都区\",\"rootName\":\"huadu.gov.cn\",\"icp\":\"粤ICP备05082023号\",\"lmmc_1_6\":\"无\",\"lmmc_1_7\":\"无\",\"wzmc\":\"广州市花都区农林局\",\"lmdz_1_1\":\"http://www.huadu.gov.cn/web/hdny/zwgk/jgsz/\",\"lmdz_1_2\":\"http://www.huadu.gov.cn/web/hdny/zwgk/gzdt/\",\"lmdz_1_3\":\"http://www.huadu.gov.cn/web/hdny/nydt/ggl/\",\"lmdz_1_4\":\"http://www.huadu.gov.cn/web/hdny/nyfg/\",\"lmdz_1_5\":\"无\",\"inner_lastmodified\":\"2015-4-15 15:47:28\",\"isorganizational\":0,\"shi\":\"广州市\",\"sheng\":\"广东省\",\"isexp\":0,\"vcode\":\"5BVAS6\",\"inner_uuid\":\"9293ad20-deb3-4303-9365-048de84ad8ae-1846896831\",\"lmdz_1_7\":\"无\",\"url\":\"http://www.huadu.gov.cn/web/hdny/sy/\",\"lmdz_1_6\":\"无\",\"lmdz_2_3\":\"无\",\"lmdz_2_4\":\"无\",\"sitecode\":\"4401140027\",\"lmmc_1_1\":\"机构设置\",\"lmdz_2_1\":\"http://www.huadu.gov.cn/web/hdny/bmxx/\",\"lmdz_2_2\":\"http://www.huadu.gov.cn/web/hdny/dczj/\",\"lmmc_1_3\":\"公告栏\",\"lmmc_2_4\":\"无\",\"lmmc_1_2\":\"工作动态\",\"lmmc_2_3\":\"无\",\"lmmc_1_5\":\"无\",\"lmmc_2_2\":\"调查征集\",\"lmmc_1_4\":\"农业法规\",\"lmmc_2_1\":\"部门信箱\",\"lmmc_3_3\":\"无\",\"lmmc_3_2\":\"无\",\"lmmc_3_1\":\"办事指南\",\"lmdz_3_2\":\"无\",\"lmdz_3_3\":\"无\",\"lmdz_3_1\":\"http://www.huadu.gov.cn/web/hdny/zwgk/bszn/\"}";
						
//						JSONObject jsonObject = JSONObject.fromObject(obj);
						System.out.println("首页url:"+jsonObject.getString("url")+"--"+jsonObject.getString("sitecode"));
						
						System.out.println("=========================1=============================");
						//栏目1_1
						String lmmc_1_1  = splitString("1_1",jsonObject.getString("lmmc_1_1"),jsonObject.getString("lmdz_1_1"));
						//栏目1_2
						String lmmc_1_2  = splitString("1_2",jsonObject.getString("lmmc_1_2"),jsonObject.getString("lmdz_1_2"));
						//栏目1_3
						String lmmc_1_3  = splitString("1_3",jsonObject.getString("lmmc_1_3"),jsonObject.getString("lmdz_1_3"));
						//栏目1_4
						String lmmc_1_4  = splitString("1_4",jsonObject.getString("lmmc_1_4"),jsonObject.getString("lmdz_1_4"));
						//栏目1_5
						String lmmc_1_5  = splitString("1_5",jsonObject.getString("lmmc_1_5"),jsonObject.getString("lmdz_1_5"));
						//栏目1_6
						String lmmc_1_6  = splitString("1_6",jsonObject.getString("lmmc_1_6"),jsonObject.getString("lmdz_1_6"));
						//栏目1_7
						String lmmc_1_7  = splitString("1_7",jsonObject.getString("lmmc_1_7"),jsonObject.getString("lmdz_1_7"));
						
						System.out.println("===========================2===========================");
						
						//栏目2_1
						String lmmc_2_1  = splitString("2_1",jsonObject.getString("lmmc_2_1"),jsonObject.getString("lmdz_2_1"));
						//栏目2_2
						String lmmc_2_2  = splitString("2_2",jsonObject.getString("lmmc_2_2"),jsonObject.getString("lmdz_2_2"));
						//栏目2_3
						String lmmc_2_3  = splitString("2_3",jsonObject.getString("lmmc_2_3"),jsonObject.getString("lmdz_2_3"));
						//栏目2_4
						String lmmc_2_4  = splitString("2_4",jsonObject.getString("lmmc_2_4"),jsonObject.getString("lmdz_2_4"));
						
						System.out.println("===========================3===========================");
						
						//栏目3_1
						String lmmc_3_1  = splitString("3_1",jsonObject.getString("lmmc_3_1"),jsonObject.getString("lmdz_3_1"));
						//栏目3_2
						String lmmc_3_2  = splitString("3_2",jsonObject.getString("lmmc_3_2"),jsonObject.getString("lmdz_3_2"));
						//栏目3_3
						String lmmc_3_3  = splitString("3_3",jsonObject.getString("lmmc_3_3"),jsonObject.getString("lmdz_3_3"));
						
						
					}
					
				}
			}
			
					
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	public String splitString(String name ,String tempName,String tempUrl ){
		
		Pattern p2 = Pattern.compile("[\u4e00-\u9fa5]+");
		
		Pattern p = Pattern.compile("((http|https)|www).*?(?=(&nbsp;|\\s|　|、|；|;|<br />|$|[<>]))");
		
		//名称
		if(tempName.equals("无") || tempUrl.equals("无")){
			System.out.println(name+"--暂无栏目url！");
		} else {
			System.out.println("栏目名称："+tempName);
			
			List<String> result2=new ArrayList<String>();
			Matcher m2 = p2.matcher(tempName);
			while (m2.find()) {
				result2.add(m2.group());
			}
			
			//栏目url
			Matcher m = p.matcher(tempUrl);
			List<String> result=new ArrayList<String>();
			while(m.find()){
				result.add(m.group());
			}
		
//			if(result.size()==1){
//				for(String s1:result){
//					System.out.println(name+"--"+tempName+":"+s1);
//				}
//			} else 
			if(result2.size() == result.size()){
				for (int j = 0; j < result2.size(); j++) {
					System.out.println("1:"+name +"--"+result2.get(j)+":"+result.get(j));
				}
			} else if(result2.size() == 1){
				for(String s1:result){
					System.out.println("2:"+name+"--"+tempName+":"+s1);
				}
			} else if(result2.size() > 1 && result.size()!=0){
				for(String s2:result2){
					System.out.println("3:"+name+"--"+s2+":"+result.get(0));
				}
			}
				
		}
		
		return null;
	}
	
	
//	public void getInfo(){
//		try {
//			logger.info("autoDatabase 启动... ...");
//			
//			String reqUrl = prop.getProperty("checkWebUrl");
//			String size = prop.getProperty("count");
//			int pageSize = 1;
//			for (int x = 1; x <= pageSize; x++) { 
//				String param = "sortAsc=false&sortField=modified&size=" + size + "&pos=" + x;
//				String temp = HttpRequestProxy.sendGet(reqUrl, param);
//				if (temp != "") {
//					JSONObject json = new JSONObject(temp);
//					if (temp.indexOf("errmsg") == -1) {
//						JSONObject jsonObj = json.getJSONObject("items");
//						JSONArray listJson = jsonObj.getJSONArray("pageObjects");
//						for (int i = 0; i < listJson.length(); i++) {
//							JSONObject object = (JSONObject) listJson.get(i);
//							
////							System.out.println(">>>>"+object.toString());
//							DatabaseInfo info = getCreateDatabaseInfo(object);
//							if (info != null) {
//								DatabaseInfoRequest request = new DatabaseInfoRequest();
//								request.setSiteCode(info.getSiteCode());
//								request.setVcode(info.getVcode());
//								List<DatabaseInfo> list = databaseInfoServiceImpl.queryList(request);
//								
//								if(list.size()>0){
//									DatabaseInfo database = list.get(0);
//									if(info.getIsexp()==0||info.getIsexp()==1||info.getIsexp()==3||info.getIsexp()==5){//正常
//										database.setIsexp(1);
//									}else if(info.getIsexp() == 2){//列外
//										database.setIsexp(2);
//									} else if(info.getIsexp() == 4){//关停
//										database.setIsexp(3);
//									}
//									
//									databaseInfoServiceImpl.update(database);
//									System.out.println(">>>>>>>>>>>>>>"+database.getAddress());
//								}
//										
//								
////								addDatebaseInfo(info);
////								System.out.println(info.getSiteCode()+">>>>>>>>>>>>>>>>>>>>>"+info.getIsexp());
//							}
//						}
//						pageSize = jsonObj.getInt("pageCount");
//					} else {
//						System.out.println("无数据!");
//					}
//
//				}
//			}
//			System.out.println("autoDatabase 结束... ...");
//			logger.info("autoDatabase 结束... ...");
//		} catch (Exception e) {
//			logger.error(e);
//		}
//	}
//	
	public DatabaseInfo getCreateDatabaseInfo(JSONObject object) {
		DatabaseInfo info = new DatabaseInfo();
		try {
//			info.setInnerUuid(object.getString("inner_uuid"));
			if (!"".equals(object.getString("sitecode"))) {
				info.setSiteCode(object.getString("sitecode"));
			}
			if (!"".equals(object.getString("vcode"))) {
				info.setVcode(object.getString("vcode"));
			}
//			if (!"".equals(object.getString("wzmc"))) {
//				info.setWzmc(object.getString("wzmc"));
//			}
//			if (!"".equals(object.getString("url"))) {
//				info.setUrl(object.getString("url"));
//			}
//			if (!"".equals(object.getString("wzzgdw"))) {
//				info.setWzzgdw(object.getString("wzzgdw"));
//			}
			if (!"".equals(object.getInt("isexp"))) {
				info.setIsexp(object.getInt("isexp"));
			}
//			if(!"".equals(object.getString("sheng"))){
//				info.setSheng(object.getString("sheng"));
//			}
//			if(!"".equals(object.getString("shi"))){
//				info.setShi(object.getString("shi"));
//			}
//			if(!"".equals(object.getString("xian"))){
//				info.setXian(object.getString("xian"));
//			}
//			if(!"".equals(object.getInt("isorganizational"))){
//				info.setIsorganizational(object.getInt("isorganizational"));
//			}
//			if(info.getIsexp() == 1||info.getIsexp() == 2){
//				info.setXxgkTx(object.getString("xxgk_tx"));
//				info.setXxgkDx(object.getInt("xxgk_dx"));
//				info.setHdhyTx(object.getString("hdhy_tx"));
//				info.setHdhyDx(object.getInt("hdhy_dx"));
//				info.setBsfwTx(object.getString("bsfw_tx"));
//				info.setBsfwDx(object.getInt("bsfw_dx"));
//				info.setShutmem(object.getString("shutmem"));
//				info.setEnddate(object.getString("enddate"));
//			}
//			if(info.getIsexp() == 3){
//				info.setExpreason(object.getInt("expreason"));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return info;
	}
	
	
	public void getLink(){
//		Users users = usersServiceImpl.get(1);
		System.out.println(">>>>>>>>>>>>>>>>>");
		
		
		List<String> list = databaseInfoServiceImpl.querySortCodeList();
		
		for (int i = 0; i < list.size(); i++) {
			String siteCode = list.get(i);
			
			DatabaseInfoRequest request = new DatabaseInfoRequest();
			request.setPageSize(Integer.MAX_VALUE);
			
			if(!siteCode.startsWith("bm")){
				
				//直辖市
				if(siteCode.startsWith("11")||siteCode.startsWith("12")||siteCode.startsWith("31")||siteCode.startsWith("50")){
					
					if(siteCode.endsWith("0000")){
						request.setSiteCodeLike(siteCode);
						request.setLevel("1");
						request.setIsexp(1);
						request.setIsorganizational(1);
						List<DatabaseInfo> databaseInfoList = databaseInfoServiceImpl.queryList(request);
						if(databaseInfoList.size()>0){
							DatabaseInfo databaseInfo = databaseInfoList.get(0);
							
							DatabaseLink databaseLink = new DatabaseLink();
							databaseLink.setDatabaseInfoId(databaseInfo.getId());
							databaseLink.setSiteCode(databaseInfo.getSiteCode());
							databaseLink.setOrgSiteCode(siteCode);
							databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
							databaseLink.setType(1);
							databaseLinkServiceImpl.add(databaseLink);
						}
						
						request.setIsorganizational(0);
						databaseInfoList = databaseInfoServiceImpl.queryList(request);
						for (int j = 0; j < databaseInfoList.size(); j++) {
							DatabaseInfo databaseInfo = databaseInfoList.get(j);
							
							DatabaseLink databaseLink = new DatabaseLink();
							databaseLink.setDatabaseInfoId(databaseInfo.getId());
							databaseLink.setSiteCode(databaseInfo.getSiteCode());
							databaseLink.setOrgSiteCode(siteCode);
							databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
							databaseLink.setType(2);
							databaseLinkServiceImpl.add(databaseLink);
						}

						
						
						
						request.setSiteCodeLike(siteCode.substring(0,2));
						request.setIsorganizational(1);
						request.setLevel("2");
						databaseInfoList = databaseInfoServiceImpl.queryList(request);
//						databaseInfoList = databaseInfoServiceImpl.queryList(request);
						for (int j = 0; j < databaseInfoList.size(); j++) {
							DatabaseInfo databaseInfo = databaseInfoList.get(j);
							
							DatabaseLink databaseLink = new DatabaseLink();
							databaseLink.setDatabaseInfoId(databaseInfo.getId());
							databaseLink.setSiteCode(databaseInfo.getSiteCode());
							databaseLink.setOrgSiteCode(siteCode);
							databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
							databaseLink.setType(3);
							databaseLinkServiceImpl.add(databaseLink);
						}
						
						
						request.setSiteCodeLike(siteCode);
						request.setIsexp(2);
						request.setIsorganizational(null);
						request.setLevel("1");
						databaseInfoList = databaseInfoServiceImpl.queryList(request);
//						databaseInfoList = databaseInfoServiceImpl.queryList(request);
						for (int j = 0; j < databaseInfoList.size(); j++) {
							DatabaseInfo databaseInfo = databaseInfoList.get(j);
							
							DatabaseLink databaseLink = new DatabaseLink();
							databaseLink.setDatabaseInfoId(databaseInfo.getId());
							databaseLink.setSiteCode(databaseInfo.getSiteCode());
							databaseLink.setOrgSiteCode(siteCode);
							databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
							databaseLink.setType(4);
							databaseLinkServiceImpl.add(databaseLink);
						}
						
						request.setIsexp(3);
						databaseInfoList = databaseInfoServiceImpl.queryList(request);
//						databaseInfoList = databaseInfoServiceImpl.queryList(request);
						for (int j = 0; j < databaseInfoList.size(); j++) {
							DatabaseInfo databaseInfo = databaseInfoList.get(j);
							
							DatabaseLink databaseLink = new DatabaseLink();
							databaseLink.setDatabaseInfoId(databaseInfo.getId());
							databaseLink.setSiteCode(databaseInfo.getSiteCode());
							databaseLink.setOrgSiteCode(siteCode);
							databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
							databaseLink.setType(5);
							databaseLinkServiceImpl.add(databaseLink);
						}
						
						
					}else {
						request.setSiteCodeLike(siteCode);
						request.setLevel("2");
						request.setIsexp(1);
						request.setIsorganizational(1);
						List<DatabaseInfo> databaseInfoList = databaseInfoServiceImpl.queryList(request);
						if(databaseInfoList.size()>0){
							DatabaseInfo databaseInfo = databaseInfoList.get(0);
							
							DatabaseLink databaseLink = new DatabaseLink();
							databaseLink.setDatabaseInfoId(databaseInfo.getId());
							databaseLink.setSiteCode(databaseInfo.getSiteCode());
							databaseLink.setOrgSiteCode(siteCode);
							databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
							databaseLink.setType(1);
							databaseLinkServiceImpl.add(databaseLink);
						}
						
						request.setIsorganizational(0);
						databaseInfoList = databaseInfoServiceImpl.queryList(request);
//						databaseInfoList = databaseInfoServiceImpl.queryList(request);
						for (int j = 0; j < databaseInfoList.size(); j++) {
							DatabaseInfo databaseInfo = databaseInfoList.get(j);
							
							DatabaseLink databaseLink = new DatabaseLink();
							databaseLink.setDatabaseInfoId(databaseInfo.getId());
							databaseLink.setSiteCode(databaseInfo.getSiteCode());
							databaseLink.setOrgSiteCode(siteCode);
							databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
							databaseLink.setType(2);
							databaseLinkServiceImpl.add(databaseLink);
						}
						
						request.setIsexp(2);
						request.setIsorganizational(null);
						databaseInfoList = databaseInfoServiceImpl.queryList(request);
//						databaseInfoList = databaseInfoServiceImpl.queryList(request);
						for (int j = 0; j < databaseInfoList.size(); j++) {
							DatabaseInfo databaseInfo = databaseInfoList.get(j);
							
							DatabaseLink databaseLink = new DatabaseLink();
							databaseLink.setDatabaseInfoId(databaseInfo.getId());
							databaseLink.setSiteCode(databaseInfo.getSiteCode());
							databaseLink.setOrgSiteCode(siteCode);
							databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
							databaseLink.setType(4);
							databaseLinkServiceImpl.add(databaseLink);
						}
						
						request.setIsexp(3);
						databaseInfoList = databaseInfoServiceImpl.queryList(request);
//						databaseInfoList = databaseInfoServiceImpl.queryList(request);
						for (int j = 0; j < databaseInfoList.size(); j++) {
							DatabaseInfo databaseInfo = databaseInfoList.get(j);
							
							DatabaseLink databaseLink = new DatabaseLink();
							databaseLink.setDatabaseInfoId(databaseInfo.getId());
							databaseLink.setSiteCode(databaseInfo.getSiteCode());
							databaseLink.setOrgSiteCode(siteCode);
							databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
							databaseLink.setType(5);
							databaseLinkServiceImpl.add(databaseLink);
						}
						
					}
					
				}else if(siteCode.endsWith("0000")){//省份
					request.setSiteCodeLike(siteCode);
					request.setLevel("1");
					request.setIsexp(1);
					request.setIsorganizational(1);
					List<DatabaseInfo> databaseInfoList = databaseInfoServiceImpl.queryList(request);
					if(databaseInfoList.size()>0){
						DatabaseInfo databaseInfo = databaseInfoList.get(0);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(1);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setIsorganizational(0);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(2);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setSiteCodeLike(siteCode.substring(0,2));
					request.setIsorganizational(1);
					request.setLevel("2");
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(3);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					
					
					request.setSiteCodeLike(siteCode);
					request.setLevel("1");
					request.setIsexp(2);
					request.setIsorganizational(null);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(4);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setIsexp(3);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(5);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					
				} else if(siteCode.endsWith("00")){
					request.setSiteCodeLike(siteCode);
					request.setLevel("2");
					request.setIsexp(1);
					request.setIsorganizational(1);
					List<DatabaseInfo> databaseInfoList = databaseInfoServiceImpl.queryList(request);
					if(databaseInfoList.size()>0){
						DatabaseInfo databaseInfo = databaseInfoList.get(0);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(1);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setIsorganizational(0);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(2);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setSiteCodeLike(siteCode.substring(0,4));
					request.setIsorganizational(1);
					request.setLevel("3");
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(3);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					
					
					request.setSiteCodeLike(siteCode);
					request.setLevel("2");
					request.setIsorganizational(null);
					request.setIsexp(2);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(4);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setIsexp(3);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(5);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
				} else{
					request.setSiteCodeLike(siteCode);
					request.setLevel("3");
					request.setIsexp(1);
					request.setIsorganizational(1);
					List<DatabaseInfo> databaseInfoList = databaseInfoServiceImpl.queryList(request);
					if(databaseInfoList.size()>0){
						DatabaseInfo databaseInfo = databaseInfoList.get(0);

						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(1);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setIsorganizational(0);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(2);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					
					request.setSiteCodeLike(siteCode);
					request.setLevel("3");
					request.setIsorganizational(null);
					request.setIsexp(2);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(4);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setIsexp(3);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(5);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
				}
			} else if(siteCode.startsWith("bm")){ //部委
				if(siteCode.endsWith("00")){
					request.setSiteCodeLike(siteCode);
					request.setIsexp(1);
					request.setLevel("1");
					request.setIsorganizational(1);
					List<DatabaseInfo> databaseInfoList = databaseInfoServiceImpl.queryList(request);
					if(databaseInfoList.size()>0){
						DatabaseInfo databaseInfo = databaseInfoList.get(0);

						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(1);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setIsorganizational(0);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(2);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setSiteCodeLike(siteCode.substring(0,4));
					request.setIsorganizational(1);
					request.setLevel("2");
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(3);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					
					request.setSiteCodeLike(siteCode);
					request.setLevel("1");
					request.setIsorganizational(null);
					request.setIsexp(2);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(4);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setIsexp(3);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(5);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
				} else {
					
					request.setSiteCodeLike(siteCode);
					request.setIsexp(1);
					//request.setLevel("3");
					request.setIsorganizational(1);
					List<DatabaseInfo> databaseInfoList = databaseInfoServiceImpl.queryList(request);
					if(databaseInfoList.size()>0){
						DatabaseInfo databaseInfo = databaseInfoList.get(0);

						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(1);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setSiteCodeLike(siteCode);
					request.setIsorganizational(0);
					//request.setLevel("3");
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(2);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					
					request.setSiteCodeLike(siteCode);
					request.setIsorganizational(null);
					request.setIsexp(2);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(4);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
					request.setIsexp(3);
					databaseInfoList = databaseInfoServiceImpl.queryList(request);
//					databaseInfoList = databaseInfoServiceImpl.queryList(request);
					for (int j = 0; j < databaseInfoList.size(); j++) {
						DatabaseInfo databaseInfo = databaseInfoList.get(j);
						
						DatabaseLink databaseLink = new DatabaseLink();
						databaseLink.setDatabaseInfoId(databaseInfo.getId());
						databaseLink.setSiteCode(databaseInfo.getSiteCode());
						databaseLink.setOrgSiteCode(siteCode);
						databaseLink.setIsorganizational(databaseInfo.getIsorganizational());
						databaseLink.setType(5);
						databaseLinkServiceImpl.add(databaseLink);
					}
					
				}
				
			}

			
		}
		
		
		System.out.println(">>>>>>>>>>www>>>"+list.size());
		
		
		
	}
}
