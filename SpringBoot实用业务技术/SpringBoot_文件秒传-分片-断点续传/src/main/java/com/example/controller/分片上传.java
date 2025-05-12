package com.example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-15 21:00
 * @apiNote TODO 大文件分片上传
 */
@Controller
public class 分片上传 {

    @Autowired
    private ResourceLoader resourceLoader;

    // 存放分片文件的路径
    public static final String UPLOAD_PATH = System.getProperty("user.dir")
            + "/SpringBoot_文件秒传-分片-断点续传/src/main/resources/static";

    // 使用 currentHashMap，保证多线程下数据安全
    private Map<String, List<File>> chunksMap = new ConcurrentHashMap<>();


    @RequestMapping("/sharding")
    public String sharding() {
        return "sharding";
    }


    // 文件的分片上传
    @PostMapping("/file/upload")
    public @ResponseBody void upload(@RequestParam int currentChunk,
                                     @RequestParam int totalChunks,
                                     @RequestParam MultipartFile chunk,
                                     @RequestParam String fileName) throws IOException {
        // 将分片保存到临时文件夹中
        String chunkName = chunk.getOriginalFilename() + "." + currentChunk;
        File chunkFile = new File(UPLOAD_PATH, chunkName);
        // 使用 Spring 框架transferTo()方法：将所指向的 'chunk' 文件上传到对应的 'chunkFile' 目录下
        chunk.transferTo(chunkFile);
        // 记录分片上传状态
        List<File> chunkList = chunksMap.computeIfAbsent(fileName, k -> new ArrayList<>(totalChunks));
        chunkList.add(chunkFile);
    }


    // 将分片文件合并
    @PostMapping("/file/merge")
    public @ResponseBody String merge(@RequestParam String fileName) throws IOException {
        // 获取所有分片，并按照分片的顺序将它们合并成一个文件
        List<File> chunkList = chunksMap.get(fileName);
        if (chunkList == null || chunkList.isEmpty()) {
            throw new RuntimeException("分片不存在");
        }
        File outputFile = new File(UPLOAD_PATH, fileName);
        // 获取FileChannel：是一个可以读写文件内容的通道，它提供了更多的文件操作功能，比如文件锁定、直接内存读写等。
        try (FileChannel outChannel = new FileOutputStream(outputFile).getChannel()) {
            for (int i = 0; i < chunkList.size(); i++) {
                try (FileChannel inChannel = new FileInputStream(chunkList.get(i)).getChannel()) {
                    // 将分片文件内容写入到输出文件中
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                }
                chunkList.get(i).delete(); // 删除分片
            }
        }
        chunksMap.remove(fileName); // 删除记录
        // 获取文件的访问URL
        Resource resource = resourceLoader.getResource("file:" + UPLOAD_PATH + fileName);
        // 由于是本地文件，所以开头是"file"，如果是服务器，请改成自己服务器前缀
        return resource.getURI().toString();
    }
}
