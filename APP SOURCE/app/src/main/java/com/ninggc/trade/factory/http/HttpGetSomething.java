package com.ninggc.trade.factory.http;

import android.graphics.Bitmap;

import com.ninggc.trade.factory.nohttp.CallServer;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

/**
 * @author Ning
 * Created by Ning on 10/22/2017 0022.
 */

public class HttpGetSomething {
    public static final int NO_WHAT = -1;

//    public static void get(String url, SimpleResponseListener<Bitmap> responseListener) {
//        getImage(NO_WHAT, url, responseListener);
//    }

    public static void getImage(int what, String url, SimpleResponseListener<Bitmap> responseListener) {
        Request<Bitmap> request = NoHttp.createImageRequest(url);
        CallServer.getInstance().add(what, request, responseListener);
    }

    public static void getImage(String url, SimpleResponseListener<Bitmap> responseListener) {
        getImage(0, url, responseListener);
    }

//    public static void get(String url, SimpleResponseListener<String> responseListener) {
//        getString(NO_WHAT, url, responseListener);
//    }

    public static void getString(int what, String url, SimpleResponseListener<String> responseListener) {
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        CallServer.getInstance().add(what, request, responseListener);
    }

    public static void getString(String url, SimpleResponseListener<String> responseListener) {
        getString(NO_WHAT, url, responseListener);
    }


}
