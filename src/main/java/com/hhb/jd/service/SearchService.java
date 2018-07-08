package com.hhb.jd.service;

import com.hhb.jd.pojo.QueryVo;
import com.hhb.jd.pojo.Result;

import javax.management.Query;

/**
 * @ClassName : SearchService
 * @Author : River
 * @Description  搜索业务接口
 * @Date: Create in 19:14 2018/7/7
 * @Modified By:
 */

public interface SearchService {


    /**
     * 搜索商品
     */

    Result searchProduct(QueryVo queryVo, Integer page, String sort);



}
