package com.micromall.controller;

import com.micromall.commonPojo.AppEnum;
import com.micromall.commonPojo.EasyUIDataGridResult;
import com.micromall.commonPojo.MicromallResult;
import com.micromall.pojo.TbItem;
import com.micromall.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 〈the controller of item 〉
 *
 * @author liujie
 * @create 2018/05/15 20:53
 */
@Controller
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{itemId}")
    @ResponseBody
    public TbItem getItem(@PathVariable("itemId") Long itemId){
        TbItem itemById = itemService.getItemById(itemId);
        return itemById;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult<TbItem> list(int page, int rows){
        EasyUIDataGridResult<TbItem> items = itemService.getItems(page, rows);
        return items;
    }

    @RequestMapping("/item/save")
    @ResponseBody
    public MicromallResult save(TbItem item, String desc){
        return itemService.addItem(item, desc);
    }

    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public MicromallResult instock(String ids){
        return itemService.updateItemStatus(ids, AppEnum.ItemStatus.OFF_SHELF);
    }

    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public MicromallResult reshelf(String ids){
        return itemService.updateItemStatus(ids, AppEnum.ItemStatus.ON_SHELF);
    }

    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public MicromallResult delete(String ids){
        return itemService.updateItemStatus(ids, AppEnum.ItemStatus.DELETED);
    }

    @RequestMapping("/rest/item/query/item/desc/{itemId}")
    @ResponseBody
    public MicromallResult delete(@PathVariable("itemId") Long itemId){
        return itemService.findItemDesc(itemId);
    }


}
