package com.micromall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈the controller of page infomation〉
 *
 * @author liujie
 * @create 2018/05/20 10:00
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping("/{page}")
    public String toPage(@PathVariable("page") String page){
        return page;
    }

    @RequestMapping("/rest/page/{page}")
    public String toRestPage(@PathVariable("page") String page){
        return page;
    }
}
