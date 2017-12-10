package com.ninggc.trade.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;

/**
 * Created by Ning on 12/10/2017 0010.
 */

public class StartupActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_startup);
        super.onCreate(savedInstanceState);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartupActivity.this, MainActivity.class));
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //nothing
            }
        }, 2 * 1000);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
