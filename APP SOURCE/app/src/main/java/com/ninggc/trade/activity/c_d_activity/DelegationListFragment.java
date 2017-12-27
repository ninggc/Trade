package com.ninggc.trade.activity.c_d_activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.DAO.Delegation;
import com.ninggc.trade.R;
import com.ninggc.trade.adapter.CommodityRecyclerViewAdapter;
import com.ninggc.trade.adapter.DelegationRecycleViewAdapter;

/**
 * Created by Ning on 12/19/2017 0019.
 */

public class DelegationListFragment extends Fragment {
    Context context;
    View view;
    RecyclerView recyclerView;
    DelegationRecycleViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_recyclerview, null);
        context = getContext();
        initView();
        return view;
    }

    public static DelegationListFragment newInstance(DelegationRecycleViewAdapter adapter) {
        DelegationListFragment fragment = new DelegationListFragment();
        fragment.adapter = adapter;
        return fragment;
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        } else {
            adapter = new DelegationRecycleViewAdapter(context);
            recyclerView.setAdapter(adapter);
            adapter.addItem(Delegation.getTestInstance());
        }
    }
}
