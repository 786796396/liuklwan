package com.ucap.cloud_web.dao;


import com.ucap.cloud_web.dto.PaAdviceRequest;
import com.ucap.cloud_web.entity.PaAdvice;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-26 09:34:34 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface PaAdviceDao extends GenericDao<PaAdvice>{
	/**
	 * 通过 id 修改  
	 * linhb 2016-08-26
	 * @param aaRequest
	 */
	void updateById(PaAdviceRequest aaRequest);}

