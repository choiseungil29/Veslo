package com.clogic.veslo.View.CompactCalendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by clogic on 2015. 12. 27..
 */
public class CompactSimpleCalendarView extends View {

    private int width;
    private int height;

    public CompactSimpleCalendarView(Context context) {
        this(context, null);
    }

    public CompactSimpleCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CompactSimpleCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void init() {

    }

    @Override
    protected void onMeasure(int parentWidth, int parentHeight) {
        super.onMeasure(parentWidth, parentHeight);
        int width = MeasureSpec.getSize(parentWidth);
        int height = MeasureSpec.getSize(parentHeight);
        if(width > 0 && height > 0) {

        }
        setMeasuredDimension(width, height);

        this.width = width;
        this.height = height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Calendar today = Calendar.getInstance(Locale.KOREA);
        //today.setTime(new Date());

        int dayOfMonth = today.get(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = today.getFirstDayOfWeek();

        String[] daysName = new DateFormatSymbols(Locale.KOREA).getShortWeekdays();
        Integer[] days = new Integer[7];

        final int maximumDayOfCurrentMonth = today.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0; i<7;i ++) {
            int day = dayOfMonth + i;
            if(maximumDayOfCurrentMonth < day) {
                day -= maximumDayOfCurrentMonth;
            }
            days[i] = day;
        }

        float segmentWidth = width/7;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        for(int i=0; i<7; i++) {
            canvas.drawText(String.valueOf(days[i]), segmentWidth * i + segmentWidth/2, 100, paint);
        }
    }
}
