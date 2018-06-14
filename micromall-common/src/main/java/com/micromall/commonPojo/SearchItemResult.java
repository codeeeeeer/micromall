package com.micromall.commonPojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈the class of searching item result〉
 *
 * @author LewJay
 * @create 2018/6/6 19:21
 */
@Data
public class SearchItemResult implements Serializable {
    private String id;
    private String title;
    private String sell_point;
    private Long price;
    private String image;
    private String category_name;
}
