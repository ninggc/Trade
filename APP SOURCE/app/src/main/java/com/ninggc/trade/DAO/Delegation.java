package com.ninggc.trade.DAO;


import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * Created by Ning on 7/24/2017 0024.
 */

public class Delegation implements IBean, Serializable {
    private int id;
//    private int publisherId;
    private String title;
    private int sort;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

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

    static Random random = new Random();
    public static Delegation getTestInstance() {
        Delegation delegation = new Delegation();
        delegation.setId(random.nextInt(1000));
        delegation.setSort(random.nextInt(4));
        delegation.setTitle("Test instance");
        delegation.setDescription("这是一个测试用的委托");
        return delegation;
    }
}
