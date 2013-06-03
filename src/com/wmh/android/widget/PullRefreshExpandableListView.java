package com.wmh.android.widget;

import java.util.Date;

import com.wmh.android.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

/**
 * 下拉刷新ExpandableListView
 * 
 * @author weimh
 * @time 2012-9-4
 */
public class PullRefreshExpandableListView extends ExpandableListView implements OnScrollListener {

	// private static final String TAG = "listview";

	private final static int RELEASE_To_REFRESH = 0; // 下拉完成
	private final static int PULL_To_REFRESH = 1; // 下拉中
	private final static int REFRESHING = 2; // 正在刷新列表
	private final static int DONE = 3; // 初始状态
	private final static int LOADING = 4; //

	private final static int RATIO = 3;

	private LayoutInflater inflater;

	private LinearLayout headView;
	private LinearLayout footerView;

	private TextView tipsTextview;
	private TextView lastUpdatedTextView;
	private ImageView arrowImageView;
	private ProgressBar progressBar;
	private ProgressBar footer_progressBar;
	private TextView load_more_data;

	private RotateAnimation animation;
	private RotateAnimation reverseAnimation;

	private boolean isRecored;

	@SuppressWarnings("unused")
	private int headContentWidth; // 头部内容实际宽度
	private int headContentHeight; // 头部内容实际高度
	@SuppressWarnings("unused")
	private int footerContentWidth;
	private int footerContentHeight;
	private int startY;
	private int firstItemIndex;
	@SuppressWarnings("unused")
	private int totalItemCount;
	private int footerLastItem;
	private int state;
	private boolean isBack;
	private boolean isLoading;
	private boolean hasMore = true; // 是否有更多记录
	private int pageIndex; // 当前页码

	private MyListViewListener myListViewListener;

	public PullRefreshExpandableListView(Context context) {
		super(context);
		init(context);
	}

	public PullRefreshExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		inflater = LayoutInflater.from(context);

		// 列表头部
		headView = (LinearLayout) inflater.inflate(R.layout.pull_refresh_listview_head, null);

		arrowImageView = (ImageView) headView.findViewById(R.id.head_arrowImageView);
		// arrowImageView.setMinimumWidth(70);
		// arrowImageView.setMinimumHeight(50);
		progressBar = (ProgressBar) headView.findViewById(R.id.head_progressBar);
		tipsTextview = (TextView) headView.findViewById(R.id.head_tipsTextView);
		lastUpdatedTextView = (TextView) headView.findViewById(R.id.head_lastUpdatedTextView);

		measureView(headView);
		headContentHeight = headView.getMeasuredHeight();
		headContentWidth = headView.getMeasuredWidth();

		// 初始显示时，隐藏头部
		headView.setPadding(0, -1 * headContentHeight, 0, 0);
		headView.invalidate();

		// 列表尾部
		footerView = (LinearLayout) inflater.inflate(R.layout.pull_refresh_listview_footer, null);

		footer_progressBar = (ProgressBar) footerView.findViewById(R.id.load_data_progress);
		load_more_data = (TextView) footerView.findViewById(R.id.load_more_data);
		load_more_data.setText("加载更多");
		/*
		 * load_more_data.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { if (!isLoading) { if
		 * (totalItemCount!=0) { onLoadMoreData((totalItemCount /
		 * (AppConstant.PAGE_SIZE * 2)) + 1); } } } });
		 */

		measureView(footerView);
		footerContentHeight = footerView.getMeasuredHeight();
		footerContentWidth = footerView.getMeasuredWidth();

		// 初始显示时，隐藏尾部
		footerView.setPadding(0, -1 * footerContentHeight, 0, 0);
		footerView.invalidate();

		addHeaderView(headView, null, false);
		addFooterView(footerView, null, false);
		setOnScrollListener(this);

		// 设置动画
		animation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(250);
		animation.setFillAfter(true);

		reverseAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		reverseAnimation.setInterpolator(new LinearInterpolator());
		reverseAnimation.setDuration(200);
		reverseAnimation.setFillAfter(true);

