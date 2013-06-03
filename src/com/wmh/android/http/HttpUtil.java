package com.wmh.android.http;

import java.util.Map;

/**
 * 网络请求工具类
 * 
 * @author william
 * 
 */
public class HttpUtil {

	private static HttpRequestAsyncTask task;
	public static final int RESPONSE_TYPE_STRING = 0;
	public static final int RESPONSE_TYPE_INPUTSTREAM = 1;
	public static final int RESPONSE_TYPE_BITMAP = 2;

	/**
	 * post請求，不帶cookie
	 * 
	 * @param url
	 * @param postParams
	 * @param listener
	 */
	public static void doPost(String url, Map<String, Object> httpParams, HttpRequestListener listener) {
		doPost(url, httpParams, false, listener);
	}

	/**
	 * post請求
	 * 
	 * @param url
	 * @param httpParams
	 * @param listener
	 * @param responseType
	 */
	public static void doPost(String url, Map<String, Object> httpParams, boolean isNeedCookie,
			HttpRequestListener listener) {
		task = new HttpPostAsyncTask(listener, url, httpParams, isNeedCookie);
		task.execute();
	}

	/**
	 * post file等MultipartEntity請求，不帶cookie
	 * 
	 * @param url
	 * @param httpParams
	 * @param listener
	 */
	public static void doMultipartPost(String url, Map<String, Object> httpParams, HttpRequestListener listener) {
		doMultipartPost(url, httpParams, false, listener);
	}

	/**
	 * post請求
	 * 
	 * @param url
	 * @param httpParams
	 * @param listener
	 */
	public static void doMultipartPost(String url, Map<String, Object> httpParams, boolean isNeedCookie,
			HttpRequestListener listener) {
		task = new HttpMultipartPostAsyncTask(listener, url, httpParams, isNeedCookie);
		task.execute();
	}

	/**
	 * get請求，不帶cookie
	 * 
	 * @param url
	 * @param httpParams
	 * @param listener
	 */
	public static void doGet(String url, Map<String, Object> httpParams, HttpRequestListener listener) {
		doGet(url, httpParams, false, listener);
	}

	/**
	 * get請求
	 * 
	 * @param url
	 * @param httpParams
	 * @param listener
	 */
	public static void doGet(String url, Map<String, Object> httpParams, boolean isNeedCookie,
			HttpRequestListener listener) {
		task = new HttpGetAsyncTask(listener, url, httpParams, isNeedCookie);
		task.execute();
	}

	/**
	 * 停止http请求
	 */
	public static void stopHttpRequest() {
		task.cancel(true);
	}

}
