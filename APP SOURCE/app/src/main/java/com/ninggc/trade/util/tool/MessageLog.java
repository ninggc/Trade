package com.ninggc.trade.util.tool;

import android.util.Log;

/**
 * @author Ning
 * Created by Ning on 12/27/2017 0027.
 * 方便打印日志
 * 使容易实现在发布的时候控制调试日志的输出
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
