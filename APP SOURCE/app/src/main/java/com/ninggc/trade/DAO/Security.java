package com.ninggc.trade.DAO;


/**
 * Created by Ning on 7/24/2017 0024.
 */

public class Security implements IBean {
    private String password;
    private String email;
    private int userId;
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return phone != null ? phone.equals(security.phone) : security.phone == null;
    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
