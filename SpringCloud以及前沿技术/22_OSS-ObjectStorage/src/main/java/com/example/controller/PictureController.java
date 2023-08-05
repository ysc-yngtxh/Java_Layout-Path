package com.example.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.UUID;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-05 15:30
 * @apiNote TODO 上传图片
 */
@Controller
public class PictureController {

    private static String upPath = "https://ysc-test0322.oss-cn-shenzhen.aliyuncs.com/";

    // @RequestMapping("/")
    // public String index(){
    //     return "updown";
    // }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "上传文件失败";
        }
        String filename = file.getOriginalFilename();
        String substr = filename.substring(filename.indexOf("."));
        String reFileName = UUID.randomUUID().toString().replace("-", "") + substr;

        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        String accessKeyId = "LTAI5tMGA97nbLGRdSVBqZxQ";
        String accessKeySecret = "VPBZ6qcRNlFdJ5PE2eFlOQ87y0XKIO";
        OSS ossClient = new OSSClientBuilder().build(
                endpoint          // 地域节点
                , accessKeyId     // 访问密钥 ID
                , accessKeySecret // 连接秘钥
        );
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/jpg");
        objectMetadata.setContentDisposition("inline");
        ossClient.putObject(
                "ysc-test0322"  // 列表名
                , reFileName    // 文件名
                , file.getInputStream()  //文件流
                , objectMetadata
        );
        ossClient.shutdown();
        return upPath + reFileName;
    }
}
