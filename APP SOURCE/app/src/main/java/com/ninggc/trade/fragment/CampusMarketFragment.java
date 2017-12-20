package com.ninggc.trade.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.account.PleaseLoginFragment;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.activity.c_d_activity.CommodityListFragment;
import com.ninggc.trade.adapter.CommodityRecyclerViewAdapter;
import com.ninggc.trade.adapter.MyFragmentPagerAdapter;
import com.ninggc.trade.factory.Server;
import com.ninggc.trade.factory.constants.Constant;
import com.ninggc.trade.factory.exception.NotSupportNowException;
import com.ninggc.trade.factory.http.ResponseListener;
import com.ninggc.trade.factory.image.GlideImageLoader;
import com.ninggc.trade.view.MyViewPager;
import com.yanzhenjie.nohttp.rest.Response;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ning on 12/10/2017 0010.
 */

public class CampusMarketFragment extends Fragment {
    View view;
    Banner banner;
//    RecyclerView recyclerView;
//    CommodityRecyclerViewAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView tv_notice;

    TabLayout tabLayout;
    MyViewPager viewPager;

    List<Commodity> commodities = new ArrayList<>();
    List<String> titles;
    List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_campus_market, container, false);

        initView();
        initViewPager();
        initList();

        return view;
    }

    private void initView() {
        banner = (Banner) view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(Arrays.asList(Constant.image1, Constant.image2, Constant.image3));
        banner.start();
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                syncList();
            }
        });
        tv_notice = (TextView) view.findViewById(R.id.campus_market_notice);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (MyViewPager) view.findViewById(R.id.view_pager);
    }

    void initViewPager() {
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        Collections.addAll(titles, getResources().getStringArray(R.array.title_book));
//        LayoutInflater inflater = getLayoutInflater().from(getContext());
//        fragments.add(inflater.inflate(R.layout.item_commidity, null));
//        fragments.add(inflater.inflate(R.layout.item_commidity, null));
//        fragments.add(inflater.inflate(R.layout.item_commidity, null));

        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setTag(titles.get(i)));
            fragments.add(new CommodityListFragment());
        }

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setOffscreenPageLimit(titles.size());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initList() {
//        List<Commodity> commodities = new ArrayList<>(10);
//        // TEST: 12/17/2017 0017 TEST 添加10个测试元素
//        for (int i = 0; i < 10; i++) {
//            commodities.add(i, Commodity.getTestInstance());
//        }
//
//        adapter = new CommodityRecyclerViewAdapter(getContext(), commodities);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);
    }

    private void syncList() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        int id = 0;
        try {
            Server.showCommodityListWithCampus(id, new ResponseListener<String>() {
                @Override
                public void onSucceed(int what, Response<String> response) {
                    super.onSucceed(what, response);
                }

                @Override
                public void onFailed(int what, Response<String> response) {
                    super.onFailed(what, response);
                    Log.e(BaseActivity.TAG_NOHTTP, "onFailed: " + response.get());
                }

                @Override
                public void onFinish(int what) {
                    super.onFinish(what);
                }
            });
        } catch (NotSupportNowException e) {
            e.printStackTrace();
        }
        swipeRefreshLayout.setRefreshing(false);
    }
}
