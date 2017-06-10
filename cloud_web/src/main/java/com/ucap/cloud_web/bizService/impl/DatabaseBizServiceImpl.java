package com.ucap.cloud_web.bizService.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.QueryUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dtoResponse.VerJsonResponse;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseLink;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseLinkService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.util.SignUtils;
@Service
public class DatabaseBizServiceImpl implements DatabaseBizService {

	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IDatabaseLinkService databaseLinkServiceImpl;
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	
	
	public String superiorUnit(String siteCode) {
		DatabaseTreeInfoRequest databaseTreeInfoRequest = new DatabaseTreeInfoRequest();
		databaseTreeInfoRequest.setSiteCode(siteCode);
		String code = "";
		List<DatabaseTreeInfo> treeInfo = databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
		if (treeInfo != null && treeInfo.size() > 0) {
			databaseTreeInfoRequest.setId(treeInfo.get(0).getParentId());
			databaseTreeInfoRequest.setSiteCode(null);
			// 根据ID
			List<DatabaseTreeInfo> treeInfoInfo = databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
			if (treeInfoInfo != null && treeInfoInfo.size() > 0) {
				code = treeInfoInfo.get(0).getSiteCode();
			}
		}
		return code;
	}
	 
	/*//判断单位等级（县）方法
	public Integer isHasChridren(String siteCode) {
		// TODO Auto-generated method stub
		//根据用户名查询database——tree—info表
		@SuppressWarnings("unused")
		DatabaseTreeInfoRequest databaseTreeInfoRequest = new DatabaseTreeInfoRequest();
		databaseTreeInfoRequest.setSiteCode(siteCode);
		databaseTreeInfoRequest.setIsBigdata(1);
		//定义是否拥有下级单位 默认为0 无   !=0 有
		int flag = 0;
		List<DatabaseTreeInfo> treeInfo = databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
		if(treeInfo.size()>0){
			databaseTreeInfoRequest.setParentId(treeInfo.get(0).getId());
			databaseTreeInfoRequest.setSiteCode(null);
			databaseTreeInfoRequest.setIsOrg("1");
//			根据ID  
			List<DatabaseTreeInfo> treeInfoInfo = databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
			if(treeInfoInfo.size() > 0){
				flag= treeInfo.get(0).getId();
			}
		}
		return flag;
	}*/
	
