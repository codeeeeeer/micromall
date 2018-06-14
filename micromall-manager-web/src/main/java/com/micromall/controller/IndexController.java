package com.micromall.controller;

import com.micromall.commonPojo.MicromallResult;
import com.micromall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈the controller class of index〉
 *
 * @author LewJay
 * @create 2018/6/6 21:10
 */
@Controller
public class IndexController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/index/item/import")
    @ResponseBody
    public MicromallResult importIndex(){
         return searchService.findItems();
    }
}
