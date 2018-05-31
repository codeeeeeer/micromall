package com.micromall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈the controller class of page management〉
 *
 * @author LewJay
 * @create 2018/5/30 22:07
 */
@Controller
public class PageController {
    @RequestMapping("/index")
    public String homePage(){
        return "index";
    }
}
