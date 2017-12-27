package com.ninggc.trade.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author Ning
 * Created by Ning on 7/31/2017 0031.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragments;
    List<String> mTitles;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> mTitles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = mTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
