package com.wmh.android.ui.user;

import java.util.ArrayList;
import java.util.List;

import com.wmh.android.R;
import com.wmh.android.adapter.CustomerPagerAdapter;
import com.wmh.android.widget.slidingmenu.SlidingMenu;

import android.app.Activity;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * test界面
 * 
 * @author wmh
 * 
 */
public class TestFragment6 extends ListFragment {
	
	private List<String> data;
	private ArrayAdapter adapter;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		data=new ArrayList<String>();
		adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		new AsyncTask<Void, Void, Void>(){

			@Override
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			protected void onPostExecute(Void result) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < 10; i++) {
					data.add("ff");
				}
				adapter.notifyDataSetChanged();
			}
			
		}.execute();
	}
}
