package com.ninggc.trade.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.ninggc.trade.DAO.Campus;
import com.ninggc.trade.DAO.Location;
import com.ninggc.trade.DAO.Province;
import com.ninggc.trade.DAO.User;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.address.CampusCheckActivity;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

public class PersonalInfoActivity extends BaseActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView tv_username;
    TextView tv_from;
    TextView tv_campus;
    View layout_edit;

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
        tv_username = (TextView) findViewById(R.id.personal_tv_username);
        tv_from = (TextView) findViewById(R.id.personal_info_tv_from);
        tv_campus = (TextView) findViewById(R.id.personal_info_tv_campus);
        layout_edit = findViewById(R.id.layout_edit);
        layout_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonalInfoActivity.this, "修改个人信息界面，尚未完成", Toast.LENGTH_SHORT).show();
            }
        });
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
        AndPermission.with(this)
                .requestCode(100)
                .permission(Permission.LOCATION)
                .callback(permissionListener)
                .start();
        mLocationClient.start();

        if (AccountUtil.isLogin()) {
            tv_username.setText(AccountUtil.getCurrentUser().getUsername());
        }
        User user = AccountUtil.getCurrentUser();
        if (user != null && user.getCampus() != null) {
            tv_campus.setText("学校：" + user.getCampus().getName() + "(未认证)");
        } else {
            tv_campus.setText("学校：无");
        }

        findViewById(R.id.personal_info_modify_tv_campus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCampus();
            }
        });
    }

    private void selectCampus() {
        Intent intent = new Intent(PersonalInfoActivity.this, CampusCheckActivity.class);
        startActivityForResult(intent, 777);
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            Log.e("PERMISSION", "onSucceed: " + requestCode);
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            Toast.makeText(PersonalInfoActivity.this, "无法获取定位权限", Toast.LENGTH_SHORT).show();
            Log.e("PERMISSION", "onFailed: " + gson.toJson(deniedPermissions));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 777:
                Province province = gson.fromJson(data.getStringExtra("province"), Province.class);
                Campus campus = gson.fromJson(data.getStringExtra("campus"), Campus.class);

                Log.e("CAMPUS", "onActivityResult: " + gson.toJson(province));
                Log.e("CAMPUS", "onActivityResult: " + gson.toJson(campus));
                AccountUtil.getCurrentUser().setCampus(campus);
                tv_campus.setText("学校：" + campus.getName() + "(未认证)");
                break;
            default:break;
        }
    }
}
