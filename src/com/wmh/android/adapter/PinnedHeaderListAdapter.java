package com.wmh.android.adapter;

import java.util.List;
import java.util.Map;
import com.wmh.android.widget.PinnedHeaderListView;
import com.wmh.android.widget.PinnedHeaderListView.PinnedHeaderAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * 置顶滑动listview adapter
 * 
 * @author wmh
 * 
 */
public abstract class PinnedHeaderListAdapter extends SimpleAdapter implements PinnedHeaderAdapter, OnScrollListener {

	protected int resource;
	protected String[] from;
	protected int[] to;
	protected Context context;
	protected List<Map<String, ? extends Object>> data;
	private int lastItem = 0;
	protected int headerViewId;

	public PinnedHeaderListAdapter(Context context, List<Map<String, ? extends Object>> data, int resource,
			String[] from, int[] to, int headerViewId) {
		super(context, data, resource, from, to);
		this.data = data;
		this.resource = resource;
		this.from = from;
		this.to = to;
		this.context = context;
		this.headerViewId = headerViewId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(resource, null);
		}
		RelativeLayout header = (RelativeLayout) convertView.findViewById(headerViewId);
		initConvertView();
		if (lastItem == position) {
			header.setVisibility(View.INVISIBLE);
		} else {
			header.setVisibility(View.VISIBLE);
		}
		return convertView;
	}

	protected abstract void initConvertView();

	@Override
	public int getPinnedHeaderState(int position) {
		return PINNED_HEADER_PUSHED_UP;
	}

	@Override
	public void configurePinnedHeader(View header, int position) {
		if (lastItem != position) {
			notifyDataSetChanged();
		}
		lastItem = position;
		initPinnedHeader(header, position);
	}

	protected abstract void initPinnedHeader(View header, int position);

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (view instanceof PinnedHeaderListView) {
			((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

}
