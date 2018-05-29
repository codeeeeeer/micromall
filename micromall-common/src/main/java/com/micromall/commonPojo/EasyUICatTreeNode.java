package com.micromall.commonPojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈the tree node of  ui category〉
 *
 * @author LewJay
 * @create 2018/5/27 8:50
 */
@Data
public class EasyUICatTreeNode implements Serializable {
    Long id;
    String text;
    String state;
}
