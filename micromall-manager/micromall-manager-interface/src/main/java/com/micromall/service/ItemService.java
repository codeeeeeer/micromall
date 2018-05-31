package com.micromall.service;

import com.micromall.commonPojo.AppEnum;
import com.micromall.commonPojo.EasyUIDataGridResult;
import com.micromall.commonPojo.MicromallResult;
import com.micromall.pojo.TbItem;

/**
 * 〈the service service of item〉
 *
 * @author liujie
 * @create 2018/05/15 20:44
 */
public interface ItemService {
    /**
     * get item by primary key
     * @param itemId    primary key
     * @return the item found by primary key
     */
    TbItem getItemById(Long itemId);

    /**
     *  add new item
     * @param item  the base information of item
     * @param description   the description of the new item
     * @return the result of the operation
     */
    MicromallResult addItem(TbItem item, String description);

    /**
     * get items
     * @param pageNum page number
     * @param pageSize page size
     * @return the result of the operation
     */
    EasyUIDataGridResult<TbItem> getItems(Integer pageNum, Integer pageSize);

    /**
     * instock item by item ids
     * @param idsStr the ids of items,joined by ','
     * @param targetStatus the target item status
     * @return the result of the operation
     */
    MicromallResult updateItemStatus(String idsStr, AppEnum.ItemStatus targetStatus);

    /**
     * get item description by item id
     * @param itemId    target item id
     * @return  result with item description
     */
    MicromallResult findItemDesc(Long itemId);
}
