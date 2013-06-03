package com.wmh.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class HorizontalScrollCoverView extends HorizontalScrollView {
	
	private ScrollStateListener scrollStateListener;
	
	private boolean f = true;
	
	public HorizontalScrollCoverView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public boolean onTouchEvent(MotionEvent paramMotionEvent) {
		return false;
	}
	
	protected void onLayout(boolean changed, int l,int t,int r,int b) {
		super.onLayout(changed, l, t, r, b);
		refresh();
	}

	private void refresh() {
		if (scrollStateListener != null && f) {
			scrollStateListener.onScrollMostLeft();
			f = false;
		}
	}
	
	
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onSizeChanged(l, t, oldl, oldt);
		
		if (scrollStateListener != null) {
			if  (l == 0) {
				scrollStateListener.onScrollMostLeft();
			}
			
			if (l > oldl) {
				scrollStateListener.onScrollFromMostLeft();
			}
			
			int mostRightL = this.getChildAt(0).getWidth() - getWidth();
			if (l >= mostRightL) {
				scrollStateListener.onScrollMostRight();
			}
			
			if (oldl >= mostRightL && l < mostRightL) {
				scrollStateListener.onScrollFromMostRight();
			}
			
		}
	}


	///////////////////////////////////////////////listener///////////////////////////////////////////////////////
	public interface ScrollStateListener {
		public void onScrollMostLeft();
		public void onScrollFromMostLeft();
		public void onScrollMostRight();
		public void onScrollFromMostRight();
	}



	public ScrollStateListener getScrollStateListener() {
		return scrollStateListener;
	}

	public void setScrollStateListener(ScrollStateListener scrollStateListener) {
		this.scrollStateListener = scrollStateListener;
	}
	///end~
	
}