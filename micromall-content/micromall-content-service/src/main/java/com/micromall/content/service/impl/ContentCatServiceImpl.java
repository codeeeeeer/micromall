package com.micromall.content.service.impl;

import com.micromall.commonPojo.AppEnum;
import com.micromall.commonPojo.EasyUICatTreeNode;
import com.micromall.commonPojo.MicromallResult;
import com.micromall.content.service.ContentCatService;
import com.micromall.mapper.TbContentCategoryMapper;
import com.micromall.pojo.TbContentCategory;
import com.micromall.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈the implementation of content category service〉
 *
 * @author LewJay
 * @create 2018/5/31 20:32
 */
@Service("contentCatService")
public class ContentCatServiceImpl implements ContentCatService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUICatTreeNode> findChildrenNode(Long parentId) {
        TbContentCategoryExample categoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria categoryExampleCriteria = categoryExample.createCriteria();
        categoryExampleCriteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> categories = contentCategoryMapper.selectByExample(categoryExample);

        List<EasyUICatTreeNode> result = new ArrayList<>();
        for (TbContentCategory category:
                categories) {
            EasyUICatTreeNode node = new EasyUICatTreeNode();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent() ? AppEnum.CategoryIsParent.CLOSED.getCode()
                    : AppEnum.CategoryIsParent.OPEN.getCode());
            result.add(node);
        }
        return result;
    }

    @Override
    public MicromallResult addNode(Long parentId, String newNodeName) {
        Date currentDate = new Date();
        TbContentCategory category = new TbContentCategory();
        category.setCreated(currentDate);
        category.setIsParent(AppEnum.CategoryIsParent.OPEN.isHasChild());
        category.setParentId(parentId);
        category.setUpdated(currentDate);
        category.setName(newNodeName);
        category.setStatus(AppEnum.CategoryStatus.NORMAL.getCode());

        contentCategoryMapper.insert(category);

        TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (parentCategory != null && ! parentCategory.getIsParent()){
            parentCategory.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentCategory);
        }
        return MicromallResult.ok(category.getId());
    }

    @Override
    public MicromallResult updateNodeName(Long id, String newNodeName) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setId(id);
        contentCategory.setUpdated(new Date());
        contentCategory.setName(newNodeName);
        contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
        return MicromallResult.ok();
    }

    @Override
    public MicromallResult deleteNode(Long id) {
        TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
        deleteCategory(category);

        //检查兄弟节点是不是已经全部被删除了，如果全部被删除了，那么要关闭父节点
        TbContentCategoryExample categoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andParentIdEqualTo(category.getParentId());
        List<TbContentCategory> categories = contentCategoryMapper.selectByExample(categoryExample);
        for (TbContentCategory category1:
                categories) {
            //只有还有至少一个兄弟节点没有被删除，那就不用关闭父节点
            if (AppEnum.CategoryStatus.NORMAL.getCode().equals(category1.getStatus())){
                return MicromallResult.ok();
            }
        }
        //父节点已经没有子节点了，关闭父节点
        TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(category.getParentId());
        parentCategory.setIsParent(AppEnum.CategoryIsParent.OPEN.isHasChild());
        parentCategory.setUpdated(new Date());
        contentCategoryMapper.updateByPrimaryKey(parentCategory);
        return MicromallResult.ok();
    }

    private void deleteCategory(TbContentCategory category){
        //先删除子节点
        deleteAllChildren(category.getId());
        //在删除节点
        category.setIsParent(AppEnum.CategoryIsParent.OPEN.isHasChild());
        category.setStatus(AppEnum.CategoryStatus.DELETED.getCode());
        category.setUpdated(new Date());
        contentCategoryMapper.updateByPrimaryKey(category);
    }

    private void deleteAllChildren(Long parentId){
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> children = contentCategoryMapper.selectByExample(example);
        for (TbContentCategory category:
                children) {
            deleteCategory(category);
        }
    }
}
