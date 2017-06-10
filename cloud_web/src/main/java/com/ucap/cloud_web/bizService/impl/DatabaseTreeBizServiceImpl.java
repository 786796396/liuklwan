package com.ucap.cloud_web.bizService.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucap.cloud_web.bizService.DatabaseTreeBizService;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
/**
 * 描述： databaseTreeinfo 公用方法接口实现类
 * 包：com.ucap.cloud_web.bizService.impl
 * 文件名称：DatabaseTreeBizServiceImpl
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2017-1-16上午11:34:23 
 * @version V1.0
 */
@Service
public class DatabaseTreeBizServiceImpl implements DatabaseTreeBizService {
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;

	/**
	 * 
	 * @描述:获取上级组织单位编码
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-1-16上午11:33:41
	 * @param siteCode
	 * @return
	 */
	@Override
	public String getUpperOrgCode(String siteCode) {
		String returnCode=null;
		try {
			DatabaseTreeInfoRequest databaseTreeInfoRequest =new DatabaseTreeInfoRequest();
			databaseTreeInfoRequest.setIsBigdata(1);
			databaseTreeInfoRequest.setSiteCode(siteCode);
			databaseTreeInfoRequest.setPageNo(0);
			databaseTreeInfoRequest.setPageSize(Integer.MAX_VALUE);
			List<DatabaseTreeInfo> list=databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
			
			if(list.size()>0){
				Integer parentId=list.get(0).getParentId();
				if(parentId!=null){
					databaseTreeInfoRequest.setSiteCode(null);
					databaseTreeInfoRequest.setId(parentId);
					List<DatabaseTreeInfo> returnList=databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
					if(returnList.size()>0){
						returnCode=returnList.get(0).getSiteCode();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return returnCode;
	}
	/**
	 * 
	 * @描述:查询组织单位下的填报单位（注：不是所有的站点，只是父id挂在该站点的）
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-1-16上午11:15:37 
	 * @param Isorganizational //0非门户，1门户   siteCode
	 * @return List<DatabaseTreeInfo>
	 */
	@Override
	public List<DatabaseInfo> localLevelSite(DatabaseTreeInfoRequest databaseTreeInfoRequest) {
		List<DatabaseInfo> nextDataList=new ArrayList<DatabaseInfo>();
		try {
			DatabaseTreeInfoRequest orgReques=new DatabaseTreeInfoRequest();
			orgReques.setIsBigdata(1);
			orgReques.setPageNo(0);
			orgReques.setPageSize(Integer.MAX_VALUE);
			orgReques.setSiteCode(databaseTreeInfoRequest.getSiteCode());
			List<DatabaseTreeInfo> list=databaseTreeInfoServiceImpl.queryList(orgReques);
			if(list.size()>0){
				for(DatabaseTreeInfo databaseTreeInfo:list){
					Integer parentId=databaseTreeInfo.getId();
					databaseTreeInfoRequest.setParentId(parentId);
					databaseTreeInfoRequest.setSiteCode(null);
					databaseTreeInfoRequest.setSiteCodeLength(10);//填报单位
					//是否部委 1:部委 0：非部委
					databaseTreeInfoRequest.setIsBm(databaseTreeInfo.getIsBm());
					//填报单位
					nextDataList=databaseTreeInfoServiceImpl.querySiteDatabaseInfoList(databaseTreeInfoRequest);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nextDataList;
	}
	/**
	 * 
	 * @描述:查询组织单位下所有的10位站点
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-1-17上午10:43:06 
	 * @param databaseTreeInfoRequest.siteCode   可以区别是否门户    正常关停例外
	 * @return
	 */
	@Override
	public List<DatabaseInfo> localLevelSiteAll(DatabaseTreeInfoRequest databaseTreeInfoRequest) {
		List<DatabaseInfo> nextDataList=new ArrayList<DatabaseInfo>();
		try {
			databaseTreeInfoRequest.setIsBigdata(1);
			databaseTreeInfoRequest.setPageNo(0);
			databaseTreeInfoRequest.setPageSize(Integer.MAX_VALUE);
			List<DatabaseTreeInfo> list=databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
			if(list.size()>0){
				for(DatabaseTreeInfo databaseTreeInfo:list){
					String code=databaseTreeInfo.getCode();
					databaseTreeInfoRequest.setCodeLike(code);
					databaseTreeInfoRequest.setSiteCode(null);
					databaseTreeInfoRequest.setSiteCodeLength(10);//填报单位
					//是否部委 1:部委 0：非部委
					databaseTreeInfoRequest.setIsBm(databaseTreeInfo.getIsBm());
					//填报单位
					nextDataList=databaseTreeInfoServiceImpl.querySiteDatabaseInfoList(databaseTreeInfoRequest);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nextDataList;
	}
	
	/** 新表方法 */

	/**
	 * 
	 * @描述:查询下级单位
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年2月13日下午7:38:52
	 * @return
	 */
	@Override
	public List<DatabaseTreeInfo> getDatabaseTreeInfoList(DatabaseTreeInfoRequest request) {
		List<DatabaseTreeInfo> treeInfoList = new ArrayList<DatabaseTreeInfo>();
		try {
			treeInfoList = databaseTreeInfoServiceImpl.getDatabaseTreeInfoList(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return treeInfoList;
	}

}
