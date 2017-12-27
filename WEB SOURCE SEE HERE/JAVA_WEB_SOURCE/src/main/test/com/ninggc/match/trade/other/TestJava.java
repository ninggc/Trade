package com.ninggc.match.trade.other;

import com.ninggc.match.trade.controller.ILoginStatus;
import org.junit.Test;

public class TestJava implements ILoginStatus {
    @Test
    public void show() {
        System.out.println(Integer.valueOf(NOT_EXIST).toString());
    }
}
