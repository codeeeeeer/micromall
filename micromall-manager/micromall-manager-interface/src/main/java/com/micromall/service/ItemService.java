package com.micromall.service;

import com.micromall.commonPojo.EasyUIDataGridResult;
import com.micromall.pojo.TbItem;

/**
 * 〈the service interface of item〉
 *
 * @author liujie
 * @create 2018/05/15 20:44
 */
public interface ItemService {
    TbItem getItemById(Long itemId);
    EasyUIDataGridResult<TbItem> getItems(Integer pageNum, Integer pageSize);
}
