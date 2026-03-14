package com.example;

import com.example.pdf.XslFoPdfGenerator;
import jakarta.annotation.Resource;
import org.apache.fop.apps.FopFactory;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class SpringBootFopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFopApplication.class, args);
    }

    @Resource
    private XslFoPdfGenerator pdfGenerator;

    @Override
    public void run(String @NonNull ... args) throws Exception {
        // 生成PDF到指定路径
        String parentPath = System.getProperty("user.dir");
        Path path = Paths.get(parentPath, "24_SpringBoot-Fop", "src", "main", "resources", "demo.pdf");
        Files.createFile(path);
        pdfGenerator.generatePdf(path.toString());
        System.out.println("PDF生成成功，路径：" + path);
    }

}
