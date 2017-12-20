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
import com.ninggc.trade.R;
import com.ninggc.trade.adapter.CommodityRecyclerViewAdapter;

/**
 * Created by Ning on 12/19/2017 0019.
 */

public class CommodityListFragment extends Fragment {
    Context context;
    View view;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_recyclerview, null);
        context = getContext();
        initView();
        return view;
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        CommodityRecyclerViewAdapter adapter = new CommodityRecyclerViewAdapter(context);
        recyclerView.setAdapter(adapter);

        adapter.addItem(Commodity.getTestInstance());
        adapter.addItem(Commodity.getTestInstance());
        adapter.addItem(Commodity.getTestInstance());
    }
}
