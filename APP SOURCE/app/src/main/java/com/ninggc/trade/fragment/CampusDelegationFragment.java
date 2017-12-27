package com.ninggc.trade.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.ninggc.trade.DAO.Delegation;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.c_d_activity.CommodityListFragment;
import com.ninggc.trade.activity.c_d_activity.DelegationListFragment;
import com.ninggc.trade.adapter.CommodityRecyclerViewAdapter;
import com.ninggc.trade.adapter.DelegationRecycleViewAdapter;
import com.ninggc.trade.adapter.MyFragmentPagerAdapter;
import com.ninggc.trade.util.IGson;
import com.ninggc.trade.util.ITAG;
import com.ninggc.trade.util.Server;
import com.ninggc.trade.util.constants.Constant;
import com.ninggc.trade.util.exception.NotSupportNowException;
import com.ninggc.trade.util.http.ResponseListener;
import com.ninggc.trade.util.image.GlideImageLoader;
import com.ninggc.trade.util.log.MessageLog;
import com.ninggc.trade.view.MyViewPager;
import com.yanzhenjie.nohttp.rest.Response;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ning on 12/27/2017 0027.
 */

public class CampusDelegationFragment extends Fragment implements IGson, ITAG {
    View view;
    Banner banner;
    //    RecyclerView recyclerView;
//    CommodityRecyclerViewAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView tv_notice;
    TabLayout tabLayout;
    MyViewPager viewPager;
    DelegationRecycleViewAdapter recyclerViewAdapter;
    MyFragmentPagerAdapter pagerAdapter;

    ///或许可以只在adapter保存一个集合的引用就可以，将会考虑删掉这个
    @Deprecated
    List<Delegation> delegations = new ArrayList<>(10);
    List<String> titles;
    List<Fragment> fragments;
    List<Delegation> delegationList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_campus_delegation, container, false);

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
        tv_notice = (TextView) view.findViewById(R.id.campus_delegation_notice);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (MyViewPager) view.findViewById(R.id.view_pager);
    }

    void initViewPager() {
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        delegationList = new ArrayList<>();
        recyclerViewAdapter = new DelegationRecycleViewAdapter(getContext());
        Collections.addAll(titles, getResources().getStringArray(R.array.title_delegation));
        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setTag(titles.get(i)));
            fragments.add(DelegationListFragment.newInstance(recyclerViewAdapter));
        }
        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setOffscreenPageLimit(titles.size());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                syncRecyclerView(null);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //初始化集合数组
        //第一个集合设为页面最开始显示的列表
        syncRecyclerView(null);
    }

    private void initList() {

    }

    private void syncList() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        int id = 1;
        try {
            Server.showCommodityListWithCampus(id, new ResponseListener<String>() {
                @Override
                public void onSucceed(int what, Response<String> response) {
//                    super.onSucceed(what, response);
//                    String s = response.get();
//                    Log.e(TAG_NOHTTP + TAG_INFO, "onSucceed: response.get: " + s);
//                    try {
//                        List<Delegation> list = gson.fromJson(s, new TypeToken<List<Delegation>>(){}.getType());
//                        Log.e(TAG_INFO, "onSucceed: gson解析后: " + gson.toJson(list));
//                        syncRecyclerView(list);
//                    } catch (JsonSyntaxException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
                    Toast.makeText(getContext(), "未完成", Toast.LENGTH_SHORT).show();

                    // FIXME: 12/27/2017 0027 创建了十个测试用实例
                    for (int i = 0; i < 10; i++) {
                        delegationList.add(Delegation.getTestInstance());
                    }
                    syncRecyclerView(null);
                }

                @Override
                public void onFailed(int what, Response<String> response) {
                    super.onFailed(what, response);
                    Log.e(TAG_NOHTTP, "onFailed: " + response.get());
                }

                @Override
                public void onFinish(int what) {
                    super.onFinish(what);
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        } catch (NotSupportNowException e) {
            e.printStackTrace();
        }
    }

    private void syncRecyclerView(List<Delegation> list) {
        Set<Delegation> set = new LinkedHashSet<>(delegationList.size() * 2);
        if (list != null) {
            set.addAll(delegationList);
            set.addAll(list);
            delegationList = new ArrayList<>(set);
        }

        MessageLog.show(TAG_INFO, "CampusDelegationFragment.syncRecyclerView: ", delegationList);
        int[] value = getResources().getIntArray(R.array.sort_value_delegation);
        int sort = value[tabLayout.getSelectedTabPosition()];
        Collection<Delegation> collection = Collections2.filter(delegationList, new Predicate<Delegation>() {
            @Override
            public boolean apply(@NonNull Delegation input) {
                return sort == input.getSort();
            }
        });
        recyclerViewAdapter.changeList(new ArrayList<>(collection));
    }

}
