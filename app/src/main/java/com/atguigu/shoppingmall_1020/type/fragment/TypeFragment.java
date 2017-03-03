package com.atguigu.shoppingmall_1020.type.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.atguigu.shoppingmall_1020.MainActivity;
import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：尚硅谷-杨光福 on 2017/2/22 11:36
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：分类Fragment
 */
public class TypeFragment extends BaseFragment {
    @InjectView(R.id.tl_1)
    SegmentTabLayout tl1;
    @InjectView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    @InjectView(R.id.fl_type)
    FrameLayout flType;
    private String[] titles = {"分类","标签"};

    private ArrayList<BaseFragment> fragments;
    /**
     * 刚才被显示的fragment
     */
    private Fragment tempFragment;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.inject(this, view);
        return view;
    }

    /**
     * 1.把数据绑定到控件上的时候，重新该方法
     * 2.联网请求，把得到的数据绑定到视图上
     */
    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "分类的数据被初始化了...");
        initListener();
        initFragment();
        switchFragment(fragments.get(0));
    }

    private void initListener() {
        tl1.setTabData(titles);
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                switchFragment(fragments.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new TagFragment());
    }

    private void switchFragment(Fragment currentFragment) {
        //切换的不是同一个页面
        if(tempFragment != currentFragment){

            MainActivity activity = (MainActivity) mContext;
            //得到FragmentMager
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            //如果没有添加就添加
            if(!currentFragment.isAdded()){
                //缓存的隐藏
                if(tempFragment != null){
                    ft.hide(tempFragment);
                }

                //添加
                ft.add(R.id.fl_type,currentFragment);

            }else{
                //缓存的隐藏
                if(tempFragment != null){
                    ft.hide(tempFragment);
                }

                //显示
                ft.show(currentFragment);
            }

            //事务提交
            ft.commit();


            //把当前的赋值成缓存的
            tempFragment = currentFragment;

        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.iv_type_search)
    public void onClick() {
    }
}
