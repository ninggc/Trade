package com.ninggc.trade.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.TextureMapView;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;

/**
 * Created by Ning on 12/3/2017 0003.
 */

public class TestBaiduMap extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.test_baidumap);
        super.onCreate(savedInstanceState);

        initBaiduMapLocation(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
                //以下只列举部分获取经纬度相关（常用）的结果信息
                //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

                double latitude = location.getLatitude();    //获取纬度信息
                double longitude = location.getLongitude();    //获取经度信息
                float radius = location.getRadius();    //获取定位精度，默认值为0.0f

                String coorType = location.getCoorType();
                //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

                int errorCode = location.getLocType();
                //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

                Log.e(TAG, "onReceiveLocation: " + gson.toJson(location));

                String addr = location.getAddrStr();    //获取详细地址信息
                String country = location.getCountry();    //获取国家
                String province = location.getProvince();    //获取省份
                String city = location.getCity();    //获取城市
                String district = location.getDistrict();    //获取区县
                String street = location.getStreet();    //获取街道信息

                Log.e(TAG_BaiduMap, "onReceiveLocation: " + addr);
                Log.e(TAG_BaiduMap, "onReceiveLocation: " + country);
                Log.e(TAG_BaiduMap, "onReceiveLocation: " + province);
                Log.e(TAG_BaiduMap, "onReceiveLocation: " + city);
                Log.e(TAG_BaiduMap, "onReceiveLocation: " + district);
                Log.e(TAG_BaiduMap, "onReceiveLocation: " + street);
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();
//        TextureMapView mapView = (TextureMapView) findViewById(R.id.bmapView);
//        BaiduMap map = mapView.getMap();

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLocationClient == null) {
                    Toast.makeText(TestBaiduMap.this, "NULL", Toast.LENGTH_SHORT).show();
                } else {
                    mLocationClient.start();
                }
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationClient.stop();
            }
        });
    }

//    @Override
//    protected void initBaiduMapLocation(BDAbstractLocationListener listener) {
//
//        mLocationClient = new LocationClient(getApplicationContext());
//        //声明LocationClient类
//        mLocationClient.registerLocationListener(listener);
//        //注册监听函数
//
//        LocationClientOption option = new LocationClientOption();
//
//        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
//        //可选，设置定位模式，默认高精度
//        //LocationMode.Hight_Accuracy：高精度；
//        //LocationMode. Battery_Saving：低功耗；
//        //LocationMode. Device_Sensors：仅使用设备；
//
//        option.setCoorType("bd09ll");
//        //可选，设置返回经纬度坐标类型，默认gcj02
//        //gcj02：国测局坐标；
//        //bd09ll：百度经纬度坐标；
//        //bd09：百度墨卡托坐标；
//        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标
//
//        option.setScanSpan(0);
//        //可选，设置发起定位请求的间隔，int类型，单位ms
//        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
//        //如果设置非0，需设置1000ms以上才有效
//
//        option.setOpenGps(true);
//        //可选，设置是否使用gps，默认false
//        //使用高精度和仅用设备两种定位模式的，参数必须设置为true
//
//        option.setLocationNotify(true);
//        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
//
//        option.setIgnoreKillProcess(false);
//        //可选，定位SDK内部是一个service，并放到了独立进程。
//        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
//
//        option.SetIgnoreCacheException(false);
//        //可选，设置是否收集Crash信息，默认收集，即参数为false
//
//        //        option.setWifiValidTime(5*60*1000);
//        //可选，7.2版本新增能力
//        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位
//
//        option.setEnableSimulateGps(false);
//        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
//
//        option.setIsNeedAddress(true);
//
//        mLocationClient.setLocOption(option);
//        //mLocationClient为第二步初始化过的LocationClient对象
//        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
//
//
//    }
}
