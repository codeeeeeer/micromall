package com.micromall.controller;

import com.micromall.commonPojo.EasyUIDataGridResult;
import com.micromall.pojo.TbItem;
import com.micromall.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        return itemService.getItemById(itemId);
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult<TbItem> list(int page, int rows){
        log.info(page + "-" + rows);
        EasyUIDataGridResult<TbItem> items = itemService.getItems(page, rows);
        return items;
    }
}
