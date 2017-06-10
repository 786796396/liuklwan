package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.AccountBindInfoRequest;
import com.ucap.cloud_web.entity.AccountBindInfo;
import com.publics.util.dao.GenericDao;
/**
* <br>
* <b>作者：</b>cuichx<br>
* <b>日期：</b> 2015-12-18 09:26:33 <br>
* <b>版权所有：<b>版权所有(C) 2015<br>
*/

public interface AccountBindInfoDao extends GenericDao<AccountBindInfo>{

	/**
	 * @Description: 查询客户信息表和微信绑定账户信息表
	 * @author cuichx --- 2015-12-25上午11:30:39     
	 * @param paramMap
	 * @return
	 */
	AccountBindInfoRequest queryByMap(Map<String, Object> paramMap);
	
	/**
	 * @Description: 通过网站标识码查询客户信息
	 * @author cuichx --- 2015-12-30下午8:54:47     
	 * @param map
	 * @return
	 */
	List<AccountBindInfoRequest> queryBySiteCode(Map<String, Object> map);
	/**
	 * @Description: 
	 * @author cuichx --- 2015-12-31下午1:31:01     
	 * @param earlyMap
	 * @return
	 */
	List<AccountBindInfoRequest> queryEarlyInfo(Map<String, Object> earlyMap);
	/**
	 * @Description: 联表查询微信绑定账户信息表和站点信息表 
	 * @author cuichx --- 2016-1-13下午9:47:29     
	 * @param paramMap
	 * @return
	 */
	List<AccountBindInfoRequest> queryTBSite(Map<String, Object> paramMap);
	/**
	 * @Description: 绑定账户信息、合同信息表、服务周期表多表查询
	 * @author cuichx --- 2016-3-16下午2:55:48     
	 * @param paramMap
	 * @return
	 */
	List<AccountBindInfoRequest> queryTBByMap(Map<String, Object> paramMap);
	/**
	 * @Description: 获取微信绑定账户中所有的免费的标识码（组织单位编号或者填报单位编码）
	 * @author cuichx --- 2016-5-25下午3:22:51     
	 * @param paramMap
	 * @return
	 */
	List<AccountBindInfo> queryListByMap(Map<String, Object> paramMap);

}

