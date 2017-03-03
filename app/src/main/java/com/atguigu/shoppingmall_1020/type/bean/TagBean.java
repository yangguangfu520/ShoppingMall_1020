package com.atguigu.shoppingmall_1020.type.bean;

import java.util.List;

/**
 * 作者：尚硅谷-杨光福 on 2017/3/3 14:13
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class TagBean {


    /**
     * code : 200
     * msg : 请求成功
     * result : [{"name":"尚硅谷","tag_id":"1"}]
     */

    private int code;
    private String msg;
    private List<ResultEntity> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public static class ResultEntity {
        /**
         * name : 尚硅谷
         * tag_id : 1
         */

        private String name;
        private String tag_id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag_id() {
            return tag_id;
        }

        public void setTag_id(String tag_id) {
            this.tag_id = tag_id;
        }

        @Override
        public String toString() {
            return "ResultEntity{" +
                    "name='" + name + '\'' +
                    ", tag_id='" + tag_id + '\'' +
                    '}';
        }
    }
}
