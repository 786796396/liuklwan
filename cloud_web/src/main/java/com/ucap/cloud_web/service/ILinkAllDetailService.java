package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	public PageVo<LinkAllDetail> queryNoExcept(LinkAllDetailRequest request);
	/**
	 * 查询 config_link_except 表中siteCode 相同时 url不相同的
	 * @param request
	 * @return
	 */
	List<LinkAllDetail> queryNoExceptList(LinkAllDetailRequest request);
	/**
	 * @Description:根据tree表查询  某个组织单位下  某个服务周期内  每类全站死链编码的问题个数 
	 * @author cuichx --- 2017-4-7下午6:42:45     
	 * @param paramMap
	 * @return
	 */
	public List<LinkAllDetailRequest> querySumGroupBy(
			Map<String, Object> paramMap);
	/**
	 * @Description: 统计某个服务周期内  每种死链编码的个数
	 * @author cuichx --- 2017-4-8下午1:03:02     
	 * @param paramMap
	 * @return
	 */
	public List<LinkAllDetailRequest> queryByCode(Map<Object, Object> paramMap);

}
