package com.ninggc.match.trade.DO;

import com.ninggc.match.trade.DAO.Security;
import static com.ninggc.match.trade.factory.Constant.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class SecurityOperationTest {
    SecurityOperation so = new SecurityOperation();
    List<Security> list = null;
    Security security = null;
    int id = 2;
    String tel = "13721373725";

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
        security = so.selectById(id);
        Assert.assertEquals(id, security.getUserId());
        if (test_show) {
            if (security != null) {
                System.out.println(gson.toJson(security));
            }
        }
    }

    @Test
    public void selectAll() throws Exception {
    }

    @Test
    public void selectByTel() {
        security = so.selectByTel(tel);
        Assert.assertEquals(tel, security.getTel());
        if (test_show) {
            System.out.println(gson.toJson(security));
        }
    }

}