package com.micromall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micromall.commonPojo.AppEnum;
import com.micromall.commonPojo.EasyUIDataGridResult;
import com.micromall.commonUtils.IDUtils;
import com.micromall.commonPojo.MicromallResult;
import com.micromall.mapper.TbItemDescMapper;
import com.micromall.mapper.TbItemMapper;
import com.micromall.pojo.TbItem;
import com.micromall.pojo.TbItemDesc;
import com.micromall.pojo.TbItemExample;
import com.micromall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> tbItems = itemMapper.selectByExample(example);
        return tbItems == null || tbItems.size() == 0 ? null : tbItems.get(0);
    }

    @Override
    public MicromallResult addItem(TbItem item, String description) {
        Date currentDate = new Date();
        long itemId = IDUtils.generateItemId();
        item.setId(itemId);
        item.setCreated(currentDate);
        item.setUpdated(currentDate);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus(AppEnum.ItemStatus.ON_SHELF.getCode());

        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setCreated(currentDate);
        itemDesc.setUpdated(currentDate);
        itemDesc.setItemDesc(description);

        itemMapper.insert(item);
        itemDescMapper.insert(itemDesc);
        return MicromallResult.ok("商品添加成功");
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

    @Override
    public MicromallResult updateItemStatus(String idsStr, AppEnum.ItemStatus targetStatus) {
        String[] ids = idsStr.split(",");
        Date date = new Date();
        for (String id:
                ids) {
            TbItem item = itemMapper.selectByPrimaryKey(Long.parseLong(id));
            if (item == null) continue;
            item.setStatus(targetStatus.getCode());
            item.setUpdated(date);
            itemMapper.updateByPrimaryKey(item);
        }
        return MicromallResult.ok("操作成功！");
    }

    @Override
    public MicromallResult findItemDesc(Long itemId) {
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        return MicromallResult.ok(itemDesc);
    }

}
