package com.ninggc.match.trade.DAO;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Ning on 7/24/2017 0024.
 */
@Entity
@Table(name = "delegation")
public class Delegation implements IBean {
    private int id;
//    private int publisherId;
    private String title;
    private String description;
    private transient User userByPublisherId;
    private transient Collection<UserHasDelegation> userHasDelegationsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "publisher_id", nullable = false)
//    public int getPublisherId() {
//        return publisherId;
//    }
//
//    public void setPublisherId(int publisherId) {
//        this.publisherId = publisherId;
//    }

    @Basic
    @Column(name = "title", nullable = true, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delegation that = (Delegation) o;

        if (id != that.id) return false;
//        if (publisherId != that.publisherId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
//        result = 31 * result + publisherId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id", nullable = false)
    public User getUserByPublisherId() {
        return userByPublisherId;
    }

    public void setUserByPublisherId(User userByPublisherId) {
        this.userByPublisherId = userByPublisherId;
    }

    @OneToMany(mappedBy = "delegationByDelegationId")
    public Collection<UserHasDelegation> getUserHasDelegationsById() {
        return userHasDelegationsById;
    }

    public void setUserHasDelegationsById(Collection<UserHasDelegation> userHasDelegationsById) {
        this.userHasDelegationsById = userHasDelegationsById;
    }
}
