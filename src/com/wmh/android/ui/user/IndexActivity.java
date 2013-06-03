package com.wmh.android.ui.user;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;

import com.wmh.android.R;
import com.wmh.android.adapter.PinnedHeaderListAdapter;
import com.wmh.android.adapter.user.IndexPinnedHeaderListAdapter;
import com.wmh.android.http.HttpRequestListener;
import com.wmh.android.http.HttpUtil;
import com.wmh.android.util.IOUtil;
import com.wmh.android.widget.PinnedHeaderListView;

/**
 * index界面
 * 
 * @author wmh
 * 
 */
public class IndexActivity extends ListActivity {

	private List<Map<String, ?>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pinned_header_listview);
		initView();
	}

	private void initView() {
		PinnedHeaderListView indexList = (PinnedHeaderListView)getListView();
		data = new ArrayList<Map<String, ?>>();
		loadData();
		PinnedHeaderListAdapter adapter=new IndexPinnedHeaderListAdapter(this, data, R.layout.index_list_item, new String[] {}, new int[] {}, R.id.header);
		indexList.setAdapter(adapter);
		indexList.setOnScrollListener(adapter);
		indexList.setPinnedHeaderView(getLayoutInflater().inflate(
				R.layout.index_list_item_profile_header, indexList, false));
	}

	private void loadData() {
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			data.add(map);
		}
	}

}
