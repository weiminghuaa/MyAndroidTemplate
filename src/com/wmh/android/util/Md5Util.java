package com.wmh.android.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加解密工具类
 * 
 * @author wmh
 * 
 */
public class Md5Util {

	private static MessageDigest sMd5MessageDigest;
	private static StringBuilder sStringBuilder;

	static {
		try {
			sMd5MessageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			//
		}
		sStringBuilder = new StringBuilder();
	}

	private Md5Util() {
	}

	/**
	 * MD5加密小写
	 * 
	 * @param s
	 *            原文
	 * @return 密文
	 */
	public static String md5lower(String s) {

		sMd5MessageDigest.reset();
		sMd5MessageDigest.update(s.getBytes());

		byte digest[] = sMd5MessageDigest.digest();

		sStringBuilder.setLength(0);
		for (int i = 0; i < digest.length; i++) {
			final int b = digest[i] & 255;
			if (b < 16) {
				sStringBuilder.append('0');
			}
			sStringBuilder.append(Integer.toHexString(b));
		}

		return sStringBuilder.toString();
	}

	/**
	 * MD5加密
	 * 
	 * @param sessionid
	 *            原文
	 * @return 密文
	 */
	public static String md5(String sessionid) {
		StringBuffer hexString = null;
		byte[] defaultBytes = sessionid.getBytes();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();

			hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				if (Integer.toHexString(0xFF & messageDigest[i]).length() == 1) {
					hexString.append(0);
				}
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
		} catch (NoSuchAlgorithmException nsae) {
		} catch (Exception e) {
		}
		return hexString.toString().toUpperCase();
	}

}
