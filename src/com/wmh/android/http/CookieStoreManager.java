package com.wmh.android.http;

import org.apache.http.client.CookieStore;

/**
 * 单例模式，HttpClient的CookieStore存取管理
 * 
 * @author wmh
 *
 */
public class CookieStoreManager {
	
	private static CookieStoreManager cookieStoreManager;
	private CookieStore cookieStore;
	
	private CookieStoreManager(){}

	public static CookieStoreManager instance() {
		synchronized (CookieStoreManager.class) {
			if (cookieStoreManager==null) {
				cookieStoreManager=new CookieStoreManager();
			}
		}
		return cookieStoreManager;
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public synchronized void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

}
