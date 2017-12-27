package com.ninggc.match.trade.DO;

import com.ninggc.match.trade.DAO.City;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static com.ninggc.match.trade.factory.Constant.gson;
import static com.ninggc.match.trade.factory.Constant.test_show;

/**
 * Finish
 */
public class CityOperationTest {
    CityOperation co = new CityOperation();
    City c = null;
    int id = 4;

    @Test
    public void insert() throws Exception {
//        c = new City();
//        c.setCountry(Country.China);
//        c.setProvince(Province.HeNan);
//        c.setCity(com.ninggc.match.trade.factory.City.LuoHe);
//        boolean flag = co.insert(c);
//        Assert.assertEquals(true, flag);
    }

    @Test
    public void delete() throws Exception {
        c = co.selectById(id);
        Assert.assertNotEquals(null, c);
        boolean flag = co.delete(id);
        Assert.assertEquals(true, flag);
        flag = co.insert(c);
        Assert.assertEquals(true, flag);
    }

    @Test
    public void update() throws Exception {
        c = co.selectById(id);
        c.setCity(com.ninggc.match.trade.factory.City.ZhengZhou);
        boolean flag = co.update(c);
        Assert.assertEquals(true, flag);
    }

    @Test
    public void selectById() throws Exception {
        c = co.selectById(id);
        Assert.assertEquals(id, c.getId());
        if (test_show) {
            if (c != null) {
                System.out.println(gson.toJson(c));
            }
        }
    }

    @Test
    public void selectAll() throws Exception {
        Collection<City> collection = co.selectAll();
        Assert.assertNotEquals(null, collection);
        Assert.assertNotEquals(0, collection.size());
        if (test_show) {
            if (collection != null) {
                Iterator<City> iterable = collection.iterator();
                while(iterable.hasNext()) {
                    c = iterable.next();
                    System.out.println(gson.toJson(c));
                }
            }
        }
    }

}