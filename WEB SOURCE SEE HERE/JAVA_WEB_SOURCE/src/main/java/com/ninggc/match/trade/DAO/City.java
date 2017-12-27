package com.ninggc.match.trade.DAO;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Ning on 7/24/2017 0024.
 */
@Entity
@Table(name = "city")
public class City implements IBean {
    private int id;
    private String province;
    private String country;
    private String city;
    private transient Collection<Location> locationsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "province", nullable = true, length = 45)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "country", nullable = true, length = 45)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city1 = (City) o;

        if (id != city1.id) return false;
        if (province != null ? !province.equals(city1.province) : city1.province != null) return false;
        if (country != null ? !country.equals(city1.country) : city1.country != null) return false;
        if (city != null ? !city.equals(city1.city) : city1.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "cityByCityId")
    public Collection<Location> getLocationsById() {
        return locationsById;
    }

    public void setLocationsById(Collection<Location> locationsById) {
        this.locationsById = locationsById;
    }
}
