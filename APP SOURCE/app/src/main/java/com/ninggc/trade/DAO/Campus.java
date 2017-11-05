package com.ninggc.trade.DAO;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public class Campus implements IBean {
    private int id;
    private String name;
//    private int locationId;

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
//    @Column(name = "location_id", nullable = false)
//    public int getLocationId() {
//        return locationId;
//    }
//
//    public void setLocationId(int locationId) {
//        this.locationId = locationId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Campus campus = (Campus) o;

        if (id != campus.id) {
            return false;
        }
//        if (locationId != campus.locationId) return false;
        if (name != null ? !name.equals(campus.name) : campus.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + locationId;
        return result;
    }

}
