package com.ninggc.trade.activity.ease;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseUI;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.test.TestListActivity;
import com.ninggc.trade.factory.Server;
import com.ninggc.trade.factory.constants.Constant;
import com.ninggc.trade.factory.http.HttpGetSomething;
import com.ninggc.trade.factory.http.ResponseListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by Ning on 11/12/2017 0012.
 */

public class TestFragment extends Fragment {

    View view;
    String TAG = "NOHTTP";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.test_button, container, false);
        initView();
        return view;
    }

//         args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
//        args.putString(EaseConstant.EXTRA_USER_ID, "13721373725");
//        chatFragment.setArguments(args);

    void initView() {
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Server.addUser("ning", "12345678", new ResponseListener<String>() {
                    @Override
                    public void onSucceed(int what, com.yanzhenjie.nohttp.rest.Response<String> response) {
                        super.onSucceed(what, response);
                        Log.e(TAG, "onSucceed: " + response.get());
                    }

                    @Override
                    public void onFailed(int what, com.yanzhenjie.nohttp.rest.Response<String> response) {
                        super.onFailed(what, response);
                        Log.e(TAG, "onFailed: " +response.get());
                    }
                });
            }
        });

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ContactActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ChatFragment.class);
//                startActivity(intent);

//                new Thread() {
//                    @Override
//                    public void run() {
//                        Log.e("TEST", "run: ");
//                        List<String> list =  EMClient.getInstance().contactManager().getBlackListUsernames();
//                        Log.e("TEST", "run: " + list.size());
//                        for (String s :
//                                list) {
//                            Log.e("TEST", "run: " + s);
//                        }
//                    }
//                }.start();

//                Server.login("ning", "123", new ResponseListener<String>() {
//                    @Override
//                    public void onSucceed(int what, com.yanzhenjie.nohttp.rest.Response<String> response) {
//                        super.onSucceed(what, response);
//                        Log.e("NOHTTP", "onSucceed: " + response.get());
//                    }
//
//                    @Override
//                    public void onFailed(int what, com.yanzhenjie.nohttp.rest.Response<String> response) {
//                        super.onFailed(what, response);
//                        Log.e("NOHTTP", "onFailed: " + response.get());
//                    }
//                });

                startActivity(new Intent(getContext(), TestListActivity.class));

            }
        });



//        TextView tv = (TextView) view.findViewById(R.id.tv);

    }
}
