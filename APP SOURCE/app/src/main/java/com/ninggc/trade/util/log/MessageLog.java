package com.ninggc.trade.util.log;

import android.util.Log;

/**
 * Created by Ning on 12/24/2017 0024.
 */

public class MessageLog {
    public static void show(String TAG, String msg) {
        Log.e(TAG, "show: " + msg);
    }
}
