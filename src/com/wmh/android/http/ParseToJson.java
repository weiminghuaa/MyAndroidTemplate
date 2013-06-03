package com.wmh.android.http;

import org.json.JSONObject;

/**
 * @className:ParseToJson.java
 * @classDescription:
 * @author:zhangfarong
 * @createTime:2013-5-12 下午2:34:27
 */
public class ParseToJson {

	
	/**
	 * 统一的json解析方法
	 * 
	*/
	
	public static ResultBean parseToJsonMethod(String result){
		
		ResultBean resultBean = new ResultBean();
		
		try {
			
			JSONObject jsonObject = new JSONObject(result).getJSONObject("result"); 

			resultBean.setCode(jsonObject.getString("code"));
			resultBean.setMessage(jsonObject.getString("messages"));
			
		} catch (Exception e) {
			
			return null;
		}
		
		return resultBean;
	}
}
