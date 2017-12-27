package com.ninggc.match.trade.DOF;

import com.ninggc.match.trade.DAO.User;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public class AbstractOperationTest {
    AbstractOperation<User> uo = new AbstractOperation<User>("User") {};
    User user = null;
    ArrayList<User> users = null;

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
    }

    @Test
    public void selectAll() throws Exception {
    }

}