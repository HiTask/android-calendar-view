package com.tivogi.widget.tinted;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class TintImageView extends ImageView implements TintView {

	private int mTintColor = TintViewHelper.DEFAULT_TINT_COLOR;

	public TintImageView(Context context) {
		super(context);
	}

	public TintImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TintViewHelper.processAttributeSet(this, attrs);
	}

	public TintImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TintViewHelper.processAttributeSet(this, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			setColorFilter(mTintColor, PorterDuff.Mode.SRC_ATOP);
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			clearColorFilter();
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	public void setTintColor(int tintColor) {
		mTintColor = tintColor;
	}

}
