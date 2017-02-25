package com.atguigu.shoppingmall_1020.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.home.bean.HomeBean;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：尚硅谷-杨光福 on 2017/2/25 09:47
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {


    private final Context mContext;
    private final List<HomeBean.ResultEntity.SeckillInfoEntity.ListEntity> datas;

    public SeckillRecyclerViewAdapter(Context mContext, HomeBean.ResultEntity.SeckillInfoEntity seckill_info) {
        this.mContext = mContext;
        this.datas = seckill_info.getList();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_seckill, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //1.根据位置得到数据
        HomeBean.ResultEntity.SeckillInfoEntity.ListEntity listEntity = datas.get(position);
        //2.绑定数据
        holder.tvCoverPrice.setText("￥"+listEntity.getCover_price());
        holder.tvOriginPrice.setText("￥"+listEntity.getOrigin_price());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listEntity.getFigure()).into(holder.ivFigure);

    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


   static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_figure)
        ImageView ivFigure;
        @InjectView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @InjectView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
