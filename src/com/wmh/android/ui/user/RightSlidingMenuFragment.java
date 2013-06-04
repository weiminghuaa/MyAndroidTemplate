package com.wmh.android.ui;

import com.wmh.android.R;
import com.wmh.android.adapter.user.SlidingMenuListAdapter;
import com.wmh.android.ui.user.IndexFragment;
import com.wmh.android.ui.user.TestFragment6;
import com.wmh.android.ui.user.TestFragment7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * 右侧menu
 * 
 * @author wmh
 * 
 */
public class RightSlidingMenuFragment extends Fragment {

	private int rightListFocusItemPosition;

	private AdapterView.OnItemClickListener rightOnItemClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// 改变item的背景
			if (rightListFocusItemPosition == position) {
				return;
			} else {
				parent.getChildAt(rightListFocusItemPosition).setBackgroundResource(0);
				view.setBackgroundResource(R.drawable.left_nav_spiltchoose);
				rightListFocusItemPosition = position;
			}
			String tag = null;
			switch (position) {
			case 0:
				tag = IndexFragment.class.getName();
				break;

			case 1:
				tag = TestFragment6.class.getName();
				break;

			case 2:
				tag = TestFragment7.class.getName();
				break;

			default:
				break;
			}
			if (tag != null)
				switchFragment(tag);
		}
	};

	private View.OnClickListener mOnClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {

			default:
				break;
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.right_sliding_menu, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ListView rightSlidingMenuListView = (ListView) view.findViewById(R.id.rightSlidingMenuListView);
		String[] rightListData = new String[] { "子栏目 a", "子栏目 b", "子栏目 c", "子栏目 d", "子栏目 e", "子栏目 f" };
		rightSlidingMenuListView.setAdapter(new SlidingMenuListAdapter(getActivity(),
				android.R.layout.simple_list_item_1, rightListData));
		rightSlidingMenuListView.setOnItemClickListener(rightOnItemClickListener);
	}

	/**
	 * 切换content fragment
	 * 
	 * @param fragment
	 */
	protected void switchFragment(String tag) {
		if (getActivity() == null)
			return;

		if (getActivity() instanceof MainActivity) {
			MainActivity activity = (MainActivity) getActivity();
			activity.switchContent(tag);
		}
	}
}
