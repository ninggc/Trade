package com.ninggc.trade.factory.nohttp;

import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RestRequest;
import com.yanzhenjie.nohttp.rest.StringRequest;

/**
 * @author Ning
 * Created by Ning on 10/29/2017 0029.
 */

public class MyStringRequest extends RestRequest<String> {
    public MyStringRequest(String url) {
        this(url, RequestMethod.POST);
    }

    public MyStringRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
        this.set("type", "1");
    }

    @Override
    public String parseResponse(Headers responseHeaders, byte[] responseBody) throws Exception {
        return StringRequest.parseResponseString(responseHeaders, responseBody);
    }

}
