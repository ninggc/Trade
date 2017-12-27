package com.ninggc.trade.activity.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;

/**
 * @author Ning
 * Created by Ning on 8/1/2017 0001.
 */

@Deprecated
public class UserLogoutActivity extends BaseActivity {
    Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_userinfo);
        super.onCreate(savedInstanceState);



//        mToolbar = (Toolbar) findViewById(R.id.app_bar_layout);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
////        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    @Override
    protected void initView() {
        super.initView();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.login_btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountUtil.logout();
                finish();
            }
        });
    }
}
