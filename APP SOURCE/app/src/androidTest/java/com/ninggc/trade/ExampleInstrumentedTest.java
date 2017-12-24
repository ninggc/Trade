package com.ninggc.trade;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.ninggc.trade.util.nohttp.CallServer;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    Context context;

    String TAG = getClass().getSimpleName();
//    String url = "http://192.168.1.120/usermage/adduser/";
    String url = "http://123.207.244.139:8080/trade/image/test.jpg";

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.ninggc.match.trade", appContext.getPackageName());
    }

    @Test
    public void login() throws Exception {
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.set("username", "1");
        request.set("password", "1");
        request.set("ageinpassword", "1");
        request.set("phone", "1");
        request.set("email", "1");
        request.set("gender", "1");
//        request.set("age", "1");
        request.set("introduce", "1");
        request.set("unique", "1");
        Response<String> response = NoHttp.startRequestSync(request);
        String result = response.get();
        Log.e(TAG, "login: " + response.get());
    }

    @Test
    public void getImg() throws Exception {
        Request<Bitmap> request = NoHttp.createImageRequest(url, RequestMethod.POST);
        CallServer.getInstance().add(0, request, new OnResponseListener() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response response) {
                Bitmap bitmap = (Bitmap) response.get();
                System.out.println(bitmap.getByteCount());
            }

            @Override
            public void onFailed(int what, Response response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
}
