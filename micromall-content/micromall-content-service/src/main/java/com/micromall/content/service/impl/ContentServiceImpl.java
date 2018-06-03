package com.micromall.content.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micromall.commonPojo.EasyUIDataGridResult;
import com.micromall.commonPojo.MicromallResult;
import com.micromall.commonUtils.JsonUtils;
import com.micromall.content.service.ContentService;
import com.micromall.mapper.TbContentMapper;
import com.micromall.pojo.TbContent;
import com.micromall.pojo.TbContentExample;
import com.micromall.redis.JedisClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 〈the implementation of content service〉
 *
 * @author LewJay
 * @create 2018/5/31 20:56
 */
@Service("contentService")
@Slf4j
public class ContentServiceImpl implements ContentService {
    @Autowired
    private JedisClient jedisClient;

    @Value("${key.hash.content.list}")
    private String contentListKey;

    @Value("${key.hash.content.byCategoryAndPageNumAndPageSize}")
    private String contentInPage;

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public EasyUIDataGridResult<TbContent> findContentList(Long categoryId, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> contents = contentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(contents);
        EasyUIDataGridResult<TbContent> result = new EasyUIDataGridResult<>();
        result.setRows(contents);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public MicromallResult saveContent(TbContent content) {
        Date currentDate = new Date();
        content.setUpdated(currentDate);
        content.setCreated(currentDate);
        contentMapper.insert(content);

        try{
            jedisClient.hdel(contentListKey, content.getCategoryId().toString());
        }catch (Exception e){
            log.warn(e.getStackTrace().toString());
        }

        return MicromallResult.ok();
    }

    @Override
    public List<TbContent> findContentsById(Long categoryId) {
        //get contents from cache first
        try {
            String contentListWithCategoryInRedis =
                    jedisClient.hget(contentListKey, categoryId + "");
            if (StringUtils.isNotEmpty(contentListWithCategoryInRedis)){
                return JsonUtils.jsonToList(
                        contentListWithCategoryInRedis, TbContent.class);
            }
        }catch (Exception e){
            log.warn(e.getStackTrace().toString());
        }

        //no content in cache
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> contentsInDB =
                contentMapper.selectByExample(example);

        //put content into cache
        try {
            jedisClient.hset(contentListKey, categoryId + "",
                    JsonUtils.objectToJson(contentsInDB));
        }catch (Exception e){
            log.warn(e.getStackTrace().toString());
        }
        return contentsInDB;
    }
}
