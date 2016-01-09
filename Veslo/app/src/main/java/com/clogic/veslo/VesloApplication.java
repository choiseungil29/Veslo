package com.clogic.veslo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.orm.SugarApp;
import com.pixplicity.easyprefs.library.Prefs;

import java.security.MessageDigest;

/**
 * Created by clogic on 2015. 11. 14..
 */
public class VesloApplication extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();

        Session.initialize(this, AuthType.KAKAO_TALK);

        new Prefs.Builder()
                .setContext(this)
                .setMode(MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        viewDeviceKeyHash();
    }

    public void viewDeviceKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("MY KEY HASH:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
