package com.ninggc.trade.activity.account;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.ninggc.trade.DAO.Location;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;

public class PersonalInfoActivity extends BaseActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView tv_from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal_info);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    protected void initView() {
        super.initView();

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        tv_from = (TextView) findViewById(R.id.personal_info_tv_from);
    }

    @Override
    protected void initData() {
        super.initData();

        initBaiduMapLocation(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                String addr = location.getAddrStr();    //获取详细地址信息
                String country = location.getCountry();    //获取国家
                String province = location.getProvince();    //获取省份
                String city = location.getCity();    //获取城市
                String district = location.getDistrict();    //获取区县
                String street = location.getStreet();    //获取街道信息
                Location myLocation = Location.cloneFrom(location);
                Log.e(TAG, "onReceiveLocation: " + gson.toJson(myLocation));
                tv_from.setText("来自: " + addr);
            }
        });

        mLocationClient.start();
    }
}
