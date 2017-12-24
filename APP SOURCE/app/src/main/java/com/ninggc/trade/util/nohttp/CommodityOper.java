package com.ninggc.trade.util.nohttp;

import com.ninggc.trade.util.Server;
import com.ninggc.trade.util.constants.ILoginStatus;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Ning on 9/28/2017 0028.
 */
@Deprecated
class CommodityOper implements ILoginStatus {
    public static final String url = Server.url;
    public static final String TAG = CommodityOper.class.getSimpleName();

    public static void requestCommodityList() {
        Request<String> request = NoHttp.createStringRequest(url + "requestCommodityList", RequestMethod.POST);
        Response<String> response = NoHttp.startRequestSync(request);
    }
}
