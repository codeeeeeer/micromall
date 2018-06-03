package com.micromall.content.service;

import com.micromall.commonPojo.EasyUICatTreeNode;
import com.micromall.commonPojo.EasyUIDataGridResult;
import com.micromall.commonPojo.MicromallResult;
import com.micromall.pojo.TbContent;

import java.util.List;

/**
 * 〈the interface of content category〉
 *
 * @author LewJay
 * @create 2018/5/31 20:15
 */
public interface ContentService {
    /**
     * search contents by category id
     * @param categoryId target category id
     * @param page page number
     * @param rows  page size
     * @return search result
     */
    EasyUIDataGridResult<TbContent> findContentList(Long categoryId, int page, int rows);

    /**
     * save content
     * @param content
     * @return
     */
    MicromallResult saveContent(TbContent content);

    /**
     * find content by category id
     * @param categoryId
     * @return
     */
    List<TbContent> findContentsById(Long categoryId);
}
