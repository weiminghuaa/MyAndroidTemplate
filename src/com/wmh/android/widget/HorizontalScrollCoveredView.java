package com.wmh.android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class HorizontalScrollCoveredView extends HorizontalScrollView {
	private HorizontalScrollCoverView coverView;

	public HorizontalScrollCoveredView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public void draw(Canvas paramCanvas) {
		if (this.coverView != null) {
			int i = getScrollX();
			int j = getScrollY();
			this.coverView.scrollTo(i, j);
		}
		super.draw(paramCanvas);
	}

	public void invalidate() {
		super.invalidate();
		if (this.coverView == null)
			return;
		this.coverView.invalidate();
	}

	public void setConver(HorizontalScrollCoverView paramHorizontalScrollCoverView) {
		this.coverView = paramHorizontalScrollCoverView;
	}
	
	
}