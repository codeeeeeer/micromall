package com.micromall.controller;

import com.micromall.commonPojo.EasyUICatTreeNode;
import com.micromall.commonPojo.MicromallResult;
import com.micromall.content.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 〈the controller class of content category〉
 *
 * @author LewJay
 * @create 2018/5/31 20:04
 */
@Controller
public class ContentCatController {
    @Autowired
    private ContentCatService contentCatService;

    @RequestMapping(value = "/content/category/list", method = RequestMethod.GET)
    @ResponseBody
    public List<EasyUICatTreeNode> catList(@RequestParam(value = "id",
            defaultValue = "0") Long parentId){
        return contentCatService.findChildrenNode(parentId);
    }

    @RequestMapping("/content/category/create")
    @ResponseBody
    public MicromallResult create(@RequestParam(value = "parentId") Long parentId,
                                  @RequestParam(value = "name") String newNodeName){
        return contentCatService.addNode(parentId, newNodeName);
    }

    @RequestMapping("/content/category/update")
    @ResponseBody
    public MicromallResult update(@RequestParam(value = "id") Long id,
                                  @RequestParam(value = "name") String newNodeName){
        return contentCatService.updateNodeName(id, newNodeName);
    }

    @RequestMapping("/content/category/delete")
    @ResponseBody
    public MicromallResult delete(@RequestParam(value = "id") Long id){
        return contentCatService.deleteNode(id);
    }
}
