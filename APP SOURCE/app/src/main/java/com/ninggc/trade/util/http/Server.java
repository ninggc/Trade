package com.ninggc.trade.util.http;

import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.util.exception.NotSupportNowException;
import com.ninggc.trade.util.nohttp.CallServer;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

/**
 * Created by Ning on 11/16/2017 0016.
 */

@SuppressWarnings("all")
public class Server {
    public static final String url = "http://123.207.244.139:8082/";

    //没有标记
    public static final byte NO_WHAT = -1;

    //user
    public static final byte USER_LOGIN = 11;
    public static final byte USER_REGISTER = 12;

    //commodity
    public static final byte COMMODITY_SHOW = 21;
    public static final byte COMMODITY_SHOW_BY_SORT = 22;
    public static final byte COMMODITY_SHOW_BY_CAMPUS = 23;
    public static final byte COMMODITY_SHOW_BY_PAGE = 24;

    public static final short COMMODITY_RELEASE = 31;
    public static final short COMMODITY_PURCHASE = 32;
    public static final short COMMODITY_FAVORITE = 33;
    public static final short COMMODITY_COMMENT = 34;

    //delegation
    // FIXME: 12/17/2017 0017 delegation请求


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

    //自动构建POST请求没有加入type=1
    public static Request<String> createStringRequestWithoutType(String URL, RequestMethod method) {
        Request<String> request = NoHttp.createStringRequest(URL, method);
        return request;
    }

    //需要获取cookie
    public static Request request = null;
    public static void login(String username, String password, HttpResponseListener<String> httpResponseListener) {
        Request<String> request = createStringRequest(url + "usermage/login/");
        request.set("username", username);
        request.set("password", password);
        CallServer.getInstance().add(NO_WHAT, request, httpResponseListener);
        Server.request = request;
    }

    public static void addUser(String username, String password, HttpResponseListener<String> httpResponseListener) {
        Request<String> request = createStringRequest(url + "usermage/adduser/");
        request.set("username", username);
        request.set("password", password);
        request.set("ageinpassword", password);
        CallServer.getInstance().add(NO_WHAT, request, httpResponseListener);
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

    public static void showList(int page, HttpResponseListener<String> httpResponseListener) {
        Request request = createStringRequest(url + "market/look/all/" + page + "/");
        CallServer.getInstance().add(COMMODITY_SHOW, request, httpResponseListener);
    }

    public static void showCommodityListWithSort(int sort_id, HttpResponseListener<String> httpResponseListener) {
//        Request request = createStringRequest(url + "market/look/sort" + sort_id + "/1", RequestMethod.GET);
//        // FIXME: 11/16/2017 0016
//        CallServer.getTestInstance().add(NO_WHAT, request, httpResponseListener);

        Request<String> request = createStringRequest(url + "market/look/sort" + sort_id + "/1", RequestMethod.GET);
        CallServer.getInstance().add(COMMODITY_SHOW_BY_SORT, request, httpResponseListener);

//        Request<String> request = createStringRequest("http://123.207.244.139/GotWord/word/selectByGroup.php", RequestMethod.POST);
//        request.set("word_group_id", "1");
//        CallServer.getTestInstance().add(99, request, httpResponseListener);
    }

    public static void showCommodityListWithCampus(int id, HttpResponseListener<String> httpResponseListener) throws NotSupportNowException {
//        Request<String> request = createStringRequest(url + "");
//        CallServer.getInstance().add(COMMODITY_SHOW_BY_CAMPUS, request, httpResponseListener);
        showList(id, httpResponseListener);
    }

    //OK
    public static void releaseCommodity(Commodity commodity, HttpResponseListener<String> httpResponseListener) {
        Request request = createStringRequest(url + "market/sell/", RequestMethod.POST);
        request.set("cityname", commodity.getCityNumber());
        request.set("extra", commodity.getDetailLocation());
        request.set("name", commodity.getName());
        request.set("sort", "12");
        request.set("price", String.valueOf(commodity.getPrice()));
        request.set("note", commodity.getNote());
        CallServer.getInstance().add(COMMODITY_RELEASE, request, httpResponseListener);
    }

    public static void releaseComment(int commodity_id, String content, HttpResponseListener<String> httpResponseListener) {
        Request request = createStringRequest(url + "market/look/commodity/" + commodity_id + "/");
        request.set("content", content);
        CallServer.getInstance().add(COMMODITY_COMMENT, request, httpResponseListener);
    }

//    public static void getDetailCommodity(int commodity_id, HttpResponseListener<String> responseListener) {
//        Request<String> request = createStringRequest(url + "market/look/commodity/" + commodity_id + "/", RequestMethod.POST);
//        CallServer.getTestInstance().add(NO_WHAT, request, responseListener);
//    }

    public static void purchaseCommodity(int commodity_id, HttpResponseListener<String> listener) {
        Request request = createStringRequest(url + "ip/market/buy/" + commodity_id + "/");
        // FIXME: 11/16/2017 0016
        CallServer.getInstance().add(COMMODITY_PURCHASE, request, listener);
    }

    public static void addFavorite(int commodity_id, HttpResponseListener<String> httpResponseListener) {
        Request request = createStringRequest(url + "collection/" + commodity_id + "/");
        CallServer.getInstance().add(COMMODITY_FAVORITE, request, httpResponseListener);
    }
}
