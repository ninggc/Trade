package com.ninggc.trade.activity.ease;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseUI;
import com.ninggc.trade.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ning on 11/12/2017 0012.
 */

public class TestFragment extends Fragment {

    View view;

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
//                Log.e("SP", "onClick: ");;
////                Intent intent = new Intent(getContext(), ChatActivity.class);
////                startActivity(intent);
//                SPContactUtil spUtil = new SPContactUtil(getContext());
//                spUtil.clear();
//                Log.e("SP", "onClick: " + new Gson().toJson(spUtil.getALLContacts()));;
//                Log.e("SP", "onClick: " + spUtil.getALLContacts().size());;

                EaseUI instance = EaseUI.getInstance();
                Context context = instance.getContext();
                SharedPreferences mSharedPreferences = context.getSharedPreferences("EM_SP_AT_MESSAGE", Context.MODE_PRIVATE);

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

                new Thread() {
                    @Override
                    public void run() {
                        Log.e("TEST", "run: ");
                        List<String> list =  EMClient.getInstance().contactManager().getBlackListUsernames();
                        Log.e("TEST", "run: " + list.size());
                        for (String s :
                                list) {
                            Log.e("TEST", "run: " + s);
                        }
                    }
                }.start();
            }
        });

    }
}
