package com.ninggc.trade.util.http;

import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.util.tool.ITAG;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

/**
 * Created by Ning on 10/22/2017 0022.
 * 继承自nohttp，便于重构
 */

public abstract class HttpResponseListener<T> extends SimpleResponseListener<T> implements ITAG {
    final String TAG = ITAG.TAG_NOHTTP;

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
