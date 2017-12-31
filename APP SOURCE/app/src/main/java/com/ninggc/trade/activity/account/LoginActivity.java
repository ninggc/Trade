package com.ninggc.trade.activity.account;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.exceptions.HyphenateException;
import com.ninggc.trade.DAO.User;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.util.constants.Constant;
import com.ninggc.trade.util.constants.ILoginStatus;
import com.ninggc.trade.util.http.HttpResponseListener;
import com.ninggc.trade.util.http.Server;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yanzhenjie.loading.LoadingView;
import com.yanzhenjie.loading.dialog.LoadingDialog;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import static com.ninggc.trade.util.constants.Constant.DEBUG;

/**
 * @author Ning
 * Created by Ning on 7/26/2017 0026.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginStatus {
    public final String TAG = getClass().getName();
    public final String url = Server.url;
    public Gson gson = new Gson();

    TextInputLayout login_til_account;
    TextInputLayout login_til_password;
    Button login_btn_login;
    TextView login_tv_register_now;
    Toolbar mToolbar;
    Button btn_QQLogin;
    Dialog mWaitDialog;

    String result;
    String account;
    String password;

    Tencent mTencent;
    IUiListener QQUIListener;
    UserInfo userInfo;
    IUiListener QQUserInfoListener;

    /**
     * 登陆成功后Server返回的信息
     * @param result 服务器传回的字符串信息
     */
    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        login_btn_login = (Button) findViewById(R.id.login_btn_login);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mWaitDialog.isShowing()) {
            mWaitDialog.dismiss();
        }
    }

    @Override
    protected void initView() {
        LoadingView loadingView = new LoadingView(this);
        mWaitDialog = new LoadingDialog(this);
        login_til_account = (TextInputLayout) findViewById(R.id.login_til_account);
        login_til_password = (TextInputLayout) findViewById(R.id.login_til_password);
        login_tv_register_now = (TextView) findViewById(R.id.login_tv_register_now);
        login_tv_register_now.setOnClickListener(this);
        login_btn_login = (Button) findViewById(R.id.login_btn_login);
        login_btn_login.setOnClickListener(this);

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

    protected void initData() {
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

//    //NoHttp回调
//    EventHandler eventHandler  = new EventHandler() {
//        @Override
//        public void onRegister() {
//            super.onRegister();
//            Toast.makeText(LoginActivity.this, "register", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void afterEvent(int event, int result, Object data) {
//            if (data instanceof Throwable) {
//                try {
//                    Throwable throwable = (Throwable)data;
//                    String msg = throwable.getMessage();
//                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
//
//                    JSONObject object = new JSONObject(throwable.getMessage());
//                    String des = object.optString("detail");//错误描述
//                    int status = object.optInt("status");//错误代码
//                    if (status > 0 && !TextUtils.isEmpty(des)) {
//                        Toast.makeText(LoginActivity.this, des, Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
//                    if(result == SMSSDK.RESULT_COMPLETE) {
//                        boolean smart = (Boolean)data;
//                        if(smart) {
//                            Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
//                        } else {
//                            //依然走短信验证
//                            Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                    if (DEBUG) {
//                        Log.e(TAG, data.toString());
//                        Log.e(TAG, "afterEvent: " + result);
//                    }
//                    // 处理你自己的逻辑
//                }
//            }
//        }
//    };

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        //0：无状态
        //1：成功
        //-1：失败
        int myServerFlag = 0;
        int EMCFlag = 0;
        int flag = 0;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // TEST: 11/12/2017 0012 测试阶段只要为31就算作成功
            Log.e(TAG, "handleMessage: " + msg.what);
//            if (msg.what == 31) {
//                loginSuccess();
//                return;
//            }
            //2是我的服务器;21成功;-21失败
            //3是EMC的服务器;31成功;-31失败
            switch (msg.what) {
                case 21:
                    myServerFlag = 1;
                    if (EMCFlag == 1) {
                        flag = 1;
                    }
                    break;
                case -21:
                    flag = -1;
                    break;
                case 31:
                    EMCFlag = 1;
                    if (myServerFlag == 1) {
                        flag = 1;
                    }
                    break;
                case -31:
                    flag = -1;
                    break;
            }
            if (flag == -1) {
                loginFailed();
            } else if (flag == 1){
                loginSuccess();
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            点击登录按钮的事件
            case R.id.login_btn_login:
                account = login_til_account.getEditText().getText().toString();
                password = login_til_password.getEditText().getText().toString();
                if (account == null || "".equals(account)) {
                    Toast.makeText(this, getResources().getString(R.string.login_please_input_name), Toast.LENGTH_SHORT).show();
                    break;
                } else if (password == null || "".equals(password)) {
                    Toast.makeText(this, getResources().getString(R.string.login_please_input_password), Toast.LENGTH_SHORT).show();
                    break;
                }
//                 EMC和MyServer同时登陆
//                User user = new User();
//                user.setId(1);
//                user.setName("Ning");
//                AccountUtil.login(user);
                // FIXME: 11/8/2017 0008 待更正为注册成功后创建账号
                Server.login(account, password, new HttpResponseListener<String>() {
                    @Override
                    public void onStart(int what) {
                        super.onStart(what);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mWaitDialog.show();
                            }
                        });
                    }

                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        super.onSucceed(what, response);
                        String s = response.get();
                        Log.e(TAG_NOHTTP + TAG_INFO, "onSucceed: " + s);
                        if ("006".equals(s)) {
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        try {
                            if (response.responseCode() == 200) {
                                loginForEMC(account, password);
                                User user = new User();
                                user = gson.fromJson(s, User.class);

                                String cookie = Server.request.getHeaders().getValue("Cookie");
                                String[] i = cookie.split("=");
                                String Cookie = i[1];

                                AccountUtil.login(user, Cookie);

                                if (AccountUtil.isLogin()) {
                                    loginSuccess();
                                } else {
                                    Log.e(TAG, "onSucceed: " + "cookie is null");
                                    loginFailed();
                                }
                            } else {
                                Log.e(TAG, "onSucceed: " + response.responseCode());
                                loginFailed();
                            }

                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, "登录异常，请重新登录", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                        super.onFailed(what, response);
                        Log.e(TAG, "onFailed: " + response.get());
                        loginFailed();
                    }

                    @Override
                    public void onFinish(int what) {
                        super.onFinish(what);
                        mWaitDialog.dismiss();
                    }
                });
                /*
                final MyStringRequest request = new MyStringRequest(url + "usermage/login/", RequestMethod.POST);
                request.set("username", account);
                request.set("password", password);
                request(0, request, new HttpResponseListener<String>() {
                    //2是我的服务器;21成功;-21失败
                    Message msg = new Message();
                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        super.onSucceed(what, response);
                        if (response.getHeaders().getResponseCode() != 200) {
                            msg.what = -21;
                            return;
                        }
                        String result = response.get();
                        if (result.equals(String.valueOf(NOT_EXIST))) {
                            login_til_account.setError(getString(R.string.login_error_account_not_exist));
                            login_til_password.setErrorEnabled(false);
                        } else if (result.equals(String.valueOf(PASSWORD_INCORRECT))) {
                            login_til_password.setError(getString(R.string.login_error_password_incorrect));
                            login_til_account.setErrorEnabled(false);
                        } else {
                            login_til_account.setErrorEnabled(false);
                            login_til_password.setErrorEnabled(false);
                            setResult(result);
                            User user = null;
                            try {
                                user = gson.fromJson(result, User.class);
                            } catch (Exception e) {
                                e.printStackTrace();
                                msg.what = -21;
                                return;
                            }
                            // FIXME: 11/12/2017 0012 设置MD5
                            AccountUtil.MD5 = MD5Util.GetMD5Code(password);
                            msg.what = 21;
                            AccountUtil.login(user);
                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                        super.onFailed(what, response);
                        msg.what = -21;
                        if (DEBUG) {
                            Log.e(TAG, "onSucceed: " + what + response.get());
                        }
                    }

                    @Override
                    public void onFinish(int what) {
                        super.onFinish(what);
                        // FIXME: 11/12/2017 0012 忽略结果，直接设置成为成功状态
                        msg.what = 21;
                        handler.sendMessage(msg);
                    }
                });
                */
                break;
//                点击注册按钮的事件
            case R.id.login_tv_register_now:
                initRegisterPage();
                break;
            case R.id.login_btn_QQlogin:
                QQLogin();
                break;
            default:
                Toast.makeText(LoginActivity.this, "default", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void createAccountForEMC(final String account, final String password) {
        new Thread() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(account, password);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.e(TAG, "createAccountForEMC: " + "EMC注册账号失败" + e.getMessage());
                }
            }
        }.start();
        loginForEMC(account, password);
    }

    private void loginForEMC(final String account, final String password) {
        Log.e(TAG, "loginForEMC: " + "EMC开始登陆");
        EMClient.getInstance().login(account, password, new EMCallBack() {
            //3是EMC的服务器;31成功;-31失败
            Message message = new Message();
            @Override
            public void onSuccess() {
                message.what = 31;
                handler.sendMessage(message);
                AccountUtil.setEMCUser(account, password);
                Log.e(TAG, "onSuccess: " + "EMC登陆成功");
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");

                EaseUI ease = EaseUI.getInstance();
                ease.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
                    @Override
                    public EaseUser getUser(String username) {
                        // FIXME: 11/12/2017 0012 返回用户信息
                        return null;
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                message.what = -31;
                handler.sendMessage(message);
                Log.e(TAG, "onError: " + "EMC登录失败 : " + s);

            }

            @Override
            public void onProgress(int i, String s) {
                Log.i(TAG, "onProgress: " + i + s);
            }
        });
    }

    private void initRegisterPage() {
        // 如果希望在读取通信录的时候提示用户，可以添加下面的代码，并且必须在其他代码调用之前，否则不起作用；如果没这个需求，可以不加这行代码
        SMSSDK.setAskPermisionOnReadContact(true);
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(pageEventHandler);
        registerPage.show(this);
    }

    EventHandler pageEventHandler = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            super.afterEvent(event, result, data);
            Log.e(TAG, "afterEvent: " + result);
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Map<String, Object> map = (Map<String, Object>) data;
                    String country = (String) map.get("country");
                    String phone = (String) map.get("phone");
                    Log.e(TAG, "afterEvent: " + "验证成功");
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    intent.putExtra("country", country);
                    intent.putExtra("phone", phone);
                    startActivityForResult(intent, Constant.REGISTER);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Log.e(TAG, "afterEvent: " + "获取验证码成功");
//                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), IRequestCode.REGISTER);
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //如果你调用了获取国家区号类表会在这里回调
                    //返回支持发送验证码的国家列表
                }
            } else {
                if (data instanceof Throwable) {
                    try {
                        Throwable throwable = (Throwable) data;
                        throwable.printStackTrace();
                        JSONObject object = null;
                        object = new JSONObject(throwable.getMessage());
                        //错误描述
                        String des = object.optString("detail");
                        //错误代码
                        int status = object.optInt("status");
                        if (status > 0 && !TextUtils.isEmpty(des)) {
                            Log.e(TAG, "afterEvent: " + des);
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            // 根据服务器返回的网络错误，给toast提示
        }
    };

    public void loginSuccess() {
        setResult(Constant.SUCCESS);
        finish();
    }

    public void loginFailed() {
        Log.e(TAG, "loginFailed: ");
        Toast.makeText(this, getResources().getString(R.string.main_login_failed), Toast.LENGTH_SHORT).show();
    }

    public void loginCancel() {
        setResult(Constant.CANCEL);
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
//        SMSSDK.unregisterEventHandler(eventHandler);
        SMSSDK.unregisterEventHandler(pageEventHandler);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, QQUIListener);
            mTencent.onActivityResult(requestCode, resultCode, data);
        }

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.REGISTER:
                    if (data == null) {
                        break;
                    }
                    String phone = data.getStringExtra("phone");
                    if (phone != null && !"".equals(phone)) {
                        login_til_account.getEditText().setText(phone);
                        login_til_password.getEditText().setText("");
                    }
                    break;
            }
        }
    }
}
