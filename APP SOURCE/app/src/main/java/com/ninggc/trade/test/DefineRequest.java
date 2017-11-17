package com.ninggc.trade.test;

import com.ninggc.trade.encrypt.EncryptionUtil;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RestRequest;
import com.yanzhenjie.nohttp.rest.StringRequest;
import com.yanzhenjie.nohttp.tools.MultiValueMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ning on 10/6/2017 0006.
 */

public class DefineRequest extends RestRequest<String> {
    public DefineRequest(String url) {
        this(url, RequestMethod.POST);
    }

    public DefineRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
        //第一步:获取所有请求参数
        MultiValueMap<String, Object> multiValueMap = getParamKeyValues();

        //第二步,定义List用于存储所有请求参数的key
        List<String> keyList = new ArrayList<>();

        //第三步:定义Map用于存储所有请求参数的value
        Map<String, String> keyValueMap = new HashMap<>();

        //第四步:拿到所有具体请求参数
        for (Map.Entry<String, List<Object>> paramsEntry : multiValueMap.entrySet()) {
            String key = paramsEntry.getKey();
            List<Object> values = paramsEntry.getValue();
            for (Object value : values) {
                if (value instanceof String) {

                    //第五步:将请求参数的key添加到list中用于排序
                    keyList.add(key);

                    //第六步:将请求参数的value添加到Map中
                    keyValueMap.put(key, (String) value);
                }
            }
        }

        //第七步:对请求参数key进行排序
        Collections.sort(keyList);

        StringBuilder paramsBuilder = new StringBuilder();

        //第八步:依次取出排序之后的key-value,并拼接
        for (String key : keyList) {
            String value = keyValueMap.get(key);
            paramsBuilder.append(key).append("=").append(value).append("&");
        }

        String params = "";
        if(paramsBuilder.length() > 0) {
            //去掉最后一个&
            params = paramsBuilder.toString().substring(0, paramsBuilder.length() - 1);
        }

        //第九步:对拼接好的参数进行MD5加密
        String token =  EncryptionUtil.md5(params);

        //最后，添加到请求参数
        add("token", token);

        // 如果你们服务器要求添加到head，那么：
        // addHeader("token", token);
    }

    @Override
    public String parseResponse(Headers responseHeaders, byte[] responseBody) throws Exception {
        return StringRequest.parseResponseString(responseHeaders, responseBody);
    }
}
