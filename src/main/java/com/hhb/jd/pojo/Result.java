package com.hhb.jd.pojo;

import java.util.List;

/**
 * @ClassName : Result
 * @Author : River
 * @Description 搜索结果实体类对象
 * @Date: Create in 19:24 2018/7/7
 * @Modified By:
 */

public class Result {

    private Integer curPage; //当前页

    private Integer pageCount; //页数

    private Integer recordCount; //记录数

    private List<Product> productList;

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
