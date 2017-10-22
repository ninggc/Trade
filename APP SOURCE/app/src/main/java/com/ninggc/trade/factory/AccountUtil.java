package com.ninggc.trade.factory;

import com.ninggc.trade.DAO.User;

/**
 * Created by Ning on 8/16/2017 0016.
 */

public class AccountUtil {
    private static boolean loginStatus = false;
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
