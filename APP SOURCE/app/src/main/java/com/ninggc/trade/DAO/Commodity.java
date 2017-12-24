package com.ninggc.trade.DAO;

import com.google.gson.annotations.SerializedName;
import com.ninggc.trade.util.constants.Constant;
import com.yanzhenjie.album.AlbumFile;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
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
    private String detailLocation;
    private List<AlbumFile> albumFiles;
    private List<String> images;
    private String sort;
    private String location;
    private String cityNumber;
    @SerializedName("user_id")
    private int userId;
    private String sellerName;

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

    public String getDetailLocation() {
        return detailLocation;
    }

    public void setDetailLocation(String detailLocation) {
        this.detailLocation = detailLocation;
    }

    public List<AlbumFile> getAlbumFiles() {
        return albumFiles;
    }

    public void setAlbumFiles(List<AlbumFile> albumFiles) {
        this.albumFiles = albumFiles;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCityNumber() {
        return cityNumber;
    }

    public void setCityNumber(String cityNumber) {
        this.cityNumber = cityNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commodity commodity = (Commodity) o;

        if (id != commodity.id) return false;
        if (userId != commodity.userId) return false;
        if (sellerName != commodity.sellerName) return false;
        if (name != null ? !name.equals(commodity.name) : commodity.name != null) return false;
        if (price != null ? !price.equals(commodity.price) : commodity.price != null) return false;
        if (note != null ? !note.equals(commodity.note) : commodity.note != null) return false;
        if (detailLocation != null ? !detailLocation.equals(commodity.detailLocation) : commodity.detailLocation != null)
            return false;
        if (albumFiles != null ? !albumFiles.equals(commodity.albumFiles) : commodity.albumFiles != null)
            return false;
        if (images != null ? !images.equals(commodity.images) : commodity.images != null)
            return false;
        if (sort != null ? !sort.equals(commodity.sort) : commodity.sort != null) return false;
        if (location != null ? !location.equals(commodity.location) : commodity.location != null)
            return false;
        return cityNumber != null ? cityNumber.equals(commodity.cityNumber) : commodity.cityNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (detailLocation != null ? detailLocation.hashCode() : 0);
        result = 31 * result + (albumFiles != null ? albumFiles.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (cityNumber != null ? cityNumber.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (sellerName != null ? sellerName.hashCode() : 0);
        return result;
    }

    /**
     * @return 返回id不同的测试用实例
     */
    public static Commodity getTestInstance() {
        Commodity commodity = new Commodity();
        commodity.setPrice(0.01);
        commodity.setNote("什么都没有");
        commodity.setImages(Arrays.asList(Constant.image1, Constant.image1, Constant.image1, Constant.image1));
        commodity.setId((int) new Date().getTime());
        return commodity;
    }
}
