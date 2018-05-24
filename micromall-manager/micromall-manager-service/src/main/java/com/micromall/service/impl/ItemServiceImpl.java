package com.micromall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micromall.commonPojo.EasyUIDataGridResult;
import com.micromall.mapper.TbItemMapper;
import com.micromall.pojo.TbItem;
import com.micromall.pojo.TbItemExample;
import com.micromall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈the implemention of item service〉
 *
 * @author liujie
 * @create 2018/05/15 20:46
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> tbItems = itemMapper.selectByExample(example);
        return tbItems == null || tbItems.size() == 0 ? null : tbItems.get(0);
    }

    @Override
    public EasyUIDataGridResult<TbItem> getItems(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TbItem> tbItems = itemMapper.selectByExample(new TbItemExample());
        PageInfo<TbItem> info = new PageInfo<>(tbItems);
        EasyUIDataGridResult<TbItem> result = new EasyUIDataGridResult<>();
        result.setTotal(info.getTotal());
        result.setRows(tbItems);
        return result;
    }

}
