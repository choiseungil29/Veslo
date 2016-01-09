package com.clogic.veslo.Model;

import java.util.Calendar;

/**
 * Created by clogic on 2016. 1. 5..
 */
public class Meeting {

    private Calendar meetCalendar;

    public Meeting() {

    }

    public Calendar getMeetCalendar() {
        return meetCalendar;
    }

    public void setMeetCalendar(Calendar meetCalendar) {
        this.meetCalendar = meetCalendar;
    }
}
