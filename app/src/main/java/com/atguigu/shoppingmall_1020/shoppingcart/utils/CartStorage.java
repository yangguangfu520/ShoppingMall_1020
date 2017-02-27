package com.atguigu.shoppingmall_1020.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.shoppingmall_1020.home.bean.GoodsBean;
import com.atguigu.shoppingmall_1020.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：尚硅谷-杨光福 on 2017/2/27 10:42
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class CartStorage {

    public static final String JSON_CART = "json_cart";
    private static CartStorage instace;
    private final Context mContext;
    //SparseArray替代HashMap
    private SparseArray<GoodsBean> sparseArray;

    private CartStorage(Context context) {
        this.mContext = context;
        sparseArray = new SparseArray();
        //从本地获取数据
        listToSparseArray();
    }

    /**
     * 把List的数据转换成SparseArray
     */
    private void listToSparseArray() {

        List<GoodsBean> beanList = getAllData();
        for (int i = 0; i < beanList.size(); i++) {
            GoodsBean goodsBean = beanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        }

    }

    /**
     * 得到所有的数据
     *
     * @return
     */
    public List<GoodsBean> getAllData() {

        return getLocalData();
    }

    /**
     * 得到本地缓存的数据
     *
     * @return
     */
    private List<GoodsBean> getLocalData() {
        List<GoodsBean> goodsBeens = new ArrayList<>();

        //从本地获取数据
        String json = CacheUtils.getString(mContext, JSON_CART);//保持的json数据
        if (!TextUtils.isEmpty(json)) {
            //把它转化成列表
            goodsBeens = new Gson().fromJson(json, new TypeToken<List<GoodsBean>>() {
            }.getType());
        }
        //把json数据解析成List数据

        return goodsBeens;
    }


    /**
     * 懒汉模式
     *
     * @param context
     * @return
     */
    public static CartStorage getInstance(Context context) {
        if (instace == null) {
            synchronized (CartStorage.class) {
                if (instace == null) {
                    instace = new CartStorage(context);
                }
            }
        }
        return instace;

    }
}
