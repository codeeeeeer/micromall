package com.micromall.search.mapper;

import com.micromall.commonPojo.SearchItemResult;

import java.util.List;

/**
 * 〈the interface of searching item dao〉
 *
 * @author LewJay
 * @create 2018/6/6 19:31
 */
public interface SearchMapper {
    List<SearchItemResult> selectItems();
}
