package com.ninggc.match.trade.controller;

import com.ninggc.match.trade.DAO.Security;
import com.ninggc.match.trade.DO.SecurityOperation;
import com.ninggc.match.trade.DO.UserOperation;

import java.io.IOException;

/**
 * Created by Ning on 7/21/2017 0021.
 */
public class Login extends BaseController implements ILoginStatus {
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//    private InputStream is;
//    private BufferedReader bufferedReader;
//    Gson gson = Constant.gson;

    UserOperation uo = new UserOperation();
    SecurityOperation so = new SecurityOperation();

    private String account;
    private String pwd;
    String unique;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String login() {
        String json;
        String result;
        try {
            json = ready();
            System.out.println("account: " + account + "\tpwd: " + pwd);
            System.out.println("action login: " + json);
            Security security = so.selectByTel(account);
            if (security == null) {
                response.getWriter().append(Integer.valueOf(NOT_EXIST).toString());
                return null;
            } else if (pwd.equals(security.getPassword())) {
                result = gson.toJson(security.getUserByUserId());
                System.out.println(gson.toJson(security.getUserByUserId()));
                response.getWriter().append(result);
                return null;
            } else {
                response.getWriter().append(Integer.valueOf(PASSWORD_INCORRECT).toString());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public String test() {
//        String json;
//        try {
//            json = ready();
//            System.out.println("account: " + account + "\tpwd: " + pwd);
//            System.out.println("test" + json);
//            response.getWriter().append("hello this is server in 192.168.1.103");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
