package com.tivogi.widget.tinted;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.tivogi.calendar_view.R;

public class TintViewHelper {
	public static final int DEFAULT_TINT_COLOR = Color.argb(100, 0, 0, 0);

	public static <T extends TintView> void processAttributeSet(T view, AttributeSet attrs) {
		Context context = ((View) view).getContext();
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TintView);
		int tintColor = typedArray.getColor(R.styleable.TintView_tintColor, DEFAULT_TINT_COLOR);
		view.setTintColor(tintColor);
		typedArray.recycle();

	}

}
