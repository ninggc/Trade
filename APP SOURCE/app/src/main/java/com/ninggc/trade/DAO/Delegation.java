package com.ninggc.trade.DAO;


import java.io.Serializable;

/**
 * Created by Ning on 7/24/2017 0024.
 */

public class Delegation implements IBean, Serializable {
    private int id;
//    private int publisherId;
    private String title;
    private String description;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
