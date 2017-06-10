package com.ucap.cloud_web.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.functions.T;

/**
 * <p>Description:日常监测-简单内存缓存类 </p>
 * <p>@Package：com.ucap.cloud_backweb.utils </p>
 * <p>Title: CacheUtil </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：masl@ucap.com.cn </p>
 * <p>@date：2016年11月11日下午2:45:08 </p>
 */
public class MonitoringCacheUtils {
	private static Map<String, Object> m_objects;
	private static Map<String, Long> m_expiredObjects;
	private static long m_lExpireTime;
	private static ExecutorService m_executor;

	static {
		MonitoringCacheUtil(36000);// 一小时
	}

	private static void MonitoringCacheUtil(final int nExpireTime) {
		m_objects = Collections.synchronizedMap(new HashMap<String, Object>());
		m_expiredObjects = Collections.synchronizedMap(new HashMap<String, Long>());
		m_lExpireTime = nExpireTime;
		m_executor = Executors.newFixedThreadPool(256);
		Executors.newScheduledThreadPool(5).scheduleWithFixedDelay(removeExpiredObjects(), m_lExpireTime / 2, m_lExpireTime, TimeUnit.SECONDS);
	}

	private static Runnable removeExpiredObjects() {
		return new Runnable() {
			public void run() {
				for (final String name : m_expiredObjects.keySet()) {
					if (System.currentTimeMillis() > m_expiredObjects.get(name)) {
						m_executor.execute(createRemoveRunnable(name));
					}
				}
			}
		};
	}

	private static Runnable createRemoveRunnable(final String name) {
		return new Runnable() {
			public void run() {
				m_objects.remove(name);
				m_expiredObjects.remove(name);
			}
		};
	}

	public static long getExpireTime() {
		return m_lExpireTime;
	}

	public static void put(final String name, final T obj) {
		put(name, obj, m_lExpireTime);
	}

	public static void main(String[] args) {
		//long currentTime = System.currentTimeMillis() + 60 * 10 * 1000;

		/*	SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
			Date date = new Date(currentTime);
			System.out.println(formatter.format(date));*/

		try {
			MonitoringCacheUtils.put("name", "小明", 5);
			for (int i = 0; i < 1000; i++) {
				Object obj = MonitoringCacheUtils.get("name");
				Thread.sleep(1000);
				if (obj != null)
					System.out.println(obj.toString());
				else{
					System.out.println("缓存已经失效");
					break;
				}

			}
		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

	/**
	 * 
	 * @param name
	 * @param obj
	 * @param expireTime 单位：秒
	 */
	public static void put(final String name, final Object obj, final long expireTime) {
		m_objects.put(name, obj);
		m_expiredObjects.put(name, System.currentTimeMillis() + expireTime * 1000);
	}

	/**
	 * 
	 * @param name
	 * @param obj
	 */
	public static void put(final String name, final Object obj) {
		m_objects.put(name, obj);
		m_expiredObjects.put(name, System.currentTimeMillis() + m_lExpireTime * 1000);
	}

	public static Object get(final String name) {
		final Long expireTime = m_expiredObjects.get(name);
		if (expireTime == null)
			return null;
		if (System.currentTimeMillis() > expireTime) {
			m_executor.execute(createRemoveRunnable(name));
			return null;
		}
		return m_objects.get(name);
	}
}