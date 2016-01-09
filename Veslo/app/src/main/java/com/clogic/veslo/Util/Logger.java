package com.clogic.veslo.Util;

import android.util.Log;

/**
 * Created by clogic on 2015. 12. 23..
 */
public class Logger {

    public final static String TAG = Logger.class.getSimpleName();

    public static void i(String log) {
        Log.i(TAG, log);
    }
}
