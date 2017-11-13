package com.ninggc.trade.activity.ease;

import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ning on 11/12/2017 0012.
 */
public class SPUtilTest {
    SPContactUtil spUtil = new SPContactUtil(InstrumentationRegistry.getContext());
    List<String> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        list.add("123");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setAllContacts() throws Exception {
        spUtil.setAllContacts(list);
    }

    @Test
    public void getALLContacts() throws Exception {
//        System.out.println("Hello");
        String x = new Gson().toJson(spUtil.getALLContacts());
        Log.e("test", "getALLContacts: " + x);
//        System.out.println(x);
    }

}