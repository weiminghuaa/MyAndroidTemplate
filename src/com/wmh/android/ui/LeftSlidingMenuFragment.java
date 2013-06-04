package com.wmh.android.ui;

import com.wmh.android.R;
import com.wmh.android.adapter.user.SlidingMenuListAdapter;
import com.wmh.android.ui.user.IndexFragment;
import com.wmh.android.ui.user.TestFragment1;
import com.wmh.android.ui.user.TestFragment2;
import com.wmh.android.ui.user.TestFragment3;
import com.wmh.android.ui.user.TestFragment4;
import com.wmh.android.ui.user.TestFragment5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * 左侧menu
 * 
 * @author wmh
 * 
 */
public class LeftSlidingMenuFragment extends Fragment {

	private TextView topTextView, bottomTextView;
	private View topChooseLine, bottomChooseLine;
	private ViewFlipper viewFlipper;
	private ListView previousListView, nextListView;
	private int previousListFocusItemPosition;
	private int nextListFocusItemPosition;

	private AdapterView.OnItemClickListener previousOnItemClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// 改变item的背景
			if (previousListFocusItemPosition == position) {
				((MainActivity)getActivity()).getSlidingMenu().showContent();
				return;
			} else {
				parent.getChildAt(previousListFocusItemPosition).setBackgroundResource(0);
				view.setBackgroundResource(R.drawable.left_nav_spiltchoose);
				previousListFocusItemPosition = position;
			}
			String tag = null;
			switch (position) {
			case 0:
				tag = IndexFragment.class.getName();
				break;

			case 1:
				tag = TestFragment1.class.getName();
				break;

			case 2:
				tag = TestFragment2.class.getName();
				break;

			case 3:
				tag = TestFragment3.class.getName();
				break;

			case 4:
				tag = TestFragment4.class.getName();
				break;

			case 5:
				tag = TestFragment5.class.getName();
				break;

			default:
				break;
			}
			if (tag != null)
				switchFragment(tag);
		}
	};
	private AdapterView.OnItemClickListener nextOnItemClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// 改变item的背景
			if (previousListFocusItemPosition == position) {
				((MainActivity)getActivity()).getSlidingMenu().showContent();
				return;
			} else {
				parent.getChildAt(nextListFocusItemPosition).setBackgroundResource(0);
				view.setBackgroundResource(R.drawable.left_nav_spiltchoose);
				nextListFocusItemPosition = position;
			}
			String tag = null;
			switch (position) {
			case 0:
				tag = IndexFragment.class.getName();
				break;

			case 1:
				tag = TestFragment1.class.getName();
				break;

			case 2:
				tag = TestFragment2.class.getName();
				break;

			case 3:
				tag = TestFragment3.class.getName();
				break;

			case 4:
				tag = TestFragment4.class.getName();
				break;

			case 5:
				tag = TestFragment5.class.getName();
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
			case R.id.topTextView:
				// 改变字体颜色和chooseline
				if (topTextView.isEnabled()) {
					topTextView.setEnabled(false);
					bottomTextView.setEnabled(true);
					topChooseLine.setVisibility(View.VISIBLE);
					bottomChooseLine.setVisibility(View.INVISIBLE);
				}
				// 切换listview
				if (previousListView.getVisibility() == View.VISIBLE)
					return;
				viewFlipper.showPrevious();
				break;

			case R.id.bottomTextView:
				// 改变字体颜色和chooseline
				if (bottomTextView.isEnabled()) {
					bottomTextView.setEnabled(false);
					topTextView.setEnabled(true);
					bottomChooseLine.setVisibility(View.VISIBLE);
					topChooseLine.setVisibility(View.INVISIBLE);
				}
				// 切换listview
				if (nextListView.getVisibility() == View.VISIBLE)
					return;
				viewFlipper.showNext();
				break;

			default:
				break;
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.left_sliding_menu, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		topTextView = (TextView) view.findViewById(R.id.topTextView);
		bottomTextView = (TextView) view.findViewById(R.id.bottomTextView);
		viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
		previousListView = (ListView) view.findViewById(R.id.previousListView);
		nextListView = (ListView) view.findViewById(R.id.nextListView);
		topChooseLine = view.findViewById(R.id.topChooseLine);
		bottomChooseLine = view.findViewById(R.id.bottomChooseLine);

		String[] previousListViewData = new String[] { "子栏目 1", "子栏目 2", "子栏目 3", "子栏目 4", "子栏目 5", "子栏目 6" };
		String[] nextListViewData = new String[] { "子栏目 a", "子栏目 b", "子栏目 c", "子栏目 d", "子栏目 e", "子栏目 f" };
		previousListView.setAdapter(new SlidingMenuListAdapter(getActivity(), android.R.layout.simple_list_item_1,
				previousListViewData));
		nextListView.setAdapter(new SlidingMenuListAdapter(getActivity(), android.R.layout.simple_list_item_1,
				nextListViewData));

		previousListView.setOnItemClickListener(previousOnItemClickListener);
		nextListView.setOnItemClickListener(nextOnItemClickListener);
		topTextView.setOnClickListener(mOnClickListener);
		bottomTextView.setOnClickListener(mOnClickListener);
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
