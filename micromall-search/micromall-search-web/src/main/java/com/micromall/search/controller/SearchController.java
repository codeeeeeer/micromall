package com.micromall.search.controller;

import com.micromall.commonPojo.SearchResult;
import com.micromall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 〈the class of search controller〉
 *
 * @author LewJay
 * @create 2018/6/7 22:09
 */
@Controller
public class SearchController {
    @Value("${search-page-rows}")
    private int pageRows;
    @Autowired
    private SearchService searchService;

    @RequestMapping("search")
    public String search(String keyword,
                         @RequestParam(value = "rows", defaultValue = "1")int page,
                         Model model) throws Exception{
        keyword = keyword != null ? new String(keyword.getBytes("iso8859-1"), "utf-8") : null;
        SearchResult searchResult = searchService.findSearchIndex(keyword, page, pageRows);
        model.addAttribute("recourdCount", searchResult.getRecordCount());
        model.addAttribute("totalPages", searchResult.getTotalPages());
        model.addAttribute("query", keyword);
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);

        return "search";
    }
}
