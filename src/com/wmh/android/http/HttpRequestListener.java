package com.wmh.android.http;

import java.io.InputStream;

/**
 * 请求Listener
 * 
 * @author william
 *
 */
public interface HttpRequestListener {

	/**
	 * 请求成功
	 * 
	 * @param result 
	 */
	public void requestFinished(InputStream result);
	
	/**
	 * 请求失败
	 * 
	 * @param error
	 */
	public void requestFailed(int error);

	/**
	 * 请求前执行
	 */
	public void onPreExecute();

}
