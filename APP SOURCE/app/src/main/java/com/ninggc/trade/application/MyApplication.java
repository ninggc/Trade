package com.ninggc.trade.application;

import com.mob.MobApplication;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * Created by Ning on 7/29/2017 0029.
 */

public class MyApplication extends MobApplication {
    public static RequestQueue queue = null;

    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this, new NoHttp.Config()
                .setNetworkExecutor(new OkHttpNetworkExecutor()));
        Logger.setTag("NoHttpUser");

    }
}
