package com.clogic.veslo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.clogic.veslo.Activity.CalendarActivity;
import com.clogic.veslo.Adapter.GalleryAdapter;
import com.clogic.veslo.Adapter.GalleryGridAdapter;
import com.clogic.veslo.Adapter.SimpleCalendarAdapter;
import com.clogic.veslo.R;
import com.clogic.veslo.View.CompactCalendar.CompactSimpleCalendarView;
import com.clogic.veslo.View.ProfileView;
import com.clogic.veslo.View.ProfileViewEventListener;
import com.clogic.veslo.View.SimpleCalendarView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by clogic on 2015. 11. 20..
 */
public class ProfileFragment extends Fragment implements ProfileViewEventListener {


    @Bind(R.id.pv_main) ProfileView profile;
    //@Bind(R.id.rv_simple_calendar) RecyclerView rv_simpleCalendar;
    @Bind(R.id.rv_gallery) RecyclerView rv_gallery;
    @Bind(R.id.ll_calendar) LinearLayout ll_calendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        profile.setOnProfileViewListener(this);

        GalleryAdapter galleryAdapter = new GalleryAdapter();
        GridLayoutManager galleryManager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
        rv_gallery.setLayoutManager(galleryManager);
        rv_gallery.setAdapter(galleryAdapter);

        Map<Integer, Integer> weeksMap = new HashMap<>();
        String[] weeks = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };

        Calendar today = Calendar.getInstance(Locale.KOREA);
        int dayOfMonth = today.get(Calendar.DAY_OF_MONTH);
        final int maximumDayOfCurrentMonth = today.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0; i<7;i ++) {
            int day = dayOfMonth + i;
            if(maximumDayOfCurrentMonth < day) {
                day -= maximumDayOfCurrentMonth;
            }
            weeksMap.put(i+1, day);
        }

        for(int i=0; i<7; i++) {
            SimpleCalendarView childView = new SimpleCalendarView(getContext());
            childView.setWeekDay(weeks[i]);
            childView.setDayOfMonth(String.valueOf(weeksMap.get(i+1)));
            childView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), CalendarActivity.class);
                    getContext().startActivity(intent);
                }
            });
            ll_calendar.addView(childView);
        }

        return view;
    }

    @Override
    public void onClickMapTitle() {
        MapFragment fragment = new MapFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.fl_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
