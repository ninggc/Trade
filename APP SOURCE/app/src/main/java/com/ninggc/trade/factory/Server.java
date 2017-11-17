package com.ninggc.trade.factory;

import com.ninggc.trade.factory.http.ResponseListener;
import com.ninggc.trade.factory.nohttp.CallServer;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

/**
 * Created by Ning on 11/16/2017 0016.
 */

@SuppressWarnings("all")
public class Server {
    //http://123.207.244.139:8082/
//    public static final String url = Constant.url_python;
    public static final String url = "http://123.207.244.139:8082/";

    public static final byte NO_WHAT = -1;

    //自动构建POST请求并且加入type=1
    public static Request<String> createStringRequest(String URL) {
        Request<String> request = NoHttp.createStringRequest(URL, RequestMethod.POST);
        request.set("type", "1");
        return request;
    }

    public static Request create() {
//        NoHttp.create
        return null;
    }

    public static void login(String username, String password, ResponseListener<String> responseListener) {
        Request<String> request = createStringRequest(url + "usermage/login/");
        request.set("username", username);
        request.set("password", password);
        CallServer.getInstance().add(NO_WHAT, request, responseListener);
    }

    public static void addUser(String username, String password, ResponseListener<String> responseListener) {
        Request<String> request = createStringRequest(url + "usermage/adduser/");
        request.set("username", username);
        request.set("password", password);
        request.set("ageinpassword", password);
        CallServer.getInstance().add(NO_WHAT, request, responseListener);
    }

    public static void addCommodity() {
        Request<String> request = createStringRequest(url + "market/sell/");
        request.set("country", "");
        request.set("province", "");
        request.set("cityname", "");
        request.set("extra", "");
        request.set("sort", "");
        request.set("price", "");
        request.set("note", "");
//        Binary binary = new FileBinary(new File(""));
//        request.set
        request.set("", "");
        // FIXME: 11/16/2017 0016
    }

    public static void showList() {
        Request request = createStringRequest(url + "market/look/all// page");
        // FIXME: 11/16/2017 0016
    }

    public static void showListWithSort(int sort_id) {
        Request request = createStringRequest(url + "market/look/sort//sortid");
        // FIXME: 11/16/2017 0016
    }

    public static void releaseComment(int commodity_id, String content, ResponseListener<String> responseListener) {
        Request request = createStringRequest(url + "market/look/commodity/" + commodity_id + "/");
        request.set("content", content);
        CallServer.getInstance().add(NO_WHAT, request, responseListener);
    }

    public static void purchase(int commodity_id) {
        Request request = createStringRequest(url + "ip/market/buy/" + commodity_id + "/");
        // FIXME: 11/16/2017 0016
    }

    public static void addFavorite(int commodity_id, ResponseListener<String> responseListener) {
        Request request = createStringRequest(url + "collection/" + commodity_id + "/");
        CallServer.getInstance().add(NO_WHAT, request, responseListener);
    }
}
