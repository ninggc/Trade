package com.ninggc.trade.activity.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;

/**
 * Created by Ning on 12/3/2017 0003.
 */

public class BaiduMapAty extends BaseActivity {
    TextureMapView mMapView;
    BaiduMap mBaiduMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.test_baidumap);
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initView() {
        super.initView();
        mMapView = (TextureMapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG_BaiduMap, "onClick: 1");
                mLocationClient.start();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG_BaiduMap, "onClick: 2");
//                mLocationClient.stop();

                // 构造定位数据
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy((float) 751.0)
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100).latitude(41.75)
                        .longitude(123.51).build();

                // 设置定位数据
                mBaiduMap.setMyLocationData(locData);

                mBaiduMap.setMyLocationEnabled(true);


                // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.panda);

//        mCurrentMode = LocationMode.FOLLOWING;//定位跟随态
//        mCurrentMode = LocationMode.NORMAL;   //默认为 LocationMode.NORMAL 普通态
//        mCurrentMode = LocationMode.COMPASS;  //定位罗盘态
                MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
                MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);

                mBaiduMap.setMyLocationConfiguration(config);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        initBaiduMapLocation(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
                //以下只列举部分获取经纬度相关（常用）的结果信息
                //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

                double latitude = location.getLatitude();    //获取纬度信息
                double longitude = location.getLongitude();    //获取经度信息
                float radius = location.getRadius();    //获取定位精度，默认值为0.0f


                Log.e(TAG_BaiduMap, "onReceiveLocation: " + location.getRadius());
                Log.e(TAG_BaiduMap, "onReceiveLocation: " + location.getLatitude());
                Log.e(TAG_BaiduMap, "onReceiveLocation: " + location.getLongitude());



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
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