	//判断单位等级（县）方法   
	public Integer isHasChridren(String siteCode) {
		//根据用户名查询database——tree—info表
		DatabaseTreeInfoRequest databaseTreeInfoRequest = new DatabaseTreeInfoRequest();
		//定义是否拥有下级单位 默认为0 无   !=0 有
		int flag = 0;
		databaseTreeInfoRequest.setIsBigdata(1);
		databaseTreeInfoRequest.setOrgSiteCode(siteCode);
		databaseTreeInfoRequest.setIsOrg("1");
		//根据ID  
		List<DatabaseTreeInfo> treeInfo = databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
		if(treeInfo != null && treeInfo.size() > 0){
				flag= treeInfo.get(0).getParentId();
			}
		return flag;
	}
	/**
	 * 
	 * @描述:是否存在下级组织单位
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-1上午10:56:15 
	 * @param siteCode
	 * @return DatabaseTreeInfo
	 */
	public DatabaseTreeInfo isNextOrg(String siteCode) {
		DatabaseTreeInfo flag = null;
		try {
			//根据用户名查询database——tree—info表
			DatabaseTreeInfoRequest databaseTreeInfoRequest = new DatabaseTreeInfoRequest();
			//定义是否拥有下级单位 默认为null无   !=null有
			databaseTreeInfoRequest.setIsBigdata(1);
			databaseTreeInfoRequest.setOrgSiteCode(siteCode);
			databaseTreeInfoRequest.setIsOrg("1");
			//根据ID  
			List<DatabaseTreeInfo> treeInfo = databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
			if(treeInfo != null && treeInfo.size() > 0){
					flag= treeInfo.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}	
	
	/**
	 * @Description: 内容保障获取合同
	 * @author luocheng --- 2017-4-27
	 * @param siteCode
	 *            网站标识码
	 * @param nowDate
	 *            格式日期字符串（标准格式：yyyy-MM-dd）
	 * @return
	 */
	public List<ContractInfo> getContractInfoServurity(String siteCode, String nowDate) {
		List<ContractInfo> conList = null;
		try {

			ContractInfoRequest conRequest = new ContractInfoRequest();
			conRequest.setSiteCode(siteCode);
			conRequest.setTypeFlag(1);// 非抽查合同
			Integer[] conStatues = { 0, 1, 2 };
			conRequest.setExecuteStatusArr(conStatues);// 合同状态为0-初始化 1-服务中

			if (StringUtils.isNotEmpty(nowDate)) {
				conRequest.setNowTime(nowDate);
			}

			conRequest.setPageSize(Integer.MAX_VALUE);
			// 排序
			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("contract_begin_time", QueryOrderType.ASC);
			queryOrderList.add(siteQueryOrder);
			conRequest.setQueryOrderList(queryOrderList);
			conList = contractInfoServiceImpl.queryList(conRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conList;
	}

	/** 老合同信息 **/
	public List<ContractInfo> getContractInfoList(String siteCode, String nowDate) {
		List<ContractInfo> conList = null;
		try {

			ContractInfoRequest conRequest = new ContractInfoRequest();
			conRequest.setSiteCode(siteCode);
			conRequest.setTypeFlag(1);// 非抽查合同
			Integer[] conStatues = { 0, 1, 2 };
			conRequest.setExecuteStatusArr(conStatues);// 合同状态为0-初始化 1-服务中

			if (StringUtils.isNotEmpty(nowDate)) {
				conRequest.setNowTime(nowDate);
			}

			conRequest.setPageSize(Integer.MAX_VALUE);
			// 排序
			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("contract_end_time", QueryOrderType.DESC);
			queryOrderList.add(siteQueryOrder);
			conRequest.setQueryOrderList(queryOrderList);
			conList = contractInfoServiceImpl.queryList(conRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conList;
	}
	// 查询服务周期的方法
	public Map<String, Object> characterCycle(String siteCode, String siteCodeTb) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Object> list = new ArrayList<Object>();
		ServicePeriodRequest periodRequest = new ServicePeriodRequest();
		List<ServicePeriodRequest> periodList = new ArrayList<ServicePeriodRequest>();
		try {
			if (StringUtils.isNotEmpty(siteCode)) {
				if (siteCode.length() > 6) {
					List<ContractInfo> contractList = getContractInfoServurity(siteCode,
							DateUtils.formatStandardDate(new Date()));
					if (contractList.size() > 0) {
						for (ContractInfo contractInfo : contractList) {
								periodRequest.setSiteCode(siteCode);
								periodRequest.setContractInfoId(contractInfo.getId());
								periodRequest.setPageSize(Integer.MAX_VALUE);
								// 调用方法
								periodList = servicePeriodServiceImpl.queryByRelationPeriod(periodRequest);
						}

					} else {
						// 查询挂接关系
						DatabaseTreeInfoRequest treeRequese = new DatabaseTreeInfoRequest();
						treeRequese.setSiteCode(siteCode);
						treeRequese.setIsLink(1);
						treeRequese.setPageSize(Integer.MAX_VALUE);
						List<DatabaseTreeInfo> treeList = databaseTreeInfoServiceImpl.queryList(treeRequese);
						if (treeList.size() > 0) {
							for (DatabaseTreeInfo databaseTree : treeList) {
								// 查询合同
								List<ContractInfo> contractInfoList = getContractInfoServurity(databaseTree.getOrgSiteCode(),
										DateUtils.formatStandardDate(new Date()));
								if (contractInfoList.size() > 0) {
									for (ContractInfo contractInfo : contractInfoList) {
										if (contractInfo.getIsSearchTb() == 1) {
											periodRequest.setSiteCode(contractInfo.getSiteCode());
											periodRequest.setContractInfoId(contractInfo.getId());
											periodRequest.setPageSize(Integer.MAX_VALUE);
											periodList = servicePeriodServiceImpl.queryByRelationPeriod(periodRequest);
										}
									}
								}
							}
						}
					}

				} else {
					periodRequest.setSiteCode(siteCode);
					periodRequest.setSiteCodeRTb(siteCodeTb);
					periodRequest.setComboI(4);
					periodRequest.setPageSize(Integer.MAX_VALUE);
					// 调用查询周期方法
					periodList = servicePeriodServiceImpl.queryByRelationPeriod(periodRequest);
				}
			}

			// 日期拼接
			if (periodList != null && periodList.size() > 0) {
				for (int i = 0; i < periodList.size(); i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					StringBuffer sb = new StringBuffer(1000);
					ServicePeriodRequest servicePeriod = periodList.get(i);
					int spId = servicePeriod.getId();
					String startDate = servicePeriod.getStartDate();
					String endDate = servicePeriod.getEndDate();
					if (StringUtils.isEmpty(startDate)) {
						startDate = "";
					}
					if (StringUtils.isEmpty(endDate)) {
						endDate = "";
					}
					sb.append(startDate).append("至").append(endDate); // 样式
																		// 2016-04-08至2016-05-08
					map.put("id", spId);
					map.put("cycleDate", sb.toString());
					list.add(map);
				}
			}
			resultMap.put("scanCycleList", list);
			if (StringUtils.isNotEmpty(siteCode)) {
				resultMap.put("size", list.size());
			} else {
				resultMap.put("size", 0);
			}
			resultMap.put("errorMsg", "更新成功！");
		} catch (Exception e) {
			resultMap.put("errorMsg", "更新失败！");
			e.printStackTrace();
		}
		return resultMap;
	}

	/** 新产品信息 **/
	// 查询服务周期的方法
	// public Map<String, Object> characterCycle(String siteCode, String
	// siteCodeTb) {
	// Map<String, Object> resultMap = new HashMap<String, Object>();
	// List<Object> list = new ArrayList<Object>();
	// ServicePeriodRequest periodRequest = new ServicePeriodRequest();
	// List<ServicePeriodRequest> periodList = new
	// ArrayList<ServicePeriodRequest>();
	// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode() };
	// try {
	// if (StringUtils.isNotEmpty(siteCode)) {
	// if (siteCode.length() > 6) {
	// List<CrmProductsResponse> crmlist = getCrmProductsBizList(siteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()));
	//
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// for (CrmProductsResponse crm : crmlist) {
	// periodRequest.setSiteCode(siteCode);
	// periodRequest.setContractInfoId(crm.getId());
	// periodRequest.setPageSize(Integer.MAX_VALUE);
	// // 调用方法
	// periodList =
	// servicePeriodServiceImpl.queryByRelationPeriod(periodRequest);
	// }
	//
	// } else {
	// // 查询挂接关系
	// DatabaseTreeInfoRequest treeRequese = new DatabaseTreeInfoRequest();
	// treeRequese.setSiteCode(siteCode);
	// treeRequese.setIsLink(1);
	// treeRequese.setPageSize(Integer.MAX_VALUE);
	// List<DatabaseTreeInfo> treeList =
	// databaseTreeInfoServiceImpl.queryList(treeRequese);
	// if (treeList.size() > 0) {
	// for (DatabaseTreeInfo databaseTree : treeList) {
	// // 查询合同
	// List<CrmProductsResponse> crmReslist = getCrmProductsBizList(
	// databaseTree.getOrgSiteCode(), productTypeArr,
	// DateUtils.formatStandardDate(new Date()));
	// if (CollectionUtils.isNotEmpty(crmReslist)) {
	// for (CrmProductsResponse crmRes : crmReslist) {
	// if (crmRes.getIsSearchTb() == 1) {
	// periodRequest.setSiteCode(crmRes.getSiteCode());
	// periodRequest.setContractInfoId(crmRes.getId());
	// periodRequest.setPageSize(Integer.MAX_VALUE);
	// periodList =
	// servicePeriodServiceImpl.queryByRelationPeriod(periodRequest);
	// }
	// }
	// }
	// }
	// }
	// }
	//
	// } else {
	// periodRequest.setSiteCode(siteCode);
	// periodRequest.setSiteCodeRTb(siteCodeTb);
	// periodRequest.setComboI(4);
	// periodRequest.setPageSize(Integer.MAX_VALUE);
	// // 调用查询周期方法
	// periodList =
	// servicePeriodServiceImpl.queryByRelationPeriod(periodRequest);
	// }
	// }
	//
	// // 日期拼接
	// if (periodList != null && periodList.size() > 0) {
	// for (int i = 0; i < periodList.size(); i++) {
	// HashMap<String, Object> map = new HashMap<String, Object>();
	// StringBuffer sb = new StringBuffer(1000);
	// ServicePeriodRequest servicePeriod = periodList.get(i);
	// int spId = servicePeriod.getId();
	// String startDate = servicePeriod.getStartDate();
	// String endDate = servicePeriod.getEndDate();
	// if (StringUtils.isEmpty(startDate)) {
	// startDate = "";
	// }
	// if (StringUtils.isEmpty(endDate)) {
	// endDate = "";
	// }
	// sb.append(startDate).append("至").append(endDate); // 样式
	// // 2016-04-08至2016-05-08
	// map.put("id", spId);
	// map.put("cycleDate", sb.toString());
	// list.add(map);
	// }
	// }
	// resultMap.put("scanCycleList", list);
	// if (StringUtils.isNotEmpty(siteCode)) {
	// resultMap.put("size", list.size());
	// } else {
	// resultMap.put("size", 0);
	// }
	// resultMap.put("errorMsg", "更新成功！");
	// } catch (Exception e) {
	// resultMap.put("errorMsg", "更新失败！");
	// e.printStackTrace();
	// }
	// return resultMap;
	// }

	/**
	 * 
	 * @描述:获取产品信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月13日下午6:06:49
	 * @param siteCode
	 * @param productTypeArr
	 *            产品分类（可随时增加）:1.全面检测 2.抽查 3 安全
	 * @param nowDate
	 * @return
	 */
	// public List<CrmProductsResponse> getCrmProductsBizList(String siteCode,
	// Integer[] productTypeArr, String nowDate) {
	// List<CrmProductsResponse> crmlist = new ArrayList<CrmProductsResponse>();
	// CrmProductsRequest crmReq = new CrmProductsRequest();
	// List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
	// QueryOrder siteQueryOrder = new QueryOrder("co.order_end_time",
	// QueryOrderType.DESC);
	// queryOrderList.add(siteQueryOrder);
	// try {
	// crmReq.setSiteCode(siteCode);
	// crmReq.setProductTypeArr(productTypeArr);
	// Integer[] executeStatusArr = { 0, 1 };
	// crmReq.setExecuteStatusArr(executeStatusArr);
	// if (StringUtils.isNotEmpty(nowDate)) {
	// crmReq.setNowTime(nowDate);
	// }
	// crmReq.setQueryOrderList(queryOrderList);
	// crmlist = crmProductsServiceImpl.getCrmProductsList(crmReq);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return crmlist;
	// }

	@Override
	public List<DatabaseInfo> getDatebaseInfoByType(String type, String siteCode) {
		Integer tp = -1;
		if (StringUtils.isNotEmpty(type) && !type.equals("0")) {
			tp = Integer.valueOf(type);
		}
		List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
		queryOrderList.add(new QueryOrder("isorganizational", QueryOrderType.DESC));
		List<DatabaseInfo> currentNextSiteCode = new ArrayList<DatabaseInfo>();
		DatabaseLinkRequest databaseLinkRequest = new DatabaseLinkRequest();
		databaseLinkRequest.setPageSize(Integer.MAX_VALUE);
		databaseLinkRequest.setOrgSiteCode(siteCode);
		databaseLinkRequest.setType(tp);
		databaseLinkRequest.setLinkStatus(1);
		databaseLinkRequest.setIsexp(1);
		databaseLinkRequest.setQueryOrderList(queryOrderList);
		try {
			List<DatabaseLink> queryList = databaseLinkServiceImpl.queryList(databaseLinkRequest);
			if (!CollectionUtils.isEmpty(queryList)) {
				for (DatabaseLink databaseLink : queryList) {
					int databaseInfoId = databaseLink.getDatabaseInfoId();
					DatabaseInfo databaseInfo = databaseInfoServiceImpl.get(databaseInfoId);
					currentNextSiteCode.add(databaseInfo);
				}
			} else { // 为了防止currentNextSiteCode为空，程序报错
				DatabaseInfo databaseInfo = new DatabaseInfo();
				databaseInfo.setSiteCode("0");
				currentNextSiteCode.add(databaseInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentNextSiteCode;
	}
	
	/**
	 * 根据siteCode查询服务周期
	 */
	@Override
	public List<ServicePeriodRequest> queryByRelationPeriodBasic(ServicePeriodRequest request) {
			request.setSpotCheckScheduleId(0); //抽查进度表Id
		    request.setPageSize(Integer.MAX_VALUE); 
		    List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder2=new QueryOrder("s.start_date",QueryOrderType.DESC); //根据周期的开始时间进行倒序排序
			queryOrderList.add(siteQueryOrder2);
			request.setQueryOrderList(queryOrderList);
			Map<String, Object> param = QueryUtils.getQueryMap(request);
			param.put("statasArray", new int[]{1,2}); //查询服务状态 在服务中已经完成服务的
			List<ServicePeriodRequest> list = servicePeriodServiceImpl.queryByRelationPeriodBasic(param);
			return list;
	}
	
	@Override
	public List<DatabaseTreeInfo> getDatas(DatabaseTreeInfoRequest rRequest) {
		return databaseTreeInfoServiceImpl.getDatas(rRequest);
	}
	@Override
	public PageVo<DatabaseInfo> getNativeDatas(DatabaseTreeInfoRequest sRequest) {
		return databaseTreeInfoServiceImpl.getNativeDatas(sRequest);
	}
	@Override
	public List<DatabaseInfo> getNatives(DatabaseTreeInfoRequest sRequest) {
		return databaseTreeInfoServiceImpl.getNatives(sRequest);
	}
	@Override
	public DatabaseTreeInfo getInfoStates(DatabaseTreeInfoRequest rRequest) {
		return databaseTreeInfoServiceImpl.getInfoStates(rRequest);
	}

	@Override
	public VerJsonResponse verParams(Map<String, String> params, String noNullParams) {
		VerJsonResponse json = new VerJsonResponse();
		String signs = getSign(params);
		String signStr = signs.split("\\|")[0];
		String timestamp = signs.split("\\|")[1];

		json.setSign(signStr);
		json.setTimestamp(timestamp);

		if (signStr.length() < 2) {
			json.setStatus(false);
			json.setErrMsg("签名错误");
			return json;
		}
		// === 参数非空判断  start===
		if (StringUtils.isNotEmpty(noNullParams)) {
			String mString = "";
			for (String str : noNullParams.split("\\|")) {
				if (!params.containsKey(str)) {
					mString += "缺少参数" + str + ";";
					continue;
				}
				if (StringUtils.isEmpty(params.get(str))) {
					mString += str + "不能为空;";
				}
			}
			if (mString.length() > 0) {
				json.setStatus(false);
				json.setErrMsg(mString);
				return json;
			}
		}
		// === 参数非空判断  end===
		return json;
	}

	@Override
	public String getSign(Map<String, String> params) {
		if (params.containsKey("sign")) {
			params.remove("sign");
		}
		//Enumeration<?> pNames = spotCheckResultRequest.getParameterNames();
		//Map<String, Object> params = new HashMap<String, Object>();

		//Map<String, String> newParams = params;
		long timestamp = new Date().getTime();
		params.put("timestamp", String.valueOf(timestamp));

		String sign = SignUtils.createSign(params, false);
		System.out.println("======签名：" + sign + "=======");

		return sign + "|" + timestamp;
	}

	@Override
	public String getDatabaseUrl(String siteCode) {
		String uri = null;
		DatabaseInfoRequest request = new DatabaseInfoRequest();
		try {
			request.setSiteCode(siteCode);
			List<DatabaseInfo> dataList = databaseInfoServiceImpl.queryList(request);
			if (CollectionUtils.isNotEmpty(dataList)) {
				DatabaseInfo info = dataList.get(0);
				if (StringUtils.isNotEmpty(info.getJumpUrl())) {
					uri = info.getJumpUrl(); // 跳转url地址
				} else {
					uri = info.getUrl(); // 首页url地址
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uri;
	}
	@Override
	public int getResourceType(int urlType) {
		// （站内死链=0,域内死链=1,域外死链=2,域内空白标题死链=3,域外空白标题死链=4,域内附件死链=5,
		// 域外附件死链=6,域内图片死链=7,域外图片死链=8,非法URL不符合URL规则,没有经过httpclient验证的死链 = 9）
		if (urlType == 7 || urlType == 8) {
			// 1图片、2网页、3附件、4其他
			return 1;
		} else if (urlType == 5 || urlType == 6) {
			return 3;
		} else if (urlType == 9) {
			return 4;
		} else {
			return 2;
		}
	}

}
