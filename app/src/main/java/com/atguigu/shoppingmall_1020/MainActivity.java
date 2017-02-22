package com.atguigu.shoppingmall_1020;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.atguigu.shoppingmall_1020.base.BaseFragment;
import com.atguigu.shoppingmall_1020.community.fragment.CommunityFragment;
import com.atguigu.shoppingmall_1020.home.fragment.HomeFragment;
import com.atguigu.shoppingmall_1020.shoppingcart.fragment.ShoppingCartFragment;
import com.atguigu.shoppingmall_1020.type.fragment.TypeFragment;
import com.atguigu.shoppingmall_1020.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    /**
     * 装5个Fragment的集合
     */
    private ArrayList<BaseFragment> fragments;
    /**
     * Fragment对应的位置
     */
    private int position;

    /**
     * 刚才被显示的fragment
     */
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);


        //初始化Fragment
        initFragment();

        //监听RadioGroup状态改变，并且切换到不同的Fragment
        initListener();
    }

    private void initListener() {

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                }

                //根据位置切换到对应的Fragment
                Fragment currentFragment = fragments.get(position);
                switchFragment (currentFragment);
            }
        });

        //默认选中首页--注意默认选中要放在后边
        rgMain.check(R.id.rb_home);
    }

    private void switchFragment(Fragment currentFragment) {
        //切换的不是同一个页面
        if(tempFragment != currentFragment){

            //得到FragmentMager
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //如果没有添加就添加
            if(!currentFragment.isAdded()){
                //缓存的隐藏
                if(tempFragment != null){
                    ft.hide(tempFragment);
                }

                //添加
                ft.add(R.id.fl_main,currentFragment);

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

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());//添加主页Fragment-0，
        fragments.add(new TypeFragment());//添加分类Fragment--1
        fragments.add(new CommunityFragment());//添加发现Fragment--2
        fragments.add(new ShoppingCartFragment());//添加购物车Fragment
        fragments.add(new UserFragment());//添加用户Fragment--4
    }
}
