package com.atguigu.shoppingmall_1020.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 作者：尚硅谷-杨光福 on 2017/2/25 11:04
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class MyGridView extends GridView {
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
