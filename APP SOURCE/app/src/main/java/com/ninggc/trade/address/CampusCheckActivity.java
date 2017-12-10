/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ninggc.trade.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ninggc.trade.DAO.Campus;
import com.ninggc.trade.DAO.Province;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>选择地址的页面。</p>
 * Created by YanZhenjie on 2017/6/1.
 */
public class CampusCheckActivity extends BaseActivity {

//    private static final String KEY_OUTPUT_PROVINCE_CITY_DISTRICT = "KEY_OUTPUT_PROVINCE_CITY_DISTRICT";
    private static final String KEY_OUTPUT_CAMPUS = "KEY_OUTPUT_CAAMPUS";

    public static ArrayList<Province> parse(Intent data) {
        return data.getParcelableArrayListExtra(KEY_OUTPUT_CAMPUS);
    }

    TabLayout mTabLayout;
    ViewPager mViewPager;

    CampusListAdapter mOneListAdapter;
    List<Province> mOneList;
    int mCurrentOneSelect = -1;

    CampusListAdapter mTwoListAdapter;
    List<IEntity> mTwoList;
    int mCurrentTwoSelect = -1;

//    CampusListAdapter mThreeListAdapter;
//    List<Province> mThreeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_select);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout.addOnTabSelectedListener(tabSelectedListener);

        RecyclerView oneView = new RecyclerView(this);
        RecyclerView twoView = new RecyclerView(this);
        RecyclerView threeView = new RecyclerView(this);
        List<RecyclerView> recyclerViewList = new ArrayList<>();
        recyclerViewList.add(oneView);
        recyclerViewList.add(twoView);
        recyclerViewList.add(threeView);
        PagerViewAdapter<RecyclerView> pagerViewAdapter = new PagerViewAdapter<>(recyclerViewList);
        mViewPager.setAdapter(pagerViewAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(R.string.select_please);

        oneView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        oneView.setLayoutManager(new LinearLayoutManager(this));
        mOneListAdapter = new CampusListAdapter(getLayoutInflater(), mProvinceItemClickListener);
        oneView.setAdapter(mOneListAdapter);

        twoView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        twoView.setLayoutManager(new LinearLayoutManager(this));
        mTwoListAdapter = new CampusListAdapter(getLayoutInflater(), mCampusItemClickListener);
        twoView.setAdapter(mTwoListAdapter);

        threeView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        threeView.setLayoutManager(new LinearLayoutManager(this));
//        mThreeListAdapter = new CampusListAdapter(getLayoutInflater(), mDistrictItemClickListener);
//        threeView.setAdapter(mThreeListAdapter);

        RequestCampusListTask requestCampusTask = new RequestCampusListTask(this, callback);
        requestCampusTask.execute();
    }

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {

        private int mCurrentPosition;

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int newPosition = tab.getPosition();
            switch (newPosition) {
                case 1: {
                    if (mTwoList == null) {
                        mTabLayout.getTabAt(mCurrentPosition).select();
                        return;
                    }
                    break;
                }
//                case 2: {
//                    if (mThreeList == null) {
//                        mTabLayout.getTabAt(mCurrentPosition).select();
//                        return;
//                    }
//                    break;
//                }
            }
            this.mCurrentPosition = tab.getPosition();
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };

    /**
     * 请求服务器数据回来。(我这里是从asset中的json中读取的，模拟从服务器请求。)
     */
    private RequestCampusListTask.Callback callback = new RequestCampusListTask.Callback() {
        @Override
        public void callback(List<Province> provinces) {
            Log.e("CAMPUS", "callback: " + provinces.size());
            Log.e("CAMPUS", "callback: " + gson.toJson(provinces.get(0)));
            mOneList = provinces;
            mOneListAdapter.notifyDataSetChanged(mOneList);
        }
    };

