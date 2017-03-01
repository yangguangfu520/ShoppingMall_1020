package com.atguigu.shoppingmall_1020.home.bean;

/**
 * 作者：尚硅谷-杨光福 on 2017/3/1 11:57
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class H5Bean {

    /**
     * option : 1
     * type : 1
     * value : {"product_id":10290}
     */

    private int option;
    private int type;
    private ValueEntity value;

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ValueEntity getValue() {
        return value;
    }

    public void setValue(ValueEntity value) {
        this.value = value;
    }

    public static class ValueEntity {
        /**
         * product_id : 10290
         */

        private int product_id;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }
    }
}
