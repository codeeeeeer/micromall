package com.micromall.portal.controller;

import com.micromall.content.service.ContentService;
import com.micromall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 〈the controller class of page management〉
 *
 * @author LewJay
 * @create 2018/5/30 22:07
 */
@Controller
public class PageController {
    @Value("${homepage.broadcast.pictures.id}")
    private Long broadcastPicturesId;

    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String homePage(Model model){
        List<TbContent> broadcastContent = contentService.findContentsById(broadcastPicturesId);
        model.addAttribute("ad1List", broadcastContent);
        return "index";
    }
}
