package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService {
    @Value("${image.localDir}")
    private String localDir;
    @Value("${image.urlName}")
    private String urlName;
    private static Set set = new HashSet();

    static {
        set.add(".jpg");
        set.add(".png");
        set.add(".gif");
    }

    /**
     * 1. 校验图片类型
     * 2. 校验是否为恶意程序
     * 3. 采用分目录方式进行存储
     * 4. 防止文件重名 动态生成ID
     *
     * @param uploadFile
     * @return
     */
    @Override
    public ImageVO upload(MultipartFile uploadFile) {
        //1. 获取图片名称 a.jpg | A.JPG
        String filename = uploadFile.getOriginalFilename();
        //得到的文件名不一定全是小写的，这里需要将其转为小写
        filename = filename.toLowerCase();
        //2. 校验是否为图片
        String fileType = filename.substring(filename.lastIndexOf("."));
        if (!set.contains(fileType)) {
            //如果不属于类型，则表示不是图片
            return ImageVO.fail();
        }
        //3.校验是否为恶意程序，将数据转化为图片类型检查是否正常转化
        try {
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            if (width == 0 || height == 0) {
                return ImageVO.fail();
            }
            //4.hash方式/时间方式
            String dateDir = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
            //5.定义磁盘文件存储目录
            String dirPath = localDir + dateDir;
            File dirFile = new File(dirPath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            //6.防止文件重名 动态生成ID      (UUID)
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String realFileName = uuid + fileType;
            //7.实现文件上传
            uploadFile.transferTo(new File(dirPath + realFileName));

//            String url = "https://pro.jd.com/mall/active/nxhvaREgrMeBufDruWu8boqZXB4/index.html";
            String url = urlName+dateDir+realFileName;
            return ImageVO.success(url,width,height);
        } catch (IOException e) {
            e.printStackTrace();
            return ImageVO.fail();
        }

    }
}
