package com.ninggc.trade.DAO;

/**
 * Created by Ning on 7/24/2017 0024.
 */

public class User implements IBean {
    private int id;
    private String name;
//    private int campusId;
//    private int locationId;
//    private int addressId;
    private String gender;
    private Integer age;
    private String introduce;
    private String unique;
    private String portrait;
    private String MD5;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Basic
//    @Column(name = "campus_id", nullable = false)
//    public int getCampusId() {
//        return campusId;
//    }
//
//    public void setCampusId(int campusId) {
//        this.campusId = campusId;
//    }
//
//    @Basic
//    @Column(name = "location_id", nullable = false)
//    public int getLocationId() {
//        return locationId;
//    }
//
//    public void setLocationId(int locationId) {
//        this.locationId = locationId;
//    }
//
//    @Basic
//    @Column(name = "address_id", nullable = false)
//    public int getAddressId() {
//        return addressId;
//    }
//
//    public void setAddressId(int addressId) {
//        this.addressId = addressId;
//    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
//        if (campusId != user.campusId) return false;
//        if (locationId != user.locationId) return false;
//        if (addressId != user.addressId) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        if (age != null ? !age.equals(user.age) : user.age != null) return false;
        if (introduce != null ? !introduce.equals(user.introduce) : user.introduce != null) return false;
        if (unique != null ? !unique.equals(user.unique) : user.unique != null) return false;
        if (portrait != null ? !portrait.equals(user.portrait) : user.portrait != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + campusId;
//        result = 31 * result + locationId;
//        result = 31 * result + addressId;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (introduce != null ? introduce.hashCode() : 0);
        result = 31 * result + (unique != null ? unique.hashCode() : 0);
        result = 31 * result + (portrait != null ? portrait.hashCode() : 0);
        return result;
    }

}
