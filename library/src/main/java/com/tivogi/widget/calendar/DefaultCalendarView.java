package com.tivogi.widget.calendar;

import android.content.Context;
import android.util.AttributeSet;

public class DefaultCalendarView extends CalendarView<DaysGridView> {

	public DefaultCalendarView(Context context) {
		super(context);
	}

	public DefaultCalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected DaysGridView createDaysGridView() {
		return new DaysGridView(getContext());
	}

}
