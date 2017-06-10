package com.ucap.cloud_web.service.impl;


import java.util.List;

import com.publics.util.page.PageVo;


/**


	@Autowired







	@Override
	public DatabaseOrgInfo getDatabaseOrgByCode(String siteCode) {
		DatabaseOrgInfoRequest request = new DatabaseOrgInfoRequest();
		request.setStieCode(siteCode);
		List<DatabaseOrgInfo> databaseOrgInfoList = queryList(request);
		if(!CollectionUtils.isEmpty(databaseOrgInfoList)){
			return databaseOrgInfoList.get(0);
		}
		return null;
	}

