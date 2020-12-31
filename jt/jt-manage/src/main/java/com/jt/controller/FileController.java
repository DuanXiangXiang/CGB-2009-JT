package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * 文件上传的入门案例
     * 1.url地址: /file
     * 2.请求参数: XXXX fileImage
     * 3.返回值:   返回String数据类型
     * SpringMVC 动态提供了顶级接口,用来实现文件上传
     *
     * 文件上传步骤:
     *      目录   文件名称    文件存储路径
     */
    @RequestMapping("/file")
    public String file(MultipartFile fileImage) throws IOException {

        String dirPath = "D:/JT-SOFT/images";
        File dirFile = new File(dirPath);
        if (!dirFile.exists()){
            //如果不存在，需要创建目录
            dirFile.mkdirs();//一次性创建多级目录
        }
        //动态获取图片存储目录
        String fileName = fileImage.getOriginalFilename();
        //确定文件上传的全路径    D:/JT-SOFT/images/abc.jpg
        File file = new File(dirPath + "/" + fileName);
        fileImage.transferTo(file);

        return "文件上传成功";
    }

    /**
     * 实现文件上传
     * url地址：http://localhost:8091/pic/upload?dir=image
     * 参数：uploadFile
     * 返回值：ImageVO对象
     *
     * 文件上传考虑哪些：
     *      1. 校验图片类型   .jpg
     *      2. 校验是否为恶意程序
     *      3. 分目录存储
     *      4. 实现文件上传
     */
    @RequestMapping("/pic/upload")
    public ImageVO upload(MultipartFile uploadFile){

        return fileService.upload(uploadFile);
    }

}
