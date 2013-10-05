package com.tivogi.helper;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Checkable;
import android.widget.TextView;

public class UiHelper {

	public static float convertDpToPx(float dpValue) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, Resources.getSystem().getDisplayMetrics());
	}

	public static float convertSpToPx(float spValue) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, Resources.getSystem().getDisplayMetrics());
	}

	public static String getTextViewText(Activity activity, int resId) {
		TextView textView = (TextView) activity.findViewById(resId);
		return textView.getText().toString();
	}

	public static String getTextViewText(View view, int resId) {
		TextView textView = (TextView) view.findViewById(resId);
		return textView.getText().toString();
	}

	public static void hideSoftKeyboard(Activity acitivity) {
		InputMethodManager imm = (InputMethodManager) acitivity.getSystemService(Context.INPUT_METHOD_SERVICE);
		View view = acitivity.getCurrentFocus();
		if (view != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static boolean isViewChecked(Activity activity, int resId) {
		Checkable checkable = (Checkable) activity.findViewById(resId);
		return checkable.isChecked();
	}

	public static void setOnClickListener(View view, int[] ids, OnClickListener listener) {
		for (int id : ids) {
			view.findViewById(id).setOnClickListener(listener);
		}
	}

	public static void setViewChecked(Activity activity, int resId, boolean checked) {
		Checkable checkable = (Checkable) activity.findViewById(resId);
		checkable.setChecked(checked);
	}

	public static void setViewsVisibility(Activity activity, int visibility, int[] ids) {
		for (int id : ids) {
			activity.findViewById(id).setVisibility(visibility);
		}
	}

	public static void setViewsVisibility(View view, int visibility, int[] ids) {
		for (int id : ids) {
			view.findViewById(id).setVisibility(visibility);
		}
	}

	public static TextView updateTextView(Activity activity, int resId, CharSequence text) {
		TextView textView = (TextView) activity.findViewById(resId);
		textView.setText(text);
		return textView;
	}

	public static TextView updateTextView(View view, int resId, CharSequence text) {
		TextView textView = (TextView) view.findViewById(resId);
		textView.setText(text);
		return textView;
	}

	public static TextView updateTextView(View view, int resId, int stringResId) {
		return updateTextView(view, resId, view.getContext().getString(stringResId));
	}

	public static TextView updateTextViewFromHtml(Activity activity, int resId, int stringResId) {
		return updateTextViewFromHtml(activity, resId, activity.getString(stringResId));
	}

	public static TextView updateTextViewFromHtml(Activity activity, int resId, String html) {
		return updateTextView(activity, resId, html == null ? "" : Html.fromHtml(html));
	}

	public static TextView updateTextViewFromHtml(View view, int resId, int stringResId) {
		return updateTextViewFromHtml(view, resId, view.getContext().getString(stringResId));
	}

	public static TextView updateTextViewFromHtml(View view, int resId, String html) {
		return updateTextView(view, resId, html == null ? "" : Html.fromHtml(html));
	}
}
