package com.wmh.android.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class CustomerPagerAdapter extends PagerAdapter {
	
	private List<View> mData; 

	public CustomerPagerAdapter(List<View> mData) {
		this.mData=mData;
	}

	public int getCount() {
		return mData.size();
	}

	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mData.get(position));
	}

	public Object instantiateItem(ViewGroup container, int position) {
		View view = mData.get(position);
		container.addView(view);
		return view;
	}
}
