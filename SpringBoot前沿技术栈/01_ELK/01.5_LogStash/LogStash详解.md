## LogStash

配置环境变量：LS_JAVA_HOME

启动命令：
1、logstash -e 'input { stdin {} } output { stdout {} }'
   -e                    表示 “executor”，执行
   input { stdin {} }    从标准输入（键盘）读取数据。
   output { stdout {} }  将数据输出到标准输出（控制台）。
2、logstash -e 'input { generator { count => 1 message => "哈啰" } } output { stdout {} }'
   generator  生成器，生成数据。
   count      生成数据的次数。
   message    生成的数据。
3、logstash -f ./logstash.conf
   -f：指定配置文件。
   logstash.conf：需要自己提供的配置文件，指定输入、过滤、输出。


logstash-codec-json_lines 是 Logstash 的一个编解码器插件，主要用于处理 每行一个独立 JSON 对象 的数据格式。
安装命令：logstash-plugin install logstash-codec-json_lines
使用示例：
```
input {
  stdin {
    message => "{'name':'张三','age':18}"
    codec => json_lines  # 指定编解码器，将JSON对象解析为字段
  }
}
output {
  stdout {
    codec => json_lines # 指定编解码器，将字段解析为JSON字符串
  }
}
```

## Mac电脑使用Homebrew安装LogStash
  Logstash 9.x 默认要求 pipelines.yml 非空，而 Homebrew 安装可能未初始化此文件。
  因此，需要在 pipelines.yml 文件中定义管道配置文件路径。
```
# 在 /opt/homebrew/etc/logstash/pipelines.yml 中添加以下内容
- pipeline.id: main
  path.config: "/opt/homebrew/etc/logstash/*.conf"
  pipeline.workers: 1
  
pipeline.id：定义管道名称（这里是 main）
path.config：指定了 Logstash 配置文件的路径（支持通配符 *.conf）
pipeline.workers：设置工作线程数为 1（测试环境建议先用单线程）

作用：启动一个名为 main 的管道，加载 /opt/homebrew/etc/logstash/ 目录下所有 .conf 文件作为配置，
     使用单线程运行（避免复杂环境下的并发问题）
```
