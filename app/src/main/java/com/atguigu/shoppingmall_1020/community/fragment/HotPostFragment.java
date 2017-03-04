package com.atguigu.shoppingmall_1020.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.base.BaseFragment;
import com.atguigu.shoppingmall_1020.community.adapter.HotPostListViewAdapter;
import com.atguigu.shoppingmall_1020.community.adapter.NewPostListViewAdapter;
import com.atguigu.shoppingmall_1020.community.bean.HotPostBean;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * 作者：尚硅谷-杨光福 on 2017/3/4 09:22
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：热帖Fragment
 */
public class HotPostFragment extends BaseFragment {
    @InjectView(R.id.lv_hot_post)
    ListView lvHotPost;
    private HotPostListViewAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_hot_post, null);
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
        getDataFromNet();

    }


    public void getDataFromNet() {
        OkHttpUtils
                .get()
                //联网地址
                .url(Constants.HOT_POST_URL)
                .id(100)//http,
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG","联网失败=="+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG","热帖联网成功==");
                        processData(response);

                    }
                });
    }

    private void processData(String json) {

        HotPostBean bean = JSON.parseObject(json,HotPostBean.class);
//        Toast.makeText(mContext, ""+bean.getResult().get(0).getSaying(), Toast.LENGTH_SHORT).show();
        List<HotPostBean.ResultEntity> result = bean.getResult();
        if(result != null && result.size() >0){

            //设置适配器
            adapter = new HotPostListViewAdapter(mContext,result);
            lvHotPost.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
