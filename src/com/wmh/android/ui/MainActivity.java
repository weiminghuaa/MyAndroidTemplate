package com.wmh.android.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.wmh.android.R;
import com.wmh.android.ui.user.IndexFragment;
import com.wmh.android.widget.slidingmenu.SlidingMenu;
import com.wmh.android.widget.slidingmenu.app.SlidingFragmentActivity;

/**
 * 主界面
 * 
 * @author wmh
 *
 */
public class MainActivity extends SlidingFragmentActivity {

	// private final static String TAG = "MainActivity";
	private SlidingMenu sm;
	private Handler handler;
	private boolean isExit = false;
	private String lastFragmentClassName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("好股轻基金");
		
		// 加载 content View
		setContentView(R.layout.sliding_content_frame);

		// 设置SlidingMenu属性
		sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setMode(SlidingMenu.LEFT_RIGHT);

		

		lastFragmentClassName = IndexFragment.class.getName();
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new IndexFragment(),
				lastFragmentClassName).commit();

		// 加载 left sliding menu
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new LeftSlidingMenuFragment())
		.commit();

		// 加载 right sliding menu
		getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame_two, new RightSlidingMenuFragment())
		.commit();

		// 为实现双击返回键退出
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				isExit = false;
			}
		};
	}

	@Override
	public void onBackPressed() {
		if (getSlidingMenu().isMenuShowing()) {
			showContent();// 显示content view
		} else {
			if (!isExit) {
				isExit = true;
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				handler.sendEmptyMessageDelayed(0, 2000);
			} else {
				finish();
				System.exit(0);
			}
		}
	}

	/**
	 * 切换fragment
	 * 
	 * @param tag
	 */
	public void switchContent(String tag) {
		if (lastFragmentClassName.equals(tag)) {
			return;
		}
		Fragment f = getSupportFragmentManager().findFragmentByTag(tag);
		Fragment lastFragment = getSupportFragmentManager().findFragmentByTag(lastFragmentClassName);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		if (f == null) {
			try {
				f=(Fragment) Class.forName(tag).newInstance();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ft.add(R.id.content_frame, f, tag);
		} else {
			ft.show(f);
		}
		ft.hide(lastFragment);
		ft.commit();
		lastFragmentClassName = tag;

		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				sm.showContent();
			}
		}, 50);
	}
}
