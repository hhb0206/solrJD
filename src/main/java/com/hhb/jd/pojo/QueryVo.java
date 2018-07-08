package com.hhb.jd.pojo;

/**
 * @ClassName : QueryVo
 * @Author : River
 * @Description 搜索参数
 * @Date: Create in 19:18 2018/7/7
 * @Modified By:
 */

public class QueryVo {

    private String queryString ; //搜索栏内容

    private String catalog_name; //商品类名

    private String price; //商品价格

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getCatalog_name() {
        return catalog_name;
    }

    public void setCatalog_name(String catalog_name) {
        this.catalog_name = catalog_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
