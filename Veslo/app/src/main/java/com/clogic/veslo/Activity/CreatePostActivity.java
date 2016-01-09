package com.clogic.veslo.Activity;

import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.clogic.veslo.API.API;
import com.clogic.veslo.Fragment.SelectableMapsFragmentListener;
import com.clogic.veslo.Fragment.SelectableMapFragment;
import com.clogic.veslo.Model.Server.Info;
import com.clogic.veslo.Model.Server.User;
import com.clogic.veslo.R;
import com.clogic.veslo.Util.Logger;
import com.clogic.veslo.Util.Map;
import com.clogic.veslo.Util.Provider;
import com.google.android.gms.maps.model.LatLng;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by clogic on 2016. 1. 2..
 */
public class CreatePostActivity extends AppCompatActivity {

    Calendar meetDay;
    Calendar endDay;

    @Bind(R.id.tv_target_day) TextView tv_targetDay;
    @Bind(R.id.tv_target_time) TextView tv_targetTime;
    @Bind(R.id.tv_target_end_time) TextView tv_targetEndTime;

    @Bind(R.id.et_origin) EditText et_origin;
    @Bind(R.id.et_dest) EditText et_dest;
    @Bind(R.id.et_describe) EditText et_describe;

    LatLng originLocation;
    LatLng destLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        ButterKnife.bind(this, findViewById(R.id.create_meeting_post_view));

        meetDay = Calendar.getInstance();
        endDay = Calendar.getInstance();

        tv_targetDay.setText("" + meetDay.get(Calendar.YEAR) + "년 " +
                (meetDay.get(Calendar.MONTH)+1) + "월 " +
                meetDay.get(Calendar.DAY_OF_MONTH) + "일");
        tv_targetTime.setText("" + meetDay.get(Calendar.HOUR_OF_DAY) + ":" + meetDay.get(Calendar.MINUTE));
    }

    @OnClick(R.id.tv_target_day)
    public void setTargetDay() {
        Calendar today = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        meetDay.set(year, monthOfYear, dayOfMonth);
                        tv_targetDay.setText("" + year + "년 " + (monthOfYear+1) + "월 " + dayOfMonth + "일");
                    }
                },
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH));
        dpd.setMinDate(today);
        dpd.show(getFragmentManager(), null);
    }

    @OnClick(R.id.tv_target_time)
    public void setTime(View view) {
        Calendar today = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                        meetDay.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        meetDay.set(Calendar.MINUTE, minute);
                        tv_targetTime.setText("" + hourOfDay + ":" + minute);
                    }
                },
                today.get(Calendar.HOUR_OF_DAY),
                today.get(Calendar.MINUTE),
                true);
        tpd.show(getFragmentManager(), null);
    }

    @OnClick(R.id.tv_target_end_time)
    public void setEndTime(View view) {
        Calendar today = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                        endDay.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        endDay.set(Calendar.MINUTE, minute);
                        tv_targetEndTime.setText("" + hourOfDay + ":" + minute);
                    }
                },
                today.get(Calendar.HOUR_OF_DAY),
                today.get(Calendar.MINUTE),
                true);
        tpd.show(getFragmentManager(), null);
    }

    @OnClick(R.id.et_origin)
    public void setOriginLocation() {
        SelectableMapFragment fragment = new SelectableMapFragment();
        fragment.setListener(new SelectableMapsFragmentListener() {
            @Override
            public void onDestroy(LatLng location) {
                // 위치정보 가져온당
                originLocation = location;
                Map map = new Map(getApplicationContext());
                Address addr = map.latlngToAddress(originLocation);
                et_origin.setText(addr.getAdminArea() + " " + addr.getLocality() + " " + addr.getThoroughfare());
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_content, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @OnClick(R.id.et_dest)
    public void setDestiniationLocation() {
        SelectableMapFragment fragment = new SelectableMapFragment();
        fragment.setListener(new SelectableMapsFragmentListener() {
            @Override
            public void onDestroy(LatLng location) {
                // Location 챙겨왔다
                destLocation = location;
                Map map = new Map(getApplicationContext());
                Address addr = map.latlngToAddress(destLocation);
                et_dest.setText(addr.getAdminArea() + " " + addr.getLocality() + " " + addr.getThoroughfare());
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_content, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @OnClick(R.id.btn_create_meeting)
    public void createMeeting() {
        User user = Provider.getInstance().getUserProfile();
        API.getPostAPI().postMeeting(user.id, et_origin.getText().toString(), originLocation.latitude, originLocation.longitude,
                et_dest.getText().toString(), destLocation.latitude, destLocation.longitude,
                meetDay.getTimeInMillis(), endDay.getTimeInMillis(), et_describe.getText().toString()).enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Response<Info> response, Retrofit retrofit) {
                Logger.i("Success!");

                Info info = response.body();
            }

            @Override
            public void onFailure(Throwable t) {
                Logger.i("Failed!");
            }
        });
    }
}
