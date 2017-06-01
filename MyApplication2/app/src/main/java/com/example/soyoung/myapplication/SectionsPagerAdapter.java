package com.example.soyoung.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by soyoung on 2017-02-02.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    //private int tabCount;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public PlaceholderFragment getItem(int position) {
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "밀도";
            case 1:
                return "유속";
            case 2:
                return "수분량";
            case 3:
                return "i 퍼센트";
        }
        return null;
    }
}

