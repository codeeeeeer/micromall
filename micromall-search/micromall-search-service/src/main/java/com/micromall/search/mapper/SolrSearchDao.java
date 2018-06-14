package com.micromall.search.mapper;

import com.micromall.commonPojo.SearchItemResult;
import com.micromall.commonPojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 〈the dao class of solr search〉
 *
 * @author LewJay
 * @create 2018/6/7 21:03
 */
@Repository
public class SolrSearchDao {

    @Autowired
    private SolrServer solrServer;

    public SearchResult search(SolrQuery solrQuery) throws Exception{
        SearchResult result = new SearchResult();
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList results = response.getResults();
        result.setRecordCount(results.getNumFound());

        //创建一个商品列表对象
        List<SearchItemResult> itemList = new ArrayList<>();
        //取商品列表
        //取高亮后的结果
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        for (SolrDocument solrDocument : results) {
            //取商品信息
            SearchItemResult searchItem = new SearchItemResult();
            searchItem.setCategory_name((String) solrDocument.get("item_category_name"));
            searchItem.setId((String) solrDocument.get("id"));
            searchItem.setImage((String) solrDocument.get("item_image"));
            searchItem.setPrice((long) solrDocument.get("item_price"));
            searchItem.setSell_point((String) solrDocument.get("item_sell_point"));
            //取高亮结果
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";
            if (list != null && list.size() > 0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            searchItem.setTitle(itemTitle);
            //添加到商品列表
            itemList.add(searchItem);
        }
        //把列表添加到返回结果对象中
        result.setItemList(itemList);

        return result;
    }
}
