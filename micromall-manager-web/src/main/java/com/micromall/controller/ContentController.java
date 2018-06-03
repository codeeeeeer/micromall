package com.micromall.controller;

import com.micromall.commonPojo.EasyUIDataGridResult;
import com.micromall.commonPojo.MicromallResult;
import com.micromall.content.service.ContentService;
import com.micromall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 〈the controller class of content〉
 *
 * @author LewJay
 * @create 2018/6/1 6:43
 */
@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult<TbContent> listContent(Long categoryId,
                                                       int page, int rows){
        return contentService.findContentList(categoryId, page, rows);
    }

    @RequestMapping("/content/save")
    @ResponseBody
    public MicromallResult saveContent(TbContent content){
        Date date = new Date();
        content.setCreated(date);
        content.setUpdated(date);
        return contentService.saveContent(content);
    }
}
