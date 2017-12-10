package com.ninggc.trade.activity.account;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ninggc.trade.R;
import com.ninggc.trade.activity.MainActivity;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.activity.ease.ConversationListFragment;
import com.ninggc.trade.activity.test.TestListFragment;
import com.ninggc.trade.factory.constants.Constant;

/**
 * Created by Ning on 11/22/2017 0022.
 */

@SuppressLint("ValidFragment")
public class PleaseLoginFragment extends Fragment {

    Fragment fragment;

    public PleaseLoginFragment(Fragment fragment) {
        this.fragment = fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_login_please, container, false);
        view.findViewById(R.id.login_btn_please_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), LoginActivity.class), Constant.LOGIN);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (AccountUtil.isLogin()) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contentContainer, fragment);
            if (!MainActivity.mainActivity.isDestroyed() && MainActivity.mainActivity.bottomBar != null) {
                MainActivity.mainActivity.bottomBar.selectTabAtPosition(0);
            }
            transaction.commit();
        }
    }
}
