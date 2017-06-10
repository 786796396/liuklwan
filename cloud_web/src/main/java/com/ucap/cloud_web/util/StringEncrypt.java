package com.ucap.cloud_web.util;

import java.security.*;

import javax.crypto.Cipher;

import javax.crypto.SecretKey;

import javax.crypto.SecretKeyFactory;

import javax.crypto.spec.DESKeySpec;

public class StringEncrypt {

	public static final String PASSWORD_CRYPT_KEY = "Ucap2016@so.kaipuyun.cn";

	private final static String DES = "DES";

	/**
	 * 加密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */

	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {

		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);

	}

	/**
	 * 
	 * 解密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */

	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);

	}

	/**
	 * 
	 * 密码解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */

	public final static String decrypt(String data) {

		try {
			return new String(decrypt(hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));

		} catch (Exception e) {

		}

		return null;

	}
	
	/**
	 * 
	 * 定制加密串密码解密
	 * 
	 * @param data
	 * @param customKey
	 * @return
	 * @throws Exception
	 */

	public final static String decrypt(String data,String customKey) {

		try {
			return new String(decrypt(hex2byte(data.getBytes("utf-8")), customKey.getBytes()));

		} catch (Exception e) {

		}

		return null;

	}

	/**
	 * 密码加密
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String password) {

		try {
			return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}

		return null;
	}
	
	/**
	 * 密码加密
	 * 
	 * @param password
	 * @param customKey
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String password,String customKey) {

		try {
			return byte2hex(encrypt(password.getBytes("utf-8"), customKey.getBytes()));
		} catch (Exception e) {
		}

		return null;
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {

		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}

		return hs.toUpperCase();

	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");

		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}

		return b2;
	}

	public static void main(String[] args) {
		String a = "toptime";
		System.out.println(a);
//		String str = "E749E9387709CE8D4ED499D76DB1622ACE313F1B2711FA1F23F07F9BD6C8ED12";
//		System.out.println("key =" + str);
//		String strEnc = StringEncrypt.encrypt(a);// 加密字符串,返回String的密文
//		System.out.println("密文=" + strEnc);
//		String strDes = StringEncrypt.decrypt(strEnc);// 把String 类型的密文解密
//		System.out.println("明文=" + strDes);
		String ss=encrypt("userName=440000&passWord=fvnDq1", PASSWORD_CRYPT_KEY);
		System.out.println(ss);
		String q=ss;
		String data = decrypt(q, PASSWORD_CRYPT_KEY);
		System.out.println(data);
		
		if(data != null){
			String userName = data.split("&")[0].split("=")[1];
			String passWord = data.split("&")[1].split("=")[1];
			System.out.println(userName+"<<<<<<<<<<<<<<>>>>>>>>>>>"+passWord);
		}
		
		
		
		
	}
}
