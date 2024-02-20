package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootGraalVmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGraalVmApplication.class, args);
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
     * 6、执行Maven命令构建：mvn -Pnative native:compile  （构建时间较长）
     * 7、在该SpringBoot项目的target目录下生成一个exe文件(80.5MB)，可无需Java环境即可运行，且运行速度极快。
     */
}
