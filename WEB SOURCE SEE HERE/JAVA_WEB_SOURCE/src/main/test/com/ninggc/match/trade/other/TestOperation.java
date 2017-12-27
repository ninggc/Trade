package com.ninggc.match.trade.other;

import org.junit.Assert;

import java.io.IOException;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public class TestOperation implements I {
    @org.junit.Test
    public void test() throws IOException {
        String msg = getClass().getSimpleName();
        if (msg.contains("Operation")) {
            msg = msg.replaceAll("Operation", "");
        }
        Assert.assertEquals("Test", msg);

    }
}
