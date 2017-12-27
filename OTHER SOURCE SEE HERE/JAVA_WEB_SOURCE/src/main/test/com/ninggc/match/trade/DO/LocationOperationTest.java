package com.ninggc.match.trade.DO;

import com.ninggc.match.trade.DAO.Location;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static com.ninggc.match.trade.factory.Constant.gson;
import static com.ninggc.match.trade.factory.Constant.test_show;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public class LocationOperationTest {
    LocationOperation lo = new LocationOperation();
    ArrayList<Location> locations = null;
    Location location = null;
    int id = 1;

    @Test
    public void insert() throws Exception {
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void selectById() throws Exception {
        location = lo.selectById(id);
        Assert.assertEquals(id, location.getId());
        if (test_show) {
            System.out.println(gson.toJson(location));
        }
    }

    @Test
    public void selectAll() throws Exception {
        Collection<Location> collection = lo.selectAll();
        Assert.assertNotEquals(null, collection);
        Assert.assertNotEquals(0, collection.size());
        if (test_show) {
            if (collection != null) {
                Iterator<Location> iterable = collection.iterator();
                while(iterable.hasNext()) {
                    location = iterable.next();
                    System.out.println(gson.toJson(location));
                }
            }
        }
    }

}