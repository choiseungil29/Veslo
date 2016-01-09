package com.clogic.veslo.View.Calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.clogic.veslo.R;
import com.clogic.veslo.Util.Logger;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by clogic on 2015. 12. 23..
 */
public class CLogicCalendarView extends View {

    private int width;
    private int height;

    private String[] daysName;

    protected float textSize;
    protected int textColor;
    protected int backgroundColor;
    protected int todayBackgroundColor;
    protected int selectedDayBackgroundColor;
    protected int daysNameBackgroundColor;

    public CLogicCalendarView(Context context) {
        this(context, null);
    }

    public CLogicCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CLogicCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CLogicCalendarView, 0, 0);
        //textSize = array.getFloat(R.styleable.CLogicCalendarView_calendarTextSize, 12);
        textSize = array.getDimension(R.styleable.CLogicCalendarView_calendarTextSize, 15);
        Logger.i("" + textSize);
        textColor = array.getColor(R.styleable.CLogicCalendarView_calendarTextColor, getResources().getColor(R.color.main_purple));
        backgroundColor = array.getColor(R.styleable.CLogicCalendarView_calendarBackgroundColor, getResources().getColor(R.color.main_purple));
        todayBackgroundColor = array.getColor(R.styleable.CLogicCalendarView_calendarTodayBackgroundColor, getResources().getColor(R.color.main_purple));
        selectedDayBackgroundColor = array.getColor(R.styleable.CLogicCalendarView_calendarSelectedDayBackgroundColor, getResources().getColor(R.color.main_purple));
        daysNameBackgroundColor = array.getColor(R.styleable.CLogicCalendarView_calendarDaysNameBackgroundColor, getResources().getColor(R.color.main_purple));

        initView(context);
    }

    public void initView(Context context) {
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(Locale.KOREA);
        daysName = dateFormatSymbols.getShortWeekdays();
        for(int i=0; i<daysName.length; i++) {
            Logger.i("" + i + ", " + daysName[i]);
        }
    }

    @Override
    protected void onMeasure(int parentWidth, int parentHeight) {
        super.onMeasure(parentWidth, parentHeight);

        width = MeasureSpec.getSize(parentWidth);
        height = MeasureSpec.getSize(parentHeight);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawMonth(canvas, Calendar.getInstance(Locale.KOREA));
    }

    protected void drawBackground(Canvas canvas) {
        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(backgroundColor);

        Paint daysNameBackgroundPaint = new Paint();
        daysNameBackgroundPaint.setColor(daysNameBackgroundColor);

        Paint linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.line_color));

        canvas.drawRect(0, 0, width, height, backgroundPaint); // default background
        canvas.drawRect(0, 0, width, textSize * 2 + textSize/2, daysNameBackgroundPaint); // days name background
        canvas.drawLine(0, 0, width, getResources().getDimensionPixelSize(R.dimen.line_height), linePaint);
    }

    protected void drawMonth(Canvas canvas, Calendar calendar) {

        // draw days name
        for(int i=1; i<daysName.length; i++) {
            int width = this.width/(daysName.length-1);
            Paint textPaint = new Paint();
            textPaint.setTextSize(textSize);

            if(i == 1 || i == 7) {
                textPaint.setColor(getResources().getColor(R.color.main_transparent_white));
            } else {
                textPaint.setColor(textColor);
            }
            canvas.drawText(daysName[i], width/2 + width * (i-1) - textSize/2, textSize * 2, textPaint);
        }

        float monthHeight = textSize * 4 + getResources().getDimensionPixelSize(R.dimen.line_height);

        // draw month text
        Paint monthPaint = new Paint();
        monthPaint.setColor(textColor);
        monthPaint.setTextSize(textSize);
        int month = calendar.get(Calendar.MONTH);
        String monthText = String.valueOf(month+1) + "월";
        Rect bounds = new Rect();
        monthPaint.getTextBounds(monthText, 0, monthText.length(), bounds);
        canvas.drawText(monthText,
                width/2 - bounds.width()/2,
                textSize * 4 + getResources().getDimensionPixelSize(R.dimen.line_height),
                monthPaint);


        /**
         * 선들을 그린다
         * 선을 그리고 각자 날들을 그림
         */
        Paint linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.line_color));
        canvas.drawLine(0, textSize * 5 + getResources().getDimensionPixelSize(R.dimen.line_height),
                width, textSize * 5 + getResources().getDimensionPixelSize(R.dimen.line_height) * 2, linePaint);
    }
}
