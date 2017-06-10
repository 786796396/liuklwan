package com.ucap.cloud_web.common;

import java.util.Comparator;
import java.util.Map;

/**
 * @描述:[排序]: map按指定Key值排序的比较器   要排序的key为 【errorThan】 字符串类型
 * 关键栏目，业务系统使用
 * @作者:liukl@ucap.com.cn
 * @时间:2017年4月19日15:02:38
 * @return
 */
public class ComparatorColumnHashMap implements Comparator<Object>{
	@Override
	public int compare(Object o1, Object o2) {
		@SuppressWarnings("unchecked")
		Map<String,Object> map1 =(Map<String,Object>) o1;
		@SuppressWarnings("unchecked")
		Map<String,Object> map2 =(Map<String,Object>) o2;
		if(Float.valueOf((String)map1.get("errorThan")) != null && Float.valueOf((String)map2.get("errorThan"))!= null){
			if(Float.valueOf((String)map1.get("errorThan")) > Float.valueOf((String)map2.get("errorThan"))){
				return -1;
			}else if(Float.valueOf((String)map2.get("errorThan")) > Float.valueOf((String)map1.get("errorThan"))){
				return 1;
			}
		}
		return 0;
	}

}
