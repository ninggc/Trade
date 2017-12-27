package com.ninggc.match.trade.DO;

import com.ninggc.match.trade.DAO.User;
import static com.ninggc.match.trade.factory.Constant.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public class UserOperationTest {
    UserOperation uo = new UserOperation();
    ArrayList<User> users = null;
    User user;
    int id = 2;
    boolean flag = false;

    /**
     * test insert after test delete
     */
    public void insert(User u) throws Exception {
//        boolean flag = uo.insert(u);
//        Assert.assertEquals(true, flag);
    }

    @Test
    public void delete() throws Exception {
//        user = uo.selectById(id);
//        boolean flag =  uo.delete(id);
//        Assert.assertEquals(true, flag);
//        insert(user);
    }

    @org.junit.Test
    public void selectById() throws Exception {
        user = uo.selectById(id);
        Assert.assertEquals(id, user.getId());
        if (test_show) {
            System.out.println(gson.toJson(user.getSecurityById()));
        }
    }

    @org.junit.Test
    public void selectAll() throws Exception {
        Collection<User> collection = uo.selectAll();
        Assert.assertNotEquals(null, collection);
        Assert.assertNotEquals(0, collection.size());
    }

    @Test
    public void verify() throws Exception {

    }

    @Test
    public void selectByUnique() throws Exception {
        user = uo.selectByUnique("123");
        if (user != null) {
            Assert.assertEquals(id, user.getId());
        }
        if (test_show) {
            System.out.println(gson.toJson(user));
        }
    }

    @Test
    public void updatePassword() throws Exception {
        flag = uo.updatePassword(id, "456");
        Assert.assertEquals(true, flag);
    }

}