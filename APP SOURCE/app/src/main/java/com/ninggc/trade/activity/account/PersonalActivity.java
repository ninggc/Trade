package com.ninggc.trade.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ninggc.trade.R;

/**
 * @author Ning
 */
public class PersonalActivity extends AppCompatActivity {

    TextView textView2;
    TextView textView4;
    TextView textView6;
    TextView textView8;

    TextView tv_number1;
    TextView tv_number2;
    TextView tv_number3;

    TextView tv_username;
    TextView tv_description;
    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AccountUtil.isLogin()) {
            tv_username.setText(AccountUtil.getCurrentUser().getUsername());
        } else {
            tv_username.setText(getResources().getString(R.string.login_please_login));
        }
    }

    protected void initView() {
        textView2 = (TextView) findViewById(R.id.textView2);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView8 = (TextView) findViewById(R.id.textView8);

        tv_number1 = (TextView) findViewById(R.id.personal_number_1);
        tv_number2 = (TextView) findViewById(R.id.personal_number_2);
        tv_number3 = (TextView) findViewById(R.id.personal_number_3);

        tv_username = (TextView) findViewById(R.id.personal_tv_username);
        tv_description = (TextView) findViewById(R.id.personal_tv_description);
        iv_image = (ImageView) findViewById(R.id.personal_iv_image);

        initClickListener();
    }

    private void initClickListener() {
        tv_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!AccountUtil.isLogin()) {
                    startActivity(new Intent(PersonalActivity.this, LoginActivity.class));
                }
            }
        });

        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalActivity.this, PersonalInfoActivity.class));
            }
        });

        findViewById(R.id.layout_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonalActivity.this, "我发布的", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.layout_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonalActivity.this, "我卖出的", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.layout_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonalActivity.this, "我买到的", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.layout_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonalActivity.this, "我收藏的", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.layout_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountUtil.logout();
                finish();
            }
        });
    }
}
