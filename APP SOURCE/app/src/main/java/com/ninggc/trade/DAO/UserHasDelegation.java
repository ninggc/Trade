package com.ninggc.trade.DAO;


import java.sql.Timestamp;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public class UserHasDelegation implements IBean {
    private String number;
//    private int userId;
//    private int delegationId;
    private Timestamp datetime;
    private String remark;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

//    @Basic
//    @Column(name = "user_id", nullable = false)
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    @Basic
//    @Column(name = "delegation_id", nullable = false)
//    public int getDelegationId() {
//        return delegationId;
//    }
//
//    public void setDelegationId(int delegationId) {
//        this.delegationId = delegationId;
//    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserHasDelegation that = (UserHasDelegation) o;

//        if (userId != that.userId) return false;
//        if (delegationId != that.delegationId) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (datetime != null ? !datetime.equals(that.datetime) : that.datetime != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
//        result = 31 * result + userId;
//        result = 31 * result + delegationId;
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

}
