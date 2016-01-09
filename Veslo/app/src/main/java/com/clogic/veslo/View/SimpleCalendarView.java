package com.clogic.veslo.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clogic.veslo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by clogic on 2015. 12. 27..
 */
public class SimpleCalendarView extends LinearLayout {

    @Bind(R.id.tv_week_day) TextView tv_weekDay;
    @Bind(R.id.tv_day_of_month) TextView tv_dayOfMonth;

    public SimpleCalendarView(Context context) {
        this(context, null);
    }

    public SimpleCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_simple_calendar, this, true);

        ButterKnife.bind(this);
    }

    public void setWeekDay(String weekDay) {
        tv_weekDay.setText(weekDay);
    }

    public void setDayOfMonth(String dayOfMonth) {
        tv_dayOfMonth.setText(dayOfMonth);
    }
}
