package com.qp.inst_life.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
  List<Fragment> fragments=new ArrayList<Fragment>();



    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
            this.fragments=fragments;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
        }



    @Override
    public int getCount() {
        return fragments.size();
    }
}
