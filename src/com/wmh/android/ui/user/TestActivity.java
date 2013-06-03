package com.wmh.android.ui.user;

import java.util.ArrayList;
import java.util.List;

import com.wmh.android.R;
import com.wmh.android.adapter.CustomerPagerAdapter;
import com.wmh.android.widget.slidingmenu.SlidingMenu;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


/**
 * test界面
 * 
 * @author wmh
 * 
 */
public class TestActivity extends Activity {

	ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		pager = (ViewPager) findViewById(R.id.viewPager);
		List<View> imageViews= new ArrayList<View>();
		for (int i = 0; i < 3; i++) {
			ImageView tempImageView = new ImageView(this);
//			tempImageView.setBackgroundResource(R.drawable.dot_white);
			imageViews.add(tempImageView);
		}
		pager.setAdapter(new CustomerPagerAdapter(imageViews));
		((MainActivity)getParent()).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_ENABLE_LEFT_SLIDING_IN_VIEWPAGER);
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					((MainActivity)getParent()).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_ENABLE_LEFT_SLIDING_IN_VIEWPAGER);
					break;

				case 2://2是最后一页
					((MainActivity)getParent()).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_ENABLE_RIGHT_SLIDING_IN_VIEWPAGER);
					break;
					
				default:
					((MainActivity)getParent()).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE_SLIDING_IN_VIEWPAGER);
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

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		int[] location = new int[2];
		pager.getLocationInWindow(location);
		int pagerX = location[0];
		int pagerY = location[1];
		int pagerWidth = pager.getWidth();
		int pagerHeight = pager.getHeight();
		Rect rect=new Rect(pagerX, pagerY, pagerX+pagerWidth, pagerY+pagerHeight);
		((MainActivity)getParent()).getSlidingMenu().setViewPagerRectAbove(rect);
	}
}
