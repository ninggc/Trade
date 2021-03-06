package com.ninggc.match.trade.DAO;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Ning on 7/24/2017 0024.
 */
@Entity
@Table(name = "indent")
public class Indent implements IBean {
    private String number;
//    private int purchaseId;
//    private int commodityId;
//    private int locationId;
    private Timestamp datetime;
    private String remark;
    private transient User userByPurchaseId;
    private transient Commodity commodityByCommodityId;
    private transient Location locationByLocationId;

    @Id
    @Column(name = "number", nullable = false, length = 45)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

//    @Basic
//    @Column(name = "purchase_id", nullable = false)
//    public int getPurchaseId() {
//        return purchaseId;
//    }
//
//    public void setPurchaseId(int purchaseId) {
//        this.purchaseId = purchaseId;
//    }
//
//    @Basic
//    @Column(name = "commodity_id", nullable = false)
//    public int getCommodityId() {
//        return commodityId;
//    }
//
//    public void setCommodityId(int commodityId) {
//        this.commodityId = commodityId;
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

    @Basic
    @Column(name = "datetime", nullable = true)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 45)
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

        Indent indent = (Indent) o;

//        if (purchaseId != indent.purchaseId) return false;
//        if (commodityId != indent.commodityId) return false;
//        if (locationId != indent.locationId) return false;
        if (number != null ? !number.equals(indent.number) : indent.number != null) return false;
        if (datetime != null ? !datetime.equals(indent.datetime) : indent.datetime != null) return false;
        if (remark != null ? !remark.equals(indent.remark) : indent.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
//        result = 31 * result + purchaseId;
//        result = 31 * result + commodityId;
//        result = 31 * result + locationId;
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "id", nullable = false)
    public User getUserByPurchaseId() {
        return userByPurchaseId;
    }

    public void setUserByPurchaseId(User userByPurchaseId) {
        this.userByPurchaseId = userByPurchaseId;
    }

    @ManyToOne
    @JoinColumn(name = "commodity_id", referencedColumnName = "id", nullable = false)
    public Commodity getCommodityByCommodityId() {
        return commodityByCommodityId;
    }

    public void setCommodityByCommodityId(Commodity commodityByCommodityId) {
        this.commodityByCommodityId = commodityByCommodityId;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    public Location getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(Location locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }
}
