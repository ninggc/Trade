package com.ninggc.trade.activity.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ninggc.trade.DAO.Security;
import com.ninggc.trade.DAO.User;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.util.http.HttpResponseListener;
import com.ninggc.trade.util.http.Server;
import com.ninggc.trade.util.tool.MessageLog;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * @author Ning
 * Created by Ning on 11/4/2017 0004.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button btnRegister;
    TextInputLayout til_username;
    TextInputLayout til_password;
    TextInputLayout til_password_again;
    TextInputLayout til_email;
//    EditText et_password;
//    EditText et_password_again;

    MatchUtil matchUtil = new MatchUtil();
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
        til_username = (TextInputLayout) findViewById(R.id.register_til_username);
        til_password = (TextInputLayout) findViewById(R.id.register_til_password);
        til_password_again = (TextInputLayout) findViewById(R.id.register_til_password_again);
        til_email = (TextInputLayout) findViewById(R.id.register_til_email);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn_register:
                register();
                break;
            default:break;
        }
    }

    //验证输入字符是否合法
    public class MatchUtil {
        private boolean verifyUsername(String text) {
            if (text == null || "".equals(text)) {
                Log.e(TAG_TIP, "verifyUsername: " + "用户名不能为空");
                return false;
            }

            return true;
        }

        private boolean verifyPassword(String text) {
            if (text == null || "".equals(text)) {
                Log.e(TAG_TIP, "verifyPassword: " + "密码不能为空");
                return false;
            } else if (text.length() < 9 || text.length() > 16) {
                Log.e(TAG_TIP, "verifyPassword: "+ "密码长度为 : " + text.length());
                return false;
            }
            return true;
        }

        private boolean verifyEmail(String text) {
            if (text == null || "".equals(text)) {
                Log.e(TAG_TIP, "verifyEmail: " + "邮箱不能为空");
                return false;
            }

            return true;
        }
    }


    private void register() {
        String username = til_username.getEditText().getText().toString();
        String password = til_password.getEditText().getText().toString();
        String againPassword = til_password_again.getEditText().getText().toString();
        String email = til_email.getEditText().getText().toString();

        ///验证用户名
        if (!matchUtil.verifyUsername(username)) {
            Toast.makeText(this, getResources().getString(R.string.register_username_invalid), Toast.LENGTH_SHORT).show();
            return;
        }
        ///验证密码合法性
        if (!matchUtil.verifyPassword(password)) {
            Toast.makeText(this, getResources().getString(R.string.register_password_invalid), Toast.LENGTH_SHORT).show();
            return;
        }

        ///验证再次输入的密码的合法性
        if (!password.equals(againPassword)) {
            Toast.makeText(this, getResources().getString(R.string.register_password_diff), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!matchUtil.verifyEmail(email)) {
            Toast.makeText(this, getResources().getString(R.string.register_email_invalid), Toast.LENGTH_SHORT).show();
            return;
        }

        ///通过验证
        ///开始注册到服务器
        User user = new User();
        user.setUsername(username);
        Security security = new Security();
        security.setPhone(phone);
        security.setPassword(password);
        user.setSecurity(security);
        Server.register(user, new HttpResponseListener<String>() {
            @Override
            public void onStart(int what) {
                super.onStart(what);
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                super.onSucceed(what, response);
                String result = response.get();
                MessageLog.show(TAG_INFO, "RegisterActivity.onSucceed: ", result);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                super.onFailed(what, response);
            }

            @Override
            public void onFinish(int what) {
                super.onFinish(what);
            }
        });
    }
}
