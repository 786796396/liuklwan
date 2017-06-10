package com.ucap.cloud_web.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.service.IDicItemService;
import com.ucap.cloud_web.util.CacheUtils;

/**
 * 描述： 描述:字典表工具(读取缓存)
 * 包：com.ucap.cloud_web.common
 * 文件名称：DicUtils
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-12-7上午11:59:32 
 * @version V1.0
 */
@Component
public class DicUtils {

	@Autowired
	private IDicItemService dicItemServiceImpl;

	public final String CACHE_DICT_MAP = "dictMap";
	public final String CACHE_DICT_LIST = "dictList";

	/*private static final DicUtils INSTANCE = new DicUtils();

	private DicUtils() {
	}

	public static final DicUtils getInstance() {
		return DicUtils.INSTANCE;
	}*/

	/**
	 * 
	 * @描述:根据 pEnName 取列表
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-7上午11:59:43 
	 * @param pEnName
	 * @return
	 */
	public List<DicItem> getDictList(String pEnName) {
		if (StringUtils.isEmpty(pEnName)) {
			return null;
		}
		String key = CACHE_DICT_LIST + pEnName;
		List<DicItem> dics = new ArrayList<DicItem>();
		try {
			dics = (List<DicItem>) CacheUtils.get(key);
			if (dics == null) {
				dics = new ArrayList<DicItem>();
				List<DicItem> dicList = dicItemServiceImpl.queryByPEnName(pEnName);
				if (CollectionUtils.isNotEmpty(dicList)) {
					CacheUtils.put(key, dicList);
					return dicList;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dics;
	}

	/**
	 * 
	 * @描述:根据 enName 取实体
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-7上午11:59:52 
	 * @param enName
	 * @return
	 */
	public DicItem getDicByEnName(String enName) {
		if (StringUtils.isEmpty(enName)) {
			return null;
		}
		try {
			String key = CACHE_DICT_LIST + enName;
			DicItem dic = (DicItem) CacheUtils.get(key);
			if (dic == null) {
				dic = dicItemServiceImpl.getByEnName(enName);
				if (dic != null) {
					CacheUtils.put(key, dic);
					return dic;
				}
			}
			return dic;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @描述:根据 enName 取 value
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-7下午12:00:07 
	 * @param enName
	 * @return
	 */
	public String getValue(String enName) {
		DicItem dic = getDicByEnName(enName);
		if (dic != null) {
			return dic.getValue();
		}
		return "";
	}

	public static void main(String[] args) {
		//System.out.println(DicUtils.getInstance().getValue("cr_basic_2weekDTYW"));
	}
}
