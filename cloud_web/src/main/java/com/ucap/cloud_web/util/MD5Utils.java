package com.ucap.cloud_web.util;

import java.security.MessageDigest;

public class MD5Utils {
	private static final String CHARSET = "utf-8";
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes(CHARSET));
			byte b[] = md.digest();
//			byte[] btInput = str.getBytes(CHARSET);
			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}

	public static void main(String[] args) {
		/*
		 * System.out.println(md5("31119@qq.com" + "123456"));
		 * System.out.println(md5("mj1")); System.out.println(md5("lin" +
		 * DateUtil.getDays() + ",fh,"));
		 */
		System.out.println(md5("测试"));
	}
}
