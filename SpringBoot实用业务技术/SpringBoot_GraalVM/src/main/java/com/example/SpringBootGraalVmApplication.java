package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@SpringBootApplication
public class SpringBootGraalVmApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringBootGraalVmApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGraalVmApplication.class, args);

        // 使用默认浏览器打开
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://localhost:8080/");
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open http://localhost:8080/");
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                Runtime.getRuntime().exec("xdg-open http://localhost:8080/");
            } else {
                // Handle other OS types
            }
        } catch (IOException e) {
            log.warn("打开客户端主页失败", e);
        }
        // Runtime.getRuntime().exec(String.format("cmd /c start %s", "http://localhost:8080/index.html"));
    }

    @RequestMapping("/test")
    public String test() {
        return "您好！使用GraalVM构建SpringBoot项目完成✌️";
    }

    /**
     * 实现Mac系统GraalVm构建SpringBoot项目流程：
     * 1、到GraalVM官网下载
     * 2、解压到 /Library/Java/JavaVirtualMachines 包下
     * 3、配置环境变量：（其他的JAVA_HOME先注释一下）
     *    export JAVA_HOME=/Library/Java/JavaVirtualMachines/graalvm-jdk-21.0.2+13.1/Contents/Home
     *    export PATH=/Library/Java/JavaVirtualMachines/graalvm-jdk-21.0.2+13.1/Contents/Home/bin:$PATH
     *    export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-jdk-21.0.2+13.1/Contents/Home
     * 4、Mac系统配置完GraalVM环境后，执行java -version 会显示GraalVM文件已损坏
     *    需要执行一下：sudo xattr -r -d com.apple.quarantine /Library/Java/JavaVirtualMachines 即可
     * 5、创建SpringBoot项目，注意：在勾选依赖的时候加上：GraalVM Native Support
     *    <plugin>
     *        <groupId>org.graalvm.buildtools</groupId>
     *        <artifactId>native-maven-plugin</artifactId>
     *    </plugin>
     * 6、使用终端的方式(IDEA构建报错)进行Maven命令构建：mvn -Pnative native:compile  （构建时间较长）
     * 7、在该SpringBoot项目的target目录下生成一个exe文件(80.5MB)，可无需Java环境即可运行，且运行速度极快。
     */
}
