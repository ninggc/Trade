package com.ninggc.match.trade.DAO;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Ning on 7/24/2017 0024.
 */
@Entity
@Table(name = "location")
public class Location implements IBean {
    private int id;
//    private int cityId;
    private String extra;
    private transient Collection<Campus> campusesById;
    private transient Collection<Commodity> commoditiesById;
    private transient Collection<Indent> indentsById;
    private transient City cityByCityId;
    private transient Collection<User> usersById;
    private transient Collection<User> usersById_0;

    @Id
    @Column(name = "id", nullable = false)
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

    @Basic
    @Column(name = "extra", nullable = true, length = 45)
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

    @OneToMany(mappedBy = "locationByLocationId")
    public Collection<Campus> getCampusesById() {
        return campusesById;
    }

    public void setCampusesById(Collection<Campus> campusesById) {
        this.campusesById = campusesById;
    }

    @OneToMany(mappedBy = "locationByLocationId")
    public Collection<Commodity> getCommoditiesById() {
        return commoditiesById;
    }

    public void setCommoditiesById(Collection<Commodity> commoditiesById) {
        this.commoditiesById = commoditiesById;
    }

    @OneToMany(mappedBy = "locationByLocationId")
    public Collection<Indent> getIndentsById() {
        return indentsById;
    }

    public void setIndentsById(Collection<Indent> indentsById) {
        this.indentsById = indentsById;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    public City getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(City cityByCityId) {
        this.cityByCityId = cityByCityId;
    }

    @OneToMany(mappedBy = "locationByAddressId")
    public Collection<User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<User> usersById) {
        this.usersById = usersById;
    }

    @OneToMany(mappedBy = "locationByLocationId")
    public Collection<User> getUsersById_0() {
        return usersById_0;
    }

    public void setUsersById_0(Collection<User> usersById_0) {
        this.usersById_0 = usersById_0;
    }
}
