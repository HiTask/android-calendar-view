package com.tivogi.widget.calendar;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

	private final class GestureListener extends SimpleOnGestureListener {

		private static final int SWIPE_THRESHOLD = 100;
		private static final int SWIPE_VELOCITY_THRESHOLD = 100;

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			float diffY = e2.getY() - e1.getY();
			float diffX = e2.getX() - e1.getX();
			if (Math.abs(diffX) > Math.abs(diffY)) {
				if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
					if (diffX > 0) {
						return onSwipeRight();
					} else {
						return onSwipeLeft();
					}
				}
			} else {
				if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
					if (diffY > 0) {
						return onSwipeBottom();
					} else {
						return onSwipeTop();
					}
				}
			}
			return false;
		}
	}

	private GestureDetector mGestureDetector;

	public OnSwipeTouchListener(Context context) {
		mGestureDetector = new GestureDetector(context, new GestureListener());
	}

	public boolean onSwipeBottom() {
		return false;
	}

	public boolean onSwipeLeft() {
		return false;
	}

	public boolean onSwipeRight() {
		return false;
	}

	public boolean onSwipeTop() {
		return false;
	}

	public boolean onTouch(final View view, final MotionEvent motionEvent) {
		return mGestureDetector.onTouchEvent(motionEvent);
	}
}