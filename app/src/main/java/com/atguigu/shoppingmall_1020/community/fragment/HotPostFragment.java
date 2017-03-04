package com.atguigu.shoppingmall_1020.community.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.base.BaseFragment;

/**
 * 作者：尚硅谷-杨光福 on 2017/3/4 09:22
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：热帖Fragment
 */
public class HotPostFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    /**
     * 1.把数据绑定到控件上的时候，重新该方法
     * 2.联网请求，把得到的数据绑定到视图上
     */
    @Override
    public void initData() {
        super.initData();
        textView.setText("热帖Fragment");
    }
}
