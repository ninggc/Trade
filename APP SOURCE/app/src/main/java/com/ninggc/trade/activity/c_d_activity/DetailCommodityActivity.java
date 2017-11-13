package com.ninggc.trade.activity.c_d_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;

/**
 * @author Ning
 * Created by Ning on 8/16/2017 0016.
 */

public class DetailCommodityActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tv_name;
    TextView tv_intro;
    Commodity commodity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail_commodity);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_intro = (TextView) findViewById(R.id.detail_main_1_tv_intro);
        tv_name = (TextView) findViewById(R.id.detail_main_1_tv_name);
        initData();
    }

    private void initData() {
        commodity = (Commodity) getIntent().getSerializableExtra("commodity");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        tv_name.setText(commodity.getName());
        tv_intro.setText(commodity.getNote());
    }
}
