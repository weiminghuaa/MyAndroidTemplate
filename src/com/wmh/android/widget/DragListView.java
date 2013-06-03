package com.wmh.android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.wmh.android.adapter.DragListViewAdapter;

/**
 * 可移动的lsitview
 * 
 * @author wmh
 * 
 */
public class DragListView extends ListView {

	private ImageView dragImageView;
	private WindowManager windowManager = null;
	private WindowManager.LayoutParams windowParams = null;
	private int mUpperBound;
	private int mLowerBound;
	private int mHeight;
	private Bitmap mDragBitmap;
	private final int mTouchSlop;
	private Rect draggerRect = new Rect();
	private int mDragPoint; // at what offset inside the item did the user grab
	// it
	private int mCoordOffset; // the difference between screen coordinates and
	// coordinates in this view
	private int startPosition;
	private int dragPosition;
	private int holdposition;
	private OnItemDragListener mItemDragListener;
	private boolean isMoving = false;
	private int draggerViewId;//触摸移动的view id

	public DragListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DragListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}
	
	public void setDraggerViewId(int draggerViewId) {
		this.draggerViewId = draggerViewId;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			int x = (int) ev.getX();
			int y = (int) ev.getY();
			int itemnum = myPointToPosition(x, y);
			if (itemnum == AdapterView.INVALID_POSITION) {
				break;
			}
			ViewGroup item = (ViewGroup) getChildAt(itemnum - getFirstVisiblePosition());
			mDragPoint = y - item.getTop();
			mCoordOffset = ((int) ev.getRawY()) - y;
			View dragger = item.findViewById(draggerViewId);
			if (dragger.getVisibility() == View.GONE) {
				return super.onInterceptTouchEvent(ev);
			}
			draggerRect.left = dragger.getLeft();
			draggerRect.right = dragger.getRight();
			draggerRect.top = dragger.getTop();
			draggerRect.bottom = dragger.getBottom();

			if ((draggerRect.left < x) && (x < draggerRect.right)) {
				startPosition = dragPosition = holdposition = itemnum;
				item.setDrawingCacheEnabled(true);
				Bitmap bitmap = Bitmap.createBitmap(item.getDrawingCache());
				startDragging(bitmap, y);
				mHeight = getHeight();
				int touchSlop = mTouchSlop;
				mUpperBound = Math.min(y - touchSlop, mHeight / 3);
				mLowerBound = Math.max(y + touchSlop, mHeight * 2 / 3);
				hideDragItem();
				item.setVisibility(View.INVISIBLE);
				isMoving = false;
				return false;
			}
			dragImageView = null;
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (dragImageView != null) {
			int action = ev.getAction();
			switch (action) {
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				stopDragging();
				mItemDragListener.onItemDrag(startPosition, dragPosition);
				final DragListViewAdapter adapter = (DragListViewAdapter) this.getAdapter();
				adapter.showDropItem(true);
				adapter.notifyDataSetChanged();
				break;

			case MotionEvent.ACTION_MOVE:
				int x = (int) ev.getX();
				int y = (int) ev.getY();
				onDrag(x, y);// 不断的移动dragImageView
				int itemnum = myPointToPosition(x, y);
				if (itemnum != dragPosition && itemnum != AdapterView.INVALID_POSITION) {
					if (!isMoving) {
						doExpansion(itemnum);
						dragPosition = itemnum;
					}
				}
				/**
				 * 下面代码是为了滑动listview,通过setSelectionFromTop
				 */
				int speed = 0;
				adjustScrollBounds(y);
				if (y > mLowerBound) {
					speed = y > (mHeight + mLowerBound) / 2 ? 16 : 4;
				} else if (y < mUpperBound) {
					speed = y < mUpperBound / 2 ? -16 : -4;
				}
				if (speed != 0) {
					int ref = pointToPosition(0, mHeight / 2);
					if (ref == AdapterView.INVALID_POSITION) {
						ref = pointToPosition(0, mHeight / 2 + getDividerHeight() + 64);
					}
					View v = getChildAt(ref - getFirstVisiblePosition());
					if (v != null) {
						int pos = v.getTop();
						setSelectionFromTop(ref, pos - speed);
					}
				}
				break;
			}
			return true;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 更新dragview的位置
	 * 
	 * @param x
	 * @param y
	 */
	private void onDrag(int x, int y) {
		if (dragImageView != null) {
			windowParams.y = y - mDragPoint + mCoordOffset;
			windowManager.updateViewLayout(dragImageView, windowParams);
		}
	}

	/**
	 * 生成dragImageView
	 * 
	 * @param bm
	 * @param y
	 */
	private void startDragging(Bitmap bm, int y) {
		stopDragging();
		windowParams = new WindowManager.LayoutParams();
		windowParams.gravity = Gravity.TOP;
		windowParams.x = 0;
		windowParams.y = y - mDragPoint + mCoordOffset;

		windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		windowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
		windowParams.format = PixelFormat.TRANSLUCENT;
		windowParams.windowAnimations = 0;
		windowParams.alpha = 0.8f;

		ImageView v = new ImageView(getContext());
		v.setBackgroundColor(Color.rgb(77, 89, 32));
		v.setImageBitmap(bm);
		mDragBitmap = bm;
		windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		windowManager.addView(v, windowParams);
		dragImageView = v;
	}

	/**
	 * 销毁dragImageView，以及相关的bitmap
	 */
	private void stopDragging() {
		if (dragImageView != null) {
			WindowManager wm = (WindowManager) getContext().getSystemService("window");
			wm.removeView(dragImageView);
			dragImageView.setImageDrawable(null);
			dragImageView = null;
		}
		if (mDragBitmap != null) {
			mDragBitmap.recycle();
			mDragBitmap = null;
		}
	}

	/**
	 * 移动其他item
	 * 
	 * @param x
	 * @param y
	 */
	public void doExpansion(final int dropPosition) {
		System.out.println("dragPosition:" + dragPosition + " dropPosition:" + dropPosition);
		int itemMoveNum = Math.abs(dropPosition - dragPosition);
		for (int i = 0; i < itemMoveNum; i++) {
			float Xoffset = 0, Yoffset = 0;
			if (dropPosition > dragPosition) {// dragView往下移，item往上移
				dragPosition++;
				Xoffset = 0;
				Yoffset = -1;
			} else if (dropPosition < dragPosition) {
				dragPosition--;
				Xoffset = 0;
				Yoffset = 1;
			}
			// System.out.println("child count:"+getChildCount());
			View moveView = getChildAt(dragPosition - getFirstVisiblePosition());
			// System.out.println(moveView==null?"moveView is null":"moveView is not null");
			Animation animation = getMoveAnimation(Xoffset, Yoffset);
			moveView.startAnimation(animation);
			final DragListViewAdapter adapter = (DragListViewAdapter) this.getAdapter();
			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					isMoving = true;
				}

				@Override
				public void onAnimationRepeat(Animation animation) {
				}

				@Override
				public void onAnimationEnd(Animation animation) {
					adapter.exchange(holdposition, dragPosition);
					holdposition = dragPosition;
					isMoving = false;
				}
			});
		}
	}

	public Animation getMoveAnimation(float x, float y) {
		TranslateAnimation go = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, x,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, y);
		go.setFillAfter(true);
		go.setDuration(200);
		return go;
	}

	private void hideDragItem() {
		final DragListViewAdapter adapter = (DragListViewAdapter) this.getAdapter();
		adapter.showDropItem(false);
	}

	/*
	 * pointToPosition() doesn't consider invisible views, but we need to, so
	 * implement a slightly different version.
	 */
	private int myPointToPosition(int x, int y) {
		Rect frame = new Rect();
		final int count = getChildCount();
		for (int i = count - 1; i >= 0; i--) {
			final View child = getChildAt(i);
			if (i == 0) {
				System.out.println("y: " + y + "  top:" + child.getTop());
				if (y < child.getTop()) {
					return getFirstVisiblePosition();
				}
			}
			if (i == count - 1) {
				System.out.println("y: " + y + "  bottom:" + child.getBottom());
				if (y > child.getBottom()) {
					return count - 1 - getFirstVisiblePosition();
				}
			}
			child.getHitRect(frame);
			if (frame.contains(x, y)) {
				return getFirstVisiblePosition() + i;
			}
		}
		return INVALID_POSITION;
	}
	
	

	private void adjustScrollBounds(int y) {
		if (y >= mHeight / 3) {
			mUpperBound = mHeight / 3;
		}
		if (y <= mHeight * 2 / 3) {
			mLowerBound = mHeight * 2 / 3;
		}
	}

	public void setOnItemDragListener(OnItemDragListener listener) {
		mItemDragListener = listener;
	}

	public interface OnItemDragListener {
		void onItemDrag(int startPosition, int endPosition);
	}
}
