package com.ninggc.trade.DAO;

import com.baidu.location.BDLocation;

/**
 * Created by Ning on 7/24/2017 0024.
 */

public class Location implements IBean {
    private int id;
//    private int cityId;
    private String extra;
    private String address;
    private String country;
    private String province;
    private String city;
    private String district;
    private String street;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (id != location.id) return false;
        if (extra != null ? !extra.equals(location.extra) : location.extra != null) return false;
        if (address != null ? !address.equals(location.address) : location.address != null)
            return false;
        if (country != null ? !country.equals(location.country) : location.country != null)
            return false;
        if (province != null ? !province.equals(location.province) : location.province != null)
            return false;
        if (city != null ? !city.equals(location.city) : location.city != null) return false;
        if (district != null ? !district.equals(location.district) : location.district != null)
            return false;
        return street != null ? street.equals(location.street) : location.street == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (extra != null ? extra.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        return result;
    }

    public static Location cloneFrom(BDLocation bdLocation) {

        if (bdLocation == null) {
            return null;
        }

//        String addr = bdLocation.getAddrStr();    //获取详细地址信息
//        String country = bdLocation.getCountry();    //获取国家
//        String province = bdLocation.getProvince();    //获取省份
//        String city = bdLocation.getCity();    //获取城市
//        String district = bdLocation.getDistrict();    //获取区县
//        String street = bdLocation.getStreet();    //获取街道信息

        Location location = new Location();
        location.address = bdLocation.getAddrStr();
        location.country = bdLocation.getCountry();
        location.province = bdLocation.getProvince();
        location.city = bdLocation.getCity();
        location.district = bdLocation.getDistrict();
        location.street = bdLocation.getStreet();

        return location;
    }
}
