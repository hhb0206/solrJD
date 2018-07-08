package com.hhb.jd.service.impl;

import com.ctc.wstx.util.StringUtil;
import com.hhb.jd.pojo.Product;
import com.hhb.jd.pojo.QueryVo;
import com.hhb.jd.pojo.Result;
import com.hhb.jd.service.SearchService;
import com.sun.org.apache.bcel.internal.generic.DCMPG;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : SearchServiceImpl
 * @Author : River
 * @Description 搜索业务接口实现类
 * @Date: Create in 19:27 2018/7/7
 * @Modified By:
 */
@Service
public class SearchServiceImpl implements SearchService {

    //注入HttpSolrServer
    @Autowired
    private HttpSolrServer server;


    /**
     * 搜索商品
     * @param queryVo 查询条件
     * @param page 当前页数
     * @param sort 排序规则
     * @return
     */

    public Result searchProduct(QueryVo queryVo, Integer page, String sort) {

        String catalog_name = queryVo.getCatalog_name();

        String queryString = queryVo.getQueryString();

        String price = queryVo.getPrice();


        //创建查询对象
        SolrQuery solrQuery = new SolrQuery();

        //设置查询关键词
        /**
         * 如果queryString为空,搜索全部,否则,指定指定的关键词
         */



        if(StringUtils.isNotBlank(queryString)){
            solrQuery.setQuery(queryString);
        }else{
            solrQuery.setQuery("*:*");
        }
        //设置默认搜索域
        solrQuery.set("df", "product_keywords");

        //设置过滤条件 start
        //商品分类名称
        if (StringUtils.isNotBlank(catalog_name)) {
             catalog_name = "product_catalog_name:" + catalog_name;
        }

        //商品价格 0-9
        if (StringUtils.isNotBlank(price)) {
            String[] arr = price.split("-");
           price = "product_price:[" + arr[0] + "TO" + arr[1] + "]";
        }
        solrQuery.setFilterQueries(catalog_name, price);

        //设置过滤条件 end

        //设置分页
        if (page == null) {
            page = 1;
        }

        int pageSize=10;

        solrQuery.setStart((page-1)*pageSize); //当前索引
        solrQuery.setRows(pageSize);

        //设置排序 1:升序, 0: 降序
        if("1".equals(sort)){
            solrQuery.setSort("product_price", SolrQuery.ORDER.asc);

        }else{
            solrQuery.setSort("product_price", SolrQuery.ORDER.desc);
        }


        //设置高亮显示
        solrQuery.setHighlight(true); //开启高亮显示
        //添加高亮显示的域
        solrQuery.addHighlightField("product_name");
        //设置高亮显示html
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");

        //执行搜索,返回QueryResponse


        QueryResponse queryResponse = null;


        try {
             queryResponse = server.query(solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        //从queryResponse中取出结果集
        SolrDocumentList results = queryResponse.getResults();
        //取出高亮数据
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

        //处理结果集
        //创建Result对象
        Result result = new Result();

        //设置当前约束
        result.setCurPage(page);

        //设置页数
        //总记录数
        int totals = (int) results.getNumFound();

        /**
         * 计算页数
         *
         */

        int pageCount = 0;

        if(totals%pageSize==0){
            pageCount = totals / pageSize;
        }else{
            pageCount = (totals / pageSize)+1;
        }

        result.setPageCount(pageCount);

        //设置总记录数
        result.setRecordCount(totals);

        //设置商品结果集合list
        List<Product> productList = new ArrayList<Product>();

        for (SolrDocument document : results) {
        //    获取商品id
            String pid = document.get("id").toString();

        //     商品名称
            String pname = "";

            List<String> list = highlighting.get(pid).get("product_name");
            if (list != null && list.size() > 0) {
                pname = list.get(0);
            }else{
                pname = document.get("product_name").toString();
            }

        //    商品价格
            String pprice = document.get("product_price").toString();

        //    商品图片
            String ppricture = document.get("product_picture").toString();

        //    创建商品对象,封装数据
            Product product = new Product();
            product.setName(pname);
            product.setPicture(ppricture);
            product.setPid(pid);
            product.setPrice(pprice);

            productList.add(product);
        }

        result.setProductList(productList);


        return result;
    }
}
