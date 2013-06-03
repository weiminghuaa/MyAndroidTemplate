package com.wmh.android.ui.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.wmh.android.R;
import com.wmh.android.widget.slidingmenu.SlidingMenu;
import com.wmh.android.widget.slidingmenu.app.SlidingActivityGroup;
import com.wmh.android.widget.slidingmenu.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity implements OnClickListener {

	// private final static String TAG = "MainActivity";
	private TextView topTextView, bottomTextView;
	private View topChooseLine, bottomChooseLine;
	private ViewFlipper viewFlipper;
	private ListView previousListView, nextListView;
	private SlidingMenu sm;
	private Handler handler;
	private boolean isExit = false;
	private int previousListFocusItemPosition;
	private int nextListFocusItemPosition;
	private int rightListFocusItemPosition;
	private String lastFragmentClassName; 

	private AdapterView.OnItemClickListener previousOnItemClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// 改变item的背景
			if (previousListFocusItemPosition == position) {
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
				launchContent(tag);
		}
	};
	private AdapterView.OnItemClickListener nextOnItemClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// 改变item的背景
			if (nextListFocusItemPosition != position) {
				parent.getChildAt(nextListFocusItemPosition).setBackgroundResource(0);
				view.setBackgroundResource(R.drawable.left_nav_spiltchoose);
				nextListFocusItemPosition = position;
			}
			Intent intent = null;
			switch (position) {
			case 0:
				intent = new Intent(MainActivity.this, IndexActivity.class);
				break;

			case 1:
				break;

			case 2:
				break;

			default:
				break;
			}
			if (intent != null)
				launchContent(null);
		}
	};
	private AdapterView.OnItemClickListener rightOnItemClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// 改变item的背景
			if (rightListFocusItemPosition != position) {
				parent.getChildAt(rightListFocusItemPosition).setBackgroundResource(0);
				view.setBackgroundResource(R.drawable.left_nav_spiltchoose);
				rightListFocusItemPosition = position;
			}
			Intent intent = null;
			switch (position) {
			case 0:
				intent = new Intent(MainActivity.this, IndexActivity.class);
				break;

			case 1:
				break;

			case 2:
				break;

			default:
				break;
			}
			if (intent != null)
				launchContent(null);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("好股轻基金");

		// 设置SlidingMenu属性
		sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setMode(SlidingMenu.LEFT_RIGHT);

		// 加载 content View
		setContentView(R.layout.sliding_menu_main);

		lastFragmentClassName=IndexFragment.class.getName();
		getSupportFragmentManager().beginTransaction().add(R.id.slidingmenumain, new IndexFragment(), lastFragmentClassName)
				.commit();

		// 加载 left sliding menu
		launchLeftSildingMenu();

		// 加载 right sliding menu
		launchRightSildingMenu();

		// 为实现双击返回键退出
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				isExit = false;
			}
		};
	}

	/**
	 * 加载left sliding menu
	 */
	private void launchLeftSildingMenu() {
		View v = LayoutInflater.from(this).inflate(R.layout.left_sliding_menu, null);
		topTextView = (TextView) v.findViewById(R.id.topTextView);
		bottomTextView = (TextView) v.findViewById(R.id.bottomTextView);
		viewFlipper = (ViewFlipper) v.findViewById(R.id.viewFlipper);
		previousListView = (ListView) v.findViewById(R.id.previousListView);
		nextListView = (ListView) v.findViewById(R.id.nextListView);
		topChooseLine = v.findViewById(R.id.topChooseLine);
		bottomChooseLine = v.findViewById(R.id.bottomChooseLine);

		String[] previousListViewData = new String[] { "子栏目 1", "子栏目 2", "子栏目 3", "子栏目 4", "子栏目 5", "子栏目 6" };
		String[] nextListViewData = new String[] { "子栏目 a", "子栏目 b", "子栏目 c", "子栏目 d", "子栏目 e", "子栏目 f" };
		previousListView.setAdapter(new LeftMenuListAdapter(this, android.R.layout.simple_list_item_1,
				previousListViewData));
		nextListView.setAdapter(new LeftMenuListAdapter(this, android.R.layout.simple_list_item_1, nextListViewData));

		previousListView.setOnItemClickListener(previousOnItemClickListener);
		nextListView.setOnItemClickListener(nextOnItemClickListener);
		topTextView.setOnClickListener(this);
		bottomTextView.setOnClickListener(this);

		setBehindContentView(v);
	}

	/**
	 * 加载right sliding menu
	 */
	private void launchRightSildingMenu() {
		View v = LayoutInflater.from(this).inflate(R.layout.right_sliding_menu, null);
		ListView rightSlidingMenuListView = (ListView) v.findViewById(R.id.rightSlidingMenuListView);
		String[] rightListData = new String[] { "子栏目 a", "子栏目 b", "子栏目 c", "子栏目 d", "子栏目 e", "子栏目 f" };
		rightSlidingMenuListView.setAdapter(new LeftMenuListAdapter(this, android.R.layout.simple_list_item_1,
				rightListData));
		rightSlidingMenuListView.setOnItemClickListener(rightOnItemClickListener);

		sm.setSecondaryMenu(v);
		sm.setSecondaryShadowDrawable(R.drawable.shadowright);
	}

	/**
	 * 加载content view
	 * 
	 * @param intent
	 */
	public void launchContent(String tag) {
		if (lastFragmentClassName.equals(tag)) {
			return;
		}
		Fragment f = getSupportFragmentManager().findFragmentByTag(tag);
		Fragment lastFragment = getSupportFragmentManager().findFragmentByTag(lastFragmentClassName);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		if (f == null) {
			try {
				ft.replace(R.id.slidingmenumain, (Fragment) Class.forName(tag).newInstance(), tag);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			ft.show(f);
		}
		ft.hide(lastFragment);
		ft.commit();
		lastFragmentClassName=tag;
		
		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				sm.showContent();
			}
		}, 50);
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

	/**
	 * adapter,为了改变listview item 的背景
	 * 
	 * @author wmh
	 * 
	 */
	private class LeftMenuListAdapter extends ArrayAdapter<String> {

		public LeftMenuListAdapter(Context context, int resource, String[] objects) {
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
}
