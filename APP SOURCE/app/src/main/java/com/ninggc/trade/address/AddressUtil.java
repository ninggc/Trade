package com.ninggc.trade.address;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ning on 11/19/2017 0019.
 */

public class AddressUtil {

//    List<City> list;

    /**
     * 去选择地址。
     */
//    public  static void selectAddress(Context context) {
//        Intent intent = new Intent(context, AddressCheckActivity.class);
//        startActivityForResult(intent, 666);
//    }

    /**
     * 解析地址。
     */
    public static void parseAddress(Intent intent) {
        ArrayList<City> cityList = AddressCheckActivity.parse(intent);

        String tvAddress = "", lastId = "";
        if (cityList != null) {
            for (int i = 0; i < cityList.size(); i++) {
                City city = cityList.get(i);
                lastId = city.getId();
                tvAddress += city.getName();
            }
        }
//        mTextView.setText(tvAddress + "\n提交到服务器的id是：" + lastId);
    }
}
