package com.cn.leyou.service;

import com.cn.leyou.enums.ExceptionEnum;
import com.cn.leyou.exception.LyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UploadService {

    private static final List<String> ALLOW_TYPES = Arrays.asList("image/jpeg","image/png","image/bmp","image/jpg");

    public String uploadImage(MultipartFile file) {
        try {
            //校验文件类型,避免什么文件都上传，万一病毒怎么办？
            String contentType = file.getContentType();   //文件的类型
            if(!ALLOW_TYPES.contains(contentType)){       //contains是比较文件的后缀名中是否有ALLOW_TYPES中的类型
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //校验文件内容，简单的校验
            BufferedImage image = ImageIO.read(file.getInputStream());  //去读输入流文件image的图片属性
            if(image == null){
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }

            //准备目标路径
            File dest = new File("D:\\IDEA", Objects.requireNonNull(file.getOriginalFilename()));//桌面路径加上文件名字
            //保存文件到本地
            file.transferTo(dest);

            //返回路径
            return "http://image.leyou.com/" + file.getOriginalFilename();

        } catch (IOException e) {
            //上传失败
            log.error("上传文件失败！",e);
            throw new LyException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }
    }
}
