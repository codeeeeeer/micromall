package com.micromall.content.service;

import com.micromall.commonPojo.EasyUICatTreeNode;
import com.micromall.commonPojo.MicromallResult;

import java.util.List;

/**
 * 〈the interface of content category〉
 *
 * @author LewJay
 * @create 2018/5/31 20:24
 */
public interface ContentCatService {
    /**
     * find children nodes with this parent id
     * @param parentId  the parent id
     * @return  the children nodes
     */
    List<EasyUICatTreeNode> findChildrenNode(Long parentId);

    /**
     * add new node
     * @param parentId new node's parent id
     * @param newNodeName new node's name
     * @return result with new node's id
     */
    MicromallResult addNode(Long parentId, String newNodeName);

    /**
     * update node's name by node's id
     * @param id    node's id
     * @param newNodeName   node's new name
     * @return operation result
     */
    MicromallResult updateNodeName(Long id, String newNodeName);

    /**
     * delete node by id
     * @param id    node id
     * @return operation result
     */
    MicromallResult deleteNode(Long id);
}
