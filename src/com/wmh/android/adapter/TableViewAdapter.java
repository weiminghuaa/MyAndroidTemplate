package com.wmh.android.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 自定义控件TableView的adapter,抽象类
 * 
 * @author wmh
 * 
 */
public abstract class TableViewAdapter {
	public BaseAdapter mLeftAdapter;
	public BaseAdapter mRightAdapter;
	protected int leftListViewItemLayout, rightListViewItemLayout, leftListViewItemLayoutWidth,
			leftListViewItemLayoutHeight, rightListViewItemLayoutWidth, rightListViewItemLayoutHeight;
	protected List<Map<String, ?>> mData;
	protected Context context;
	protected final LayoutInflater mInflater;

	public TableViewAdapter(Context context, List<Map<String, ?>> data) {
		this.mLeftAdapter = new LeftListAdapter();
		this.mRightAdapter = new RightListAdapter();
		this.mData = data;
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
	}

	class LeftListAdapter extends BaseAdapter {

		public int getCount() {
			return mData.size();
		}

		public Object getItem(int position) {
			return mData.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(leftListViewItemLayout, parent, false);
				ViewGroup.LayoutParams convertViewLayoutParams = convertView.getLayoutParams();
				convertViewLayoutParams.width = leftListViewItemLayoutWidth;
				convertViewLayoutParams.height = leftListViewItemLayoutHeight;
			}
			initLeftConvertView(position, convertView, parent);
			return convertView;
		}

	}

	class RightListAdapter extends BaseAdapter {

		public int getCount() {
			return mData.size();
		}

		public Object getItem(int position) {
			return mData.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(rightListViewItemLayout, parent, false);
				ViewGroup.LayoutParams convertViewLayoutParams = convertView.getLayoutParams();
				convertViewLayoutParams.width = rightListViewItemLayoutWidth;
				convertViewLayoutParams.height = rightListViewItemLayoutHeight;
			}
			initRightConvertView(position, convertView, parent);
			return convertView;
		}
	}

	/**
	 * 子类实现，初始化左边 convertView
	 * 
	 * @param paramInt
	 * @param paramView
	 * @param paramViewGroup
	 * @return
	 */
	public abstract View initLeftConvertView(int paramInt, View paramView, ViewGroup paramViewGroup);

	/**
	 * 子类实现，初始化右边 convertView
	 * 
	 * @param paramInt
	 * @param paramView
	 * @param paramViewGroup
	 * @return
	 */
	public abstract View initRightConvertView(int paramInt, View paramView, ViewGroup paramViewGroup);
	
	/**
	 * 刷新数据
	 */
	public void notifyDataSetChanged() {
		mLeftAdapter.notifyDataSetChanged();
		mRightAdapter.notifyDataSetChanged();
	}

	public void setLeftListViewItemLayout(int leftListViewItemLayout) {
		this.leftListViewItemLayout = leftListViewItemLayout;
	}

	public void setRightListViewItemLayout(int rightListViewItemLayout) {
		this.rightListViewItemLayout = rightListViewItemLayout;
	}

	public void setLeftListViewItemLayoutWidth(int leftListViewItemLayoutWidth) {
		this.leftListViewItemLayoutWidth = leftListViewItemLayoutWidth;
	}

	public void setRightListViewItemLayoutWidth(int rightListViewItemLayoutWidth) {
		this.rightListViewItemLayoutWidth = rightListViewItemLayoutWidth;
	}

	public void setLeftListViewItemLayoutHeight(int leftListViewItemLayoutHeight) {
		this.leftListViewItemLayoutHeight = leftListViewItemLayoutHeight;
	}

	public void setRightListViewItemLayoutHeight(int rightListViewItemLayoutHeight) {
		this.rightListViewItemLayoutHeight = rightListViewItemLayoutHeight;
	}

}
