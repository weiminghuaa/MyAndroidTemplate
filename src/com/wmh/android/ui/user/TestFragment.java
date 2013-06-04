package com.wmh.android.ui.user;

import java.util.ArrayList;
import java.util.List;

import com.wmh.android.R;
import com.wmh.android.adapter.CustomerPagerAdapter;
import com.wmh.android.ui.MainActivity;
import com.wmh.android.widget.slidingmenu.SlidingMenu;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * test界面
 * 
 * @author wmh
 * 
 */
public class TestFragment extends Fragment {

	ViewPager pager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.test, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		pager = (ViewPager) view.findViewById(R.id.viewPager);
		List<View> imageViews = new ArrayList<View>();
		for (int i = 0; i < 3; i++) {
			ImageView tempImageView = new ImageView(getActivity());
			// tempImageView.setBackgroundResource(R.drawable.dot_white);
			imageViews.add(tempImageView);
		}
		pager.setAdapter(new CustomerPagerAdapter(imageViews));
		((MainActivity)getActivity()).getSlidingMenu().setTouchModeAbove(
				SlidingMenu.TOUCHMODE_ENABLE_LEFT_SLIDING_IN_VIEWPAGER);
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					((MainActivity) getActivity()).getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_ENABLE_LEFT_SLIDING_IN_VIEWPAGER);
					break;

				case 2:// 2是最后一页
					((MainActivity) getActivity()).getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_ENABLE_RIGHT_SLIDING_IN_VIEWPAGER);
					break;

				default:
					((MainActivity) getActivity()).getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_NONE_SLIDING_IN_VIEWPAGER);
					break;
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
	}
	
}
