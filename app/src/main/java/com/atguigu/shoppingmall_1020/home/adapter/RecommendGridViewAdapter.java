package com.atguigu.shoppingmall_1020.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.home.bean.HomeBean;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：尚硅谷-杨光福 on 2017/2/25 10:36
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class RecommendGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HomeBean.ResultEntity.RecommendInfoEntity> datas;

    public RecommendGridViewAdapter(Context mContext, List<HomeBean.ResultEntity.RecommendInfoEntity> recommend_info) {
        this.mContext = mContext;
        this.datas = recommend_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        HomeBean.ResultEntity.RecommendInfoEntity infoEntity = datas.get(position);
        //绑定数据
        viewHolder.tvName.setText(infoEntity.getName());
        viewHolder.tvPrice.setText("￥"+infoEntity.getCover_price());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+infoEntity.getFigure()).into(viewHolder.ivRecommend);


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_recommend)
        ImageView ivRecommend;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
