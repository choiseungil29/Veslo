package com.clogic.veslo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.clogic.veslo.R;

/**
 * Created by clogic on 2015. 11. 14..
 */
public class SplashActivity extends AppCompatActivity implements Runnable {

    private final int DEFAULT_LOADING_MILLS = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(this, DEFAULT_LOADING_MILLS);
    }

    @Override
    public void run() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.step_back);
    }
}
