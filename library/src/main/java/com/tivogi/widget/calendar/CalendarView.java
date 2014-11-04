package com.tivogi.widget.calendar;

import hirondelle.date4j.DateTime;
import hirondelle.date4j.DateTime.DayOverflow;

import java.util.Locale;
import java.util.TimeZone;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.tivogi.calendar_view.R;

public abstract class CalendarView<T extends DaysGridView> extends FrameLayout implements OnDateSetListener {

	private final class OnClickListenerImplementation implements OnClickListener {
		@Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.vc_button_next_month) {
				addMonth(1);
			} else if (id == R.id.vc_button_previous_month) {
				addMonth(-1);
			} else if (id == R.id.vc_button_next_year) {
				addMonth(12);
			} else if (id == R.id.vc_button_previous_year) {
				addMonth(-12);
			} else if (id == R.id.vc_button_pick_date) {
				new DatePickerDialog(getContext(), CalendarView.this, mDateTime.getYear(), mDateTime.getMonth() - 1, mDateTime.getDay()).show();
			} else {
			}
		}
	}

	public interface OnDateClickLisetener {
		public void onDateClick(DateTime date);
	}

	private TextView mTitleView;
	private DaysGridView mCurrentDaysGridView;
	private OnClickListener mOnClickListener;
	private LinearLayout mWeekHeaderLinearLayout;
	private TextView[] mWeekHeaderTextViews;
	private OnDateClickLisetener mOnDateClickListener;
	private ViewSwitcher mSwitcherView;
	private boolean mSelectable = true;
	private OnSwipeTouchListener mOnSwipeTouchListener = new OnSwipeTouchListener(getContext()) {
		@Override
		public boolean onSwipeLeft() {
			addMonth(1);
			return true;
		}

		@Override
		public boolean onSwipeRight() {
			addMonth(-1);
			return true;
		};
	};
	private DateTime mDateTime;
	private DateTime mSelectedDate;

	private View mProgressView;;

	public CalendarView(Context context) {
		super(context);
		initialize(context);
	}

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(context);
	}

	protected void addMonth(int countOfMonth) {
		if (countOfMonth > 0) {
			setDate(mDateTime.plus(0, countOfMonth, 0, 0, 0, 0, 0, DayOverflow.LastDay));
		} else {
			setDate(mDateTime.minus(0, -countOfMonth, 0, 0, 0, 0, 0, DayOverflow.LastDay));
		}
	}

	private void bindViews() {
		mSwitcherView = (ViewSwitcher) findViewById(R.id.vc_switcher);
		mProgressView = findViewById(R.id.vc_progress_wrapper);
		for (int i = 0; i < 2; i++) {
			T daysGridView = createDaysGridView();
			daysGridView.setBackgroundColor(getResources().getColor(R.color.days_grid_background));
			daysGridView.setCalendarView(this);
			mSwitcherView.addView(daysGridView);
		}
		mCurrentDaysGridView = (DaysGridView) mSwitcherView.getCurrentView();
		mTitleView = (TextView) findViewById(R.id.vc_button_pick_date);
		mWeekHeaderLinearLayout = (LinearLayout) findViewById(R.id.vc_week_header);
	}

	protected abstract T createDaysGridView();

	private TextView createWeekHeaderTextView() {
		TextView result = new TextView(getContext());
		result.setTextSize(14f); // TODO configurable via xml
		result.setTextColor(Color.WHITE); // TODO configurable via xml
		result.setGravity(Gravity.CENTER);
		return result;
	}

	protected DaysGridView getCurrentDaysGridView() {
		return mCurrentDaysGridView;
	}

	public DateTime getDate() {
		return mDateTime;
	}

	public OnDateClickLisetener getOnDateClickListener() {
		return mOnDateClickListener;
	}

	public DateTime getSelectedDate() {
		return mSelectedDate;
	}

	private void initialize(Context context) {
		View.inflate(context, R.layout.view_calendar, this);
		bindViews();
		initizlizeWeekHeader();
		initializeButtonsListeners();
		mDateTime = DateTime.today(TimeZone.getDefault());
		setDate(mDateTime, false);
	}

	private void initializeButtonsListeners() {
		mOnClickListener = new OnClickListenerImplementation();
		setButtonOnClickListener(R.id.vc_button_previous_year);
		setButtonOnClickListener(R.id.vc_button_previous_month);
		setButtonOnClickListener(R.id.vc_button_next_month);
		setButtonOnClickListener(R.id.vc_button_next_year);
		setButtonOnClickListener(R.id.vc_button_pick_date);
	}

	private void initizlizeWeekHeader() {
		int columns = DaysGridView.getWeekHeaderColumnCount();
		mWeekHeaderLinearLayout.setWeightSum(columns);
		mWeekHeaderTextViews = new TextView[columns];
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		params.gravity = Gravity.CENTER;
		for (int i = 0; i < mWeekHeaderTextViews.length; i++) {
			mWeekHeaderTextViews[i] = createWeekHeaderTextView();
			mWeekHeaderLinearLayout.addView(mWeekHeaderTextViews[i], params);
		}
	}

	public boolean isSelectable() {
		return mSelectable;
	}

	public void onDateClick(DateTime date) {
		setSelectedDate(date);
		mDateTime = date;
		if (getOnDateClickListener() == null) return;
		getOnDateClickListener().onDateClick(date);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		/* +1 because The month that was set (0-11) for compatibility with Calendar. */
		setDate(DateTime.forDateOnly(year, monthOfYear + 1, dayOfMonth));
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return mOnSwipeTouchListener.onTouch(this, ev);
	}

	private void prepareAnimation(DateTime dateTime) {
		if (dateTime.lt(mDateTime)) {
			mSwitcherView.setInAnimation(getContext(), R.anim.slide_in_left);
			mSwitcherView.setOutAnimation(getContext(), R.anim.slide_out_right);
		} else {
			mSwitcherView.setOutAnimation(getContext(), R.anim.slide_out_left);
			mSwitcherView.setInAnimation(getContext(), R.anim.slide_in_right);
		}
	}

	private void setButtonOnClickListener(int resId) {
		View view = (View) findViewById(resId);
		view.setOnClickListener(mOnClickListener);
	}

	public void setDate(DateTime dateTime) {
		setDate(dateTime, true);
	}

	public void setDate(DateTime dateTime, boolean animation) {
		setSelectedDate(dateTime);
		boolean showAnimation = false;
		if (animation && (mDateTime.getMonth().intValue() != dateTime.getMonth().intValue() || mDateTime.getYear().intValue() != dateTime.getYear().intValue())) {
			prepareAnimation(dateTime);
			mCurrentDaysGridView = (DaysGridView) mSwitcherView.getNextView();
			showAnimation = true;
		}
		String title = dateTime.format("MMMM YYYY", Locale.getDefault()).toUpperCase(Locale.getDefault());
		mTitleView.setText(title);
		mDateTime = dateTime;
		mCurrentDaysGridView.setDate(dateTime);
		if (showAnimation) {
			mSwitcherView.showNext();
		}
		updateWeekHeader();
	}

	public void setOnDateClickListener(OnDateClickLisetener onDateClickListener) {
		mOnDateClickListener = onDateClickListener;
	}

	public void setProgressVisible(boolean visible) {
		if (visible) {
			mProgressView.setVisibility(View.VISIBLE);
		} else {
			mProgressView.setVisibility(View.GONE);
		}
	}

	public void setSelectable(boolean selectable) {
		mSelectable = selectable;
	}

	protected void setSelectedDate(DateTime dateTime) {
		if (isSelectable()) mSelectedDate = dateTime;
	}

	public void updateCells() {
		getCurrentDaysGridView().updateDaysCells();
	}

	private void updateWeekHeader() {
		for (int i = 0; i < mWeekHeaderTextViews.length; i++) {
			mWeekHeaderTextViews[i].setText(mCurrentDaysGridView.getWeekHeaderColumnTitle(i));
		}
	}

}
