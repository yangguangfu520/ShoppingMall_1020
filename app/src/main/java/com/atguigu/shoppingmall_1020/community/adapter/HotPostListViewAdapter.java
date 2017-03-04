package com.atguigu.shoppingmall_1020.community.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.community.bean.HotPostBean;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.atguigu.shoppingmall_1020.utils.DensityUtil;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：尚硅谷-杨光福 on 2017/3/4 09:52
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class HotPostListViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HotPostBean.ResultEntity> datas;

    public HotPostListViewAdapter(Context mContext, List<HotPostBean.ResultEntity> result) {
        this.mContext = mContext;
        this.datas = result;
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
            convertView = View.inflate(mContext, R.layout.item_hotpost_listview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        HotPostBean.ResultEntity entity = datas.get(position);
        //图像
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + entity.getAvatar()).into(viewHolder.ivNewPostAvatar);

        viewHolder.tvHotUsername.setText(entity.getUsername());
        //图像
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + entity.getFigure()).into(viewHolder.ivHotFigure);

        viewHolder.tvHotSaying.setText(entity.getSaying());
        viewHolder.tvHotLikes.setText(entity.getLikes());
        viewHolder.tvHotComments.setText(entity.getComments());

        //设置置顶
        String is_top = entity.getIs_top();
        if ("1".equals(is_top)) {

            //显示置顶
            TextView hotTextView = new TextView(mContext);
            hotTextView.setText("置顶");
            hotTextView.setGravity(Gravity.CENTER);

            //白色
            hotTextView.setTextColor(Color.WHITE);

            //设置背景
            hotTextView.setBackgroundResource(R.drawable.is_top_shape);
            //padding都是5
            hotTextView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            //先移除
            viewHolder.llHotPost.removeAllViews();


            //参数
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            viewHolder.llHotPost.addView(hotTextView,textViewLp);

        }


        //热门
        String is_hot = entity.getIs_hot();
        if ("1".equals(is_hot)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(mContext);
            textViewLp.setMargins(DensityUtil.dip2px(mContext, 5), 0, DensityUtil.dip2px(mContext, 5), 0);
            textView.setText("热门");
            textView.setGravity(Gravity.CENTER);
            //文字为白色
            textView.setTextColor(Color.WHITE);
            //设置padding
            textView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            //设置橙色北京
            textView.setBackgroundResource(R.drawable.is_hot_shape);
            viewHolder.llHotPost.addView(textView, textViewLp);
        }


        //精华
        String is_essence = entity.getIs_essence();
        if ("1".equals(is_essence)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //距离右边
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(mContext, 5), 0);
            TextView textView = new TextView(mContext);
            textView.setText("精华");
            textView.setGravity(Gravity.CENTER);
            //文字白色
            textView.setTextColor(Color.WHITE);
            textView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            //设置背景为亮橙色
            textView.setBackgroundResource(R.drawable.is_essence_shape);
            viewHolder.llHotPost.addView(textView, textViewLp);
        }


        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.tv_hot_username)
        TextView tvHotUsername;
        @InjectView(R.id.tv_hot_addtime)
        TextView tvHotAddtime;
        @InjectView(R.id.rl)
        RelativeLayout rl;
        @InjectView(R.id.iv_new_post_avatar)
        ImageView ivNewPostAvatar;
        @InjectView(R.id.iv_hot_figure)
        ImageView ivHotFigure;
        @InjectView(R.id.ll_hot_post)
        LinearLayout llHotPost;
        @InjectView(R.id.tv_hot_saying)
        TextView tvHotSaying;
        @InjectView(R.id.tv_hot_likes)
        TextView tvHotLikes;
        @InjectView(R.id.tv_hot_comments)
        TextView tvHotComments;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
