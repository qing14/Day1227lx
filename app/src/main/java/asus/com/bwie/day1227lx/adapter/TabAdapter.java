package asus.com.bwie.day1227lx.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import asus.com.bwie.day1227lx.fragment.PLFragment;
import asus.com.bwie.day1227lx.fragment.SPFragment;
import asus.com.bwie.day1227lx.fragment.XQFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private String[] tabname=new String[]{"商品","详情","评论"};


    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SPFragment();
            case 1:
                return new XQFragment();
            default:
                return new PLFragment();

        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabname[position];
    }

    @Override
    public int getCount() {
        return tabname.length;
    }
}
