package com.ninggc.trade.util.http;

import java.util.Map;

/**
 * @author Ning
 * Created by Ning on 11/5/2017 0005.
 */
@Deprecated
public class HttpGetString {
    public static final int NO_WHAT = -1;
    private int what = NO_WHAT;
    private String url;
    private HttpResponseListener httpResponseListener;
    private Map<String, String> map;

    private HttpGetString(String url) {
        this.url = url;
    }

    public static HttpGetString getInstance(String url) {
        return new HttpGetString(url);
    }

    public void set(String key, String value) {
        map.put(key, value);
    }

    public void start() {
        HttpGetSomething.getString(what, url, httpResponseListener, map);
    }

    public int getWhat() {
        return what;
    }

    public HttpGetString setWhat(int what) {
        this.what = what;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public HttpGetString setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpResponseListener getHttpResponseListener() {
        return httpResponseListener;
    }

    public HttpGetString setHttpResponseListener(HttpResponseListener httpResponseListener) {
        this.httpResponseListener = httpResponseListener;
        return this;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public HttpGetString setMap(Map<String, String> map) {
        this.map = map;
        return this;
    }

}
