package com.atguigu.shoppingmall_1020.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.app.GoodsInfoActivity;
import com.atguigu.shoppingmall_1020.home.adapter.GoodsListAdapter;
import com.atguigu.shoppingmall_1020.home.adapter.HomeAdapter;
import com.atguigu.shoppingmall_1020.home.bean.GoodsBean;
import com.atguigu.shoppingmall_1020.home.bean.TypeListBean;
import com.atguigu.shoppingmall_1020.home.view.SpaceItemDecoration;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class GoodsListActivity extends AppCompatActivity {

    @InjectView(R.id.ib_goods_list_back)
    ImageButton ibGoodsListBack;
    @InjectView(R.id.tv_goods_list_search)
    TextView tvGoodsListSearch;
    @InjectView(R.id.ib_goods_list_home)
    ImageButton ibGoodsListHome;
    @InjectView(R.id.tv_goods_list_sort)
    TextView tvGoodsListSort;
    @InjectView(R.id.tv_goods_list_price)
    TextView tvGoodsListPrice;
    @InjectView(R.id.iv_goods_list_arrow)
    ImageView ivGoodsListArrow;
    @InjectView(R.id.ll_goods_list_price)
    LinearLayout llGoodsListPrice;
    @InjectView(R.id.tv_goods_list_select)
    TextView tvGoodsListSelect;
    @InjectView(R.id.ll_goods_list_head)
    LinearLayout llGoodsListHead;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.dl_left)
    DrawerLayout dlLeft;

    private int position;
    /**
     * 请求网络
     */
    private String[] urls = new String[]{
            Constants.CLOSE_STORE,
            Constants.GAME_STORE,
            Constants.COMIC_STORE,
            Constants.COSPLAY_STORE,
            Constants.GUFENG_STORE,
            Constants.STICK_STORE,
            Constants.WENJU_STORE,
            Constants.FOOD_STORE,
            Constants.SHOUSHI_STORE,
    };
    private GoodsListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.inject(this);
        getData();
    }

    private void getData() {
        position = getIntent().getIntExtra("position", 0);
//        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        getDataFromNet(urls[position]);
    }

    @OnClick({R.id.ib_goods_list_back, R.id.tv_goods_list_search, R.id.ib_goods_list_home, R.id.tv_goods_list_sort, R.id.tv_goods_list_price, R.id.tv_goods_list_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_goods_list_back:
                finish();
                break;
            case R.id.tv_goods_list_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_goods_list_home:
                Toast.makeText(this, "主页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_sort:
                Toast.makeText(this, "综合排序", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_price:
                Toast.makeText(this, "价格", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_select:
                Toast.makeText(this, "筛选", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG","联网失败了"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG","GoodsListActivity的数据联网成功了==");
                        processData(response);

                    }
                });
    }

    private void processData(String json) {
        TypeListBean bean = JSON.parseObject(json,TypeListBean.class);
//        Log.e("TAG",bean.getResult().getPage_data().get(0).getName());
        List<TypeListBean.ResultEntity.PageDataEntity> datas =  bean.getResult().getPage_data();
        if(datas != null && datas.size() >0){
            //有数据-设置适配器

            adapter = new GoodsListAdapter(this,datas);
            recyclerview.setAdapter(adapter);

            //设置布局管理器
            recyclerview.setLayoutManager(new GridLayoutManager(this,2));

            recyclerview.addItemDecoration(new SpaceItemDecoration(10));


            adapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(TypeListBean.ResultEntity.PageDataEntity data) {

                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setProduct_id(data.getProduct_id());
                    goodsBean.setName(data.getName());
                    goodsBean.setCover_price(data.getCover_price());
                    goodsBean.setFigure(data.getFigure());

                    Intent intent = new Intent(GoodsListActivity.this, GoodsInfoActivity.class);
                    intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                    startActivity(intent);

                }
            });

        }


    }
}
