package com.wmh.android.adapter.user;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wmh.android.R;

/**
 * 侧滑menu list adapter
 * 
 * @author wmh
 *
 */
public class SlidingMenuListAdapter extends ArrayAdapter<String> {

	public SlidingMenuListAdapter(Context context, int resource, String[] objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = super.getView(position, convertView, parent);
		if (position == 0) {
			v.setBackgroundResource(R.drawable.left_nav_spiltchoose);
		}
		((TextView) v).setTextColor(Color.WHITE);
		return v;
	}

}