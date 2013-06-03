package com.wmh.android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 圆形图片组件
 * 
 * @author wmh
 *
 */
public class RoundImageView extends ImageView {

	public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public RoundImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		BitmapDrawable drawable = (BitmapDrawable) getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return; 
        }

        Bitmap fullSizeBitmap = drawable.getBitmap();
        fullSizeBitmap = getBitmapCenter(fullSizeBitmap);
        int scaledWidth = getMeasuredWidth();
        int scaledHeight = getMeasuredHeight();

        Bitmap mScaledBitmap;
        if (scaledWidth == fullSizeBitmap.getWidth() && scaledHeight == fullSizeBitmap.getHeight()) {
            mScaledBitmap = fullSizeBitmap;
        } else {
            mScaledBitmap = Bitmap.createScaledBitmap(fullSizeBitmap, scaledWidth, scaledHeight, true /* filter */);
        }
        Bitmap roundBitmap = getCircleBitmap(mScaledBitmap);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
	}
	
	

	private Bitmap getBitmapCenter(Bitmap bitmap){
		Bitmap output=null;
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		if(w>h){
			output = Bitmap.createBitmap(bitmap, (w-h)/2, 0, h, h);
		}else if(w<h){
			output = Bitmap.createBitmap(bitmap, 0, (h-w)/2, w, w);
		}else{
			output = bitmap;
		}
		
		return output;
	}
	private  Bitmap getCircleBitmap(Bitmap bitmap) {
		int x = bitmap.getWidth();
		Bitmap output = Bitmap.createBitmap(x,
		x, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		paint.setAntiAlias(true);
		paint.setColor(color);
	
		canvas.drawCircle(x/2, x/2, x/2, paint);
		
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
	
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
}
