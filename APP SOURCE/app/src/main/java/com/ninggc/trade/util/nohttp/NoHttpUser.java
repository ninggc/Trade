package com.ninggc.trade.util.nohttp;

import android.util.Log;

import com.ninggc.trade.util.Server;
import com.ninggc.trade.util.constants.ILoginStatus;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import static com.ninggc.trade.util.constants.Constant.DEBUG;

/**
 * Created by Ning on 7/28/2017 0028.
 */
@Deprecated
class NoHttpUser implements ILoginStatus {
    public static final String url = Server.url;
    public static final String TAG = "NoHttpUser";
    private static String result;

    public static void setDebug(boolean b) {
        Logger.setDebug(b);
    }

    public static boolean verify(String account, String password) {
        boolean flag = false;
        Request<String> request = NoHttp.createStringRequest(url + "test");
        request.set("account", account);
        request.set("pwd", password);
        Response<String> response = NoHttp.startRequestSync(request);
        if (DEBUG) {
            Log.e(TAG, "verify: " + response.get());
        }
        result = response.get();
        return flag;
    }

//    public static boolean updatePortrait() {
//        Binary
//    }

    public static String getResult() {
        return result;
    }
}
