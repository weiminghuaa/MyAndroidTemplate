package com.wmh.android.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * 通用的适配器
 * @author wmh
 *
 */
@SuppressWarnings("unchecked")
public class CustomerAdapter extends SimpleAdapter {
	ArrayList<HashMap<String, Object>> data;
	int resource;
	String[] from;
	int[] to;
	Context context;
	OnItemRenderListener listener; // item渲染时的监听器
	
	public CustomerAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, OnItemRenderListener listener) {
		super(context, data, resource, from, to);
		this.data = (ArrayList<HashMap<String, Object>>) data;
		this.resource = resource;
		this.from = from;
		this.to = to;
		this.context = context;
		this.listener = listener;
	}
	
	public CustomerAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		this(context, data, resource, from, to, null);
	}

	/**
	 * 每有一个item进入视野范围内的时候就会调用
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(resource, null);
		}
		HashMap<String, Object> map = data.get(position); // 取出item对应的map数据
		
		/**
		 * 初始化数据
		 */
		int toLength = to.length;
		for (int i = 0; i < toLength; i++) { // 遍历子控件
			int childId = to[i]; // 子控件的资源id
			View childView = convertView.findViewById(childId); // 得到子控件
			
			String key = from[i]; // 子控件对应的key
			Object value = map.get(key);  // 子控件对应的数据
			
			if (value == null) continue; // 说明这个控件对应的数据是null
			
			if (childView instanceof TextView) { // 如果的TextView，说明要设置文本
				((TextView)childView).setText(value.toString());
			} else if (childView instanceof ImageView) {// 如果的ImageView，说明要设置图片资源
				if (value instanceof Integer) { // 如果是资源id
					((ImageView)childView).setImageResource((Integer)value);
				} else if (value instanceof String) { // 如果是图片路径
					// 根据图片路径加载图片路径
					// /mnt/sdcard/
					//Bitmap bitmap = BitmapFactory.decodeFile(value.toString());
					//((ImageView)childView).setImageBitmap(bitmap);
					// ((ImageView)childView).setImageURI(Uri.parse("file:///mnt/sdcard/contacts/images/a.png"));
				}
			}
		}
		
		if (listener != null) {
			listener.onItemRender(convertView, map, position);
		}
		
		return convertView;
	}
	
	public interface OnItemRenderListener {
		/**
		 * @param convertView ： item对应的View
		 * @param map ： item对应的map数据
		 * @param position ： item的位置
		 */
		public void onItemRender(View convertView, HashMap<String, Object> map, int position);
	}
}
