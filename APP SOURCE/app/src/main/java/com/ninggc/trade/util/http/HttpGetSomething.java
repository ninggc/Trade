package com.ninggc.trade.util.http;

import android.graphics.Bitmap;

import com.ninggc.trade.util.nohttp.CallServer;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.Map;

/**
 * @author Ning
 * Created by Ning on 10/22/2017 0022.
 */
@Deprecated
public class HttpGetSomething {
    public static final int NO_WHAT = -1;

//    public static void get(String url, SimpleResponseListener<Bitmap> responseListener) {
//        getImage(NO_WHAT, url, responseListener);
//    }

    public static void getImage(int what, String url, HttpResponseListener<Bitmap> httpResponseListener, Map<String, String> map) {
        Request<Bitmap> request = NoHttp.createImageRequest(url);
        request.set("type", "1");
        if (map != null && map.size() != 0) {
            for (String s : map.keySet()) {
                request.set(s, map.get(s));
            }
        }
        CallServer.getInstance().add(what, request, httpResponseListener);
    }

    public static void getImage(int what, String url, HttpResponseListener<Bitmap> httpResponseListener) {
        getImage(what, url, httpResponseListener, null);
    }

    public static void getImage(String url, HttpResponseListener<Bitmap> httpResponseListener) {
        getImage(0, url, httpResponseListener, null);
    }

//    public static void get(String url, SimpleResponseListener<String> responseListener) {
//        getString(NO_WHAT, url, responseListener);
//    }

    public static void getString(int what, String url, HttpResponseListener<String> httpResponseListener, Map<String, String> map) {
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.set("type", "1");
        if (map != null && map.size() != 0) {
            for (String s : map.keySet()) {
                request.set(s, map.get(s));
            }
        }
        CallServer.getInstance().add(what, request, httpResponseListener);
    }

    public static void getString(int what, String url, HttpResponseListener<String> httpResponseListener) {
        getString(what, url, httpResponseListener, null);
    }

    public static void getString(String url, HttpResponseListener<String> httpResponseListener) {
        getString(NO_WHAT, url, httpResponseListener, null);
    }


}
