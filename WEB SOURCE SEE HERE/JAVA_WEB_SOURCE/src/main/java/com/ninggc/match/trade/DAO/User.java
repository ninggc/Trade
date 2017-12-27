package com.ninggc.match.trade.DAO;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Ning on 7/24/2017 0024.
 */
@Entity
@Table(name = "user")
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
    private transient Collection<Commodity> commoditiesById;
    private transient Collection<Delegation> delegationsById;
    private transient Collection<Indent> indentsById;
    private transient Security securityById;
    private transient Campus campusByCampusId;
    private transient Location locationByAddressId;
    private transient Location locationByLocationId;
    private transient Collection<UserHasDelegation> userHasDelegationsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
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

    @Basic
    @Column(name = "gender", nullable = true, length = 1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "introduce", nullable = true, length = 45)
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Basic
    @Column(name = "unique", nullable = true, length = 45)
    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    @Basic
    @Column(name = "portrait", nullable = true, length = 45)
    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
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

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Commodity> getCommoditiesById() {
        return commoditiesById;
    }

    public void setCommoditiesById(Collection<Commodity> commoditiesById) {
        this.commoditiesById = commoditiesById;
    }

    @OneToMany(mappedBy = "userByPublisherId")
    public Collection<Delegation> getDelegationsById() {
        return delegationsById;
    }

    public void setDelegationsById(Collection<Delegation> delegationsById) {
        this.delegationsById = delegationsById;
    }

    @OneToMany(mappedBy = "userByPurchaseId")
    public Collection<Indent> getIndentsById() {
        return indentsById;
    }

    public void setIndentsById(Collection<Indent> indentsById) {
        this.indentsById = indentsById;
    }

    @OneToOne(mappedBy = "userByUserId")
    public Security getSecurityById() {
        return securityById;
    }

    public void setSecurityById(Security securityById) {
        this.securityById = securityById;
    }

    @ManyToOne
    @JoinColumn(name = "campus_id", referencedColumnName = "id", nullable = false)
    public Campus getCampusByCampusId() {
        return campusByCampusId;
    }

    public void setCampusByCampusId(Campus campusByCampusId) {
        this.campusByCampusId = campusByCampusId;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    public Location getLocationByAddressId() {
        return locationByAddressId;
    }

    public void setLocationByAddressId(Location locationByAddressId) {
        this.locationByAddressId = locationByAddressId;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    public Location getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(Location locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<UserHasDelegation> getUserHasDelegationsById() {
        return userHasDelegationsById;
    }

    public void setUserHasDelegationsById(Collection<UserHasDelegation> userHasDelegationsById) {
        this.userHasDelegationsById = userHasDelegationsById;
    }
}
