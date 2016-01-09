package com.clogic.veslo.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.clogic.veslo.Activity.CalendarActivity;
import com.clogic.veslo.View.SimpleCalendarView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by clogic on 2015. 12. 27..
 */
public class SimpleCalendarAdapter extends RecyclerView.Adapter<SimpleCalendarAdapter.ViewHolder> {

    private Map<Integer, Integer> weeksMap;
    private String[] weeks = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };

    public SimpleCalendarAdapter() {
        Calendar today = Calendar.getInstance(Locale.KOREA);

        weeksMap = new HashMap<>();

        int dayOfMonth = today.get(Calendar.DAY_OF_MONTH);
        final int maximumDayOfCurrentMonth = today.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0; i<7;i ++) {
            int day = dayOfMonth + i;
            if(maximumDayOfCurrentMonth < day) {
                day -= maximumDayOfCurrentMonth;
            }
            weeksMap.put(i+1, day);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout)
        SimpleCalendarView view = new SimpleCalendarView(parent.getContext());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), CalendarActivity.class);
                parent.getContext().startActivity(intent);
            }
        });

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.view.setWeekDay(weeks[position]);
        holder.view.setDayOfMonth(String.valueOf(weeksMap.get(position+1)));
    }

    @Override
    public int getItemCount() {
        return weeksMap.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        SimpleCalendarView view;

        public ViewHolder(SimpleCalendarView v) {
            super(v);
            view = v;
        }
    }
}
