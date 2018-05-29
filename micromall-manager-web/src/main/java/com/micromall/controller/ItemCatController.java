package com.micromall.controller;

import com.micromall.commonPojo.EasyUICatTreeNode;
import com.micromall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 〈the controller of item category〉
 *
 * @author LewJay
 * @create 2018/5/27 8:54
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;


    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUICatTreeNode> categoryList(@RequestParam(value = "id", defaultValue = "0") Long pid){
        List<EasyUICatTreeNode> categoryByPid = itemCatService.findCategoryByPid(pid);
        return categoryByPid;
    }
}
