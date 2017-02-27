package com.atguigu.shoppingmall_1020.shoppingcart.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.base.BaseFragment;
import com.atguigu.shoppingmall_1020.home.bean.GoodsBean;
import com.atguigu.shoppingmall_1020.shoppingcart.utils.CartStorage;

/**
 * 作者：尚硅谷-杨光福 on 2017/2/22 11:36
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：购物车Fragment
 */
public class ShoppingCartFragment extends BaseFragment {
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


        Log.e("TAG","购物车的数据被初始化了...");
        textView.setText("购物车内容");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsBean goodsBean  = new GoodsBean();
                goodsBean.setProduct_id("10659");
                goodsBean.setNumber(3);
                CartStorage.getInstance(mContext).updateData(goodsBean);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        for (int i=0;i<CartStorage.getInstance(mContext).getAllData().size();i++){
            Log.e("TAG",""+CartStorage.getInstance(mContext).getAllData().get(i).toString());
        }
    }
}
