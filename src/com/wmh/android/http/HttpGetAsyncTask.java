package com.wmh.android.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * 异步get请求task
 * 
 * @author wmh
 * 
 */
public class HttpGetAsyncTask extends HttpRequestAsyncTask {

	public HttpGetAsyncTask(HttpRequestListener listener, String url, Map<String, Object> httpParams,
			boolean isNeedCookie) {
		super(listener, url, httpParams, isNeedCookie);
	}

	@Override
	public InputStream getInputStreamByHttp(DefaultHttpClient client) throws IOException, ClientProtocolException {
		InputStream is = null;
		String requestUrl = buildRequestUrl();
		HttpResponse response = client.execute(new HttpGet(requestUrl));
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new IOException();
		} else {
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		return is;
	}

	/**
	 * 封装请求地址
	 * 
	 * @return
	 */
	private String buildRequestUrl() {
		String requestUrl;
		// 设置请求参数
		if (httpParams != null && httpParams.size() > 0) {
			StringBuffer temp = new StringBuffer("?");
			// 添加的请求参数
			Iterator<Entry<String, Object>> it = httpParams.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				Object value = entry.getValue();
				if (value instanceof String) {
					temp.append(entry.getKey()).append("=").append((String) value).append("&");
				}
			}
			String param = temp.substring(0, temp.length() - 2);
			requestUrl = new StringBuffer(url).append(param).toString();
		} else {
			requestUrl = url;
		}
		return requestUrl;
	}

}
