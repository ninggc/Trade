package com.ninggc.trade.activity.c_d_activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.util.constants.Constant;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.BitmapCallback;

import java.io.ByteArrayInputStream;

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
        if (commodity == null) {
            Toast.makeText(this, "您传了一个空的商品哦", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.e("INTENT", "initData: " + gson.toJson(commodity));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        layout_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        tv_name.setText(commodity.getName());
        tv_note.setText(commodity.getNote());
        tv_price.setText("￥" + commodity.getPrice().toString());

        if (commodity.getImages() != null) {
            Tiny.BitmapCompressOptions options = new Tiny.BitmapCompressOptions();
            for (int i = 0; i < commodity.getImages().size(); i++) {
                Tiny.getInstance().source(commodity.getImages().get(i)).asBitmap().withOptions(options).compress(new BitmapCallback() {
                    @Override
                    public void callback(boolean isSuccess, Bitmap bitmap, Throwable t) {
                        addImageView(bitmap);
                    }
                });
            }
        }

        if (commodity.getImg1() != null && !"".equals(commodity.getImg1())) {
            Glide.with(this).load(commodity.getImg1()).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    addImageView(resource);
                }
            });
        }
    }

    private void addImageView(Bitmap bitmap) {
        ImageView imageView = new ImageView(this);
        Tiny.BitmapCompressOptions options = new Tiny.BitmapCompressOptions();
        options.height = 150;
        options.width = 150;
        Tiny.getInstance().source(Constant.localImage).asBitmap().withOptions(options).compress(new BitmapCallback() {
            @Override
            public void callback(boolean isSuccess, Bitmap bitmap, Throwable t) {
                imageView.setImageBitmap(bitmap);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailCommodityActivity.this, "height" + imageView.getImageMatrix(), Toast.LENGTH_SHORT).show();
            }
        });

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        WindowManager windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) ((width / 3.0) - 8),(int) ((width / 3.0) - 8));
        params.setMargins(8, 0, 0, 0);
        imageView.setLayoutParams(params);
        linearLayout.addView(imageView);
    }

}
