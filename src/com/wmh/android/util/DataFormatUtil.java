package com.wmh.android.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 数据格式转换工具类
 * 
 * @author wmh
 * 
 */
public class DataFormatUtil {



	public static String formatDate(String strDate) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date d = df.parse(strDate);

			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return df2.format(d);
		} catch (Exception e) {
		}
		return "";
	}

	public static String formatDate2(String strDate) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date d = df.parse(strDate);

			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return df2.format(d);
		} catch (Exception e) {
		}
		return "";
	}

	public static String formatDate3(String strDate) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			Date d = df.parse(strDate);

			SimpleDateFormat df2 = new SimpleDateFormat("MM-dd HH:mm");
			return df2.format(d);
		} catch (Exception e) {
		}
		return "";
	}
	
	/**
	 * 获取时间指定格式的字符串
	 * @param oridate
	 * @return
	 */	
	public static String formatDate4(String oridate){
		String pattern1="yyyy-MM-dd";
		String pattern2="yyyy.MM.dd";
		SimpleDateFormat sdf1=new SimpleDateFormat(pattern1);
		SimpleDateFormat sdf2=new SimpleDateFormat(pattern2);
		try {			
			Date date=sdf1.parse(oridate);
			sdf2.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
			String newdate=sdf2.format(date);
			return newdate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static String formatDate5(String oridate){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = "";
		try {
			Date date = format.parse(oridate);
			SimpleDateFormat df2 = new SimpleDateFormat("MM-dd HH:mm");
			SimpleDateFormat df3 = new SimpleDateFormat("HH:mm");
			Long timeMillis = (System.currentTimeMillis() - date.getTime()) / 1000;// 获得间隔时间，以秒为单位
			if (timeMillis < 86400) {
				time = "今日   " + df3.format(date);
			} else {
				time = df2.format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
	
	/**
	 * 得到当前系统的时间
	 * @return 当前时间
	 */
	public static String getCurrentLocaleData(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(new Date());
	}

	/**
	 * 处理时间参数
	 * 
	 * @param prama
	 *            时间字符串
	 * @return 经过处理的时间字符串
	 */
	public static String dealTime(String prama) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = "";
		try {
			Date date = format.parse(prama);

			SimpleDateFormat df2 = new SimpleDateFormat("MM-dd HH:mm");
			SimpleDateFormat df3 = new SimpleDateFormat("HH:mm");
			Long timeMillis = (System.currentTimeMillis() - date.getTime()) / 1000;// 获得间隔时间，以秒为单位
			if (timeMillis < 86400) {
				time = "今日   " + df3.format(date);
			} else {
				time = df2.format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 简单的处理时间参数，返回格式"MM-dd"
	 * 
	 * @param prama
	 *            时间字符串
	 * @return 经过处理的时间字符串
	 */
	public static String dealTimeSimple(String prama) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = "";
		try {
			Date date = format.parse(prama);

			SimpleDateFormat df2 = new SimpleDateFormat("MM-dd");
			time = df2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 处理百分数,已包装好颜色,采用html格式标签
	 * 
	 * @param prama
	 * @return 经过处理的百分数字符串
	 */
	public static String dealPercent(double prama) {
		if (prama > 0) {
			return new StringBuilder("<font color=red>").append(String.format("%.2f", prama * 100)).append("%</font>")
					.toString();
		} else {
			return new StringBuilder("<font color=green>").append(String.format("%.2f", prama * 100))
					.append("%</font>").toString();
		}
	}

	/**
	 * 处理百分数,不包括颜色 String.format("%.2f", prama * 100)
	 * 
	 * @param prama
	 * @return 经过处理的百分数字符串
	 */
	public static String dealPercentWithoutFont(double prama) {
		if (prama > 0) {
			return new StringBuilder().append(String.format("%.2f", prama * 100)).append("%").toString();
		} else {
			return new StringBuilder().append(String.format("%.2f", prama * 100)).append("%").toString();
		}
	}


}
