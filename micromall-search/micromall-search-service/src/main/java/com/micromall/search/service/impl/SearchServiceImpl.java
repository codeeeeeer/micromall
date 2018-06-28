package com.micromall.search.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micromall.commonPojo.MicromallResult;
import com.micromall.commonPojo.SearchItemResult;
import com.micromall.commonPojo.SearchResult;
import com.micromall.search.mapper.SearchMapper;
import com.micromall.search.mapper.SolrSearchDao;
import com.micromall.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 〈the implementation of searching service〉
 *
 * @author LewJay
 * @create 2018/6/5 22:24
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private SolrServer solrServer;
    @Value("${default-field}")
    private String DEFAULT_FIELD;
    @Autowired
    private SolrSearchDao solrSearchDao;

    @Override
    public MicromallResult findItems() {
        try {
            int pageSize = 50;
            int page = 0;
            PageHelper.startPage(page, pageSize);
            PageInfo<SearchItemResult> info = null;
            do {
                List<SearchItemResult> searchItemResults = searchMapper.selectItems();
                info = new PageInfo<>(searchItemResults);
                for (SearchItemResult itemResult:
                        searchItemResults) {
                    SolrInputDocument document = new SolrInputDocument();
                    document.addField("id", itemResult.getId());
                    document.addField("item_title", itemResult.getTitle());
                    document.addField("item_sell_point", itemResult.getSell_point());
                    document.addField("item_price", itemResult.getPrice());
                    document.addField("item_image", itemResult.getImage());
                    document.addField("item_category_name", itemResult.getCategory_name());

                    solrServer.add(document);
                }
            }while (info != null && ++page < info.getPages());
            solrServer.commit();
            return MicromallResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MicromallResult.build(500, "商品导入失败！");
        }
    }

    @Override
    public SearchResult findSearchIndex(String keyWord, int pageNum, int pageSize) throws Exception{
        SolrQuery query = new SolrQuery();
        query.setQuery(keyWord);
        //设置分页条件
        query.setStart((pageNum - 1) * pageSize);
        //设置rows
        query.setRows(pageSize);
        //设置默认搜索域
        query.set("df", DEFAULT_FIELD);
        //设置高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        //执行查询
        SearchResult searchResult = solrSearchDao.search(query);
        //计算总页数
        long recourdCount = searchResult.getRecordCount();
        int pages = (int)recourdCount / pageSize;
        if (recourdCount % pageSize > 0) pages++;
        //设置到返回结果
        searchResult.setTotalPages(pages);

        return searchResult;
    }

    @Override
    public MicromallResult addIndexById(Long itemId) throws Exception {
        SearchItemResult itemByIndex = searchMapper.getItemByIndex(itemId);
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", itemByIndex.getId());
        document.addField("item_title", itemByIndex.getTitle());
        document.addField("item_sell_point", itemByIndex.getSell_point());
        document.addField("item_price", itemByIndex.getPrice());
        document.addField("item_image", itemByIndex.getImage());
        document.addField("item_category_name", itemByIndex.getCategory_name());

        solrServer.add(document);
        solrServer.commit();
        return MicromallResult.ok("商品索引添加成功！");
    }
}
