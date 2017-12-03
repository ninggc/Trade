package com.ninggc.trade.DAO;

import com.yanzhenjie.album.AlbumFile;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ning on 7/24/2017 0024.
 */

public class Commodity implements IBean, Serializable {
    private int id;
    private String name;
//    private int userId;
//    private int locationId;
    private Double price;
    //详述
    private String note;
    //备注
    private String detail_location;
    private List<AlbumFile> images;
    private String kind;
    private String location;
    private String cityNumber;

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
//    @Column(name = "location_id", nullable = false)
//    public int getLocationId() {
//        return locationId;
//    }
//
//    public void setLocationId(int locationId) {
//        this.locationId = locationId;
//    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<AlbumFile> getImages() {
        return images;
    }

    public void setImages(List<AlbumFile> images) {
        this.images = images;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commodity commodity = (Commodity) o;

        if (id != commodity.id) return false;
//        if (userId != commodity.userId) return false;
//        if (locationId != commodity.locationId) return false;
        if (name != null ? !name.equals(commodity.name) : commodity.name != null) return false;
        if (price != null ? !price.equals(commodity.price) : commodity.price != null) return false;
        if (note != null ? !note.equals(commodity.note) : commodity.note != null) return false;
        if (images != null ? !images.equals(commodity.images) : commodity.images != null) return false;
        if (kind != null ? !kind.equals(commodity.kind) : commodity.kind!= null) return false;
        if (detail_location != null ? !detail_location.equals(commodity.detail_location) : commodity.detail_location != null) return false;
        if (cityNumber != null ? !cityNumber.equals(commodity.cityNumber ) : commodity.cityNumber!= null) return false;
        if (location != null ? !location.equals(commodity.location) : commodity.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + userId;
//        result = 31 * result + locationId;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (detail_location != null ? detail_location.hashCode() : 0);
        result = 31 * result + (cityNumber != null ? cityNumber.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    public String getDetail_location() {
        return detail_location;
    }

    public void setDetail_location(String detail_location) {
        this.detail_location = detail_location;
    }

    public String getCityNumber() {
        return cityNumber;
    }

    public void setCityNumber(String cityNumber) {
        this.cityNumber = cityNumber;
    }
}
