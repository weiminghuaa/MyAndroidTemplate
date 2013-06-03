package com.wmh.android.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpMultipartPostAsyncTask extends HttpRequestAsyncTask {

	public HttpMultipartPostAsyncTask(HttpRequestListener listener, String url, Map<String, Object> httpParams,
			boolean isNeedCookie) {
		super(listener, url, httpParams, isNeedCookie);
	}

	@Override
	public InputStream getInputStreamByHttp(DefaultHttpClient client) throws IOException, ClientProtocolException {
		InputStream is;
		HttpPost post = new HttpPost(url);
		// 设置请求体
		MultipartEntity reqEntity = new MultipartEntity();
		if (httpParams != null && httpParams.size() > 0) {
			// 添加的请求参数
			Iterator<Entry<String, Object>> it = httpParams.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				Object value = entry.getValue();
				Log.e("", "name:" + entry.getKey() + ",value:" + value);
				if (value instanceof String) {
					reqEntity.addPart(entry.getKey(), new StringBody(entry.getValue().toString(), Charset
							.forName("UTF-8")));
				} else if (value instanceof File) {
					reqEntity.addPart(entry.getKey(), new FileBody((File) value));
				}
			}
		}
		post.setEntity(reqEntity);

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
