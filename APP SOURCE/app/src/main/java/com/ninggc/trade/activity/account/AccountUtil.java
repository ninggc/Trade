package com.ninggc.trade.activity.account;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.hyphenate.chat.EMClient;
import com.ninggc.trade.DAO.User;
import com.ninggc.trade.activity.MainActivity;

/**
 * @author Ning
 * Created by Ning on 8/16/2017 0016.
 * 记录账户的情况
 */

public class AccountUtil {
    /**
     * 登录状态
     */
    private static boolean loginStatus = false;
    /**
     * 登陆的账户
     */
    private static User currentUser;
    private static EMCUser emcUser = new EMCUser();
    public static String MD5;
    private static String cookie;

    public static boolean isLogin() {
        return loginStatus;
    }

    public static void login(User user, String cookie) {
        if (user == null || cookie == null || "".equals(cookie)) {
            return;
        }
        loginStatus = true;
        currentUser = user;
        AccountUtil.cookie = cookie;
    }

    public static void logout() {
        loginStatus = false;
        currentUser = null;
        cookie = null;
        EMClient.getInstance().logout(true);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static EMCUser getEmcUser() {
        return emcUser;
    }

    public static void setEMCUser(String username, String password) {
        emcUser.setUsername(username);
        emcUser.setPassword(password);
    }

    public static String getCookie() {
        return cookie;
    }

    public static class EMCUser {
        String username;
        String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

    public static boolean loginTip(Context context) {
        if (!AccountUtil.isLogin()) {
            AlertDialog builder = new AlertDialog.Builder(context)
                    .setTitle("您好像还没有登录哦")
                    .setPositiveButton("登录", (dialog, which) -> context.startActivity(new Intent(context, LoginActivity.class)))
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (context instanceof Activity) {
                                Activity activity = (Activity) context;
                                if (!activity.getClass().getSimpleName().equals(MainActivity.class.getSimpleName())) {
                                    activity.finish();
                                }
                            }
                        }
                    })
                    .show();
        } else {
            return true;
        }

        return false;
    }
}
