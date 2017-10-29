package com.ninggc.trade.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ninggc.trade.R;
import com.ninggc.trade.factory.Constant;
import com.ninggc.trade.factory.nohttp.ILoginStatus;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import static com.ninggc.trade.factory.Constant.DEBUG;

/**
 * Created by Ning on 7/26/2017 0026.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginStatus {
    public final String TAG = getClass().getName();
    public final String url = Constant.url;
    public Gson gson = new Gson();

    TextInputLayout login_til_account;
    TextInputLayout login_til_password;
    Button login_btn_login;
    TextView login_tv_register_now;
    Toolbar mToolbar;
    Button btn_QQLogin;

    String resultInfo;
    String account;
    String password;

    Tencent mTencent;
    IUiListener QQUIListener;
    UserInfo userInfo;
    IUiListener QQUserInfoListener;

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
    }

    void initView() {
        login_til_account = (TextInputLayout) findViewById(R.id.login_til_account);
        login_til_password = (TextInputLayout) findViewById(R.id.login_til_password);
        login_btn_login = (Button) findViewById(R.id.login_btn_login);
        login_btn_login.setOnClickListener(this);
        login_tv_register_now = (TextView) findViewById(R.id.login_tv_register_now);
        login_tv_register_now.setOnClickListener(this);

        btn_QQLogin = (Button) findViewById(R.id.login_btn_QQlogin);
        btn_QQLogin.setOnClickListener(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCancel();
            }
        });
    }

    void initData() {
        // 创建EventHandler对象
        // 注册监听器
//        SMSSDK.registerEventHandler(eventHandler);
        mTencent = Tencent.createInstance(Constant.QQ_APP_ID, this.getApplicationContext());
        QQUIListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                if (DEBUG) {
                    Log.e(TAG, "onComplete: " + gson.toJson(o));
                }
                JSONObject jo = (JSONObject) o;
                String openid = null;
                try {
                    openid = jo.getString("openid");
                    String accessToken = jo.getString("access_token");
                    String expires = jo.getString("expires_in");
                    mTencent.setOpenId(openid);
                    mTencent.setAccessToken(accessToken, expires);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                mTencent.setOpenId(o);
                getUserInfoFromQQ();
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        };
        QQUserInfoListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                if (DEBUG) {
                    Log.e(TAG, "onComplete: " + gson.toJson(o));
                }
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        };
    }

    //NoHttp回调
    EventHandler eventHandler  = new EventHandler() {
        @Override
        public void onRegister() {
            super.onRegister();
            Toast.makeText(LoginActivity.this, "register", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void afterEvent(int event, int result, Object data) {
            if (data instanceof Throwable) {
                try {
                    Throwable throwable = (Throwable)data;
                    String msg = throwable.getMessage();
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");//错误描述
                    int status = object.optInt("status");//错误代码
                    if (status > 0 && !TextUtils.isEmpty(des)) {
                        Toast.makeText(LoginActivity.this, des, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    if(result == SMSSDK.RESULT_COMPLETE) {
                        boolean smart = (Boolean)data;
                        if(smart) {
                            Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                        } else {
                            //依然走短信验证
                            Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    if (DEBUG) {
                        Log.e(TAG, data.toString());
                        Log.e(TAG, "afterEvent: " + result);
                    }
                    // 处理你自己的逻辑
                }
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn_login:
                account = login_til_account.getEditText().getText().toString();
                password = login_til_password.getEditText().getText().toString();
                final Request<String> request = NoHttp.createStringRequest(url + "login");
                request.set("account", account);
                request.set("pwd", password);
                request(0, request, new SimpleResponseListener<String>() {
                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        super.onSucceed(what, response);
                        if (response.getHeaders().getResponseCode() != 200) {
                            Toast.makeText(LoginActivity.this, getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String result = response.get();
                        if (DEBUG) {
                            Log.e(TAG, "onSucceed: " + what + result);
                        }
                        if (result.equals(String.valueOf(NOT_EXIST))) {
                            login_til_account.setError(getString(R.string.login_error_account_not_exist));
                            login_til_password.setErrorEnabled(false);
                        } else if (result.equals(String.valueOf(PASSWORD_INCORRECT))) {
                            login_til_password.setError(getString(R.string.login_error_password_incorrect));
                            login_til_account.setErrorEnabled(false);
                        } else {
                            login_til_account.setErrorEnabled(false);
                            login_til_password.setErrorEnabled(false);
                            setResultInfo(result);
                            loginSuccess();
                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                        super.onFailed(what, response);
                        if (DEBUG) {
                            Log.e(TAG, "onSucceed: " + what + response.get());
                        }
                    }
                });
//                NoHttpUser.verify(account, password);

//                if (!"123".equals(account)) {
//                    login_til_account.setError(getString(R.string.login_account_error));
//                    login_til_password.setErrorEnabled(false);
//                } else if (!"qwe".equals(password)) {
//                    login_til_password.setError(getString(R.string.login_password_error));
//                    login_til_account.setErrorEnabled(false);
//                }else {
//                    login_til_account.setErrorEnabled(false);
//                    login_til_password.setErrorEnabled(false);
//                }

                break;
            case R.id.login_tv_register_now:
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(eventHandler);
//                registerPage.setRegisterCallback(new EventHandler() {
//                    public void afterEvent(int event, int resultInfo, Object data) {
//// 解析注册结果
//                        if (resultInfo == SMSSDK.RESULT_COMPLETE) {
//                            @SuppressWarnings("unchecked")
//                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
//                            String country = (String) phoneMap.get("country");
//                            String phone = (String) phoneMap.get("phone");
//
//// 提交用户信息（此方法可以不调用）
////                            registerUser(country, phone);
//                        }
//                    }
//                });
                registerPage.show(LoginActivity.this);
                break;
            case R.id.login_btn_QQlogin:
                QQLogin();
                break;
            default:
                Toast.makeText(LoginActivity.this, "default", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void loginSuccess() {
        if (DEBUG) {
            Log.e(TAG, "loginSuccess: " + account);
        }
        Intent data = new Intent();
        data.putExtra("user", resultInfo);
        //1 is no sense.
        setResult(1, data);
        finish();
    }

    public void loginCancel() {
        Intent data = new Intent();
//        data.putExtra("user");
        //1 is no sense.
        setResult(-1, data);
        finish();
    }

    public void QQLogin() {
        if (!mTencent.isSessionValid())
        {
            mTencent.login(this, "all", QQUIListener);
        }
    }

    public void getUserInfoFromQQ() {
        if (DEBUG) {
            Log.e(TAG, "getUserInfoFromQQ: " + mTencent.getQQToken().getOpenId());
        }
        userInfo = new UserInfo(this, mTencent.getQQToken());
        userInfo.getUserInfo(QQUserInfoListener);
    }

    public void QQLogout() {
        if (mTencent.isSessionValid()) {
            mTencent.logout(this);
        }
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, QQUIListener);
            mTencent.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
