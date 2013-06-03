package com.wmh.android.adapter.user;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;

import com.wmh.android.adapter.PinnedHeaderListAdapter;
import com.wmh.android.ui.user.IndexActivity;

/**
 * index页面的置顶滑动listview adapter
 * 
 * @author wmh
 *
 */
public class IndexPinnedHeaderListAdapter extends PinnedHeaderListAdapter{

	public IndexPinnedHeaderListAdapter(Context context, List<Map<String, ? extends Object>> data, int resource,
			String[] from, int[] to, int headerViewId) {
		super(context, data, resource, from, to, headerViewId);
	}

	@Override
	protected void initConvertView() {
		
	}

	@Override
	protected void initPinnedHeader(View header, int position) {
		
	}

}
