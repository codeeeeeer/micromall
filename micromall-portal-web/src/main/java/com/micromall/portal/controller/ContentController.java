package com.micromall.portal.controller;

import com.micromall.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈the controller class of content in home page〉
 *
 * @author LewJay
 * @create 2018/6/1 20:58
 */
@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;
}
