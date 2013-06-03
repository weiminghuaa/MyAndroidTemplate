package com.wmh.android.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * 异步post请求task
 * 
 * @author wmh
 * 
 */
public class HttpPostAsyncTask extends HttpRequestAsyncTask {

	public HttpPostAsyncTask(HttpRequestListener listener, String url, Map<String, Object> httpParams,
			boolean isNeedCookie) {
		super(listener, url, httpParams, isNeedCookie);
	}

	@Override
	public InputStream getInputStreamByHttp(DefaultHttpClient client) throws IOException, ClientProtocolException {
		InputStream is;
		HttpPost post = new HttpPost(url);
		// 设置请求体
		HttpEntity reqEntity = null;
		if (httpParams != null && httpParams.size() > 0) {
			// 创建一个用于添加到请求实体里面的参数列表
			List<NameValuePair> paramters = new ArrayList<NameValuePair>();
			// 添加的请求参数
			Iterator<Entry<String, Object>> it = httpParams.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				Object value = entry.getValue();
				if (value instanceof String[]) {
					String[] ss = (String[]) value;
					for (String s : ss) {
						paramters.add(new BasicNameValuePair(entry.getKey(), s));
					}
				} else {
					paramters.add(new BasicNameValuePair(entry.getKey(), value.toString()));
				}
			}
			reqEntity = new UrlEncodedFormEntity(paramters);
		}

		// 创建一个请求实体
		if (reqEntity != null) {
			post.setEntity(reqEntity);
		}
		// 发送请求
		HttpResponse response = client.execute(post);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new IOException();
		} else {
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		return is;
	}

}
