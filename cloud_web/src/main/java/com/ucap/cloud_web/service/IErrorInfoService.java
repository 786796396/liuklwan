package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.ErrorInfoRequest;
import com.ucap.cloud_web.entity.ErrorInfo;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * @描述: 分页（关联tree表）
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月5日下午5:32:45
	 * @param req
	 * @return
	 */

	public PageVo<ErrorInfo> getErrorInfoList(ErrorInfoRequest req);

	/**
	 * @描述:分页（关联tree表）条数
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月5日下午5:35:13
	 * @param request
	 * @return
	 */

	int getErrorInfoCount(ErrorInfoRequest request);

}