    /**
     * 省的item被点击。
     */
    private OnCompatItemClickListener mProvinceItemClickListener = new OnCompatItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mCurrentOneSelect == position) {
                mViewPager.setCurrentItem(1, true);
                return;
            }
            if (mCurrentOneSelect != -1) {
                mOneList.get(mCurrentOneSelect).setSelect(false);
                mOneListAdapter.notifyItemChanged(mCurrentOneSelect);
            }

            mCurrentOneSelect = position;
            mOneList.get(mCurrentOneSelect).setSelect(true);
            mOneListAdapter.notifyItemChanged(mCurrentOneSelect);

            Province one = mOneList.get(mCurrentOneSelect);
            List<String> campusList = one.getCampusList();
            mTwoList = new ArrayList<>(campusList.size());
            for (int i = 0; i < campusList.size(); i++) {
                mTwoList.add((IEntity) new Campus());
            }
            for (int i = 0; i < campusList.size(); i++) {
                Campus campus = new Campus();
                campus.setName(campusList.get(i));
                mTwoList.set(i, (IEntity) campus);
            }

            if (mTwoList == null || mTwoList.size() == 0) { // 选定一级。
//                setResultFinish(one, null, null);
            } else {
                // 更新二级的content和title。
                mTwoListAdapter.notifyDataSetChanged(mTwoList);
                mTabLayout.getTabAt(1).setText(one.getName());
                mViewPager.setCurrentItem(1, true);

                // 三级置空。
//                mTabLayout.getTabAt(2).setText(null);
//                mThreeList = null;
//                mCurrentTwoSelect = -1;
            }
        }
    };

    /**
     * 市的item被点击。
     *  学校的item被点击
     */
    private OnCompatItemClickListener mCampusItemClickListener = new OnCompatItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (mCurrentTwoSelect == position) {
                mViewPager.setCurrentItem(2, true);
                return;
            }

            if (mCurrentTwoSelect != -1) {
                mTwoList.get(mCurrentTwoSelect).setSelect(false);
                mTwoListAdapter.notifyItemChanged(mCurrentTwoSelect);
            }

            mCurrentTwoSelect = position;
            mTwoList.get(mCurrentTwoSelect).setSelect(true);
            mTwoListAdapter.notifyItemChanged(mCurrentTwoSelect);

            Campus two = (Campus) mTwoList.get(mCurrentTwoSelect);
            setResultFinish((Province) mOneList.get(mCurrentOneSelect), two);
//            mThreeList = two.getCampusList();
//            if (mThreeList == null || mThreeList.size() == 0) { // 选定二级。
//                setResultFinish(mOneList.get(mCurrentOneSelect), two, null);
//            } else {
//                mThreeListAdapter.notifyDataSetChanged(mThreeList);
//                mTabLayout.getTabAt(2).setText(two.getName());
//                mViewPager.setCurrentItem(2, true);
//            }
        }
    };

    /**
     * 区的item被点击。
     */
//    private OnCompatItemClickListener mDistrictItemClickListener = new OnCompatItemClickListener() {
//        @Override
//        public void onItemClick(View view, int position) {
//            setResultFinish(mOneList.get(mCurrentOneSelect), mTwoList.get(mCurrentTwoSelect), mThreeList.get(position));
//        }
//    };

    /**
     * 选中。
     */
//    private void setResultFinish(Province province, Province campus, Province district) {
//        ArrayList<Province> provinceArrayList = new ArrayList<>();
//        provinceArrayList.add(province);
//        if (campus != null)
//            provinceArrayList.add(campus);
//        if (district != null)
//            provinceArrayList.add(district);
//
//        Intent intent = new Intent();
//        intent.putParcelableArrayListExtra(KEY_OUTPUT_CAMPUS, provinceArrayList);
//        setResult(RESULT_OK, intent);
//        finish();
//    }

    private void setResultFinish(Province province, Campus campus) {
        ArrayList<Province> provinceArrayList = new ArrayList<>();

        Intent intent = new Intent();
        intent.putExtra("province", gson.toJson(province));
        intent.putExtra("campus", gson.toJson(campus));
        Log.e("CAMPUS", "setResultFinish: " + gson.toJson(province));
        Log.e("CAMPUS", "setResultFinish: " + gson.toJson(campus));
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                finish();
                break;
            }
        }
        return true;
    }
}
