package com.ninggc.trade.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.ninggc.trade.DAO.Location;
import com.ninggc.trade.util.IGson;
import com.ninggc.trade.util.ITAG;
import com.ninggc.trade.util.nohttp.CallServer;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;

/**
 * Created by Ning on 7/29/2017 0029.
 */

public abstract class BaseActivity extends AppCompatActivity implements ITAG, IGson {
//    private Object cancelObject = new Object();

    @Deprecated public String TAG = getClass().getSimpleName();

//    public String url_commodity = url + "/commodity";

    public LocationClient mLocationClient = null;
    Location location;

    @Deprecated
    public Request<String> createStringRequest(String url) {
        return NoHttp.createStringRequest(url, RequestMethod.POST);
    }

    public <T> void request(int what, Request<T> request, OnResponseListener<T> listener) {
        // 这里设置一个sign给这个请求。
//        request.setCancelSign(cancelObject);

        CallServer.getInstance().add(what, request, listener);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    /**
     * 初始化视图,在onCreate调用
     */
    protected void initView() { }

    /**
     * 初始化数据
     * 在onCreate调用
     * initView调用之后调用
     */
    protected void initData() { }

    protected void initBaiduMapLocation(BDAbstractLocationListener listener) {
        mLocationClient = new LocationClient(getApplicationContext());
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认gcj02
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(1000 * 5);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        //        option.setWifiValidTime(5*60*1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        option.setIsNeedAddress(true);

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明


        mLocationClient.registerLocationListener(listener);
    }
}
