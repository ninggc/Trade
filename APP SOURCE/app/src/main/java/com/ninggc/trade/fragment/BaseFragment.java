package com.ninggc.trade.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.ninggc.trade.activity.account.AccountUtil;
import com.ninggc.trade.activity.account.LoginActivity;
import com.ninggc.trade.activity.base.BaseActivity;

/**
 * Created by Ning on 12/27/2017 0027.
 */

public class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!AccountUtil.isLogin()) {
            AlertDialog builder = new AlertDialog.Builder(getContext())
                    .setTitle("您好像还没有登录哦")
                    .setPositiveButton("登录", (dialog, which) -> startActivity(new Intent(getContext(), LoginActivity.class)))
//                    .setNegativeButton("取消", (dialog, which) -> )
                    .show();
        }
    }
}
