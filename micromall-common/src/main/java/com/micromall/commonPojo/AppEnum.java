package com.micromall.commonPojo;

import lombok.Getter;

/**
 * 〈the enums of the application〉
 *
 * @author LewJay
 * @create 2018/5/29 20:41
 */
public class AppEnum {
    /**
     * 商品状态，1-正常，2-下架，3-删除
     */
    public enum ItemStatus{
        ON_SHELF((byte)1, "正常"),
        OFF_SHELF((byte)2, "下架"),
        DELETED((byte)3, "删除")
        ;
        @Getter private byte code;
        @Getter private String description;
        ItemStatus(byte code, String description) {
            this.code = code;
            this.description = description;
        }
    }
}
