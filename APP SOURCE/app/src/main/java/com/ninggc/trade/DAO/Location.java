package com.ninggc.trade.DAO;

/**
 * Created by Ning on 7/24/2017 0024.
 */

public class Location implements IBean {
    private int id;
//    private int cityId;
    private String extra;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "city_id", nullable = false)
//    public int getCityId() {
//        return cityId;
//    }
//
//    public void setCityId(int cityId) {
//        this.cityId = cityId;
//    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (id != location.id) return false;
//        if (cityId != location.cityId) return false;
        if (extra != null ? !extra.equals(location.extra) : location.extra != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
//        result = 31 * result + cityId;
        result = 31 * result + (extra != null ? extra.hashCode() : 0);
        return result;
    }

}
