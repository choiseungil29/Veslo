package com.clogic.veslo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.clogic.veslo.R;
import com.clogic.veslo.View.CompactCalendar.CompactCalendarView;
import com.clogic.veslo.View.CompactCalendar.domain.CalendarDayEvent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by clogic on 2015. 12. 26..
 */
public class CalendarActivity extends AppCompatActivity {

    @Bind(R.id.calendar_view) CompactCalendarView calendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ButterKnife.bind(this);

        calendar.addEvent(new CalendarDayEvent(System.currentTimeMillis(), getResources().getColor(R.color.main_purple)), true);
        calendar.addEvent(new CalendarDayEvent(System.currentTimeMillis() - 86400000, getResources().getColor(R.color.main_purple)), true);

        calendar.drawSmallIndicatorForEvents(true);
    }
}
