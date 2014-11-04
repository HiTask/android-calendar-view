package com.tivogi.widget.tinted;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TintLinearLayout extends LinearLayout implements TintView {
	private int mTintColor = TintViewHelper.DEFAULT_TINT_COLOR;
	private boolean mPressed;

	public TintLinearLayout(Context context) {
		super(context);
	}

	public TintLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		TintViewHelper.processAttributeSet(this, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public TintLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TintViewHelper.processAttributeSet(this, attrs);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (mPressed) canvas.drawColor(mTintColor, PorterDuff.Mode.SRC_ATOP);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mPressed = true;
			invalidate();
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mPressed = false;
			invalidate();
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
