package com.tivogi.calendar_view_sample;

import hirondelle.date4j.DateTime;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.tivogi.widget.calendar.CalendarView.OnDateClickLisetener;
import com.tivogi.widget.calendar.DefaultCalendarView;

public class MainActivity extends Activity {

	private DefaultCalendarView mCalendarView;

	private void bindViews() {
		mCalendarView = (DefaultCalendarView) findViewById(R.id.a_m_calendar);
		mCalendarView.setOnDateClickListener(new OnDateClickLisetener() {

			@Override
			public boolean onDateClick(DateTime date) {
				onDateClickImpl(date);
				return true;
			}
		});
	}

	protected void onDateClickImpl(DateTime date) {
		Toast.makeText(this, date.format("WWWW, MMMM D, YYYY", Locale.getDefault()), Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bindViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
