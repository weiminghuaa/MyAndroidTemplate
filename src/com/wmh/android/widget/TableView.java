package com.wmh.android.widget;

import com.wmh.android.R;
import com.wmh.android.adapter.TableViewAdapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;


/**
 * 自定义控件TableView
 * 
 * @author wmh
 *
 */
public class TableView extends LinearLayout {

	private int rowHeight, firstRowHeight, leftPartWidth, rightPartActualWidth, leftListViewItemLayout,
			rightListViewItemLayout, leftHeaderLayout, rightHeaderLayout,footerLayout;
	private HorizontalScrollCoverView coverView;
	private HorizontalScrollCoveredView coveredView;
	private LinearLayout leftHeader, rightHeader,footer;
	private ListView leftPartListView, rightPartListView;
	private LayoutInflater layoutInflater;
	private OnItemClickListener onItemClickListener;

	public TableView(Context context) {
		super(context);
		findView(context);
	}

	public TableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (attrs != null) {
			TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TableView);
			leftPartWidth = typedArray.getDimensionPixelSize(0, 100);
			rowHeight = typedArray.getDimensionPixelSize(1, 50);
			firstRowHeight = typedArray.getDimensionPixelSize(2, 50);
			typedArray.recycle();
		}
		findView(context);
	}

	/**
	 * 初始化tableview
	 */
	private void findView(Context context) {
		layoutInflater = LayoutInflater.from(context);
		View table = layoutInflater.inflate(R.layout.tableview, this);
		leftHeader = (LinearLayout) table.findViewById(R.id.left_header);
		rightHeader = (LinearLayout) table.findViewById(R.id.right_header);
		footer = (LinearLayout) table.findViewById(R.id.footer);
		leftPartListView = (NoScrollListView) findViewById(R.id.left_part_list);
		rightPartListView = (NoScrollListView) findViewById(R.id.right_part_list);
		coveredView = (HorizontalScrollCoveredView) findViewById(R.id.h1);
		coverView = (HorizontalScrollCoverView) findViewById(R.id.h2);
		coveredView.setConver(coverView);
		MyOnItemClickListener listener=new MyOnItemClickListener();
		leftPartListView.setOnItemClickListener(listener);
		rightPartListView.setOnItemClickListener(listener);
	}

	/**
	 * 设置TableView的适配器
	 * 
	 * @param paramTableAdapter
	 */
	public void setTableViewAdapter(TableViewAdapter paramTableAdapter) {
		if (rowHeight > 0 && firstRowHeight > 0 && leftPartWidth > 0 && rightPartActualWidth > 0
				&& leftListViewItemLayout > 0 && rightListViewItemLayout > 0 && leftHeaderLayout > 0
				&& rightHeaderLayout > 0) {
			initView();
		} else {
			throw new RuntimeException("some attribute lose");
		}
		paramTableAdapter.setLeftListViewItemLayout(leftListViewItemLayout);
		paramTableAdapter.setRightListViewItemLayout(rightListViewItemLayout);
		paramTableAdapter.setLeftListViewItemLayoutWidth(leftPartWidth);
		paramTableAdapter.setLeftListViewItemLayoutHeight(rowHeight);
		paramTableAdapter.setRightListViewItemLayoutWidth(rightPartActualWidth);
		paramTableAdapter.setRightListViewItemLayoutHeight(rowHeight);
		leftPartListView.setAdapter(paramTableAdapter.mLeftAdapter);
		rightPartListView.setAdapter(paramTableAdapter.mRightAdapter);
	}

	/**
	 * 初始化view
	 */
	private void initView() {
		/**
		 * 初始化header
		 */
		// 初始化leftheader
		View leftHeaderView = layoutInflater.inflate(leftHeaderLayout, leftHeader, false);
		ViewGroup.LayoutParams leftHeaderLayoutParams=leftHeaderView.getLayoutParams();
		leftHeaderLayoutParams.width=leftPartWidth;
		leftHeaderLayoutParams.height=firstRowHeight;
		leftHeader.removeAllViews();
		leftHeader.addView(leftHeaderView,0);
		// 初始化rightHeader
		View rightHeaderView = layoutInflater.inflate(rightHeaderLayout, rightHeader, false);
		ViewGroup.LayoutParams rightHeaderLayoutParams=rightHeaderView.getLayoutParams();
		rightHeaderLayoutParams.width=rightPartActualWidth;
		rightHeaderLayoutParams.height=firstRowHeight;
		rightHeader.removeAllViews();
		rightHeader.addView(rightHeaderView);

		/**
		 * 初始化listview
		 */
		// 设置leftPartListView的宽度
		ViewGroup.LayoutParams leftPartListViewLayoutParams = leftPartListView.getLayoutParams();
		leftPartListViewLayoutParams.width = leftPartWidth;
		// 设置rightPartListView的宽度
		ViewGroup.LayoutParams rightPartListViewLayoutParams = rightPartListView.getLayoutParams();
		rightPartListViewLayoutParams.width = rightPartActualWidth;
		
		/**
		 * 初始化footer
		 */
		if (footerLayout!=0) {
			View footerView = layoutInflater.inflate(footerLayout,footer,false);
			footer.removeAllViews();
			footer.addView(footerView);
			footer.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * 设置TableView的LeftListView的item布局
	 */
	public void setLeftListViewItemLayout(int layout) {
		this.leftListViewItemLayout = layout;
	}

	/**
	 * 设置TableView的RightListView的item布局
	 */
	public void setRightListViewItemLayout(int layout) {
		this.rightListViewItemLayout = layout;
	}

	/**
	 * 设置TableView左边部分的宽度
	 */
	public void setLeftPartWidth(int width) {
		this.leftPartWidth = width;
	}

	/**
	 * 设置TableView右边部分的实际上的宽度
	 * 
	 * @param width
	 */
	public void setRightPartActualWidth(int width) {
		this.rightPartActualWidth = width;
	}

	/**
	 * 设置lefthead的布局
	 */
	public void setLeftHeaderLayout(int layout) {
		this.leftHeaderLayout = layout;
	}

	/**
	 * 设置righthead的布局
	 */
	public void setRightHeaderLayout(int layout) {
		this.rightHeaderLayout = layout;
	}
	
	/**
	 * 设置footer
	 */
	public void setFooterlayout(int footerLayout) {
		this.footerLayout=footerLayout;
	}

	/**
	 * 判断TableView下面的listview是否在底部
	 * 
	 * @return
	 */
	public boolean isOnEnd() {
		return false;
	}
	
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}
	
	private class MyOnItemClickListener implements android.widget.AdapterView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (onItemClickListener!=null) {
				onItemClickListener.onItemClick(parent, view, position, id);
			}
		}
		
	}

	/**
	 * tableview中的listView点击监听器
	 * 
	 * @author wmh
	 *
	 */
	public interface OnItemClickListener {
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

}
