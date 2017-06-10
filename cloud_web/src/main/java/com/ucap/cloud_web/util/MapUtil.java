package com.ucap.cloud_web.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapUtil {

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(
			Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(
				map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println("111");
		HashMap<String,Double> map = new HashMap<String,Double>();  
	   // HashMap<String,Double> sorted_map = new HashMap<String,Double>();
//		map.put("A",99.5);  
//        map.put("B",67.4);  
//        map.put("C",67.4);  
//        map.put("D",67.3);  
        
    	
       Map sorted_map=  MapUtil.sortByValue(map);

		System.out.println("unsorted map: "+map);  
		
		//unsorted map: {D=67.3, A=99.5, B=67.4, C=67.4}
		
		System.out.println("sorted map: "+sorted_map);  
		
	

	}
}
