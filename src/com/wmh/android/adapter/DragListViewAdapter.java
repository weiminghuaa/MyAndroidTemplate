package com.wmh.android.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

/**
 * 自定义控件DragListView的adapter,抽象类
 * 
 * @author wmh
 *
 */
public abstract class DragListViewAdapter extends SimpleAdapter {

	protected List<Map<String, Object>> data;
	protected int resource;
	protected String[] from;
	protected int[] to;
	protected Context context;
	private boolean showItem;
	private boolean isChanged;
	private int dropPosition;

	@SuppressWarnings("unchecked")
	public DragListViewAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		this.data = (List<Map<String, Object>>) data;
		this.resource = resource;
		this.from = from;
		this.to = to;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null || isChanged) {
			convertView = LayoutInflater.from(context).inflate(resource, null);
		}

		initConvertView(position, convertView, parent);

		if (isChanged) {
			if (position == dropPosition) {
				if (!showItem) {
					convertView.setVisibility(View.INVISIBLE);
				}
			}
		}
		return convertView;
	}

	/**
	 * 子类实现，init convertView
	 * 
	 * @param position
	 * @param convertView
	 * @param parent
	 */
	protected abstract void initConvertView(int position, View convertView, ViewGroup parent);

	/**
	 * 位置对调
	 * 
	 * @param startPosition
	 * @param dropPosition
	 */
	public void exchange(int startPosition, int dropPosition) {
		Map<String, Object> start = data.get(startPosition);
		data.remove(startPosition);
		data.add(dropPosition, start);
		isChanged = true;
		this.dropPosition = dropPosition;
		notifyDataSetChanged();
	}

	/**
	 * 是否显示dropItem
	 * 
	 * @param showItem
	 */
	public void showDropItem(boolean showItem) {
		this.showItem = showItem;
	}
}
