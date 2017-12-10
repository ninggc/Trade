package com.ninggc.trade.activity.c_d_activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.sackcentury.shinebuttonlib.ShineButton;

/**
 * @author Ning
 * Created by Ning on 8/16/2017 0016.
 */

public class DetailCommodityActivity extends BaseActivity {
    Toolbar toolbar;
    TextView tv_name;
    TextView tv_note;
    TextView tv_price;
    LinearLayout linearLayout;
    Commodity commodity;
    ShineButton shineButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail_commodity);
        super.onCreate(savedInstanceState);
        initView();
    }

    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_note = (TextView) findViewById(R.id.detail_main_1_tv_intro);
        tv_name = (TextView) findViewById(R.id.detail_main_1_tv_name);
        tv_price = (TextView) findViewById(R.id.detail_main_1_tv_price);
        linearLayout = (LinearLayout) findViewById(R.id.image_container);
        shineButton = (ShineButton) findViewById(R.id.shine_button_collect);
        shineButton.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                if (checked) {
                    Toast.makeText(DetailCommodityActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailCommodityActivity.this, "UnChecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
        initData();
    }

    @SuppressLint("SetTextI18n")
    protected void initData() {
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
        tv_note.setText(commodity.getNote());
        tv_price.setText("￥" + commodity.getPrice().toString());

        addImageView(BitmapFactory.decodeResource(getResources(), R.drawable.panda));
    }

    private void addImageView(Bitmap bitmap) {
        ImageView imageView1 = new ImageView(this);
//        imageView1.setBackgroundColor(getResources().getColor(R.color.holo_red_light));
        imageView1.setImageBitmap(bitmap);
        imageView1.setBackgroundColor(getResources().getColor(R.color.white));
        imageView1.setPadding(8,8,8,8);
        linearLayout.addView(imageView1);
    }
}
