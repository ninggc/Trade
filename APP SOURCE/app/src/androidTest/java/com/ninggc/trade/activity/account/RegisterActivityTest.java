package com.ninggc.trade.activity.account;

import android.content.Context;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import static org.junit.Assert.*;

/**
 * @author Ning
 * Created by Ning on 11/4/2017 0004.
 */
public class RegisterActivityTest {
    Context context;
    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void registerPage() throws Exception {
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                super.afterEvent(event, result, data);
                Log.e(getClass().getSimpleName(), "afterEvent: ");
            }
        });
        registerPage.show(context);
    }

    @Test
    public void MobSMS() throws Exception {
        Looper.prepare();
        SMSSDK.registerEventHandler(eventHandler);
        SMSSDK.getSupportedCountries();
    }

    @After
    public void tearDown() throws Exception {

    }

    EventHandler eventHandler = new EventHandler() {
        @Override
        public void afterEvent(int i, int i1, Object o) {
            super.afterEvent(i, i1, o);
            Log.e("Mob", "afterEvent: " + o.toString());
        }
    };

}