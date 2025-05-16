package com.example;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 游家纨绔
 */
@EnableDubbo  // 开启dubbo配置
@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")
public class ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}

	/** Mac电脑使用HomeBrew安装Zookeeper注意事项：
	 *    ZooKeeper 默认会从自己的安装目录（如 /opt/homebrew/Cellar/zookeeper/3.9.3/libexec/etc/zookeeper/）查找配置文件，
	 *    但 Homebrew 将配置文件安装在了 /opt/homebrew/etc/zookeeper/
	 *    因此需要将配置文件链接到 /opt/homebrew/Cellar/zookeeper/3.9.3/libexec/etc/zookeeper/zoo.cfg
	 *    可以使用以下命令创建链接：
	 *        mkdir -p /opt/homebrew/Cellar/zookeeper/3.9.3/libexec/etc/zookeeper
	 *        ln -s /opt/homebrew/etc/zookeeper/zoo.cfg /opt/homebrew/Cellar/zookeeper/3.9.3/libexec/etc/zookeeper/
	 *        分析：
	 *            -p：表示递归创建目录。
	 *            mkdir -p /opt/homebrew/Cellar/zookeeper/3.9.3/libexec/etc/zookeeper 表示递归创建目录。
	 *            ln：Linux/macOS 中创建链接的命令。
	 *            -s：表示创建的是符号链接（Symbolic Link），而不是硬链接。类似于 Windows 中的“快捷方式”。
	 */
}
