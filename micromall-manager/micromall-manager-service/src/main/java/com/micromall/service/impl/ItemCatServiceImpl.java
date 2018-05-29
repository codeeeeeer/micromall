package com.micromall.service.impl;

import com.micromall.commonPojo.EasyUICatTreeNode;
import com.micromall.mapper.TbItemCatMapper;
import com.micromall.pojo.TbItemCat;
import com.micromall.pojo.TbItemCatExample;
import com.micromall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈the implemention of item category service〉
 *
 * @author LewJay
 * @create 2018/5/27 9:03
 */
@Service("itemCatService")
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<EasyUICatTreeNode> findCategoryByPid(Long pid) {
        TbItemCatExample itemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = itemCatExample.createCriteria();
        criteria.andParentIdEqualTo(pid);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(itemCatExample);
        List<EasyUICatTreeNode> result = new ArrayList<>();
        for (TbItemCat itemCat:
                tbItemCats) {
            EasyUICatTreeNode node = new EasyUICatTreeNode();
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            node.setState(itemCat.getIsParent() ? "closed" : "open");
            result.add(node);
        }
        return result;
    }
}
