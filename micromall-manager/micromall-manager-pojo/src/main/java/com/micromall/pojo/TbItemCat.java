package com.micromall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbItemCat  implements Serializable {
    private Long id;

    private Long parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Boolean isParent;

    private Date created;

    private Date updated;
}