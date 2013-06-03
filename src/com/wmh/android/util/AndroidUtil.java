package com.wmh.android.util;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * android属性等工具类
 * 
 * @author wmh
 *
 */
public class AndroidUtil {

	/**
	 * 获取手机终端编号
	 * 
	 * @param context
	 * @return
	 */
	public static String getImei(Context context) {
		String imei = "";
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			imei = telephonyManager.getDeviceId();
		} catch (Exception e) {
		}
		return imei;
	}
}
