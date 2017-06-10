package com.ucap.cloud_web.service;


import java.util.List;
import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.DicItemRequest;
import com.ucap.cloud_web.dto.SpSiteRequest;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.SpSite;


/**
* <br>
* <b>作者：</b>linhb<br>
* <b>日期：</b> 2016-09-13 20:17:57 <br>
* <b>版权所有：<b>版权所有(C) 2016<br>
*/
public interface IDicItemService {


	/**
	* 添加数据
	* @param dicItem			对象			(必填)
	*/
	public int add(DicItem dicItem);

	/**
	* 通过主键获取对象数据
	* @param id						主键			(必填)
	* @return dicItem
	*/
	public DicItem get(Integer id);

	/**
	* 修改数据
	* @param DicItem			对象			(必填)
	*/
	public void update(DicItem dicItem);

	/**
	* 通过id删除数据
	* @param id						对象			(必填)
	*/
	public void delete(Integer id);

	/**
	* 通过对象获取分页数据
	* @param request				dto对象			(必填)
	* @return	PageVo<DicItem>
	*/
	public PageVo<DicItem> query(DicItemRequest request);

	/**
	* 查询总条数
	* @param request				前台参数			(必填)
	* @return	int
	*/
	public int queryCount(DicItemRequest request);

	/**
	* 查询对象集合
	* @param request				前台参数			(必填)
	* @return	List<DicItem>
	*/
	public List<DicItem> queryList(DicItemRequest request);


	public List<DicItem> queryImgSize(DicItemRequest dicrequest);
	/**
	 * @描述:根据英文名称查询
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-7下午12:17:45 
	 * @param enName
	 * @return
	 */
	public DicItem getByEnName(String enName);
	/**
	 * @描述:根据 pid 查询
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-7下午12:18:03 
	 * @param pId
	 * @return
	 */
	public List<DicItem> queryByPid(int pId);
	/**
	 * @描述:根据英文名称查询数量
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-7下午12:18:09 
	 * @param enName
	 * @return
	 */
	public int  queryCountByEnName(String enName); 
	/**
	 * 
	 * @描述:根据 父节点  enName 查询
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-7下午12:14:11 
	 * @param pEnName
	 * @return
	 */
	public List<DicItem> queryByPEnName(String pEnName);

}

