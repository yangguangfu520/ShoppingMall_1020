package com.atguigu.shoppingmall_1020.community.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.shoppingmall_1020.base.BaseFragment;

import java.util.ArrayList;

/**
 * 作者：尚硅谷-杨光福 on 2017/3/4 09:26
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class CommunityViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<BaseFragment> fragments;

    public CommunityViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
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
