package com.ucap.cloud_web.common;

import java.util.Comparator;
import java.util.Map;

/**
 * @描述:[排序]: map按指定Key值排序的比较器  要排序的key为 【size】
 * @作者:luocheng@ucap.com.cn
 * @时间:2017/1/9
 * @return
 */
public class ComparatorHashMap implements Comparator<Object>{
	@Override
	public int compare(Object o1, Object o2) {
		@SuppressWarnings("unchecked")
		Map<String,Object> map1 =(Map<String,Object>) o1;
		@SuppressWarnings("unchecked")
		Map<String,Object> map2 =(Map<String,Object>) o2;
		if((Integer)map1.get("size") != null && (Integer)map2.get("size") != null){
			if((Integer)map1.get("size") > (Integer)map2.get("size")){
				return -1;
			}else if((Integer)map2.get("size") > (Integer)map1.get("size")){
				return 1;
			}
		}
		return 0;
	}

}
