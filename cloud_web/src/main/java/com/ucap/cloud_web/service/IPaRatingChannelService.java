package com.ucap.cloud_web.service;


import java.util.List;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**
	/**
	 *  linhb　2016-09-05
	 * t通过关联详情  表获取  截图  和   附件
	 * @param pRequest
	 * @return
	 */
	public List<PaRatingChannel> getImgsAndAttch(PaRatingChannelRequest pRequest);
	/**
	 * linhb　2016-10-21
	 *  通过 ratingDetail id  获取  栏目信息
	 * @param ratingChannelRequery
	 * @return
	 */
	public List<PaRatingChannel> queryJoinList(PaRatingChannelRequest ratingChannelRequery);


}
