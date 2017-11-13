package com.ninggc.trade.factory.tool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ning on 11/5/2017 0005.
 */
public class SecurityUtilTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMd5() throws Exception {
        System.out.println(SecurityUtil.getMd5("906978985"));
    }

}