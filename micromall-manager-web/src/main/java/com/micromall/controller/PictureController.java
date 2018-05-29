package com.micromall.controller;

import com.micromall.commonUtils.FastDFSClient;
import com.micromall.commonUtils.JsonUtils;
import com.micromall.commonPojo.PictureUploadResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈the controller class of picture〉
 *
 * @author LewJay
 * @create 2018/5/28 20:01
 */
@Controller
@Slf4j
public class PictureController {
    @Value("${image-server-url}")
    private String trackerServerUrl;

    @Autowired
    private FastDFSClient fastDFSClient;

    @RequestMapping(value = "/pic/upload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    @ResponseBody
    public String uploadPictures(MultipartFile uploadFile){
        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        PictureUploadResult result;
        try {
            String uploadResult = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            String url = trackerServerUrl + "/" + uploadResult;
            result = new PictureUploadResult(0, url);
        } catch (Exception e) {
            result = new PictureUploadResult(1, null, "图片上传失败");
        }
        return JsonUtils.objectToJson(result);
    }
}
