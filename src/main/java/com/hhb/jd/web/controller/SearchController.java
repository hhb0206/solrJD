package com.hhb.jd.web.controller;

import com.hhb.jd.pojo.QueryVo;
import com.hhb.jd.pojo.Result;
import com.hhb.jd.service.SearchService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName : SearchController
 * @Author : River
 * @Description (这里用一句话描述这个类的作用)
 * @Date: Create in 18:24 2018/7/7
 * @Modified By:
 */
@Controller
public class SearchController {


    //注入service
    @Autowired
    private SearchService searchService;

    /**
     * 搜索商品
     */

    @RequestMapping("/list.action")
    public String list(Model model, QueryVo queryVo,Integer page,String sort){

        //搜索商品
        Result result = searchService.searchProduct(queryVo, page, sort);

        //响应搜索结果数据
        model.addAttribute("result", result);

        //参数数据回显
        model.addAttribute("queryString", queryVo.getQueryString());
        model.addAttribute("catalog_name", queryVo.getCatalog_name());
        model.addAttribute("price", queryVo.getPrice());
        model.addAttribute("sort", sort);


        return "product_list";
    }

}
