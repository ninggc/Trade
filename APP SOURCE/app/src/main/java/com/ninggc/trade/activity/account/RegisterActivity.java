package com.ninggc.trade.activity.account;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.factory.constants.Constant;
import com.ninggc.trade.factory.http.HttpGetString;
import com.ninggc.trade.factory.http.ResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ning on 11/4/2017 0004.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button btnRegister;
    TextInputLayout til_password;
    TextInputLayout til_password_again;
//    EditText et_password;
//    EditText et_password_again;

    String country;
    String phone;
//    String code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            country = getIntent().getStringExtra("country");
            phone = getIntent().getStringExtra("phone");
        }
        Log.e(TAG, "onCreate: " + "country : " + country);
        Log.e(TAG, "onCreate: " + "phone : " + phone);

        initView();
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnRegister = (Button) findViewById(R.id.register_btn_register);
        btnRegister.setOnClickListener(this);
        til_password = (TextInputLayout) findViewById(R.id.register_til_password);
        til_password_again = (TextInputLayout) findViewById(R.id.register_til_password_again);
//        et_password = (EditText) findViewById(R.id.register_et_password);
//        et_password_again = (EditText) findViewById(R.id.register_et_password_again);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn_register:
//                String password = et_password.getText().toString();
                String password = til_password.getEditText().getText().toString();
                if(!vertify(password)) {
                    Toast.makeText(this, getResources().getString(R.string.register_password_invalid), Toast.LENGTH_SHORT).show();
                    break;
                }else if (!password.equals(til_password_again.getEditText().getText().toString())) {
                    Toast.makeText(this, getResources().getString(R.string.register_password_diff), Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    final Map<String, String> map = new HashMap<>();
                    map.put("password", password);
                    // FIXME: 11/5/2017 0005 URL
                    Log.e(TAG, "onClick: " + "start http");
                    HttpGetString.getInstance(Constant.url_usermage + "register/").setResponseListener(new ResponseListener() {
                        @Override
                        public void onSucceed(int what, Response response) {
                            super.onSucceed(what, response);
                            Log.e(TAG, "onSucceed: ");
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        }

                        @Override
                        public void onFailed(int what, Response response) {
                            super.onFailed(what, response);
                            Log.e(TAG, "onFailed: ");
                            Message msg = new Message();
                            msg.what = -1;
                            handler.sendMessage(msg);
                        }

                        @Override
                        public void onFinish(int what) {
                            super.onFinish(what);
                            Log.e(TAG, "onFinish: " + "注册结束");
                            Message msg = new Message();
                            msg.what = 0;
                            handler.sendMessage(msg);
                        }
                    }).setMap(map).start();
                }
                break;
            default:break;
        }
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e(TAG, "handleMessage: " + msg.what);
            switch (msg.what) {
                case 1:
                    break;
                default:break;
            }
        }
    };

    private boolean vertify(String text) {
        if (text == null || "".equals(text)) {
            Log.e(TAG, "vertify: " + "密码不能为空");
            return false;
        } else if (text.length() < 9 || text.length() > 16) {
            Log.e(TAG, "vertify: "+ "密码长度为 : " + text.length());
            return false;
        }
        return true;
    }
}
