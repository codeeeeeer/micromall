package com.micromall.commonPojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 〈the page info of item list〉
 *
 * @author liujie
 * @create 2018/05/20 17:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasyUIDataGridResult<T> implements Serializable {
    Long total;
    List<T> rows;
}
