package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.entity.DatabaseLink;


/**


	/**

	/**

	/**

	/**

	/**

	/**
	
	/**
	 * @Description: 分组查询
	 * @author sunjiang --- 2016-3-21下午4:34:11     
	 * @param request
	 * @return
	 */
	public List<DatabaseLinkRequest> queryCountBySiteCode(DatabaseLinkRequest request);

	/**
	/**
	 * @Title: 获取组织单位 单站预警 10位sitecode
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-2上午10:51:01
	 * @param request
	 * @return
	 */
	public List<DatabaseLink> queryEarlySingleSiteCode(DatabaseLinkRequest request);
	/**
	 * 
	 * @描述:填报单位 手动预警  ，查询 收费的组织单位
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年11月22日下午4:34:43 
	 * @param dataBaseLinkRequest
	 * @return
	 */
	public List<DatabaseLink> queryJoinContract(DatabaseLinkRequest dataBaseLinkRequest);

}
