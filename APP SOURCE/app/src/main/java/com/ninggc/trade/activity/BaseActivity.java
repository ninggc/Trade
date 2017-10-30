package com.ninggc.trade.activity;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.ninggc.trade.factory.nohttp.CallServer;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;

import static com.ninggc.trade.factory.Constant.*;

/**
 * Created by Ning on 7/29/2017 0029.
 */

public class BaseActivity extends AppCompatActivity {
    private Object cancelObject = new Object();
    public String TAG = getClass().getSimpleName();
    public Gson gson = new Gson();
    public String url_commodity = url + "/commodity";
    public final int NO_WHAT = 0;

    public Request<String> createStringRequest(String url) {
        return NoHttp.createStringRequest(url, RequestMethod.POST);
    }

    public <T> void request(int what, Request<T> request, OnResponseListener<T> listener) {
        // 这里设置一个sign给这个请求。
        request.setCancelSign(cancelObject);

        CallServer.getInstance().add(what, request, listener);
    }

    @Override
    protected void onDestroy() {
        // 在组件销毁的时候调用队列的按照sign取消的方法即可取消。
        CallServer.getInstance().cancelBySign(cancelObject);
        super.onDestroy();
    }
}
