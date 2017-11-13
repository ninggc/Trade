package com.ninggc.trade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ninggc.trade.R;

/**
 * Created by Ning on 8/1/2017 0001.
 */

public class UserinfoActivity extends AppCompatActivity {
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
}
