package com.micromall.commonPojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 〈the result of single picture uploading〉
 *
 * @author LewJay
 * @create 2018/5/28 22:04
 */
@Data
@AllArgsConstructor
public class PictureUploadResult implements Serializable {
    /**
     * 上传图片返回值，成功：0	失败：1
     */
    private Integer error;
    /**
     * 回显图片使用的url
     */
    private String url;
    /**
     * 错误时的错误消息
     */
    private String message;
    public PictureUploadResult(Integer state, String url) {
        this.url = url;
        this.error = state;
    }
}
