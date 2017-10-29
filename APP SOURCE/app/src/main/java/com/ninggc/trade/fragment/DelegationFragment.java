package com.ninggc.trade.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ninggc.trade.R;
import com.ninggc.trade.activity.c_d_activity.DelegationList;

/**
 * Created by Ning on 10/18/2017 0018.
 */

public class DelegationFragment extends Fragment {
    View view;
    Button btn_click;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delegation_index, container, false);
        this.view = view;
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn_click = (Button) view.findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toList();
            }
        });
    }

    void toList() {
        Intent i = new Intent(getContext(), DelegationList.class);
        startActivity(i);
    }
}
