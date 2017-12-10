package com.ninggc.trade.DAO;


/**
 * Created by Ning on 7/24/2017 0024.
 */

public class Security implements IBean {
    private String password;
    private String email;
    private int userId;
    private String tel;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Security security = (Security) o;

        if (userId != security.userId) return false;
        if (password != null ? !password.equals(security.password) : security.password != null)
            return false;
        if (email != null ? !email.equals(security.email) : security.email != null) return false;
        return tel != null ? tel.equals(security.tel) : security.tel == null;
    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        return result;
    }
}
