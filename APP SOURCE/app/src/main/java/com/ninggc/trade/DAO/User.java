package com.ninggc.trade.DAO;

/**
 * Created by Ning on 7/24/2017 0024.
 */

public class User implements IBean {
    private int id;
    private String username;
    private String gender;
    private Integer age;
    private String introduce;
    private String unique;
    private String portrait;
    private String MD5;
    private Campus campus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null)
            return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        if (age != null ? !age.equals(user.age) : user.age != null) return false;
        if (introduce != null ? !introduce.equals(user.introduce) : user.introduce != null)
            return false;
        if (unique != null ? !unique.equals(user.unique) : user.unique != null) return false;
        if (portrait != null ? !portrait.equals(user.portrait) : user.portrait != null)
            return false;
        if (MD5 != null ? !MD5.equals(user.MD5) : user.MD5 != null) return false;
        return campus != null ? campus.equals(user.campus) : user.campus == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (introduce != null ? introduce.hashCode() : 0);
        result = 31 * result + (unique != null ? unique.hashCode() : 0);
        result = 31 * result + (portrait != null ? portrait.hashCode() : 0);
        result = 31 * result + (MD5 != null ? MD5.hashCode() : 0);
        result = 31 * result + (campus != null ? campus.hashCode() : 0);
        return result;
    }
}
