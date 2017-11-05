package com.ninggc.trade.activity.account;

import com.ninggc.trade.DAO.User;

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
    private static User localUser;

    public static boolean isLogin() {
        return loginStatus;
    }

    public static void login(User user) {
        loginStatus = true;
        localUser = user;
    }

    public static void logout() {
        loginStatus = false;
        localUser = null;
    }

    public static User getLocalUser() {
        return localUser;
    }
}
