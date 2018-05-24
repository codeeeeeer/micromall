package com.test;

import com.micromall.commonPojo.EasyUIDataGridResult;
import com.micromall.pojo.TbItem;
import com.micromall.service.ItemService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 〈test of page helper〉
 *
 * @author liujie
 * @create 2018/05/20 17:31
 */
public class PageHelperTest {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        ItemService itemService = context.getBean(ItemService.class);
        EasyUIDataGridResult<TbItem> items = itemService.getItems(1, 20);
        System.out.println(items);
    }
}
