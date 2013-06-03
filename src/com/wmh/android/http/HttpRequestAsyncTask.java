package com.wmh.android.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import com.wmh.android.AppConstant;
import com.wmh.android.util.IOUtil;

import android.os.AsyncTask;
import android.util.Log;

/**
 * http异步请求task
 * 
 * @author wmh
 * 
 */
public abstract class HttpRequestAsyncTask extends AsyncTask<Void, Void, InputStream> {

	public HttpRequestListener listener;
	public String url;
	public Map<String, Object> httpParams;// 请求参数
	public boolean isNeedCookie;// 请求是否需要携带cookie
	private DefaultHttpClient client;
	public static final int REQUEST_TIMEOUT = 10000;// 设置请求超时10秒钟
	public static final int SO_TIMEOUT = 10000; // 设置等待数据超时时间10秒钟
	public int errorCode;// 请求失败code,AppConstant里面定义
	public boolean isDebug = true;

	public HttpRequestAsyncTask(HttpRequestListener listener, String url, Map<String, Object> httpParams,
			boolean isNeedCookie) {
		this.listener = listener;
		this.url = url;
		this.httpParams = httpParams;
		this.isNeedCookie = isNeedCookie;
		client = getHttpClient();
	}

	/**
	 * 实例化HttpClient，添加请求超时时间和等待时间
	 * 
	 * @return HttpClient对象
	 */
	public static DefaultHttpClient getHttpClient() {
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
		DefaultHttpClient client = new DefaultHttpClient(httpParams);
		return client;
	}

	protected void onPreExecute() {
		if (listener != null) {
			listener.onPreExecute();
		}
	};

	protected void onCancelled() {
		if (client != null) {
			client.getConnectionManager().shutdown();
		}
	};

	protected void onPostExecute(InputStream result) {
		if (result != null) {
			if (listener != null) {
				listener.requestFinished(result);
			}
		} else {
			listener.requestFailed(errorCode);
		}
	}

	@Override
	protected InputStream doInBackground(Void... params) {
		InputStream result = null;
		try {
			CookieStoreManager cookieStoreManager = CookieStoreManager.instance();
			if (isNeedCookie) {
				CookieStore cookieStore = cookieStoreManager.getCookieStore();
				if (cookieStore == null) {
					throw new CookieNotExistsException();
				}
				if (isDebug) {
					List<Cookie> cookies = cookieStore.getCookies();
					for (Cookie c : cookies) {
						Log.e("HttpRequestAsyncTask", "http request cookie:" + c.toString());
					}
				}
				client.setCookieStore(cookieStore);
			}

			// 得到请求返回数据流
			result = getInputStreamByHttp(client);

			// 保存cookie
			cookieStoreManager.setCookieStore(client.getCookieStore());
			if (isDebug) {
				List<Cookie> cookies = client.getCookieStore().getCookies();
				for (Cookie c : cookies) {
					Log.e("HttpRequestAsyncTask", "http response cookie:" + c.toString());
				}
			}
			return result;
		} catch (CookieNotExistsException e) {
			if (isDebug) {
				e.printStackTrace();
			}
			errorCode = AppConstant.HTTP_COOKIE_NOT_EXISTS_ERROR;
		} catch (ClientProtocolException e) {
			if (isDebug) {
				e.printStackTrace();
			}
			errorCode = AppConstant.HTTP_CLIENT_PROTOCOL_ERROR;
		} catch (IllegalStateException e) {
			if (isDebug) {
				e.printStackTrace();
			}
			errorCode = AppConstant.HTTP_ILLEGAL_STATE_ERROR;
		} catch (IOException e) {
			if (isDebug) {
				e.printStackTrace();
			}
			errorCode = AppConstant.HTTP_IO_ERROR;
		}
		return result;
	}

	public abstract InputStream getInputStreamByHttp(DefaultHttpClient client) throws IOException,
			ClientProtocolException;

}
