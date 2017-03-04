package com.atguigu.shoppingmall_1020.utils;

/**
 * 作者：尚硅谷-杨光福 on 2017/2/23 09:40
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：常量地址
 */
public class Constants {

    //public static String BASE_URL = "http://192.168.51.104:8080/atguigu";
  //    public static String BASE_URL = "http://192.168.191.1:8080/atguigu";
    public static String BASE_URL = "http://10.0.2.2:8080/atguigu";
//    public static String BASE_URL = "http://182.92.5.3:8081/android/resources";


    private static final String BASE_URL_JSON = BASE_URL+"/json/";
    /**
     * 主页面的路径
     */
    public static String HOME_URL  = BASE_URL_JSON+"HOME_URL.json";
    /**
     * 图片的基本路径
     */
    public static String BASE_URL_IMAGE  = BASE_URL+"/img";



    //小裙子
    public static final String SKIRT_URL = BASE_URL_JSON + "SKIRT_URL.json";
    //上衣
    public static final String JACKET_URL = BASE_URL_JSON + "JACKET_URL.json";
    //下装(裤子)
    public static final String PANTS_URL = BASE_URL_JSON + "PANTS_URL.json";
    //外套
    public static final String OVERCOAT_URL = BASE_URL_JSON + "OVERCOAT_URL.json";
    //配件
    public static final String ACCESSORY_URL = BASE_URL_JSON + "ACCESSORY_URL.json";
    //包包
    public static final String BAG_URL = BASE_URL_JSON + "BAG_URL.json";
    //装扮
    public static final String DRESS_UP_URL = BASE_URL_JSON + "DRESS_UP_URL.json";
    //居家宅品
    public static final String HOME_PRODUCTS_URL = BASE_URL_JSON + "HOME_PRODUCTS_URL.json";
    //办公文具
    public static final String STATIONERY_URL = BASE_URL_JSON + "STATIONERY_URL.json";
    //数码周边
    public static final String DIGIT_URL = BASE_URL_JSON +  "DIGIT_URL.json";
    //游戏专区
    public static final String GAME_URL = BASE_URL_JSON + "GAME_URL.json";



    //分类Fragment里面的标签Fragment页面数据
    public static final String TAG_URL = BASE_URL_JSON + "TAG_URL.json";



    //发现页面-的-新特路径
    public static final String NEW_POST_URL = BASE_URL_JSON + "NEW_POST_URL.json";
    public static final String HOT_POST_URL = BASE_URL_JSON + "HOT_POST_URL.json";




    //服饰
    public static final String CLOSE_STORE = BASE_URL_JSON + "CLOSE_STORE.json";
    //游戏
    public static final String GAME_STORE = BASE_URL_JSON + "GAME_STORE.json";
    //动漫
    public static final String COMIC_STORE = BASE_URL_JSON + "COMIC_STORE.json";
    //cosplay
    public static final String COSPLAY_STORE = BASE_URL_JSON + "COSPLAY_STORE.json";
    //古风
    public static final String GUFENG_STORE = BASE_URL_JSON + "GUFENG_STORE.json";
    //漫展
    public static final String STICK_STORE = BASE_URL_JSON + "STICK_STORE.json";
    //文具
    public static final String WENJU_STORE = BASE_URL_JSON + "WENJU_STORE.json";
    //零食
    public static final String FOOD_STORE = BASE_URL_JSON + "FOOD_STORE.json";
    //首饰厂
    public static final String SHOUSHI_STORE = BASE_URL_JSON + "SHOUSHI_STORE.json";

}
