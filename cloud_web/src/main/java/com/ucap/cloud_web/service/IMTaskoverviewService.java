package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import com.ucap.cloud_web.dto.MTaskoverviewRequest;
import com.ucap.cloud_web.entity.Result;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**
	
	/**
	* 查询对象集合
	* @param request				前台参数			(必填)
	* @return	List<MTaskdetail>
	*/
	public List<Result> queryResultList(MTaskoverviewRequest request);

	/**
	 * @描述:
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月22日下午7:24:43 
	 * @param hashMap 
	 */
	
	public List<MTaskoverviewRequest> getMTaskoverMap(HashMap<String, Object> hashMap);

}