		state = DONE;
		isLoading = false;
	}

	public void onScroll(AbsListView arg0, int firstVisiableItem, int visibleItemCount, int totalItemCount) {
		this.firstItemIndex = firstVisiableItem;
		this.totalItemCount = totalItemCount;
		this.footerLastItem = firstVisiableItem + visibleItemCount;
	}

	public void onScrollStateChanged(AbsListView arg0, int scrollState) {
	}

	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		// 按下
		case MotionEvent.ACTION_DOWN:
			// 按下时， 记录y坐标与是否为记录标记
			if (!isRecored) {
				isRecored = true;
				startY = (int) event.getY();
			}
			break;

		// 移动
		case MotionEvent.ACTION_MOVE:
			int tempY = (int) event.getY();

			if (!isRecored && firstItemIndex == 0) {
				isRecored = true;
				startY = tempY;
			}

			// 往下滚动
			// 向上推
			if (tempY - startY < 0) {
				if (footerLastItem == this.getAdapter().getCount() && hasMore) {
					if (!isLoading) {
						isLoading = true;
						onLoadMoreData(pageIndex + 1);
					}
				}
			}

			// 往上滚动
			// 如果当前记录在第一条，并且是向下拉
			else if (firstItemIndex == 0 && tempY - startY > 0) {
				if (state != REFRESHING && isRecored && state != LOADING) {

					if (state == RELEASE_To_REFRESH) {

						setSelection(0);

						if (((tempY - startY) / RATIO < headContentHeight) && (tempY - startY) > 0) {
							state = PULL_To_REFRESH;
							changeHeaderViewByState();

						} else if (tempY - startY <= 0) {
							state = DONE;
							changeHeaderViewByState();

						} else {
						}
					}
					if (state == PULL_To_REFRESH) {

						setSelection(0);

						if ((tempY - startY) / RATIO >= headContentHeight) {
							state = RELEASE_To_REFRESH;
							isBack = true;
							changeHeaderViewByState();

						} else if (tempY - startY <= 0) {
							state = DONE;
							changeHeaderViewByState();

						}
					}

					if (state == DONE) {
						if (tempY - startY > 0) {
							state = PULL_To_REFRESH;
							changeHeaderViewByState();
						}
					}

					if (state == PULL_To_REFRESH) {
						headView.setPadding(0, -1 * headContentHeight + (tempY - startY) / RATIO, 0, 0);

					}

					if (state == RELEASE_To_REFRESH) {
						headView.setPadding(0, (tempY - startY) / RATIO - headContentHeight, 0, 0);
					}

				}
			}
			break;

		// 松开
		case MotionEvent.ACTION_UP:

			if (state != REFRESHING && state != LOADING) {
				if (state == DONE) {
				}
				if (state == PULL_To_REFRESH) {
					state = DONE;
					changeHeaderViewByState();

				}
				if (state == RELEASE_To_REFRESH) {
					state = REFRESHING;
					changeHeaderViewByState();
					onRefresh();
				}
			}

			isRecored = false;
			isBack = false;

			break;
		}

		return super.onTouchEvent(event);
	}

	/**
	 * 设置当前页码
	 * 
	 * @param pageIndex
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 设置是否由更多记录
	 * 
	 * @param hasMore
	 */
	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}

	// 更改下拉刷新的状态
	private void changeHeaderViewByState() {
		switch (state) {
		//
		case RELEASE_To_REFRESH:
			arrowImageView.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			tipsTextview.setVisibility(View.VISIBLE);
			lastUpdatedTextView.setVisibility(View.VISIBLE);

			arrowImageView.clearAnimation();
			arrowImageView.startAnimation(animation);

			tipsTextview.setText("松开刷新");

			break;

		// 下拉中状态 （显示头部信息，显示箭头，更改显示信息，隐藏进度条）
		case PULL_To_REFRESH:
			progressBar.setVisibility(View.GONE);
			tipsTextview.setVisibility(View.VISIBLE);
			lastUpdatedTextView.setVisibility(View.VISIBLE);
			arrowImageView.clearAnimation();
			arrowImageView.setVisibility(View.VISIBLE);
			if (isBack) {
				isBack = false;
				arrowImageView.clearAnimation();
				arrowImageView.startAnimation(reverseAnimation);

			}
			tipsTextview.setText("下拉刷新");
			break;

		// 正在刷新列表状态(显示头部，更改显示信息,显示进度条)
		case REFRESHING:

			// 显示头部
			headView.setPadding(0, 0, 0, 0);

			progressBar.setVisibility(View.VISIBLE);
			arrowImageView.clearAnimation();
			arrowImageView.setVisibility(View.GONE);
			tipsTextview.setText("正在刷新...");
			lastUpdatedTextView.setVisibility(View.VISIBLE);

			break;

		// 未刷新状态（隐藏头部，设置头部显示信息）
		case DONE:

			headView.setPadding(0, -1 * headContentHeight, 0, 0);

			progressBar.setVisibility(View.GONE);
			arrowImageView.clearAnimation();
			arrowImageView.setImageResource(R.drawable.goicon);
			tipsTextview.setText("下拉刷新");
			lastUpdatedTextView.setVisibility(View.VISIBLE);

			break;
		}
	}

	public void setMyListViewListener(MyListViewListener myListViewListener) {
		this.myListViewListener = myListViewListener;
	}

	public interface MyListViewListener {

		/**
		 * 加载最新
		 */
		public void onRefresh();

		/**
		 * 加载更多
		 */
		public void onLoadMoreData(int pageIndex);

	}

	public void onRefreshComplete() {
		state = DONE;
		lastUpdatedTextView.setText("最后更新时间:" + new Date().toLocaleString());
		changeHeaderViewByState();
		invalidateViews();
		setSelection(0);
	}

	public void onLoadDataComplete() {
		load_more_data.setText("加载更多");
		footer_progressBar.setVisibility(View.GONE);
		footerView.setPadding(0, -1 * footerContentHeight, 0, 0);
		isRecored = false;
		isLoading = false;
		invalidateViews();
	}

	private void onRefresh() {
		if (myListViewListener != null) {
			myListViewListener.onRefresh();
		}
	}

	private void onLoadMoreData(int pageIndex) {
		footerView.setPadding(10, 10, 10, 10);
		load_more_data.setText("正在加载...");
		footer_progressBar.setVisibility(View.VISIBLE);
		if (myListViewListener != null) {
			myListViewListener.onLoadMoreData(pageIndex);
		}
	}

	/**
	 * 测量空间实际宽高度
	 * 
	 * @param child
	 */
	private void measureView(View child) {
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}

	public void setAdapter(BaseAdapter adapter) {
		lastUpdatedTextView.setText("最后刷新时间:" + new Date().toLocaleString());
		super.setAdapter(adapter);
	}

	public void showRefreshing() {
		state = REFRESHING;
		changeHeaderViewByState();
		// footerView.setVisibility(View.GONE);
	}

	public LinearLayout getHeadView() {
		return headView;
	}

	public void setHeadView(LinearLayout headView) {
		this.headView = headView;
	}

}