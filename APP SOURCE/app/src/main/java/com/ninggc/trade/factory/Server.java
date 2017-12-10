package com.ninggc.trade.factory;

import android.telecom.Call;
import android.util.Log;

import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.factory.http.ResponseListener;
import com.ninggc.trade.factory.nohttp.CallServer;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.Map;

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
        return createStringRequest(URL, RequestMethod.POST);
    }

    //自动构建POST请求并且加入type=1
    public static Request<String> createStringRequest(String URL, RequestMethod method) {
        Request<String> request = NoHttp.createStringRequest(URL, method);
        request.set("type", "1");
//        request.addHeader()
        return request;
    }

    public static Request request = null;
    public static void login(String username, String password, ResponseListener<String> responseListener) {
        Request<String> request = createStringRequest(url + "usermage/login/");
        request.set("username", username);
        request.set("password", password);
        CallServer.getInstance().add(NO_WHAT, request, responseListener);
        Server.request = request;
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

    public static void showList(int page, ResponseListener<String> responseListener) {
        Request request = createStringRequest(url + "market/look/all/" + page + "/");
        CallServer.getInstance().add(NO_WHAT, request, responseListener);
    }

    public static void showCommodityListWithSort(int sort_id, ResponseListener<String> responseListener) {
//        Request request = createStringRequest(url + "market/look/sort" + sort_id + "/1", RequestMethod.GET);
//        // FIXME: 11/16/2017 0016
//        CallServer.getInstance().add(NO_WHAT, request, responseListener);

        Request<String> request = createStringRequest(url + "market/look/sort" + sort_id + "/1", RequestMethod.GET);
        CallServer.getInstance().add(99, request, responseListener);

//        Request<String> request = createStringRequest("http://123.207.244.139/GotWord/word/selectByGroup.php", RequestMethod.POST);
//        request.set("word_group_id", "1");
//        CallServer.getInstance().add(99, request, responseListener);
    }

    public static void releaseCommodity(Commodity commodity, ResponseListener<String> responseListener) {
        Request request = createStringRequest(url + "market/sell/", RequestMethod.POST);
        request.set("cityname", commodity.getCityNumber());
        request.set("extra", commodity.getDetail_location());
        request.set("name", commodity.getName());
        request.set("sort", "12");
        request.set("price", String.valueOf(commodity.getPrice()));
        request.set("note", commodity.getNote());
        CallServer.getInstance().add(NO_WHAT, request, responseListener);
    }

    public static void releaseComment(int commodity_id, String content, ResponseListener<String> responseListener) {
        Request request = createStringRequest(url + "market/look/commodity/" + commodity_id + "/");
        request.set("content", content);
        CallServer.getInstance().add(NO_WHAT, request, responseListener);
    }

    public static void getDetailCommodity(int commodity_id, ResponseListener<String> responseListener) {
        Request<String> request = createStringRequest(url + "market/look/commodity/" + commodity_id + "/", RequestMethod.POST);
        CallServer.getInstance().add(NO_WHAT, request, responseListener);
    }

    public static void purchaseCommodity(int commodity_id) {
        Request request = createStringRequest(url + "ip/market/buy/" + commodity_id + "/");
        // FIXME: 11/16/2017 0016
    }

    public static void addFavorite(int commodity_id, ResponseListener<String> responseListener) {
        Request request = createStringRequest(url + "collection/" + commodity_id + "/");
        CallServer.getInstance().add(NO_WHAT, request, responseListener);
    }
}
