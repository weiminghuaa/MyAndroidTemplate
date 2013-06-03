package com.wmh.android.ui.user;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

/**
 * test界面
 * 
 * @author wmh
 * 
 */
public class TestFragment1 extends ListFragment {
	
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
					data.add("aa");
				}
				adapter.notifyDataSetChanged();
			}
			
		}.execute();
	}
}
