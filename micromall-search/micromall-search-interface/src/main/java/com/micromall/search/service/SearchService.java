package com.micromall.search.service;

import com.micromall.commonPojo.MicromallResult;
import com.micromall.commonPojo.SearchResult;

import java.io.IOException;

/**
 * 〈the interface of searching service〉
 *
 * @author LewJay
 * @create 2018/6/5 22:22
 */
public interface SearchService{
    MicromallResult findItems();
    SearchResult findSearchIndex(String keyWord, int pageNum, int pageSize) throws Exception;
    MicromallResult addIndexById(Long itemId) throws Exception;
}
