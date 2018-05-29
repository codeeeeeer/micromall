package com.micromall.service;

import com.micromall.commonPojo.EasyUICatTreeNode;

import java.util.List;

/**
 * 〈the service classs of item category〉
 *
 * @author LewJay
 * @create 2018/5/27 8:55
 */
public interface ItemCatService {
    /**
     * search category information with parent id
     * @param pid parent id
     * @return the item catefgory with parent id - pid
     */
    List<EasyUICatTreeNode> findCategoryByPid(Long pid);
}
