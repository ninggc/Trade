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
    private static User currentUser;
    private static EMCUser emcUser = new EMCUser();
    public static String MD5;

    public static boolean isLogin() {
        return loginStatus;
    }

    public static void login(User user) {
        loginStatus = true;
        currentUser = user;
    }

    public static void logout() {
        loginStatus = false;
        currentUser = null;
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
}
