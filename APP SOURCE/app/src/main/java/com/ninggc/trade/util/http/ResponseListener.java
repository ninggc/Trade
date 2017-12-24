package com.ninggc.trade.util.http;

import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

/**
 * Created by Ning on 10/22/2017 0022.
 */

public abstract class ResponseListener<T> extends SimpleResponseListener<T> {
    final String TAG = this.getClass().getSimpleName();
    @Override
    public void onStart(int what) {
        super.onStart(what);
    }

    @Override
    public void onSucceed(int what, Response<T> response) {
        super.onSucceed(what, response);
    }

    @Override
    public void onFailed(int what, Response<T> response) {
        super.onFailed(what, response);
    }

    @Override
    public void onFinish(int what) {
        super.onFinish(what);
    }
}
