package com.clogic.veslo.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.clogic.veslo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by clogic on 2016. 1. 2..
 */
public class CreateMeetingView extends LinearLayout {

    private MeetingViewListener listener;

    public CreateMeetingView(Context context) {
        this(context, null);
    }

    public CreateMeetingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CreateMeetingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_meeting, this, true);

        ButterKnife.bind(this);
    }

    public void setListener(MeetingViewListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.btn_create_meeting)
    public void createMeeting() {
        listener.createMeeting();
    }
}
