package com.micromall.commonPojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 〈the class of search result〉
 *
 * @author LewJay
 * @create 2018/6/7 20:56
 */
@Data
public class SearchResult implements Serializable {
    private List<SearchItemResult> itemList;
    private Long recordCount;
    private int totalPages;
}
