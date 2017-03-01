package com.atguigu.shoppingmall_1020.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall_1020.R;
import com.atguigu.shoppingmall_1020.home.bean.GoodsBean;
import com.atguigu.shoppingmall_1020.shoppingcart.utils.CartStorage;
import com.atguigu.shoppingmall_1020.shoppingcart.view.AddSubView;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：尚硅谷-杨光福 on 2017/2/28 10:46
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHoler> {

    private final Context mContext;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox checkboxDeleteAll;

    public ShoppingCartAdapter(Context mContext, List<GoodsBean> list, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox checkboxDeleteAll) {
        this.mContext = mContext;
        this.datas = list;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.checkboxDeleteAll = checkboxDeleteAll;
        showTotalPrice();


    }

    public void showTotalPrice() {
        //显示总价格
        tvShopcartTotal.setText("合计:"+getTotalPrice());
    }

    /**
     * 返回总价格
     * @return
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        if(datas != null && datas.size() >0){
            for (int i=0;i<datas.size();i++){
                //遍历出来
                GoodsBean goodsBean = datas.get(i);
                //必须是选择状态
                if(goodsBean.isChecked()){
                    totalPrice += Double.parseDouble(goodsBean.getCover_price()) * goodsBean.getNumber();
                }
            }
        }
        return totalPrice;
    }

    /**
     * 创建ViewHoler--创建视图
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHoler(View.inflate(mContext, R.layout.item_shop_cart, null));
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, int position) {
        //1.先得到数据
        final GoodsBean goodsBean = datas.get(position);
        //2.绑定数据
        holder.cbGov.setChecked(goodsBean.isChecked());
        //图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+goodsBean.getFigure()).into(holder.ivGov);
        //设置名称
        holder.tvDescGov.setText(goodsBean.getName());
        //设置价格
        holder.tvPriceGov.setText("￥"+goodsBean.getCover_price());

        //设置数量
        holder.addSubView.setValue(goodsBean.getNumber());

        holder.addSubView.setMinValue(1);
        //设置库存-来自服务器-
        holder.addSubView.setMaxValue(100);

        holder.addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            //回传过来的值
            @Override
            public void onNumberChanger(int value) {
                //1.回调数量
                goodsBean.setNumber(value);

                //2.持久化
                CartStorage.getInstance(mContext).updateData(goodsBean);

                //3.显示总价格
                showTotalPrice();

            }
        });



    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 校验是否全选
     */
    public void checkAll() {
        if(datas != null && datas.size() >0){

            int number = 0;
            for (int i=0;i<datas.size();i++){
                GoodsBean goodsBean = datas.get(i);
                if(!goodsBean.isChecked()){
                    //只要有一个不勾选
                    checkboxAll.setChecked(false);
                    checkboxDeleteAll.setChecked(false);
                }else{
                    //勾选
                    number ++;
                }
            }

            //勾选的数量和购物车的条目相同就全选
            if(datas.size()==number){
                checkboxAll.setChecked(true);
                checkboxDeleteAll.setChecked(true);
            }

        }else{
            //没有数据
            checkboxAll.setChecked(false);
            checkboxDeleteAll.setChecked(false);
        }
    }

    /**
     * 删除数据
     */
    public void deleteData() {
//        if(datas != null && datas.size() >0){
//            for (int i=0;i<datas.size();i++){
//                GoodsBean goodsBean = datas.get(i);
//                //当勾选的才删除
//                if(goodsBean.isChecked()){
//                    //1.内存中删除
//                    datas.remove(goodsBean);
//                    //2.本地也好保持
//                    CartStorage.getInstance(mContext).deleteData(goodsBean);
//                    //刷新数据
//                    notifyItemRemoved(i);
//                    i--;
//                }
//            }
//        }


        if(datas != null && datas.size() >0){
            for (Iterator iterator = datas.iterator();iterator.hasNext();){
                GoodsBean goodsBean = (GoodsBean) iterator.next();
                //是否选中，选中才删除哦
                if(goodsBean.isChecked()){

                    int position  = datas.indexOf(goodsBean);
                    //从内存中移除
                    iterator.remove();
                    //本地也要同步
                    CartStorage.getInstance(mContext).deleteData(goodsBean);

                    //刷新页面
                    notifyItemRemoved(position);

                }
            }
        }

    }


    public void checkAll_none(boolean isChecked) {
        if(datas != null && datas.size() >0){
            for (int i=0;i<datas.size();i++){
                //把购物车里面的Bean对象都设置勾选或者非勾选
                GoodsBean goodsBean = datas.get(i);
                //设置是否勾选状态
                goodsBean.setChecked(isChecked);
                //设置CheckBox的状态
                checkboxAll.setChecked(isChecked);
                checkboxDeleteAll.setChecked(isChecked);

                //更新视图
                notifyItemChanged(i);
            }

        }
    }


    class MyViewHoler  extends RecyclerView.ViewHolder{
        @InjectView(R.id.cb_gov)
        CheckBox cbGov;
        @InjectView(R.id.iv_gov)
        ImageView ivGov;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @InjectView(R.id.addSubView)
        AddSubView addSubView;

         MyViewHoler(View view) {
            super(view);
            ButterKnife.inject(this, view);
             //在这个地方设置item的点击事件
             view.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     //回调接口
                     if(itemClickListener != null){
                         itemClickListener.onItemClickListener(v,getLayoutPosition());
                     }
                 }
             });
        }
    }




    //回调点击事件的监听
    private OnItemClickListener itemClickListener;

    /**
     * 点击item的监听
     */
    public interface OnItemClickListener {
       public void onItemClickListener(View view, int position);
    }

    /**
     * 设置item的监听
     * @param l
     */
    public void setOnItemClickListener(OnItemClickListener l) {
        this.itemClickListener = l;
    }

}
