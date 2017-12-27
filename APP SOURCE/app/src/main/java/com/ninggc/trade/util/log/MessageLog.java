package com.ninggc.trade.util.log;

import android.util.Log;

import com.ninggc.trade.util.IGson;

/**
 * Created by Ning on 12/27/2017 0027.
 */

public class MessageLog implements IGson {
    public static <T> void show(String TAG, String msg, T var) {
        Log.e(TAG, msg + gson.toJson(var));
    }

//    @Deprecated
//    public static void show(String TAG, String msg) {
//        Log.e(TAG, msg);
//    }
}
